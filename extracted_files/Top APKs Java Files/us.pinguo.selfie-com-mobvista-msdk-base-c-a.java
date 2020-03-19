package com.mobvista.msdk.base.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.mobvista.msdk.base.entity.c;
import com.mobvista.msdk.base.utils.d;
import com.mobvista.msdk.base.utils.e;
import com.mobvista.msdk.base.utils.h;
import com.mobvista.msdk.base.utils.i;
import com.mobvista.msdk.base.utils.m;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;

public class a
{
  public static final String a = a.class.getSimpleName();
  public static List<String> b = new ArrayList();
  private static a c;
  private static CopyOnWriteArraySet<c> i = new CopyOnWriteArraySet();
  private Context d;
  private String e;
  private String f;
  private String g;
  private boolean h = false;
  private String j;
  private Location k;
  
  private a() {}
  
  public static Set<c> b()
  {
    return i;
  }
  
  public static a c()
  {
    if (c == null) {}
    try
    {
      if (c == null) {
        c = new a();
      }
      return c;
    }
    finally {}
  }
  
  public static List<Long> g()
  {
    ArrayList localArrayList;
    try
    {
      Iterator localIterator;
      if ((i != null) && (i.size() > 0))
      {
        localIterator = i.iterator();
        localArrayList = new ArrayList();
      }
      while (localIterator.hasNext())
      {
        c localC = (c)localIterator.next();
        boolean bool = localArrayList.contains(localC.a());
        if (!bool)
        {
          try
          {
            localArrayList.add(Long.valueOf(Long.parseLong(localC.a())));
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localNumberFormatException.printStackTrace();
          }
          continue;
          return null;
        }
      }
    }
    catch (Throwable localThrowable) {}
    return localArrayList;
  }
  
