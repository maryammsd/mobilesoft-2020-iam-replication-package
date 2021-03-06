package com.mobvista.msdk.base.b;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.mobvista.msdk.b.b;
import com.mobvista.msdk.base.entity.e;
import com.mobvista.msdk.base.utils.c;
import com.mobvista.msdk.base.utils.g;
import com.mobvista.msdk.base.utils.h;
import com.mobvista.msdk.base.utils.l;
import com.mobvista.msdk.base.utils.m;
import com.mobvista.msdk.base.utils.q;
import com.mobvista.msdk.base.utils.r;
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
  private static CopyOnWriteArraySet<e> j = new CopyOnWriteArraySet();
  private Context d;
  private String e;
  private String f;
  private String g;
  private boolean h = false;
  private List<String> i = null;
  private String k;
  private Location l;
  
  private a() {}
  
  public static Set<e> c()
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
    //   3: invokestatic 213	com/mobvista/msdk/base/b/a:d	()Lcom/mobvista/msdk/base/b/a;
    //   6: invokevirtual 215	com/mobvista/msdk/base/b/a:k	()Ljava/lang/String;
    //   9: invokestatic 151	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   12: ifne +105 -> 117
    //   15: invokestatic 220	com/mobvista/msdk/b/b:a	()Lcom/mobvista/msdk/b/b;
    //   18: pop
    //   19: invokestatic 213	com/mobvista/msdk/base/b/a:d	()Lcom/mobvista/msdk/base/b/a;
    //   22: invokevirtual 215	com/mobvista/msdk/base/b/a:k	()Ljava/lang/String;
    //   25: invokestatic 223	com/mobvista/msdk/b/b:b	(Ljava/lang/String;)Lcom/mobvista/msdk/b/a;
    //   28: astore_1
    //   29: aload_1
    //   30: ifnull +87 -> 117
    //   33: aload_1
    //   34: invokevirtual 228	com/mobvista/msdk/b/a:ap	()Ljava/lang/String;
    //   37: astore_1
    //   38: aload_1
    //   39: invokestatic 151	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   42: ifne +75 -> 117
    //   45: aload_1
    //   46: invokestatic 232	com/mobvista/msdk/base/utils/a:c	(Ljava/lang/String;)Ljava/lang/String;
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
    //   110: invokestatic 213	com/mobvista/msdk/base/b/a:d	()Lcom/mobvista/msdk/base/b/a;
    //   113: aload_2
    //   114: putfield 58	com/mobvista/msdk/base/b/a:i	Ljava/util/List;
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
          e localE = (e)localIterator.next();
          boolean bool = localArrayList.contains(localE.a());
          if (!bool) {
            try
            {
              localArrayList.add(Long.valueOf(Long.parseLong(localE.a())));
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
  
  public final void a(final b paramB)
  {
    if (this.h == true) {
      return;
    }
    try
    {
      Object localObject = q.b(this.d, "ga_id", "-1");
      if ((localObject != null) && ((localObject instanceof String)))
      {
        localObject = (String)localObject;
        if ((r.b((String)localObject)) && (!"-1".equals(localObject)))
        {
          String str = a;
          StringBuilder localStringBuilder = new StringBuilder("sp init gaid:");
          localStringBuilder.append((String)localObject);
          h.b(str, localStringBuilder.toString());
          c.a((String)localObject);
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
        try
        {
          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
          localObject = AdvertisingIdClient.getAdvertisingIdInfo(a.a(a.this));
          c.a(((AdvertisingIdClient.Info)localObject).getId());
          a.a(a.this, ((AdvertisingIdClient.Info)localObject).getId());
        }
        catch (Exception localException1)
        {
          for (;;)
          {
            try
            {
              Object localObject;
              c.a(a.a(a.this));
              a.e();
              l.c(a.a(a.this));
              b.a(a.a(a.this), a.b(a.this));
              a.this.m();
              a.c(a.this);
              a.this.b(paramB);
              a.a(a.this, g.a().b());
              i = a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", a.a(a.this).getPackageName());
              bool2 = false;
              if (i != 0) {
                break;
              }
              bool1 = true;
              g.a = bool1;
              bool1 = bool2;
              if (a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", a.a(a.this).getPackageName()) == 0) {
                bool1 = true;
              }
              g.b = bool1;
              return;
            }
            catch (Exception localException3)
            {
              return;
            }
            localException1 = localException1;
          }
        }
        h.c(a.a, "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
        try
        {
          localObject = new a.a(a.this).a(a.a(a.this));
          c.a(((a.a.a)localObject).a());
          a.a(a.this, ((a.a.a)localObject).a());
        }
        catch (Exception localException2)
        {
          for (;;) {}
        }
        h.c(a.a, "GET ADID FROM GOOGLE PLAY APP ERROR");
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
        q.a(this.d, "applicationIds", paramString);
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
  
  /* Error */
  public final void b(b paramB)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 61	com/mobvista/msdk/base/b/a:d	Landroid/content/Context;
    //   6: invokestatic 302	com/mobvista/msdk/base/utils/m:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/m;
    //   9: aload_0
    //   10: getfield 107	com/mobvista/msdk/base/b/a:f	Ljava/lang/String;
    //   13: invokevirtual 305	com/mobvista/msdk/base/utils/m:a	(Ljava/lang/String;)Ljava/util/concurrent/CopyOnWriteArraySet;
    //   16: astore 4
    //   18: aload 4
    //   20: putstatic 52	com/mobvista/msdk/base/b/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   23: aload 4
    //   25: invokevirtual 247	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   28: ifne +16 -> 44
    //   31: aload_1
    //   32: ifnull +9 -> 41
    //   35: aload_1
    //   36: invokeinterface 307 1 0
    //   41: aload_0
    //   42: monitorexit
    //   43: return
    //   44: new 49	java/util/concurrent/CopyOnWriteArraySet
    //   47: dup
    //   48: invokespecial 50	java/util/concurrent/CopyOnWriteArraySet:<init>	()V
    //   51: astore 4
    //   53: getstatic 52	com/mobvista/msdk/base/b/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
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
    //   85: checkcast 250	com/mobvista/msdk/base/entity/e
    //   88: astore 6
    //   90: getstatic 47	com/mobvista/msdk/base/b/a:b	Ljava/util/List;
    //   93: ifnull -27 -> 66
    //   96: getstatic 47	com/mobvista/msdk/base/b/a:b	Ljava/util/List;
    //   99: invokeinterface 123 1 0
    //   104: ifle -38 -> 66
    //   107: aload 6
    //   109: ifnull -43 -> 66
    //   112: iconst_0
    //   113: istore_2
    //   114: iload_2
    //   115: getstatic 47	com/mobvista/msdk/base/b/a:b	Ljava/util/List;
    //   118: invokeinterface 123 1 0
    //   123: if_icmpge -57 -> 66
    //   126: getstatic 47	com/mobvista/msdk/base/b/a:b	Ljava/util/List;
    //   129: iload_2
    //   130: invokeinterface 311 2 0
    //   135: checkcast 145	java/lang/String
    //   138: astore 7
    //   140: aload 6
    //   142: invokevirtual 313	com/mobvista/msdk/base/entity/e:b	()Ljava/lang/String;
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
    //   177: invokevirtual 314	java/util/concurrent/CopyOnWriteArraySet:add	(Ljava/lang/Object;)Z
    //   180: pop
    //   181: iload_2
    //   182: iconst_1
    //   183: iadd
    //   184: istore_2
    //   185: goto -71 -> 114
    //   188: astore 6
    //   190: aload 6
    //   192: invokevirtual 104	java/lang/Exception:printStackTrace	()V
    //   195: getstatic 74	com/mobvista/msdk/base/b/a:a	Ljava/lang/String;
    //   198: ldc_w 316
    //   201: invokestatic 318	com/mobvista/msdk/base/utils/h:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   204: goto -138 -> 66
    //   207: getstatic 52	com/mobvista/msdk/base/b/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   210: ifnull +9 -> 219
    //   213: getstatic 52	com/mobvista/msdk/base/b/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   216: invokevirtual 321	java/util/concurrent/CopyOnWriteArraySet:clear	()V
    //   219: aload 4
    //   221: invokevirtual 247	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   224: ifle +12 -> 236
    //   227: getstatic 52	com/mobvista/msdk/base/b/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   230: aload 4
    //   232: invokevirtual 325	java/util/concurrent/CopyOnWriteArraySet:addAll	(Ljava/util/Collection;)Z
    //   235: pop
    //   236: aload_0
    //   237: getfield 61	com/mobvista/msdk/base/b/a:d	Landroid/content/Context;
    //   240: invokestatic 302	com/mobvista/msdk/base/utils/m:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/m;
    //   243: getstatic 52	com/mobvista/msdk/base/b/a:j	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   246: invokevirtual 328	com/mobvista/msdk/base/utils/m:a	(Ljava/util/Set;)V
    //   249: aload_1
    //   250: ifnull +9 -> 259
    //   253: aload_1
    //   254: invokeinterface 307 1 0
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
    //   88	88	6	localE	e
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
        q.a(this.d, "sp_appId", paramString);
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
        q.a(this.d, "sp_appKey", paramString);
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
        m.a(this.d).a(j);
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
        String str = (String)q.b(this.d, "sp_appId", "");
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
      return (String)q.b(this.d, "sp_appKey", "");
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
      h.d(a, "get package info list error");
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
      //   6: if_acmpne +13 -> 19
      //   9: new 38	java/lang/IllegalStateException
      //   12: dup
      //   13: ldc 40
      //   15: invokespecial 43	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
      //   18: athrow
      //   19: aload_1
      //   20: invokevirtual 49	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   23: ldc 51
      //   25: iconst_0
      //   26: invokevirtual 57	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   29: pop
      //   30: new 11	com/mobvista/msdk/base/b/a$a$b
      //   33: dup
      //   34: aload_0
      //   35: iconst_0
      //   36: invokespecial 60	com/mobvista/msdk/base/b/a$a$b:<init>	(Lcom/mobvista/msdk/base/b/a$a;B)V
      //   39: astore_2
      //   40: new 62	android/content/Intent
      //   43: dup
      //   44: ldc 64
      //   46: invokespecial 65	android/content/Intent:<init>	(Ljava/lang/String;)V
      //   49: astore_3
      //   50: aload_3
      //   51: ldc 67
      //   53: invokevirtual 71	android/content/Intent:setPackage	(Ljava/lang/String;)Landroid/content/Intent;
      //   56: pop
      //   57: aload_1
      //   58: aload_3
      //   59: aload_2
      //   60: iconst_1
      //   61: invokevirtual 75	android/content/Context:bindService	(Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
      //   64: ifeq +54 -> 118
      //   67: new 14	com/mobvista/msdk/base/b/a$a$c
      //   70: dup
      //   71: aload_0
      //   72: aload_2
      //   73: invokevirtual 78	com/mobvista/msdk/base/b/a$a$b:a	()Landroid/os/IBinder;
      //   76: invokespecial 81	com/mobvista/msdk/base/b/a$a$c:<init>	(Lcom/mobvista/msdk/base/b/a$a;Landroid/os/IBinder;)V
      //   79: astore_3
      //   80: new 9	com/mobvista/msdk/base/b/a$a$a
      //   83: dup
      //   84: aload_0
      //   85: aload_3
      //   86: invokevirtual 84	com/mobvista/msdk/base/b/a$a$c:a	()Ljava/lang/String;
      //   89: aload_3
      //   90: invokevirtual 87	com/mobvista/msdk/base/b/a$a$c:b	()Z
      //   93: invokespecial 90	com/mobvista/msdk/base/b/a$a$a:<init>	(Lcom/mobvista/msdk/base/b/a$a;Ljava/lang/String;Z)V
      //   96: astore_3
      //   97: aload_1
      //   98: aload_2
      //   99: invokevirtual 94	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
      //   102: aload_3
      //   103: areturn
      //   104: astore_3
      //   105: goto +6 -> 111
      //   108: astore_3
      //   109: aload_3
      //   110: athrow
      //   111: aload_1
      //   112: aload_2
      //   113: invokevirtual 94	android/content/Context:unbindService	(Landroid/content/ServiceConnection;)V
      //   116: aload_3
      //   117: athrow
      //   118: new 96	java/io/IOException
      //   121: dup
      //   122: ldc 98
      //   124: invokespecial 99	java/io/IOException:<init>	(Ljava/lang/String;)V
      //   127: athrow
      //   128: astore_1
      //   129: aload_1
      //   130: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	131	0	this	a
      //   0	131	1	paramContext	Context
      //   39	74	2	localB	b
      //   49	54	3	localObject1	Object
      //   104	1	3	localObject2	Object
      //   108	9	3	localException	Exception
      // Exception table:
      //   from	to	target	type
      //   67	97	104	finally
      //   109	111	104	finally
      //   67	97	108	java/lang/Exception
      //   19	30	128	java/lang/Exception
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
        if (this.a) {
          throw new IllegalStateException();
        }
        this.a = true;
        return (IBinder)this.c.take();
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
      
      public final String a()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          this.b.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public final IBinder asBinder()
      {
        return this.b;
      }
      
      public final boolean b()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
          boolean bool = true;
          localParcel1.writeInt(1);
          this.b.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i == 0) {
            bool = false;
          }
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
  
  public static abstract interface b
  {
    public abstract void a();
  }
}
