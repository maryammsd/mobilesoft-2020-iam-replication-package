package com.duapps.ad.stats;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import com.duapps.ad.base.g;
import com.duapps.ad.base.i;
import com.duapps.ad.base.l;

public class h
{
  private static h b;
  private Context a;
  
  private h(Context paramContext)
  {
    this.a = paramContext;
  }
  
  public static h a(Context paramContext)
  {
    try
    {
      if (b == null) {
        b = new h(paramContext.getApplicationContext());
      }
      paramContext = b;
      return paramContext;
    }
    finally {}
  }
  
  private void b()
  {
    long l = System.currentTimeMillis();
    try
    {
      this.a.getContentResolver().delete(DuAdCacheProvider.a(this.a, 1), "ts<?", new String[] { String.valueOf(l - 86400000L) });
      return;
    }
    catch (Exception localException)
    {
      g.b("ToolboxCacheMgr", "dumpTimeOutDatas() exception: ", localException);
    }
  }
  
  /* Error */
  public i a(String paramString)
  {
    // Byte code:
    //   0: new 72	com/duapps/ad/base/i
    //   3: dup
    //   4: invokespecial 73	com/duapps/ad/base/i:<init>	()V
    //   7: astore 6
    //   9: aload 6
    //   11: aload_1
    //   12: putfield 76	com/duapps/ad/base/i:a	Ljava/lang/String;
    //   15: aload 6
    //   17: iconst_0
    //   18: putfield 80	com/duapps/ad/base/i:c	I
    //   21: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   24: lstore_2
    //   25: aload_0
    //   26: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   29: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   32: aload_0
    //   33: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   36: iconst_1
    //   37: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   40: iconst_4
    //   41: anewarray 48	java/lang/String
    //   44: dup
    //   45: iconst_0
    //   46: ldc 82
    //   48: aastore
    //   49: dup
    //   50: iconst_1
    //   51: ldc 84
    //   53: aastore
    //   54: dup
    //   55: iconst_2
    //   56: ldc 86
    //   58: aastore
    //   59: dup
    //   60: iconst_3
    //   61: ldc 88
    //   63: aastore
    //   64: ldc 90
    //   66: iconst_2
    //   67: anewarray 48	java/lang/String
    //   70: dup
    //   71: iconst_0
    //   72: aload_1
    //   73: aastore
    //   74: dup
    //   75: iconst_1
    //   76: lload_2
    //   77: ldc2_w 49
    //   80: lsub
    //   81: invokestatic 54	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   84: aastore
    //   85: ldc 92
    //   87: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   90: astore_1
    //   91: aload_1
    //   92: ifnull +99 -> 191
    //   95: aload_1
    //   96: astore 4
    //   98: aload_1
    //   99: invokeinterface 102 1 0
    //   104: ifeq +87 -> 191
    //   107: aload_1
    //   108: astore 4
    //   110: aload_1
    //   111: iconst_0
    //   112: invokeinterface 106 2 0
    //   117: astore 7
    //   119: aload 6
    //   121: astore 5
    //   123: aload_1
    //   124: astore 4
    //   126: aload 5
    //   128: aload 7
    //   130: putfield 76	com/duapps/ad/base/i:a	Ljava/lang/String;
    //   133: aload_1
    //   134: astore 4
    //   136: aload 5
    //   138: aload_1
    //   139: iconst_1
    //   140: invokeinterface 106 2 0
    //   145: putfield 108	com/duapps/ad/base/i:b	Ljava/lang/String;
    //   148: aload_1
    //   149: astore 4
    //   151: aload 5
    //   153: aload_1
    //   154: iconst_2
    //   155: invokeinterface 106 2 0
    //   160: putfield 111	com/duapps/ad/base/i:d	Ljava/lang/String;
    //   163: aload_1
    //   164: astore 4
    //   166: aload 5
    //   168: aload_1
    //   169: iconst_3
    //   170: invokeinterface 115 2 0
    //   175: putfield 80	com/duapps/ad/base/i:c	I
    //   178: goto +13 -> 191
    //   181: astore 5
    //   183: goto +35 -> 218
    //   186: astore 5
    //   188: goto +30 -> 218
    //   191: aload_1
    //   192: ifnull +57 -> 249
    //   195: aload_1
    //   196: invokeinterface 118 1 0
    //   201: ifne +48 -> 249
    //   204: goto +39 -> 243
    //   207: astore_1
    //   208: aconst_null
    //   209: astore 4
    //   211: goto +45 -> 256
    //   214: astore 5
    //   216: aconst_null
    //   217: astore_1
    //   218: aload_1
    //   219: astore 4
    //   221: ldc 62
    //   223: ldc 120
    //   225: aload 5
    //   227: invokestatic 69	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   230: aload_1
    //   231: ifnull +18 -> 249
    //   234: aload_1
    //   235: invokeinterface 118 1 0
    //   240: ifne +9 -> 249
    //   243: aload_1
    //   244: invokeinterface 123 1 0
    //   249: aload 6
    //   251: areturn
    //   252: astore_1
    //   253: goto -42 -> 211
    //   256: aload 4
    //   258: ifnull +20 -> 278
    //   261: aload 4
    //   263: invokeinterface 118 1 0
    //   268: ifne +10 -> 278
    //   271: aload 4
    //   273: invokeinterface 123 1 0
    //   278: aload_1
    //   279: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	280	0	this	h
    //   0	280	1	paramString	String
    //   24	53	2	l	long
    //   96	176	4	str1	String
    //   121	46	5	localI1	i
    //   181	1	5	localException1	Exception
    //   186	1	5	localException2	Exception
    //   214	12	5	localException3	Exception
    //   7	243	6	localI2	i
    //   117	12	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   126	133	181	java/lang/Exception
    //   136	148	181	java/lang/Exception
    //   151	163	181	java/lang/Exception
    //   166	178	181	java/lang/Exception
    //   98	107	186	java/lang/Exception
    //   110	119	186	java/lang/Exception
    //   25	91	207	finally
    //   25	91	214	java/lang/Exception
    //   98	107	252	finally
    //   110	119	252	finally
    //   126	133	252	finally
    //   136	148	252	finally
    //   151	163	252	finally
    //   166	178	252	finally
    //   221	230	252	finally
  }
  
