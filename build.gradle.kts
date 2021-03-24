import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.4" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply false
    kotlin("jvm") version "1.4.31" apply false
    kotlin("plugin.spring") version "1.4.31" apply false

    kotlin("plugin.allopen") version "1.3.61" apply false
    kotlin("plugin.jpa") version "1.4.10" apply false
}

//java.sourceCompatibility = JavaVersion.VERSION_14

repositories {
    mavenCentral()
}

allprojects {
    group = "ru.yusdm.jdbcvsr2dbc"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
        jcenter()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "14"
        targetCompatibility = "14"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "14"
        }
    }

}

subprojects {

   /* apply {
        plugin("io.spring.dependency-management")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
    }*/

    repositories {
        mavenCentral()
    }

    apply {
        plugin("io.spring.dependency-management")
    }

   /* dependencyManagement {
        val bootVersion: String by project
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$bootVersion")
        }
    }*/

//    apply(plugin = "org.jetbrains.kotlin.jvm")
//    apply(plugin = "org.springframework.boot")
//    apply(plugin = "plugin.spring")

//    dependencies {
//        implementation("org.springframework.boot", "spring-boot-starter")
//        implementation("org.jetbrains.kotlin:kotlin-reflect")
//        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//
//        testImplementation("org.springframework.boot", "spring-boot-starter-test") {
//            exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
//        }
//    }
}