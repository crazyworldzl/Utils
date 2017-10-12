# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\Android\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

## 位于module下的proguard-rules.pro
#####################################
######### 主程序不能混淆的代码 #########
#####################################

-dontwarn com.al.**
-keep class com.al.** { *; }

## 等等，自己的代码自己清楚

#####################################
########### 不优化泛型和反射 ##########
#####################################

-keepattributes Signature



-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }

-dontwarn com.squareup.**
-keep class com.squareup.** { *; }

-dontwarn com.octo.**
-keep class com.octo.** { *; }

-dontwarn de.**
-keep class de.** { *; }

-dontwarn javax.**
-keep class javax.** { *; }

-dontwarn org.**
-keep class org.** { *; }

-dontwarn u.aly.**
-keep class u.aly.** { *; }

-dontwarn uk.**
-keep class uk.** { *; }

-dontwarn com.baidu.**
-keep class com.baidu.** { *; }

-dontwarn com.facebook.**
-keep class com.facebook.** { *; }

-dontwarn com.google.**
-keep class com.google.** { *; }