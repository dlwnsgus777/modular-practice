tasks.bootJar {
    enabled = false
}

dependencies {
    implementation(project(":common"))
    implementation(project(":member:member-domain"))

}

tasks.register("prepareKotlinBuildScriptModel"){}
