plugins {
    id("feature-convention-plugin")
}

group = "org.alexcawl.bookstore.core"
version = "1.0"

dependencies {
    implementation(project(":feature"))
}