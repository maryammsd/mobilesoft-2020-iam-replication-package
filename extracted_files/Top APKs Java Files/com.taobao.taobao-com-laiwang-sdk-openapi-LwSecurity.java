package com.laiwang.sdk.openapi;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public class LwSecurity
{
  private static final String LW_SIGNATURE_DEBUG = "308201e53082014ea00302010202044e51feda300d06092a864886f70d01010505003037310b30090603550406130255533110300e060355040a1307416e64726f6964311630140603550403130d416e64726f6964204465627567301e170d3131303832323037303134365a170d3431303831343037303134365a3037310b30090603550406130255533110300e060355040a1307416e64726f6964311630140603550403130d416e64726f696420446562756730819f300d06092a864886f70d010101050003818d0030818902818100d863f4f3100ca2bc9d15503284e09b64cad4008144bc48f0bc7e5d0e097f07041e5a2e29520dfbd4e0746401438cb20819de56dc9cf26cdc6c5d1a9da4b32ffa80bc960e7d01c7b067167c5df676d64d916d09d37f9ccad935275dd2e480c360cd95a045263a298b2718a03217ea822c5cef78035cd2b114baac552a104e48670203010001300d06092a864886f70d0101050500038181006d929e4f794c5849e13ae90c8803307778257a6d27be8a32bccee13b370888afefcffeeae52eb2eea985112f46301d3e386bb8dfe8560f1bce2c64e5744be5abbc6b73320c2f2a774a189574d2fafe7ec942455bcdcac51e4805e916321f06356e03b44e8e449c87fd33152ff9d294f3ae85da83fd880a5c4671da7c75d0da0d";
  private static final String LW_SIGNATURE_RELEASE = "308201e730820150a00302010202044eca1289300d06092a864886f70d010105050030383111300f0603550407130868616e677a686f753110300e060355040a1307616c69626162613111300f06035504031308786965716962616f301e170d3131313132313038353734355a170d3434313131323038353734355a30383111300f0603550407130868616e677a686f753110300e060355040a1307616c69626162613111300f06035504031308786965716962616f30819f300d06092a864886f70d010101050003818d0030818902818100cb09f672e5ab59a06281794326367a90d306c123e4328536a215d9f0c7c021cf1b1b2a3a2a232ad0b8492e77181cef510bd3c008e0baea3094df301a2f11d90ae89cf6be8a811c66093622f5cf00952fdf53e5bb1d653b1664c839a3d47c3512f882d7c728e9f5f94f281ac5cdb9bd3fe3ffe33021f553c010e3313acf7008d70203010001300d06092a864886f70d010105050003818100ba4fde6c721b3cdf4aa27d0990cb3c764c78d2af888648f0b9593c5c82795b5df057cca2524ea6d38c2105894cc02af04ed2712950d4e3c30bb5f716aba72c012f6576669df95fa9fd0fd8e2559322d48f6c2c3796e1905aaa46f09acbc7755628061246f365301e22e85ca50e27682829d0ff89ba4700c2452d9a373bdab7eb";
  public static boolean sIsSecuritySahreSDK = false;
  private static LwSecurity sLwSecurity = null;
  public Context mContext = null;
  
  public LwSecurity(Context paramContext)
  {
    this.mContext = paramContext;
  }
  
  public static LwSecurity getInstance()
  {
    if (sLwSecurity == null) {
      sLwSecurity = new LwSecurity(LWAPI.getApplication());
    }
    return sLwSecurity;
  }
  
  private String[] getPackageNameByPID(int paramInt)
  {
    Object localObject = ((ActivityManager)this.mContext.getSystemService("activity")).getRunningAppProcesses();
    if ((localObject == null) || (((List)localObject).size() <= 0))
    {
      Log.e("LWAPI", "runningProcess err");
      return null;
    }
    localObject = ((List)localObject).iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        return null;
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
    } while (localRunningAppProcessInfo.pid != paramInt);
    return localRunningAppProcessInfo.pkgList;
  }
  
  public static String getPackageSignature(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(64).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return null;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while (!localPackageInfo.packageName.equals(paramString));
    return localPackageInfo.signatures[0].toCharsString();
  }
  
  public static boolean isLWAPPImpl(Context paramContext, String paramString)
  {
    paramContext = getPackageSignature(paramContext, paramString);
    return ("308201e53082014ea00302010202044e51feda300d06092a864886f70d01010505003037310b30090603550406130255533110300e060355040a1307416e64726f6964311630140603550403130d416e64726f6964204465627567301e170d3131303832323037303134365a170d3431303831343037303134365a3037310b30090603550406130255533110300e060355040a1307416e64726f6964311630140603550403130d416e64726f696420446562756730819f300d06092a864886f70d010101050003818d0030818902818100d863f4f3100ca2bc9d15503284e09b64cad4008144bc48f0bc7e5d0e097f07041e5a2e29520dfbd4e0746401438cb20819de56dc9cf26cdc6c5d1a9da4b32ffa80bc960e7d01c7b067167c5df676d64d916d09d37f9ccad935275dd2e480c360cd95a045263a298b2718a03217ea822c5cef78035cd2b114baac552a104e48670203010001300d06092a864886f70d0101050500038181006d929e4f794c5849e13ae90c8803307778257a6d27be8a32bccee13b370888afefcffeeae52eb2eea985112f46301d3e386bb8dfe8560f1bce2c64e5744be5abbc6b73320c2f2a774a189574d2fafe7ec942455bcdcac51e4805e916321f06356e03b44e8e449c87fd33152ff9d294f3ae85da83fd880a5c4671da7c75d0da0d".equals(paramContext)) || ("308201e730820150a00302010202044eca1289300d06092a864886f70d010105050030383111300f0603550407130868616e677a686f753110300e060355040a1307616c69626162613111300f06035504031308786965716962616f301e170d3131313132313038353734355a170d3434313131323038353734355a30383111300f0603550407130868616e677a686f753110300e060355040a1307616c69626162613111300f06035504031308786965716962616f30819f300d06092a864886f70d010101050003818d0030818902818100cb09f672e5ab59a06281794326367a90d306c123e4328536a215d9f0c7c021cf1b1b2a3a2a232ad0b8492e77181cef510bd3c008e0baea3094df301a2f11d90ae89cf6be8a811c66093622f5cf00952fdf53e5bb1d653b1664c839a3d47c3512f882d7c728e9f5f94f281ac5cdb9bd3fe3ffe33021f553c010e3313acf7008d70203010001300d06092a864886f70d010105050003818100ba4fde6c721b3cdf4aa27d0990cb3c764c78d2af888648f0b9593c5c82795b5df057cca2524ea6d38c2105894cc02af04ed2712950d4e3c30bb5f716aba72c012f6576669df95fa9fd0fd8e2559322d48f6c2c3796e1905aaa46f09acbc7755628061246f365301e22e85ca50e27682829d0ff89ba4700c2452d9a373bdab7eb".equals(paramContext));
  }
  
  public static boolean load()
  {
    try
    {
      System.loadLibrary("LwUtils");
      return true;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      localUnsatisfiedLinkError.printStackTrace();
      sIsSecuritySahreSDK = false;
    }
    return false;
  }
  
  public native boolean checkCertificate(String paramString);
  
  public boolean checkCertificateByPID(int paramInt)
  {
    boolean bool1;
    if (!sIsSecuritySahreSDK) {
      bool1 = true;
    }
    label77:
    for (;;)
    {
      return bool1;
      String[] arrayOfString = getPackageNameByPID(paramInt);
      if (arrayOfString == null) {
        return false;
      }
      int i = arrayOfString.length;
      paramInt = 0;
      boolean bool2;
      for (bool1 = false;; bool1 = bool2)
      {
        if (paramInt >= i) {
          break label77;
        }
        String str = arrayOfString[paramInt];
        if (TextUtils.isEmpty(str)) {
          return false;
        }
        bool2 = checkCertificate(str);
        bool1 = bool2;
        if (bool2) {
          break;
        }
        paramInt += 1;
      }
    }
  }
  
  public boolean checkCertificateByUID(int paramInt)
  {
    boolean bool1 = true;
    if (!sIsSecuritySahreSDK) {}
    label93:
    for (;;)
    {
      return bool1;
      String[] arrayOfString = getPackageNameByUID(paramInt);
      if (arrayOfString != null)
      {
        int i = arrayOfString.length;
        paramInt = 0;
        boolean bool2;
        for (bool1 = false;; bool1 = bool2)
        {
          if (paramInt >= i) {
            break label93;
          }
          String str = arrayOfString[paramInt];
          if (TextUtils.isEmpty(str)) {
            return false;
          }
          new StringBuilder("packageName:").append(str).toString();
          bool2 = checkCertificate(str);
          bool1 = bool2;
          if (bool2) {
            break;
          }
          paramInt += 1;
        }
      }
    }
  }
  
  public String[] getPackageNameByUID(int paramInt)
  {
    Object localObject = ((ActivityManager)this.mContext.getSystemService("activity")).getRunningAppProcesses();
    if ((localObject == null) || (((List)localObject).size() <= 0))
    {
      Log.e("LWAPI", "runningProcess err");
      return null;
    }
    localObject = ((List)localObject).iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      if (!((Iterator)localObject).hasNext()) {
        return null;
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
    } while (localRunningAppProcessInfo.uid != paramInt);
    return localRunningAppProcessInfo.pkgList;
  }
}
