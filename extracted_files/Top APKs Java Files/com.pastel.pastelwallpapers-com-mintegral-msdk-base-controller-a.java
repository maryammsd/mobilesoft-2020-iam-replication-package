package com.mintegral.msdk.base.controller;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Message;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.mintegral.msdk.b.b;
import com.mintegral.msdk.base.entity.g;
import com.mintegral.msdk.base.utils.d;
import com.mintegral.msdk.base.utils.h;
import com.mintegral.msdk.base.utils.i;
import com.mintegral.msdk.base.utils.m;
import com.mintegral.msdk.base.utils.o;
import com.mintegral.msdk.base.utils.t;
import com.mintegral.msdk.base.utils.u;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;

public class a
{
  public static final String a = "a";
  public static List<String> b = new ArrayList();
  private static a c;
  private static CopyOnWriteArraySet<g> j = new CopyOnWriteArraySet();
  private Context d;
  private String e;
  private String f;
  private String g;
  private boolean h = false;
  private List<String> i = null;
  private String k;
  private Location l;
  
  private a() {}
  
  public static Set<g> c()
  {
    return j;
  }
  
  public static a d()
  {
    if (c == null) {
      try
      {
        if (c == null) {
          c = new a();
        }
      }
      finally {}
    }
    return c;
  }
  
