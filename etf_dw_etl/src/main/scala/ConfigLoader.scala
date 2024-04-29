import java.util.Properties
import scala.io.Source

object ConfigLoader {
  
  val props = new Properties()

  def loadProperties(): Unit = {
    // Load the properties file from the class path
    val inputStream = getClass.getResourceAsStream("/config.properties")
    if (inputStream != null) {
      props.load(inputStream)
    } else {
        throw new Exception("Exception ocurred when scraping property file.")
    }
  }

  def getSparkTempFolderPath(): String = {
    // Retrive the distributions folder path as a string
    try {
      val distributionsFolder = props.getProperty("spark_temp_folder")
      return distributionsFolder
    } catch {
        case e: Exception =>
          throw new Exception("Exception thrown when getting Spark Temp Folder: " + e.getMessage())
    }
  } 

    def getJDBCServerName(): String = {
    // Retrive the distributions folder path as a string
    try {
      val distributionsFolder = props.getProperty("server_name")
      return distributionsFolder
    } catch {
        case e: Exception =>
          throw new Exception("Exception thrown when getting JDBC Server Name: " + e.getMessage())
    }
  } 

    def getJDBCServerPort(): String = {
    // Retrive the distributions folder path as a string
    try {
      val distributionsFolder = props.getProperty("server_port")
      return distributionsFolder
    } catch {
        case e: Exception =>
          throw new Exception("Exception thrown when getting JDBC Server Port: " + e.getMessage())
    }
  } 

    def getJDBCDatabaseName(): String = {
    // Retrive the distributions folder path as a string
    try {
      val distributionsFolder = props.getProperty("database_name")
      return distributionsFolder
    } catch {
        case e: Exception =>
          throw new Exception("Exception thrown when getting SQL Server Target Database Name: " + e.getMessage())
    }
  } 

    def getJDBCServerUsername(): String = {
    // Retrive the distributions folder path as a string
    try {
      val distributionsFolder = props.getProperty("server_username")
      return distributionsFolder
    } catch {
        case e: Exception =>
          throw new Exception("Exception thrown when getting JDBC Server Username: " + e.getMessage())
    }
  } 

    def getJDBCServerPassword(): String = {
    // Retrive the distributions folder path as a string
    try {
      val distributionsFolder = props.getProperty("server_password")
      return distributionsFolder
    } catch {
        case e: Exception =>
          throw new Exception("Exception thrown when getting JDBC Server Password: " + e.getMessage())
    }
  } 

  def getDistributionsFolderPath(): String = {
    // Retrive the distributions folder path as a string
    try {
      val distributionsFolder = props.getProperty("distributions_folder")
      return distributionsFolder
    } catch {
        case e: Exception =>
          throw new Exception("Exception thrown when getting distriubtions folder: " + e.getMessage())
    }
  }

}