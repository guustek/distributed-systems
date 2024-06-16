plugins {
    id("java")
}

group = "rsi.ps13-14"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.rabbitmq:amqp-client:5.21.0")
}