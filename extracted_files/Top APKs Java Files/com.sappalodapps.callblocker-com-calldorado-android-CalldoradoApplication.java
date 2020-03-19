package com.calldorado.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import c.CCL;
import c.CFY;
import c.CFY.J6;
import c.CU;
import c.E3R;
import c.LWI;
import c.M4X;
import c.OT;
import c.P7R;
import c.PDJ;
import c.PI5;
import c.PI5.J6;
import c.SYR;
import c.WP_;
import c.WZI;
import c.Y7G;
import c.Z0S;
import c._;
import com.calldorado.android.ui.views.custom.CalldoradoCustomView;
import com.calldorado.android.ui.wic.WICController;
import com.firebase.jobdispatcher.e;
import com.firebase.jobdispatcher.g;
import com.p3group.insight.InsightCore;
import com.tutelatechnologies.c1o.sdk.framework.TutelaSDK;
import com.tutelatechnologies.c1o.sdk.framework.TutelaSDKFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalldoradoApplication
{
  public static String a = "https://traffic.calldorado.com";
  public static String b;
  private static final String c = "CalldoradoApplication";
  private static CalldoradoApplication d;
  private static final byte[] v = { 108, -74, -58, 41, 11, 10, -10, 5, 4, -8, 23, -2, 7, -3, -54, 55, -48, 50, 11, 12, -65, 65, 8, -6, -55, 57, 14, -12, 8 };
  private static int w = 58;
  private ClientConfig e = null;
  private SYR f = null;
  private _ g = null;
  private WICController h = null;
  private WZI i = null;
  private OT j = null;
  private WP_ k = null;
  private E3R l = null;
  private Typeface m;
  private CalldoradoCustomView n;
  private CalldoradoCustomView o;
  private CalldoradoCustomView p;
  private LWI q;
  private boolean r;
  private e s;
  private String t;
  private final BroadcastReceiver u = new BroadcastReceiver()
  {
    public void onReceive(final Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      try
      {
        if (paramAnonymousIntent.getBooleanExtra("tutelaSdkDeploymentKeyStatusExtra", false))
        {
          Z0S.a(CalldoradoApplication.x(), "Tutela SDK successfully initialized. Setting ID");
          new PI5(paramAnonymousContext, new PI5.J6()
          {
            public void a(String paramAnonymous2String)
            {
              if (paramAnonymousContext != null) {
                TutelaSDKFactory.getTheSDK().setAaid(paramAnonymous2String, paramAnonymousContext);
              }
            }
          }).execute(new Void[0]);
        }
        else
        {
          Z0S.a(CalldoradoApplication.x(), "Tutela SDK not successfully initialized.");
        }
      }
      catch (Exception paramAnonymousIntent)
      {
        paramAnonymousIntent.printStackTrace();
      }
      TutelaSDKFactory.getTheSDK().unRegisterReceiver(paramAnonymousContext, CalldoradoApplication.a(CalldoradoApplication.this));
    }
  };
  
  private CalldoradoApplication(Context paramContext)
  {
    if (paramContext == null) {
      return;
    }
    Z0S.a("calldoradoApp", "Application CalldoradoApplication");
    h(paramContext);
    this.e = new ClientConfig(paramContext);
    this.f = new SYR(paramContext);
    this.g = new _(paramContext);
    this.l = new E3R(paramContext);
    this.h = new WICController(paramContext);
    this.i = new WZI(paramContext);
    this.j = new OT(paramContext);
    this.k = new WP_();
    this.m = M4X.j(paramContext);
    boolean bool;
    if ((M4X.e(paramContext)) && (M4X.f(paramContext))) {
      bool = true;
    } else {
      bool = false;
    }
    this.r = bool;
    this.s = new e(new g(paramContext));
    this.q = new LWI(paramContext, this.e);
    if (!M4X.g(paramContext)) {
      if ((this.e.aZ()) && (M4X.c(this.e.bJ(), this.e.bL())))
      {
        if (Build.VERSION.SDK_INT >= 14) {
          try
          {
            InsightCore.init(paramContext.getApplicationContext(), CU.a());
            String str = c;
            StringBuilder localStringBuilder = new StringBuilder("P3 GUID: ");
            localStringBuilder.append(InsightCore.getGUID());
            Z0S.a(str, localStringBuilder.toString());
            if (!InsightCore.isInitialized()) {
              break label434;
            }
            Z0S.a(c, "P3 IS initialized!!");
            if (!InsightCore.getConnectivityTestEnabled()) {
              InsightCore.setConnectivityTestEnabled(true);
            }
            if (!InsightCore.getCoverageMapperServiceEnabled()) {
              InsightCore.setCoverageMapperServiceEnabled(true);
            }
            if (!InsightCore.getVoiceServiceEnabled()) {
              InsightCore.setVoiceServiceEnabled(true);
            }
            if (!InsightCore.getAppUsageServiceEnabled()) {
              InsightCore.setAppUsageServiceEnabled(true);
            }
            if (InsightCore.getTrafficAnalyzerEnabled()) {
              break label434;
            }
            InsightCore.setTrafficAnalyzerEnabled(true);
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
          }
        }
      }
      else if (InsightCore.isInitialized())
      {
        InsightCore.setConnectivityTestEnabled(false);
        InsightCore.setCoverageMapperServiceEnabled(false);
        InsightCore.setVoiceServiceEnabled(false);
        InsightCore.setAppUsageServiceEnabled(false);
        InsightCore.setTrafficAnalyzerEnabled(false);
        Z0S.a(c, "Deactivating P3");
      }
    }
    label434:
    if ((this.e.ba()) && (M4X.c(this.e.bJ(), this.e.bK())) && (Build.VERSION.SDK_INT >= 17) && (!M4X.g(paramContext)))
    {
      Z0S.a(c, "Mars Media is initialized!");
      CCL.a(paramContext.getApplicationContext());
      CCL.a("e111e0e82cd9d0d047c02375b5f26422");
    }
    if ((this.e.cz()) && (M4X.c(this.e.bJ(), this.e.cA())))
    {
      IntentFilter localIntentFilter = new IntentFilter("tutelaSdkDeploymentKeyBroadcast");
      TutelaSDKFactory.getTheSDK().registerReceiver(paramContext, this.u, localIntentFilter);
      try
      {
        TutelaSDKFactory.getTheSDK().initializeWithApiKey(a((short)-1).intern(), paramContext.getApplicationContext());
        TutelaSDKFactory.getTheSDK().setAaid(a((short)-1).intern(), paramContext.getApplicationContext());
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    else if (TutelaSDKFactory.getTheSDK().isTutelaServiceActive(paramContext))
    {
      TutelaSDKFactory.getTheSDK().stopTutelaService(paramContext);
    }
    new CFY(paramContext, new CFY.J6()
    {
      public void a(String paramAnonymousString)
      {
        CalldoradoApplication.a(CalldoradoApplication.this, paramAnonymousString);
      }
    }).execute(new Void[0]);
  }
  
  public static CalldoradoApplication a(Context paramContext)
  {
    if ((d == null) && (paramContext != null)) {
      try
      {
        if ((d == null) && (paramContext != null))
        {
          Z0S.a("calldoradoApp", "********** Application Is Null Create a New ************");
          d = new CalldoradoApplication(paramContext);
        }
      }
      finally {}
    }
    return d;
  }
  
  private static String a(short paramShort)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: fail exe a8 = a2\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:92)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.dfs(Cfg.java:255)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze0(BaseAnalyze.java:75)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.analyze(BaseAnalyze.java:69)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer.transform(UnSSATransformer.java:274)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:163)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\nCaused by: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:552)\n\tat com.googlecode.dex2jar.ir.ts.UnSSATransformer$LiveA.onUseLocal(UnSSATransformer.java:1)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:166)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.onUse(BaseAnalyze.java:1)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:331)\n\tat com.googlecode.dex2jar.ir.ts.Cfg.travel(Cfg.java:387)\n\tat com.googlecode.dex2jar.ir.ts.an.BaseAnalyze.exec(BaseAnalyze.java:90)\n\t... 17 more\n");
  }
  
  private static void h(Context paramContext)
  {
    for (;;)
    {
      Object localObject1;
      Object localObject2;
      Object localObject3;
      boolean bool;
      try
      {
        Z0S.a("calldoradoApp", "renameOldSharedPrefs run ");
        if (paramContext == null) {}
      }
      finally {}
      try
      {
        if ((paramContext.getFilesDir() == null) || (paramContext.getFilesDir().getPath() == null)) {
          continue;
        }
        localObject1 = paramContext.getFilesDir().getPath();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(((String)localObject1).replace("files", "shared_prefs"));
        ((StringBuilder)localObject2).append("/");
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("adaffix.xml");
        localObject2 = new File(((StringBuilder)localObject2).toString());
        localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append((String)localObject1);
        ((StringBuilder)localObject3).append("adContainer.xml");
        localObject3 = new File(((StringBuilder)localObject3).toString());
        if ((((File)localObject2).exists()) && (!((File)localObject3).exists()))
        {
          StringBuilder localStringBuilder = new StringBuilder("old shared_prefs path1: ");
          localStringBuilder.append(localObject2);
          Z0S.a("calldoradoApp", localStringBuilder.toString());
          bool = ((File)localObject2).renameTo((File)localObject3);
          localObject2 = new StringBuilder("shared_prefs1 renamed OK: ");
          ((StringBuilder)localObject2).append(bool);
          Z0S.a("calldoradoApp", ((StringBuilder)localObject2).toString());
        }
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append(paramContext.getPackageName());
        ((StringBuilder)localObject2).append("adaffix.xml");
        paramContext = new File(((StringBuilder)localObject2).toString());
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("calldorado.xml");
        localObject1 = new File(((StringBuilder)localObject2).toString());
        if ((!paramContext.exists()) || (((File)localObject1).exists())) {
          continue;
        }
        localObject2 = new StringBuilder("old shared_prefs path2: ");
        ((StringBuilder)localObject2).append(paramContext);
        Z0S.a("calldoradoApp", ((StringBuilder)localObject2).toString());
        bool = paramContext.renameTo((File)localObject1);
        paramContext = new StringBuilder("shared_prefs2 renamed OK: ");
        paramContext.append(bool);
        Z0S.a("calldoradoApp", paramContext.toString());
      }
      catch (NullPointerException paramContext) {}
    }
    return;
  }
  
  public void a(CalldoradoCustomView paramCalldoradoCustomView)
  {
    if (i().bm())
    {
      this.p = paramCalldoradoCustomView;
      return;
    }
    this.p = null;
  }
  
  public boolean a()
  {
    String str = c;
    StringBuilder localStringBuilder = new StringBuilder("isEEA=");
    localStringBuilder.append(this.r);
    Z0S.a(str, localStringBuilder.toString());
    return this.r;
  }
  
  public LWI b()
  {
    return this.q;
  }
  
  public void b(Context paramContext)
  {
    this.e = new ClientConfig(paramContext);
    this.f = new SYR(paramContext);
    this.g = new _(paramContext);
    this.l = new E3R(paramContext);
    this.h = new WICController(paramContext);
    this.i = new WZI(paramContext);
    this.j = new OT(paramContext);
    this.k = new WP_();
  }
  
  public void b(CalldoradoCustomView paramCalldoradoCustomView)
  {
    if (i().bm())
    {
      this.o = paramCalldoradoCustomView;
      return;
    }
    this.o = null;
  }
  
  public e c()
  {
    return this.s;
  }
  
  public String c(Context paramContext)
  {
    if ((i().bj() == null) && (Build.VERSION.SDK_INT < 26))
    {
      i().t(Settings.Secure.getString(paramContext.getContentResolver(), "android_id"));
      paramContext = c;
      StringBuilder localStringBuilder = new StringBuilder("Android device ID: ");
      localStringBuilder.append(i().bj());
      Z0S.c(paramContext, localStringBuilder.toString());
    }
    return i().bj();
  }
  
  public String d(Context paramContext)
  {
    if (b == null) {
      b = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
    }
    if ((b != null) && (b.length() > 3)) {
      localObject2 = b.substring(0, 3);
    } else {
      localObject2 = null;
    }
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = localObject2;
      if (Build.VERSION.SDK_INT >= 22) {
        if (P7R.a(paramContext, "android.permission.READ_PHONE_STATE"))
        {
          Z0S.c(c, "GRANTED MCC");
          localObject1 = new WP_().a(paramContext, 0);
        }
        else
        {
          Z0S.e(c, "DENIED MCC - tryin fallback");
          localObject1 = localObject2;
        }
      }
    }
    Object localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = f(paramContext);
    }
    paramContext = c;
    localObject1 = new StringBuilder("MCC: ");
    ((StringBuilder)localObject1).append((String)localObject2);
    Z0S.a(paramContext, ((StringBuilder)localObject1).toString());
    return localObject2;
  }
  
  public void d()
  {
    c().a("calldoradoApp");
    Z0S.a(c, "Cancelling JobScheduler");
  }
  
  public E3R e()
  {
    return this.l;
  }
  
  public String e(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 22) {
      if (P7R.a(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        Z0S.c(c, "GRANTED MNC");
        if (this.k != null)
        {
          localObject2 = this.k.a(paramContext, 1);
          localObject1 = localObject2;
          if (this.k.a()) {
            break label91;
          }
          localObject1 = localObject2;
          if (Looper.myLooper() != Looper.getMainLooper()) {
            break label91;
          }
          this.k.a(paramContext);
          localObject1 = localObject2;
          break label91;
        }
      }
      else
      {
        Z0S.e(c, "DENIED MNC - tryin fallback");
      }
    }
    Object localObject1 = null;
    label91:
    Object localObject2 = localObject1;
    if (localObject1 == null)
    {
      if (b == null) {
        b = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperator();
      }
      localObject2 = localObject1;
      if (b != null)
      {
        localObject2 = localObject1;
        if (b.length() > 3) {
          localObject2 = b.substring(3);
        }
      }
    }
    paramContext = c;
    localObject1 = new StringBuilder("MNC: ");
    ((StringBuilder)localObject1).append((String)localObject2);
    Z0S.a(paramContext, ((StringBuilder)localObject1).toString());
    return localObject2;
  }
  
  public CalldoradoCustomView f()
  {
    return this.p;
  }
  
  public String f(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration().locale.getCountry().toLowerCase();
    if (Y7G.a == null) {
      Y7G.a = new Y7G();
    }
    return String.valueOf(Y7G.a.a().get(paramContext));
  }
  
  public CalldoradoCustomView g()
  {
    return this.o;
  }
  
  public String g(Context paramContext)
  {
    try
    {
      Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(128).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (localPackageInfo.packageName == paramContext.getPackageName())
        {
          paramContext = PDJ.a(localPackageInfo.applicationInfo);
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      Z0S.b(c, "Exception getInstallerName", paramContext);
    }
    return "";
  }
  
  public CalldoradoCustomView h()
  {
    return this.n;
  }
  
  public ClientConfig i()
  {
    return this.e;
  }
  
  public WZI j()
  {
    return this.i;
  }
  
  public OT k()
  {
    return this.j;
  }
  
  public String l()
  {
    return this.t;
  }
  
  public String m()
  {
    Z0S.a(c, "BNID = apk-5.0.15.1549");
    return "apk-5.0.15.1549";
  }
  
  public String n()
  {
    String[] arrayOfString = "5.0.15.1549".split("\\.");
    if (arrayOfString != null)
    {
      String str = c;
      StringBuilder localStringBuilder = new StringBuilder("getVersion() array length: ");
      localStringBuilder.append(arrayOfString.length);
      Z0S.a(str, localStringBuilder.toString());
    }
    if ((arrayOfString != null) && (arrayOfString.length == 4)) {
      return "5.0.15.1549".substring(0, "5.0.15.1549".lastIndexOf("."));
    }
    return "5.0.15.1549";
  }
  
  public String o()
  {
    Z0S.a(c, "fullver = 5.0.15.1549");
    return "5.0.15.1549";
  }
  
  public String p()
  {
    Object localObject = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)").matcher(o());
    ((Matcher)localObject).find();
    String str = o();
    try
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(((Matcher)localObject).group(1));
      localStringBuilder.append(".");
      localStringBuilder.append(((Matcher)localObject).group(2));
      localStringBuilder.append(".");
      localStringBuilder.append(((Matcher)localObject).group(3));
      localObject = localStringBuilder.toString();
    }
    catch (Exception localException3)
    {
      StringBuilder localStringBuilder;
      label114:
      for (;;) {}
    }
    try
    {
      str = c;
      localStringBuilder = new StringBuilder("getStrippedVersion = ");
      localStringBuilder.append((String)localObject);
      Z0S.a(str, localStringBuilder.toString());
      return localObject;
    }
    catch (Exception localException1)
    {
      Exception localException2 = localException3;
      break label114;
    }
    localObject = c;
    localStringBuilder = new StringBuilder("getStrippedVersion failed = ");
    localStringBuilder.append(str);
    Z0S.a((String)localObject, localStringBuilder.toString());
    return str;
  }
  
  public String q()
  {
    String str;
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("");
      ((StringBuilder)localObject).append("model=");
      ((StringBuilder)localObject).append(Build.MODEL);
      str = ((StringBuilder)localObject).toString();
      try
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(str);
        ((StringBuilder)localObject).append(",manufacturer=");
        ((StringBuilder)localObject).append(Build.MANUFACTURER);
        localObject = ((StringBuilder)localObject).toString();
        return localObject;
      }
      catch (Exception localException1) {}
      Z0S.b(c, "Exception getAndroidModelManufacturer", localException2);
    }
    catch (Exception localException2)
    {
      str = "";
    }
    return str;
  }
  
  public String r()
  {
    try
    {
      String str = Build.VERSION.RELEASE;
      return str;
    }
    catch (Exception localException)
    {
      Z0S.b(c, "Exception getAndroidVersion", localException);
    }
    return "";
  }
  
  public int s()
  {
    try
    {
      int i1 = Build.VERSION.SDK_INT;
      return i1;
    }
    catch (Exception localException)
    {
      Z0S.b(c, "Exception getAndroidSdk", localException);
    }
    return 0;
  }
  
  public SYR t()
  {
    return this.f;
  }
  
  public WICController u()
  {
    return this.h;
  }
  
  public _ v()
  {
    return this.g;
  }
  
  public Typeface w()
  {
    return this.m;
  }
}