  public final String a()
  {
    try
    {
      if (!TextUtils.isEmpty(this.j)) {
        return this.j;
      }
      String str1;
      if (this.d != null)
      {
        String str3 = (String)m.b(this.d, "applicationIds", "");
        str1 = str3;
        if (!TextUtils.isEmpty(str3)) {}
      }
      else if (this.d != null)
      {
        str1 = this.d.getPackageName();
        return str1;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      String str2 = null;
      return str2;
    }
  }
  
  public final void a(Context paramContext)
  {
    this.d = paramContext;
  }
  
  public final void a(String paramString)
  {
    try
    {
      this.j = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        m.a(this.d, "applicationIds", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
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
        m.a(this.d, "sp_appId", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public final void d()
  {
    if (this.h == true) {
      return;
    }
    new Thread(new Runnable()
    {
      public final void run()
      {
        boolean bool2 = true;
        try
        {
          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient.Info");
          com.mobvista.msdk.base.utils.b.a(AdvertisingIdClient.getAdvertisingIdInfo(a.a(a.this)).getId());
        }
        catch (Exception localException1)
        {
          try
          {
            com.mobvista.msdk.base.utils.b.a(a.a(a.this));
            h.c(a.a(a.this));
            com.mobvista.msdk.b.b.a(a.a(a.this), a.b(a.this));
            a.this.m();
            a.this.e();
            a.a(a.this, d.a().b());
            if (a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", a.a(a.this).getPackageName()) == 0)
            {
              bool1 = true;
              label126:
              d.a = bool1;
              if (a.a(a.this).getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", a.a(a.this).getPackageName()) != 0) {
                break label221;
              }
            }
            label221:
            for (boolean bool1 = bool2;; bool1 = false)
            {
              d.b = bool1;
              return;
              localException1 = localException1;
              e.c(a.a, "GET ADID ERROR TRY TO GET FROM GOOGLE PLAY APP");
              try
              {
                com.mobvista.msdk.base.utils.b.a(new a.a(a.this).a(a.a(a.this)).a());
              }
              catch (Exception localException2)
              {
                e.c(a.a, "GET ADID FROM GOOGLE PLAY APP ERROR");
              }
              break;
              bool1 = false;
              break label126;
            }
            return;
          }
          catch (Exception localException3) {}
        }
      }
    }).start();
    this.h = true;
  }
  
  public final void d(String paramString)
  {
    try
    {
      this.g = paramString;
      if ((!TextUtils.isEmpty(paramString)) && (this.d != null)) {
        m.a(this.d, "sp_appKey", paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  /* Error */
  public final void e()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 63	com/mobvista/msdk/base/c/a:d	Landroid/content/Context;
    //   6: invokestatic 182	com/mobvista/msdk/base/utils/i:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/i;
    //   9: aload_0
    //   10: getfield 69	com/mobvista/msdk/base/c/a:f	Ljava/lang/String;
    //   13: invokevirtual 185	com/mobvista/msdk/base/utils/i:a	(Ljava/lang/String;)Ljava/util/concurrent/CopyOnWriteArraySet;
    //   16: astore 6
    //   18: aload 6
    //   20: putstatic 56	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   23: aload 6
    //   25: ifnull +14 -> 39
    //   28: getstatic 56	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   31: invokevirtual 85	java/util/concurrent/CopyOnWriteArraySet:size	()I
    //   34: istore_1
    //   35: iload_1
    //   36: ifne +6 -> 42
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: getstatic 56	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   45: invokevirtual 89	java/util/concurrent/CopyOnWriteArraySet:iterator	()Ljava/util/Iterator;
    //   48: astore 6
    //   50: iconst_0
    //   51: istore_2
    //   52: aload 6
    //   54: invokeinterface 95 1 0
    //   59: istore 5
    //   61: iload 5
    //   63: ifeq +108 -> 171
    //   66: iload_2
    //   67: istore 4
    //   69: aload 6
    //   71: invokeinterface 99 1 0
    //   76: checkcast 101	com/mobvista/msdk/base/entity/c
    //   79: astore 7
    //   81: iconst_0
    //   82: istore_3
    //   83: iload_2
    //   84: istore_1
    //   85: iload_1
    //   86: istore_2
    //   87: iload_1
    //   88: istore 4
    //   90: iload_3
    //   91: getstatic 51	com/mobvista/msdk/base/c/a:b	Ljava/util/List;
    //   94: invokeinterface 186 1 0
    //   99: if_icmpge -47 -> 52
    //   102: iload_1
    //   103: istore 4
    //   105: getstatic 51	com/mobvista/msdk/base/c/a:b	Ljava/util/List;
    //   108: iload_3
    //   109: invokeinterface 190 2 0
    //   114: checkcast 147	java/lang/String
    //   117: aload 7
    //   119: invokevirtual 192	com/mobvista/msdk/base/entity/c:b	()Ljava/lang/String;
    //   122: invokevirtual 195	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   125: ifeq +73 -> 198
    //   128: iload_1
    //   129: istore 4
    //   131: getstatic 56	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   134: aload 7
    //   136: invokevirtual 198	java/util/concurrent/CopyOnWriteArraySet:remove	(Ljava/lang/Object;)Z
    //   139: pop
    //   140: iconst_1
    //   141: istore_1
    //   142: iload_3
    //   143: iconst_1
    //   144: iadd
    //   145: istore_3
    //   146: goto -61 -> 85
    //   149: astore 7
    //   151: aload 7
    //   153: invokevirtual 153	java/lang/Exception:printStackTrace	()V
    //   156: getstatic 44	com/mobvista/msdk/base/c/a:a	Ljava/lang/String;
    //   159: ldc -56
    //   161: invokestatic 205	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   164: pop
    //   165: iload 4
    //   167: istore_2
    //   168: goto -116 -> 52
    //   171: iload_2
    //   172: ifeq -133 -> 39
    //   175: aload_0
    //   176: getfield 63	com/mobvista/msdk/base/c/a:d	Landroid/content/Context;
    //   179: invokestatic 182	com/mobvista/msdk/base/utils/i:a	(Landroid/content/Context;)Lcom/mobvista/msdk/base/utils/i;
    //   182: getstatic 56	com/mobvista/msdk/base/c/a:i	Ljava/util/concurrent/CopyOnWriteArraySet;
    //   185: invokevirtual 208	com/mobvista/msdk/base/utils/i:a	(Ljava/util/Set;)V
    //   188: goto -149 -> 39
    //   191: astore 6
    //   193: aload_0
    //   194: monitorexit
    //   195: aload 6
    //   197: athrow
    //   198: goto -56 -> 142
    //   201: astore 6
    //   203: goto -164 -> 39
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	206	0	this	a
    //   34	108	1	m	int
    //   51	121	2	n	int
    //   82	64	3	i1	int
    //   67	99	4	i2	int
    //   59	3	5	bool	boolean
    //   16	54	6	localObject1	Object
    //   191	5	6	localObject2	Object
    //   201	1	6	localThrowable	Throwable
    //   79	56	7	localC	c
    //   149	3	7	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   69	81	149	java/lang/Exception
    //   90	102	149	java/lang/Exception
    //   105	128	149	java/lang/Exception
    //   131	140	149	java/lang/Exception
    //   2	23	191	finally
    //   28	35	191	finally
    //   42	50	191	finally
    //   52	61	191	finally
    //   69	81	191	finally
    //   90	102	191	finally
    //   105	128	191	finally
    //   131	140	191	finally
    //   151	165	191	finally
    //   175	188	191	finally
    //   2	23	201	java/lang/Throwable
    //   28	35	201	java/lang/Throwable
    //   42	50	201	java/lang/Throwable
    //   52	61	201	java/lang/Throwable
    //   69	81	201	java/lang/Throwable
    //   90	102	201	java/lang/Throwable
    //   105	128	201	java/lang/Throwable
    //   131	140	201	java/lang/Throwable
    //   151	165	201	java/lang/Throwable
    //   175	188	201	java/lang/Throwable
  }
  
  public final void f()
  {
    try
    {
      if ((i != null) && (i.size() > 0)) {
        i.a(this.d).a(i);
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public final Location h()
  {
    return this.k;
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
        String str = (String)m.b(this.d, "sp_appId", "");
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
      return (String)m.b(this.d, "sp_appKey", "");
    }
    return null;
  }
  
  public final List<String> m()
  {
    try
    {
      if (b != null) {
        return b;
      }
      List localList = this.d.getPackageManager().getInstalledPackages(0);
      int m = 0;
      while (m < localList.size())
      {
        b.add(((PackageInfo)localList.get(m)).packageName);
        m += 1;
      }
      localList = b;
      return localList;
    }
    catch (Exception localException)
    {
      e.d(a, "get package info list error");
    }
    return null;
  }
  
  public final class a
  {
    public a() {}
    
    public final a a(Context paramContext)
    {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        throw new IllegalStateException("Cannot be called from the main thread");
      }
      try
      {
        paramContext.getPackageManager().getPackageInfo("com.android.vending", 0);
        b localB = new b((byte)0);
        Object localObject1 = new Intent("com.google.android.gms.ads.identifier.service.START");
        ((Intent)localObject1).setPackage("com.google.android.gms");
        if (paramContext.bindService((Intent)localObject1, localB, 1)) {}
        throw new IOException("Google Play connection failed");
      }
      catch (Exception paramContext)
      {
        try
        {
          localObject1 = new c(localB.a());
          localObject1 = new a(((c)localObject1).a(), ((c)localObject1).b());
          return localObject1;
        }
        catch (Exception localException)
        {
          throw localException;
        }
        finally
        {
          paramContext.unbindService(localB);
        }
        paramContext = paramContext;
        throw paramContext;
      }
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
      
      /* Error */
      public final boolean b()
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_2
        //   2: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   5: astore_3
        //   6: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   9: astore 4
        //   11: aload_3
        //   12: ldc 34
        //   14: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload_3
        //   18: iconst_1
        //   19: invokevirtual 60	android/os/Parcel:writeInt	(I)V
        //   22: aload_0
        //   23: getfield 24	com/mobvista/msdk/base/c/a$a$c:b	Landroid/os/IBinder;
        //   26: iconst_2
        //   27: aload_3
        //   28: aload 4
        //   30: iconst_0
        //   31: invokeinterface 44 5 0
        //   36: pop
        //   37: aload 4
        //   39: invokevirtual 47	android/os/Parcel:readException	()V
        //   42: aload 4
        //   44: invokevirtual 64	android/os/Parcel:readInt	()I
        //   47: istore_1
        //   48: iload_1
        //   49: ifeq +14 -> 63
        //   52: aload 4
        //   54: invokevirtual 53	android/os/Parcel:recycle	()V
        //   57: aload_3
        //   58: invokevirtual 53	android/os/Parcel:recycle	()V
        //   61: iload_2
        //   62: ireturn
        //   63: iconst_0
        //   64: istore_2
        //   65: goto -13 -> 52
        //   68: astore 5
        //   70: aload 4
        //   72: invokevirtual 53	android/os/Parcel:recycle	()V
        //   75: aload_3
        //   76: invokevirtual 53	android/os/Parcel:recycle	()V
        //   79: aload 5
        //   81: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	82	0	this	c
        //   47	2	1	i	int
        //   1	64	2	bool	boolean
        //   5	71	3	localParcel1	Parcel
        //   9	62	4	localParcel2	Parcel
        //   68	12	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   11	48	68	finally
      }
    }
  }
}
