plugins {
    id("feature-convention-plugin")
}

group = "org.alexcawl.bookstore"
version = "1.0"

dependencies {
    implementation(project(":feature"))
//    implementation(project(":core:api"))
//    implementation(project(":core:configuration"))
    implementation(project(":core:db"))
}