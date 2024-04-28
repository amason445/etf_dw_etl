import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.to_date


import java.nio.file.{Files, Paths}
import scala.collection.JavaConverters._

object MainApp {
  def main(args: Array[String]): Unit = {

      // load configuration variables
      ConfigLoader.loadProperties()
      
      val spark_temp_folder = ConfigLoader.getSparkTempFolderPath()
      val jdbc_path = ConfigLoader.getJDBCFilePath()

      val directory_path = ConfigLoader.getDistributionsFolderPath()

      val spark = SparkSession.builder()
      .appName("ETF_DW ETL Package")
      .config("spark.master","local")
      .config("spark.local.dir", spark_temp_folder)
      .config("spark.jars", jdbc_path)
      .getOrCreate()
      
      val csvFiles = Files.list(Paths.get(directory_path))
        .iterator()
        .asScala
        .filter(_.toString.endsWith(".csv"))
        .toList

      for (csv_file <- csvFiles) {
        println(csv_file)

        val raw_df = spark.read
        .option("header", "true")
        .csv(csv_file.toString)

        // convert date columns to date type
        val df_ExDate = raw_df.withColumn("ExDate", to_date(raw_df("ExDate"), "M/d/yyyy"))
        val df_RecordDate = df_ExDate.withColumn("RecordDate", to_date(df_ExDate("RecordDate"), "M/d/yyyy"))
        val df_PayableDate = df_RecordDate.withColumn("PayableDate", to_date(df_RecordDate("PayableDate"), "M/d/yyyy"))

        //convert columns to float
        val columnsToConvert = Seq("TotalDistribution", "Income", "ShortTermCapitalGain", "LongTermCapitalGain", "ReturnOfCapital")
        val df_TotalDistribution = Utilities.convertColumnsToFloat(df_PayableDate, columnsToConvert)

        df_TotalDistribution.printSchema()
        df_TotalDistribution.show()

      }

      spark.stop()

  }

} 
