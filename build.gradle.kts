plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

allprojects {
    apply {
        plugin("java")
        plugin("com.github.johnrengelman.shadow")
    }

    group = "net.exotia.plugins"
    version = "1.2"

    tasks {
        compileJava {
            options.encoding = "UTF-8"
        }
    }

    repositories {
        mavenCentral()
        maven {
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
        maven {
            url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
        }
        maven {
            url = uri("https://repo.oraxen.com/releases")
        }

        maven {
            url = uri("https://storehouse.okaeri.eu/repository/maven-public/")
        }

        maven {
            url = uri("https://repo.panda-lang.org/releases/")
        }
    }

    dependencies {
        implementation("org.projectlombok:lombok:1.18.24")
        annotationProcessor("org.projectlombok:lombok:1.18.24")

        compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
        compileOnly("me.clip:placeholderapi:2.11.5")
        compileOnly("io.th0rgal:oraxen:1.163.0")

        implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.0-beta.5")
        implementation("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.0-beta.5")
        implementation("eu.okaeri:okaeri-injector:2.1.0")

        implementation("dev.rollczi:litecommands-bukkit:3.0.2")
        implementation("dev.rollczi:litecommands-adventure:3.0.2")

        implementation("dev.triumphteam:triumph-gui:3.1.7")
    }

    tasks {
        shadowJar {
            relocate("dev.rollczi:litecommands-bukkit:3.0.2", "net.exotia.plugins.litecommands-bukkit")
            relocate("dev.rollczi:litecommands-adventure:3.0.2", "net.exotia.plugins.litecommands-adventure")

            relocate("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.0-beta.5", "net.exotia.plugins.okaeri-configs-yaml-bukkit")
            relocate("eu.okaeri:okaeri-injector:2.1.0", "net.exotia.plugins.okaeri-injector")
            relocate("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.0-beta.5", "net.exotia.plugins.okaeri-serdes-bukkit")

            relocate("dev.triumphteam:triumph-gui:3.1.7", "net.exotia.plugins.triumph-gui")

            minimize()

            archiveVersion.set("")
            archiveFileName.set(project.name + "-" + project.version + ".jar")
            archiveClassifier.set("")
            archiveBaseName.set("shadow")

            mergeServiceFiles()

            exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
        }
    }
}

tasks.register<GradleBuild>("plugin") {
    tasks = listOf("build", "shadowJar")
}