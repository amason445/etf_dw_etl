import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.to_date


import scala.collection.JavaConverters._
import org.apache.hadoop.hdfs.util.ByteArrayManager.Conf

object MainApp {
  def main(args: Array[String]): Unit = {

      // load configuration variables
      ConfigLoader.loadProperties()
      
      val spark_temp_folder = ConfigLoader.getSparkTempFolderPath()

      val jdbc_server_name = ConfigLoader.getJDBCServerName()
      val jdbc_server_port = ConfigLoader.getJDBCServerPort()
      val jdbc_database_name = ConfigLoader.getJDBCDatabaseName()
      val jdbc_server_username = ConfigLoader.getJDBCServerUsername()
      val jdbc_server_password = ConfigLoader.getJDBCServerPassword()

      val distributions_folder_path = ConfigLoader.getDistributionsFolderPath()

      val jdbcUrl = "jdbc:sqlserver://" + jdbc_server_name + ":" + jdbc_server_port + ";databaseName=" + jdbc_database_name +";encrypt=false"

      val connectionProperties = new java.util.Properties()
      connectionProperties.put("user", jdbc_server_username)
      connectionProperties.put("password", jdbc_server_password)

      val spark = SparkSession.builder()
      .appName("ETF_DW ETL Package")
      .config("spark.master","local")
      .config("spark.local.dir", spark_temp_folder)
      .getOrCreate()
      
      val DistributionsJob = new DistributionsFactTable(spark, distributions_folder_path)

      val DistributionsDF = DistributionsJob.generateDistibutionDataFrame()
        
      DistributionsDF.write
        .mode("append")
        .jdbc(jdbcUrl, "etf_raw.fact_fund_distributions", connectionProperties)

      spark.stop()

  }

} 
