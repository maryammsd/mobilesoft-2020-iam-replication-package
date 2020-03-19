package com.art.artcamera.image.shareimage;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.text.TextUtils;
import com.art.artcamera.CameraApp;
import com.art.artcamera.d.f;
import com.art.artcamera.d.i;
import com.art.artcamera.d.k;
import com.art.artcamera.h.b;
import com.art.artcamera.image.BitmapBean;
import com.art.artcamera.image.m;
import com.art.artcamera.utils.aa;
import com.art.artcamera.utils.x;
import com.art.artcamera.utils.y;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX.Req;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShareImageTools
{
  public static final String FACEBOOK_SEND_PIC_TO_SHARE_ACTIVITY_NAME1 = "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias";
  public static final String FACEBOOK_SEND_PIC_TO_SHARE_ACTIVITY_NAME2 = "com.facebook.composer.shareintent.ImplicitShareIntentHandler";
  public static final String FACEBOOK_SEND_PIC_TO_SHARE_PACKAGE_NAME = "com.facebook.katana";
  public static final String GMAIL_SEND_PIC_TO_SHARE_ACTIVITY_NAME = "com.google.android.gm.ComposeActivityGmailExternal";
  public static final String GMAIL_SEND_PIC_TO_SHARE_PACKAGE_NAME = "com.google.android.gm";
  public static final String GOSMS_SEND_PIC_TO_FRIEND_ACTIVITY_NAME = "com.jb.gosms.ui.ComposeMessageActivity";
  public static final String GOSMS_SEND_PIC_TO_FRIEND_PACKAGE_NAME = "com.jb.gosms";
  public static final String INSTAGRAM_SEND_PIC_TO_SHARE_ACTIVITY_NAME = "com.instagram.android.activity.ShareHandlerActivity";
  public static final String INSTAGRAM_SEND_PIC_TO_SHARE_PACKAGE_NAME = "com.instagram.android";
  public static final String LINE = "Line";
  public static final String LINE_SEND_PIC_TO_SHARE_ACTIVITY_NAME = "jp.naver.line.android.activity.selectchat.SelectChatActivity";
  public static final String LINE_SEND_PIC_TO_SHARE_PACKAGE_NAME = "jp.naver.line.android";
  public static final String PRINT_LOCAL_PACKAGE_NAME = "print_local";
  public static final String QQ_SEND_PIC_TO_KONGJIAN_ACTIVITY_NAME = "com.qzonex.module.operation.ui.QZonePublishMoodActivity";
  public static final String QQ_SEND_PIC_TO_KONGJIAN_PACKAGE_NAME = "com.qzone";
  public static final String SHARE_COMMUNITY_PACKAGE_NAME = "zcamera_community";
  public static final String SINA_SEND_PIC_TO_PENGYOUQUAN_ACTIVITY_NAME = "com.sina.weibo.ComposerDispatchActivity";
  public static final String SINA_SEND_PIC_TO_PENGYOUQUAN_PACKAGE_NAME = "com.sina.weibo";
  private static final int TIMELINE_SUPPORTED_VERSION = 553779201;
  public static final String TWITTER = "Twitter";
  public static final String TWITTER_SEND_PIC_TO_SHARE_ACTIVITY_NAME = "com.twitter.android.composer.ComposerActivity";
  public static final String TWITTER_SEND_PIC_TO_SHARE_PACKAGE_NAME = "com.twitter.android";
  public static final String WEIXIN_SEND_PIC_TO_COLLECTION_ACTIVITY_NAME = "com.tencent.mm.ui.tools.AddFavoriteUI";
  public static final String WEIXIN_SEND_PIC_TO_FRIEND_ACTIVITY_NAME = "com.tencent.mm.ui.tools.ShareImgUI";
  public static final String WEIXIN_SEND_PIC_TO_PENGYOUQUAN_ACTIVITY_NAME = "com.tencent.mm.ui.tools.ShareToTimeLineUI";
  public static final String WEIXIN_SEND_PIC_TO_PENGYOUQUAN_PACKAGE_NAME = "com.tencent.mm";
  public static final String WHATSAPP_SEND_PIC_TO_SHARE_ACTIVITY_NAME = "com.whatsapp.ContactPicker";
  public static final String WHATSAPP_SEND_PIC_TO_SHARE_PACKAGE_NAME = "com.whatsapp";
  private static boolean mIsStarted = false;
  
  public ShareImageTools() {}
  
  private static String buildTransaction(String paramString)
  {
    if (paramString == null) {
      return String.valueOf(System.currentTimeMillis());
    }
    return paramString + System.currentTimeMillis();
  }
  
  private static List<ResolveInfo> getAllSendImageOrVideoTools(Context paramContext, boolean paramBoolean)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.SEND");
    if (paramBoolean) {
      localIntent.setType("image/*");
    }
    for (;;)
    {
      localIntent.addCategory("android.intent.category.DEFAULT");
      return paramContext.queryIntentActivities(localIntent, 65536);
      localIntent.setType("video/*");
    }
  }
  
  private static List<ResolveInfo> getAllSendMutilMediaTools(Context paramContext, int paramInt1, int paramInt2)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent();
    if ((paramInt1 > 1) || (paramInt2 > 1) || ((paramInt1 > 0) && (paramInt2 > 0)))
    {
      localIntent.setAction("android.intent.action.SEND_MULTIPLE");
      if ((paramInt1 <= 0) || (paramInt2 <= 0)) {
        break label78;
      }
      localIntent.setType("media/*");
    }
    for (;;)
    {
      localIntent.addCategory("android.intent.category.DEFAULT");
      return paramContext.queryIntentActivities(localIntent, 65536);
      localIntent.setAction("android.intent.action.SEND");
      break;
      label78:
      if (paramInt2 > 0) {
        localIntent.setType("video/*");
      } else {
        localIntent.setType("image/*");
      }
    }
  }
  
  private static List<ResolveInfo> getAllSendTextTools(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/*");
    localIntent.addCategory("android.intent.category.DEFAULT");
    return paramContext.queryIntentActivities(localIntent, 65536);
  }
  
  public static List<ShareImageItem.a> getAllShareMutilMediaTools(Context paramContext, int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = getAllSendMutilMediaTools(paramContext, paramInt1, paramInt2).iterator();
    while (paramContext.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.next();
      if (!localResolveInfo.activityInfo.packageName.equals("com.iart.camera.photo")) {
        localArrayList.add(new ShareImageItem.a(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, localResolveInfo.loadIcon(localPackageManager), localResolveInfo.loadLabel(localPackageManager).toString()));
      }
    }
    return localArrayList;
  }
  
  public static List<ShareImageItem.a> getAllShareTextTools(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = getAllSendTextTools(paramContext).iterator();
    while (paramContext.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.next();
      if (!localResolveInfo.activityInfo.packageName.equals("com.iart.camera.photo")) {
        localArrayList.add(new ShareImageItem.a(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, localResolveInfo.loadIcon(localPackageManager), localResolveInfo.loadLabel(localPackageManager).toString()));
      }
    }
    return localArrayList;
  }
  
  public static List<ShareImageItem.a> getAllShareTools(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = getAllSendImageOrVideoTools(paramContext, paramBoolean).iterator();
    while (paramContext.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.next();
      if (!localResolveInfo.activityInfo.packageName.equals("com.iart.camera.photo")) {
        localArrayList.add(new ShareImageItem.a(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, localResolveInfo.loadIcon(localPackageManager), localResolveInfo.loadLabel(localPackageManager).toString()));
      }
    }
    return localArrayList;
  }
  
  public static boolean getAppIsInstalled(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    for (;;)
    {
      return false;
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
        if (paramContext != null) {
          return true;
        }
      }
      catch (Throwable paramContext) {}
    }
    return false;
  }
  
  public static boolean getAppIsInstalled(Context paramContext, String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {
      return false;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        int j = paramArrayOfString.length;
        int i = 0;
        while (i < j)
        {
          String str = paramArrayOfString[i];
          boolean bool = localPackageInfo.packageName.equals(str);
          if (bool) {
            return true;
          }
          i += 1;
        }
      }
      return false;
    }
    catch (Throwable paramContext) {}
  }
  
  private static int getCountTools(Context paramContext, List<ShareImageItem.a> paramList, boolean paramBoolean, int paramInt)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = getAllSendImageOrVideoTools(paramContext, paramBoolean);
    int m = paramContext.size();
    int j = 0;
    int i = 0;
    while (j < m)
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.get(j);
      if (!isDefaultTools(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name))
      {
        paramList.add(new ShareImageItem.a(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, localResolveInfo.loadIcon(localPackageManager), localResolveInfo.loadLabel(localPackageManager).toString()));
        int k = i + 1;
        i = k;
        if (k == paramInt) {
          return k;
        }
      }
      j += 1;
    }
    return i;
  }
  
  private static int getCountTools(Context paramContext, List<ShareImageItem.a> paramList, boolean paramBoolean, int paramInt, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = getAllSendImageOrVideoTools(paramContext, paramBoolean);
    int m = paramContext.size();
    int j = 0;
    int i = 0;
    while (j < m)
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramContext.get(j);
      if ((!isDefaultTools(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name)) && (!isFilterTools(localResolveInfo, paramArrayOfString1, paramArrayOfString2)))
      {
        paramList.add(new ShareImageItem.a(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name, localResolveInfo.loadIcon(localPackageManager), localResolveInfo.loadLabel(localPackageManager).toString()));
        int k = i + 1;
        i = k;
        if (k == paramInt) {
          return k;
        }
      }
      j += 1;
    }
    return i;
  }
  
  public static String getPkgName()
  {
    return CameraApp.getApplication().getPackageName();
  }
  
  private static void getStoreTools(Context paramContext, List<ShareImageItem.a> paramList, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      Object localObject4 = x.e();
      Object localObject3 = x.f();
      Object localObject2 = x.g();
      boolean bool1;
      boolean bool2;
      for (Object localObject1 = x.h();; localObject1 = x.l())
      {
        bool1 = TextUtils.isEmpty((CharSequence)localObject4);
        bool2 = TextUtils.isEmpty((CharSequence)localObject2);
        if ((bool1) || (bool2)) {
          break label673;
        }
        bool1 = isDefaultTools((String)localObject4, (String)localObject3);
        bool2 = isDefaultTools((String)localObject2, (String)localObject1);
        if ((!bool1) || (!bool2)) {
          break;
        }
        getCountTools(paramContext, paramList, paramBoolean, 2);
        return;
        localObject4 = x.i();
        localObject3 = x.j();
        localObject2 = x.k();
      }
      if ((!bool1) && (!bool2))
      {
        bool1 = getAppIsInstalled(paramContext, (String)localObject4);
        bool2 = getAppIsInstalled(paramContext, (String)localObject2);
        if ((bool1) && (bool2))
        {
          paramContext = paramContext.getPackageManager();
          ActivityInfo localActivityInfo1 = paramContext.getActivityInfo(new ComponentName((String)localObject4, (String)localObject3), 65536);
          ActivityInfo localActivityInfo2 = paramContext.getActivityInfo(new ComponentName((String)localObject2, (String)localObject1), 65536);
          paramList.add(new ShareImageItem.a((String)localObject4, (String)localObject3, localActivityInfo1.loadIcon(paramContext), localActivityInfo1.loadLabel(paramContext).toString()));
          paramList.add(new ShareImageItem.a((String)localObject2, (String)localObject1, localActivityInfo2.loadIcon(paramContext), localActivityInfo2.loadLabel(paramContext).toString()));
          return;
        }
        if ((!bool1) && (!bool2))
        {
          getCountTools(paramContext, paramList, paramBoolean, 2);
          return;
        }
        if (bool1)
        {
          localObject1 = paramContext.getPackageManager();
          localObject2 = ((PackageManager)localObject1).getActivityInfo(new ComponentName((String)localObject4, (String)localObject3), 65536);
          paramList.add(new ShareImageItem.a((String)localObject4, (String)localObject3, ((ActivityInfo)localObject2).loadIcon((PackageManager)localObject1), ((ActivityInfo)localObject2).loadLabel((PackageManager)localObject1).toString()));
          getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject4 }, new String[] { localObject3 });
          return;
        }
        localObject3 = paramContext.getPackageManager();
        localObject4 = ((PackageManager)localObject3).getActivityInfo(new ComponentName((String)localObject2, (String)localObject1), 65536);
        paramList.add(new ShareImageItem.a((String)localObject2, (String)localObject1, ((ActivityInfo)localObject4).loadIcon((PackageManager)localObject3), ((ActivityInfo)localObject4).loadLabel((PackageManager)localObject3).toString()));
        getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject2 }, new String[] { localObject1 });
        return;
      }
      if (bool1)
      {
        if (getAppIsInstalled(paramContext, (String)localObject2))
        {
          localObject3 = paramContext.getPackageManager();
          localObject4 = ((PackageManager)localObject3).getActivityInfo(new ComponentName((String)localObject2, (String)localObject1), 65536);
          paramList.add(new ShareImageItem.a((String)localObject2, (String)localObject1, ((ActivityInfo)localObject4).loadIcon((PackageManager)localObject3), ((ActivityInfo)localObject4).loadLabel((PackageManager)localObject3).toString()));
          getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject2 }, new String[] { localObject1 });
          return;
        }
        getCountTools(paramContext, paramList, paramBoolean, 2);
        return;
      }
      if (getAppIsInstalled(paramContext, (String)localObject4))
      {
        localObject1 = paramContext.getPackageManager();
        localObject2 = ((PackageManager)localObject1).getActivityInfo(new ComponentName((String)localObject4, (String)localObject3), 65536);
        paramList.add(new ShareImageItem.a((String)localObject4, (String)localObject3, ((ActivityInfo)localObject2).loadIcon((PackageManager)localObject1), ((ActivityInfo)localObject2).loadLabel((PackageManager)localObject1).toString()));
        getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject4 }, new String[] { localObject3 });
        return;
      }
      getCountTools(paramContext, paramList, paramBoolean, 2);
      return;
      label673:
      if ((bool1) && (bool2))
      {
        getCountTools(paramContext, paramList, paramBoolean, 2);
        return;
      }
      if (!bool1)
      {
        if (isDefaultTools((String)localObject4, (String)localObject3))
        {
          getCountTools(paramContext, paramList, paramBoolean, 2);
          return;
        }
        if (getAppIsInstalled(paramContext, (String)localObject4))
        {
          localObject1 = paramContext.getPackageManager();
          localObject2 = ((PackageManager)localObject1).getActivityInfo(new ComponentName((String)localObject4, (String)localObject3), 65536);
          paramList.add(new ShareImageItem.a((String)localObject4, (String)localObject3, ((ActivityInfo)localObject2).loadIcon((PackageManager)localObject1), ((ActivityInfo)localObject2).loadLabel((PackageManager)localObject1).toString()));
          getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject4 }, new String[] { localObject3 });
          return;
        }
        getCountTools(paramContext, paramList, paramBoolean, 2);
        return;
      }
      if (isDefaultTools((String)localObject2, (String)localObject1))
      {
        getCountTools(paramContext, paramList, paramBoolean, 2);
        return;
      }
      if (getAppIsInstalled(paramContext, (String)localObject2))
      {
        localObject3 = paramContext.getPackageManager();
        localObject4 = ((PackageManager)localObject3).getActivityInfo(new ComponentName((String)localObject2, (String)localObject1), 65536);
        paramList.add(new ShareImageItem.a((String)localObject2, (String)localObject1, ((ActivityInfo)localObject4).loadIcon((PackageManager)localObject3), ((ActivityInfo)localObject4).loadLabel((PackageManager)localObject3).toString()));
        getCountTools(paramContext, paramList, paramBoolean, 1, new String[] { localObject2 }, new String[] { localObject1 });
        return;
      }
      getCountTools(paramContext, paramList, paramBoolean, 2);
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  public static List<ShareImageItem.a> getTop3ShareTools(Context paramContext, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    return getTop3ShareTools(paramContext, paramInt, paramBoolean1, paramBoolean2, false);
  }
  
  @SuppressLint({"NewApi"})
  public static List<ShareImageItem.a> getTop3ShareTools(Context paramContext, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    ArrayList localArrayList = new ArrayList();
    Resources localResources = paramContext.getResources();
    if (y.c()) {}
    localArrayList.add(new ShareImageItem.a("com.facebook.katana", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias", localResources.getDrawable(d.f.share_icon_facebook), localResources.getString(d.k.facebook)));
    if ((paramInt == 4) && (paramBoolean3))
    {
      localArrayList.add(0, new ShareImageItem.a("zcamera_community", "", localResources.getDrawable(d.f.share_icon_community), localResources.getString(d.k.share_community)));
      localArrayList.add(new ShareImageItem.a("com.instagram.android", "com.instagram.android.activity.ShareHandlerActivity", localResources.getDrawable(d.f.share_icon_instagram), localResources.getString(d.k.instagram)));
      if ((paramInt == 1) && (paramBoolean1))
      {
        localArrayList.add(new ShareImageItem.a("com.jb.gosms", "com.jb.gosms.ui.ComposeMessageActivity", localResources.getDrawable(d.f.share_icon_sms), localResources.getString(d.k.gosms)));
        if (!paramBoolean3) {
          break label303;
        }
        localArrayList.add(0, new ShareImageItem.a("zcamera_community", "", localResources.getDrawable(d.f.share_icon_community), localResources.getString(d.k.share_community)));
      }
    }
    for (;;)
    {
      getStoreTools(paramContext, localArrayList, true);
      localArrayList.add(new ShareImageItem.a(null, null, localResources.getDrawable(d.f.share_icon_more), localResources.getString(d.k.more)));
      return localArrayList;
      localArrayList.add(new ShareImageItem.a("com.whatsapp", "com.whatsapp.ContactPicker", localResources.getDrawable(d.f.share_icon_whatsapp), localResources.getString(d.k.whatsapp)));
      break;
      label303:
      if ((paramBoolean2) && (Build.VERSION.SDK_INT >= 19)) {
        localArrayList.add(new ShareImageItem.a("print_local", "", localResources.getDrawable(d.f.share_icon_print_at_home), localResources.getString(d.k.share_print_at_home)));
      }
    }
  }
  
  public static List<ShareImageItem.a> getTopNewShareTools(Context paramContext, int paramInt, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getResources();
    if (y.c()) {}
    localArrayList.add(new ShareImageItem.a("com.facebook.katana", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias", paramContext.getDrawable(d.i.iart_icon_editor_share_fb), paramContext.getString(d.k.facebook)));
    localArrayList.add(new ShareImageItem.a("com.instagram.android", "com.instagram.android.activity.ShareHandlerActivity", paramContext.getDrawable(d.i.iart_icon_editor_share_ins), paramContext.getString(d.k.instagram)));
    localArrayList.add(new ShareImageItem.a("com.twitter.android", "com.twitter.android.composer.ComposerActivity", paramContext.getDrawable(d.i.iart_icon_editor_share_twitter), paramContext.getString(d.k.share_twitter)));
    localArrayList.add(new ShareImageItem.a("jp.naver.line.android", "jp.naver.line.android.activity.selectchat.SelectChatActivity", paramContext.getDrawable(d.i.iart_icon_editor_share_line), paramContext.getString(d.k.share_line)));
    localArrayList.add(new ShareImageItem.a(null, null, paramContext.getDrawable(d.i.iart_icon_editor_share_more), paramContext.getString(d.k.more)));
    return localArrayList;
  }
  
  public static boolean isDefaultTools(String paramString1, String paramString2)
  {
    boolean bool = false;
    if (y.c()) {
      if ((("com.tencent.mm".equals(paramString1)) && ("com.tencent.mm.ui.tools.ShareToTimeLineUI".equals(paramString2))) || (("com.qzone".equals(paramString1)) && ("com.qzonex.module.operation.ui.QZonePublishMoodActivity".equals(paramString2))) || (("com.sina.weibo".equals(paramString1)) && ("com.sina.weibo.ComposerDispatchActivity".equals(paramString2))) || (("com.jb.gosms".equals(paramString1)) && ("com.jb.gosms.ui.ComposeMessageActivity".equals(paramString2))) || ("print_local".equals(paramString1))) {
        bool = true;
      }
    }
    while (((!"com.facebook.katana".equals(paramString1)) || ((!"com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias".equals(paramString2)) && (!"com.facebook.composer.shareintent.ImplicitShareIntentHandler".equals(paramString2)))) && ((!"com.whatsapp".equals(paramString1)) || (!"com.whatsapp.ContactPicker".equals(paramString2))) && ((!"com.instagram.android".equals(paramString1)) || (!"com.instagram.android.activity.ShareHandlerActivity".equals(paramString2))) && ((!"com.jb.gosms".equals(paramString1)) || (!"com.jb.gosms.ui.ComposeMessageActivity".equals(paramString2))) && (!"print_local".equals(paramString1))) {
      return bool;
    }
    return true;
  }
  
  public static boolean isFacebookInstalled(Context paramContext)
  {
    return getAppIsInstalled(paramContext, new String[] { "com.facebook.katana", "com.facebook.lite" });
  }
  
  private static boolean isFilterTools(ResolveInfo paramResolveInfo, String[] paramArrayOfString1, String[] paramArrayOfString2)
  {
    boolean bool2 = false;
    int j = paramArrayOfString1.length;
    String str = paramResolveInfo.activityInfo.packageName;
    paramResolveInfo = paramResolveInfo.activityInfo.name;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if ((paramArrayOfString1[i].equals(str)) && (paramArrayOfString2[i].equals(paramResolveInfo))) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public static boolean startCommonShareActivity(Context paramContext, String paramString1, String paramString2, Uri paramUri, boolean paramBoolean)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    if (paramBoolean) {
      localIntent.setType("image/*");
    }
    boolean bool;
    for (;;)
    {
      localIntent.putExtra("android.intent.extra.STREAM", paramUri);
      try
      {
        paramContext.startActivity(localIntent);
        bool = true;
        return bool;
      }
      catch (Throwable paramString2)
      {
        paramString2 = getAllShareTools(paramContext, paramBoolean).iterator();
        if (!paramString2.hasNext()) {
          break;
        }
      }
      localIntent.setType("video/*");
    }
    for (;;)
    {
      paramUri = (ShareImageItem.a)paramString2.next();
      if (paramUri.a().equals(paramString1)) {
        localIntent.setComponent(new ComponentName(paramUri.a(), paramUri.b()));
      }
    }
    for (paramBoolean = true;; paramBoolean = false)
    {
      bool = paramBoolean;
      if (!paramBoolean) {
        break;
      }
      try
      {
        paramContext.startActivity(localIntent);
        return paramBoolean;
      }
      catch (Throwable paramContext)
      {
        return false;
      }
    }
  }
  
  public static boolean startCommonShareMutilMediaActivity(Context paramContext, String paramString1, String paramString2, ArrayList<Uri> paramArrayList, int paramInt1, int paramInt2)
  {
    boolean bool2;
    if ((paramArrayList == null) || (paramArrayList.size() == 0))
    {
      bool2 = false;
      return bool2;
    }
    Intent localIntent = new Intent();
    if ((paramInt1 > 1) || (paramInt2 > 1) || ((paramInt1 > 0) && (paramInt2 > 0)))
    {
      localIntent.setAction("android.intent.action.SEND_MULTIPLE");
      localIntent.setComponent(new ComponentName(paramString1, paramString2));
      if ((paramInt1 <= 0) || (paramInt2 <= 0)) {
        break label131;
      }
      localIntent.setType("media/*");
      label89:
      if (!localIntent.getAction().equals("android.intent.action.SEND_MULTIPLE")) {
        break label158;
      }
      localIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", paramArrayList);
    }
    for (;;)
    {
      try
      {
        paramContext.startActivity(localIntent);
        return true;
      }
      catch (Throwable paramString2)
      {
        label131:
        label158:
        paramString2 = getAllShareMutilMediaTools(paramContext, paramInt1, paramInt2).iterator();
        if (!paramString2.hasNext()) {
          break label268;
        }
      }
      localIntent.setAction("android.intent.action.SEND");
      break;
      if (paramInt2 > 0)
      {
        localIntent.setType("video/*");
        break label89;
      }
      localIntent.setType("image/*");
      break label89;
      localIntent.putExtra("android.intent.extra.STREAM", (Parcelable)paramArrayList.get(0));
      continue;
      do
      {
        paramArrayList = (ShareImageItem.a)paramString2.next();
      } while (!paramArrayList.a().equals(paramString1));
      localIntent.setComponent(new ComponentName(paramArrayList.a(), paramArrayList.b()));
    }
    label268:
    for (boolean bool1 = true;; bool1 = false)
    {
      bool2 = bool1;
      if (!bool1) {
        break;
      }
      try
      {
        paramContext.startActivity(localIntent);
        return bool1;
      }
      catch (Throwable paramContext)
      {
        return false;
      }
    }
  }
  
  public static boolean startCommonShareTextActivity(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    localIntent.setType("text/plain");
    localIntent.putExtra("android.intent.extra.TEXT", paramString3);
    for (;;)
    {
      try
      {
        paramContext.startActivity(localIntent);
        return true;
      }
      catch (Throwable paramString2)
      {
        paramString2.printStackTrace();
        paramString2 = getAllShareTextTools(paramContext).iterator();
        if (!paramString2.hasNext()) {
          break;
        }
      }
      paramString3 = (ShareImageItem.a)paramString2.next();
      if (paramString3.a().equals(paramString1)) {
        localIntent.setComponent(new ComponentName(paramString3.a(), paramString3.b()));
      }
    }
    for (boolean bool = true;; bool = false) {
      try
      {
        paramContext.startActivity(localIntent);
        return bool;
      }
      catch (Throwable paramContext)
      {
        return false;
      }
    }
  }
  
  public static boolean startInstagramPrivateShareActivity(Context paramContext, String paramString1, String paramString2, File paramFile, boolean paramBoolean)
  {
    Intent localIntent;
    boolean bool;
    if ((paramFile != null) && (paramFile.exists()))
    {
      localIntent = new Intent("android.intent.action.SEND");
      localIntent.setComponent(new ComponentName(paramString1, paramString2));
      if (paramBoolean) {
        localIntent.setType("image/*");
      }
      for (;;)
      {
        localIntent.putExtra("android.intent.extra.STREAM", aa.a(paramFile));
        localIntent.putExtra("android.intent.extra.TEXT", "#iart camera");
        try
        {
          paramContext.startActivity(localIntent);
          bool = true;
          return bool;
        }
        catch (Throwable paramString2)
        {
          paramString2 = getAllShareTools(paramContext, paramBoolean).iterator();
          if (!paramString2.hasNext()) {
            break;
          }
        }
        localIntent.setType("video/*");
      }
      do
      {
        paramFile = (ShareImageItem.a)paramString2.next();
      } while (!paramFile.a().equals(paramString1));
      localIntent.setComponent(new ComponentName(paramFile.a(), paramFile.b()));
    }
    for (paramBoolean = true;; paramBoolean = false)
    {
      bool = paramBoolean;
      if (!paramBoolean) {
        break;
      }
      try
      {
        paramContext.startActivity(localIntent);
        return paramBoolean;
      }
      catch (Throwable paramContext)
      {
        return false;
      }
      return false;
    }
  }
  
  public static boolean startInstagramShareActivity(Context paramContext, String paramString1, String paramString2, Uri paramUri, boolean paramBoolean)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    if (paramBoolean) {
      localIntent.setType("image/*");
    }
    boolean bool;
    for (;;)
    {
      localIntent.putExtra("android.intent.extra.STREAM", paramUri);
      localIntent.putExtra("android.intent.extra.TEXT", "#iart camera");
      try
      {
        paramContext.startActivity(localIntent);
        bool = true;
        return bool;
      }
      catch (Throwable paramString2)
      {
        paramString2 = getAllShareTools(paramContext, paramBoolean).iterator();
        if (!paramString2.hasNext()) {
          break;
        }
      }
      localIntent.setType("video/*");
    }
    for (;;)
    {
      paramUri = (ShareImageItem.a)paramString2.next();
      if (paramUri.a().equals(paramString1)) {
        localIntent.setComponent(new ComponentName(paramUri.a(), paramUri.b()));
      }
    }
    for (paramBoolean = true;; paramBoolean = false)
    {
      bool = paramBoolean;
      if (!paramBoolean) {
        break;
      }
      try
      {
        paramContext.startActivity(localIntent);
        return paramBoolean;
      }
      catch (Throwable paramContext)
      {
        return false;
      }
    }
  }
  
  public static boolean startPrivateShareActivity(Context paramContext, String paramString1, String paramString2, File paramFile, boolean paramBoolean)
  {
    Intent localIntent;
    boolean bool;
    if ((paramFile != null) && (paramFile.exists()))
    {
      localIntent = new Intent("android.intent.action.SEND");
      localIntent.setComponent(new ComponentName(paramString1, paramString2));
      if (paramBoolean) {
        localIntent.setType("image/*");
      }
      for (;;)
      {
        localIntent.putExtra("android.intent.extra.STREAM", aa.a(paramFile));
        try
        {
          paramContext.startActivity(localIntent);
          bool = true;
          return bool;
        }
        catch (Throwable paramString2)
        {
          paramString2 = getAllShareTools(paramContext, paramBoolean).iterator();
          if (!paramString2.hasNext()) {
            break;
          }
        }
        localIntent.setType("video/*");
      }
      for (;;)
      {
        paramFile = (ShareImageItem.a)paramString2.next();
        if (paramFile.a().equals(paramString1)) {
          localIntent.setComponent(new ComponentName(paramFile.a(), paramFile.b()));
        }
      }
    }
    for (paramBoolean = true;; paramBoolean = false)
    {
      bool = paramBoolean;
      if (!paramBoolean) {
        break;
      }
      try
      {
        paramContext.startActivity(localIntent);
        return paramBoolean;
      }
      catch (Throwable paramContext)
      {
        return false;
      }
      return false;
    }
  }
  
  public static boolean startShareActivity(Context paramContext, String paramString1, String paramString2, BitmapBean paramBitmapBean)
  {
    return startCommonShareActivity(paramContext, paramString1, paramString2, paramBitmapBean.mUri, m.c(paramBitmapBean.mType));
  }
  
  public static boolean startShareTextActivity(Context paramContext, String paramString1, String paramString2, String[] paramArrayOfString)
  {
    IWXAPI localIWXAPI;
    int i;
    if (paramString1.equals("com.tencent.mm"))
    {
      localIWXAPI = WXAPIFactory.createWXAPI(paramContext, "wxedef4530c4aa9c59");
      if (localIWXAPI.getWXAppSupportAPI() >= 553779201) {
        if (paramString2.equals("com.tencent.mm.ui.tools.ShareToTimeLineUI")) {
          i = 1;
        }
      }
    }
    for (;;)
    {
      if (i != -1)
      {
        try
        {
          paramString1 = new WXWebpageObject();
          paramString1.webpageUrl = "http://fusion.qq.com/cgi-bin/qzapps/unified_jump?appid=11844344&from=singlemessage&isTimeline=false&actionFlag=0&params=pname%3Dcom.art.artcamera%26versioncode%3D31%26channelid%3D%26actionflag%3D0&isappinstalled=0";
          paramString1 = new WXMediaMessage(paramString1);
          paramString1.title = "极相机";
          paramString1.description = "回忆是生活的足迹，开启“极相机”记录你的点滴生活,让你成为生活中的摄影师！";
          if (i == 1) {
            paramString1.title = "回忆是生活的足迹，开启“极相机”记录你的点滴生活！";
          }
          paramString1.setThumbImage(BitmapFactory.decodeResource(paramContext.getResources(), d.f.iart_camera_main));
          paramContext = new SendMessageToWX.Req();
          paramContext.transaction = buildTransaction("webpage");
          paramContext.message = paramString1;
          paramContext.scene = i;
          localIWXAPI.sendReq(paramContext);
        }
        catch (Throwable paramContext)
        {
          for (;;)
          {
            paramContext.printStackTrace();
            if (b.a()) {
              b.c("ShareImageTools", paramContext.getMessage());
            }
          }
        }
        mIsStarted = false;
        return true;
        if (paramString2.equals("com.tencent.mm.ui.tools.ShareImgUI"))
        {
          i = 0;
          continue;
        }
        if (paramString2.equals("com.tencent.mm.ui.tools.AddFavoriteUI")) {
          i = 2;
        }
      }
      else
      {
        return startCommonShareTextActivity(paramContext, paramString1, paramString2, paramArrayOfString[1]);
      }
      i = -1;
    }
  }
}
