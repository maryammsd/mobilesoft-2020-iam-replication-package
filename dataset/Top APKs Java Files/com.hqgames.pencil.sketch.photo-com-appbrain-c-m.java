package com.appbrain.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.SystemClock;
import android.support.annotation.UiThread;
import java.util.Collections;
import java.util.List;

public class m
{
  public static a a;
  private static final String b = "m";
  private static List c;
  private static long d;
  
  public m() {}
  
  @UiThread
  public static List a()
  {
    ae.c();
    long l = SystemClock.elapsedRealtime();
    if ((c == null) || (d < l - 60000L)) {
      try
      {
        c = l.a().getPackageManager().getInstalledPackages(0);
        if (a != null)
        {
          SystemClock.elapsedRealtime();
          if (c != null) {
            c.size();
          }
        }
        d = l;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
    if (c == null) {
      return Collections.emptyList();
    }
    return c;
  }
  
  public static boolean a(String paramString)
  {
    return c(paramString) != null;
  }
  
  @UiThread
  public static void b(String paramString)
  {
    Context localContext = l.a(null);
    try
    {
      paramString = localContext.getPackageManager().getLaunchIntentForPackage(paramString);
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        try
        {
          paramString.addFlags(268435456);
          localContext.startActivity(paramString);
          return;
        }
        catch (Exception paramString) {}
        paramString = paramString;
      }
    }
    paramString = null;
    if (paramString != null) {}
  }
  
  private static PackageInfo c(String paramString)
  {
    try
    {
      paramString = l.a().getPackageManager().getPackageInfo(paramString, 0);
      return paramString;
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static abstract interface a {}
}
