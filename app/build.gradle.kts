dependencies {
    implementation(project(":common"))
    implementation(project(":auth"))

    implementation(project(":member:member-api"))
    implementation(project(":member:member-domain"))
    implementation(project(":member:member-infra"))

    implementation(project(":product:product-api"))
    implementation(project(":product:product-domain"))
    implementation(project(":product:product-infra"))

    implementation(project(":wishlist:wishlist-api"))
    implementation(project(":wishlist:wishlist-domain"))
    implementation(project(":wishlist:wishlist-infra"))


    testImplementation(project(":member:member-api"))
    
    testImplementation(project(":product:product-domain"))
    testImplementation(project(":product:product-infra"))

    testImplementation(project(":wishlist:wishlist-domain"))
    testImplementation(project(":wishlist:wishlist-infra"))

}

tasks.register("prepareKotlinBuildScriptModel"){}