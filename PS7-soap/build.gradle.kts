plugins {
    id("java")
    id("war")
}

group = "rsi.ps7"

repositories {
    mavenCentral()
}

java {
    targetCompatibility = JavaVersion.VERSION_1_8
    sourceCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation("javax.xml.ws:jaxws-api:2.3.1")
    implementation("javax.jws:javax.jws-api:1.1")
}