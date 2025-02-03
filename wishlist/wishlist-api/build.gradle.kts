tasks.bootJar {
    enabled = false
}

dependencies {
    implementation(project(":common"))
    implementation(project(":auth"))
    implementation(project(":wishlist:wishlist-domain"))

}

tasks.register("prepareKotlinBuildScriptModel"){}
