# NovaMC Client — GitHub Build Starter

This archive turns the supplied NovaMC skeleton into a **buildable Phase 1 launcher project**.

## Current scope

- Original NovaMC launcher UI
- Local/offline development profile label
- Version selector UI
- RAM selector UI
- Common module
- Updater service stub
- Client-core JAR
- Gradle multi-module build
- GitHub Actions Windows build
- Windows `.exe` packaging with `jpackage`

This is **not yet a complete Minecraft client**. Minecraft/Fabric integration, legitimate Microsoft authentication, installation/version management, HUD modules, performance modules, cosmetics, updater networking, and installer polish are later phases from the supplied specification.

## Build locally

Requirements:
- JDK 17
- Gradle 8.10+

```bat
gradle clean build
gradle :launcher:run
```

## Build on GitHub

1. Create a new GitHub repository.
2. Upload the contents of this folder (the folder containing `settings.gradle.kts`).
3. Push to `main`.
4. Open **Actions → Build NovaMC for Windows**.
5. Download the `NovaMC-Windows` artifact.

The workflow builds the JARs and packages the launcher as `NovaMC-Launcher.exe`.

## Important

Do not distribute Minecraft/Microsoft proprietary assets. Add legitimate authentication and Minecraft runtime integration only in later implementation phases.
