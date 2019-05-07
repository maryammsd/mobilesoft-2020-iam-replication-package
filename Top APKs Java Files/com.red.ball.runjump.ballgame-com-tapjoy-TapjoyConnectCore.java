package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.tapjoy.internal.ApiKeyDecoded;
import com.tapjoy.internal.ApiKeyDecoded.KeyUsage;
import com.tapjoy.internal.cw;
import com.tapjoy.internal.ey;
import com.tapjoy.internal.fk;
import com.tapjoy.internal.gd;
import com.tapjoy.internal.gt.a;
import com.tapjoy.internal.gt.l;
import com.tapjoy.internal.gt.n;
import com.tapjoy.internal.gt.x;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import java.util.Vector;

public class TapjoyConnectCore
{
  private static String A;
  private static int B = 0;
  private static float C = 0.0F;
  private static int D = 0;
  public static final float DEFAULT_CURRENCY_MULTIPLIER = 1.0F;
  private static String E;
  private static boolean F = false;
  private static String G;
  private static String H;
  private static String I;
  private static String J;
  private static String K;
  private static String L;
  private static String M;
  public static final int MAX_NUMBER_OF_OFFLINE_LOGS = 50;
  private static String N;
  private static String O;
  private static String P;
  private static String Q;
  private static String R;
  private static String S;
  private static float T;
  private static boolean U;
  private static boolean V;
  private static boolean W;
  private static boolean X;
  private static boolean Y;
  private static String Z;
  protected static int a;
  private static int aA;
  private static String aB;
  private static String aC;
  private static String aD;
  private static String aE;
  private static String aF;
  private static int aG;
  private static int aH;
  private static int aI;
  private static long aJ;
  private static long aK;
  private static long aL;
  private static String aM;
  private static int aN;
  private static double aO;
  private static double aP;
  private static long aQ;
  private static int aR;
  private static int aS;
  private static int aT;
  private static String aU;
  private static String aV;
  private static String aW;
  private static boolean aX = false;
  private static boolean aY = true;
  private static String aa;
  private static String ab;
  private static String ac;
  private static String ad;
  private static long ah;
  private static boolean aj;
  private static PackageManager ak;
  private static Hashtable an;
  private static String ao;
  private static boolean ap;
  private static String aq;
  private static String ar;
  private static String as;
  private static String at;
  private static int au;
  private static String av;
  private static String aw;
  private static long ax;
  private static String ay;
  private static int az;
  protected static int b;
  protected static String c;
  protected static boolean d;
  protected static String e;
  protected static String f;
  private static Context g = null;
  private static String h = null;
  private static TapjoyConnectCore i = null;
  private static TapjoyURLConnection j = null;
  private static TJConnectListener k = null;
  private static TJViewListener l = null;
  private static Vector m = new Vector(Arrays.asList(TapjoyConstants.dependencyClassNames));
  private static String n = "";
  private static String o = "";
  private static String p;
  private static String q = "";
  private static String r = "";
  private static String s = "";
  private static String t = "";
  private static String u = "";
  private static String v = "";
  private static String w = "";
  private static String x = "";
  private static String y = "";
  private static String z = "";
  private long ae;
  private Timer af;
  private boolean ag;
  private boolean ai;
  private fk al;
  private TapjoyGpsHelper am;
  
  static
  {
    A = "";
    B = 1;
    C = 1.0F;
    D = 1;
    E = "";
    F = false;
    G = "";
    H = "";
    I = "";
    J = "";
    K = "";
    L = "";
    M = "";
    N = "";
    O = "";
    P = "";
    Q = "native";
    R = "";
    S = "";
    T = 1.0F;
    U = false;
    V = false;
    W = false;
    X = false;
    Y = false;
    Z = "";
    aa = "";
    ab = "";
    ac = "";
    ad = null;
    ah = 0L;
    a = 0;
    b = 0;
    c = "";
    e = "";
    f = "";
    an = TapjoyConnectFlag.CONNECT_FLAG_DEFAULTS;
    ao = "";
    ap = false;
  }
  
