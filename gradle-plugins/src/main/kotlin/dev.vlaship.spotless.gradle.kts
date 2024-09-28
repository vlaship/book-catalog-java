plugins {
    id("com.diffplug.spotless")
}

spotless {
//    ratchetFrom("origin/main")

    format("misc") {
        // define the files to apply `misc` to
        target("*.kts", ".gitignore")

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }

    java {
        target("src/*/java/**/*.java", "src/*/test/**/*.java")
        eclipse("4.33").configFile("../spotless.xml")
        indentWithTabs(2)
        indentWithSpaces(4)
        toggleOffOn()
        trimTrailingWhitespace()
        endWithNewline()
        removeUnusedImports()
//        cleanthat()
    }
}
