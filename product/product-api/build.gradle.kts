tasks.bootJar {
    enabled = false
}

dependencies {
    implementation(project(":common"))
    implementation(project(":auth"))
    implementation(project(":product:product-domain"))

}

tasks.register("prepareKotlinBuildScriptModel"){}