  /* Error */
  public TapjoyConnectCore(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 299	java/lang/Object:<init>	()V
    //   4: aload_0
    //   5: lconst_0
    //   6: putfield 301	com/tapjoy/TapjoyConnectCore:ae	J
    //   9: aload_0
    //   10: aconst_null
    //   11: putfield 303	com/tapjoy/TapjoyConnectCore:af	Ljava/util/Timer;
    //   14: aload_0
    //   15: iconst_0
    //   16: putfield 305	com/tapjoy/TapjoyConnectCore:ag	Z
    //   19: aload_0
    //   20: iconst_0
    //   21: putfield 307	com/tapjoy/TapjoyConnectCore:ai	Z
    //   24: aload_1
    //   25: putstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   28: new 309	com/tapjoy/TapjoyURLConnection
    //   31: dup
    //   32: invokespecial 310	com/tapjoy/TapjoyURLConnection:<init>	()V
    //   35: putstatic 148	com/tapjoy/TapjoyConnectCore:j	Lcom/tapjoy/TapjoyURLConnection;
    //   38: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   41: invokevirtual 316	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   44: putstatic 318	com/tapjoy/TapjoyConnectCore:ak	Landroid/content/pm/PackageManager;
    //   47: aload_0
    //   48: new 320	com/tapjoy/TapjoyGpsHelper
    //   51: dup
    //   52: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   55: invokespecial 322	com/tapjoy/TapjoyGpsHelper:<init>	(Landroid/content/Context;)V
    //   58: putfield 324	com/tapjoy/TapjoyConnectCore:am	Lcom/tapjoy/TapjoyGpsHelper;
    //   61: getstatic 279	com/tapjoy/TapjoyConnectCore:an	Ljava/util/Hashtable;
    //   64: ifnonnull +13 -> 77
    //   67: new 326	java/util/Hashtable
    //   70: dup
    //   71: invokespecial 327	java/util/Hashtable:<init>	()V
    //   74: putstatic 279	com/tapjoy/TapjoyConnectCore:an	Ljava/util/Hashtable;
    //   77: ldc_w 329
    //   80: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   83: ifnull +22 -> 105
    //   86: ldc_w 329
    //   89: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   92: ldc_w 335
    //   95: invokevirtual 341	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   98: ifeq +7 -> 105
    //   101: iconst_1
    //   102: invokestatic 347	com/tapjoy/TapjoyLog:setDebugEnabled	(Z)V
    //   105: invokestatic 349	com/tapjoy/TapjoyConnectCore:i	()V
    //   108: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   111: invokevirtual 353	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   114: ldc_w 355
    //   117: aconst_null
    //   118: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   121: invokevirtual 359	android/content/Context:getPackageName	()Ljava/lang/String;
    //   124: invokevirtual 365	android/content/res/Resources:getIdentifier	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
    //   127: istore_2
    //   128: new 367	java/util/Properties
    //   131: dup
    //   132: invokespecial 368	java/util/Properties:<init>	()V
    //   135: astore_1
    //   136: aload_1
    //   137: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   140: invokevirtual 353	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   143: iload_2
    //   144: invokevirtual 372	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   147: invokevirtual 376	java/util/Properties:load	(Ljava/io/InputStream;)V
    //   150: aload_1
    //   151: invokestatic 379	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/Properties;)V
    //   154: ldc_w 381
    //   157: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   160: ldc -82
    //   162: if_acmpne +7 -> 169
    //   165: aload_0
    //   166: invokespecial 383	com/tapjoy/TapjoyConnectCore:j	()V
    //   169: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   172: invokevirtual 387	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   175: ldc_w 389
    //   178: invokestatic 395	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   181: astore_1
    //   182: aload_1
    //   183: putstatic 176	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   186: aload_1
    //   187: ifnull +12 -> 199
    //   190: getstatic 176	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   193: invokevirtual 398	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   196: putstatic 176	com/tapjoy/TapjoyConnectCore:n	Ljava/lang/String;
    //   199: getstatic 318	com/tapjoy/TapjoyConnectCore:ak	Landroid/content/pm/PackageManager;
    //   202: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   205: invokevirtual 359	android/content/Context:getPackageName	()Ljava/lang/String;
    //   208: iconst_0
    //   209: invokevirtual 404	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   212: getfield 409	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   215: putstatic 196	com/tapjoy/TapjoyConnectCore:y	Ljava/lang/String;
    //   218: ldc_w 411
    //   221: putstatic 190	com/tapjoy/TapjoyConnectCore:v	Ljava/lang/String;
    //   224: ldc_w 411
    //   227: putstatic 212	com/tapjoy/TapjoyConnectCore:G	Ljava/lang/String;
    //   230: getstatic 416	android/os/Build:MODEL	Ljava/lang/String;
    //   233: putstatic 186	com/tapjoy/TapjoyConnectCore:t	Ljava/lang/String;
    //   236: getstatic 419	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   239: putstatic 188	com/tapjoy/TapjoyConnectCore:u	Ljava/lang/String;
    //   242: getstatic 424	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   245: putstatic 192	com/tapjoy/TapjoyConnectCore:w	Ljava/lang/String;
    //   248: ldc_w 426
    //   251: putstatic 198	com/tapjoy/TapjoyConnectCore:z	Ljava/lang/String;
    //   254: ldc_w 428
    //   257: putstatic 200	com/tapjoy/TapjoyConnectCore:A	Ljava/lang/String;
    //   260: getstatic 431	android/os/Build$VERSION:SDK_INT	I
    //   263: iconst_3
    //   264: if_icmple +35 -> 299
    //   267: new 433	com/tapjoy/TapjoyDisplayMetricsUtil
    //   270: dup
    //   271: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   274: invokespecial 434	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   277: astore_1
    //   278: aload_1
    //   279: invokevirtual 438	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityDPI	()I
    //   282: putstatic 202	com/tapjoy/TapjoyConnectCore:B	I
    //   285: aload_1
    //   286: invokevirtual 442	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensityScale	()F
    //   289: putstatic 204	com/tapjoy/TapjoyConnectCore:C	F
    //   292: aload_1
    //   293: invokevirtual 445	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   296: putstatic 206	com/tapjoy/TapjoyConnectCore:D	I
    //   299: ldc_w 447
    //   302: invokestatic 450	com/tapjoy/TapjoyConnectCore:h	(Ljava/lang/String;)Z
    //   305: istore_3
    //   306: iload_3
    //   307: ifeq +911 -> 1218
    //   310: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   313: ldc_w 452
    //   316: invokevirtual 456	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   319: checkcast 458	android/net/wifi/WifiManager
    //   322: astore_1
    //   323: aload_1
    //   324: ifnull +42 -> 366
    //   327: aload_1
    //   328: invokevirtual 462	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   331: astore_1
    //   332: aload_1
    //   333: ifnull +33 -> 366
    //   336: aload_1
    //   337: invokevirtual 467	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   340: astore_1
    //   341: aload_1
    //   342: putstatic 182	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   345: aload_1
    //   346: ifnull +20 -> 366
    //   349: getstatic 182	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   352: ldc_w 469
    //   355: ldc -82
    //   357: invokevirtual 473	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   360: invokevirtual 398	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   363: putstatic 182	com/tapjoy/TapjoyConnectCore:r	Ljava/lang/String;
    //   366: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   369: ldc_w 475
    //   372: invokevirtual 456	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   375: checkcast 477	android/telephony/TelephonyManager
    //   378: astore_1
    //   379: aload_1
    //   380: ifnull +223 -> 603
    //   383: aload_1
    //   384: invokevirtual 480	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   387: putstatic 214	com/tapjoy/TapjoyConnectCore:H	Ljava/lang/String;
    //   390: aload_1
    //   391: invokevirtual 483	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   394: putstatic 216	com/tapjoy/TapjoyConnectCore:I	Ljava/lang/String;
    //   397: aload_1
    //   398: invokevirtual 486	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   401: ifnull +49 -> 450
    //   404: aload_1
    //   405: invokevirtual 486	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   408: invokevirtual 489	java/lang/String:length	()I
    //   411: iconst_5
    //   412: if_icmpeq +15 -> 427
    //   415: aload_1
    //   416: invokevirtual 486	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   419: invokevirtual 489	java/lang/String:length	()I
    //   422: bipush 6
    //   424: if_icmpne +26 -> 450
    //   427: aload_1
    //   428: invokevirtual 486	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   431: iconst_0
    //   432: iconst_3
    //   433: invokevirtual 493	java/lang/String:substring	(II)Ljava/lang/String;
    //   436: putstatic 218	com/tapjoy/TapjoyConnectCore:J	Ljava/lang/String;
    //   439: aload_1
    //   440: invokevirtual 486	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   443: iconst_3
    //   444: invokevirtual 496	java/lang/String:substring	(I)Ljava/lang/String;
    //   447: putstatic 220	com/tapjoy/TapjoyConnectCore:K	Ljava/lang/String;
    //   450: ldc_w 498
    //   453: invokestatic 450	com/tapjoy/TapjoyConnectCore:h	(Ljava/lang/String;)Z
    //   456: istore_3
    //   457: iload_3
    //   458: ifeq +952 -> 1410
    //   461: ldc_w 500
    //   464: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   467: ifnull +763 -> 1230
    //   470: ldc_w 500
    //   473: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   476: invokevirtual 489	java/lang/String:length	()I
    //   479: ifle +751 -> 1230
    //   482: ldc_w 500
    //   485: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   488: putstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   491: ldc_w 502
    //   494: new 504	java/lang/StringBuilder
    //   497: dup
    //   498: ldc_w 506
    //   501: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   504: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   507: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   513: invokestatic 519	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   516: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   519: ifnonnull +755 -> 1274
    //   522: ldc_w 502
    //   525: ldc_w 521
    //   528: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   531: iconst_0
    //   532: istore_2
    //   533: ldc_w 502
    //   536: new 504	java/lang/StringBuilder
    //   539: dup
    //   540: ldc_w 525
    //   543: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   546: getstatic 431	android/os/Build$VERSION:SDK_INT	I
    //   549: invokevirtual 528	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   552: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   555: invokestatic 519	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   558: getstatic 431	android/os/Build$VERSION:SDK_INT	I
    //   561: bipush 9
    //   563: if_icmplt +40 -> 603
    //   566: ldc_w 502
    //   569: ldc_w 530
    //   572: invokestatic 519	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   575: aload_0
    //   576: invokevirtual 533	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   579: astore_1
    //   580: iload_2
    //   581: ifne +7 -> 588
    //   584: aload_1
    //   585: putstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   588: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   591: ifnonnull +747 -> 1338
    //   594: ldc_w 502
    //   597: ldc_w 535
    //   600: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   603: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   606: ldc_w 537
    //   609: iconst_0
    //   610: invokevirtual 541	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   613: astore_1
    //   614: aload_1
    //   615: ldc_w 543
    //   618: ldc -82
    //   620: invokeinterface 548 3 0
    //   625: astore 4
    //   627: aload 4
    //   629: putstatic 184	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   632: aload 4
    //   634: ifnull +14 -> 648
    //   637: getstatic 184	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   640: invokevirtual 489	java/lang/String:length	()I
    //   643: istore_2
    //   644: iload_2
    //   645: ifne +61 -> 706
    //   648: new 504	java/lang/StringBuilder
    //   651: dup
    //   652: invokespecial 549	java/lang/StringBuilder:<init>	()V
    //   655: invokestatic 555	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   658: invokevirtual 556	java/util/UUID:toString	()Ljava/lang/String;
    //   661: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   664: invokestatic 562	java/lang/System:currentTimeMillis	()J
    //   667: invokevirtual 565	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   670: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   673: invokestatic 570	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   676: putstatic 184	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   679: aload_1
    //   680: invokeinterface 574 1 0
    //   685: astore_1
    //   686: aload_1
    //   687: ldc_w 543
    //   690: getstatic 184	com/tapjoy/TapjoyConnectCore:s	Ljava/lang/String;
    //   693: invokeinterface 580 3 0
    //   698: pop
    //   699: aload_1
    //   700: invokeinterface 584 1 0
    //   705: pop
    //   706: ldc_w 586
    //   709: ldc_w 588
    //   712: invokestatic 591	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;Ljava/lang/String;)Z
    //   715: putstatic 210	com/tapjoy/TapjoyConnectCore:F	Z
    //   718: ldc_w 593
    //   721: invokestatic 595	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   724: putstatic 242	com/tapjoy/TapjoyConnectCore:U	Z
    //   727: ldc_w 597
    //   730: invokestatic 595	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   733: putstatic 244	com/tapjoy/TapjoyConnectCore:V	Z
    //   736: ldc_w 599
    //   739: invokestatic 595	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   742: putstatic 246	com/tapjoy/TapjoyConnectCore:W	Z
    //   745: ldc_w 601
    //   748: invokestatic 595	com/tapjoy/TapjoyConnectCore:g	(Ljava/lang/String;)Z
    //   751: putstatic 248	com/tapjoy/TapjoyConnectCore:X	Z
    //   754: ldc_w 603
    //   757: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   760: ifnull +71 -> 831
    //   763: ldc_w 603
    //   766: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   769: invokevirtual 489	java/lang/String:length	()I
    //   772: ifle +59 -> 831
    //   775: ldc_w 603
    //   778: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   781: putstatic 226	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   784: new 605	java/util/ArrayList
    //   787: dup
    //   788: getstatic 608	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   791: invokestatic 166	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   794: invokespecial 609	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   797: getstatic 226	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   800: invokevirtual 612	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   803: ifne +28 -> 831
    //   806: ldc_w 502
    //   809: new 504	java/lang/StringBuilder
    //   812: dup
    //   813: ldc_w 614
    //   816: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   819: getstatic 226	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   822: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   825: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   828: invokestatic 616	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   831: getstatic 226	com/tapjoy/TapjoyConnectCore:N	Ljava/lang/String;
    //   834: astore_1
    //   835: new 618	android/content/Intent
    //   838: dup
    //   839: ldc_w 620
    //   842: invokespecial 621	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   845: astore 4
    //   847: aload_1
    //   848: invokevirtual 489	java/lang/String:length	()I
    //   851: ifgt +661 -> 1512
    //   854: aload 4
    //   856: ldc_w 623
    //   859: invokestatic 629	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   862: invokevirtual 633	android/content/Intent:setData	(Landroid/net/Uri;)Landroid/content/Intent;
    //   865: pop
    //   866: getstatic 318	com/tapjoy/TapjoyConnectCore:ak	Landroid/content/pm/PackageManager;
    //   869: aload 4
    //   871: iconst_0
    //   872: invokevirtual 637	android/content/pm/PackageManager:queryIntentActivities	(Landroid/content/Intent;I)Ljava/util/List;
    //   875: invokeinterface 642 1 0
    //   880: ifle +712 -> 1592
    //   883: iconst_1
    //   884: istore_3
    //   885: iload_3
    //   886: putstatic 250	com/tapjoy/TapjoyConnectCore:Y	Z
    //   889: invokestatic 644	com/tapjoy/TapjoyConnectCore:g	()V
    //   892: ldc_w 646
    //   895: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   898: ifnull +24 -> 922
    //   901: ldc_w 646
    //   904: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   907: invokevirtual 489	java/lang/String:length	()I
    //   910: ifle +12 -> 922
    //   913: ldc_w 646
    //   916: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   919: putstatic 272	com/tapjoy/TapjoyConnectCore:f	Ljava/lang/String;
    //   922: ldc_w 648
    //   925: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   928: ifnull +24 -> 952
    //   931: ldc_w 648
    //   934: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   937: invokevirtual 489	java/lang/String:length	()I
    //   940: ifle +12 -> 952
    //   943: ldc_w 648
    //   946: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   949: putstatic 270	com/tapjoy/TapjoyConnectCore:e	Ljava/lang/String;
    //   952: ldc_w 650
    //   955: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   958: ifnull +52 -> 1010
    //   961: ldc_w 650
    //   964: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   967: invokevirtual 489	java/lang/String:length	()I
    //   970: ifle +40 -> 1010
    //   973: ldc_w 502
    //   976: new 504	java/lang/StringBuilder
    //   979: dup
    //   980: ldc_w 652
    //   983: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   986: ldc_w 650
    //   989: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   992: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   995: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   998: invokestatic 519	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1001: ldc_w 650
    //   1004: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1007: invokestatic 655	com/tapjoy/TapjoyConnectCore:setUserID	(Ljava/lang/String;)V
    //   1010: ldc_w 657
    //   1013: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1016: invokestatic 660	com/tapjoy/TapjoyUtil:getRedirectDomain	(Ljava/lang/String;)Ljava/lang/String;
    //   1019: putstatic 238	com/tapjoy/TapjoyConnectCore:S	Ljava/lang/String;
    //   1022: new 504	java/lang/StringBuilder
    //   1025: dup
    //   1026: ldc_w 506
    //   1029: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1032: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1035: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1038: astore 4
    //   1040: ldc_w 500
    //   1043: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1046: ifnull +536 -> 1582
    //   1049: ldc_w 500
    //   1052: invokestatic 333	com/tapjoy/TapjoyConnectCore:getConnectFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1055: invokevirtual 489	java/lang/String:length	()I
    //   1058: ifle +524 -> 1582
    //   1061: ldc_w 662
    //   1064: astore_1
    //   1065: ldc_w 502
    //   1068: aload 4
    //   1070: aload_1
    //   1071: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1074: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1077: invokestatic 519	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1080: getstatic 279	com/tapjoy/TapjoyConnectCore:an	Ljava/util/Hashtable;
    //   1083: ifnull +6 -> 1089
    //   1086: invokestatic 664	com/tapjoy/TapjoyConnectCore:h	()V
    //   1089: aload_0
    //   1090: invokevirtual 667	com/tapjoy/TapjoyConnectCore:callConnect	()V
    //   1093: aload_0
    //   1094: iconst_1
    //   1095: putfield 307	com/tapjoy/TapjoyConnectCore:ai	Z
    //   1098: return
    //   1099: astore_1
    //   1100: new 293	com/tapjoy/TapjoyException
    //   1103: dup
    //   1104: aload_1
    //   1105: invokevirtual 670	android/content/pm/PackageManager$NameNotFoundException:getMessage	()Ljava/lang/String;
    //   1108: invokespecial 671	com/tapjoy/TapjoyException:<init>	(Ljava/lang/String;)V
    //   1111: athrow
    //   1112: astore_1
    //   1113: new 504	java/lang/StringBuilder
    //   1116: dup
    //   1117: ldc_w 673
    //   1120: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1123: aload_1
    //   1124: invokevirtual 674	com/tapjoy/TapjoyIntegrationException:getMessage	()Ljava/lang/String;
    //   1127: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1130: pop
    //   1131: invokestatic 676	com/tapjoy/TapjoyConnectCore:e	()V
    //   1134: return
    //   1135: astore_1
    //   1136: ldc_w 502
    //   1139: new 504	java/lang/StringBuilder
    //   1142: dup
    //   1143: ldc_w 678
    //   1146: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1149: aload_1
    //   1150: invokevirtual 679	java/lang/Exception:toString	()Ljava/lang/String;
    //   1153: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1156: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1159: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1162: goto -863 -> 299
    //   1165: astore_1
    //   1166: new 504	java/lang/StringBuilder
    //   1169: dup
    //   1170: ldc_w 681
    //   1173: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1176: aload_1
    //   1177: invokevirtual 682	com/tapjoy/TapjoyException:getMessage	()Ljava/lang/String;
    //   1180: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1183: pop
    //   1184: invokestatic 676	com/tapjoy/TapjoyConnectCore:e	()V
    //   1187: return
    //   1188: astore_1
    //   1189: ldc_w 502
    //   1192: new 504	java/lang/StringBuilder
    //   1195: dup
    //   1196: ldc_w 684
    //   1199: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1202: aload_1
    //   1203: invokevirtual 679	java/lang/Exception:toString	()Ljava/lang/String;
    //   1206: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1209: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1212: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1215: goto -849 -> 366
    //   1218: ldc_w 502
    //   1221: ldc_w 686
    //   1224: invokestatic 519	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1227: goto -861 -> 366
    //   1230: aload_1
    //   1231: invokevirtual 689	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1234: putstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1237: goto -746 -> 491
    //   1240: astore_1
    //   1241: ldc_w 502
    //   1244: new 504	java/lang/StringBuilder
    //   1247: dup
    //   1248: ldc_w 691
    //   1251: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1254: aload_1
    //   1255: invokevirtual 679	java/lang/Exception:toString	()Ljava/lang/String;
    //   1258: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1261: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1264: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1267: aconst_null
    //   1268: putstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1271: goto -668 -> 603
    //   1274: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1277: invokevirtual 489	java/lang/String:length	()I
    //   1280: ifeq +27 -> 1307
    //   1283: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1286: ldc_w 693
    //   1289: invokevirtual 341	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1292: ifne +15 -> 1307
    //   1295: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1298: ldc_w 695
    //   1301: invokevirtual 341	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1304: ifeq +17 -> 1321
    //   1307: ldc_w 502
    //   1310: ldc_w 697
    //   1313: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1316: iconst_0
    //   1317: istore_2
    //   1318: goto -785 -> 533
    //   1321: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1324: invokestatic 703	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1327: invokevirtual 706	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1330: putstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1333: iconst_1
    //   1334: istore_2
    //   1335: goto -802 -> 533
    //   1338: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1341: invokevirtual 489	java/lang/String:length	()I
    //   1344: ifeq +39 -> 1383
    //   1347: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1350: ldc_w 693
    //   1353: invokevirtual 341	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1356: ifne +27 -> 1383
    //   1359: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1362: ldc_w 695
    //   1365: invokevirtual 341	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1368: ifne +15 -> 1383
    //   1371: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1374: ldc_w 708
    //   1377: invokevirtual 341	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1380: ifeq +15 -> 1395
    //   1383: ldc_w 502
    //   1386: ldc_w 710
    //   1389: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1392: goto -789 -> 603
    //   1395: getstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1398: invokestatic 703	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1401: invokevirtual 706	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1404: putstatic 180	com/tapjoy/TapjoyConnectCore:q	Ljava/lang/String;
    //   1407: goto -804 -> 603
    //   1410: ldc_w 502
    //   1413: ldc_w 712
    //   1416: invokestatic 519	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1419: goto -816 -> 603
    //   1422: astore_1
    //   1423: ldc_w 502
    //   1426: new 504	java/lang/StringBuilder
    //   1429: dup
    //   1430: ldc_w 714
    //   1433: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1436: aload_1
    //   1437: invokevirtual 679	java/lang/Exception:toString	()Ljava/lang/String;
    //   1440: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1443: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1446: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1449: goto -743 -> 706
    //   1452: astore_1
    //   1453: ldc_w 502
    //   1456: new 504	java/lang/StringBuilder
    //   1459: dup
    //   1460: ldc_w 716
    //   1463: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1466: aload_1
    //   1467: invokevirtual 679	java/lang/Exception:toString	()Ljava/lang/String;
    //   1470: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1473: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1476: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1479: goto -761 -> 718
    //   1482: astore_1
    //   1483: ldc_w 502
    //   1486: new 504	java/lang/StringBuilder
    //   1489: dup
    //   1490: ldc_w 718
    //   1493: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1496: aload_1
    //   1497: invokevirtual 679	java/lang/Exception:toString	()Ljava/lang/String;
    //   1500: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1503: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1506: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1509: goto -755 -> 754
    //   1512: aload_1
    //   1513: ldc_w 720
    //   1516: invokevirtual 341	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1519: ifeq +13 -> 1532
    //   1522: ldc_w 722
    //   1525: invokestatic 724	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   1528: istore_3
    //   1529: goto -644 -> 885
    //   1532: aload_1
    //   1533: ldc_w 726
    //   1536: invokevirtual 341	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1539: ifeq +53 -> 1592
    //   1542: ldc_w 728
    //   1545: invokestatic 724	com/tapjoy/TapjoyConnectCore:f	(Ljava/lang/String;)Z
    //   1548: istore_3
    //   1549: goto -664 -> 885
    //   1552: astore_1
    //   1553: ldc_w 502
    //   1556: new 504	java/lang/StringBuilder
    //   1559: dup
    //   1560: ldc_w 730
    //   1563: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1566: aload_1
    //   1567: invokevirtual 679	java/lang/Exception:toString	()Ljava/lang/String;
    //   1570: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1573: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1576: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1579: goto -690 -> 889
    //   1582: ldc -82
    //   1584: astore_1
    //   1585: goto -520 -> 1065
    //   1588: astore_1
    //   1589: goto -1435 -> 154
    //   1592: iconst_0
    //   1593: istore_3
    //   1594: goto -709 -> 885
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1597	0	this	TapjoyConnectCore
    //   0	1597	1	paramContext	Context
    //   127	1208	2	i1	int
    //   305	1289	3	bool	boolean
    //   625	444	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   199	218	1099	android/content/pm/PackageManager$NameNotFoundException
    //   61	77	1112	com/tapjoy/TapjoyIntegrationException
    //   77	105	1112	com/tapjoy/TapjoyIntegrationException
    //   105	136	1112	com/tapjoy/TapjoyIntegrationException
    //   136	154	1112	com/tapjoy/TapjoyIntegrationException
    //   154	169	1112	com/tapjoy/TapjoyIntegrationException
    //   169	186	1112	com/tapjoy/TapjoyIntegrationException
    //   190	199	1112	com/tapjoy/TapjoyIntegrationException
    //   199	218	1112	com/tapjoy/TapjoyIntegrationException
    //   218	260	1112	com/tapjoy/TapjoyIntegrationException
    //   260	299	1112	com/tapjoy/TapjoyIntegrationException
    //   299	306	1112	com/tapjoy/TapjoyIntegrationException
    //   310	323	1112	com/tapjoy/TapjoyIntegrationException
    //   327	332	1112	com/tapjoy/TapjoyIntegrationException
    //   336	345	1112	com/tapjoy/TapjoyIntegrationException
    //   349	366	1112	com/tapjoy/TapjoyIntegrationException
    //   366	379	1112	com/tapjoy/TapjoyIntegrationException
    //   383	427	1112	com/tapjoy/TapjoyIntegrationException
    //   427	450	1112	com/tapjoy/TapjoyIntegrationException
    //   450	457	1112	com/tapjoy/TapjoyIntegrationException
    //   461	491	1112	com/tapjoy/TapjoyIntegrationException
    //   491	531	1112	com/tapjoy/TapjoyIntegrationException
    //   533	580	1112	com/tapjoy/TapjoyIntegrationException
    //   584	588	1112	com/tapjoy/TapjoyIntegrationException
    //   588	603	1112	com/tapjoy/TapjoyIntegrationException
    //   603	632	1112	com/tapjoy/TapjoyIntegrationException
    //   637	644	1112	com/tapjoy/TapjoyIntegrationException
    //   648	706	1112	com/tapjoy/TapjoyIntegrationException
    //   706	718	1112	com/tapjoy/TapjoyIntegrationException
    //   718	754	1112	com/tapjoy/TapjoyIntegrationException
    //   754	831	1112	com/tapjoy/TapjoyIntegrationException
    //   831	883	1112	com/tapjoy/TapjoyIntegrationException
    //   885	889	1112	com/tapjoy/TapjoyIntegrationException
    //   889	922	1112	com/tapjoy/TapjoyIntegrationException
    //   922	952	1112	com/tapjoy/TapjoyIntegrationException
    //   952	1010	1112	com/tapjoy/TapjoyIntegrationException
    //   1010	1061	1112	com/tapjoy/TapjoyIntegrationException
    //   1065	1089	1112	com/tapjoy/TapjoyIntegrationException
    //   1089	1098	1112	com/tapjoy/TapjoyIntegrationException
    //   1100	1112	1112	com/tapjoy/TapjoyIntegrationException
    //   1136	1162	1112	com/tapjoy/TapjoyIntegrationException
    //   1189	1215	1112	com/tapjoy/TapjoyIntegrationException
    //   1218	1227	1112	com/tapjoy/TapjoyIntegrationException
    //   1230	1237	1112	com/tapjoy/TapjoyIntegrationException
    //   1241	1271	1112	com/tapjoy/TapjoyIntegrationException
    //   1274	1307	1112	com/tapjoy/TapjoyIntegrationException
    //   1307	1316	1112	com/tapjoy/TapjoyIntegrationException
    //   1321	1333	1112	com/tapjoy/TapjoyIntegrationException
    //   1338	1383	1112	com/tapjoy/TapjoyIntegrationException
    //   1383	1392	1112	com/tapjoy/TapjoyIntegrationException
    //   1395	1407	1112	com/tapjoy/TapjoyIntegrationException
    //   1410	1419	1112	com/tapjoy/TapjoyIntegrationException
    //   1423	1449	1112	com/tapjoy/TapjoyIntegrationException
    //   1453	1479	1112	com/tapjoy/TapjoyIntegrationException
    //   1483	1509	1112	com/tapjoy/TapjoyIntegrationException
    //   1512	1529	1112	com/tapjoy/TapjoyIntegrationException
    //   1532	1549	1112	com/tapjoy/TapjoyIntegrationException
    //   1553	1579	1112	com/tapjoy/TapjoyIntegrationException
    //   260	299	1135	java/lang/Exception
    //   61	77	1165	com/tapjoy/TapjoyException
    //   77	105	1165	com/tapjoy/TapjoyException
    //   105	136	1165	com/tapjoy/TapjoyException
    //   136	154	1165	com/tapjoy/TapjoyException
    //   154	169	1165	com/tapjoy/TapjoyException
    //   169	186	1165	com/tapjoy/TapjoyException
    //   190	199	1165	com/tapjoy/TapjoyException
    //   199	218	1165	com/tapjoy/TapjoyException
    //   218	260	1165	com/tapjoy/TapjoyException
    //   260	299	1165	com/tapjoy/TapjoyException
    //   299	306	1165	com/tapjoy/TapjoyException
    //   310	323	1165	com/tapjoy/TapjoyException
    //   327	332	1165	com/tapjoy/TapjoyException
    //   336	345	1165	com/tapjoy/TapjoyException
    //   349	366	1165	com/tapjoy/TapjoyException
    //   366	379	1165	com/tapjoy/TapjoyException
    //   383	427	1165	com/tapjoy/TapjoyException
    //   427	450	1165	com/tapjoy/TapjoyException
    //   450	457	1165	com/tapjoy/TapjoyException
    //   461	491	1165	com/tapjoy/TapjoyException
    //   491	531	1165	com/tapjoy/TapjoyException
    //   533	580	1165	com/tapjoy/TapjoyException
    //   584	588	1165	com/tapjoy/TapjoyException
    //   588	603	1165	com/tapjoy/TapjoyException
    //   603	632	1165	com/tapjoy/TapjoyException
    //   637	644	1165	com/tapjoy/TapjoyException
    //   648	706	1165	com/tapjoy/TapjoyException
    //   706	718	1165	com/tapjoy/TapjoyException
    //   718	754	1165	com/tapjoy/TapjoyException
    //   754	831	1165	com/tapjoy/TapjoyException
    //   831	883	1165	com/tapjoy/TapjoyException
    //   885	889	1165	com/tapjoy/TapjoyException
    //   889	922	1165	com/tapjoy/TapjoyException
    //   922	952	1165	com/tapjoy/TapjoyException
    //   952	1010	1165	com/tapjoy/TapjoyException
    //   1010	1061	1165	com/tapjoy/TapjoyException
    //   1065	1089	1165	com/tapjoy/TapjoyException
    //   1089	1098	1165	com/tapjoy/TapjoyException
    //   1100	1112	1165	com/tapjoy/TapjoyException
    //   1136	1162	1165	com/tapjoy/TapjoyException
    //   1189	1215	1165	com/tapjoy/TapjoyException
    //   1218	1227	1165	com/tapjoy/TapjoyException
    //   1230	1237	1165	com/tapjoy/TapjoyException
    //   1241	1271	1165	com/tapjoy/TapjoyException
    //   1274	1307	1165	com/tapjoy/TapjoyException
    //   1307	1316	1165	com/tapjoy/TapjoyException
    //   1321	1333	1165	com/tapjoy/TapjoyException
    //   1338	1383	1165	com/tapjoy/TapjoyException
    //   1383	1392	1165	com/tapjoy/TapjoyException
    //   1395	1407	1165	com/tapjoy/TapjoyException
    //   1410	1419	1165	com/tapjoy/TapjoyException
    //   1423	1449	1165	com/tapjoy/TapjoyException
    //   1453	1479	1165	com/tapjoy/TapjoyException
    //   1483	1509	1165	com/tapjoy/TapjoyException
    //   1512	1529	1165	com/tapjoy/TapjoyException
    //   1532	1549	1165	com/tapjoy/TapjoyException
    //   1553	1579	1165	com/tapjoy/TapjoyException
    //   310	323	1188	java/lang/Exception
    //   327	332	1188	java/lang/Exception
    //   336	345	1188	java/lang/Exception
    //   349	366	1188	java/lang/Exception
    //   461	491	1240	java/lang/Exception
    //   491	531	1240	java/lang/Exception
    //   533	580	1240	java/lang/Exception
    //   584	588	1240	java/lang/Exception
    //   588	603	1240	java/lang/Exception
    //   1230	1237	1240	java/lang/Exception
    //   1274	1307	1240	java/lang/Exception
    //   1307	1316	1240	java/lang/Exception
    //   1321	1333	1240	java/lang/Exception
    //   1338	1383	1240	java/lang/Exception
    //   1383	1392	1240	java/lang/Exception
    //   1395	1407	1240	java/lang/Exception
    //   648	706	1422	java/lang/Exception
    //   706	718	1452	java/lang/Exception
    //   718	754	1482	java/lang/Exception
    //   831	883	1552	java/lang/Exception
    //   885	889	1552	java/lang/Exception
    //   1512	1529	1552	java/lang/Exception
    //   1532	1549	1552	java/lang/Exception
    //   136	154	1588	java/lang/Exception
  }
  
