plugins {
    id("spring-convention-plugin")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework:spring-jdbc")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("io.r2dbc:r2dbc-h2")
}