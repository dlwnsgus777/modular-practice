tasks.bootJar {
    enabled = false
}

dependencies {
    implementation(project(":common"))
    implementation(project(":product:product-domain"))
    implementation("org.apache.httpcomponents.client5:httpclient5:5.2")
}

tasks.register("prepareKotlinBuildScriptModel"){}