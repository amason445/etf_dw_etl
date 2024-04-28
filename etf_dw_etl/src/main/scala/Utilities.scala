import org.apache.spark.sql.functions.col

object Utilities {

    def convertColumnsToFloat(df: org.apache.spark.sql.DataFrame, cols: Seq[String]): org.apache.spark.sql.DataFrame = {
        cols.foldLeft(df) { (tempDf, colName) =>
            tempDf.withColumn(colName, col(colName).cast("float"))
          }
        }

}