  private static String a(long paramLong)
  {
    try
    {
      String str = TapjoyUtil.SHA256(x + ":" + o() + ":" + paramLong + ":" + O);
      return str;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", "getVerifier ERROR: " + localException.toString());
    }
    return "";
  }
  
  private static String a(long paramLong, String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(x + ":" + o() + ":" + paramLong + ":" + O + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      TapjoyLog.e("TapjoyConnect", "getVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  private static void a(List paramList)
  {
    try
    {
      ao = "";
      Iterator localIterator = ak.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (((localApplicationInfo.flags & 0x1) != 1) && (paramList.contains(localApplicationInfo.packageName)))
        {
          TapjoyLog.i("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
          if (ao.length() > 0) {
            ao += ",";
          }
          ao += localApplicationInfo.packageName;
        }
      }
    }
    finally {}
  }
  
  private static void a(Properties paramProperties)
  {
    Enumeration localEnumeration = paramProperties.keys();
    while (localEnumeration.hasMoreElements()) {
      try
      {
        String str = (String)localEnumeration.nextElement();
        b(str, (String)paramProperties.get(str));
      }
      catch (ClassCastException localClassCastException)
      {
        TapjoyLog.e("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
      }
    }
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    FeatureInfo[] arrayOfFeatureInfo = ak.getSystemAvailableFeatures();
    int i2 = arrayOfFeatureInfo.length;
    int i1 = 0;
    while (i1 < i2)
    {
      if (arrayOfFeatureInfo[i1].name.matches(paramString1))
      {
        if (paramString2 == null) {}
        while (ak.checkPermission(paramString2, g.getPackageName()) == 0) {
          return true;
        }
        return false;
      }
      i1 += 1;
    }
    return false;
  }
  
  private static void b(String paramString1, String paramString2)
  {
    String str;
    if (!paramString1.equals("TJC_OPTION_SERVICE_URL"))
    {
      str = paramString2;
      if (!paramString1.equals("TJC_OPTION_PLACEMENT_SERVICE_URL")) {}
    }
    else
    {
      str = paramString2;
      if (!paramString2.endsWith("/")) {
        str = paramString2 + "/";
      }
    }
    an.put(paramString1, str);
  }
  
  /* Error */
  private static boolean d(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 873	com/tapjoy/internal/bu:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bu;
    //   4: astore_0
    //   5: aload_0
    //   6: astore_3
    //   7: aload_0
    //   8: invokevirtual 876	com/tapjoy/internal/bu:e	()Ljava/util/Map;
    //   11: astore 5
    //   13: aload_0
    //   14: astore_3
    //   15: aload 5
    //   17: ldc_w 878
    //   20: invokeinterface 881 2 0
    //   25: checkcast 337	java/lang/String
    //   28: invokestatic 885	com/tapjoy/internal/cw:a	(Ljava/lang/String;)Ljava/lang/String;
    //   31: astore 4
    //   33: aload_0
    //   34: astore_3
    //   35: aload 5
    //   37: ldc_w 887
    //   40: invokeinterface 881 2 0
    //   45: checkcast 337	java/lang/String
    //   48: invokestatic 885	com/tapjoy/internal/cw:a	(Ljava/lang/String;)Ljava/lang/String;
    //   51: astore 7
    //   53: aload_0
    //   54: astore_3
    //   55: aload 5
    //   57: ldc_w 889
    //   60: invokeinterface 881 2 0
    //   65: checkcast 337	java/lang/String
    //   68: invokestatic 885	com/tapjoy/internal/cw:a	(Ljava/lang/String;)Ljava/lang/String;
    //   71: astore 8
    //   73: aload_0
    //   74: astore_3
    //   75: aload 5
    //   77: ldc_w 891
    //   80: invokeinterface 881 2 0
    //   85: checkcast 337	java/lang/String
    //   88: invokestatic 885	com/tapjoy/internal/cw:a	(Ljava/lang/String;)Ljava/lang/String;
    //   91: astore 9
    //   93: aload_0
    //   94: astore_3
    //   95: aload 5
    //   97: ldc_w 893
    //   100: invokeinterface 881 2 0
    //   105: checkcast 337	java/lang/String
    //   108: invokestatic 885	com/tapjoy/internal/cw:a	(Ljava/lang/String;)Ljava/lang/String;
    //   111: astore 6
    //   113: aload_0
    //   114: astore_3
    //   115: aload 5
    //   117: ldc_w 895
    //   120: invokeinterface 881 2 0
    //   125: checkcast 897	java/lang/Boolean
    //   128: astore 5
    //   130: aload 5
    //   132: ifnull +13 -> 145
    //   135: aload_0
    //   136: astore_3
    //   137: aload 5
    //   139: invokevirtual 900	java/lang/Boolean:booleanValue	()Z
    //   142: putstatic 287	com/tapjoy/TapjoyConnectCore:aY	Z
    //   145: aload_0
    //   146: astore_3
    //   147: new 902	com/tapjoy/internal/ApiKeyDecoded
    //   150: dup
    //   151: aload 8
    //   153: invokespecial 903	com/tapjoy/internal/ApiKeyDecoded:<init>	(Ljava/lang/String;)V
    //   156: astore 10
    //   158: aload_0
    //   159: astore_3
    //   160: aload 10
    //   162: invokevirtual 907	com/tapjoy/internal/ApiKeyDecoded:getKeyUsage	()Lcom/tapjoy/internal/ApiKeyDecoded$KeyUsage;
    //   165: getstatic 913	com/tapjoy/internal/ApiKeyDecoded$KeyUsage:RPC_ANALYTICS	Lcom/tapjoy/internal/ApiKeyDecoded$KeyUsage;
    //   168: if_acmpeq +33 -> 201
    //   171: aload_0
    //   172: astore_3
    //   173: new 866	java/io/IOException
    //   176: dup
    //   177: ldc_w 915
    //   180: invokespecial 916	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   183: athrow
    //   184: astore_3
    //   185: ldc_w 502
    //   188: aload_3
    //   189: invokevirtual 917	java/io/IOException:getMessage	()Ljava/lang/String;
    //   192: invokestatic 919	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   195: aload_0
    //   196: invokestatic 924	com/tapjoy/internal/de:a	(Ljava/io/Closeable;)V
    //   199: iconst_0
    //   200: ireturn
    //   201: aload_0
    //   202: astore_3
    //   203: aload 10
    //   205: invokevirtual 927	com/tapjoy/internal/ApiKeyDecoded:getAppId	()Ljava/lang/String;
    //   208: invokestatic 930	com/tapjoy/internal/ApiKeyDecoded:get5RocksAppId	(Ljava/lang/String;)Ljava/lang/String;
    //   211: astore 5
    //   213: aload_0
    //   214: astore_3
    //   215: aload 10
    //   217: invokevirtual 933	com/tapjoy/internal/ApiKeyDecoded:getSecretKey	()Ljava/lang/String;
    //   220: astore 10
    //   222: aload 4
    //   224: ifnonnull +224 -> 448
    //   227: aload 5
    //   229: astore 4
    //   231: aload_0
    //   232: astore_3
    //   233: invokestatic 938	com/tapjoy/internal/gd:a	()Lcom/tapjoy/internal/gd;
    //   236: getstatic 142	com/tapjoy/TapjoyConnectCore:g	Landroid/content/Context;
    //   239: aload 8
    //   241: ldc_w 426
    //   244: ldc_w 940
    //   247: aload 5
    //   249: aload 10
    //   251: invokevirtual 943	com/tapjoy/internal/gd:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   254: aload_0
    //   255: astore_3
    //   256: aload 4
    //   258: putstatic 252	com/tapjoy/TapjoyConnectCore:Z	Ljava/lang/String;
    //   261: aload_0
    //   262: astore_3
    //   263: aload 7
    //   265: putstatic 254	com/tapjoy/TapjoyConnectCore:aa	Ljava/lang/String;
    //   268: aload_0
    //   269: astore_3
    //   270: aload 8
    //   272: putstatic 256	com/tapjoy/TapjoyConnectCore:ab	Ljava/lang/String;
    //   275: aload_0
    //   276: astore_3
    //   277: aload 9
    //   279: putstatic 258	com/tapjoy/TapjoyConnectCore:ac	Ljava/lang/String;
    //   282: aload_0
    //   283: astore_3
    //   284: new 605	java/util/ArrayList
    //   287: dup
    //   288: invokespecial 944	java/util/ArrayList:<init>	()V
    //   291: astore 4
    //   293: aload 6
    //   295: ifnull +64 -> 359
    //   298: aload_0
    //   299: astore_3
    //   300: aload 6
    //   302: ldc_w 772
    //   305: invokevirtual 948	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   308: astore 5
    //   310: aload_0
    //   311: astore_3
    //   312: aload 5
    //   314: arraylength
    //   315: istore_2
    //   316: iconst_0
    //   317: istore_1
    //   318: iload_1
    //   319: iload_2
    //   320: if_icmpge +39 -> 359
    //   323: aload_0
    //   324: astore_3
    //   325: aload 5
    //   327: iload_1
    //   328: aaload
    //   329: invokevirtual 851	java/lang/String:trim	()Ljava/lang/String;
    //   332: astore 6
    //   334: aload_0
    //   335: astore_3
    //   336: aload 6
    //   338: invokevirtual 489	java/lang/String:length	()I
    //   341: ifle +110 -> 451
    //   344: aload_0
    //   345: astore_3
    //   346: aload 4
    //   348: aload 6
    //   350: invokeinterface 949 2 0
    //   355: pop
    //   356: goto +95 -> 451
    //   359: aload_0
    //   360: astore_3
    //   361: aload 4
    //   363: invokeinterface 952 1 0
    //   368: ifne +10 -> 378
    //   371: aload_0
    //   372: astore_3
    //   373: aload 4
    //   375: invokestatic 856	com/tapjoy/TapjoyConnectCore:a	(Ljava/util/List;)V
    //   378: aload_0
    //   379: astore_3
    //   380: aload_0
    //   381: invokevirtual 955	com/tapjoy/internal/bu:close	()V
    //   384: aconst_null
    //   385: invokestatic 924	com/tapjoy/internal/de:a	(Ljava/io/Closeable;)V
    //   388: iconst_1
    //   389: ireturn
    //   390: astore 4
    //   392: aconst_null
    //   393: astore_0
    //   394: aload_0
    //   395: astore_3
    //   396: ldc_w 502
    //   399: aload 4
    //   401: invokevirtual 956	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   404: invokestatic 919	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   407: aload_0
    //   408: invokestatic 924	com/tapjoy/internal/de:a	(Ljava/io/Closeable;)V
    //   411: goto -212 -> 199
    //   414: astore_0
    //   415: aconst_null
    //   416: astore_3
    //   417: aload_3
    //   418: invokestatic 924	com/tapjoy/internal/de:a	(Ljava/io/Closeable;)V
    //   421: aload_0
    //   422: athrow
    //   423: astore_0
    //   424: goto -7 -> 417
    //   427: astore 4
    //   429: aload_0
    //   430: astore_3
    //   431: aload 4
    //   433: astore_0
    //   434: goto -17 -> 417
    //   437: astore 4
    //   439: goto -45 -> 394
    //   442: astore_3
    //   443: aconst_null
    //   444: astore_0
    //   445: goto -260 -> 185
    //   448: goto -217 -> 231
    //   451: iload_1
    //   452: iconst_1
    //   453: iadd
    //   454: istore_1
    //   455: goto -137 -> 318
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	458	0	paramString	String
    //   317	138	1	i1	int
    //   315	6	2	i2	int
    //   6	167	3	str1	String
    //   184	5	3	localIOException1	java.io.IOException
    //   202	229	3	str2	String
    //   442	1	3	localIOException2	java.io.IOException
    //   31	343	4	localObject1	Object
    //   390	10	4	localRuntimeException1	RuntimeException
    //   427	5	4	localObject2	Object
    //   437	1	4	localRuntimeException2	RuntimeException
    //   11	315	5	localObject3	Object
    //   111	238	6	str3	String
    //   51	213	7	str4	String
    //   71	200	8	str5	String
    //   91	187	9	str6	String
    //   156	94	10	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   7	13	184	java/io/IOException
    //   15	33	184	java/io/IOException
    //   35	53	184	java/io/IOException
    //   55	73	184	java/io/IOException
    //   75	93	184	java/io/IOException
    //   95	113	184	java/io/IOException
    //   115	130	184	java/io/IOException
    //   137	145	184	java/io/IOException
    //   147	158	184	java/io/IOException
    //   160	171	184	java/io/IOException
    //   173	184	184	java/io/IOException
    //   203	213	184	java/io/IOException
    //   215	222	184	java/io/IOException
    //   233	254	184	java/io/IOException
    //   256	261	184	java/io/IOException
    //   263	268	184	java/io/IOException
    //   270	275	184	java/io/IOException
    //   277	282	184	java/io/IOException
    //   284	293	184	java/io/IOException
    //   300	310	184	java/io/IOException
    //   312	316	184	java/io/IOException
    //   325	334	184	java/io/IOException
    //   336	344	184	java/io/IOException
    //   346	356	184	java/io/IOException
    //   361	371	184	java/io/IOException
    //   373	378	184	java/io/IOException
    //   380	384	184	java/io/IOException
    //   0	5	390	java/lang/RuntimeException
    //   0	5	414	finally
    //   7	13	423	finally
    //   15	33	423	finally
    //   35	53	423	finally
    //   55	73	423	finally
    //   75	93	423	finally
    //   95	113	423	finally
    //   115	130	423	finally
    //   137	145	423	finally
    //   147	158	423	finally
    //   160	171	423	finally
    //   173	184	423	finally
    //   203	213	423	finally
    //   215	222	423	finally
    //   233	254	423	finally
    //   256	261	423	finally
    //   263	268	423	finally
    //   270	275	423	finally
    //   277	282	423	finally
    //   284	293	423	finally
    //   300	310	423	finally
    //   312	316	423	finally
    //   325	334	423	finally
    //   336	344	423	finally
    //   346	356	423	finally
    //   361	371	423	finally
    //   373	378	423	finally
    //   380	384	423	finally
    //   396	407	423	finally
    //   185	195	427	finally
    //   7	13	437	java/lang/RuntimeException
    //   15	33	437	java/lang/RuntimeException
    //   35	53	437	java/lang/RuntimeException
    //   55	73	437	java/lang/RuntimeException
    //   75	93	437	java/lang/RuntimeException
    //   95	113	437	java/lang/RuntimeException
    //   115	130	437	java/lang/RuntimeException
    //   137	145	437	java/lang/RuntimeException
    //   147	158	437	java/lang/RuntimeException
    //   160	171	437	java/lang/RuntimeException
    //   173	184	437	java/lang/RuntimeException
    //   203	213	437	java/lang/RuntimeException
    //   215	222	437	java/lang/RuntimeException
    //   233	254	437	java/lang/RuntimeException
    //   256	261	437	java/lang/RuntimeException
    //   263	268	437	java/lang/RuntimeException
    //   270	275	437	java/lang/RuntimeException
    //   277	282	437	java/lang/RuntimeException
    //   284	293	437	java/lang/RuntimeException
    //   300	310	437	java/lang/RuntimeException
    //   312	316	437	java/lang/RuntimeException
    //   325	334	437	java/lang/RuntimeException
    //   336	344	437	java/lang/RuntimeException
    //   346	356	437	java/lang/RuntimeException
    //   361	371	437	java/lang/RuntimeException
    //   373	378	437	java/lang/RuntimeException
    //   380	384	437	java/lang/RuntimeException
    //   0	5	442	java/io/IOException
  }
  
  private static void e()
  {
    if (!cw.c(P)) {
      gd.a().a(g, h, "11.2.2", "https://rpc.tapjoy.com/", P, O);
    }
    if (k != null) {
      k.onConnectFailure();
    }
  }
  
  /* Error */
  private static boolean e(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 873	com/tapjoy/internal/bu:b	(Ljava/lang/String;)Lcom/tapjoy/internal/bu;
    //   4: astore_1
    //   5: aload_1
    //   6: astore_0
    //   7: aload_1
    //   8: invokevirtual 965	com/tapjoy/internal/bu:a	()Z
    //   11: ifeq +32 -> 43
    //   14: aload_1
    //   15: astore_0
    //   16: aload_1
    //   17: invokevirtual 967	com/tapjoy/internal/bu:t	()V
    //   20: aload_1
    //   21: astore_0
    //   22: ldc_w 502
    //   25: ldc_w 969
    //   28: invokestatic 519	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   31: aload_1
    //   32: astore_0
    //   33: aload_1
    //   34: invokevirtual 955	com/tapjoy/internal/bu:close	()V
    //   37: aconst_null
    //   38: invokestatic 924	com/tapjoy/internal/de:a	(Ljava/io/Closeable;)V
    //   41: iconst_1
    //   42: ireturn
    //   43: aload_1
    //   44: astore_0
    //   45: aload_1
    //   46: invokevirtual 955	com/tapjoy/internal/bu:close	()V
    //   49: aconst_null
    //   50: invokestatic 924	com/tapjoy/internal/de:a	(Ljava/io/Closeable;)V
    //   53: ldc_w 502
    //   56: ldc_w 971
    //   59: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   62: iconst_0
    //   63: ireturn
    //   64: astore_2
    //   65: aconst_null
    //   66: astore_1
    //   67: aload_1
    //   68: astore_0
    //   69: ldc_w 502
    //   72: aload_2
    //   73: invokevirtual 917	java/io/IOException:getMessage	()Ljava/lang/String;
    //   76: invokestatic 919	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   79: aload_1
    //   80: invokestatic 924	com/tapjoy/internal/de:a	(Ljava/io/Closeable;)V
    //   83: goto -30 -> 53
    //   86: astore_2
    //   87: aconst_null
    //   88: astore_1
    //   89: aload_1
    //   90: astore_0
    //   91: ldc_w 502
    //   94: aload_2
    //   95: invokevirtual 956	java/lang/RuntimeException:getMessage	()Ljava/lang/String;
    //   98: invokestatic 919	com/tapjoy/TapjoyLog:v	(Ljava/lang/String;Ljava/lang/String;)V
    //   101: aload_1
    //   102: invokestatic 924	com/tapjoy/internal/de:a	(Ljava/io/Closeable;)V
    //   105: goto -52 -> 53
    //   108: astore_1
    //   109: aconst_null
    //   110: astore_0
    //   111: aload_0
    //   112: invokestatic 924	com/tapjoy/internal/de:a	(Ljava/io/Closeable;)V
    //   115: aload_1
    //   116: athrow
    //   117: astore_1
    //   118: goto -7 -> 111
    //   121: astore_2
    //   122: goto -33 -> 89
    //   125: astore_2
    //   126: goto -59 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	129	0	paramString	String
    //   4	98	1	localBu	com.tapjoy.internal.bu
    //   108	8	1	localObject1	Object
    //   117	1	1	localObject2	Object
    //   64	9	2	localIOException1	java.io.IOException
    //   86	9	2	localRuntimeException1	RuntimeException
    //   121	1	2	localRuntimeException2	RuntimeException
    //   125	1	2	localIOException2	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   0	5	64	java/io/IOException
    //   0	5	86	java/lang/RuntimeException
    //   0	5	108	finally
    //   7	14	117	finally
    //   16	20	117	finally
    //   22	31	117	finally
    //   33	37	117	finally
    //   45	49	117	finally
    //   69	79	117	finally
    //   91	101	117	finally
    //   7	14	121	java/lang/RuntimeException
    //   16	20	121	java/lang/RuntimeException
    //   22	31	121	java/lang/RuntimeException
    //   33	37	121	java/lang/RuntimeException
    //   45	49	121	java/lang/RuntimeException
    //   7	14	125	java/io/IOException
    //   16	20	125	java/io/IOException
    //   22	31	125	java/io/IOException
    //   33	37	125	java/io/IOException
    //   45	49	125	java/io/IOException
  }
  
  private static Map f()
  {
    HashMap localHashMap1 = new HashMap();
    HashMap localHashMap2 = new HashMap();
    HashMap localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "plugin", Q, true);
    TapjoyUtil.safePut(localHashMap3, "sdk_type", R, true);
    TapjoyUtil.safePut(localHashMap3, "app_id", x, true);
    TapjoyUtil.safePut(localHashMap3, "library_version", z, true);
    TapjoyUtil.safePut(localHashMap3, "library_revision", "ab58402", true);
    TapjoyUtil.safePut(localHashMap3, "bridge_version", A, true);
    TapjoyUtil.safePut(localHashMap3, "app_version", y, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    TapjoyUtil.safePut(localHashMap3, "device_name", t, true);
    TapjoyUtil.safePut(localHashMap3, "platform", G, true);
    TapjoyUtil.safePut(localHashMap3, "os_version", w, true);
    TapjoyUtil.safePut(localHashMap3, "device_manufacturer", u, true);
    TapjoyUtil.safePut(localHashMap3, "device_type", v, true);
    TapjoyUtil.safePut(localHashMap3, "screen_layout_size", D, true);
    TapjoyUtil.safePut(localHashMap3, "device_location", String.valueOf(F), true);
    TapjoyUtil.safePut(localHashMap3, "store_name", N, true);
    TapjoyUtil.safePut(localHashMap3, "store_view", String.valueOf(Y), true);
    TapjoyUtil.safePut(localHashMap3, "carrier_name", H, true);
    TapjoyUtil.safePut(localHashMap3, "carrier_country_code", I, true);
    TapjoyUtil.safePut(localHashMap3, "mobile_network_code", K, true);
    TapjoyUtil.safePut(localHashMap3, "mobile_country_code", J, true);
    TapjoyUtil.safePut(localHashMap3, "country_code", Locale.getDefault().getCountry(), true);
    TapjoyUtil.safePut(localHashMap3, "language_code", Locale.getDefault().getLanguage(), true);
    L = getConnectionType();
    TapjoyUtil.safePut(localHashMap3, "connection_type", L, true);
    M = getConnectionSubType();
    TapjoyUtil.safePut(localHashMap3, "connection_subtype", M, true);
    TapjoyUtil.safePut(localHashMap3, "screen_density", B, true);
    localHashMap2.putAll(localHashMap3);
    localHashMap3 = new HashMap();
    if (l())
    {
      TapjoyUtil.safePut(localHashMap3, "advertising_id", c, true);
      TapjoyUtil.safePut(localHashMap3, "ad_tracking_enabled", String.valueOf(d), true);
    }
    if (!m())
    {
      TapjoyUtil.safePut(localHashMap3, "android_id", n, true);
      TapjoyUtil.safePut(localHashMap3, "udid", q, true);
      TapjoyUtil.safePut(localHashMap3, "mac_address", r, true);
    }
    TapjoyUtil.safePut(localHashMap3, "threatmetrix_session_id", p, true);
    TapjoyUtil.safePut(localHashMap3, "install_id", s, true);
    TapjoyUtil.safePut(localHashMap3, "publisher_user_id", E, true);
    TapjoyUtil.safePut(localHashMap3, "ad_id_check_disabled", e, true);
    TapjoyUtil.safePut(localHashMap3, "persistent_ids_disabled", f, true);
    if (a != 0) {
      TapjoyUtil.safePut(localHashMap3, "packaged_gps_version", Integer.toString(a), true);
    }
    if (b != 0) {
      TapjoyUtil.safePut(localHashMap3, "device_gps_version", Integer.toString(b), true);
    }
    if ((o == null) || (o.length() == 0) || (System.currentTimeMillis() - ah > 1800000L)) {
      o = n();
    }
    for (;;)
    {
      TapjoyUtil.safePut(localHashMap3, "session_id", o, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "app_group_id", Z, true);
      TapjoyUtil.safePut(localHashMap3, "store", aa, true);
      TapjoyUtil.safePut(localHashMap3, "analytics_api_key", ab, true);
      TapjoyUtil.safePut(localHashMap3, "managed_device_id", ac, true);
      localHashMap2.putAll(localHashMap3);
      if ((TapjoyCache.getInstance() != null) && (TapjoyCache.getInstance().getCachedOfferIDs() != null) && (TapjoyCache.getInstance().getCachedOfferIDs().length() > 0)) {
        TapjoyUtil.safePut(localHashMap2, "cached_ids", TapjoyCache.getInstance().getCachedOfferIDs(), true);
      }
      TapjoyUtil.safePut(localHashMap2, "display_multiplier", Float.toString(T), true);
      localHashMap1.putAll(localHashMap2);
      localHashMap2 = new HashMap();
      g();
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "analytics_id", aq, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_id", ar, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_sign", as, true);
      TapjoyUtil.safePut(localHashMap3, "display_d", aR, true);
      TapjoyUtil.safePut(localHashMap3, "display_w", aS, true);
      TapjoyUtil.safePut(localHashMap3, "display_h", aT, true);
      TapjoyUtil.safePut(localHashMap3, "country_sim", aU, true);
      TapjoyUtil.safePut(localHashMap3, "timezone", aV, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "pkg_ver", at, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_rev", au, true);
      TapjoyUtil.safePut(localHashMap3, "pkg_data_ver", av, true);
      TapjoyUtil.safePut(localHashMap3, "installer", aw, true);
      if (cw.c(N)) {
        TapjoyUtil.safePut(localHashMap3, "store_name", aW, true);
      }
      localHashMap2.putAll(localHashMap3);
      localHashMap3 = new HashMap();
      TapjoyUtil.safePut(localHashMap3, "installed", ax, true);
      TapjoyUtil.safePut(localHashMap3, "referrer", ay, true);
      TapjoyUtil.safePut(localHashMap3, "user_level", az, true);
      TapjoyUtil.safePut(localHashMap3, "friend_count", aA, true);
      TapjoyUtil.safePut(localHashMap3, "uv1", aB, true);
      TapjoyUtil.safePut(localHashMap3, "uv2", aC, true);
      TapjoyUtil.safePut(localHashMap3, "uv3", aD, true);
      TapjoyUtil.safePut(localHashMap3, "uv4", aE, true);
      TapjoyUtil.safePut(localHashMap3, "uv5", aF, true);
      TapjoyUtil.safePut(localHashMap3, "fq7", aG, true);
      TapjoyUtil.safePut(localHashMap3, "fq30", aH, true);
      TapjoyUtil.safePut(localHashMap3, "session_total_count", aI, true);
      TapjoyUtil.safePut(localHashMap3, "session_total_length", aJ, true);
      TapjoyUtil.safePut(localHashMap3, "session_last_at", aK, true);
      TapjoyUtil.safePut(localHashMap3, "session_last_length", aL, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_currency", aM, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_total_count", aN, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_total_price", aO, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_last_price", aP, true);
      TapjoyUtil.safePut(localHashMap3, "purchase_last_at", aQ, true);
      localHashMap2.putAll(localHashMap3);
      localHashMap1.putAll(localHashMap2);
      return localHashMap1;
      ah = System.currentTimeMillis();
    }
  }
  
  private static boolean f(String paramString)
  {
    Iterator localIterator = ak.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private static void g()
  {
    gt.n localN = gd.a(g).b(true);
    aq = localN.f().h();
    ar = localN.f().B();
    as = localN.f().D();
    aR = localN.f().r();
    aS = localN.f().t();
    aT = localN.f().v();
    aU = localN.f().H();
    aV = localN.f().z();
    at = localN.h().f();
    au = localN.h().h();
    av = localN.h().j();
    aw = localN.h().l();
    aW = localN.h().n();
    ax = localN.j().f();
    ay = localN.j().h();
    az = localN.j().N();
    aA = localN.j().P();
    aB = localN.j().R();
    aC = localN.j().T();
    aD = localN.j().V();
    aE = localN.j().X();
    aF = localN.j().Z();
    aG = localN.j().j();
    aH = localN.j().l();
    aI = localN.j().p();
    aJ = localN.j().r();
    aK = localN.j().t();
    aL = localN.j().v();
    aM = localN.j().x();
    aN = localN.j().z();
    aO = localN.j().B();
    aP = localN.j().F();
    aQ = localN.j().D();
  }
  
  private static boolean g(String paramString)
  {
    Object localObject = new Intent("android.intent.action.SEND");
    ((Intent)localObject).setType("text/plain");
    localObject = ak.queryIntentActivities((Intent)localObject, 0).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static String getAndroidID()
  {
    return n;
  }
  
  public static String getAppID()
  {
    return x;
  }
  
  public static String getAwardCurrencyVerifier(long paramLong, int paramInt, String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(x + ":" + o() + ":" + paramLong + ":" + O + ":" + paramInt + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      TapjoyLog.e("TapjoyConnect", "getAwardCurrencyVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  public static String getCarrierName()
  {
    return H;
  }
  
  public static String getConnectFlagValue(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    if (an != null)
    {
      str1 = str2;
      if (an.get(paramString) != null) {
        str1 = an.get(paramString).toString();
      }
    }
    return str1;
  }
  
  public static String getConnectURL()
  {
    return "https://connect.tapjoy.com/";
  }
  
  public static String getConnectionSubType()
  {
    try
    {
      Object localObject = (ConnectivityManager)g.getSystemService("connectivity");
      if (localObject != null) {
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo().getSubtypeName();
      }
      return "";
    }
    catch (Exception localException1)
    {
      try
      {
        TapjoyLog.i("TapjoyConnect", "connection_sub_type: " + (String)localObject);
        return localObject;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      localException1 = localException1;
      localObject = "";
      TapjoyLog.e("TapjoyConnect", "getConnectionSubType error: " + localException1.toString());
      return localObject;
    }
  }
  
  public static String getConnectionType()
  {
    String str1 = "";
    String str3 = str1;
    String str4;
    for (;;)
    {
      try
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)g.getSystemService("connectivity");
        str4 = str1;
        if (localConnectivityManager == null) {
          break;
        }
        str3 = str1;
        str4 = str1;
        if (localConnectivityManager.getActiveNetworkInfo() == null) {
          break;
        }
        str3 = str1;
        switch (localConnectivityManager.getActiveNetworkInfo().getType())
        {
        case 1: 
          str3 = str1;
          TapjoyLog.i("TapjoyConnect", "connectivity: " + localConnectivityManager.getActiveNetworkInfo().getType());
          str3 = str1;
          TapjoyLog.i("TapjoyConnect", "connection_type: " + str1);
          str4 = str1;
        }
      }
      catch (Exception localException)
      {
        TapjoyLog.e("TapjoyConnect", "getConnectionType error: " + localException.toString());
        return str3;
      }
      str1 = "wifi";
      continue;
      String str2 = "mobile";
    }
    return str4;
  }
  
  public static Context getContext()
  {
    return g;
  }
  
  public static String getDeviceID()
  {
    return q;
  }
  
  public static float getDeviceScreenDensityScale()
  {
    return C;
  }
  
  public static Map getGenericURLParams()
  {
    Map localMap = f();
    TapjoyUtil.safePut(localMap, "app_id", x, true);
    return localMap;
  }
  
  public static String getHostURL()
  {
    return getConnectFlagValue("TJC_OPTION_SERVICE_URL");
  }
  
  public static TapjoyConnectCore getInstance()
  {
    return i;
  }
  
  public static String getMacAddress()
  {
    return r;
  }
  
  public static Map getOfflineLogs()
  {
    return g.getSharedPreferences("tapjoyOfflineLog", 0).getAll();
  }
  
  public static String getPlacementURL()
  {
    return getConnectFlagValue("TJC_OPTION_PLACEMENT_SERVICE_URL");
  }
  
  public static String getRedirectDomain()
  {
    return S;
  }
  
  public static String getSecretKey()
  {
    return O;
  }
  
  public static String getSha1MacAddress()
  {
    try
    {
      String str = TapjoyUtil.SHA1(r);
      return str;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", "Error generating sha1 of macAddress: " + localException.toString());
    }
    return null;
  }
  
  public static Map getTimeStampAndVerifierParams()
  {
    long l1 = System.currentTimeMillis() / 1000L;
    String str = a(l1);
    HashMap localHashMap = new HashMap();
    TapjoyUtil.safePut(localHashMap, "timestamp", String.valueOf(l1), true);
    TapjoyUtil.safePut(localHashMap, "verifier", str, true);
    return localHashMap;
  }
  
  public static Map getURLParams()
  {
    Map localMap = getGenericURLParams();
    localMap.putAll(getTimeStampAndVerifierParams());
    return localMap;
  }
  
  public static String getUserID()
  {
    return E;
  }
  
  private static void h()
  {
    TapjoyLog.i("TapjoyConnect", "Connect Flags:");
    TapjoyLog.i("TapjoyConnect", "--------------------");
    Iterator localIterator = an.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      TapjoyLog.i("TapjoyConnect", "key: " + (String)localEntry.getKey() + ", value: " + Uri.encode(localEntry.getValue().toString()));
    }
    TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue("TJC_OPTION_SERVICE_URL") + "]");
    TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + S + "]");
    TapjoyLog.i("TapjoyConnect", "--------------------");
  }
  
  private static boolean h(String paramString)
  {
    return ak.checkPermission(paramString, g.getPackageName()) == 0;
  }
  
  private static void i()
  {
    for (;;)
    {
      int i1;
      try
      {
        if (ak == null) {
          break;
        }
        ApplicationInfo localApplicationInfo = ak.getApplicationInfo(g.getPackageName(), 128);
        if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
        {
          String[] arrayOfString = TapjoyConnectFlag.FLAG_ARRAY;
          int i2 = arrayOfString.length;
          i1 = 0;
          if (i1 < i2)
          {
            String str1 = arrayOfString[i1];
            String str2 = localApplicationInfo.metaData.getString("tapjoy." + str1);
            if (str2 != null)
            {
              TapjoyLog.i("TapjoyConnect", "Found manifest flag: " + str1 + ", " + str2);
              b(str1, str2);
            }
          }
          else
          {
            TapjoyLog.i("TapjoyConnect", "Metadata successfully loaded");
          }
        }
        else
        {
          TapjoyLog.i("TapjoyConnect", "No metadata present.");
          return;
        }
      }
      catch (Exception localException)
      {
        TapjoyLog.e("TapjoyConnect", "Error reading manifest meta-data: " + localException.toString());
        return;
      }
      i1 += 1;
    }
  }
  
  public static boolean isAutoSessionTrackingStarted()
  {
    return aX;
  }
  
  public static boolean isConnected()
  {
    return aj;
  }
  
  public static boolean isUnitTestMode()
  {
    return getConnectFlagValue("unit_test_mode") == "true";
  }
  
  public static boolean isViewOpen()
  {
    return ap;
  }
  
  private void j()
  {
    Object localObject3;
    for (;;)
    {
      int i1;
      try
      {
        Object localObject1 = Arrays.asList(ak.getPackageInfo(g.getPackageName(), 1).activities);
        if (localObject1 == null) {
          break;
        }
        localObject1 = ((List)localObject1).iterator();
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localObject3 = (ActivityInfo)((Iterator)localObject1).next();
        if (!m.contains(((ActivityInfo)localObject3).name)) {
          continue;
        }
        i1 = m.indexOf(((ActivityInfo)localObject3).name);
        Vector localVector;
        try
        {
          Class.forName((String)m.get(i1));
          localVector = new Vector();
          if ((((ActivityInfo)localObject3).configChanges & 0x80) != 128) {
            localVector.add("orientation");
          }
          if ((((ActivityInfo)localObject3).configChanges & 0x20) != 32) {
            localVector.add("keyboardHidden");
          }
          if (localVector.size() == 0) {
            break label295;
          }
          if (localVector.size() == 1) {
            throw new TapjoyIntegrationException(localVector.toString() + " property is not specified in manifest configChanges for " + (String)m.get(i1));
          }
        }
        catch (ClassNotFoundException localClassNotFoundException1)
        {
          throw new TapjoyIntegrationException("[ClassNotFoundException] Could not find dependency class " + (String)m.get(i1));
        }
        throw new TapjoyIntegrationException(localVector.toString() + " properties are not specified in manifest configChanges for " + (String)m.get(i1));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        throw new TapjoyIntegrationException("NameNotFoundException: Could not find package.");
      }
      label295:
      if ((Build.VERSION.SDK_INT >= 13) && ((((ActivityInfo)localObject3).configChanges & 0x400) != 1024)) {
        TapjoyLog.w("TapjoyConnect", "WARNING -- screenSize property is not specified in manifest configChanges for " + (String)m.get(i1));
      }
      if ((Build.VERSION.SDK_INT >= 11) && (((ActivityInfo)localObject3).name.equals("com.tapjoy.TJAdUnitView")) && ((((ActivityInfo)localObject3).flags & 0x200) != 512)) {
        throw new TapjoyIntegrationException("'hardwareAccelerated' property not specified in manifest for " + (String)m.get(i1));
      }
      m.remove(i1);
    }
    if (m.size() != 0)
    {
      if (m.size() == 1) {
        throw new TapjoyIntegrationException("Missing " + m.size() + " dependency class in manifest: " + m.toString());
      }
      throw new TapjoyIntegrationException("Missing " + m.size() + " dependency classes in manifest: " + m.toString());
    }
    k();
    try
    {
      Object localObject2 = Class.forName("com.tapjoy.TJAdUnitJSBridge");
      if (getConnectFlagValue("TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK") == null) {
        break label659;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException2)
    {
      try
      {
        ((Class)localObject2).getMethod("closeRequested", new Class[0]);
        localObject3 = (String)TapjoyUtil.getResource("mraid.js");
        localObject2 = localObject3;
        if (localObject3 == null) {
          localObject2 = TapjoyUtil.copyTextFromJarIntoString("js/mraid.js", g);
        }
        if (localObject2 != null) {
          break label625;
        }
        throw new TapjoyIntegrationException("ClassNotFoundException: mraid.js was not found.");
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        throw new TapjoyIntegrationException("Try configuring Proguard or other code obfuscators to ignore com.tapjoy classes. Visit http://dev.tapjoy.comfor more information.");
      }
      localClassNotFoundException2 = localClassNotFoundException2;
      throw new TapjoyIntegrationException("ClassNotFoundException: com.tapjoy.TJAdUnitJSBridge was not found.");
    }
    label625:
    if (getConnectFlagValue("TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK").equals("true"))
    {
      TapjoyLog.i("TapjoyConnect", "Skipping integration check for Google Play Services and Advertising ID. Do this only if you do not have access to Google Play Services.");
      return;
    }
    label659:
    this.am.checkGooglePlayIntegration();
  }
  
  private static void k()
  {
    int i2 = 0;
    Vector localVector = new Vector();
    String[] arrayOfString = TapjoyConstants.dependencyPermissions;
    int i3 = arrayOfString.length;
    int i1 = 0;
    String str;
    while (i1 < i3)
    {
      str = arrayOfString[i1];
      if (!h(str)) {
        localVector.add(str);
      }
      i1 += 1;
    }
    if (localVector.size() != 0)
    {
      if (localVector.size() == 1) {
        throw new TapjoyIntegrationException("Missing 1 permission in manifest: " + localVector.toString());
      }
      throw new TapjoyIntegrationException("Missing " + localVector.size() + " permissions in manifest: " + localVector.toString());
    }
    localVector = new Vector();
    arrayOfString = TapjoyConstants.optionalPermissions;
    i3 = arrayOfString.length;
    i1 = i2;
    while (i1 < i3)
    {
      str = arrayOfString[i1];
      if (!h(str)) {
        localVector.add(str);
      }
      i1 += 1;
    }
    if (localVector.size() != 0)
    {
      if (localVector.size() == 1) {
        TapjoyLog.w("TapjoyConnect", "WARNING -- " + localVector.toString() + " permission was not found in manifest. The exclusion of this permission could cause problems.");
      }
    }
    else {
      return;
    }
    TapjoyLog.w("TapjoyConnect", "WARNING -- " + localVector.toString() + " permissions were not found in manifest. The exclusion of these permissions could cause problems.");
  }
  
  private static boolean l()
  {
    return (c != null) && (c.length() > 0);
  }
  
  private static boolean m()
  {
    return (l()) && (getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS") != null) && (getConnectFlagValue("TJC_OPTION_DISABLE_PERSISTENT_IDS").equals("true"));
  }
  
  private static String n()
  {
    TapjoyLog.i("TapjoyConnect", "generating sessionID...");
    try
    {
      str = TapjoyUtil.SHA256(System.currentTimeMillis() / 1000L + x + q);
      TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + localException1.toString());
    }
    catch (Exception localException1)
    {
      try
      {
        ah = System.currentTimeMillis();
        return str;
      }
      catch (Exception localException2)
      {
        String str;
        for (;;) {}
      }
      localException1 = localException1;
      str = null;
    }
    return str;
  }
  
  private static String o()
  {
    int i2 = 1;
    if (m()) {
      return c;
    }
    if ((q != null) && (q.length() > 0)) {}
    for (int i1 = 1; i1 != 0; i1 = 0) {
      return q;
    }
    if ((r != null) && (r.length() > 0)) {}
    for (i1 = 1; i1 != 0; i1 = 0) {
      return r;
    }
    if (l()) {
      return c;
    }
    if ((n != null) && (n.length() > 0)) {}
    for (i1 = i2; i1 != 0; i1 = 0) {
      return n;
    }
    return null;
  }
  
  public static void removeOfflineLog(String paramString)
  {
    SharedPreferences.Editor localEditor = g.getSharedPreferences("tapjoyOfflineLog", 0).edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString)
  {
    requestTapjoyConnect(paramContext, paramString, null, null);
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString, Hashtable paramHashtable)
  {
    requestTapjoyConnect(paramContext, paramString, paramHashtable, null);
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString, Hashtable paramHashtable, TJConnectListener paramTJConnectListener)
  {
    ApiKeyDecoded localApiKeyDecoded;
    try
    {
      localApiKeyDecoded = new ApiKeyDecoded(paramString);
      if (localApiKeyDecoded.getKeyUsage() != ApiKeyDecoded.KeyUsage.SDK_ANDROID) {
        throw new IllegalArgumentException("The given API key was not for Android.");
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      throw new TapjoyIntegrationException(paramContext.getMessage());
    }
    h = paramString;
    x = localApiKeyDecoded.getAppId();
    O = localApiKeyDecoded.getSecretKey();
    P = localApiKeyDecoded.getAnalyticsId();
    gd.a(paramContext).a(paramString);
    if (paramHashtable != null) {
      an.putAll(paramHashtable);
    }
    k = paramTJConnectListener;
    i = new TapjoyConnectCore(paramContext);
  }
  
  public static void saveOfflineLog(String paramString)
  {
    SharedPreferences localSharedPreferences = g.getSharedPreferences("tapjoyOfflineLog", 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (getOfflineLogs().size() >= 50)
    {
      localEditor.remove((String)new TreeMap(localSharedPreferences.getAll()).firstKey());
      localEditor.commit();
    }
    paramString = paramString + "&original_timestamp=" + System.currentTimeMillis() / 1000L;
    paramString = paramString + "&offline=true";
    localEditor.putString(Long.toString(System.currentTimeMillis()), paramString);
    localEditor.commit();
  }
  
  public static void sendOfflineLogs()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        TapjoyURLConnection localTapjoyURLConnection = new TapjoyURLConnection();
        Iterator localIterator = TapjoyConnectCore.getOfflineLogs().entrySet().iterator();
        for (;;)
        {
          if (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            try
            {
              TapjoyLog.i("TapjoyConnect", "sending offline log: " + localEntry.getValue());
              localTapjoyURLConnection.getResponseFromURL((String)localEntry.getValue() + "&" + TapjoyUtil.convertURLParams(TapjoyConnectCore.getTimeStampAndVerifierParams(), false), "");
              TapjoyConnectCore.removeOfflineLog((String)localEntry.getKey());
            }
            catch (Exception localException)
            {
              for (;;)
              {
                TapjoyLog.i("TapjoyConnect", "error sending offline log");
              }
            }
          }
        }
      }
    }).start();
  }
  
  public static void setAutoSessionTrackingStarted(boolean paramBoolean)
  {
    aX = paramBoolean;
  }
  
  public static void setPlugin(String paramString)
  {
    Q = paramString;
  }
  
  public static void setSDKType(String paramString)
  {
    R = paramString;
  }
  
  public static void setUserID(String paramString)
  {
    E = paramString;
    TapjoyLog.i("TapjoyConnect", "URL parameters: " + getURLParams());
    new Thread(new Runnable()
    {
      public final void run()
      {
        TapjoyLog.i("TapjoyConnect", "setUserID...");
        TapjoyHttpURLResponse localTapjoyHttpURLResponse = TapjoyConnectCore.d().getResponseFromURL(TapjoyConnectCore.getHostURL() + "set_publisher_user_id?", TapjoyConnectCore.getURLParams());
        if (localTapjoyHttpURLResponse.response != null)
        {
          TapjoyConnectCore.b(localTapjoyHttpURLResponse.response);
          TapjoyLog.i("TapjoyConnect", "setUserID successful...");
        }
      }
    }).start();
  }
  
  public static void setViewShowing(boolean paramBoolean)
  {
    ap = paramBoolean;
  }
  
  public static void viewDidClose(int paramInt)
  {
    ap = false;
    if (l != null) {
      l.onViewDidClose(paramInt);
    }
  }
  
  public static void viewDidOpen(int paramInt)
  {
    if (l != null) {
      l.onViewDidOpen(paramInt);
    }
  }
  
  public static void viewWillClose(int paramInt)
  {
    if (l != null) {
      l.onViewWillClose(paramInt);
    }
  }
  
  public static void viewWillOpen(int paramInt)
  {
    ap = true;
    if (l != null) {
      l.onViewWillOpen(paramInt);
    }
  }
  
  public void actionComplete(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "actionComplete: " + paramString);
    Map localMap = f();
    TapjoyUtil.safePut(localMap, "app_id", paramString, true);
    localMap.putAll(getTimeStampAndVerifierParams());
    TapjoyLog.i("TapjoyConnect", "PPA URL parameters: " + localMap);
    new Thread(new PPAThread(localMap)).start();
  }
  
  public void appPause()
  {
    this.ag = true;
  }
  
  public void appResume()
  {
    if (this.ag)
    {
      n();
      this.ag = false;
    }
  }
  
  public void callConnect()
  {
    fetchAdvertisingID();
  }
  
  public void completeConnectCall()
  {
    TapjoyLog.i("TapjoyConnect", "starting connect call...");
    Object localObject1 = "https://connect.tapjoy.com/";
    if (getHostURL() != "https://ws.tapjoyads.com/") {
      localObject1 = getHostURL();
    }
    localObject1 = j.getResponseFromURL((String)localObject1 + "api/connect/v3.json?", null, null, getURLParams());
    if ((localObject1 != null) && (((TapjoyHttpURLResponse)localObject1).statusCode == 200))
    {
      Object localObject3;
      if (d(((TapjoyHttpURLResponse)localObject1).response))
      {
        TapjoyLog.i("TapjoyConnect", "Successfully connected to Tapjoy");
        if (aY) {
          if (Build.VERSION.SDK_INT <= 8) {}
        }
        for (;;)
        {
          try
          {
            doProfileAsync();
            localObject1 = getGenericURLParams().entrySet().iterator();
            if (!((Iterator)localObject1).hasNext()) {
              break;
            }
            localObject3 = (Map.Entry)((Iterator)localObject1).next();
            TapjoyLog.i("TapjoyConnect", (String)((Map.Entry)localObject3).getKey() + ": " + (String)((Map.Entry)localObject3).getValue());
            continue;
          }
          catch (Exception localException)
          {
            TapjoyLog.w("TapjoyConnect", "Error building Threatmetrix profile: " + localException.toString());
            continue;
          }
          TapjoyLog.i("TapjoyConnect", "TM disabled");
        }
        sendOfflineLogs();
        aj = true;
        if (k != null) {
          k.onConnectSuccess();
        }
      }
      for (;;)
      {
        if (ao.length() > 0)
        {
          Object localObject2 = getGenericURLParams();
          TapjoyUtil.safePut((Map)localObject2, "package_names", ao, true);
          long l1 = System.currentTimeMillis() / 1000L;
          localObject3 = a(l1, ao);
          TapjoyUtil.safePut((Map)localObject2, "timestamp", String.valueOf(l1), true);
          TapjoyUtil.safePut((Map)localObject2, "verifier", (String)localObject3, true);
          localObject2 = new TapjoyURLConnection().getResponseFromURL(getHostURL() + "apps_installed?", (Map)localObject2);
          if ((localObject2 != null) && (((TapjoyHttpURLResponse)localObject2).statusCode == 200)) {
            TapjoyLog.i("TapjoyConnect", "Successfully pinged sdkless api.");
          }
        }
        return;
        e();
      }
    }
    e();
  }
  
  public void doProfileAsync()
  {
    TapjoyLog.i("TapjoyConnect", "Initializing Threatmetrix: 2.5-16");
    this.al = new fk();
    try
    {
      this.al.a(new ey()
      {
        /* Error */
        public final void a()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
          //   4: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/fk;
          //   7: invokevirtual 31	com/tapjoy/internal/fk:c	()Lcom/tapjoy/internal/fk$c;
          //   10: getstatic 37	com/tapjoy/internal/fk$c:b	Lcom/tapjoy/internal/fk$c;
          //   13: if_acmpne +28 -> 41
          //   16: aload_0
          //   17: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
          //   20: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/fk;
          //   23: invokevirtual 40	com/tapjoy/internal/fk:b	()Ljava/lang/String;
          //   26: invokestatic 43	com/tapjoy/TapjoyConnectCore:a	(Ljava/lang/String;)Ljava/lang/String;
          //   29: pop
          //   30: aload_0
          //   31: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
          //   34: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/fk;
          //   37: invokevirtual 46	com/tapjoy/internal/fk:d	()V
          //   40: return
          //   41: ldc 48
          //   43: ldc 50
          //   45: invokestatic 56	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
          //   48: goto -18 -> 30
          //   51: astore_1
          //   52: ldc 48
          //   54: new 58	java/lang/StringBuilder
          //   57: dup
          //   58: ldc 60
          //   60: invokespecial 63	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
          //   63: aload_1
          //   64: invokevirtual 66	java/lang/Exception:toString	()Ljava/lang/String;
          //   67: invokevirtual 70	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
          //   70: invokevirtual 71	java/lang/StringBuilder:toString	()Ljava/lang/String;
          //   73: invokestatic 56	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
          //   76: aload_0
          //   77: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
          //   80: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/fk;
          //   83: invokevirtual 46	com/tapjoy/internal/fk:d	()V
          //   86: return
          //   87: astore_1
          //   88: aload_0
          //   89: getfield 17	com/tapjoy/TapjoyConnectCore$1:a	Lcom/tapjoy/TapjoyConnectCore;
          //   92: invokestatic 25	com/tapjoy/TapjoyConnectCore:a	(Lcom/tapjoy/TapjoyConnectCore;)Lcom/tapjoy/internal/fk;
          //   95: invokevirtual 46	com/tapjoy/internal/fk:d	()V
          //   98: aload_1
          //   99: athrow
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	100	0	this	1
          //   51	13	1	localException	Exception
          //   87	12	1	localObject	Object
          // Exception table:
          //   from	to	target	type
          //   0	30	51	java/lang/Exception
          //   41	48	51	java/lang/Exception
          //   0	30	87	finally
          //   41	48	87	finally
          //   52	76	87	finally
        }
      });
      this.al.a();
      this.al.a(g, "rrx68giz", "h.online-metrix.net", "http://content-js.tapjoy.com");
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
  
  public void enablePaidAppWithActionID(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "enablePaidAppWithActionID: " + paramString);
    ad = paramString;
    this.ae = g.getSharedPreferences("tjcPrefrences", 0).getLong("tapjoy_elapsed_time", 0L);
    TapjoyLog.i("TapjoyConnect", "paidApp elapsed: " + this.ae);
    if (this.ae >= 900000L) {
      if ((ad != null) && (ad.length() > 0))
      {
        TapjoyLog.i("TapjoyConnect", "Calling PPA actionComplete...");
        actionComplete(ad);
      }
    }
    while (this.af != null) {
      return;
    }
    this.af = new Timer();
    this.af.schedule(new a((byte)0), 10000L, 10000L);
  }
  
  public void fetchAdvertisingID()
  {
    new Thread(new Runnable()
    {
      public final void run()
      {
        TapjoyConnectCore.b(TapjoyConnectCore.this).loadAdvertisingId();
        if ((TapjoyConnectCore.b(TapjoyConnectCore.this).isGooglePlayServicesAvailable()) && (TapjoyConnectCore.b(TapjoyConnectCore.this).isGooglePlayManifestConfigured()))
        {
          TapjoyConnectCore.b = TapjoyConnectCore.b(TapjoyConnectCore.this).getDeviceGooglePlayServicesVersion();
          TapjoyConnectCore.a = TapjoyConnectCore.b(TapjoyConnectCore.this).getPackagedGooglePlayServicesVersion();
        }
        if (TapjoyConnectCore.b(TapjoyConnectCore.this).isAdIdAvailable())
        {
          TapjoyConnectCore.d = TapjoyConnectCore.b(TapjoyConnectCore.this).isAdTrackingEnabled();
          TapjoyConnectCore.c = TapjoyConnectCore.b(TapjoyConnectCore.this).getAdvertisingId();
        }
        if (TapjoyConnectCore.a()) {
          TapjoyLog.i("TapjoyConnect", "Disabling persistent IDs. Do this only if you are not using Tapjoy to manage currency.");
        }
        TapjoyConnectCore.this.completeConnectCall();
      }
    }).start();
  }
  
  public float getCurrencyMultiplier()
  {
    return T;
  }
  
  /* Error */
  public String getSerial()
  {
    // Byte code:
    //   0: ldc_w 1802
    //   3: invokestatic 1510	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 1804
    //   9: invokevirtual 1808	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 1813	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 1816	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 413
    //   29: invokevirtual 1817	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1354	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc_w 502
    //   39: new 504	java/lang/StringBuilder
    //   42: dup
    //   43: ldc_w 1819
    //   46: invokespecial 509	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: aload_1
    //   50: invokevirtual 513	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: invokevirtual 516	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   56: invokestatic 519	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_1
    //   60: areturn
    //   61: astore_2
    //   62: aconst_null
    //   63: astore_1
    //   64: ldc_w 502
    //   67: aload_2
    //   68: invokevirtual 679	java/lang/Exception:toString	()Ljava/lang/String;
    //   71: invokestatic 523	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   74: aload_1
    //   75: areturn
    //   76: astore_2
    //   77: goto -13 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	80	0	this	TapjoyConnectCore
    //   12	63	1	localObject	Object
    //   61	7	2	localException1	Exception
    //   76	1	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	25	61	java/lang/Exception
    //   25	36	61	java/lang/Exception
    //   36	59	76	java/lang/Exception
  }
  
  public boolean isInitialized()
  {
    return this.ai;
  }
  
  public void release()
  {
    i = null;
    j = null;
    TapjoyLog.i("TapjoyConnect", "Releasing core static instance.");
  }
  
  public void setCurrencyMultiplier(float paramFloat)
  {
    TapjoyLog.i("TapjoyConnect", "setVirtualCurrencyMultiplier: " + paramFloat);
    T = paramFloat;
  }
  
  public void setTapjoyViewListener(TJViewListener paramTJViewListener)
  {
    l = paramTJViewListener;
  }
  
  public class PPAThread
    implements Runnable
  {
    private Map b;
    
    public PPAThread(Map paramMap)
    {
      this.b = paramMap;
    }
    
    public void run()
    {
      TapjoyHttpURLResponse localTapjoyHttpURLResponse = TapjoyConnectCore.d().getResponseFromURL(TapjoyConnectCore.getHostURL() + "api/connect/v3.json?", null, null, this.b);
      if (localTapjoyHttpURLResponse.response != null) {
        TapjoyConnectCore.c(localTapjoyHttpURLResponse.response);
      }
    }
  }
  
  final class a
    extends TimerTask
  {
    private a() {}
    
    public final void run()
    {
      TapjoyConnectCore.c(TapjoyConnectCore.this);
      TapjoyLog.i("TapjoyConnect", "elapsed_time: " + TapjoyConnectCore.d(TapjoyConnectCore.this) + " (" + TapjoyConnectCore.d(TapjoyConnectCore.this) / 1000L / 60L + "m " + TapjoyConnectCore.d(TapjoyConnectCore.this) / 1000L % 60L + "s)");
      SharedPreferences.Editor localEditor = TapjoyConnectCore.b().getSharedPreferences("tjcPrefrences", 0).edit();
      localEditor.putLong("tapjoy_elapsed_time", TapjoyConnectCore.d(TapjoyConnectCore.this));
      localEditor.commit();
      if (TapjoyConnectCore.d(TapjoyConnectCore.this) >= 900000L)
      {
        TapjoyLog.i("TapjoyConnect", "timer done...");
        if ((TapjoyConnectCore.c() != null) && (TapjoyConnectCore.c().length() > 0))
        {
          TapjoyLog.i("TapjoyConnect", "Calling PPA actionComplete...");
          TapjoyConnectCore.this.actionComplete(TapjoyConnectCore.c());
        }
        cancel();
      }
    }
  }
}
