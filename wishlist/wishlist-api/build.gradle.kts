tasks.bootJar {
    enabled = false
}

dependencies {
    implementation(project(":common"))
    implementation(project(":wishlist:wishlist-domain"))

}

tasks.register("prepareKotlinBuildScriptModel"){}
