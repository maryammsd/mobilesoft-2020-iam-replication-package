package com.tapjoy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.tapjoy.internal.cw;
import com.tapjoy.mraid.view.MraidView;
import com.tapjoy.mraid.view.MraidView.PLACEMENT_TYPE;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class TJAdUnitJSBridge
{
  private TJWebViewJSInterface a;
  public boolean allowRedirect = true;
  private TJAdUnitJSBridge b;
  private Context c;
  public boolean closeRequested = false;
  public boolean customClose = false;
  private TJAdUnit d;
  public boolean didLaunchOtherActivity = false;
  private WebView e;
  private ProgressDialog f;
  private LocationManager g;
  private LocationListener h;
  private View i = null;
  private boolean j;
  public String otherActivityCallbackID = null;
  
  public TJAdUnitJSBridge(Context paramContext, WebView paramWebView)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "creating AdUnit/JS Bridge");
    this.c = paramContext;
    this.e = paramWebView;
    this.b = this;
    if (this.e == null)
    {
      TapjoyLog.e("TJAdUnitJSBridge", "Error: webView is NULL");
      return;
    }
    this.a = new TJWebViewJSInterface(this.e, new TJWebViewJSInterfaceListener()
    {
      public final void onDispatchMethod(String paramAnonymousString, JSONObject paramAnonymousJSONObject)
      {
        if (TJAdUnitJSBridge.a(TJAdUnitJSBridge.this)) {}
        try
        {
          str1 = paramAnonymousJSONObject.getString("callbackId");
        }
        catch (Exception localException)
        {
          for (;;)
          {
            try
            {
              String str1;
              paramAnonymousJSONObject = paramAnonymousJSONObject.getJSONObject("data");
              TJAdUnitJSBridge.class.getMethod(paramAnonymousString, new Class[] { JSONObject.class, String.class }).invoke(TJAdUnitJSBridge.b(TJAdUnitJSBridge.this), new Object[] { paramAnonymousJSONObject, str1 });
              return;
            }
            catch (Exception paramAnonymousString)
            {
              String str2;
              paramAnonymousString.printStackTrace();
              TJAdUnitJSBridge.this.invokeJSCallback(str2, new Object[] { Boolean.FALSE });
            }
            localException = localException;
            str2 = null;
          }
        }
      }
    });
    this.e.addJavascriptInterface(this.a, "AndroidJavascriptInterface");
    this.j = true;
  }
  
  public TJAdUnitJSBridge(Context paramContext, TJAdUnit paramTJAdUnit)
  {
    this(paramContext, paramTJAdUnit.getWebView());
    this.d = paramTJAdUnit;
  }
  
  @SuppressLint({"WorldReadableFiles"})
  private File a(String paramString)
  {
    String str = paramString.substring(paramString.lastIndexOf('.'));
    FileOutputStream localFileOutputStream;
    try
    {
      paramString = new URL(paramString).openStream();
      localFileOutputStream = this.c.openFileOutput("share_temp" + str, 1);
      byte[] arrayOfByte = new byte['က'];
      for (;;)
      {
        int k = paramString.read(arrayOfByte, 0, 4096);
        if (k < 0) {
          break;
        }
        localFileOutputStream.write(arrayOfByte, 0, k);
      }
      return new File(this.c.getFilesDir(), "share_temp" + str);
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    for (;;)
    {
      try
      {
        localFileOutputStream.close();
        paramString.close();
      }
      catch (Exception paramString) {}
    }
  }
  
  /* Error */
  public void alert(JSONObject paramJSONObject, final String paramString)
  {
    // Byte code:
    //   0: ldc 79
    //   2: new 154	java/lang/StringBuilder
    //   5: dup
    //   6: ldc -41
    //   8: invokespecial 157	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   11: aload_1
    //   12: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   15: invokevirtual 165	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   18: invokestatic 86	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   21: ldc -36
    //   23: astore 6
    //   25: aload_1
    //   26: ldc -34
    //   28: invokevirtual 228	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   31: astore 5
    //   33: aload 5
    //   35: astore 6
    //   37: aload_1
    //   38: ldc -26
    //   40: invokevirtual 228	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   43: astore 7
    //   45: aload 7
    //   47: astore 6
    //   49: aload_1
    //   50: ldc -24
    //   52: invokevirtual 236	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   55: astore_1
    //   56: new 238	android/app/AlertDialog$Builder
    //   59: dup
    //   60: aload_0
    //   61: getfield 88	com/tapjoy/TJAdUnitJSBridge:c	Landroid/content/Context;
    //   64: invokespecial 241	android/app/AlertDialog$Builder:<init>	(Landroid/content/Context;)V
    //   67: aload 5
    //   69: invokevirtual 245	android/app/AlertDialog$Builder:setTitle	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   72: aload 6
    //   74: invokevirtual 248	android/app/AlertDialog$Builder:setMessage	(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
    //   77: invokevirtual 252	android/app/AlertDialog$Builder:create	()Landroid/app/AlertDialog;
    //   80: astore 5
    //   82: aload_1
    //   83: ifnull +10 -> 93
    //   86: aload_1
    //   87: invokevirtual 258	org/json/JSONArray:length	()I
    //   90: ifne +56 -> 146
    //   93: aload_0
    //   94: aload_2
    //   95: iconst_1
    //   96: anewarray 4	java/lang/Object
    //   99: dup
    //   100: iconst_0
    //   101: getstatic 264	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   104: aastore
    //   105: invokevirtual 268	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   108: return
    //   109: astore_1
    //   110: ldc -36
    //   112: astore 7
    //   114: aload 6
    //   116: astore 5
    //   118: aload 7
    //   120: astore 6
    //   122: aload_0
    //   123: aload_2
    //   124: iconst_1
    //   125: anewarray 4	java/lang/Object
    //   128: dup
    //   129: iconst_0
    //   130: getstatic 264	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   133: aastore
    //   134: invokevirtual 268	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   137: aload_1
    //   138: invokevirtual 186	java/lang/Exception:printStackTrace	()V
    //   141: aconst_null
    //   142: astore_1
    //   143: goto -87 -> 56
    //   146: new 270	java/util/ArrayList
    //   149: dup
    //   150: invokespecial 271	java/util/ArrayList:<init>	()V
    //   153: astore 6
    //   155: iconst_0
    //   156: istore 4
    //   158: iload 4
    //   160: aload_1
    //   161: invokevirtual 258	org/json/JSONArray:length	()I
    //   164: if_icmpge +98 -> 262
    //   167: iload 4
    //   169: tableswitch	default:+23->192, 0:+71->240, 1:+77->246
    //   192: iconst_m1
    //   193: istore_3
    //   194: aload 6
    //   196: aload_1
    //   197: iload 4
    //   199: invokevirtual 273	org/json/JSONArray:getString	(I)Ljava/lang/String;
    //   202: invokevirtual 277	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   205: pop
    //   206: aload 5
    //   208: iload_3
    //   209: aload 6
    //   211: iload 4
    //   213: invokevirtual 281	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   216: checkcast 283	java/lang/CharSequence
    //   219: new 12	com/tapjoy/TJAdUnitJSBridge$3
    //   222: dup
    //   223: aload_0
    //   224: aload_2
    //   225: invokespecial 286	com/tapjoy/TJAdUnitJSBridge$3:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Ljava/lang/String;)V
    //   228: invokevirtual 292	android/app/AlertDialog:setButton	(ILjava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)V
    //   231: iload 4
    //   233: iconst_1
    //   234: iadd
    //   235: istore 4
    //   237: goto -79 -> 158
    //   240: bipush -2
    //   242: istore_3
    //   243: goto -49 -> 194
    //   246: bipush -3
    //   248: istore_3
    //   249: goto -55 -> 194
    //   252: astore 7
    //   254: aload 7
    //   256: invokevirtual 186	java/lang/Exception:printStackTrace	()V
    //   259: goto -53 -> 206
    //   262: aload 5
    //   264: iconst_0
    //   265: invokevirtual 296	android/app/AlertDialog:setCancelable	(Z)V
    //   268: aload 5
    //   270: iconst_0
    //   271: invokevirtual 299	android/app/AlertDialog:setCanceledOnTouchOutside	(Z)V
    //   274: aload 5
    //   276: invokevirtual 302	android/app/AlertDialog:show	()V
    //   279: return
    //   280: astore_1
    //   281: goto -159 -> 122
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	284	0	this	TJAdUnitJSBridge
    //   0	284	1	paramJSONObject	JSONObject
    //   0	284	2	paramString	String
    //   193	56	3	k	int
    //   156	80	4	m	int
    //   31	244	5	localObject1	Object
    //   23	187	6	localObject2	Object
    //   43	76	7	str	String
    //   252	3	7	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   25	33	109	java/lang/Exception
    //   37	45	109	java/lang/Exception
    //   194	206	252	java/lang/Exception
    //   49	56	280	java/lang/Exception
  }
  
  public void cacheAsset(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = "";
    localLong = Long.valueOf(0L);
    try
    {
      str2 = paramJSONObject.getString("url");
    }
    catch (Exception paramJSONObject)
    {
      String str2;
      String str1;
      long l;
      label45:
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    for (;;)
    {
      try
      {
        str1 = paramJSONObject.getString("offerId");
        localObject = str1;
      }
      catch (Exception localException)
      {
        break;
      }
      try
      {
        l = paramJSONObject.getLong("timeToLive");
        paramJSONObject = Long.valueOf(l);
      }
      catch (Exception paramJSONObject)
      {
        paramJSONObject = localLong;
        break label45;
      }
    }
    if (TapjoyCache.getInstance() != null)
    {
      invokeJSCallback(paramString, new Object[] { TapjoyCache.getInstance().cacheAssetFromURL(str2, (String)localObject, paramJSONObject.longValue()) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void cachePathForURL(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("url");
      if (TapjoyCache.getInstance() != null)
      {
        invokeJSCallback(paramString, new Object[] { TapjoyCache.getInstance().getPathOfCachedURL(paramJSONObject) });
        return;
      }
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { "" });
      return;
    }
    invokeJSCallback(paramString, new Object[] { "" });
  }
  
  public void checkAppInstalled(JSONObject paramJSONObject, String paramString)
  {
    Object localObject = "";
    try
    {
      paramJSONObject = paramJSONObject.getString("bundle");
      if ((paramJSONObject != null) && (paramJSONObject.length() > 0))
      {
        localObject = this.c.getPackageManager().getInstalledApplications(0).iterator();
        while (((Iterator)localObject).hasNext()) {
          if (((ApplicationInfo)((Iterator)localObject).next()).packageName.equals(paramJSONObject))
          {
            invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
            return;
          }
        }
      }
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        paramJSONObject.printStackTrace();
        paramJSONObject = (JSONObject)localObject;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
    }
  }
  
  public void clearCache(JSONObject paramJSONObject, String paramString)
  {
    if (TapjoyCache.getInstance() != null)
    {
      TapjoyCache.getInstance().clearTapjoyCache();
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void clearVideo(JSONObject paramJSONObject, final String paramString)
  {
    if (this.d != null) {
      this.d.clearVideo(new AdUnitAsyncTaskListner()
      {
        public final void onComplete(boolean paramAnonymousBoolean)
        {
          TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramAnonymousBoolean) });
        }
      });
    }
  }
  
  public void closeRequested()
  {
    this.closeRequested = true;
    invokeJSAdunitMethod("closeRequested", new Object[0]);
  }
  
  public void contentReady(JSONObject paramJSONObject, String paramString)
  {
    if (this.d != null)
    {
      this.d.fireContentReady();
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void destroy()
  {
    if ((this.h != null) && (this.g != null))
    {
      this.g.removeUpdates(this.h);
      this.g = null;
      this.h = null;
    }
  }
  
  public void disable()
  {
    this.j = false;
  }
  
  public void dismiss(JSONObject paramJSONObject, String paramString)
  {
    if ((this.c instanceof TJAdUnitActivity))
    {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      ((Activity)this.c).finish();
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void display()
  {
    invokeJSAdunitMethod("display", new Object[0]);
  }
  
  public void displayRichMedia(final JSONObject paramJSONObject, String paramString)
  {
    try
    {
      bool = paramJSONObject.getBoolean("inline");
    }
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          String str1 = paramJSONObject.getString("html");
          if (str1 != null) {
            break;
          }
          invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
          return;
          localException1 = localException1;
          bool = false;
        }
      }
      catch (Exception localException2)
      {
        boolean bool;
        String str2;
        for (;;)
        {
          localException2.printStackTrace();
          str2 = null;
        }
        if (bool)
        {
          ((Activity)this.c).runOnUiThread(new Runnable()
          {
            public final void run()
            {
              try
              {
                String str = paramJSONObject.getString("html");
                if ((TJAdUnitJSBridge.c(TJAdUnitJSBridge.this) != null) && (TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).getParent() != null)) {
                  ((ViewGroup)TJAdUnitJSBridge.c(TJAdUnitJSBridge.this).getParent()).removeView(TJAdUnitJSBridge.c(TJAdUnitJSBridge.this));
                }
                MraidView localMraidView = new MraidView(TJAdUnitJSBridge.d(TJAdUnitJSBridge.this));
                TJAdUnitJSBridge.e(TJAdUnitJSBridge.this).getSettings().setJavaScriptEnabled(true);
                localMraidView.setPlacementType(MraidView.PLACEMENT_TYPE.INLINE);
                localMraidView.setLayoutParams(new ViewGroup.LayoutParams(640, 100));
                localMraidView.setInitialScale(100);
                localMraidView.setBackgroundColor(0);
                localMraidView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
                int i = ((WindowManager)((Activity)TJAdUnitJSBridge.d(TJAdUnitJSBridge.this)).getSystemService("window")).getDefaultDisplay().getWidth();
                TJAdUnitJSBridge.a(TJAdUnitJSBridge.this, TapjoyUtil.scaleDisplayAd(localMraidView, i));
                ((Activity)TJAdUnitJSBridge.d(TJAdUnitJSBridge.this)).addContentView(TJAdUnitJSBridge.c(TJAdUnitJSBridge.this), new ViewGroup.LayoutParams(i, (int)(100.0D * (i / 640.0D))));
                return;
              }
              catch (Exception localException)
              {
                for (;;)
                {
                  localException.printStackTrace();
                  Object localObject = null;
                }
              }
            }
          });
          return;
        }
        paramJSONObject = new TJPlacementData(TapjoyConnectCore.getHostURL(), str2);
        paramJSONObject.setViewType(2);
        paramString = new Intent(this.c, TJAdUnitActivity.class);
        paramString.putExtra("placement_data", paramJSONObject);
        paramString.setFlags(268435456);
        ((Activity)this.c).startActivity(paramString);
      }
    }
  }
  
  public void displayStoreURL(JSONObject paramJSONObject, String paramString)
  {
    displayURL(paramJSONObject, paramString);
  }
  
  public void displayURL(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("url");
      this.didLaunchOtherActivity = true;
      this.otherActivityCallbackID = paramString;
      paramJSONObject = new Intent("android.intent.action.VIEW", Uri.parse(paramJSONObject));
      ((Activity)this.c).startActivity(paramJSONObject);
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void displayVideo(JSONObject paramJSONObject, final String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("url");
      if ((paramJSONObject.length() > 0) && (paramJSONObject != ""))
      {
        this.d.loadVideoUrl(paramJSONObject, new AdUnitAsyncTaskListner()
        {
          public final void onComplete(boolean paramAnonymousBoolean)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramAnonymousBoolean) });
          }
        });
        return;
      }
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void flushMessageQueue()
  {
    this.a.flushMessageQueue();
  }
  
  public void getCachedAssets(JSONObject paramJSONObject, String paramString)
  {
    if (TapjoyCache.getInstance() != null)
    {
      invokeJSCallback(paramString, new Object[] { TapjoyCache.getInstance().cachedAssetsToJSON() });
      return;
    }
    invokeJSCallback(paramString, new Object[] { "" });
  }
  
  public void getInstalledAppData(JSONObject paramJSONObject, String paramString)
  {
    paramJSONObject = this.c.getPackageManager();
    Object localObject1 = paramJSONObject.getInstalledApplications(0);
    JSONArray localJSONArray = new JSONArray();
    localObject1 = ((List)localObject1).iterator();
    for (;;)
    {
      Object localObject2;
      HashMap localHashMap;
      String str;
      if (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
        if ((((ApplicationInfo)localObject2).flags & 0x1) != 1)
        {
          localHashMap = new HashMap();
          str = ((ApplicationInfo)localObject2).packageName;
          localHashMap.put("packageName", str);
          localHashMap.put("targetSdk", Integer.valueOf(((ApplicationInfo)localObject2).targetSdkVersion));
        }
      }
      else
      {
        try
        {
          localObject2 = paramJSONObject.getPackageInfo(str, 4096);
          localHashMap.put("installTime", new Date(((PackageInfo)localObject2).firstInstallTime));
          localHashMap.put("updateTime", new Date(((PackageInfo)localObject2).lastUpdateTime));
          localHashMap.put("versionName", ((PackageInfo)localObject2).versionName);
          localHashMap.put("versionCode", Integer.valueOf(((PackageInfo)localObject2).versionCode));
          localJSONArray.put(new JSONObject(localHashMap));
        }
        catch (Exception localException) {}
        invokeJSCallback(paramString, new Object[] { localJSONArray });
        return;
      }
    }
  }
  
  public void getLocation(JSONObject paramJSONObject, String paramString)
  {
    float f1 = 100.0F;
    for (;;)
    {
      try
      {
        float f2 = Float.valueOf(paramJSONObject.getString("gpsAccuracy")).floatValue();
        f1 = f2;
      }
      catch (Exception localException)
      {
        boolean bool;
        continue;
      }
      try
      {
        bool = Boolean.valueOf(paramJSONObject.getString("enabled")).booleanValue();
        this.g = ((LocationManager)this.c.getSystemService("location"));
        paramJSONObject = new Criteria();
        paramJSONObject = this.g.getBestProvider(paramJSONObject, false);
        if (this.h == null) {
          this.h = new LocationListener()
          {
            public final void onLocationChanged(Location paramAnonymousLocation)
            {
              if ((TJAdUnitJSBridge.d(TJAdUnitJSBridge.this) == null) || (TJAdUnitJSBridge.e(TJAdUnitJSBridge.this) == null)) {
                if ((TJAdUnitJSBridge.f(TJAdUnitJSBridge.this) != null) && (TJAdUnitJSBridge.g(TJAdUnitJSBridge.this) != null))
                {
                  TapjoyLog.i("TJAdUnitJSBridge", "stopping updates");
                  TJAdUnitJSBridge.f(TJAdUnitJSBridge.this).removeUpdates(TJAdUnitJSBridge.g(TJAdUnitJSBridge.this));
                }
              }
              while (paramAnonymousLocation == null) {
                return;
              }
              HashMap localHashMap = new HashMap();
              localHashMap.put("lat", Double.valueOf(paramAnonymousLocation.getLatitude()));
              localHashMap.put("long", Double.valueOf(paramAnonymousLocation.getLongitude()));
              localHashMap.put("altitude", Double.valueOf(paramAnonymousLocation.getAltitude()));
              localHashMap.put("timestamp", Long.valueOf(paramAnonymousLocation.getTime()));
              localHashMap.put("speed", Float.valueOf(paramAnonymousLocation.getSpeed()));
              localHashMap.put("course", Float.valueOf(paramAnonymousLocation.getBearing()));
              TJAdUnitJSBridge.this.invokeJSAdunitMethod("locationUpdated", localHashMap);
            }
            
            public final void onProviderDisabled(String paramAnonymousString) {}
            
            public final void onProviderEnabled(String paramAnonymousString) {}
            
            public final void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle) {}
          };
        }
        if (bool) {
          if (paramJSONObject != null)
          {
            this.g.requestLocationUpdates(paramJSONObject, 0L, f1, this.h);
            invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
            return;
          }
        }
      }
      catch (Exception paramJSONObject)
      {
        paramJSONObject.printStackTrace();
        bool = false;
        continue;
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        return;
      }
      if ((this.g != null) && (this.h != null)) {
        this.g.removeUpdates(this.h);
      }
    }
  }
  
  public void invokeJSAdunitMethod(String paramString, Map paramMap)
  {
    this.a.callback(paramMap, paramString, null);
  }
  
  public void invokeJSAdunitMethod(String paramString, Object... paramVarArgs)
  {
    paramVarArgs = new ArrayList(Arrays.asList(paramVarArgs));
    this.a.callback(paramVarArgs, paramString, null);
  }
  
  public void invokeJSCallback(String paramString, Map paramMap)
  {
    this.a.callback(paramMap, "", paramString);
  }
  
  public void invokeJSCallback(String paramString, Object... paramVarArgs)
  {
    if (cw.c(paramString))
    {
      TapjoyLog.i("TJAdUnitJSBridge", "No callbackID provided");
      return;
    }
    paramVarArgs = new ArrayList(Arrays.asList(paramVarArgs));
    this.a.callback(paramVarArgs, "", paramString);
  }
  
  public void log(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      TapjoyLog.i("TJAdUnitJSBridge", paramJSONObject.getString("message"));
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public void nativeEval(final JSONObject paramJSONObject, final String paramString)
  {
    ((Activity)this.c).runOnUiThread(new Runnable()
    {
      public final void run()
      {
        try
        {
          if (Build.VERSION.SDK_INT >= 19) {
            TJAdUnitJSBridge.e(TJAdUnitJSBridge.this).evaluateJavascript(paramJSONObject.getString("command"), null);
          }
          for (;;)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
            return;
            TJAdUnitJSBridge.e(TJAdUnitJSBridge.this).loadUrl("javascript:" + paramJSONObject.getString("command"));
          }
          return;
        }
        catch (Exception localException)
        {
          TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        }
      }
    });
  }
  
  public void onVideoCompletion()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoComplete");
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoError(String paramString)
  {
    paramString = new HashMap();
    paramString.put("videoEventName", "videoError");
    invokeJSAdunitMethod("videoEvent", paramString);
  }
  
  public void onVideoInfo(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoInfo");
    localHashMap.put("info", paramString);
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoPaused(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoPause");
    localHashMap.put("currentTime", Integer.valueOf(paramInt));
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoProgress(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoProgress");
    localHashMap.put("currentTime", Integer.valueOf(paramInt));
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoReady(int paramInt1, int paramInt2, int paramInt3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoReady");
    localHashMap.put("videoDuration", Integer.valueOf(paramInt1));
    localHashMap.put("videoWidth", Integer.valueOf(paramInt2));
    localHashMap.put("videoHeight", Integer.valueOf(paramInt3));
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void onVideoStarted(int paramInt)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("videoEventName", "videoStart");
    localHashMap.put("currentTime", Integer.valueOf(paramInt));
    invokeJSAdunitMethod("videoEvent", localHashMap);
  }
  
  public void openApp(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("bundle");
      paramJSONObject = this.c.getPackageManager().getLaunchIntentForPackage(paramJSONObject);
      this.c.startActivity(paramJSONObject);
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void pauseVideo(JSONObject paramJSONObject, String paramString)
  {
    if (this.d != null) {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(this.d.pauseVideo()) });
    }
  }
  
  public void playVideo(JSONObject paramJSONObject, String paramString)
  {
    if (this.d != null) {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(this.d.playVideo()) });
    }
  }
  
  /* Error */
  public void present(JSONObject paramJSONObject, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: invokestatic 403	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   4: astore_3
    //   5: aload_1
    //   6: ldc_w 680
    //   9: invokevirtual 228	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   12: invokestatic 576	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   15: astore 5
    //   17: aload_1
    //   18: ldc_w 682
    //   21: invokevirtual 228	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   24: invokestatic 576	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   27: astore 4
    //   29: aload 4
    //   31: astore_3
    //   32: aload_0
    //   33: aload_1
    //   34: ldc_w 683
    //   37: invokevirtual 228	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   40: invokestatic 576	java/lang/Boolean:valueOf	(Ljava/lang/String;)Ljava/lang/Boolean;
    //   43: invokevirtual 579	java/lang/Boolean:booleanValue	()Z
    //   46: putfield 75	com/tapjoy/TJAdUnitJSBridge:customClose	Z
    //   49: new 29	com/tapjoy/TJAdUnitJSBridge$a
    //   52: dup
    //   53: aload_0
    //   54: aload_0
    //   55: getfield 90	com/tapjoy/TJAdUnitJSBridge:e	Landroid/webkit/WebView;
    //   58: invokespecial 686	com/tapjoy/TJAdUnitJSBridge$a:<init>	(Lcom/tapjoy/TJAdUnitJSBridge;Landroid/webkit/WebView;)V
    //   61: iconst_2
    //   62: anewarray 260	java/lang/Boolean
    //   65: dup
    //   66: iconst_0
    //   67: aload 5
    //   69: aastore
    //   70: dup
    //   71: iconst_1
    //   72: aload_3
    //   73: aastore
    //   74: invokevirtual 690	com/tapjoy/TJAdUnitJSBridge$a:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   77: pop
    //   78: aload_0
    //   79: aload_2
    //   80: iconst_1
    //   81: anewarray 4	java/lang/Object
    //   84: dup
    //   85: iconst_0
    //   86: getstatic 383	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   89: aastore
    //   90: invokevirtual 268	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   93: return
    //   94: astore_1
    //   95: aload_0
    //   96: aload_2
    //   97: iconst_1
    //   98: anewarray 4	java/lang/Object
    //   101: dup
    //   102: iconst_0
    //   103: getstatic 264	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   106: aastore
    //   107: invokevirtual 268	com/tapjoy/TJAdUnitJSBridge:invokeJSCallback	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   110: aload_1
    //   111: invokevirtual 186	java/lang/Exception:printStackTrace	()V
    //   114: return
    //   115: astore_1
    //   116: goto -67 -> 49
    //   119: astore 4
    //   121: goto -89 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	this	TJAdUnitJSBridge
    //   0	124	1	paramJSONObject	JSONObject
    //   0	124	2	paramString	String
    //   4	69	3	localObject	Object
    //   27	3	4	localBoolean1	Boolean
    //   119	1	4	localException	Exception
    //   15	53	5	localBoolean2	Boolean
    // Exception table:
    //   from	to	target	type
    //   0	17	94	java/lang/Exception
    //   49	93	94	java/lang/Exception
    //   32	49	115	java/lang/Exception
    //   17	29	119	java/lang/Exception
  }
  
  public void removeAssetFromCache(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("url");
      if (TapjoyCache.getInstance() != null)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(TapjoyCache.getInstance().removeAssetFromCache(paramJSONObject)) });
        return;
      }
    }
    catch (Exception paramJSONObject)
    {
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void setAllowRedirect(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      bool = paramJSONObject.getBoolean("enabled");
      this.allowRedirect = bool;
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        boolean bool = true;
      }
    }
  }
  
  public void setBackgroundColor(JSONObject paramJSONObject, final String paramString)
  {
    try
    {
      paramJSONObject = paramJSONObject.getString("backgroundColor");
      if (this.d != null)
      {
        this.d.setBackgroundColor(paramJSONObject, new AdUnitAsyncTaskListner()
        {
          public final void onComplete(boolean paramAnonymousBoolean)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramAnonymousBoolean) });
          }
        });
        return;
      }
    }
    catch (Exception paramJSONObject)
    {
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background color. Invalid parameters.");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void setBackgroundWebViewContent(JSONObject paramJSONObject, final String paramString)
  {
    TapjoyLog.i("TJAdUnitJSBridge", "setBackgroundWebViewContent");
    try
    {
      paramJSONObject = paramJSONObject.getString("backgroundContent");
      if (this.d != null)
      {
        this.d.setBackgroundContent(paramJSONObject, new AdUnitAsyncTaskListner()
        {
          public final void onComplete(boolean paramAnonymousBoolean)
          {
            TJAdUnitJSBridge.this.invokeJSCallback(paramString, new Object[] { Boolean.valueOf(paramAnonymousBoolean) });
          }
        });
        return;
      }
    }
    catch (Exception paramJSONObject)
    {
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to set background content. Invalid parameters.");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
  }
  
  public void setCloseButtonVisible(JSONObject paramJSONObject, String paramString)
  {
    if (!(this.c instanceof TJAdUnitActivity))
    {
      TapjoyLog.i("TJAdUnitJSBridge", "Not a TJAdUnitActivity");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    try
    {
      final boolean bool = paramJSONObject.getBoolean("visible");
      ((Activity)this.c).runOnUiThread(new Runnable()
      {
        public final void run()
        {
          ((TJAdUnitActivity)TJAdUnitJSBridge.d(TJAdUnitJSBridge.this)).setCloseButtonVisibility(bool);
        }
      });
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (Exception paramJSONObject)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      paramJSONObject.printStackTrace();
    }
  }
  
  public void setContext(Context paramContext)
  {
    this.c = paramContext;
  }
  
  public void setEventPreloadLimit(JSONObject paramJSONObject, String paramString)
  {
    if (TapjoyCache.getInstance() != null) {
      try
      {
        int k = paramJSONObject.getInt("eventPreloadLimit");
        TJPlacementManager.setCachedPlacementLimit(k);
        invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
        return;
      }
      catch (Exception paramJSONObject)
      {
        TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy cache's event preload limit. Invalid parameters.");
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        return;
      }
    }
    invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
  }
  
  public void setOrientation(JSONObject paramJSONObject, String paramString)
  {
    if (this.d == null)
    {
      TapjoyLog.i("TJAdUnitJSBridge", "No ad unit provided");
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
      return;
    }
    for (;;)
    {
      int k;
      try
      {
        paramJSONObject = paramJSONObject.getString("orientation");
        if (paramJSONObject.equals("landscape")) {
          break label131;
        }
        if (!paramJSONObject.equals("landscapeLeft")) {
          break label106;
        }
      }
      catch (Exception paramJSONObject)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.valueOf(false) });
        return;
      }
      this.d.setOrientation(k);
      invokeJSCallback(paramString, new Object[] { Boolean.valueOf(true) });
      return;
      label106:
      boolean bool = paramJSONObject.equals("landscapeRight");
      if (bool)
      {
        k = 8;
      }
      else
      {
        k = 1;
        continue;
        label131:
        k = 0;
      }
    }
  }
  
  public void setPrerenderLimit(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      int k = paramJSONObject.getInt("prerenderLimit");
      TJPlacementManager.setPreRenderedPlacementLimit(k);
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
    }
    catch (Exception paramJSONObject)
    {
      TapjoyLog.w("TJAdUnitJSBridge", "Unable to set Tapjoy placement pre-render limit. Invalid parameters.");
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
    }
  }
  
  public void setSpinnerVisible(JSONObject paramJSONObject, String paramString)
  {
    localObject1 = "Loading...";
    try
    {
      bool = paramJSONObject.getBoolean("visible");
    }
    catch (Exception paramJSONObject)
    {
      boolean bool;
      label32:
      invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
      paramJSONObject.printStackTrace();
      return;
    }
    try
    {
      localObject2 = paramJSONObject.getString("title");
      localObject1 = localObject2;
      paramJSONObject = paramJSONObject.getString("message");
    }
    catch (Exception paramJSONObject)
    {
      paramJSONObject = "";
      localObject2 = localObject1;
      break label32;
    }
    if (bool) {
      this.f = ProgressDialog.show(this.c, (CharSequence)localObject2, paramJSONObject);
    }
    for (;;)
    {
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
      return;
      if (this.f != null) {
        this.f.dismiss();
      }
    }
  }
  
  public void share(JSONObject paramJSONObject, String paramString)
  {
    Object localObject4 = null;
    for (;;)
    {
      Intent localIntent;
      Object localObject3;
      int k;
      Object localObject2;
      try
      {
        String str2 = paramJSONObject.getString("network");
        String str1 = paramJSONObject.getString("message");
        localIntent = new Intent("android.intent.action.SEND");
        try
        {
          localObject1 = paramJSONObject.getString("imageURL");
        }
        catch (Exception localException)
        {
          try
          {
            Object localObject1;
            paramJSONObject = paramJSONObject.getString("linkURL");
            localObject3 = localObject4;
            if (localObject1 != null)
            {
              File localFile = a((String)localObject1);
              localObject3 = localObject4;
              if (localFile != null) {
                localObject3 = "file://" + localFile.getAbsolutePath();
              }
            }
            localObject4 = str1;
            if (paramJSONObject != null) {
              localObject4 = str1 + "\n" + paramJSONObject;
            }
            localIntent.putExtra("android.intent.extra.TEXT", (String)localObject4);
            if (str2.equals("facebook")) {
              if ((localObject1 != null) && (localObject3 != null))
              {
                localIntent.setType("image/*");
                localIntent.putExtra("android.intent.extra.STREAM", Uri.parse((String)localObject3));
                paramJSONObject = this.c.getPackageManager().queryIntentActivities(localIntent, 0).iterator();
                if (!paramJSONObject.hasNext()) {
                  break label448;
                }
                localObject1 = (ResolveInfo)paramJSONObject.next();
                if ((!((ResolveInfo)localObject1).activityInfo.packageName.toLowerCase(Locale.ENGLISH).contains(str2)) && (!((ResolveInfo)localObject1).activityInfo.name.toLowerCase(Locale.ENGLISH).contains(str2))) {
                  continue;
                }
                localIntent.setPackage(((ResolveInfo)localObject1).activityInfo.packageName);
                k = 1;
                if (k != 0) {
                  break label417;
                }
                invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
                return;
                localException = localException;
                TapjoyLog.i("TJAdUnitJSBridge", "no imageURL");
                localObject2 = null;
                continue;
              }
            }
          }
          catch (Exception paramJSONObject)
          {
            TapjoyLog.i("TJAdUnitJSBridge", "no linkURL");
            paramJSONObject = null;
            continue;
            localIntent.setType("text/plain");
            continue;
          }
        }
        if (!str2.equals("twitter")) {
          continue;
        }
      }
      catch (Exception paramJSONObject)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        paramJSONObject.printStackTrace();
        return;
      }
      localIntent.setType("*/*");
      if ((localObject2 != null) && (localObject3 != null))
      {
        localIntent.putExtra("android.intent.extra.STREAM", Uri.parse((String)localObject3));
        continue;
        label417:
        this.didLaunchOtherActivity = true;
        this.otherActivityCallbackID = paramString;
        paramJSONObject = Intent.createChooser(localIntent, "Select");
        ((Activity)this.c).startActivity(paramJSONObject);
        return;
        label448:
        k = 0;
      }
    }
  }
  
  public void shouldClose(JSONObject paramJSONObject, String paramString)
  {
    try
    {
      if ((Boolean.valueOf(paramJSONObject.getString("close")).booleanValue()) && ((this.c instanceof Activity))) {
        ((Activity)this.c).finish();
      }
      invokeJSCallback(paramString, new Object[] { Boolean.TRUE });
    }
    catch (Exception paramJSONObject)
    {
      for (;;)
      {
        invokeJSCallback(paramString, new Object[] { Boolean.FALSE });
        ((Activity)this.c).finish();
        paramJSONObject.printStackTrace();
      }
    }
    this.closeRequested = false;
  }
  
  public void triggerEvent(JSONObject paramJSONObject, String paramString)
  {
    if (this.d != null) {}
    do
    {
      try
      {
        paramJSONObject = paramJSONObject.getString("eventName");
        if (paramJSONObject.equals("start"))
        {
          this.d.getPublisherVideoListener().onVideoStart();
          return;
        }
      }
      catch (Exception paramJSONObject)
      {
        TapjoyLog.w("TJAdUnitJSBridge", "Unable to triggerEvent. No event name.");
        return;
      }
      if (paramJSONObject.equals("complete"))
      {
        this.d.getPublisherVideoListener().onVideoComplete();
        return;
      }
    } while (!paramJSONObject.equals("error"));
    this.d.getPublisherVideoListener().onVideoError(3);
  }
  
  public static abstract interface AdUnitAsyncTaskListner
  {
    public abstract void onComplete(boolean paramBoolean);
  }
  
  @TargetApi(11)
  final class a
    extends AsyncTask
  {
    WebView a;
    
    public a(WebView paramWebView)
    {
      this.a = paramWebView;
    }
  }
}
