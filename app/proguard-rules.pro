# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.ac
-renamesourcefileattribute SourceFile

-keepattributes *Annotation*, Signature, Exception


# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# models
-keep class com.am.themovieapp.data.models.** { *; }
-keep class com.am.themovieapp.data.vos.** { *; }
-keep class com.am.themovieapp.network.** { *; }
-keep class com.am.themovieapp.network.responses.** { *; }

# Retofit
-keep class com.squareup.** { *; }
-keep interface com.squareup.** { *; }
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *;}
-keep interface com.squareup.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.** { *; }
