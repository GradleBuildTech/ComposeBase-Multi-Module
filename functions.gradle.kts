import java.io.FileInputStream
import java.util.Properties
import java.util.regex.Pattern

val loadPropertiesFromFile: (String) -> Properties = { fileName ->
    val propertiesFile = rootProject.file(fileName)
    val properties = Properties()
    properties.load(FileInputStream(propertiesFile))
    properties
}

val getCurrentFlavor: () -> String = {
    val gradle = gradle
    val pattern = Pattern.compile("([A-Z][A-Za-z]+)(Release|Debug)")
    var flavor = "prod"

    gradle.startParameter.taskNames.any { name ->
        val matcher = pattern.matcher(name)
        if (matcher.find()) {
            flavor = matcher.group(1).toLowerCase()
            return@any true
        }
        false
    }
    flavor
}


val projectConfigurations: () -> Properties = {
    val getCurrentFlavor = getCurrentFlavor()
    loadPropertiesFromFile("$getCurrentFlavor.properties")
}

