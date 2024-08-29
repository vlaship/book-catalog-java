plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("dev.vlaship.lombok")
    id("dev.vlaship.spotless")
}

dependencies {
    implementation(project(":book-catalog-api"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.apache.httpcomponents.client5:httpclient5")
}
