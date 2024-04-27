import org.apache.spark.sql.SparkSession
object MainApp {
  def main(args: Array[String]): Unit = {
  val spark = SparkSession.builder()
    .appName("ETF_DW ETL Package")
    .config("spark.master","local")
    .getOrCreate()

    println("Hello Spark!")

    spark.stop()

  }

} 
