plugins {
    java
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
}

val axonVersion = "4.9.3"
val axonExtensionKafkaVersion = "4.9.0"
val axonExtensionReactor = "4.9.0"
val axonExtensionSpringCloudVersion = "4.9.0"

allprojects {
    group = "com.example"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "java")

    java {
        sourceCompatibility = JavaVersion.VERSION_21
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.functionaljava:functionaljava:5.0")
        implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.5")
        implementation("com.fasterxml.jackson.core:jackson-core:2.13.5")
        implementation("com.fasterxml.jackson.core:jackson-databind:2.13.5")
        compileOnly("org.projectlombok:lombok:1.18.30")
        annotationProcessor("org.projectlombok:lombok:1.18.30")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project (":synchronous-communication") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation("org.axonframework:axon-spring-boot-starter:$axonVersion")
        implementation("org.axonframework.extensions.kafka:axon-kafka-spring-boot-starter:$axonExtensionKafkaVersion")
        implementation("org.axonframework.extensions.reactor:axon-reactor:$axonExtensionReactor")
        implementation("org.axonframework.extensions.springcloud:axon-springcloud-spring-boot-starter:$axonExtensionSpringCloudVersion")
        implementation("org.apache.kafka:kafka-clients:3.2.2")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

        runtimeOnly("com.h2database:h2")
        runtimeOnly("com.mysql:mysql-connector-j")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}
