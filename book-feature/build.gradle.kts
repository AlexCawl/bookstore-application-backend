plugins {
    id("feature-convention-plugin")
}

group = "org.alexcawl.bookstore.book-feature"
version = "1.0"

dependencies {
    implementation(project(":core:api"))
    implementation(project(":core:configuration"))
    implementation(project(":core:db"))
}