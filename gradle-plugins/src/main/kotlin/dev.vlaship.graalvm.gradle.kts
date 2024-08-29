plugins {
    id("org.graalvm.buildtools.native")
}

graalvmNative {
    binaries {
        named("main") {
            sharedLibrary.set(false)
            mainClass.set("dev.vlaship.book.catalog.App")
            imageName.set("backoffice-app")
        }
    }
    binaries.all {
        resources.autodetect()
    }
    toolchainDetection.set(true)
}
