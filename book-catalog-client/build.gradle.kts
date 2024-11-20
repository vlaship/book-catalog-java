plugins {
    id("java")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("dev.vlaship.lombok")
    id("dev.vlaship.spotless")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.3")
//        mavenBom(libs.spring.cloud.bom)
    }
}

dependencies {
    implementation(project(":book-catalog-api"))
    implementation(libs.jspecify)
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.retry:spring-retry")
    implementation("org.apache.httpcomponents.client5:httpclient5")
    implementation("org.springframework.cloud:spring-cloud-openfeign-core")
    implementation("io.github.openfeign:feign-core")
    implementation("io.github.openfeign:feign-hc5")
    implementation("io.github.openfeign:feign-jackson")
    implementation("io.github.openfeign:feign-okhttp")
    implementation("com.squareup.okhttp3:okhttp")
}
