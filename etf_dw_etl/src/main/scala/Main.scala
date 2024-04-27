import org.apache.spark.sql.SparkSession
import org.tomlj.TomlParseResult

object MainApp {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("ETF_DW ETL Package")
      .config("spark.master","local")
      .getOrCreate()

      val config: TomlParseResult = ConfigLoader.loadConfig("config.toml")

      if (config.hasErrors()) {
        config.errors().forEach(error => println(s"Config error: $error"))
      }
      else {
        val distributionsFolder = config.getString("folder_paths.distributions_folder")

        println(s"The test was successful: $distributionsFolder")

      }

      spark.stop()

  }

} 
