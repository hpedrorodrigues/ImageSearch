# Proguard Rules

# Retrolambda
-dontwarn java.lang.invoke.*

# General
-keepattributes Exceptions   # Retrofit
-keepattributes Signature    # Retrofit
-keepattributes InnerClasses # Crashlytics

# Crashlytics
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception
-printmapping mapping.txt

# Fabric SDK
-keep class io.fabric.sdk.android.** { *; }
-keep interface io.fabric.sdk.android.** { *; }
-keep class io.fabric.** { *; }
-keep interface io.fabric.** { *; }

# Rx
-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Okio
-dontwarn okio.**

# Licenses Dialog
-dontwarn de.psdev.licensesdialog.**

# Retrofit 2
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8

# Gson
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.examples.android.model.** { *; }

# Support library v4
-dontwarn android.support.v4.app.**
-dontwarn android.support.v4.view.**
-dontwarn android.support.v4.widget.**

# Support library v7
-keep public class android.support.v7.widget.** { *; }
-keep public class android.support.v7.internal.widget.** { *; }
-keep public class android.support.v7.internal.view.menu.** { *; }

# Support Design
-keep class android.support.design.widget.** { *; }
-keep class android.support.design.internal.** { *; }
-keep interface android.support.design.widget.** { *; }
-dontwarn android.support.design.**

# R class
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Serializable classes
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Firebase
-keep class com.google.firebase. { *; }
-dontwarn com.google.firebase.

# Local code
-keep class com.hpedrorodrigues.imagesearch.** { *; }