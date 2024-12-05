dependencyResolutionManagement {
    // Reuse version catalog from the main build to resolve plugin dependencies
    versionCatalogs {
        register("libs", { from(files("../libs.versions.toml")) })
    }
}

rootProject.name = "plugins"
