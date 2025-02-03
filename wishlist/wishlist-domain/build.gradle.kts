tasks.bootJar {
	enabled = false
}

dependencies {
	implementation(project(":common"))
}

tasks.register("prepareKotlinBuildScriptModel"){}