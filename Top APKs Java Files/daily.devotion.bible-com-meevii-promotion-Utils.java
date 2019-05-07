package com.meevii.promotion;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.meevii.promotion.bean.AppModel;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Utils
{
  private static boolean checkOnlineVersion(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext()) {
      if (((PackageInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static int dpToPx(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    return Math.round(paramInt * (paramContext.xdpi / 160.0F));
  }
  
  private static DisplayMetrics getScreenMetric(Context paramContext)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }
  
  public static int getScreenWidth(Context paramContext)
  {
    return getScreenMetric(paramContext).widthPixels;
  }
  
  public static String getTodayString()
  {
    Calendar localCalendar = Calendar.getInstance();
    return new SimpleDateFormat("yyyyMMdd", Locale.US).format(localCalendar.getTime());
  }
  
  public static void handleOnClick(Context paramContext, AppModel paramAppModel)
  {
    if (paramAppModel == null) {
      return;
    }
    String str = paramAppModel.packageName;
    if (TextUtils.isEmpty(str)) {
      return;
    }
    if (!checkOnlineVersion(paramContext, str))
    {
      searchMarket(paramContext, paramAppModel);
      return;
    }
    paramAppModel = paramContext.getPackageManager().getLaunchIntentForPackage(str);
    paramAppModel.setAction("android.intent.action.VIEW");
    paramAppModel.setFlags(268435456);
    try
    {
      paramContext.startActivity(paramAppModel);
      return;
    }
    catch (ActivityNotFoundException paramAppModel)
    {
      for (;;) {}
    }
    Toast.makeText(paramContext, R.string.google_play_store_not_available, 1).show();
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    paramContext = null;
    if (paramContext != null) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isEmpty(Collection paramCollection)
  {
    return (paramCollection == null) || (paramCollection.isEmpty());
  }
  
  static boolean isNetWorkConnected(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnected());
  }
  
  public static String md5(String paramString)
    throws RuntimeException
  {
    try
    {
      paramString = MessageDigest.getInstance("MD5").digest(paramString.getBytes("UTF-8"));
      StringBuilder localStringBuilder = new StringBuilder(paramString.length * 2);
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        int k = paramString[i] & 0xFF;
        if (k < 16) {
          localStringBuilder.append("0");
        }
        localStringBuilder.append(Integer.toHexString(k));
        i += 1;
      }
      return localStringBuilder.toString().toLowerCase();
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new RuntimeException(" UTF-8 should be supported?", paramString);
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new RuntimeException("MD5 should be supported?", paramString);
    }
  }
  
  private static boolean searchMarket(Context paramContext, AppModel paramAppModel)
  {
    paramAppModel = Uri.parse(paramAppModel.url);
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(paramAppModel);
    try
    {
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (ActivityNotFoundException paramAppModel)
    {
      Toast.makeText(paramContext, R.string.google_play_store_not_available, 1).show();
      ThrowableExtension.printStackTrace(paramAppModel);
    }
    return false;
  }
}
