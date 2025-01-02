dependencies {
    implementation(project(":common"))
    implementation(project(":auth"))

    implementation(project(":member:member-api"))
    implementation(project(":member:member-domain"))
    implementation(project(":member:member-infra"))

    implementation(project(":product:product-api"))
    implementation(project(":product:product-domain"))
    implementation(project(":product:product-infra"))

    testImplementation(project(":member:member-api"))
}

tasks.register("prepareKotlinBuildScriptModel"){}