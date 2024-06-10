plugins {
  id("java")
  id ("war")
}

java {
  targetCompatibility = JavaVersion.VERSION_1_8
  sourceCompatibility = JavaVersion.VERSION_1_8
}

group = "rsi.ps7"

repositories {
  mavenCentral()
}

dependencies {
  implementation("javax.xml.ws:jaxws-api:2.3.1")
  implementation("javax.jws:javax.jws-api:1.1")

}