import org.gradle.api.Project
import java.util.Locale
import java.util.Properties
import java.util.regex.Pattern

///[BuildConfig] is a singleton object that contains utility functions to load properties from a file and get the current flavor of the project.
///This is used to load properties from a file and get the current flavor of the project.
/// Example: dev.properties, prod.properties

object BuildConfig {
    ///[loadPropertiesFromFile] is a function that loads properties from a file.
    val loadPropertiesFromFile: (Project, String) -> Properties = { project, fileName ->
        val propertiesFile = project.rootProject.file(fileName)
        val properties = Properties()
        propertiesFile.inputStream().use { inputStream ->
            properties.load(inputStream)
        }
        properties
    }

    ///[getCurrentFlavor] is a function that gets the current flavor of the project.
    val getCurrentFlavor: (Project) -> String = { project ->
        val gradle = project.gradle
        val pattern = Pattern.compile("([A-Z][A-Za-z]+)(Release|Debug)")
        var flavor = "prod"

        gradle.startParameter.taskNames.any { name ->
            val matcher = pattern.matcher(name)
            if (matcher.find()) {
                flavor = matcher.group(1).lowercase(Locale.getDefault())
                return@any true
            }
            false
        }
        flavor
    }

    ///[projectConfigurations] is a function that gets the project configurations.
    val projectConfigurations: (Project) -> Properties = { project ->
        val currentFlavor = getCurrentFlavor(project)
        loadPropertiesFromFile(project, "$currentFlavor.properties")
    }
}
