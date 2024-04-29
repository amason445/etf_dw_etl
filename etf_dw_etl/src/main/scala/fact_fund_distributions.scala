import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame

import java.nio.file.{Files, Paths}
import scala.collection.JavaConverters._

import scala.collection.mutable.ListBuffer


class DistributionsFactTable(val spark: SparkSession, val folder_path: String) {


    val dfs_list = ListBuffer[DataFrame]()

    val csvFiles = Files.list(Paths.get(folder_path))
        .iterator()
        .asScala
        .filter(_.toString.endsWith(".csv"))
        .toList

    def generateDistibutionDataFrame(): DataFrame = {
        for (csv_file <- csvFiles) {
            println(csv_file)

            val raw_df = spark.read
            .option("header", "true")
            .csv(csv_file.toString)

            dfs_list += raw_df
        }

        val finalDF = dfs_list.reduceLeft(_.union(_))

        return finalDF
    }
}