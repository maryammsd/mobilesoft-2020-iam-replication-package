package com.eyefilter.nightmode.bluelightfilter.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class n
{
  private static n a;
  
  private n() {}
  
  public static n a()
  {
    try
    {
      if (a == null) {
        a = new n();
      }
      n localN = a;
      return localN;
    }
    finally {}
  }
  
  public void a(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(paramString));
      localIntent.setFlags(268435456);
      if (a(paramContext)) {
        localIntent.setPackage("com.android.vending");
      }
      paramContext.startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public boolean a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        boolean bool;
        do
        {
          PackageInfo localPackageInfo;
          do
          {
            if (!paramContext.hasNext()) {
              break;
            }
            localPackageInfo = (PackageInfo)paramContext.next();
          } while (TextUtils.isEmpty(localPackageInfo.packageName));
          bool = localPackageInfo.packageName.equals("com.android.vending");
        } while (!bool);
        return true;
      }
    }
    catch (RuntimeException paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
      }
    }
  }
}
