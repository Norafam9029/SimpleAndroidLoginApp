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
# Keep class members names and their accessors
-keepclassmembers class * {
    *** get*();
    *** set*(***);
}

# Keep custom models, such as User class
-keep class com.example.loginregistersqlitedatabase.model.** { *; }

# Keep database helper class
-keep class com.example.loginregistersqlitedatabase.DatabaseHelper { *; }

# Keep enum types along with their field values
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Preserve the special methods used for serialization and deserialization
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Preserve all native method names and the names of their classes
-keepclasseswithmembernames class * {
    native <methods>;
}

# Preserve the special static methods that are required in all enumeration classes
-keepclassmembers class * extends java.lang.Enum {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Preserve the annotations
-keepattributes *Annotation*

# Preserve the interfaces, their methods and fields
-keep interface * {
    *;
}

# Preserve all classes and methods from the Android package
-dontwarn android.**

# Preserve all classes in the Android support packages
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }

# Preserve the Android Entry Points
-keep class * extends android.app.Application
-keep class * extends android.content.BroadcastReceiver
-keep class * extends android.content.ContentProvider
-keep class * extends android.app.Service
-keep class * extends android.app.Fragment

# Preserve classes with the Serializable interface
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Preserve the Parcelable implementation
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
