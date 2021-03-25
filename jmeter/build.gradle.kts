plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.apache.jmeter:ApacheJMeter_core:5.4.1")
    implementation("org.apache.jmeter:ApacheJMeter_http:5.4.1")
//    implementation("org.apache.logging.log4j:log4j-api:2.13.3")
//    implementation("org.apache.logging.log4j:log4j-core:2.13.3")
}