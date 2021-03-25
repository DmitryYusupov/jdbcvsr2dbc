plugins {

}

dependencies {
    // https://mvnrepository.com/artifact/org.apache.jmeter/ApacheJMeter_core
    implementation("org.apache.jmeter:ApacheJMeter_core:5.4.1")
    implementation("org.apache.jmeter:ApacheJMeter_http:5.4.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.apache.logging.log4j:log4j-api:2.13.3")
    implementation("org.apache.logging.log4j:log4j-core:2.13.3")
}
