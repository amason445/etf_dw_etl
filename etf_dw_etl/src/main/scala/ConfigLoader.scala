// import java.io.InputStream
// import org.tomlj.Toml
// import org.tomlj.TomlParseResult

// object ConfigLoader {
//   def loadConfig(resourceName: String): TomlParseResult = {
//     // Use the class loader to get the resource as a stream
//     val stream: InputStream = getClass.getClassLoader.getResourceAsStream(resourceName)
//     if (stream == null) {
//       throw new IllegalArgumentException(s"Resource not found: $resourceName")
//     }
//     Toml.parse(stream)
//   }
// }


// val config: TomlParseResult = ConfigLoader.loadConfig("config.toml")

// if (config.hasErrors()) {
//   config.errors().forEach(error => println(s"Config error: $error"))
// }

// val distributionsFolder = config.getString("folder_paths.distributions_folder")

// println(s"The folder parse was successful: $distributionsFolder")