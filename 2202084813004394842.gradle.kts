plugins {
    java
    id("io.quarkus")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-micrometer-registry-prometheus")
    implementation("io.quarkus:quarkus-hibernate-validator")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-hibernate-reactive-panache-kotlin")
    implementation("io.quarkus:quarkus-reactive-pg-client")
    implementation("io.quarkus:quarkus-smallrye-health")
    implementation("io.quarkus:quarkus-resteasy-reactive-kotlin-serialization")
    implementation("io.quarkus:quarkus-micrometer")
    implementation("io.quarkus:quarkus-arc")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

group = "io.blends"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

val quarkusDeployment by configurations.creating
dependencies {
quarkusDeployment("io.quarkus:quarkus-smallrye-context-propagation-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-kotlin-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-datasource-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-reactive-datasource-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-arc-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-caffeine-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-jsonp-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-hibernate-reactive-panache-common-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-mutiny-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-reactive-pg-client-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-micrometer-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-smallrye-health-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-hibernate-reactive-panache-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-hibernate-reactive-panache-kotlin-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-hibernate-validator-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-resteasy-reactive-kotlin-serialization-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-resteasy-reactive-common-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-resteasy-reactive-kotlin-serialization-common-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-config-yaml-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-hibernate-orm-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-netty-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-resteasy-reactive-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-vertx-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-micrometer-registry-prometheus-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-hibernate-reactive-deployment:3.2.1.Final")
quarkusDeployment("io.quarkus:quarkus-vertx-http-deployment:3.2.1.Final")
}
typealias PrintWriter = java.io.PrintWriter
typealias FileWriter = java.io.FileWriter
tasks.register("listQuarkusDependencies") {
    val writer = PrintWriter(FileWriter("C:\\Users\\alexa\\AppData\\Local\\Temp\\18225958878902542772.txt"))
    quarkusDeployment.incoming.artifacts.forEach {
        writer.println(it.id.componentIdentifier)
        writer.println(it.file)
    }
    val componentIds = quarkusDeployment.incoming.resolutionResult.allDependencies.map { (it as ResolvedDependencyResult).selected.id }
    val result = dependencies.createArtifactResolutionQuery()
        .forComponents(componentIds)
        .withArtifacts(JvmLibrary::class, SourcesArtifact::class)
        .execute()
    result.resolvedComponents.forEach { component ->
        val sources = component.getArtifacts(SourcesArtifact::class)
        sources.forEach { ar ->
            if (ar is ResolvedArtifactResult) {
                writer.println(ar.id.componentIdentifier)
                writer.println(ar.file)
            }
        }
    }
    writer.close()
}