package com.blankj.utilcode.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AppOpsManager;
import android.app.Application;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.Log;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class AppUtils
{
  private static final char[] HEX_DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  
  private AppUtils()
  {
    throw new UnsupportedOperationException("u can't instantiate me...");
  }
  
  private static String bytes2HexString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return "";
    }
    int k = paramArrayOfByte.length;
    if (k <= 0) {
      return "";
    }
    char[] arrayOfChar = new char[k << 1];
    int i = 0;
    int j = 0;
    while (i < k)
    {
      int m = j + 1;
      arrayOfChar[j] = HEX_DIGITS[(paramArrayOfByte[i] >> 4 & 0xF)];
      j = m + 1;
      arrayOfChar[m] = HEX_DIGITS[(paramArrayOfByte[i] & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }
  
  public static void exitApp()
  {
    LinkedList localLinkedList = Utils.getActivityList();
    int i = localLinkedList.size() - 1;
    while (i >= 0)
    {
      ((Activity)localLinkedList.get(i)).finish();
      i -= 1;
    }
    System.exit(0);
  }
  
  public static Drawable getAppIcon()
  {
    return getAppIcon(Utils.getApp().getPackageName());
  }
  
  public static Drawable getAppIcon(String paramString)
  {
    if (isSpace(paramString)) {
      return null;
    }
    try
    {
      PackageManager localPackageManager = Utils.getApp().getPackageManager();
      paramString = localPackageManager.getPackageInfo(paramString, 0);
      if (paramString == null) {
        return null;
      }
      paramString = paramString.applicationInfo.loadIcon(localPackageManager);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static AppInfo getAppInfo()
  {
    return getAppInfo(Utils.getApp().getPackageName());
  }
  
  public static AppInfo getAppInfo(String paramString)
  {
    try
    {
      PackageManager localPackageManager = Utils.getApp().getPackageManager();
      paramString = getBean(localPackageManager, localPackageManager.getPackageInfo(paramString, 0));
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String getAppName()
  {
    return getAppName(Utils.getApp().getPackageName());
  }
  
  public static String getAppName(String paramString)
  {
    if (isSpace(paramString)) {
      return "";
    }
    try
    {
      PackageManager localPackageManager = Utils.getApp().getPackageManager();
      paramString = localPackageManager.getPackageInfo(paramString, 0);
      if (paramString == null) {
        return null;
      }
      paramString = paramString.applicationInfo.loadLabel(localPackageManager).toString();
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static String getAppPackageName()
  {
    return Utils.getApp().getPackageName();
  }
  
  public static String getAppPath()
  {
    return getAppPath(Utils.getApp().getPackageName());
  }
  
  public static String getAppPath(String paramString)
  {
    if (isSpace(paramString)) {
      return "";
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getPackageInfo(paramString, 0);
      if (paramString == null) {
        return null;
      }
      paramString = paramString.applicationInfo.sourceDir;
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static Signature[] getAppSignature()
  {
    return getAppSignature(Utils.getApp().getPackageName());
  }
  
  public static Signature[] getAppSignature(String paramString)
  {
    if (isSpace(paramString)) {
      return null;
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getPackageInfo(paramString, 64);
      if (paramString == null) {
        return null;
      }
      paramString = paramString.signatures;
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  private static String getAppSignatureHash(String paramString1, String paramString2)
  {
    if (isSpace(paramString1)) {
      return "";
    }
    paramString1 = getAppSignature(paramString1);
    if ((paramString1 != null) && (paramString1.length > 0)) {
      return bytes2HexString(hashTemplate(paramString1[0].toByteArray(), paramString2)).replaceAll("(?<=[0-9A-F]{2})[0-9A-F]{2}", ":$0");
    }
    return "";
  }
  
  public static String getAppSignatureMD5()
  {
    return getAppSignatureMD5(Utils.getApp().getPackageName());
  }
  
  public static String getAppSignatureMD5(String paramString)
  {
    return getAppSignatureHash(paramString, "MD5");
  }
  
  public static String getAppSignatureSHA1()
  {
    return getAppSignatureSHA1(Utils.getApp().getPackageName());
  }
  
  public static String getAppSignatureSHA1(String paramString)
  {
    return getAppSignatureHash(paramString, "SHA1");
  }
  
  public static String getAppSignatureSHA256()
  {
    return getAppSignatureSHA256(Utils.getApp().getPackageName());
  }
  
  public static String getAppSignatureSHA256(String paramString)
  {
    return getAppSignatureHash(paramString, "SHA256");
  }
  
  public static int getAppVersionCode()
  {
    return getAppVersionCode(Utils.getApp().getPackageName());
  }
  
  public static int getAppVersionCode(String paramString)
  {
    if (isSpace(paramString)) {
      return -1;
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getPackageInfo(paramString, 0);
      if (paramString == null) {
        return -1;
      }
      int i = paramString.versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return -1;
  }
  
  public static String getAppVersionName()
  {
    return getAppVersionName(Utils.getApp().getPackageName());
  }
  
  public static String getAppVersionName(String paramString)
  {
    if (isSpace(paramString)) {
      return "";
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getPackageInfo(paramString, 0);
      if (paramString == null) {
        return null;
      }
      paramString = paramString.versionName;
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static List<AppInfo> getAppsInfo()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = Utils.getApp().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      AppInfo localAppInfo = getBean(localPackageManager, (PackageInfo)localIterator.next());
      if (localAppInfo != null) {
        localArrayList.add(localAppInfo);
      }
    }
    return localArrayList;
  }
  
  private static AppInfo getBean(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    if ((paramPackageManager != null) && (paramPackageInfo != null))
    {
      ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
      String str1 = paramPackageInfo.packageName;
      String str2 = localApplicationInfo.loadLabel(paramPackageManager).toString();
      paramPackageManager = localApplicationInfo.loadIcon(paramPackageManager);
      String str3 = localApplicationInfo.sourceDir;
      String str4 = paramPackageInfo.versionName;
      int i = paramPackageInfo.versionCode;
      boolean bool;
      if ((localApplicationInfo.flags & 0x1) != 0) {
        bool = true;
      } else {
        bool = false;
      }
      return new AppInfo(str1, str2, paramPackageManager, str3, str4, i, bool);
    }
    return null;
  }
  
  private static File getFileByPath(String paramString)
  {
    if (isSpace(paramString)) {
      return null;
    }
    return new File(paramString);
  }
  
  private static String getForegroundProcessName()
  {
    Object localObject1 = ((ActivityManager)Utils.getApp().getSystemService("activity")).getRunningAppProcesses();
    Object localObject3;
    if ((localObject1 != null) && (((List)localObject1).size() > 0))
    {
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject1).next();
        if (((ActivityManager.RunningAppProcessInfo)localObject3).importance == 100) {
          return ((ActivityManager.RunningAppProcessInfo)localObject3).processName;
        }
      }
    }
    Object localObject4;
    if (Build.VERSION.SDK_INT > 21)
    {
      localObject3 = Utils.getApp().getPackageManager();
      localObject1 = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
      localObject4 = ((PackageManager)localObject3).queryIntentActivities((Intent)localObject1, 65536);
      Log.i("ProcessUtils", localObject4.toString());
      if (((List)localObject4).size() <= 0)
      {
        Log.i("ProcessUtils", "getForegroundProcessName: noun of access to usage information.");
        return "";
      }
    }
    label350:
    Object localObject2;
    label355:
    do
    {
      for (;;)
      {
        try
        {
          localObject3 = ((PackageManager)localObject3).getApplicationInfo(Utils.getApp().getPackageName(), 0);
          localObject4 = (AppOpsManager)Utils.getApp().getSystemService("appops");
          if (((AppOpsManager)localObject4).checkOpNoThrow("android:get_usage_stats", ((ApplicationInfo)localObject3).uid, ((ApplicationInfo)localObject3).packageName) != 0)
          {
            ((Intent)localObject1).addFlags(268435456);
            Utils.getApp().startActivity((Intent)localObject1);
          }
          if (((AppOpsManager)localObject4).checkOpNoThrow("android:get_usage_stats", ((ApplicationInfo)localObject3).uid, ((ApplicationInfo)localObject3).packageName) != 0)
          {
            Log.i("ProcessUtils", "getForegroundProcessName: refuse to device usage stats.");
            return "";
          }
          localObject1 = (UsageStatsManager)Utils.getApp().getSystemService("usagestats");
          localObject3 = null;
          if (localObject1 == null) {
            break label350;
          }
          long l = System.currentTimeMillis();
          localObject1 = ((UsageStatsManager)localObject1).queryUsageStats(4, l - 604800000L, l);
          if (localObject1 != null)
          {
            if (((List)localObject1).isEmpty()) {
              return null;
            }
            localObject4 = ((List)localObject1).iterator();
            localObject1 = null;
            if (!((Iterator)localObject4).hasNext()) {
              break;
            }
            localObject3 = (UsageStats)((Iterator)localObject4).next();
            if (localObject1 == null) {
              break label355;
            }
            if (((UsageStats)localObject3).getLastTimeUsed() <= ((UsageStats)localObject1).getLastTimeUsed()) {
              continue;
            }
            break label355;
            localObject3 = ((UsageStats)localObject1).getPackageName();
          }
          return localObject3;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          localNameNotFoundException.printStackTrace();
        }
        return "";
        localObject2 = null;
        continue;
        localObject2 = localObject3;
      }
    } while (localObject2 != null);
    return null;
  }
  
  private static Intent getInstallAppIntent(File paramFile)
  {
    return getInstallAppIntent(paramFile, false);
  }
  
  private static Intent getInstallAppIntent(File paramFile, boolean paramBoolean)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    if (Build.VERSION.SDK_INT < 24)
    {
      paramFile = Uri.fromFile(paramFile);
    }
    else
    {
      localIntent.setFlags(1);
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(Utils.getApp().getPackageName());
      ((StringBuilder)localObject).append(".utilcode.provider");
      localObject = ((StringBuilder)localObject).toString();
      paramFile = FileProvider.getUriForFile(Utils.getApp(), (String)localObject, paramFile);
    }
    localIntent.setDataAndType(paramFile, "application/vnd.android.package-archive");
    paramFile = localIntent;
    if (paramBoolean) {
      paramFile = localIntent.addFlags(268435456);
    }
    return paramFile;
  }
  
  private static Intent getLaunchAppIntent(String paramString)
  {
    return getLaunchAppIntent(paramString, false);
  }
  
  private static Intent getLaunchAppIntent(String paramString, boolean paramBoolean)
  {
    Intent localIntent = Utils.getApp().getPackageManager().getLaunchIntentForPackage(paramString);
    if (localIntent == null) {
      return null;
    }
    paramString = localIntent;
    if (paramBoolean) {
      paramString = localIntent.addFlags(268435456);
    }
    return paramString;
  }
  
  private static Intent getUninstallAppIntent(String paramString)
  {
    return getUninstallAppIntent(paramString, false);
  }
  
  private static Intent getUninstallAppIntent(String paramString, boolean paramBoolean)
  {
    Intent localIntent = new Intent("android.intent.action.DELETE");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramString);
    localIntent.setData(Uri.parse(localStringBuilder.toString()));
    paramString = localIntent;
    if (paramBoolean) {
      paramString = localIntent.addFlags(268435456);
    }
    return paramString;
  }
  
  private static byte[] hashTemplate(byte[] paramArrayOfByte, String paramString)
  {
    if (paramArrayOfByte != null)
    {
      if (paramArrayOfByte.length <= 0) {
        return null;
      }
      try
      {
        paramString = MessageDigest.getInstance(paramString);
        paramString.update(paramArrayOfByte);
        paramArrayOfByte = paramString.digest();
        return paramArrayOfByte;
      }
      catch (NoSuchAlgorithmException paramArrayOfByte)
      {
        paramArrayOfByte.printStackTrace();
      }
    }
    return null;
  }
  
  public static void installApp(Activity paramActivity, File paramFile, int paramInt)
  {
    if (!isFileExists(paramFile)) {
      return;
    }
    paramActivity.startActivityForResult(getInstallAppIntent(paramFile), paramInt);
  }
  
  public static void installApp(Activity paramActivity, String paramString, int paramInt)
  {
    installApp(paramActivity, getFileByPath(paramString), paramInt);
  }
  
  public static void installApp(File paramFile)
  {
    if (!isFileExists(paramFile)) {
      return;
    }
    Utils.getApp().startActivity(getInstallAppIntent(paramFile, true));
  }
  
  public static void installApp(String paramString)
  {
    installApp(getFileByPath(paramString));
  }
  
  public static boolean installAppSilent(File paramFile)
  {
    return installAppSilent(paramFile, null);
  }
  
  public static boolean installAppSilent(File paramFile, String paramString)
  {
    return installAppSilent(paramFile, paramString, isDeviceRooted());
  }
  
  public static boolean installAppSilent(File paramFile, String paramString, boolean paramBoolean)
  {
    if (!isFileExists(paramFile)) {
      return false;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append('"');
    ((StringBuilder)localObject).append(paramFile.getAbsolutePath());
    ((StringBuilder)localObject).append('"');
    localObject = ((StringBuilder)localObject).toString();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm install ");
    if (paramString == null)
    {
      paramFile = "";
    }
    else
    {
      paramFile = new StringBuilder();
      paramFile.append(paramString);
      paramFile.append(" ");
      paramFile = paramFile.toString();
    }
    localStringBuilder.append(paramFile);
    localStringBuilder.append((String)localObject);
    paramFile = ShellUtils.execCmd(localStringBuilder.toString(), paramBoolean);
    if ((paramFile.successMsg != null) && (paramFile.successMsg.toLowerCase().contains("success"))) {
      return true;
    }
    paramString = new StringBuilder();
    paramString.append("installAppSilent successMsg: ");
    paramString.append(paramFile.successMsg);
    paramString.append(", errorMsg: ");
    paramString.append(paramFile.errorMsg);
    Log.e("AppUtils", paramString.toString());
    return false;
  }
  
  public static boolean installAppSilent(String paramString)
  {
    return installAppSilent(getFileByPath(paramString), null);
  }
  
  public static boolean installAppSilent(String paramString1, String paramString2)
  {
    return installAppSilent(getFileByPath(paramString1), paramString2);
  }
  
  public static boolean isAppDebug()
  {
    return isAppDebug(Utils.getApp().getPackageName());
  }
  
  public static boolean isAppDebug(String paramString)
  {
    boolean bool1 = isSpace(paramString);
    boolean bool2 = false;
    if (bool1) {
      return false;
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getApplicationInfo(paramString, 0);
      bool1 = bool2;
      if (paramString != null)
      {
        int i = paramString.flags;
        bool1 = bool2;
        if ((i & 0x2) != 0) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  public static boolean isAppForeground()
  {
    return Utils.isAppForeground();
  }
  
  public static boolean isAppForeground(@NonNull String paramString)
  {
    if (paramString != null) {
      return (!isSpace(paramString)) && (paramString.equals(getForegroundProcessName()));
    }
    throw new NullPointerException("Argument 'packageName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
  }
  
  public static boolean isAppInstalled(@NonNull String paramString)
  {
    if (paramString != null) {
      return (!isSpace(paramString)) && (Utils.getApp().getPackageManager().getLaunchIntentForPackage(paramString) != null);
    }
    throw new NullPointerException("Argument 'packageName' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
  }
  
  public static boolean isAppInstalled(@NonNull String paramString1, @NonNull String paramString2)
  {
    if (paramString1 != null)
    {
      if (paramString2 != null)
      {
        paramString1 = new Intent(paramString1);
        paramString1.addCategory(paramString2);
        paramString2 = Utils.getApp().getPackageManager();
        boolean bool = false;
        if (paramString2.resolveActivity(paramString1, 0) != null) {
          bool = true;
        }
        return bool;
      }
      throw new NullPointerException("Argument 'category' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }
    throw new NullPointerException("Argument 'action' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
  }
  
  public static boolean isAppRoot()
  {
    ShellUtils.CommandResult localCommandResult = ShellUtils.execCmd("echo root", true);
    if (localCommandResult.result == 0) {
      return true;
    }
    if (localCommandResult.errorMsg != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("isAppRoot() called");
      localStringBuilder.append(localCommandResult.errorMsg);
      Log.d("AppUtils", localStringBuilder.toString());
    }
    return false;
  }
  
  public static boolean isAppSystem()
  {
    return isAppSystem(Utils.getApp().getPackageName());
  }
  
  public static boolean isAppSystem(String paramString)
  {
    if (isSpace(paramString)) {
      return false;
    }
    try
    {
      paramString = Utils.getApp().getPackageManager().getApplicationInfo(paramString, 0);
      if (paramString != null)
      {
        int i = paramString.flags;
        if ((i & 0x1) != 0) {
          return true;
        }
      }
      return false;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  private static boolean isDeviceRooted()
  {
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "/system/bin/";
    arrayOfString[1] = "/system/xbin/";
    arrayOfString[2] = "/sbin/";
    arrayOfString[3] = "/system/sd/xbin/";
    arrayOfString[4] = "/system/bin/failsafe/";
    arrayOfString[5] = "/data/local/xbin/";
    arrayOfString[6] = "/data/local/bin/";
    arrayOfString[7] = "/data/local/";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = arrayOfString[i];
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append("su");
      if (new File(localStringBuilder.toString()).exists()) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private static boolean isFileExists(File paramFile)
  {
    return (paramFile != null) && (paramFile.exists());
  }
  
  private static boolean isSpace(String paramString)
  {
    if (paramString == null) {
      return true;
    }
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      if (!Character.isWhitespace(paramString.charAt(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static void launchApp(Activity paramActivity, String paramString, int paramInt)
  {
    if (isSpace(paramString)) {
      return;
    }
    paramActivity.startActivityForResult(getLaunchAppIntent(paramString), paramInt);
  }
  
  public static void launchApp(String paramString)
  {
    if (isSpace(paramString)) {
      return;
    }
    Utils.getApp().startActivity(getLaunchAppIntent(paramString, true));
  }
  
  public static void launchAppDetailsSettings()
  {
    launchAppDetailsSettings(Utils.getApp().getPackageName());
  }
  
  public static void launchAppDetailsSettings(String paramString)
  {
    if (isSpace(paramString)) {
      return;
    }
    Intent localIntent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramString);
    localIntent.setData(Uri.parse(localStringBuilder.toString()));
    Utils.getApp().startActivity(localIntent.addFlags(268435456));
  }
  
  public static void registerAppStatusChangedListener(@NonNull Object paramObject, @NonNull Utils.OnAppStatusChangedListener paramOnAppStatusChangedListener)
  {
    if (paramObject != null)
    {
      if (paramOnAppStatusChangedListener != null)
      {
        Utils.getActivityLifecycle().addListener(paramObject, paramOnAppStatusChangedListener);
        return;
      }
      throw new NullPointerException("Argument 'listener' of type Utils.OnAppStatusChangedListener (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }
    throw new NullPointerException("Argument 'obj' of type Object (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
  }
  
  public static void relaunchApp()
  {
    Intent localIntent = Utils.getApp().getPackageManager().getLaunchIntentForPackage(Utils.getApp().getPackageName());
    if (localIntent == null) {
      return;
    }
    localIntent = Intent.makeRestartActivityTask(localIntent.getComponent());
    Utils.getApp().startActivity(localIntent);
    System.exit(0);
  }
  
  public static void uninstallApp(Activity paramActivity, String paramString, int paramInt)
  {
    if (isSpace(paramString)) {
      return;
    }
    paramActivity.startActivityForResult(getUninstallAppIntent(paramString), paramInt);
  }
  
  public static void uninstallApp(String paramString)
  {
    if (isSpace(paramString)) {
      return;
    }
    Utils.getApp().startActivity(getUninstallAppIntent(paramString, true));
  }
  
  public static boolean uninstallAppSilent(String paramString)
  {
    return uninstallAppSilent(paramString, false);
  }
  
  public static boolean uninstallAppSilent(String paramString, boolean paramBoolean)
  {
    return uninstallAppSilent(paramString, paramBoolean, isDeviceRooted());
  }
  
  public static boolean uninstallAppSilent(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (isSpace(paramString)) {
      return false;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LD_LIBRARY_PATH=/vendor/lib*:/system/lib* pm uninstall ");
    if (paramBoolean1) {
      localObject = "-k ";
    } else {
      localObject = "";
    }
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(paramString);
    paramString = ShellUtils.execCmd(localStringBuilder.toString(), paramBoolean2);
    if ((paramString.successMsg != null) && (paramString.successMsg.toLowerCase().contains("success"))) {
      return true;
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("uninstallAppSilent successMsg: ");
    ((StringBuilder)localObject).append(paramString.successMsg);
    ((StringBuilder)localObject).append(", errorMsg: ");
    ((StringBuilder)localObject).append(paramString.errorMsg);
    Log.e("AppUtils", ((StringBuilder)localObject).toString());
    return false;
  }
  
  public static void unregisterAppStatusChangedListener(@NonNull Object paramObject)
  {
    if (paramObject != null)
    {
      Utils.getActivityLifecycle().removeListener(paramObject);
      return;
    }
    throw new NullPointerException("Argument 'obj' of type Object (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
  }
  
  public static class AppInfo
  {
    private Drawable icon;
    private boolean isSystem;
    private String name;
    private String packageName;
    private String packagePath;
    private int versionCode;
    private String versionName;
    
    public AppInfo(String paramString1, String paramString2, Drawable paramDrawable, String paramString3, String paramString4, int paramInt, boolean paramBoolean)
    {
      setName(paramString2);
      setIcon(paramDrawable);
      setPackageName(paramString1);
      setPackagePath(paramString3);
      setVersionName(paramString4);
      setVersionCode(paramInt);
      setSystem(paramBoolean);
    }
    
    public Drawable getIcon()
    {
      return this.icon;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public String getPackageName()
    {
      return this.packageName;
    }
    
    public String getPackagePath()
    {
      return this.packagePath;
    }
    
    public int getVersionCode()
    {
      return this.versionCode;
    }
    
    public String getVersionName()
    {
      return this.versionName;
    }
    
    public boolean isSystem()
    {
      return this.isSystem;
    }
    
    public void setIcon(Drawable paramDrawable)
    {
      this.icon = paramDrawable;
    }
    
    public void setName(String paramString)
    {
      this.name = paramString;
    }
    
    public void setPackageName(String paramString)
    {
      this.packageName = paramString;
    }
    
    public void setPackagePath(String paramString)
    {
      this.packagePath = paramString;
    }
    
    public void setSystem(boolean paramBoolean)
    {
      this.isSystem = paramBoolean;
    }
    
    public void setVersionCode(int paramInt)
    {
      this.versionCode = paramInt;
    }
    
    public void setVersionName(String paramString)
    {
      this.versionName = paramString;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("pkg name: ");
      localStringBuilder.append(getPackageName());
      localStringBuilder.append("\napp icon: ");
      localStringBuilder.append(getIcon());
      localStringBuilder.append("\napp name: ");
      localStringBuilder.append(getName());
      localStringBuilder.append("\napp path: ");
      localStringBuilder.append(getPackagePath());
      localStringBuilder.append("\napp v name: ");
      localStringBuilder.append(getVersionName());
      localStringBuilder.append("\napp v code: ");
      localStringBuilder.append(getVersionCode());
      localStringBuilder.append("\nis system: ");
      localStringBuilder.append(isSystem());
      return localStringBuilder.toString();
    }
  }
}
