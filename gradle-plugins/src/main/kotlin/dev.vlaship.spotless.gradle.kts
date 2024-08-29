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
        toggleOffOn()
        //googleJavaFormat()
//        cleanthat()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        removeUnusedImports()
    }
}
