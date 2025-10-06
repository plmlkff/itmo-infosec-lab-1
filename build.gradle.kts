import com.github.spotbugs.snom.Confidence

plugins {
    id("java")
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.github.spotbugs") version "6.0.7"
    id("org.owasp.dependencycheck") version "12.1.6"
}

group = "ru.itmo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.4.3")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("io.vavr:vavr:0.10.5")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    spotbugs("com.github.spotbugs:spotbugs:4.8.3")
    spotbugsPlugins("com.h3xstream.findsecbugs:findsecbugs-plugin:1.12.0")

    // Test dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

spotbugs {
    toolVersion.set("4.8.3")
    ignoreFailures.set(false)
    showStackTraces.set(false)
    reportLevel.set(Confidence.HIGH)
}

dependencyCheck {
    format = "HTML"

    // Только критические уязвимости
    failBuildOnCVSS = 9.0f
    nvd{
        apiKey=System.getenv("NVD_API_KEY")
    }

    analyzers {
        assemblyEnabled = false
        nugetconfEnabled = false
        nuspecEnabled = false
        centralEnabled = false
        nexusEnabled = false
        pyDistributionEnabled = false
        pyPackageEnabled = false
        rubygemsEnabled = false
        cocoapodsEnabled = false
        swiftEnabled = false
        archiveEnabled = true
    }
}

tasks.test {
    useJUnitPlatform()
}