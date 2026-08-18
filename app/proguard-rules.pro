# ProGuard rules for WaySense
-keepattributes Signature
-keepattributes *Annotation*

# Keep data classes
-keep class com.waysense.app.data.model.** { *; }

# Keep Compose
-dontwarn androidx.compose.**
