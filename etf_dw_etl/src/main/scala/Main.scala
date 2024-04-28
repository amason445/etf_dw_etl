import org.apache.spark.sql.SparkSession
//import org.tomlj.TomlParseResult

import java.nio.file.{Files, Paths}
import scala.collection.JavaConverters._

object MainApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("ETF_DW ETL Package")
      .config("spark.master","local")
      .getOrCreate()

      val directory_path = "C:\\Users\\Administrator\\OneDrive\\SQLServer\\ETF_DW\\RawData\\Distributions\\"
      
      val csvFiles = Files.list(Paths.get(directory_path))
        .iterator()
        .asScala
        .filter(_.toString.endsWith(".csv"))
        .toList

      for (csv_file <- csvFiles) {
        println(csv_file)

        val test_df = spark.read
        .option("header", "true")
        .csv(csv_file.toString)

        //test_df = test_df.withColumn(("ExDate"))

        test_df.printSchema()
        test_df.show()

      }

      spark.stop()

  }

} 
