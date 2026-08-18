# Release Process

## WaySense — Release and CI/CD Documentation

---

## Overview

WaySense uses GitHub Actions for all builds. No local Gradle builds are executed.

---

## Workflow Strategy

### Push to `main`

Triggers: validation build + artifact upload

```
Push to main
  → Checkout
  → Setup JDK 17
  → Gradle cache
  → Lint check
  → Build debug APK
  → Upload artifact
```

### Tag `v*`

Triggers: signed release build + GitHub Release

```
Push tag v*
  → Checkout
  → Setup JDK 17
  → Gradle cache
  → Reconstruct keystore from secrets
  → Build signed release APKs
  → Split by ABI
  → Upload artifacts
  → Create GitHub Release
  → Attach APKs
```

---

## Signing

### Keystore Generation

Generate a release keystore once:

```bash
keytool -genkeypair \
  -alias waysense \
  -keyalg RSA \
  -keysize 2048 \
  -validity 10000 \
  -keystore waysense-release.jks \
  -storepass YOUR_PASSWORD \
  -keypass YOUR_KEY_PASSWORD \
  -dname "CN=WaySense, OU=Demo, O=Academic, L=Kolkata, ST=WB, C=IN"
```

### Store as GitHub Secrets

```bash
# Base64 encode the keystore
base64 -i waysense-release.jks > keystore.base64

# Store secrets
gh secret set KEYSTORE_BASE64 < keystore.base64
gh secret set KEYSTORE_PASSWORD --body "YOUR_PASSWORD"
gh secret set KEY_ALIAS --body "waysense"
gh secret set KEY_PASSWORD --body "YOUR_KEY_PASSWORD"
```

### Workflow Reconstruction

The GitHub Actions workflow reconstructs the keystore temporarily:

```yaml
- name: Reconstruct keystore
  run: |
    echo "${{ secrets.KEYSTORE_BASE64 }}" | base64 -d > waysense-release.jks
```

After the build, the keystore is discarded with the CI environment.

---

## ABI Splits

APKs are split by ABI for smaller download sizes:

| ABI | Target Devices |
|-----|---------------|
| arm64-v8a | Most modern Android phones (64-bit ARM) |
| armeabi-v7a | Older 32-bit ARM devices |
| x86_64 | Emulators, Chromebooks |

### Artifact Names

```
WaySense-arm64-v8a.apk
WaySense-armeabi-v7a.apk
WaySense-x86_64.apk
```

---

## GitHub Releases

### Versioning

Semantic versioning: `v0.1.0`, `v0.1.1`, `v0.2.0`, `v1.0.0`

### Release Content

Each release includes:

- Version number
- Changelog / changes
- Known limitations
- Installation instructions
- Demo disclaimer
- Signed APK artifacts

### Creating a Release

```bash
git tag v0.1.0
git push origin v0.1.0
```

The workflow automatically creates the GitHub Release and attaches APKs.

---

## CI Quality

The workflow fails clearly on:

- Gradle build failures
- Kotlin compilation errors
- Lint violations
- Missing signing configuration
- Keystore reconstruction failures
- APK generation failures

Error handling uses `set -e` in shell scripts. No `|| true` to hide failures.

---

## Security

- Keystore is NEVER committed to Git
- Passwords are NEVER in source code
- GitHub Secrets are masked in workflow logs
- `.gitignore` excludes `*.jks`, `*.keystore`, `local.properties`
