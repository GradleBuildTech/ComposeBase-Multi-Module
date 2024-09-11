val currentFlavor: String by extra {
    getCurrentFlavor()
}

val projectConfigurations: Properties by extra {
    loadPropertiesFromFile("$currentFlavor.properties")
}
