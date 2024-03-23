plugins {
  id("java")
  id ("war")
}

group = "rsi.ps4"

repositories {
  mavenCentral()
}

dependencies {
  implementation("javax.xml.ws:jaxws-api:2.3.1")
  implementation("javax.jws:javax.jws-api:1.1")

}