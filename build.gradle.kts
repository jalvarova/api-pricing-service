plugins {
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("java")
    id("org.openapi.generator") version "7.4.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    // WebFlux y validación
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("jakarta.validation:jakarta.validation-api")

    // JPA + H2
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.h2database:h2")

    // Redis Reactivo
    implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")

    // Vault
    implementation("org.springframework.vault:spring-vault-core")

    // Swagger/OpenAPI UI
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.5.0")

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// OpenAPI Generator
openApiGenerate {
    generatorName.set("spring")
    inputSpec.set("$rootDir/openapi.yaml")
    outputDir.set("$buildDir/generated")
    apiPackage.set("com.ecommerce.pricing.infrastructure.controller.api")
    modelPackage.set("com.ecommerce.pricing.domain.model")
    configOptions.set(
        mapOf(
            "interfaceOnly" to "true",
            "useReactive" to "true",
            "useSpringBoot3" to "true",
            "dateLibrary" to "java8"
        )
    )
}

sourceSets["main"].java {
    srcDir("$buildDir/generated/src/main/java")
}
