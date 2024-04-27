import java.io.InputStream
import org.tomlj.Toml
import org.tomlj.TomlParseResult

object ConfigLoader {
  def loadConfig(resourceName: String): TomlParseResult = {
    // Use the class loader to get the resource as a stream
    val stream: InputStream = getClass.getClassLoader.getResourceAsStream(resourceName)
    if (stream == null) {
      throw new IllegalArgumentException(s"Resource not found: $resourceName")
    }
    Toml.parse(stream)
  }
}