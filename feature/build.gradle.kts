plugins {
    id("feature-convention-plugin")
}

group = "org.alexcawl.bookstore.feature"
version = "1.0"

dependencies {
    implementation(project(":core:db"))
//    implementation(project(":core:api"))
//    implementation(project(":core:configuration"))
}