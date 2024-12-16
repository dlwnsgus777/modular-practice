dependencies {
    implementation(project(":common"))
    implementation(project(":auth"))
    implementation(project(":member:member-api"))
    implementation(project(":member:member-domain"))
    implementation(project(":member:member-infra"))

    testImplementation(project(":member:member-api"))
}

tasks.register("prepareKotlinBuildScriptModel"){}