  public void a()
  {
    long l1 = l.k(this.a);
    long l2 = System.currentTimeMillis();
    try
    {
      this.a.getContentResolver().delete(DuAdCacheProvider.a(this.a, 7), "ts<?", new String[] { String.valueOf(l2 - l1) });
      return;
    }
    catch (Throwable localThrowable)
    {
      g.b("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas del exception: ", localThrowable);
      return;
    }
    catch (Exception localException)
    {
      g.b("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas exception: ", localException);
    }
  }
  
  public void a(i paramI)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramI.a);
    localContentValues.put("pkg", paramI.b);
    localContentValues.put("p_url", paramI.d);
    localContentValues.put("type", Integer.valueOf(paramI.c));
    localContentValues.put("ts", Long.valueOf(paramI.e));
    paramI = paramI.a;
    try
    {
      if (this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 1), localContentValues, "_url = ?", new String[] { paramI }) < 1) {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 1), localContentValues);
      }
      b();
      return;
    }
    catch (Exception paramI)
    {
      g.b("ToolboxCacheMgr", "saveParseResult() exception: ", paramI);
    }
  }
  
  /* Error */
  public int b(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: iconst_0
    //   4: istore_3
    //   5: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   8: lstore 5
    //   10: aconst_null
    //   11: astore 8
    //   13: aconst_null
    //   14: astore 7
    //   16: aload_0
    //   17: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   20: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   23: aload_0
    //   24: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   27: iconst_1
    //   28: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   31: iconst_1
    //   32: anewarray 48	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: ldc 88
    //   39: aastore
    //   40: ldc 90
    //   42: iconst_2
    //   43: anewarray 48	java/lang/String
    //   46: dup
    //   47: iconst_0
    //   48: aload_1
    //   49: aastore
    //   50: dup
    //   51: iconst_1
    //   52: lload 5
    //   54: ldc2_w 49
    //   57: lsub
    //   58: invokestatic 54	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   61: aastore
    //   62: ldc 92
    //   64: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   67: astore_1
    //   68: iload_3
    //   69: istore_2
    //   70: aload_1
    //   71: ifnull +45 -> 116
    //   74: iload_3
    //   75: istore_2
    //   76: aload_1
    //   77: invokeinterface 102 1 0
    //   82: ifeq +34 -> 116
    //   85: aload_1
    //   86: iconst_0
    //   87: invokeinterface 115 2 0
    //   92: istore_2
    //   93: goto +23 -> 116
    //   96: astore 7
    //   98: aload_1
    //   99: astore 8
    //   101: aload 7
    //   103: astore_1
    //   104: aload 8
    //   106: astore 7
    //   108: goto +88 -> 196
    //   111: astore 8
    //   113: goto +41 -> 154
    //   116: iload_2
    //   117: istore_3
    //   118: aload_1
    //   119: ifnull +75 -> 194
    //   122: iload_2
    //   123: istore_3
    //   124: aload_1
    //   125: invokeinterface 118 1 0
    //   130: ifne +64 -> 194
    //   133: aload_1
    //   134: invokeinterface 123 1 0
    //   139: iload_2
    //   140: ireturn
    //   141: astore_1
    //   142: goto +54 -> 196
    //   145: astore 7
    //   147: aload 8
    //   149: astore_1
    //   150: aload 7
    //   152: astore 8
    //   154: aload_1
    //   155: astore 7
    //   157: ldc 62
    //   159: ldc -74
    //   161: aload 8
    //   163: invokestatic 69	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   166: iload 4
    //   168: istore_3
    //   169: aload_1
    //   170: ifnull +24 -> 194
    //   173: iload 4
    //   175: istore_3
    //   176: aload_1
    //   177: invokeinterface 118 1 0
    //   182: ifne +12 -> 194
    //   185: aload_1
    //   186: invokeinterface 123 1 0
    //   191: iload 4
    //   193: istore_3
    //   194: iload_3
    //   195: ireturn
    //   196: aload 7
    //   198: ifnull +20 -> 218
    //   201: aload 7
    //   203: invokeinterface 118 1 0
    //   208: ifne +10 -> 218
    //   211: aload 7
    //   213: invokeinterface 123 1 0
    //   218: aload_1
    //   219: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	220	0	this	h
    //   0	220	1	paramString	String
    //   69	71	2	i	int
    //   4	191	3	j	int
    //   1	191	4	k	int
    //   8	45	5	l	long
    //   14	1	7	localObject1	Object
    //   96	6	7	localObject2	Object
    //   106	1	7	str1	String
    //   145	6	7	localException1	Exception
    //   155	57	7	str2	String
    //   11	94	8	str3	String
    //   111	37	8	localException2	Exception
    //   152	10	8	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   76	93	96	finally
    //   76	93	111	java/lang/Exception
    //   16	68	141	finally
    //   157	166	141	finally
    //   16	68	145	java/lang/Exception
  }
  
  /* Error */
  public void b(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 184	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 185	java/util/ArrayList:<init>	()V
    //   9: astore 6
    //   11: aload_0
    //   12: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   15: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   18: aload_0
    //   19: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   22: bipush 7
    //   24: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   27: iconst_1
    //   28: anewarray 48	java/lang/String
    //   31: dup
    //   32: iconst_0
    //   33: ldc 84
    //   35: aastore
    //   36: aconst_null
    //   37: aconst_null
    //   38: aconst_null
    //   39: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   42: astore_3
    //   43: aload_3
    //   44: ifnull +59 -> 103
    //   47: aload_3
    //   48: astore 4
    //   50: aload_3
    //   51: invokeinterface 102 1 0
    //   56: ifeq +47 -> 103
    //   59: aload_3
    //   60: astore 4
    //   62: aload_3
    //   63: iconst_0
    //   64: invokeinterface 106 2 0
    //   69: astore 5
    //   71: aload_3
    //   72: astore 4
    //   74: aload 5
    //   76: invokestatic 191	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   79: ifne +24 -> 103
    //   82: aload_3
    //   83: astore 4
    //   85: aload 6
    //   87: aload 5
    //   89: invokeinterface 197 2 0
    //   94: pop
    //   95: goto +8 -> 103
    //   98: astore 5
    //   100: goto +30 -> 130
    //   103: aload_3
    //   104: ifnull +57 -> 161
    //   107: aload_3
    //   108: invokeinterface 118 1 0
    //   113: ifne +48 -> 161
    //   116: goto +39 -> 155
    //   119: astore_1
    //   120: aconst_null
    //   121: astore 4
    //   123: goto +376 -> 499
    //   126: astore 5
    //   128: aconst_null
    //   129: astore_3
    //   130: aload_3
    //   131: astore 4
    //   133: ldc 62
    //   135: ldc 120
    //   137: aload 5
    //   139: invokestatic 69	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   142: aload_3
    //   143: ifnull +18 -> 161
    //   146: aload_3
    //   147: invokeinterface 118 1 0
    //   152: ifne +9 -> 161
    //   155: aload_3
    //   156: invokeinterface 123 1 0
    //   161: aload 6
    //   163: invokeinterface 201 1 0
    //   168: ifle +329 -> 497
    //   171: new 184	java/util/ArrayList
    //   174: dup
    //   175: invokespecial 185	java/util/ArrayList:<init>	()V
    //   178: astore_3
    //   179: aload_1
    //   180: invokevirtual 205	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   183: astore_1
    //   184: aload_1
    //   185: ifnull +102 -> 287
    //   188: aload_1
    //   189: sipush 256
    //   192: invokevirtual 211	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   195: astore_1
    //   196: aload 6
    //   198: invokeinterface 215 1 0
    //   203: astore 4
    //   205: aload 4
    //   207: invokeinterface 220 1 0
    //   212: ifeq +75 -> 287
    //   215: aload 4
    //   217: invokeinterface 224 1 0
    //   222: checkcast 48	java/lang/String
    //   225: astore 5
    //   227: aload_1
    //   228: invokeinterface 215 1 0
    //   233: astore 6
    //   235: aload 6
    //   237: invokeinterface 220 1 0
    //   242: ifeq -37 -> 205
    //   245: aload 6
    //   247: invokeinterface 224 1 0
    //   252: checkcast 226	android/content/pm/PackageInfo
    //   255: astore 7
    //   257: aload 7
    //   259: ifnull -24 -> 235
    //   262: aload 7
    //   264: getfield 229	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   267: aload 5
    //   269: invokevirtual 232	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   272: ifeq -37 -> 235
    //   275: aload_3
    //   276: aload 5
    //   278: invokeinterface 197 2 0
    //   283: pop
    //   284: goto -49 -> 235
    //   287: aload_3
    //   288: invokeinterface 201 1 0
    //   293: ifle +204 -> 497
    //   296: ldc -22
    //   298: astore_1
    //   299: iload_2
    //   300: aload_3
    //   301: invokeinterface 201 1 0
    //   306: if_icmpge +121 -> 427
    //   309: aload_3
    //   310: iload_2
    //   311: invokeinterface 238 2 0
    //   316: checkcast 48	java/lang/String
    //   319: astore 4
    //   321: iload_2
    //   322: ifne +52 -> 374
    //   325: new 240	java/lang/StringBuilder
    //   328: dup
    //   329: invokespecial 241	java/lang/StringBuilder:<init>	()V
    //   332: astore 5
    //   334: aload 5
    //   336: aload_1
    //   337: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: pop
    //   341: aload 5
    //   343: ldc -9
    //   345: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: pop
    //   349: aload 5
    //   351: aload 4
    //   353: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: pop
    //   357: aload 5
    //   359: ldc -9
    //   361: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   364: pop
    //   365: aload 5
    //   367: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   370: astore_1
    //   371: goto +49 -> 420
    //   374: new 240	java/lang/StringBuilder
    //   377: dup
    //   378: invokespecial 241	java/lang/StringBuilder:<init>	()V
    //   381: astore 5
    //   383: aload 5
    //   385: aload_1
    //   386: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: pop
    //   390: aload 5
    //   392: ldc -3
    //   394: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: pop
    //   398: aload 5
    //   400: aload 4
    //   402: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: pop
    //   406: aload 5
    //   408: ldc -9
    //   410: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   413: pop
    //   414: aload 5
    //   416: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   419: astore_1
    //   420: iload_2
    //   421: iconst_1
    //   422: iadd
    //   423: istore_2
    //   424: goto -125 -> 299
    //   427: new 240	java/lang/StringBuilder
    //   430: dup
    //   431: invokespecial 241	java/lang/StringBuilder:<init>	()V
    //   434: astore_3
    //   435: aload_3
    //   436: aload_1
    //   437: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   440: pop
    //   441: aload_3
    //   442: ldc -1
    //   444: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: pop
    //   448: aload_3
    //   449: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   452: astore_1
    //   453: aload_0
    //   454: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   457: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   460: aload_0
    //   461: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   464: bipush 7
    //   466: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   469: aload_1
    //   470: aconst_null
    //   471: invokevirtual 60	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   474: pop
    //   475: return
    //   476: astore_1
    //   477: ldc 62
    //   479: ldc_w 257
    //   482: aload_1
    //   483: invokestatic 69	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   486: return
    //   487: astore_1
    //   488: ldc 62
    //   490: ldc_w 259
    //   493: aload_1
    //   494: invokestatic 69	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   497: return
    //   498: astore_1
    //   499: aload 4
    //   501: ifnull +20 -> 521
    //   504: aload 4
    //   506: invokeinterface 118 1 0
    //   511: ifne +10 -> 521
    //   514: aload 4
    //   516: invokeinterface 123 1 0
    //   521: aload_1
    //   522: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	523	0	this	h
    //   0	523	1	paramContext	Context
    //   1	423	2	i	int
    //   42	407	3	localObject1	Object
    //   48	467	4	localObject2	Object
    //   69	19	5	str	String
    //   98	1	5	localException1	Exception
    //   126	12	5	localException2	Exception
    //   225	190	5	localObject3	Object
    //   9	237	6	localObject4	Object
    //   255	8	7	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   50	59	98	java/lang/Exception
    //   62	71	98	java/lang/Exception
    //   74	82	98	java/lang/Exception
    //   85	95	98	java/lang/Exception
    //   11	43	119	finally
    //   11	43	126	java/lang/Exception
    //   453	475	476	java/lang/Throwable
    //   453	475	487	java/lang/Exception
    //   50	59	498	finally
    //   62	71	498	finally
    //   74	82	498	finally
    //   85	95	498	finally
    //   133	142	498	finally
  }
  
  public void b(i paramI)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramI.a);
    localContentValues.put("pkg", paramI.b);
    localContentValues.put("p_url", paramI.d);
    localContentValues.put("type", Integer.valueOf(paramI.c));
    localContentValues.put("ts", Long.valueOf(paramI.e));
    paramI = paramI.a;
    try
    {
      if (this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 7), localContentValues, "_url = ?", new String[] { paramI }) < 1)
      {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 7), localContentValues);
        return;
      }
    }
    catch (Exception paramI)
    {
      g.b("ToolboxCacheMgr", "saveParseResult() exception: ", paramI);
    }
  }
  
  /* Error */
  public int c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   4: invokestatic 131	com/duapps/ad/base/l:k	(Landroid/content/Context;)J
    //   7: lstore 5
    //   9: iconst_0
    //   10: istore 4
    //   12: iconst_0
    //   13: istore_3
    //   14: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   17: lstore 7
    //   19: aconst_null
    //   20: astore 10
    //   22: aconst_null
    //   23: astore 9
    //   25: aload_0
    //   26: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   29: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   32: aload_0
    //   33: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   36: bipush 7
    //   38: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   41: iconst_1
    //   42: anewarray 48	java/lang/String
    //   45: dup
    //   46: iconst_0
    //   47: ldc 88
    //   49: aastore
    //   50: ldc_w 261
    //   53: iconst_2
    //   54: anewarray 48	java/lang/String
    //   57: dup
    //   58: iconst_0
    //   59: aload_1
    //   60: aastore
    //   61: dup
    //   62: iconst_1
    //   63: lload 7
    //   65: lload 5
    //   67: lsub
    //   68: invokestatic 54	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   71: aastore
    //   72: ldc 92
    //   74: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   77: astore_1
    //   78: iload_3
    //   79: istore_2
    //   80: aload_1
    //   81: ifnull +45 -> 126
    //   84: iload_3
    //   85: istore_2
    //   86: aload_1
    //   87: invokeinterface 102 1 0
    //   92: ifeq +34 -> 126
    //   95: aload_1
    //   96: iconst_0
    //   97: invokeinterface 115 2 0
    //   102: istore_2
    //   103: goto +23 -> 126
    //   106: astore 9
    //   108: aload_1
    //   109: astore 10
    //   111: aload 9
    //   113: astore_1
    //   114: aload 10
    //   116: astore 9
    //   118: goto +88 -> 206
    //   121: astore 10
    //   123: goto +41 -> 164
    //   126: iload_2
    //   127: istore_3
    //   128: aload_1
    //   129: ifnull +75 -> 204
    //   132: iload_2
    //   133: istore_3
    //   134: aload_1
    //   135: invokeinterface 118 1 0
    //   140: ifne +64 -> 204
    //   143: aload_1
    //   144: invokeinterface 123 1 0
    //   149: iload_2
    //   150: ireturn
    //   151: astore_1
    //   152: goto +54 -> 206
    //   155: astore 9
    //   157: aload 10
    //   159: astore_1
    //   160: aload 9
    //   162: astore 10
    //   164: aload_1
    //   165: astore 9
    //   167: ldc 62
    //   169: ldc -74
    //   171: aload 10
    //   173: invokestatic 69	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   176: iload 4
    //   178: istore_3
    //   179: aload_1
    //   180: ifnull +24 -> 204
    //   183: iload 4
    //   185: istore_3
    //   186: aload_1
    //   187: invokeinterface 118 1 0
    //   192: ifne +12 -> 204
    //   195: aload_1
    //   196: invokeinterface 123 1 0
    //   201: iload 4
    //   203: istore_3
    //   204: iload_3
    //   205: ireturn
    //   206: aload 9
    //   208: ifnull +20 -> 228
    //   211: aload 9
    //   213: invokeinterface 118 1 0
    //   218: ifne +10 -> 228
    //   221: aload 9
    //   223: invokeinterface 123 1 0
    //   228: aload_1
    //   229: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	230	0	this	h
    //   0	230	1	paramString	String
    //   79	71	2	i	int
    //   13	192	3	j	int
    //   10	192	4	k	int
    //   7	59	5	l1	long
    //   17	47	7	l2	long
    //   23	1	9	localObject1	Object
    //   106	6	9	localObject2	Object
    //   116	1	9	str1	String
    //   155	6	9	localException1	Exception
    //   165	57	9	str2	String
    //   20	95	10	str3	String
    //   121	37	10	localException2	Exception
    //   162	10	10	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   86	103	106	finally
    //   86	103	121	java/lang/Exception
    //   25	78	151	finally
    //   167	176	151	finally
    //   25	78	155	java/lang/Exception
  }
  
  /* Error */
  public i d(String paramString)
  {
    // Byte code:
    //   0: new 72	com/duapps/ad/base/i
    //   3: dup
    //   4: invokespecial 73	com/duapps/ad/base/i:<init>	()V
    //   7: astore 8
    //   9: aload 8
    //   11: aload_1
    //   12: putfield 108	com/duapps/ad/base/i:b	Ljava/lang/String;
    //   15: aload 8
    //   17: iconst_0
    //   18: putfield 80	com/duapps/ad/base/i:c	I
    //   21: aload_0
    //   22: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   25: invokestatic 131	com/duapps/ad/base/l:k	(Landroid/content/Context;)J
    //   28: lstore_2
    //   29: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   32: lstore 4
    //   34: aconst_null
    //   35: astore 9
    //   37: aconst_null
    //   38: astore 6
    //   40: aload_0
    //   41: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   44: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   47: aload_0
    //   48: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   51: bipush 7
    //   53: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   56: iconst_4
    //   57: anewarray 48	java/lang/String
    //   60: dup
    //   61: iconst_0
    //   62: ldc 82
    //   64: aastore
    //   65: dup
    //   66: iconst_1
    //   67: ldc 84
    //   69: aastore
    //   70: dup
    //   71: iconst_2
    //   72: ldc 86
    //   74: aastore
    //   75: dup
    //   76: iconst_3
    //   77: ldc 88
    //   79: aastore
    //   80: ldc_w 261
    //   83: iconst_2
    //   84: anewarray 48	java/lang/String
    //   87: dup
    //   88: iconst_0
    //   89: aload_1
    //   90: aastore
    //   91: dup
    //   92: iconst_1
    //   93: lload 4
    //   95: lload_2
    //   96: lsub
    //   97: invokestatic 54	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   100: aastore
    //   101: ldc 92
    //   103: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +97 -> 207
    //   113: aload 7
    //   115: invokeinterface 102 1 0
    //   120: ifeq +87 -> 207
    //   123: aload 7
    //   125: iconst_0
    //   126: invokeinterface 106 2 0
    //   131: astore 6
    //   133: aload 8
    //   135: astore_1
    //   136: aload_1
    //   137: aload 6
    //   139: putfield 76	com/duapps/ad/base/i:a	Ljava/lang/String;
    //   142: aload_1
    //   143: aload 7
    //   145: iconst_1
    //   146: invokeinterface 106 2 0
    //   151: putfield 108	com/duapps/ad/base/i:b	Ljava/lang/String;
    //   154: aload_1
    //   155: aload 7
    //   157: iconst_2
    //   158: invokeinterface 106 2 0
    //   163: putfield 111	com/duapps/ad/base/i:d	Ljava/lang/String;
    //   166: aload_1
    //   167: aload 7
    //   169: iconst_3
    //   170: invokeinterface 115 2 0
    //   175: putfield 80	com/duapps/ad/base/i:c	I
    //   178: goto +29 -> 207
    //   181: astore_1
    //   182: goto +12 -> 194
    //   185: astore_1
    //   186: aload 7
    //   188: astore 6
    //   190: goto +43 -> 233
    //   193: astore_1
    //   194: aload 7
    //   196: astore 6
    //   198: aload_1
    //   199: astore 7
    //   201: aload 6
    //   203: astore_1
    //   204: goto +37 -> 241
    //   207: aload 7
    //   209: ifnull +63 -> 272
    //   212: aload 7
    //   214: invokeinterface 118 1 0
    //   219: ifne +53 -> 272
    //   222: aload 7
    //   224: invokeinterface 123 1 0
    //   229: aload 8
    //   231: areturn
    //   232: astore_1
    //   233: goto +42 -> 275
    //   236: astore 7
    //   238: aload 9
    //   240: astore_1
    //   241: aload_1
    //   242: astore 6
    //   244: ldc 62
    //   246: ldc 120
    //   248: aload 7
    //   250: invokestatic 69	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   253: aload_1
    //   254: ifnull +18 -> 272
    //   257: aload_1
    //   258: invokeinterface 118 1 0
    //   263: ifne +9 -> 272
    //   266: aload_1
    //   267: invokeinterface 123 1 0
    //   272: aload 8
    //   274: areturn
    //   275: aload 6
    //   277: ifnull +20 -> 297
    //   280: aload 6
    //   282: invokeinterface 118 1 0
    //   287: ifne +10 -> 297
    //   290: aload 6
    //   292: invokeinterface 123 1 0
    //   297: aload_1
    //   298: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	299	0	this	h
    //   0	299	1	paramString	String
    //   28	68	2	l1	long
    //   32	62	4	l2	long
    //   38	253	6	localObject1	Object
    //   106	117	7	localObject2	Object
    //   236	13	7	localException	Exception
    //   7	266	8	localI	i
    //   35	204	9	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   136	178	181	java/lang/Exception
    //   113	133	185	finally
    //   136	178	185	finally
    //   113	133	193	java/lang/Exception
    //   40	108	232	finally
    //   244	253	232	finally
    //   40	108	236	java/lang/Exception
  }
}
