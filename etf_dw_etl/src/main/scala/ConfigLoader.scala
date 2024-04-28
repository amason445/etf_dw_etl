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