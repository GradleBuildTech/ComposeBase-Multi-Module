import org.gradle.api.Project
import java.util.Locale
import java.util.Properties
import java.util.regex.Pattern

object BuildConfig {
    val loadPropertiesFromFile: (Project, String) -> Properties = { project, fileName ->
        val propertiesFile = project.rootProject.file(fileName)
        val properties = Properties()
        propertiesFile.inputStream().use { inputStream ->
            properties.load(inputStream)
        }
        properties
    }

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

    val projectConfigurations: (Project) -> Properties = { project ->
        val currentFlavor = getCurrentFlavor(project)
        loadPropertiesFromFile(project, "$currentFlavor.properties")
    }

//    fun createProductFlavors(project: Project, android: AppExtension, flavorNames: List<String>) {
//        flavorNames.forEach { flavorName ->
//            android.productFlavors.create(flavorName) {
//                dimensi♦on = "env"
//                applicationIdSuffix = ".$flavorName"
//                versionNameSuffix = "-$flavorName"
//
//                val properties = loadPropertiesFromFile(project, "$flavorName.properties")
//                buildConfigField("String", "BASE_URL", "\"${properties["BASE_URL"]}\"")
//            }
//        }
//    }

}