  /* Error */
  public static void e()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 213	com/mintegral/msdk/base/controller/a:d	()Lcom/mintegral/msdk/base/controller/a;
    //   6: invokevirtual 215	com/mintegral/msdk/base/controller/a:k	()Ljava/lang/String;
    //   9: invokestatic 151	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   12: ifne +105 -> 117
    //   15: invokestatic 220	com/mintegral/msdk/b/b:a	()Lcom/mintegral/msdk/b/b;
    //   18: pop
    //   19: invokestatic 213	com/mintegral/msdk/base/controller/a:d	()Lcom/mintegral/msdk/base/controller/a;
    //   22: invokevirtual 215	com/mintegral/msdk/base/controller/a:k	()Ljava/lang/String;
    //   25: invokestatic 223	com/mintegral/msdk/b/b:b	(Ljava/lang/String;)Lcom/mintegral/msdk/b/a;
    //   28: astore_1
    //   29: aload_1
    //   30: ifnull +87 -> 117
    //   33: aload_1
    //   34: invokevirtual 228	com/mintegral/msdk/b/a:aN	()Ljava/lang/String;
    //   37: astore_1
    //   38: aload_1
    //   39: invokestatic 151	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   42: ifne +75 -> 117
    //   45: aload_1
    //   46: invokestatic 232	com/mintegral/msdk/base/utils/b:c	(Ljava/lang/String;)Ljava/lang/String;
    //   49: astore_1
    //   50: aload_1
    //   51: invokestatic 151	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   54: ifne +63 -> 117
    //   57: new 234	org/json/JSONArray
    //   60: dup
    //   61: aload_1
    //   62: invokespecial 235	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   65: astore_1
    //   66: aload_1
    //   67: invokevirtual 238	org/json/JSONArray:length	()I
    //   70: ifle +47 -> 117
    //   73: new 42	java/util/ArrayList
    //   76: dup
    //   77: invokespecial 45	java/util/ArrayList:<init>	()V
    //   80: astore_2
    //   81: iconst_0
    //   82: istore_0
    //   83: iload_0
    //   84: aload_1
    //   85: invokevirtual 238	org/json/JSONArray:length	()I
    //   88: if_icmpge +22 -> 110
    //   91: aload_2
    //   92: aload_1
    //   93: iload_0
    //   94: invokevirtual 242	org/json/JSONArray:optString	(I)Ljava/lang/String;
    //   97: invokeinterface 174 2 0
    //   102: pop
    //   103: iload_0
    //   104: iconst_1
    //   105: iadd
    //   106: istore_0
    //   107: goto -24 -> 83
    //   110: invokestatic 213	com/mintegral/msdk/base/controller/a:d	()Lcom/mintegral/msdk/base/controller/a;
    //   113: aload_2
    //   114: putfield 58	com/mintegral/msdk/base/controller/a:i	Ljava/util/List;
    //   117: ldc 2
    //   119: monitorexit
    //   120: return
    //   121: astore_1
    //   122: ldc 2
    //   124: monitorexit
    //   125: aload_1
    //   126: athrow
    //   127: ldc 2
    //   129: monitorexit
    //   130: return
    //   131: astore_1
    //   132: goto -5 -> 127
    // Local variable table:
    //   start	length	slot	name	signature
    //   82	25	0	m	int
    //   28	65	1	localObject1	Object
    //   121	5	1	localObject2	Object
    //   131	1	1	localException	Exception
    //   80	34	2	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   3	29	121	finally
    //   33	81	121	finally
    //   83	103	121	finally
    //   110	117	121	finally
    //   3	29	131	java/lang/Exception
    //   33	81	131	java/lang/Exception
    //   83	103	131	java/lang/Exception
    //   110	117	131	java/lang/Exception
  }
  
  public static List<Long> g()
  {
    try
    {
      if ((j != null) && (j.size() > 0))
      {
        Iterator localIterator = j.iterator();
        ArrayList localArrayList = new ArrayList();
        while (localIterator.hasNext())
        {
          g localG = (g)localIterator.next();
          boolean bool = localArrayList.contains(localG.a());
          if (!bool) {
            try
            {
              localArrayList.add(Long.valueOf(Long.parseLong(localG.a())));
            }
            catch (NumberFormatException localNumberFormatException)
            {
              localNumberFormatException.printStackTrace();
            }
          }
        }
        return localArrayList;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
  
  public final String a()
  {
    try
    {
      if (this.d != null)
      {
        String str = this.d.getPackageName();
        return str;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public final void a(Context paramContext)
  {
    this.d = paramContext;
  }
  
  /* Error */
  public final void a(b paramB)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 61	com/mintegral/msdk/base/controller/a:d	Landroid/content/Context;
    //   6: invokestatic 276	com/mintegral/msdk/base/utils/o:a	(Landroid/content/Context;)Lcom/mintegral/msdk/base/utils/o;
    //   9: aload_0
    //   10: getfield 107	com/mintegral/msdk/base/controller/a:f	Ljava/lang/String;
    //   13: invokevirtual 279	com/mintegral/msdk/base/utils/o:a	(Ljava/lang/String;)Ljava/util/concurrent/CopyOnWriteArraySet;
    //   16: astore 4
    //   18: aload 4
    //   20: putstatic 52	com/mintegral/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   23: aload 4
    //   25: invokevirtual 247	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   28: ifne +16 -> 44
    //   31: aload_1
    //   32: ifnull +9 -> 41
    //   35: aload_1
    //   36: invokeinterface 281 1 0
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: new 49	java/util/concurrent/CopyOnWriteArraySet
    //   47: dup
    //   48: invokespecial 50	java/util/concurrent/CopyOnWriteArraySet:<init>	()V
    //   51: astore 4
    //   53: getstatic 52	com/mintegral/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   56: invokevirtual 248	java/util/concurrent/CopyOnWriteArraySet:iterator	()Ljava/util/Iterator;
    //   59: astore 5
    //   61: aload 5
    //   63: ifnull +144 -> 207
    //   66: aload 5
    //   68: invokeinterface 139 1 0
    //   73: istore_3
    //   74: iload_3
    //   75: ifeq +132 -> 207
    //   78: aload 5
    //   80: invokeinterface 143 1 0
    //   85: checkcast 250	com/mintegral/msdk/base/entity/g
    //   88: astore 6
    //   90: getstatic 47	com/mintegral/msdk/base/controller/a:b	Ljava/util/List;
    //   93: ifnull -27 -> 66
    //   96: getstatic 47	com/mintegral/msdk/base/controller/a:b	Ljava/util/List;
    //   99: invokeinterface 123 1 0
    //   104: ifle -38 -> 66
    //   107: aload 6
    //   109: ifnull -43 -> 66
    //   112: iconst_0
    //   113: istore_2
    //   114: iload_2
    //   115: getstatic 47	com/mintegral/msdk/base/controller/a:b	Ljava/util/List;
    //   118: invokeinterface 123 1 0
    //   123: if_icmpge -57 -> 66
    //   126: getstatic 47	com/mintegral/msdk/base/controller/a:b	Ljava/util/List;
    //   129: iload_2
    //   130: invokeinterface 285 2 0
    //   135: checkcast 145	java/lang/String
    //   138: astore 7
    //   140: aload 6
    //   142: invokevirtual 287	com/mintegral/msdk/base/entity/g:b	()Ljava/lang/String;
    //   145: astore 8
    //   147: aload 7
    //   149: invokestatic 151	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   152: ifne +29 -> 181
    //   155: aload 8
    //   157: invokestatic 151	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   160: ifne +21 -> 181
    //   163: aload 7
    //   165: aload 8
    //   167: invokevirtual 195	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   170: ifeq +11 -> 181
    //   173: aload 4
    //   175: aload 6
    //   177: invokevirtual 288	java/util/concurrent/CopyOnWriteArraySet:add	(Ljava/lang/Object;)Z
    //   180: pop
    //   181: iload_2
    //   182: iconst_1
    //   183: iadd
    //   184: istore_2
    //   185: goto -71 -> 114
    //   188: astore 6
    //   190: aload 6
    //   192: invokevirtual 104	java/lang/Exception:printStackTrace	()V
    //   195: getstatic 74	com/mintegral/msdk/base/controller/a:a	Ljava/lang/String;
    //   198: ldc_w 290
    //   201: invokestatic 292	com/mintegral/msdk/base/utils/i:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   204: goto -138 -> 66
    //   207: getstatic 52	com/mintegral/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   210: ifnull +9 -> 219
    //   213: getstatic 52	com/mintegral/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   216: invokevirtual 295	java/util/concurrent/CopyOnWriteArraySet:clear	()V
    //   219: aload 4
    //   221: invokevirtual 247	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   224: ifle +12 -> 236
    //   227: getstatic 52	com/mintegral/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   230: aload 4
    //   232: invokevirtual 299	java/util/concurrent/CopyOnWriteArraySet:addAll	(Ljava/util/Collection;)Z
    //   235: pop
    //   236: aload_0
    //   237: getfield 61	com/mintegral/msdk/base/controller/a:d	Landroid/content/Context;
    //   240: invokestatic 276	com/mintegral/msdk/base/utils/o:a	(Landroid/content/Context;)Lcom/mintegral/msdk/base/utils/o;
    //   243: getstatic 52	com/mintegral/msdk/base/controller/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   246: invokevirtual 302	com/mintegral/msdk/base/utils/o:a	(Ljava/util/Set;)V
    //   249: aload_1
    //   250: ifnull +9 -> 259
    //   253: aload_1
    //   254: invokeinterface 281 1 0
    //   259: aload_0
    //   260: monitorexit
    //   261: return
    //   262: astore_1
    //   263: aload_0
    //   264: monitorexit
    //   265: aload_1
    //   266: athrow
    //   267: aload_0
    //   268: monitorexit
    //   269: return
    //   270: astore_1
    //   271: goto -4 -> 267
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	274	0	this	a
    //   0	274	1	paramB	b
    //   113	72	2	m	int
    //   73	2	3	bool	boolean
    //   16	215	4	localCopyOnWriteArraySet	CopyOnWriteArraySet
    //   59	20	5	localIterator	Iterator
    //   88	88	6	localG	g
    //   188	3	6	localException	Exception
    //   138	26	7	str1	String
    //   145	21	8	str2	String
    // Exception table:
    //   from	to	target	type
    //   78	107	188	java/lang/Exception
    //   114	181	188	java/lang/Exception
    //   2	31	262	finally
    //   35	41	262	finally
    //   44	61	262	finally
    //   66	74	262	finally
    //   78	107	262	finally
    //   114	181	262	finally
    //   190	204	262	finally
    //   207	219	262	finally
    //   219	236	262	finally
    //   236	249	262	finally
    //   253	259	262	finally
    //   2	31	270	java/lang/Throwable
    //   35	41	270	java/lang/Throwable
    //   44	61	270	java/lang/Throwable
    //   66	74	270	java/lang/Throwable
    //   78	107	270	java/lang/Throwable
    //   114	181	270	java/lang/Throwable
    //   190	204	270	java/lang/Throwable
    //   207	219	270	java/lang/Throwable
    //   219	236	270	java/lang/Throwable
    //   236	249	270	java/lang/Throwable
    //   253	259	270	java/lang/Throwable
  }
  
  public final void a(final b paramB, final Handler paramHandler)
  {
    if (this.h == true) {
      return;
    }
    try
    {
      Object localObject = t.c(this.d, "ga_id", "-1");
      if ((localObject != null) && ((localObject instanceof String)))
      {
        localObject = (String)localObject;
        if ((u.b((String)localObject)) && (!"-1".equals(localObject)))
        {
          String str = a;
          StringBuilder localStringBuilder = new StringBuilder("sp init gaid:");
          localStringBuilder.append((String)localObject);
          i.b(str, localStringBuilder.toString());
          com.mintegral.msdk.base.controller.authoritycontroller.a.a();
          if (com.mintegral.msdk.base.controller.authoritycontroller.a.a("authority_device_id")) {
            d.a((String)localObject);
          }
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    new Thread(new Runnable()
    {
      public final void run()
      {
        com.mintegral.msdk.base.controller.authoritycontroller.a.a();
        if (com.mintegral.msdk.base.controller.authoritycontroller.a.a("authority_device_id")) {}
        try
        {
          try
          {
            Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(a.a(a.this));
            d.a(localInfo.getId());
            a.a(a.this, localInfo.getId());
          }
          catch (Throwable localThrowable)
          {
            localThrowable.printStackTrace();
          }
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            try
            {
              d.a(a.a(a.this));
              a.e();
              m.c(a.a(a.this));
              b.a(a.a(a.this), a.b(a.this));
              a.this.m();
              a.c(a.this);
              a.this.a(paramB);
              i = a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", a.a(a.this).getPackageName());
              bool2 = false;
              if (i != 0) {
                break;
              }
              bool1 = true;
              h.a = bool1;
              bool1 = bool2;
              if (a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", a.a(a.this).getPackageName()) == 0) {
                bool1 = true;
              }
              h.b = bool1;
              h.a().a(a.a(a.this));
              a.a(a.this, h.a().b());
              return;
            }
            catch (Exception localException4)
            {
              return;
            }
            localException2 = localException2;
          }
        }
        i.c(a.a, "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
        try
        {
          localObject1 = new a.a(a.this).a(a.a(a.this));
          d.a(((a.a.a)localObject1).a());
          a.a(a.this, ((a.a.a)localObject1).a());
        }
        catch (Exception localException3)
        {
          Object localObject1;
          for (;;) {}
        }
        i.c(a.a, "GET ADID FROM GOOGLE PLAY APP ERROR");
        try
        {
          b.a();
          Object localObject2 = b.b(a.d().k());
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            b.a();
            localObject1 = b.b();
          }
          localObject2 = Message.obtain();
          ((Message)localObject2).obj = localObject1;
          ((Message)localObject2).what = 9;
          paramHandler.sendMessage((Message)localObject2);
        }
        catch (Exception localException1)
        {
          localException1.printStackTrace();
        }
        for (;;)
        {
          int i;
          boolean bool2;
          boolean bool1 = false;
        }
      }
    }).start();
  }
  
  public final void a(String paramString)
  {
    try
    {
      this.k = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        t.b(this.d, "applicationIds", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public final List<String> b()
  {
    return this.i;
  }
  
  public final void b(String paramString)
  {
    this.e = paramString;
  }
  
  public final void c(String paramString)
  {
    try
    {
      this.f = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        t.b(this.d, "sp_appId", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public final void d(String paramString)
  {
    try
    {
      this.g = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        t.b(this.d, "sp_appKey", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public final void f()
  {
    try
    {
      if ((j != null) && (j.size() > 0)) {
        o.a(this.d).a(j);
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public final Location h()
  {
    return this.l;
  }
  
  public final Context i()
  {
    return this.d;
  }
  
  public final String j()
  {
    return this.e;
  }
  
  public final String k()
  {
    try
    {
      if (!TextUtils.isEmpty(this.f)) {
        return this.f;
      }
      if (this.d != null)
      {
        String str = (String)t.c(this.d, "sp_appId", "");
        return str;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public final String l()
  {
    if (!TextUtils.isEmpty(this.g)) {
      return this.g;
    }
    if (this.d != null) {
      return (String)t.c(this.d, "sp_appKey", "");
    }
    return null;
  }
  
  public final List<String> m()
  {
    try
    {
      localList = d().i;
      if (b != null) {
        return b;
      }
      localObject = this.d.getPackageManager();
      m = 0;
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        List localList;
        Object localObject;
        int m;
        continue;
        m += 1;
      }
    }
    if (m < ((List)localObject).size())
    {
      if ((((PackageInfo)((List)localObject).get(m)).applicationInfo.flags & 0x1) <= 0) {
        b.add(((PackageInfo)((List)localObject).get(m)).packageName);
      } else if ((localList != null) && (localList.size() > 0) && (localList.contains(((PackageInfo)((List)localObject).get(m)).packageName))) {
        b.add(((PackageInfo)((List)localObject).get(m)).packageName);
      }
    }
    else
    {
      localList = b;
      return localList;
      i.d(a, "get package info list error");
      return null;
    }
  }
  
  public final class a
  {
    public a() {}
    
    /* Error */
    public final a a(Context paramContext)
      throws Exception
    {
      // Byte code:
      //   0: invokestatic 33	android/os/Looper:myLooper	()Landroid/os/Looper;
      //   3: invokestatic 36	android/os/Looper:getMainLooper	()Landroid/os/Looper;
      //   6: if_acmpeq +115 -> 121
      //   9: aload_1
      //   10: invokevirtual 42	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   13: ldc 44
      //   15: iconst_0
      //   16: invokevirtual 50	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   19: pop
      //   20: new 11	com/mintegral/msdk/base/controller/a$a$b
      //   23: dup
      //   24: aload_0
      //   25: iconst_0
      //   26: invokespecial 53	com/mintegral/msdk/base/controller/a$a$b:<init>	(Lcom/mintegral/msdk/base/controller/a$a;B)V
      //   29: astore_2
      //   30: new 55	android/content/Intent
      //   33: dup
      //   34: ldc 57
      //   36: invokespecial 60	android/content/Intent:<init>	(Ljava/lang/String;)V
      //   39: astore_3
      //   40: aload_3
      //   41: ldc 62
      //   43: invokevirtual 66	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
      //   46: pop
      //   47: aload_1
      //   48: aload_3
      //   49: aload_2
      //   50: iconst_1
      //   51: invokevirtual 70	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
      //   54: ifeq +54 -> 108
      //   57: new 14	com/mintegral/msdk/base/controller/a$a$c
      //   60: dup
      //   61: aload_0
      //   62: aload_2
      //   63: invokevirtual 73	com/mintegral/msdk/base/controller/a$a$b:a	()Landroid/os/IBinder;
      //   66: invokespecial 76	com/mintegral/msdk/base/controller/a$a$c:<init>	(Lcom/mintegral/msdk/base/controller/a$a;Landroid/os/IBinder;)V
      //   69: astore_3
      //   70: new 9	com/mintegral/msdk/base/controller/a$a$a
      //   73: dup
      //   74: aload_0
      //   75: aload_3
      //   76: invokevirtual 79	com/mintegral/msdk/base/controller/a$a$c:a	()Ljava/lang/String;
      //   79: aload_3
      //   80: invokevirtual 82	com/mintegral/msdk/base/controller/a$a$c:b	()Z
      //   83: invokespecial 85	com/mintegral/msdk/base/controller/a$a$a:<init>	(Lcom/mintegral/msdk/base/controller/a$a;Ljava/lang/String;Z)V
      //   86: astore_3
      //   87: aload_1
      //   88: aload_2
      //   89: invokevirtual 89	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
      //   92: aload_3
      //   93: areturn
      //   94: astore_3
      //   95: goto +6 -> 101
      //   98: astore_3
      //   99: aload_3
      //   100: athrow
      //   101: aload_1
      //   102: aload_2
      //   103: invokevirtual 89	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
      //   106: aload_3
      //   107: athrow
      //   108: new 91	java/io/IOException
      //   111: dup
      //   112: ldc 93
      //   114: invokespecial 94	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   117: athrow
      //   118: astore_1
      //   119: aload_1
      //   120: athrow
      //   121: new 96	java/lang/IllegalStateException
      //   124: dup
      //   125: ldc 98
      //   127: invokespecial 99	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
      //   130: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	131	0	this	a
      //   0	131	1	paramContext	Context
      //   29	74	2	localB	b
      //   39	54	3	localObject1	Object
      //   94	1	3	localObject2	Object
      //   98	9	3	localException	Exception
      // Exception table:
      //   from	to	target	type
      //   57	87	94	finally
      //   99	101	94	finally
      //   57	87	98	java/lang/Exception
      //   9	20	118	java/lang/Exception
    }
    
    public final class a
    {
      private final String b;
      private final boolean c;
      
      a(String paramString, boolean paramBoolean)
      {
        this.b = paramString;
        this.c = paramBoolean;
      }
      
      public final String a()
      {
        return this.b;
      }
    }
    
    private final class b
      implements ServiceConnection
    {
      boolean a = false;
      private final LinkedBlockingQueue<IBinder> c = new LinkedBlockingQueue(1);
      
      private b() {}
      
      public final IBinder a()
        throws InterruptedException
      {
        if (!this.a)
        {
          this.a = true;
          return (IBinder)this.c.take();
        }
        throw new IllegalStateException();
      }
      
      public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
      {
        try
        {
          this.c.put(paramIBinder);
          return;
        }
        catch (InterruptedException paramComponentName) {}
      }
      
      public final void onServiceDisconnected(ComponentName paramComponentName) {}
    }
    
    private final class c
      implements IInterface
    {
      private IBinder b;
      
      public c(IBinder paramIBinder)
      {
        this.b = paramIBinder;
      }
      
      /* Error */
      public final String a()
        throws android.os.RemoteException
      {
        // Byte code:
        //   0: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_1
        //   4: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_2
        //   8: aload_1
        //   9: ldc 38
        //   11: invokevirtual 42	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 24	com/mintegral/msdk/base/controller/a$a$c:b	Landroid/os/IBinder;
        //   18: iconst_1
        //   19: aload_1
        //   20: aload_2
        //   21: iconst_0
        //   22: invokeinterface 48 5 0
        //   27: pop
        //   28: aload_2
        //   29: invokevirtual 51	android/os/Parcel:readException	()V
        //   32: aload_2
        //   33: invokevirtual 54	android/os/Parcel:readString	()Ljava/lang/String;
        //   36: astore_3
        //   37: aload_2
        //   38: invokevirtual 57	android/os/Parcel:recycle	()V
        //   41: aload_1
        //   42: invokevirtual 57	android/os/Parcel:recycle	()V
        //   45: aload_3
        //   46: areturn
        //   47: astore_3
        //   48: goto +18 -> 66
        //   51: astore_3
        //   52: aload_3
        //   53: invokevirtual 60	java/lang/Throwable:printStackTrace	()V
        //   56: aload_2
        //   57: invokevirtual 57	android/os/Parcel:recycle	()V
        //   60: aload_1
        //   61: invokevirtual 57	android/os/Parcel:recycle	()V
        //   64: aconst_null
        //   65: areturn
        //   66: aload_2
        //   67: invokevirtual 57	android/os/Parcel:recycle	()V
        //   70: aload_1
        //   71: invokevirtual 57	android/os/Parcel:recycle	()V
        //   74: aload_3
        //   75: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	76	0	this	c
        //   3	68	1	localParcel1	android.os.Parcel
        //   7	60	2	localParcel2	android.os.Parcel
        //   36	10	3	str	String
        //   47	1	3	localObject	Object
        //   51	24	3	localThrowable	Throwable
        // Exception table:
        //   from	to	target	type
        //   8	37	47	finally
        //   52	56	47	finally
        //   8	37	51	java/lang/Throwable
      }
      
      public final IBinder asBinder()
      {
        return this.b;
      }
      
      /* Error */
      public final boolean b()
        throws android.os.RemoteException
      {
        // Byte code:
        //   0: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: iconst_0
        //   10: istore_2
        //   11: aload_3
        //   12: ldc 38
        //   14: invokevirtual 42	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_3
        //   18: iconst_1
        //   19: invokevirtual 68	android/os/Parcel:writeInt	(I)V
        //   22: aload_0
        //   23: getfield 24	com/mintegral/msdk/base/controller/a$a$c:b	Landroid/os/IBinder;
        //   26: iconst_2
        //   27: aload_3
        //   28: aload 4
        //   30: iconst_0
        //   31: invokeinterface 48 5 0
        //   36: pop
        //   37: aload 4
        //   39: invokevirtual 51	android/os/Parcel:readException	()V
        //   42: aload 4
        //   44: invokevirtual 72	android/os/Parcel:readInt	()I
        //   47: istore_1
        //   48: iload_1
        //   49: ifeq +20 -> 69
        //   52: iconst_1
        //   53: istore_2
        //   54: goto +15 -> 69
        //   57: astore 5
        //   59: goto +21 -> 80
        //   62: astore 5
        //   64: aload 5
        //   66: invokevirtual 60	java/lang/Throwable:printStackTrace	()V
        //   69: aload 4
        //   71: invokevirtual 57	android/os/Parcel:recycle	()V
        //   74: aload_3
        //   75: invokevirtual 57	android/os/Parcel:recycle	()V
        //   78: iload_2
        //   79: ireturn
        //   80: aload 4
        //   82: invokevirtual 57	android/os/Parcel:recycle	()V
        //   85: aload_3
        //   86: invokevirtual 57	android/os/Parcel:recycle	()V
        //   89: aload 5
        //   91: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	92	0	this	c
        //   47	2	1	i	int
        //   10	69	2	bool	boolean
        //   3	83	3	localParcel1	android.os.Parcel
        //   7	74	4	localParcel2	android.os.Parcel
        //   57	1	5	localObject	Object
        //   62	28	5	localThrowable	Throwable
        // Exception table:
        //   from	to	target	type
        //   11	48	57	finally
        //   64	69	57	finally
        //   11	48	62	java/lang/Throwable
      }
    }
  }
  
  public static abstract interface b
  {
    public abstract void a();
  }
}
