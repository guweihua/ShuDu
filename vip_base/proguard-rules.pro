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

# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html
#
# Starting with version 2.2 of the Android plugin for Gradle, this file is distributed together with
# the plugin and unpacked at build-time. The files in $ANDROID_HOME are no longer maintained and
# will be ignored by new version of the Android plugin for Gradle.

# Optimizations can be turned on and off in the 'postProcessing' DSL block.
# The configuration below is applied if optimizations are enabled.
# Adding optimization introduces certain risks, since for example not all optimizations performed by
# ProGuard works on all versions of Dalvik.  The following flags turn off various optimizations
# known to have issues, but the list may not be complete or up to date. (The "arithmetic"
# optimization can be used if you are only targeting Android 2.0 or later.)  Make sure you test
# thoroughly if you go this route.
# --------------------------------------------基本指令区-------------------------------------------# 指定代码的压缩级别(在0~7之间，默认为5)
-optimizationpasses 5
# 是否使用大小写混合(windows大小写不敏感，建议加入)
-dontusemixedcaseclassnames
 # 是否混淆非公共的库的类
-dontskipnonpubliclibraryclasses
# 是否混淆非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers
# 混淆时是否做预校验(Android不需要预校验，去掉可以加快混淆速度)
# 混淆时是否记录日志(混淆后会生成映射文件)
-verbose


# 混淆时所采用的算法(谷歌推荐算法)
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!code/allocation/variable
-useuniqueclassmembernames
-allowaccessmodification
# 将文件来源重命名为“SourceFile”字符串
-renamesourcefileattribute SBFile

# 保持注解不被混淆
-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation {*;}

# 保持泛型不被混淆
-keepattributes Signature
# 保持反射不被混淆
-keepattributes EnclosingMethod
# 保持异常不被混淆
-keepattributes Exceptions
# 保持内部类不被混淆
-keepattributes Exceptions,InnerClasses
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

# --------------------------------------------默认保留区--------------------------------------------#
# 保持基本组件不被混淆
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference


# Support包规则
-dontwarn android.support.**
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**

# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留自定义控件(继承自View)不被混淆
-keep public class * extends android.view.View {
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保留指定格式的构造方法不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保留在Activity中的方法参数是view的方法(避免布局文件里面onClick被影响)
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# 保持枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保持R(资源)下的所有类及其方法不能被混淆
-keep class **.R$* { *; }

# 保持 Parcelable 序列化的类不被混淆(注：aidl文件不能去混淆)
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# 需要序列化和反序列化的类不能被混淆(注：Java反射用到的类也不能被混淆)
-keepnames class * implements java.io.Serializable

# 保持 Serializable 序列化的类成员不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 保持 BaseAdapter 类不被混淆
-keep public class * extends android.widget.BaseAdapter { *; }

# --------------------------------------------webView区--------------------------------------------#
# WebView处理，项目中没有使用到webView忽略即可
# 保持Android与JavaScript进行交互的类不被混淆
-keep class **.AndroidJavaScript { *; }
-keepclassmembers class * extends android.webkit.WebViewClient {
     public void *(android.webkit.WebView,java.lang.String,android.graphics.Bitmap);
     public boolean *(android.webkit.WebView,java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebChromeClient {
     public void *(android.webkit.WebView,java.lang.String);
}

# 网络请求相关
-keep public class android.net.http.SslError



################retrofit###############
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

################butterknife###############
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
   @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
 @butterknife.* <methods>;
}

################gson###############
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.sunloto.shandong.bean.** { *; }


################okhttp###############
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn com.squareup.okhttp.**

-keep class com.facebook.** { *; }

################autolayout###############
-keep class com.zhy.autolayout.** { *; }
-keep interface com.zhy.autolayout.** { *; }

################RxJava and RxAndroid###############
-dontwarn org.mockito.**
-dontwarn org.junit.**
-dontwarn org.robolectric.**

-keep class io.reactivex.** { *; }
-keep interface io.reactivex.** { *; }

-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-dontwarn okio.**
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn io.reactivex.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}


-dontwarn java.lang.invoke.*


-keepclassmembers class io.reactivex.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}