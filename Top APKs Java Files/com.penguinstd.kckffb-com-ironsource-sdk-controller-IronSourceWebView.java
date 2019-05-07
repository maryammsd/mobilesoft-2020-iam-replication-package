package com.ironsource.sdk.controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.MutableContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.ironsource.environment.ApplicationContext;
import com.ironsource.environment.ConnectivityService;
import com.ironsource.environment.DeviceStatus;
import com.ironsource.environment.LocationService;
import com.ironsource.environment.UrlHandler;
import com.ironsource.sdk.constants.Constants.JSMethods;
import com.ironsource.sdk.data.AdUnitsReady;
import com.ironsource.sdk.data.AdUnitsState;
import com.ironsource.sdk.data.DemandSource;
import com.ironsource.sdk.data.ProductParameters;
import com.ironsource.sdk.data.SSABCParameters;
import com.ironsource.sdk.data.SSAEnums.ControllerState;
import com.ironsource.sdk.data.SSAEnums.DebugMode;
import com.ironsource.sdk.data.SSAEnums.ProductType;
import com.ironsource.sdk.data.SSAFile;
import com.ironsource.sdk.data.SSAObj;
import com.ironsource.sdk.listeners.OnGenericFunctionListener;
import com.ironsource.sdk.listeners.OnOfferWallListener;
import com.ironsource.sdk.listeners.OnWebViewChangeListener;
import com.ironsource.sdk.listeners.internals.DSAdProductListener;
import com.ironsource.sdk.listeners.internals.DSBannerListener;
import com.ironsource.sdk.listeners.internals.DSInterstitialListener;
import com.ironsource.sdk.listeners.internals.DSRewardedVideoListener;
import com.ironsource.sdk.precache.DownloadManager;
import com.ironsource.sdk.precache.DownloadManager.OnPreCacheCompletion;
import com.ironsource.sdk.utils.DeviceProperties;
import com.ironsource.sdk.utils.IronSourceAsyncHttpRequestTask;
import com.ironsource.sdk.utils.IronSourceSharedPrefHelper;
import com.ironsource.sdk.utils.IronSourceStorageUtils;
import com.ironsource.sdk.utils.Logger;
import com.ironsource.sdk.utils.SDKUtils;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IronSourceWebView
  extends WebView
  implements DownloadManager.OnPreCacheCompletion, DownloadListener
{
  public static String APP_IDS;
  public static int DISPLAY_WEB_VIEW_INTENT;
  public static String EXTERNAL_URL;
  public static String IS_INSTALLED;
  public static String IS_STORE;
  public static String IS_STORE_CLOSE;
  private static String JSON_KEY_FAIL = "fail";
  private static String JSON_KEY_SUCCESS;
  public static int OPEN_URL_INTENT;
  public static String REQUEST_ID;
  public static String RESULT;
  public static String SECONDARY_WEB_VIEW;
  public static String WEBVIEW_TYPE;
  public static int mDebugMode = 0;
  private final String GENERIC_MESSAGE = "We're sorry, some error occurred. we will investigate it";
  private String PUB_TAG = "IronSource";
  private String TAG = IronSourceWebView.class.getSimpleName();
  private DownloadManager downloadManager;
  private Boolean isKitkatAndAbove = null;
  private boolean isRemoveCloseEventHandler;
  private String mBNAppKey;
  private String mBNUserId;
  private BannerJSAdapter mBannerJsAdapter;
  private String mCacheDirectory;
  private OnWebViewChangeListener mChangeListener;
  private CountDownTimer mCloseEventTimer;
  private BroadcastReceiver mConnectionReceiver = new IronSourceWebView.8(this);
  private ArrayList<String> mControllerCommandsQueue;
  private String mControllerKeyPressed = "interrupt";
  private FrameLayout mControllerLayout;
  private SSAEnums.ControllerState mControllerState = SSAEnums.ControllerState.None;
  Context mCurrentActivityContext;
  private View mCustomView;
  private WebChromeClient.CustomViewCallback mCustomViewCallback;
  private FrameLayout mCustomViewContainer;
  private DSBannerListener mDSBannerListener;
  private DSInterstitialListener mDSInterstitialListener;
  private DSRewardedVideoListener mDSRewardedVideoListener;
  private DemandSourceManager mDemandSourceManager;
  private boolean mGlobalControllerTimeFinish;
  private CountDownTimer mGlobalControllerTimer;
  private int mHiddenForceCloseHeight = 50;
  private String mHiddenForceCloseLocation = "top-right";
  private int mHiddenForceCloseWidth = 50;
  private String mISAppKey;
  private String mISUserId;
  private boolean mIsActivityThemeTranslucent = false;
  private boolean mIsImmersive = false;
  private CountDownTimer mLoadControllerTimer;
  private MOATJSAdapter mMoatJsAdapter;
  private String mOWAppKey;
  private String mOWCreditsAppKey;
  private boolean mOWCreditsMiss;
  private String mOWCreditsUserId;
  private Map<String, String> mOWExtraParameters;
  private String mOWUserId;
  private boolean mOWmiss;
  private OnGenericFunctionListener mOnGenericFunctionListener;
  private OnOfferWallListener mOnOfferWallListener;
  private String mOrientationState;
  private PermissionsJSAdapter mPermissionsJsAdapter;
  private ProductParametersCollection mProductParametersCollection = new ProductParametersCollection();
  private String mRVAppKey;
  private String mRVUserId;
  private String mRequestParameters;
  private AdUnitsState mSavedState;
  private Object mSavedStateLocker = new Object();
  private State mState;
  Handler mUiHandler;
  private Uri mUri;
  private VideoEventsListener mVideoEventsListener;
  private IronSourceWebView.ChromeClient mWebChromeClient;
  private WebViewMessagingMediator mWebViewMessagingMediator;
  
  static
  {
    IS_STORE = "is_store";
    IS_STORE_CLOSE = "is_store_close";
    WEBVIEW_TYPE = "webview_type";
    EXTERNAL_URL = "external_url";
    SECONDARY_WEB_VIEW = "secondary_web_view";
    DISPLAY_WEB_VIEW_INTENT = 0;
    OPEN_URL_INTENT = 1;
    APP_IDS = "appIds";
    REQUEST_ID = "requestId";
    IS_INSTALLED = "isInstalled";
    RESULT = "result";
    JSON_KEY_SUCCESS = "success";
  }
  
  public IronSourceWebView(Context paramContext, DemandSourceManager paramDemandSourceManager)
  {
    super(paramContext.getApplicationContext());
    Logger.i(this.TAG, "C'tor");
    this.mControllerCommandsQueue = new ArrayList();
    this.mCacheDirectory = initializeCacheDirectory(paramContext.getApplicationContext());
    this.mCurrentActivityContext = paramContext;
    this.mDemandSourceManager = paramDemandSourceManager;
    initLayout(this.mCurrentActivityContext);
    this.mSavedState = new AdUnitsState();
    this.downloadManager = getDownloadManager();
    this.downloadManager.setOnPreCacheCompletion(this);
    this.mWebChromeClient = new IronSourceWebView.ChromeClient(this, null);
    setWebViewClient(new IronSourceWebView.ViewClient(this, null));
    setWebChromeClient(this.mWebChromeClient);
    setWebViewSettings();
    addJavascriptInterface(createJSInterface(paramContext), "Android");
    setDownloadListener(this);
    setOnTouchListener(new IronSourceWebView.SupersonicWebViewTouchListener(this, null));
    this.mUiHandler = createMainThreadHandler();
  }
  
  private void closeWebView()
  {
    if (this.mChangeListener != null) {
      this.mChangeListener.onCloseRequested();
    }
  }
  
  private boolean controllerCommandSupportsQueue(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("updateConsentInfo");
    return localArrayList.contains(paramString);
  }
  
  private String createInitProductJSMethod(SSAEnums.ProductType paramProductType, DemandSource paramDemandSource)
  {
    Object localObject = "";
    if ((paramProductType == SSAEnums.ProductType.RewardedVideo) || (paramProductType == SSAEnums.ProductType.Interstitial) || (paramProductType == SSAEnums.ProductType.OfferWall) || (paramProductType == SSAEnums.ProductType.Banner))
    {
      localObject = new HashMap();
      ProductParameters localProductParameters = this.mProductParametersCollection.getProductParameters(paramProductType);
      if (localProductParameters != null)
      {
        ((Map)localObject).put("applicationKey", localProductParameters.appKey);
        ((Map)localObject).put("applicationUserId", localProductParameters.userId);
      }
      if (paramDemandSource != null)
      {
        if (paramDemandSource.getExtraParams() != null) {
          ((Map)localObject).putAll(paramDemandSource.getExtraParams());
        }
        ((Map)localObject).put("demandSourceName", paramDemandSource.getDemandSourceName());
        paramDemandSource = flatMapToJsonAsString((Map)localObject);
        paramProductType = Constants.JSMethods.getInitMethodByProduct(paramProductType);
        paramDemandSource = generateJSToInject(paramProductType.methodName, paramDemandSource, paramProductType.successCallbackName, paramProductType.failureCallbackName);
      }
    }
    do
    {
      return paramDemandSource;
      if (getExtraParamsByProduct(paramProductType) == null) {
        break;
      }
      ((Map)localObject).putAll(getExtraParamsByProduct(paramProductType));
      break;
      paramDemandSource = (DemandSource)localObject;
    } while (paramProductType != SSAEnums.ProductType.OfferWallCredits);
    return generateJSToInject("getUserCredits", parseToJson("productType", "OfferWall", "applicationKey", this.mOWCreditsAppKey, "applicationUserId", this.mOWCreditsUserId, null, null, null, false), "null", "onGetUserCreditsFail");
  }
  
  private SSAObj createLocationObject(String paramString, Location paramLocation)
  {
    paramString = new SSAObj(paramString);
    if (paramLocation != null)
    {
      paramString.put("provider", paramLocation.getProvider());
      paramString.put("latitude", Double.toString(paramLocation.getLatitude()));
      paramString.put("longitude", Double.toString(paramLocation.getLongitude()));
      paramString.put("altitude", Double.toString(paramLocation.getAltitude()));
      paramString.put("time", Long.toString(paramLocation.getTime()));
      paramString.put("accuracy", Float.toString(paramLocation.getAccuracy()));
      paramString.put("bearing", Float.toString(paramLocation.getBearing()));
      paramString.put("speed", Float.toString(paramLocation.getSpeed()));
      return paramString;
    }
    paramString.put("error", "location data is not available");
    return paramString;
  }
  
  private String createShowProductJSMethod(SSAEnums.ProductType paramProductType, JSONObject paramJSONObject)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("sessionDepth", Integer.toString(paramJSONObject.optInt("sessionDepth")));
    paramJSONObject = paramJSONObject.optString("demandSourceName");
    DemandSource localDemandSource = this.mDemandSourceManager.getDemandSourceByName(paramProductType, paramJSONObject);
    if (localDemandSource != null)
    {
      if (localDemandSource.getExtraParams() != null) {
        localHashMap.putAll(localDemandSource.getExtraParams());
      }
      if (!TextUtils.isEmpty(paramJSONObject)) {
        localHashMap.put("demandSourceName", paramJSONObject);
      }
    }
    for (;;)
    {
      paramJSONObject = flatMapToJsonAsString(localHashMap);
      paramProductType = Constants.JSMethods.getShowMethodByProduct(paramProductType);
      return generateJSToInject(paramProductType.methodName, paramJSONObject, paramProductType.successCallbackName, paramProductType.failureCallbackName);
      if (getExtraParamsByProduct(paramProductType) != null) {
        localHashMap.putAll(getExtraParamsByProduct(paramProductType));
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  private void evaluateJavascriptKitKat(String paramString)
  {
    evaluateJavascript(paramString, null);
  }
  
  private String extractFailFunctionToCall(String paramString)
  {
    return new SSAObj(paramString).getString(JSON_KEY_FAIL);
  }
  
  private String extractSuccessFunctionToCall(String paramString)
  {
    return new SSAObj(paramString).getString(JSON_KEY_SUCCESS);
  }
  
  private String flatMapToJsonAsString(Map<String, String> paramMap)
  {
    JSONObject localJSONObject = new JSONObject();
    if (paramMap != null)
    {
      paramMap = paramMap.entrySet().iterator();
      for (;;)
      {
        if (paramMap.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)paramMap.next();
          try
          {
            localJSONObject.putOpt((String)localEntry.getKey(), SDKUtils.encodeString((String)localEntry.getValue()));
            paramMap.remove();
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              Logger.i(this.TAG, "flatMapToJsonAsStringfailed " + localJSONException.toString());
            }
          }
        }
      }
    }
    return localJSONObject.toString();
  }
  
  private String generateJSToInject(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString).append("');");
    return localStringBuilder.toString();
  }
  
  private String generateJSToInject(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString1).append("?parameters=").append(paramString2).append("');");
    return localStringBuilder.toString();
  }
  
  private String generateJSToInject(String paramString1, String paramString2, String paramString3)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString1).append("','").append(paramString2).append("','").append(paramString3).append("');");
    return localStringBuilder.toString();
  }
  
  private String generateJSToInject(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SSA_CORE.SDKController.runFunction('").append(paramString1).append("?parameters=").append(paramString2).append("','").append(paramString3).append("','").append(paramString4).append("');");
    return localStringBuilder.toString();
  }
  
  private DSAdProductListener getAdProductListenerByProductType(SSAEnums.ProductType paramProductType)
  {
    if (paramProductType == SSAEnums.ProductType.Interstitial) {
      return this.mDSInterstitialListener;
    }
    if (paramProductType == SSAEnums.ProductType.RewardedVideo) {
      return this.mDSRewardedVideoListener;
    }
    if (paramProductType == SSAEnums.ProductType.Banner) {
      return this.mDSBannerListener;
    }
    return null;
  }
  
  /* Error */
  private Object[] getApplicationParams(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: new 794	org/json/JSONObject
    //   5: dup
    //   6: invokespecial 835	org/json/JSONObject:<init>	()V
    //   9: astore 9
    //   11: ldc_w 627
    //   14: astore 4
    //   16: ldc_w 627
    //   19: astore 6
    //   21: aconst_null
    //   22: astore 7
    //   24: aconst_null
    //   25: astore 5
    //   27: aload_1
    //   28: invokestatic 818	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   31: ifne +369 -> 400
    //   34: aload_0
    //   35: aload_1
    //   36: invokespecial 539	com/ironsource/sdk/controller/IronSourceWebView:getStringProductTypeAsEnum	(Ljava/lang/String;)Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   39: astore 10
    //   41: aload 10
    //   43: getstatic 633	com/ironsource/sdk/data/SSAEnums$ProductType:RewardedVideo	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   46: if_acmpeq +19 -> 65
    //   49: aload 10
    //   51: getstatic 636	com/ironsource/sdk/data/SSAEnums$ProductType:Interstitial	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   54: if_acmpeq +11 -> 65
    //   57: aload 10
    //   59: getstatic 642	com/ironsource/sdk/data/SSAEnums$ProductType:Banner	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   62: if_acmpne +280 -> 342
    //   65: aload_0
    //   66: getfield 248	com/ironsource/sdk/controller/IronSourceWebView:mProductParametersCollection	Lcom/ironsource/sdk/controller/ProductParametersCollection;
    //   69: aload 10
    //   71: invokevirtual 649	com/ironsource/sdk/controller/ProductParametersCollection:getProductParameters	(Lcom/ironsource/sdk/data/SSAEnums$ProductType;)Lcom/ironsource/sdk/data/ProductParameters;
    //   74: astore 4
    //   76: aload 4
    //   78: getfield 656	com/ironsource/sdk/data/ProductParameters:appKey	Ljava/lang/String;
    //   81: astore 7
    //   83: aload 4
    //   85: getfield 667	com/ironsource/sdk/data/ProductParameters:userId	Ljava/lang/String;
    //   88: astore 8
    //   90: aload_0
    //   91: getfield 278	com/ironsource/sdk/controller/IronSourceWebView:mDemandSourceManager	Lcom/ironsource/sdk/controller/DemandSourceManager;
    //   94: aload 10
    //   96: aload_2
    //   97: invokevirtual 812	com/ironsource/sdk/controller/DemandSourceManager:getDemandSourceByName	(Lcom/ironsource/sdk/data/SSAEnums$ProductType;Ljava/lang/String;)Lcom/ironsource/sdk/data/DemandSource;
    //   100: astore 10
    //   102: aload 7
    //   104: astore 4
    //   106: aload 8
    //   108: astore 6
    //   110: aload 10
    //   112: ifnull +30 -> 142
    //   115: aload 10
    //   117: invokevirtual 673	com/ironsource/sdk/data/DemandSource:getExtraParams	()Ljava/util/Map;
    //   120: astore 5
    //   122: aload 5
    //   124: ldc_w 679
    //   127: aload_2
    //   128: invokeinterface 662 3 0
    //   133: pop
    //   134: aload 8
    //   136: astore 6
    //   138: aload 7
    //   140: astore 4
    //   142: aload 9
    //   144: ldc_w 712
    //   147: aload_1
    //   148: invokevirtual 903	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   151: pop
    //   152: aload 6
    //   154: invokestatic 818	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   157: ifne +281 -> 438
    //   160: aload 9
    //   162: ldc_w 664
    //   165: invokestatic 870	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   168: aload 6
    //   170: invokestatic 870	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   173: invokevirtual 903	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   176: pop
    //   177: aload 4
    //   179: invokestatic 818	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   182: ifne +290 -> 472
    //   185: aload 9
    //   187: ldc_w 651
    //   190: invokestatic 870	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   193: aload 4
    //   195: invokestatic 870	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   198: invokevirtual 903	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   201: pop
    //   202: aload 5
    //   204: ifnull +273 -> 477
    //   207: aload 5
    //   209: invokeinterface 905 1 0
    //   214: ifne +263 -> 477
    //   217: aload 5
    //   219: invokeinterface 839 1 0
    //   224: invokeinterface 845 1 0
    //   229: astore_1
    //   230: aload_1
    //   231: invokeinterface 851 1 0
    //   236: ifeq +241 -> 477
    //   239: aload_1
    //   240: invokeinterface 855 1 0
    //   245: checkcast 857	java/util/Map$Entry
    //   248: astore_2
    //   249: aload_2
    //   250: invokeinterface 860 1 0
    //   255: checkcast 862	java/lang/String
    //   258: ldc_w 907
    //   261: invokevirtual 910	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   264: ifeq +16 -> 280
    //   267: aload_0
    //   268: aload_2
    //   269: invokeinterface 865 1 0
    //   274: checkcast 862	java/lang/String
    //   277: invokespecial 913	com/ironsource/sdk/controller/IronSourceWebView:setWebviewCache	(Ljava/lang/String;)V
    //   280: aload 9
    //   282: aload_2
    //   283: invokeinterface 860 1 0
    //   288: checkcast 862	java/lang/String
    //   291: invokestatic 870	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   294: aload_2
    //   295: invokeinterface 865 1 0
    //   300: checkcast 862	java/lang/String
    //   303: invokestatic 870	com/ironsource/sdk/utils/SDKUtils:encodeString	(Ljava/lang/String;)Ljava/lang/String;
    //   306: invokevirtual 903	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   309: pop
    //   310: goto -80 -> 230
    //   313: astore_2
    //   314: aload_2
    //   315: invokevirtual 916	org/json/JSONException:printStackTrace	()V
    //   318: new 918	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   321: dup
    //   322: invokespecial 919	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   325: iconst_1
    //   326: anewarray 862	java/lang/String
    //   329: dup
    //   330: iconst_0
    //   331: ldc_w 921
    //   334: aastore
    //   335: invokevirtual 925	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   338: pop
    //   339: goto -109 -> 230
    //   342: aload 10
    //   344: getstatic 639	com/ironsource/sdk/data/SSAEnums$ProductType:OfferWall	Lcom/ironsource/sdk/data/SSAEnums$ProductType;
    //   347: if_acmpne -205 -> 142
    //   350: aload_0
    //   351: getfield 427	com/ironsource/sdk/controller/IronSourceWebView:mOWAppKey	Ljava/lang/String;
    //   354: astore 4
    //   356: aload_0
    //   357: getfield 430	com/ironsource/sdk/controller/IronSourceWebView:mOWUserId	Ljava/lang/String;
    //   360: astore 6
    //   362: aload_0
    //   363: getfield 434	com/ironsource/sdk/controller/IronSourceWebView:mOWExtraParameters	Ljava/util/Map;
    //   366: astore 5
    //   368: goto -226 -> 142
    //   371: astore_1
    //   372: aload_1
    //   373: invokevirtual 916	org/json/JSONException:printStackTrace	()V
    //   376: new 918	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   379: dup
    //   380: invokespecial 919	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   383: iconst_1
    //   384: anewarray 862	java/lang/String
    //   387: dup
    //   388: iconst_0
    //   389: ldc_w 927
    //   392: aastore
    //   393: invokevirtual 925	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   396: pop
    //   397: goto -245 -> 152
    //   400: iconst_1
    //   401: istore_3
    //   402: aload 7
    //   404: astore 5
    //   406: goto -254 -> 152
    //   409: astore_1
    //   410: aload_1
    //   411: invokevirtual 916	org/json/JSONException:printStackTrace	()V
    //   414: new 918	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   417: dup
    //   418: invokespecial 919	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   421: iconst_1
    //   422: anewarray 862	java/lang/String
    //   425: dup
    //   426: iconst_0
    //   427: ldc_w 929
    //   430: aastore
    //   431: invokevirtual 925	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   434: pop
    //   435: goto -258 -> 177
    //   438: iconst_1
    //   439: istore_3
    //   440: goto -263 -> 177
    //   443: astore_1
    //   444: aload_1
    //   445: invokevirtual 916	org/json/JSONException:printStackTrace	()V
    //   448: new 918	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask
    //   451: dup
    //   452: invokespecial 919	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:<init>	()V
    //   455: iconst_1
    //   456: anewarray 862	java/lang/String
    //   459: dup
    //   460: iconst_0
    //   461: ldc_w 931
    //   464: aastore
    //   465: invokevirtual 925	com/ironsource/sdk/utils/IronSourceAsyncHttpRequestTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   468: pop
    //   469: goto -267 -> 202
    //   472: iconst_1
    //   473: istore_3
    //   474: goto -272 -> 202
    //   477: iconst_2
    //   478: anewarray 235	java/lang/Object
    //   481: dup
    //   482: iconst_0
    //   483: aload 9
    //   485: invokevirtual 890	org/json/JSONObject:toString	()Ljava/lang/String;
    //   488: aastore
    //   489: dup
    //   490: iconst_1
    //   491: iload_3
    //   492: invokestatic 937	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   495: aastore
    //   496: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	497	0	this	IronSourceWebView
    //   0	497	1	paramString1	String
    //   0	497	2	paramString2	String
    //   1	491	3	bool	boolean
    //   14	341	4	localObject1	Object
    //   25	380	5	localObject2	Object
    //   19	342	6	localObject3	Object
    //   22	381	7	str1	String
    //   88	47	8	str2	String
    //   9	475	9	localJSONObject	JSONObject
    //   39	304	10	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   280	310	313	org/json/JSONException
    //   142	152	371	org/json/JSONException
    //   160	177	409	org/json/JSONException
    //   185	202	443	org/json/JSONException
  }
  
  private Object[] getAppsStatus(String paramString1, String paramString2)
  {
    bool = false;
    JSONObject localJSONObject1 = new JSONObject();
    for (;;)
    {
      try
      {
        if ((TextUtils.isEmpty(paramString1)) || (paramString1.equalsIgnoreCase("null"))) {
          continue;
        }
        if ((TextUtils.isEmpty(paramString2)) || (paramString2.equalsIgnoreCase("null"))) {
          continue;
        }
        List localList = DeviceStatus.getInstalledApplications(getContext());
        paramString1 = new JSONArray(paramString1);
        JSONObject localJSONObject2 = new JSONObject();
        i = 0;
        if (i < paramString1.length())
        {
          String str = paramString1.getString(i).trim();
          if (TextUtils.isEmpty(str)) {
            continue;
          }
          JSONObject localJSONObject3 = new JSONObject();
          int k = 0;
          Iterator localIterator = localList.iterator();
          int j = k;
          if (localIterator.hasNext())
          {
            if (!str.equalsIgnoreCase(((ApplicationInfo)localIterator.next()).packageName)) {
              continue;
            }
            localJSONObject3.put(IS_INSTALLED, true);
            localJSONObject2.put(str, localJSONObject3);
            j = 1;
          }
          if (j != 0) {
            continue;
          }
          localJSONObject3.put(IS_INSTALLED, false);
          localJSONObject2.put(str, localJSONObject3);
          continue;
        }
        localJSONObject1.put(RESULT, localJSONObject2);
        localJSONObject1.put(REQUEST_ID, paramString2);
      }
      catch (Exception paramString1)
      {
        int i;
        bool = true;
        continue;
      }
      return new Object[] { localJSONObject1.toString(), Boolean.valueOf(bool) };
      bool = true;
      localJSONObject1.put("error", "requestId is null or empty");
      continue;
      bool = true;
      localJSONObject1.put("error", "appIds is null or empty");
      continue;
      i += 1;
    }
  }
  
  private Object[] getDeviceParams(Context paramContext)
  {
    bool3 = false;
    boolean bool2 = false;
    Object localObject1 = DeviceProperties.getInstance(paramContext);
    JSONObject localJSONObject = new JSONObject();
    bool1 = bool3;
    for (;;)
    {
      try
      {
        localJSONObject.put("appOrientation", SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(getCurrentActivityContext())));
        bool1 = bool3;
        Object localObject2 = ((DeviceProperties)localObject1).getDeviceOem();
        if (localObject2 != null)
        {
          bool1 = bool3;
          localJSONObject.put(SDKUtils.encodeString("deviceOEM"), SDKUtils.encodeString((String)localObject2));
        }
        bool1 = bool3;
        localObject2 = ((DeviceProperties)localObject1).getDeviceModel();
        if (localObject2 == null) {
          continue;
        }
        bool1 = bool3;
        localJSONObject.put(SDKUtils.encodeString("deviceModel"), SDKUtils.encodeString((String)localObject2));
        bool1 = bool2;
        SDKUtils.loadGoogleAdvertiserInfo(paramContext);
        bool1 = bool2;
        localObject2 = SDKUtils.getAdvertiserId();
        bool1 = bool2;
        bool3 = SDKUtils.isLimitAdTrackingEnabled();
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject2))
        {
          bool1 = bool2;
          Logger.i(this.TAG, "add AID and LAT");
          bool1 = bool2;
          localJSONObject.put("isLimitAdTrackingEnabled", Boolean.valueOf(bool3));
          bool1 = bool2;
          localJSONObject.put("deviceIds" + "[" + "AID" + "]", SDKUtils.encodeString((String)localObject2));
        }
        bool1 = bool2;
        localObject2 = ((DeviceProperties)localObject1).getDeviceOsType();
        if (localObject2 == null) {
          continue;
        }
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("deviceOs"), SDKUtils.encodeString((String)localObject2));
        bool1 = bool2;
        localObject2 = ((DeviceProperties)localObject1).getDeviceOsVersion();
        if (localObject2 == null) {
          continue;
        }
        bool1 = bool2;
        localObject2 = ((String)localObject2).replaceAll("[^0-9/.]", "");
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("deviceOSVersion"), localObject2);
        bool1 = bool2;
        localObject2 = String.valueOf(((DeviceProperties)localObject1).getDeviceApiLevel());
        if (localObject2 == null) {
          continue;
        }
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("deviceApiLevel"), localObject2);
        bool1 = bool2;
        localObject2 = DeviceProperties.getSupersonicSdkVersion();
        if (localObject2 != null)
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("SDKVersion"), SDKUtils.encodeString((String)localObject2));
        }
        bool1 = bool2;
        if (((DeviceProperties)localObject1).getDeviceCarrier() != null)
        {
          bool1 = bool2;
          if (((DeviceProperties)localObject1).getDeviceCarrier().length() > 0)
          {
            bool1 = bool2;
            localJSONObject.put(SDKUtils.encodeString("mobileCarrier"), SDKUtils.encodeString(((DeviceProperties)localObject1).getDeviceCarrier()));
          }
        }
        bool1 = bool2;
        localObject1 = ConnectivityService.getConnectionType(paramContext);
        bool1 = bool2;
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          continue;
        }
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("connectionType"), SDKUtils.encodeString((String)localObject1));
        bool1 = bool2;
        localObject1 = paramContext.getResources().getConfiguration().locale.getLanguage();
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("deviceLanguage"), SDKUtils.encodeString(((String)localObject1).toUpperCase()));
        }
        bool1 = bool2;
        if (!SDKUtils.isExternalStorageAvailable()) {
          continue;
        }
        bool1 = bool2;
        long l = DeviceStatus.getAvailableMemorySizeInMegaBytes(this.mCacheDirectory);
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("diskFreeSize"), SDKUtils.encodeString(String.valueOf(l)));
        bool1 = bool2;
        localObject1 = String.valueOf(DeviceStatus.getDeviceWidth());
        bool1 = bool2;
        if (TextUtils.isEmpty((CharSequence)localObject1)) {
          continue;
        }
        bool1 = bool2;
        localObject2 = new StringBuilder();
        bool1 = bool2;
        ((StringBuilder)localObject2).append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("width")).append("]");
        bool1 = bool2;
        localJSONObject.put(((StringBuilder)localObject2).toString(), SDKUtils.encodeString((String)localObject1));
        bool1 = bool2;
        int i = DeviceStatus.getDeviceHeight();
        bool1 = bool2;
        localObject1 = new StringBuilder();
        bool1 = bool2;
        ((StringBuilder)localObject1).append(SDKUtils.encodeString("deviceScreenSize")).append("[").append(SDKUtils.encodeString("height")).append("]");
        bool1 = bool2;
        localJSONObject.put(((StringBuilder)localObject1).toString(), SDKUtils.encodeString(String.valueOf(i)));
        bool1 = bool2;
        localObject1 = ApplicationContext.getPackageName(getContext());
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("bundleId"), SDKUtils.encodeString((String)localObject1));
        }
        bool1 = bool2;
        localObject1 = String.valueOf(DeviceStatus.getDeviceDensity());
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("deviceScreenScale"), SDKUtils.encodeString((String)localObject1));
        }
        bool1 = bool2;
        localObject1 = String.valueOf(DeviceStatus.isRootedDevice());
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("unLocked"), SDKUtils.encodeString((String)localObject1));
        }
        bool1 = bool2;
        float f = DeviceProperties.getInstance(paramContext).getDeviceVolume(paramContext);
        bool1 = bool2;
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("deviceVolume"), f);
        }
        bool1 = bool2;
        paramContext = getCurrentActivityContext();
        bool1 = bool2;
        if (Build.VERSION.SDK_INT >= 19)
        {
          bool1 = bool2;
          if ((paramContext instanceof Activity))
          {
            bool1 = bool2;
            localJSONObject.put(SDKUtils.encodeString("immersiveMode"), DeviceStatus.isImmersiveSupported((Activity)paramContext));
          }
        }
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("batteryLevel"), DeviceStatus.getBatteryLevel(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("mcc"), ConnectivityService.getNetworkMCC(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("mnc"), ConnectivityService.getNetworkMNC(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("phoneType"), ConnectivityService.getPhoneType(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("simOperator"), SDKUtils.encodeString(ConnectivityService.getSimOperator(paramContext)));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("lastUpdateTime"), ApplicationContext.getLastUpdateTime(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("firstInstallTime"), ApplicationContext.getFirstInstallTime(paramContext));
        bool1 = bool2;
        localJSONObject.put(SDKUtils.encodeString("appVersion"), SDKUtils.encodeString(ApplicationContext.getApplicationVersionName(paramContext)));
        bool1 = bool2;
        paramContext = ApplicationContext.getInstallerPackageName(paramContext);
        bool3 = bool2;
        bool1 = bool2;
        if (!TextUtils.isEmpty(paramContext))
        {
          bool1 = bool2;
          localJSONObject.put(SDKUtils.encodeString("installerPackageName"), SDKUtils.encodeString(paramContext));
          bool3 = bool2;
        }
      }
      catch (JSONException paramContext)
      {
        paramContext.printStackTrace();
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
        bool3 = bool1;
        continue;
      }
      return new Object[] { localJSONObject.toString(), Boolean.valueOf(bool3) };
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
      continue;
      bool2 = true;
    }
  }
  
  private Map<String, String> getExtraParamsByProduct(SSAEnums.ProductType paramProductType)
  {
    if (paramProductType == SSAEnums.ProductType.OfferWall) {
      return this.mOWExtraParameters;
    }
    return null;
  }
  
  private String getRequestParameters(JSONObject paramJSONObject)
  {
    Object localObject = DeviceProperties.getInstance(getContext());
    StringBuilder localStringBuilder = new StringBuilder();
    String str1 = DeviceProperties.getSupersonicSdkVersion();
    if (!TextUtils.isEmpty(str1)) {
      localStringBuilder.append("SDKVersion").append("=").append(str1).append("&");
    }
    localObject = ((DeviceProperties)localObject).getDeviceOsType();
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      localStringBuilder.append("deviceOs").append("=").append((String)localObject);
    }
    localObject = Uri.parse(SDKUtils.getControllerUrl());
    if (localObject != null)
    {
      String str2 = ((Uri)localObject).getScheme() + ":";
      str1 = ((Uri)localObject).getHost();
      int i = ((Uri)localObject).getPort();
      localObject = str1;
      if (i != -1) {
        localObject = str1 + ":" + i;
      }
      localStringBuilder.append("&").append("protocol").append("=").append(str2);
      localStringBuilder.append("&").append("domain").append("=").append((String)localObject);
      if (!paramJSONObject.keys().hasNext()) {}
    }
    try
    {
      paramJSONObject = new JSONObject(paramJSONObject, new String[] { "isSecured", "applicationKey" }).toString();
      if (!TextUtils.isEmpty(paramJSONObject)) {
        localStringBuilder.append("&").append("controllerConfig").append("=").append(paramJSONObject);
      }
      localStringBuilder.append("&").append("debug").append("=").append(getDebugMode());
      return localStringBuilder.toString();
    }
    catch (JSONException paramJSONObject)
    {
      for (;;)
      {
        paramJSONObject.printStackTrace();
      }
    }
  }
  
  private SSAEnums.ProductType getStringProductTypeAsEnum(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return null;
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
        return SSAEnums.ProductType.Interstitial;
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
        return SSAEnums.ProductType.RewardedVideo;
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
        return SSAEnums.ProductType.OfferWall;
      }
    } while (!paramString.equalsIgnoreCase(SSAEnums.ProductType.Banner.toString()));
    return SSAEnums.ProductType.Banner;
  }
  
  private WebView getWebview()
  {
    return this;
  }
  
  private void initLayout(Context paramContext)
  {
    FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-1, -1);
    this.mControllerLayout = new FrameLayout(paramContext);
    this.mCustomViewContainer = new FrameLayout(paramContext);
    FrameLayout.LayoutParams localLayoutParams2 = new FrameLayout.LayoutParams(-1, -1);
    this.mCustomViewContainer.setLayoutParams(localLayoutParams2);
    this.mCustomViewContainer.setVisibility(8);
    paramContext = new FrameLayout(paramContext);
    paramContext.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    paramContext.addView(this);
    this.mControllerLayout.addView(this.mCustomViewContainer, localLayoutParams1);
    this.mControllerLayout.addView(paramContext);
  }
  
  private void initProduct(String paramString1, String paramString2, SSAEnums.ProductType paramProductType, DemandSource paramDemandSource, String paramString3)
  {
    if ((TextUtils.isEmpty(paramString2)) || (TextUtils.isEmpty(paramString1))) {
      triggerOnControllerInitProductFail("User id or Application key are missing", paramProductType, paramDemandSource.getDemandSourceName());
    }
    do
    {
      return;
      if (this.mControllerState == SSAEnums.ControllerState.Ready)
      {
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setApplicationKey(paramString1, paramProductType);
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUserID(paramString2, paramProductType);
        injectJavascript(createInitProductJSMethod(paramProductType, paramDemandSource));
        return;
      }
      setMissProduct(paramProductType, paramDemandSource);
      if (this.mControllerState == SSAEnums.ControllerState.Failed)
      {
        triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(paramString3, "Initiating Controller"), paramProductType, paramDemandSource.getDemandSourceName());
        return;
      }
    } while (!this.mGlobalControllerTimeFinish);
    downloadController();
  }
  
  private void injectJavascript(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    Object localObject2 = "empty";
    Object localObject1;
    if (getDebugMode() == SSAEnums.DebugMode.MODE_0.getValue()) {
      localObject1 = "console.log(\"JS exeption: \" + JSON.stringify(e));";
    }
    for (;;)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("try{").append(paramString).append("}catch(e){").append((String)localObject1).append("}");
      runOnUiThread(new IronSourceWebView.6(this, "javascript:" + ((StringBuilder)localObject2).toString(), (StringBuilder)localObject2));
      return;
      localObject1 = localObject2;
      if (getDebugMode() >= SSAEnums.DebugMode.MODE_1.getValue())
      {
        localObject1 = localObject2;
        if (getDebugMode() <= SSAEnums.DebugMode.MODE_3.getValue()) {
          localObject1 = "console.log(\"JS exeption: \" + JSON.stringify(e));";
        }
      }
    }
  }
  
  private void injectJavascript(String paramString1, String paramString2)
  {
    if ((!isControllerStateReady()) && (controllerCommandSupportsQueue(paramString1)))
    {
      this.mControllerCommandsQueue.add(paramString2);
      return;
    }
    injectJavascript(paramString2);
  }
  
  private void invokePendingCommands()
  {
    while (this.mControllerCommandsQueue.size() > 0)
    {
      injectJavascript((String)this.mControllerCommandsQueue.get(0));
      this.mControllerCommandsQueue.remove(0);
    }
  }
  
  private boolean isControllerStateReady()
  {
    return SSAEnums.ControllerState.Ready.equals(this.mControllerState);
  }
  
  private String mapToJson(Map<String, String> paramMap)
  {
    JSONObject localJSONObject = new JSONObject();
    if ((paramMap != null) && (!paramMap.isEmpty()))
    {
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        String str2 = (String)paramMap.get(str1);
        try
        {
          localJSONObject.put(str1, SDKUtils.encodeString(str2));
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
      }
    }
    return localJSONObject.toString();
  }
  
  private String parseToJson(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString2))) {
        localJSONObject.put(paramString1, SDKUtils.encodeString(paramString2));
      }
      if ((!TextUtils.isEmpty(paramString3)) && (!TextUtils.isEmpty(paramString4))) {
        localJSONObject.put(paramString3, SDKUtils.encodeString(paramString4));
      }
      if ((!TextUtils.isEmpty(paramString5)) && (!TextUtils.isEmpty(paramString6))) {
        localJSONObject.put(paramString5, SDKUtils.encodeString(paramString6));
      }
      if ((!TextUtils.isEmpty(paramString7)) && (!TextUtils.isEmpty(paramString8))) {
        localJSONObject.put(paramString7, SDKUtils.encodeString(paramString8));
      }
      if (!TextUtils.isEmpty(paramString9)) {
        localJSONObject.put(paramString9, paramBoolean);
      }
    }
    catch (JSONException paramString1)
    {
      for (;;)
      {
        paramString1.printStackTrace();
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramString1.getStackTrace()[0].getMethodName() });
      }
    }
    return localJSONObject.toString();
  }
  
  private void responseBack(String paramString1, boolean paramBoolean, String paramString2, String paramString3)
  {
    Object localObject = new SSAObj(paramString1);
    String str1 = ((SSAObj)localObject).getString(JSON_KEY_SUCCESS);
    String str2 = ((SSAObj)localObject).getString(JSON_KEY_FAIL);
    localObject = null;
    if (paramBoolean) {
      if (!TextUtils.isEmpty(str1)) {
        localObject = str1;
      }
    }
    for (;;)
    {
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        str1 = paramString1;
        if (TextUtils.isEmpty(paramString2)) {}
      }
      try
      {
        str1 = new JSONObject(paramString1).put("errMsg", paramString2).toString();
        paramString1 = str1;
        if (!TextUtils.isEmpty(paramString3)) {}
        try
        {
          paramString1 = new JSONObject(str1).put("errCode", paramString3).toString();
          injectJavascript(generateJSToInject((String)localObject, paramString1));
          return;
          if (TextUtils.isEmpty(str2)) {
            continue;
          }
          localObject = str2;
        }
        catch (JSONException paramString1)
        {
          for (;;)
          {
            paramString1 = str1;
          }
        }
      }
      catch (JSONException paramString2)
      {
        for (;;)
        {
          str1 = paramString1;
        }
      }
    }
  }
  
  private void sendProductErrorMessage(SSAEnums.ProductType paramProductType, String paramString)
  {
    String str = "";
    switch (IronSourceWebView.9.$SwitchMap$com$ironsource$sdk$data$SSAEnums$ProductType[paramProductType.ordinal()])
    {
    }
    for (;;)
    {
      triggerOnControllerInitProductFail(SDKUtils.createErrorMessage(str, "Initiating Controller"), paramProductType, paramString);
      return;
      str = "Init RV";
      continue;
      str = "Init IS";
      continue;
      str = "Init OW";
      continue;
      str = "Show OW Credits";
      continue;
      str = "Init BN";
    }
  }
  
  private void setDisplayZoomControls(WebSettings paramWebSettings)
  {
    if (Build.VERSION.SDK_INT > 11) {
      paramWebSettings.setDisplayZoomControls(false);
    }
  }
  
  public static void setEXTERNAL_URL(String paramString)
  {
    EXTERNAL_URL = paramString;
  }
  
  @SuppressLint({"NewApi"})
  private void setMediaPlaybackJellyBean(WebSettings paramWebSettings)
  {
    if (Build.VERSION.SDK_INT >= 17) {
      paramWebSettings.setMediaPlaybackRequiresUserGesture(false);
    }
  }
  
  @SuppressLint({"NewApi"})
  private void setWebDebuggingEnabled()
  {
    if (Build.VERSION.SDK_INT >= 19) {
      setWebContentsDebuggingEnabled(true);
    }
  }
  
  private void setWebDebuggingEnabled(JSONObject paramJSONObject)
  {
    if (paramJSONObject.optBoolean("inspectWebview")) {
      setWebDebuggingEnabled();
    }
  }
  
  private void setWebViewSettings()
  {
    WebSettings localWebSettings = getSettings();
    localWebSettings.setLoadWithOverviewMode(true);
    localWebSettings.setUseWideViewPort(true);
    setVerticalScrollBarEnabled(false);
    setHorizontalScrollBarEnabled(false);
    if (Build.VERSION.SDK_INT >= 16) {}
    try
    {
      getSettings().setAllowFileAccessFromFileURLs(true);
      localWebSettings.setBuiltInZoomControls(false);
      localWebSettings.setJavaScriptEnabled(true);
      localWebSettings.setSupportMultipleWindows(true);
      localWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
      localWebSettings.setGeolocationEnabled(true);
      localWebSettings.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
      localWebSettings.setDomStorageEnabled(true);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          setDisplayZoomControls(localWebSettings);
          setMediaPlaybackJellyBean(localWebSettings);
          return;
        }
        catch (Throwable localThrowable)
        {
          Logger.e(this.TAG, "setWebSettings - " + localThrowable.toString());
        }
        localException = localException;
        localException.printStackTrace();
      }
    }
  }
  
  private void setWebviewBackground(String paramString)
  {
    paramString = new SSAObj(paramString).getString("color");
    int i = 0;
    if (!"transparent".equalsIgnoreCase(paramString)) {
      i = Color.parseColor(paramString);
    }
    setBackgroundColor(i);
  }
  
  private void setWebviewCache(String paramString)
  {
    if (paramString.equalsIgnoreCase("0"))
    {
      getSettings().setCacheMode(2);
      return;
    }
    getSettings().setCacheMode(-1);
  }
  
  private boolean shouldNotifyDeveloper(String paramString)
  {
    boolean bool = false;
    if (TextUtils.isEmpty(paramString))
    {
      Logger.d(this.TAG, "Trying to trigger a listener - no product was found");
      return false;
    }
    if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
      if (this.mDSInterstitialListener != null) {
        bool = true;
      }
    }
    do
    {
      for (;;)
      {
        if (!bool) {
          Logger.d(this.TAG, "Trying to trigger a listener - no listener was found for product " + paramString);
        }
        return bool;
        bool = false;
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString()))
      {
        if (this.mDSRewardedVideoListener != null) {}
        for (bool = true;; bool = false) {
          break;
        }
      }
      if (paramString.equalsIgnoreCase(SSAEnums.ProductType.Banner.toString()))
      {
        if (this.mDSBannerListener != null) {}
        for (bool = true;; bool = false) {
          break;
        }
      }
    } while ((!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) && (!paramString.equalsIgnoreCase(SSAEnums.ProductType.OfferWallCredits.toString())));
    if (this.mOnOfferWallListener != null) {}
    for (bool = true;; bool = false) {
      break;
    }
  }
  
  private void toastingErrMsg(String paramString1, String paramString2)
  {
    paramString2 = new SSAObj(paramString2).getString("errMsg");
    if (!TextUtils.isEmpty(paramString2)) {
      runOnUiThread(new IronSourceWebView.7(this, paramString1, paramString2));
    }
  }
  
  private void triggerOnControllerInitProductFail(String paramString1, SSAEnums.ProductType paramProductType, String paramString2)
  {
    if (shouldNotifyDeveloper(paramProductType.toString())) {
      runOnUiThread(new IronSourceWebView.5(this, paramProductType, paramString2, paramString1));
    }
  }
  
  public void addBannerJSInterface(BannerJSAdapter paramBannerJSAdapter)
  {
    this.mBannerJsAdapter = paramBannerJSAdapter;
  }
  
  public void addMoatJSInterface(MOATJSAdapter paramMOATJSAdapter)
  {
    this.mMoatJsAdapter = paramMOATJSAdapter;
  }
  
  public void addPermissionsJSInterface(PermissionsJSAdapter paramPermissionsJSAdapter)
  {
    this.mPermissionsJsAdapter = paramPermissionsJSAdapter;
  }
  
  public void assetCached(String paramString1, String paramString2)
  {
    injectJavascript(generateJSToInject("assetCached", parseToJson("file", paramString1, "path", paramString2, null, null, null, null, null, false)));
  }
  
  public void assetCachedFailed(String paramString1, String paramString2, String paramString3)
  {
    injectJavascript(generateJSToInject("assetCachedFailed", parseToJson("file", paramString1, "path", paramString2, "errMsg", paramString3, null, null, null, false)));
  }
  
  JSInterface createJSInterface(Context paramContext)
  {
    return new JSInterface(paramContext);
  }
  
  Handler createMainThreadHandler()
  {
    return new Handler(Looper.getMainLooper());
  }
  
  public void destroy()
  {
    super.destroy();
    if (this.downloadManager != null) {
      this.downloadManager.release();
    }
    if (this.mConnectionReceiver != null) {
      this.mConnectionReceiver = null;
    }
    this.mUiHandler = null;
    this.mCurrentActivityContext = null;
  }
  
  public void deviceStatusChanged(String paramString)
  {
    injectJavascript(generateJSToInject("deviceStatusChanged", parseToJson("connectionType", paramString, null, null, null, null, null, null, null, false)));
  }
  
  public void downloadController()
  {
    IronSourceStorageUtils.deleteFile(this.mCacheDirectory, "", "mobileController.html");
    String str = SDKUtils.getControllerUrl();
    SSAFile localSSAFile = new SSAFile(str, "");
    this.mGlobalControllerTimer = new IronSourceWebView.2(this, 200000L, 1000L).start();
    if (!this.downloadManager.isMobileControllerThreadLive())
    {
      Logger.i(this.TAG, "Download Mobile Controller: " + str);
      this.downloadManager.downloadMobileControllerFile(localSSAFile);
      return;
    }
    Logger.i(this.TAG, "Download Mobile Controller: already alive");
  }
  
  public void engageEnd(String paramString)
  {
    if (paramString.equals("forceClose")) {
      closeWebView();
    }
    injectJavascript(generateJSToInject("engageEnd", parseToJson("action", paramString, null, null, null, null, null, null, null, false)));
  }
  
  public void enterBackground()
  {
    if (this.mControllerState == SSAEnums.ControllerState.Ready) {
      injectJavascript(generateJSToInject("enterBackground"));
    }
  }
  
  public void enterForeground()
  {
    if (this.mControllerState == SSAEnums.ControllerState.Ready) {
      injectJavascript(generateJSToInject("enterForeground"));
    }
  }
  
  public WebViewMessagingMediator getControllerDelegate()
  {
    if (this.mWebViewMessagingMediator == null) {
      this.mWebViewMessagingMediator = new IronSourceWebView.1(this);
    }
    return this.mWebViewMessagingMediator;
  }
  
  public String getControllerKeyPressed()
  {
    String str = this.mControllerKeyPressed;
    setControllerKeyPressed("interrupt");
    return str;
  }
  
  public Context getCurrentActivityContext()
  {
    return ((MutableContextWrapper)this.mCurrentActivityContext).getBaseContext();
  }
  
  public int getDebugMode()
  {
    return mDebugMode;
  }
  
  DownloadManager getDownloadManager()
  {
    return DownloadManager.getInstance(this.mCacheDirectory);
  }
  
  public FrameLayout getLayout()
  {
    return this.mControllerLayout;
  }
  
  public void getOfferWallCredits(String paramString1, String paramString2, OnOfferWallListener paramOnOfferWallListener)
  {
    this.mOWCreditsAppKey = paramString1;
    this.mOWCreditsUserId = paramString2;
    this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.OfferWallCredits, paramString1, paramString2);
    this.mOnOfferWallListener = paramOnOfferWallListener;
    initProduct(this.mOWCreditsAppKey, this.mOWCreditsUserId, SSAEnums.ProductType.OfferWallCredits, null, "Show OW Credits");
  }
  
  public String getOrientationState()
  {
    return this.mOrientationState;
  }
  
  public AdUnitsState getSavedState()
  {
    return this.mSavedState;
  }
  
  public State getState()
  {
    return this.mState;
  }
  
  public boolean handleSearchKeysURLs(String paramString)
    throws Exception
  {
    Object localObject = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSearchKeys();
    if (localObject != null) {
      try
      {
        if (!((List)localObject).isEmpty())
        {
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext()) {
            if (paramString.contains((String)((Iterator)localObject).next()))
            {
              UrlHandler.openUrl(getCurrentActivityContext(), paramString);
              return true;
            }
          }
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    return false;
  }
  
  public void hideCustomView()
  {
    this.mWebChromeClient.onHideCustomView();
  }
  
  public boolean inCustomView()
  {
    return this.mCustomView != null;
  }
  
  public void initBanner(String paramString1, String paramString2, DemandSource paramDemandSource, DSBannerListener paramDSBannerListener)
  {
    this.mBNAppKey = paramString1;
    this.mBNUserId = paramString2;
    this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.Banner, paramString1, paramString2);
    this.mDSBannerListener = paramDSBannerListener;
    initProduct(paramString1, paramString2, SSAEnums.ProductType.Banner, paramDemandSource, "Init BN");
  }
  
  public void initInterstitial(String paramString1, String paramString2, DemandSource paramDemandSource, DSInterstitialListener paramDSInterstitialListener)
  {
    this.mISAppKey = paramString1;
    this.mISUserId = paramString2;
    this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.Interstitial, paramString1, paramString2);
    this.mDSInterstitialListener = paramDSInterstitialListener;
    this.mSavedState.setInterstitialAppKey(this.mISAppKey);
    this.mSavedState.setInterstitialUserId(this.mISUserId);
    initProduct(this.mISAppKey, this.mISUserId, SSAEnums.ProductType.Interstitial, paramDemandSource, "Init IS");
  }
  
  public void initOfferWall(String paramString1, String paramString2, Map<String, String> paramMap, OnOfferWallListener paramOnOfferWallListener)
  {
    this.mOWAppKey = paramString1;
    this.mOWUserId = paramString2;
    this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.OfferWall, paramString1, paramString2);
    this.mOWExtraParameters = paramMap;
    this.mOnOfferWallListener = paramOnOfferWallListener;
    this.mSavedState.setOfferWallExtraParams(this.mOWExtraParameters);
    this.mSavedState.setOfferwallReportInit(true);
    initProduct(this.mOWAppKey, this.mOWUserId, SSAEnums.ProductType.OfferWall, null, "Init OW");
  }
  
  public void initRewardedVideo(String paramString1, String paramString2, DemandSource paramDemandSource, DSRewardedVideoListener paramDSRewardedVideoListener)
  {
    this.mRVAppKey = paramString1;
    this.mRVUserId = paramString2;
    this.mProductParametersCollection.setProductParameters(SSAEnums.ProductType.RewardedVideo, paramString1, paramString2);
    this.mDSRewardedVideoListener = paramDSRewardedVideoListener;
    this.mSavedState.setRVAppKey(paramString1);
    this.mSavedState.setRVUserId(paramString2);
    initProduct(paramString1, paramString2, SSAEnums.ProductType.RewardedVideo, paramDemandSource, "Init RV");
  }
  
  String initializeCacheDirectory(Context paramContext)
  {
    return IronSourceStorageUtils.initializeCacheDirectory(paramContext.getApplicationContext());
  }
  
  public void interceptedUrlToStore()
  {
    injectJavascript(generateJSToInject("interceptedUrlToStore"));
  }
  
  public boolean isInterstitialAdAvailable(String paramString)
  {
    paramString = this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.Interstitial, paramString);
    return (paramString != null) && (paramString.getAvailabilityState());
  }
  
  public void load(int paramInt)
  {
    try
    {
      loadUrl("about:blank");
      str = "file://" + this.mCacheDirectory + File.separator + "mobileController.html";
      if (new File(this.mCacheDirectory + File.separator + "mobileController.html").exists())
      {
        JSONObject localJSONObject = SDKUtils.getControllerConfigAsJSONObject();
        setWebDebuggingEnabled(localJSONObject);
        this.mRequestParameters = getRequestParameters(localJSONObject);
        str = str + "?" + this.mRequestParameters;
        this.mLoadControllerTimer = new IronSourceWebView.3(this, 50000L, 1000L, paramInt).start();
      }
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        String str;
        loadUrl(str);
        Logger.i(this.TAG, "load(): " + str);
        return;
        localThrowable1 = localThrowable1;
        Logger.e(this.TAG, "WebViewController:: load: " + localThrowable1.toString());
        new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadBlank" });
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          Logger.e(this.TAG, "WebViewController:: load: " + localThrowable2.toString());
          new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewLoadWithPath" });
        }
      }
      Logger.i(this.TAG, "load(): Mobile Controller HTML Does not exist");
      new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=htmlControllerDoesNotExistOnFileSystem" });
    }
  }
  
  public void loadBanner(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {
      injectJavascript(generateJSToInject("loadBanner", paramJSONObject.toString(), "onLoadBannerSuccess", "onLoadBannerFail"));
    }
  }
  
  public void loadInterstitial(String paramString)
  {
    Object localObject = new HashMap();
    if (!TextUtils.isEmpty(paramString)) {
      ((Map)localObject).put("demandSourceName", paramString);
    }
    localObject = flatMapToJsonAsString((Map)localObject);
    if (!isInterstitialAdAvailable(paramString))
    {
      this.mSavedState.setReportLoadInterstitial(paramString, true);
      injectJavascript(generateJSToInject("loadInterstitial", (String)localObject, "onLoadInterstitialSuccess", "onLoadInterstitialFail"));
    }
    while (!shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
      return;
    }
    runOnUiThread(new IronSourceWebView.4(this, paramString));
  }
  
  public void nativeNavigationPressed(String paramString)
  {
    injectJavascript(generateJSToInject("nativeNavigationPressed", parseToJson("action", paramString, null, null, null, null, null, null, null, false)));
  }
  
  public void notifyLifeCycle(String paramString1, String paramString2)
  {
    injectJavascript(generateJSToInject("onNativeLifeCycleEvent", parseToJson("lifeCycleEvent", paramString2, "productType", paramString1, null, null, null, null, null, false)));
  }
  
  public void onDownloadStart(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong)
  {
    Logger.i(this.TAG, paramString1 + " " + paramString4);
  }
  
  public void onFileDownloadFail(SSAFile paramSSAFile)
  {
    if (paramSSAFile.getFile().contains("mobileController.html"))
    {
      this.mGlobalControllerTimer.cancel();
      paramSSAFile = this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo).iterator();
      DemandSource localDemandSource;
      while (paramSSAFile.hasNext())
      {
        localDemandSource = (DemandSource)paramSSAFile.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, localDemandSource.getDemandSourceName());
        }
      }
      paramSSAFile = this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial).iterator();
      while (paramSSAFile.hasNext())
      {
        localDemandSource = (DemandSource)paramSSAFile.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          sendProductErrorMessage(SSAEnums.ProductType.Interstitial, localDemandSource.getDemandSourceName());
        }
      }
      paramSSAFile = this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Banner).iterator();
      while (paramSSAFile.hasNext())
      {
        localDemandSource = (DemandSource)paramSSAFile.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          sendProductErrorMessage(SSAEnums.ProductType.Banner, localDemandSource.getDemandSourceName());
        }
      }
      if (this.mOWmiss) {
        sendProductErrorMessage(SSAEnums.ProductType.OfferWall, null);
      }
      if (this.mOWCreditsMiss) {
        sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits, null);
      }
      return;
    }
    assetCachedFailed(paramSSAFile.getFile(), paramSSAFile.getPath(), paramSSAFile.getErrMsg());
  }
  
  public void onFileDownloadSuccess(SSAFile paramSSAFile)
  {
    if (paramSSAFile.getFile().contains("mobileController.html"))
    {
      load(1);
      return;
    }
    assetCached(paramSSAFile.getFile(), paramSSAFile.getPath());
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      if (!this.mChangeListener.onBackButtonPressed()) {
        return super.onKeyDown(paramInt, paramKeyEvent);
      }
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public void pageFinished()
  {
    injectJavascript(generateJSToInject("pageFinished"));
  }
  
  public void pause()
  {
    if (Build.VERSION.SDK_INT > 10) {}
    try
    {
      onPause();
      return;
    }
    catch (Throwable localThrowable)
    {
      Logger.i(this.TAG, "WebViewController: pause() - " + localThrowable);
      new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewPause" });
    }
  }
  
  public void registerConnectionReceiver(Context paramContext)
  {
    paramContext.registerReceiver(this.mConnectionReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
  }
  
  public void removeVideoEventsListener()
  {
    this.mVideoEventsListener = null;
  }
  
  public void restoreState(AdUnitsState paramAdUnitsState)
  {
    DemandSource localDemandSource;
    for (;;)
    {
      int i;
      synchronized (this.mSavedStateLocker)
      {
        if ((!paramAdUnitsState.shouldRestore()) || (!this.mControllerState.equals(SSAEnums.ControllerState.Ready))) {
          break label576;
        }
        Log.d(this.TAG, "restoreState(state:" + paramAdUnitsState + ")");
        i = paramAdUnitsState.getDisplayedProduct();
        if (i == -1) {
          break label394;
        }
        if (i == SSAEnums.ProductType.RewardedVideo.ordinal())
        {
          Log.d(this.TAG, "onRVAdClosed()");
          localObject2 = SSAEnums.ProductType.RewardedVideo;
          str1 = paramAdUnitsState.getDisplayedDemandSourceName();
          localObject3 = getAdProductListenerByProductType((SSAEnums.ProductType)localObject2);
          if ((localObject3 != null) && (!TextUtils.isEmpty(str1))) {
            ((DSAdProductListener)localObject3).onAdProductClose((SSAEnums.ProductType)localObject2, str1);
          }
          paramAdUnitsState.adOpened(-1);
          paramAdUnitsState.setDisplayedDemandSourceName(null);
          localObject2 = paramAdUnitsState.getInterstitialAppKey();
          str1 = paramAdUnitsState.getInterstitialUserId();
          localObject3 = this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial).iterator();
          if (!((Iterator)localObject3).hasNext()) {
            break;
          }
          localDemandSource = (DemandSource)((Iterator)localObject3).next();
          if (localDemandSource.getDemandSourceInitState() != 2) {
            continue;
          }
          Log.d(this.TAG, "initInterstitial(appKey:" + (String)localObject2 + ", userId:" + str1 + ", demandSource:" + localDemandSource.getDemandSourceName() + ")");
          initInterstitial((String)localObject2, str1, localDemandSource, this.mDSInterstitialListener);
        }
      }
      if (i == SSAEnums.ProductType.Interstitial.ordinal())
      {
        Log.d(this.TAG, "onInterstitialAdClosed()");
        localObject2 = SSAEnums.ProductType.Interstitial;
        str1 = paramAdUnitsState.getDisplayedDemandSourceName();
        localObject3 = getAdProductListenerByProductType((SSAEnums.ProductType)localObject2);
        if ((localObject3 != null) && (!TextUtils.isEmpty(str1))) {
          ((DSAdProductListener)localObject3).onAdProductClose((SSAEnums.ProductType)localObject2, str1);
        }
      }
      else if (i == SSAEnums.ProductType.OfferWall.ordinal())
      {
        Log.d(this.TAG, "onOWAdClosed()");
        if (this.mOnOfferWallListener != null)
        {
          this.mOnOfferWallListener.onOWAdClosed();
          continue;
          label394:
          Log.d(this.TAG, "No ad was opened");
        }
      }
    }
    Object localObject2 = paramAdUnitsState.getRVAppKey();
    String str1 = paramAdUnitsState.getRVUserId();
    Object localObject3 = this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localDemandSource = (DemandSource)((Iterator)localObject3).next();
      if (localDemandSource.getDemandSourceInitState() == 2)
      {
        String str2 = localDemandSource.getDemandSourceName();
        Log.d(this.TAG, "onRVNoMoreOffers()");
        this.mDSRewardedVideoListener.onRVNoMoreOffers(str2);
        Log.d(this.TAG, "initRewardedVideo(appKey:" + (String)localObject2 + ", userId:" + str1 + ", demandSource:" + str2 + ")");
        initRewardedVideo((String)localObject2, str1, localDemandSource, this.mDSRewardedVideoListener);
      }
    }
    paramAdUnitsState.setShouldRestore(false);
    label576:
    this.mSavedState = paramAdUnitsState;
  }
  
  public void resume()
  {
    if (Build.VERSION.SDK_INT > 10) {}
    try
    {
      onResume();
      return;
    }
    catch (Throwable localThrowable)
    {
      Logger.i(this.TAG, "WebViewController: onResume() - " + localThrowable);
      new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=webviewResume" });
    }
  }
  
  void runOnUiThread(Runnable paramRunnable)
  {
    this.mUiHandler.post(paramRunnable);
  }
  
  public WebBackForwardList saveState(Bundle paramBundle)
  {
    return super.saveState(paramBundle);
  }
  
  public void setControllerKeyPressed(String paramString)
  {
    this.mControllerKeyPressed = paramString;
  }
  
  public void setDebugMode(int paramInt)
  {
    mDebugMode = paramInt;
  }
  
  void setMissProduct(SSAEnums.ProductType paramProductType, DemandSource paramDemandSource)
  {
    if ((paramProductType == SSAEnums.ProductType.RewardedVideo) || (paramProductType == SSAEnums.ProductType.Interstitial) || (paramProductType == SSAEnums.ProductType.Banner)) {
      if (paramDemandSource != null) {
        paramDemandSource.setDemandSourceInitState(1);
      }
    }
    for (;;)
    {
      Logger.i(this.TAG, "setMissProduct(" + paramProductType + ")");
      return;
      if (paramProductType == SSAEnums.ProductType.OfferWall) {
        this.mOWmiss = true;
      } else if (paramProductType == SSAEnums.ProductType.OfferWallCredits) {
        this.mOWCreditsMiss = true;
      }
    }
  }
  
  public void setOnWebViewControllerChangeListener(OnWebViewChangeListener paramOnWebViewChangeListener)
  {
    this.mChangeListener = paramOnWebViewChangeListener;
  }
  
  public void setOrientationState(String paramString)
  {
    this.mOrientationState = paramString;
  }
  
  public void setState(State paramState)
  {
    this.mState = paramState;
  }
  
  public void setVideoEventsListener(VideoEventsListener paramVideoEventsListener)
  {
    this.mVideoEventsListener = paramVideoEventsListener;
  }
  
  public void showInterstitial(JSONObject paramJSONObject)
  {
    injectJavascript(createShowProductJSMethod(SSAEnums.ProductType.Interstitial, paramJSONObject));
  }
  
  public void showOfferWall(Map<String, String> paramMap)
  {
    this.mOWExtraParameters = paramMap;
    injectJavascript(generateJSToInject("showOfferWall", "onShowOfferWallSuccess", "onShowOfferWallFail"));
  }
  
  public void showRewardedVideo(JSONObject paramJSONObject)
  {
    injectJavascript(createShowProductJSMethod(SSAEnums.ProductType.RewardedVideo, paramJSONObject));
  }
  
  public void unregisterConnectionReceiver(Context paramContext)
  {
    try
    {
      paramContext.unregisterReceiver(this.mConnectionReceiver);
      return;
    }
    catch (Exception paramContext)
    {
      Log.e(this.TAG, "unregisterConnectionReceiver - " + paramContext);
      new IronSourceAsyncHttpRequestTask().execute(new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + paramContext.getStackTrace()[0].getMethodName() });
      return;
    }
    catch (IllegalArgumentException paramContext) {}
  }
  
  public void updateConsentInfo(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {}
    for (paramJSONObject = paramJSONObject.toString();; paramJSONObject = null)
    {
      injectJavascript("updateConsentInfo", generateJSToInject("updateConsentInfo", paramJSONObject));
      return;
    }
  }
  
  public void viewableChange(boolean paramBoolean, String paramString)
  {
    injectJavascript(generateJSToInject("viewableChange", parseToJson("webview", paramString, null, null, null, null, null, null, "isViewable", paramBoolean)));
  }
  
  public class JSInterface
  {
    volatile int udiaResults = 0;
    
    public JSInterface(Context paramContext) {}
    
    private void callJavaScriptFunction(String paramString1, String paramString2)
    {
      if (!TextUtils.isEmpty(paramString1))
      {
        paramString1 = IronSourceWebView.this.generateJSToInject(paramString1, paramString2);
        IronSourceWebView.this.injectJavascript(paramString1);
      }
    }
    
    private void injectGetUDIA(String paramString, JSONArray paramJSONArray)
    {
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = IronSourceWebView.this.generateJSToInject(paramString, paramJSONArray.toString(), "onGetUDIASuccess", "onGetUDIAFail");
        IronSourceWebView.this.injectJavascript(paramString);
      }
    }
    
    private void sendResults(String paramString, JSONArray paramJSONArray)
    {
      Logger.i(IronSourceWebView.this.TAG, "sendResults: " + this.udiaResults);
      if (this.udiaResults <= 0) {
        injectGetUDIA(paramString, paramJSONArray);
      }
    }
    
    private void setInterstitialAvailability(String paramString, boolean paramBoolean)
    {
      DemandSource localDemandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.Interstitial, paramString);
      if (localDemandSource != null) {
        localDemandSource.setAvailabilityState(paramBoolean);
      }
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.toastingErrMsg("onInterstitialAvailability", String.valueOf(paramBoolean + " with demand " + paramString));
      }
    }
    
    @JavascriptInterface
    public void adClicked(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "adClicked(" + paramString + ")");
      paramString = new SSAObj(paramString);
      Object localObject = paramString.getString("productType");
      paramString = paramString.getString("demandSourceName");
      if (TextUtils.isEmpty(paramString)) {}
      DSAdProductListener localDSAdProductListener;
      do
      {
        return;
        localObject = IronSourceWebView.this.getStringProductTypeAsEnum((String)localObject);
        localDSAdProductListener = IronSourceWebView.this.getAdProductListenerByProductType((SSAEnums.ProductType)localObject);
      } while ((localObject == null) || (localDSAdProductListener == null));
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.12(this, localDSAdProductListener, (SSAEnums.ProductType)localObject, paramString));
    }
    
    @JavascriptInterface
    public void adCredited(String paramString)
    {
      Log.d(IronSourceWebView.this.PUB_TAG, "adCredited(" + paramString + ")");
      SSAObj localSSAObj = new SSAObj(paramString);
      String str1 = localSSAObj.getString("credits");
      int i;
      String str6;
      int j;
      label85:
      String str4;
      String str5;
      boolean bool2;
      String str3;
      boolean bool3;
      String str2;
      if (str1 != null)
      {
        i = Integer.parseInt(str1);
        str6 = localSSAObj.getString("total");
        if (str6 == null) {
          break label209;
        }
        j = Integer.parseInt(str6);
        str4 = localSSAObj.getString("demandSourceName");
        str5 = localSSAObj.getString("productType");
        boolean bool4 = localSSAObj.getBoolean("externalPoll");
        bool2 = false;
        str3 = null;
        bool1 = false;
        bool3 = false;
        if (!bool4) {
          break label214;
        }
        str1 = IronSourceWebView.this.mOWCreditsAppKey;
        str2 = IronSourceWebView.this.mOWCreditsUserId;
      }
      for (;;)
      {
        if (str5.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString()))
        {
          if ((localSSAObj.isNull("signature")) || (localSSAObj.isNull("timestamp")) || (localSSAObj.isNull("totalCreditsFlag")))
          {
            IronSourceWebView.this.responseBack(paramString, false, "One of the keys are missing: signature/timestamp/totalCreditsFlag", null);
            return;
            i = 0;
            break;
            label209:
            j = 0;
            break label85;
            label214:
            str1 = IronSourceWebView.this.mOWAppKey;
            str2 = IronSourceWebView.this.mOWUserId;
            continue;
          }
          if (!localSSAObj.getString("signature").equalsIgnoreCase(SDKUtils.getMD5(str6 + str1 + str2))) {
            break label342;
          }
        }
      }
      for (boolean bool1 = true;; bool1 = bool3)
      {
        bool2 = localSSAObj.getBoolean("totalCreditsFlag");
        str3 = localSSAObj.getString("timestamp");
        if (!IronSourceWebView.this.shouldNotifyDeveloper(str5)) {
          break;
        }
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.5(this, str5, str4, i, bool1, j, bool2, str3, str1, str2, paramString));
        return;
        label342:
        IronSourceWebView.this.responseBack(paramString, false, "Controller signature is not equal to SDK signature", null);
      }
    }
    
    @JavascriptInterface
    public void adUnitsReady(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "adUnitsReady(" + paramString + ")");
      String str = new SSAObj(paramString).getString("demandSourceName");
      AdUnitsReady localAdUnitsReady = new AdUnitsReady(paramString);
      if (!localAdUnitsReady.isNumOfAdUnitsExist()) {
        IronSourceWebView.this.responseBack(paramString, false, "Num Of Ad Units Do Not Exist", null);
      }
      do
      {
        return;
        IronSourceWebView.this.responseBack(paramString, true, null, null);
        paramString = localAdUnitsReady.getProductType();
      } while (!IronSourceWebView.this.shouldNotifyDeveloper(paramString));
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.4(this, localAdUnitsReady, paramString, str));
    }
    
    @JavascriptInterface
    public String addTesterParametersToConfig(String paramString1, String paramString2)
      throws JSONException
    {
      paramString1 = new JSONObject(paramString1);
      paramString2 = new JSONObject(paramString2);
      paramString1.putOpt("testerABGroup", paramString2.get("testerABGroup"));
      paramString1.putOpt("testFriendlyName", paramString2.get("testFriendlyName"));
      return paramString1.toString();
    }
    
    @JavascriptInterface
    public void alert(String paramString) {}
    
    @JavascriptInterface
    public boolean areTesterParametersValid(String paramString)
    {
      if ((!TextUtils.isEmpty(paramString)) && (!paramString.contains("-1"))) {
        try
        {
          paramString = new JSONObject(paramString);
          if (!paramString.getString("testerABGroup").isEmpty())
          {
            boolean bool = paramString.getString("testFriendlyName").isEmpty();
            if (!bool) {
              return true;
            }
          }
        }
        catch (JSONException paramString)
        {
          paramString.printStackTrace();
        }
      }
      return false;
    }
    
    @JavascriptInterface
    public void bannerViewAPI(String paramString)
    {
      try
      {
        IronSourceWebView.this.mBannerJsAdapter.sendMessageToISNAdView(paramString);
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        Logger.e(IronSourceWebView.this.TAG, "bannerViewAPI failed with exception " + paramString.getMessage());
      }
    }
    
    @JavascriptInterface
    public void checkInstalledApps(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "checkInstalledApps(" + paramString + ")");
      String str1 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      String str2 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
      Object localObject1 = null;
      Object localObject2 = new SSAObj(paramString);
      paramString = ((SSAObj)localObject2).getString(IronSourceWebView.APP_IDS);
      localObject2 = ((SSAObj)localObject2).getString(IronSourceWebView.REQUEST_ID);
      paramString = IronSourceWebView.this.getAppsStatus(paramString, (String)localObject2);
      localObject2 = (String)paramString[0];
      if (((Boolean)paramString[1]).booleanValue())
      {
        paramString = localObject1;
        if (!TextUtils.isEmpty(str2)) {
          paramString = str2;
        }
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject2, "onCheckInstalledAppsSuccess", "onCheckInstalledAppsFail");
          IronSourceWebView.this.injectJavascript(paramString);
        }
        return;
        paramString = localObject1;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void createCalendarEvent(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "createCalendarEvent(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void deleteFile(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "deleteFile(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, localSSAFile.getPath()))
      {
        IronSourceWebView.this.responseBack(paramString, false, "File not exist", "1");
        return;
      }
      boolean bool = IronSourceStorageUtils.deleteFile(IronSourceWebView.this.mCacheDirectory, localSSAFile.getPath(), localSSAFile.getFile());
      IronSourceWebView.this.responseBack(paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void deleteFolder(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "deleteFolder(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, localSSAFile.getPath()))
      {
        IronSourceWebView.this.responseBack(paramString, false, "Folder not exist", "1");
        return;
      }
      boolean bool = IronSourceStorageUtils.deleteFolder(IronSourceWebView.this.mCacheDirectory, localSSAFile.getPath());
      IronSourceWebView.this.responseBack(paramString, bool, null, null);
    }
    
    @JavascriptInterface
    public void displayWebView(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "displayWebView(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      paramString = new SSAObj(paramString);
      boolean bool1 = ((Boolean)paramString.get("display")).booleanValue();
      String str3 = paramString.getString("productType");
      boolean bool2 = paramString.getBoolean("standaloneView");
      String str2 = paramString.getString("demandSourceName");
      int j = 0;
      if (bool1)
      {
        IronSourceWebView.access$5102(IronSourceWebView.this, paramString.getBoolean("immersive"));
        IronSourceWebView.access$5202(IronSourceWebView.this, paramString.getBoolean("activityThemeTranslucent"));
        if (IronSourceWebView.this.getState() != IronSourceWebView.State.Display)
        {
          IronSourceWebView.this.setState(IronSourceWebView.State.Display);
          Logger.i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
          Context localContext = IronSourceWebView.this.getCurrentActivityContext();
          String str1 = IronSourceWebView.this.getOrientationState();
          int k = DeviceStatus.getApplicationRotation(localContext);
          if (bool2)
          {
            paramString = new ControllerView(localContext);
            paramString.addView(IronSourceWebView.this.mControllerLayout);
            paramString.showInterstitial(IronSourceWebView.this);
            return;
          }
          Intent localIntent;
          int i;
          if (IronSourceWebView.this.mIsActivityThemeTranslucent)
          {
            localIntent = new Intent(localContext, InterstitialActivity.class);
            if (!SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(str3)) {
              break label469;
            }
            paramString = str1;
            if ("application".equals(str1)) {
              paramString = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
            }
            i = 1;
            localIntent.putExtra("productType", SSAEnums.ProductType.RewardedVideo.toString());
            IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.RewardedVideo.ordinal());
            IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(str2);
          }
          for (;;)
          {
            if ((i != 0) && (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString()))) {
              IronSourceWebView.this.mDSRewardedVideoListener.onAdProductOpen(SSAEnums.ProductType.RewardedVideo, str2);
            }
            localIntent.setFlags(536870912);
            localIntent.putExtra("immersive", IronSourceWebView.this.mIsImmersive);
            localIntent.putExtra("orientation_set_flag", paramString);
            localIntent.putExtra("rotation_set_flag", k);
            localContext.startActivity(localIntent);
            return;
            localIntent = new Intent(localContext, ControllerActivity.class);
            break;
            label469:
            if (SSAEnums.ProductType.OfferWall.toString().equalsIgnoreCase(str3))
            {
              localIntent.putExtra("productType", SSAEnums.ProductType.OfferWall.toString());
              IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
              i = j;
              paramString = str1;
            }
            else
            {
              i = j;
              paramString = str1;
              if (SSAEnums.ProductType.Interstitial.toString().equalsIgnoreCase(str3))
              {
                i = j;
                paramString = str1;
                if ("application".equals(str1))
                {
                  paramString = SDKUtils.translateRequestedOrientation(DeviceStatus.getActivityRequestedOrientation(IronSourceWebView.this.getCurrentActivityContext()));
                  i = j;
                }
              }
            }
          }
        }
        Logger.i(IronSourceWebView.this.TAG, "State: " + IronSourceWebView.this.mState);
        return;
      }
      IronSourceWebView.this.setState(IronSourceWebView.State.Gone);
      IronSourceWebView.this.closeWebView();
    }
    
    @JavascriptInterface
    public void getApplicationInfo(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getApplicationInfo(" + paramString + ")");
      String str1 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      String str2 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
      paramString = new SSAObj(paramString);
      String str3 = paramString.getString("productType");
      Object localObject = paramString.getString("demandSourceName");
      paramString = null;
      Object[] arrayOfObject = new Object[2];
      localObject = IronSourceWebView.this.getApplicationParams(str3, (String)localObject);
      str3 = (String)localObject[0];
      if (((Boolean)localObject[1]).booleanValue()) {
        if (!TextUtils.isEmpty(str2)) {
          paramString = str2;
        }
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = IronSourceWebView.this.generateJSToInject(paramString, str3, "onGetApplicationInfoSuccess", "onGetApplicationInfoFail");
          IronSourceWebView.this.injectJavascript(paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void getAppsInstallTime(String paramString)
    {
      Object localObject = new SSAObj(paramString);
      int i = 1;
      str2 = null;
      try
      {
        localObject = ((SSAObj)localObject).getString("systemApps");
        localObject = DeviceStatus.getAppsInstallTime(IronSourceWebView.this.getContext(), Boolean.parseBoolean((String)localObject)).toString();
        i = 0;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          Logger.i(IronSourceWebView.this.TAG, "getAppsInstallTime failed(" + localException.getLocalizedMessage() + ")");
          str1 = localException.getLocalizedMessage();
          continue;
          String str3 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
          paramString = str2;
          if (!TextUtils.isEmpty(str3)) {
            paramString = str3;
          }
        }
      }
      if (i != 0)
      {
        str3 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
        paramString = str2;
        if (!TextUtils.isEmpty(str3)) {
          paramString = str3;
        }
        if (TextUtils.isEmpty(paramString)) {}
      }
      try
      {
        str2 = URLDecoder.decode((String)localObject, Charset.defaultCharset().name());
        localObject = str2;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        for (;;)
        {
          String str1;
          localUnsupportedEncodingException.printStackTrace();
        }
      }
      paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject);
      IronSourceWebView.this.injectJavascript(paramString);
    }
    
    @JavascriptInterface
    public void getCachedFilesMap(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getCachedFilesMap(" + paramString + ")");
      String str = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      if (!TextUtils.isEmpty(str))
      {
        localObject = new SSAObj(paramString);
        if (!((SSAObj)localObject).containsKey("path")) {
          IronSourceWebView.this.responseBack(paramString, false, "path key does not exist", null);
        }
      }
      else
      {
        return;
      }
      Object localObject = (String)((SSAObj)localObject).get("path");
      if (!IronSourceStorageUtils.isPathExist(IronSourceWebView.this.mCacheDirectory, (String)localObject))
      {
        IronSourceWebView.this.responseBack(paramString, false, "path file does not exist on disk", null);
        return;
      }
      paramString = IronSourceStorageUtils.getCachedFilesMap(IronSourceWebView.this.mCacheDirectory, (String)localObject);
      paramString = IronSourceWebView.this.generateJSToInject(str, paramString, "onGetCachedFilesMapSuccess", "onGetCachedFilesMapFail");
      IronSourceWebView.this.injectJavascript(paramString);
    }
    
    @JavascriptInterface
    public void getControllerConfig(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getControllerConfig(" + paramString + ")");
      String str2 = new SSAObj(paramString).getString(IronSourceWebView.JSON_KEY_SUCCESS);
      String str1;
      String str3;
      if (!TextUtils.isEmpty(str2))
      {
        str1 = SDKUtils.getControllerConfig();
        str3 = SDKUtils.getTesterParameters();
        paramString = str1;
        if (!areTesterParametersValid(str3)) {}
      }
      try
      {
        paramString = addTesterParametersToConfig(str1, str3);
        paramString = IronSourceWebView.this.generateJSToInject(str2, paramString);
        IronSourceWebView.this.injectJavascript(paramString);
        return;
      }
      catch (JSONException paramString)
      {
        for (;;)
        {
          Logger.d(IronSourceWebView.this.TAG, "getControllerConfig Error while parsing Tester AB Group parameters");
          paramString = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void getDemandSourceState(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getMediationState(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str1 = ((SSAObj)localObject).getString("demandSourceName");
      String str2 = ((SSAObj)localObject).getString("productType");
      if ((str2 != null) && (str1 != null)) {
        try
        {
          localObject = SDKUtils.getProductType(str2);
          if (localObject != null)
          {
            DemandSource localDemandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName((SSAEnums.ProductType)localObject, str1);
            localObject = new JSONObject();
            ((JSONObject)localObject).put("productType", str2);
            ((JSONObject)localObject).put("demandSourceName", str1);
            if ((localDemandSource != null) && (!localDemandSource.isMediationState(-1)))
            {
              str1 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
              ((JSONObject)localObject).put("state", localDemandSource.getMediationState());
            }
            for (;;)
            {
              callJavaScriptFunction(str1, ((JSONObject)localObject).toString());
              return;
              str1 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
            }
          }
          return;
        }
        catch (Exception localException)
        {
          IronSourceWebView.this.responseBack(paramString, false, localException.getMessage(), null);
          localException.printStackTrace();
        }
      }
    }
    
    @JavascriptInterface
    public void getDeviceLocation(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getDeviceLocation(" + paramString + ")");
      try
      {
        paramString = IronSourceWebView.this.createLocationObject(paramString, LocationService.getLastLocation(IronSourceWebView.this.getContext()));
        IronSourceWebView.this.responseBack(paramString.toString(), true, null, null);
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    
    @JavascriptInterface
    public void getDevicePreciseLocation(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getDevicePreciseLocation(" + paramString + ")");
      try
      {
        LocationService.getPreciseLocation(IronSourceWebView.this.getContext(), new IronSourceWebView.JSInterface.29(this, paramString));
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    
    @JavascriptInterface
    public void getDeviceStatus(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getDeviceStatus(" + paramString + ")");
      String str1 = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      String str2 = IronSourceWebView.this.extractFailFunctionToCall(paramString);
      paramString = new Object[2];
      paramString = IronSourceWebView.this.getDeviceParams(IronSourceWebView.this.getContext());
      String str3 = (String)paramString[0];
      boolean bool = ((Boolean)paramString[1]).booleanValue();
      paramString = null;
      if (bool) {
        if (!TextUtils.isEmpty(str2)) {
          paramString = str2;
        }
      }
      for (;;)
      {
        if (!TextUtils.isEmpty(paramString))
        {
          paramString = IronSourceWebView.this.generateJSToInject(paramString, str3, "onGetDeviceStatusSuccess", "onGetDeviceStatusFail");
          IronSourceWebView.this.injectJavascript(paramString);
        }
        return;
        if (!TextUtils.isEmpty(str1)) {
          paramString = str1;
        }
      }
    }
    
    @JavascriptInterface
    public void getDeviceVolume(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getDeviceVolume(" + paramString + ")");
      try
      {
        float f = DeviceProperties.getInstance(IronSourceWebView.this.getCurrentActivityContext()).getDeviceVolume(IronSourceWebView.this.getCurrentActivityContext());
        paramString = new SSAObj(paramString);
        paramString.put("deviceVolume", String.valueOf(f));
        IronSourceWebView.this.responseBack(paramString.toString(), true, null, null);
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    
    @JavascriptInterface
    public void getOrientation(String paramString)
    {
      paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      String str = SDKUtils.getOrientation(IronSourceWebView.this.getCurrentActivityContext()).toString();
      if (!TextUtils.isEmpty(paramString))
      {
        paramString = IronSourceWebView.this.generateJSToInject(paramString, str, "onGetOrientationSuccess", "onGetOrientationFail");
        IronSourceWebView.this.injectJavascript(paramString);
      }
    }
    
    @JavascriptInterface
    public void getUDIA(String paramString)
    {
      this.udiaResults = 0;
      Logger.i(IronSourceWebView.this.TAG, "getUDIA(" + paramString + ")");
      String str = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("getByFlag")) {
        IronSourceWebView.this.responseBack(paramString, false, "getByFlag key does not exist", null);
      }
      for (;;)
      {
        return;
        int i = Integer.parseInt(((SSAObj)localObject).getString("getByFlag"));
        if (i == 0) {
          continue;
        }
        localObject = Integer.toBinaryString(i);
        if (TextUtils.isEmpty((CharSequence)localObject))
        {
          IronSourceWebView.this.responseBack(paramString, false, "fialed to convert getByFlag", null);
          return;
        }
        localObject = new StringBuilder((String)localObject).reverse().toString().toCharArray();
        paramString = new JSONArray();
        JSONObject localJSONObject;
        if (localObject[3] == '0') {
          localJSONObject = new JSONObject();
        }
        try
        {
          localJSONObject.put("sessions", IronSourceSharedPrefHelper.getSupersonicPrefHelper().getSessions());
          IronSourceSharedPrefHelper.getSupersonicPrefHelper().deleteSessions();
          paramString.put(localJSONObject);
          if (localObject[2] != '1') {
            continue;
          }
          this.udiaResults += 1;
          localObject = LocationService.getLastLocation(IronSourceWebView.this.getContext());
          if (localObject != null)
          {
            localJSONObject = new JSONObject();
            try
            {
              localJSONObject.put("latitude", ((Location)localObject).getLatitude());
              localJSONObject.put("longitude", ((Location)localObject).getLongitude());
              paramString.put(localJSONObject);
              this.udiaResults -= 1;
              sendResults(str, paramString);
              Logger.i(IronSourceWebView.this.TAG, "done location");
              return;
            }
            catch (JSONException paramString)
            {
              return;
            }
          }
          this.udiaResults -= 1;
          return;
        }
        catch (JSONException localJSONException)
        {
          for (;;) {}
        }
      }
    }
    
    @JavascriptInterface
    public void getUserData(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getUserData(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("key"))
      {
        IronSourceWebView.this.responseBack(paramString, false, "key does not exist", null);
        return;
      }
      paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      localObject = ((SSAObj)localObject).getString("key");
      String str = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUserData((String)localObject);
      localObject = IronSourceWebView.this.parseToJson((String)localObject, str, null, null, null, null, null, null, null, false);
      paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject);
      IronSourceWebView.this.injectJavascript(paramString);
    }
    
    @JavascriptInterface
    public void getUserUniqueId(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "getUserUniqueId(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("productType")) {
        IronSourceWebView.this.responseBack(paramString, false, "productType does not exist", null);
      }
      do
      {
        return;
        paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
      } while (TextUtils.isEmpty(paramString));
      localObject = ((SSAObj)localObject).getString("productType");
      String str = IronSourceSharedPrefHelper.getSupersonicPrefHelper().getUniqueId((String)localObject);
      localObject = IronSourceWebView.this.parseToJson("userUniqueId", str, "productType", (String)localObject, null, null, null, null, null, false);
      paramString = IronSourceWebView.this.generateJSToInject(paramString, (String)localObject, "onGetUserUniqueIdSuccess", "onGetUserUniqueIdFail");
      IronSourceWebView.this.injectJavascript(paramString);
    }
    
    void handleControllerStageFailed()
    {
      IronSourceWebView.access$1702(IronSourceWebView.this, SSAEnums.ControllerState.Failed);
      Iterator localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo).iterator();
      DemandSource localDemandSource;
      while (localIterator.hasNext())
      {
        localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.RewardedVideo, localDemandSource.getDemandSourceName());
        }
      }
      localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial).iterator();
      while (localIterator.hasNext())
      {
        localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Interstitial, localDemandSource.getDemandSourceName());
        }
      }
      localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Banner).iterator();
      while (localIterator.hasNext())
      {
        localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.Banner, localDemandSource.getDemandSourceName());
        }
      }
      if (IronSourceWebView.this.mOWmiss) {
        IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWall, null);
      }
      if (IronSourceWebView.this.mOWCreditsMiss) {
        IronSourceWebView.this.sendProductErrorMessage(SSAEnums.ProductType.OfferWallCredits, null);
      }
    }
    
    void handleControllerStageLoaded()
    {
      IronSourceWebView.access$1702(IronSourceWebView.this, SSAEnums.ControllerState.Loaded);
    }
    
    void handleControllerStageReady()
    {
      IronSourceWebView.access$1702(IronSourceWebView.this, SSAEnums.ControllerState.Ready);
      IronSourceWebView.this.mGlobalControllerTimer.cancel();
      IronSourceWebView.this.mLoadControllerTimer.cancel();
      IronSourceWebView.this.invokePendingCommands();
      Iterator localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.RewardedVideo).iterator();
      DemandSource localDemandSource;
      while (localIterator.hasNext())
      {
        localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.initRewardedVideo(IronSourceWebView.this.mRVAppKey, IronSourceWebView.this.mRVUserId, localDemandSource, IronSourceWebView.this.mDSRewardedVideoListener);
        }
      }
      localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Interstitial).iterator();
      while (localIterator.hasNext())
      {
        localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.initInterstitial(IronSourceWebView.this.mISAppKey, IronSourceWebView.this.mISUserId, localDemandSource, IronSourceWebView.this.mDSInterstitialListener);
        }
      }
      localIterator = IronSourceWebView.this.mDemandSourceManager.getDemandSources(SSAEnums.ProductType.Banner).iterator();
      while (localIterator.hasNext())
      {
        localDemandSource = (DemandSource)localIterator.next();
        if (localDemandSource.getDemandSourceInitState() == 1) {
          IronSourceWebView.this.initBanner(IronSourceWebView.this.mBNAppKey, IronSourceWebView.this.mBNUserId, localDemandSource, IronSourceWebView.this.mDSBannerListener);
        }
      }
      if (IronSourceWebView.this.mOWmiss) {
        IronSourceWebView.this.initOfferWall(IronSourceWebView.this.mOWAppKey, IronSourceWebView.this.mOWUserId, IronSourceWebView.this.mOWExtraParameters, IronSourceWebView.this.mOnOfferWallListener);
      }
      if (IronSourceWebView.this.mOWCreditsMiss) {
        IronSourceWebView.this.getOfferWallCredits(IronSourceWebView.this.mOWCreditsAppKey, IronSourceWebView.this.mOWCreditsUserId, IronSourceWebView.this.mOnOfferWallListener);
      }
      IronSourceWebView.this.restoreState(IronSourceWebView.this.mSavedState);
    }
    
    @JavascriptInterface
    public void initController(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "initController(" + paramString + ")");
      paramString = new SSAObj(paramString);
      if (paramString.containsKey("stage"))
      {
        paramString = paramString.getString("stage");
        if (!"ready".equalsIgnoreCase(paramString)) {
          break label92;
        }
        handleControllerStageReady();
      }
      for (;;)
      {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.1(this));
        return;
        label92:
        if ("loaded".equalsIgnoreCase(paramString)) {
          handleControllerStageLoaded();
        } else if ("failed".equalsIgnoreCase(paramString)) {
          handleControllerStageFailed();
        } else {
          Logger.i(IronSourceWebView.this.TAG, "No STAGE mentioned! Should not get here!");
        }
      }
    }
    
    @JavascriptInterface
    public void locationServicesEnabled(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "locationServicesEnabled(" + paramString + ")");
      try
      {
        boolean bool = LocationService.locationServicesEnabled(IronSourceWebView.this.getContext());
        paramString = new SSAObj(paramString);
        paramString.put("status", String.valueOf(bool));
        IronSourceWebView.this.responseBack(paramString.toString(), true, null, null);
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    
    @JavascriptInterface
    public void moatAPI(String paramString)
    {
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.28(this, paramString));
    }
    
    @JavascriptInterface
    public void onAdWindowsClosed(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onAdWindowsClosed(" + paramString + ")");
      IronSourceWebView.this.mSavedState.adClosed();
      IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(null);
      Object localObject = new SSAObj(paramString);
      paramString = ((SSAObj)localObject).getString("productType");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      SSAEnums.ProductType localProductType = IronSourceWebView.this.getStringProductTypeAsEnum(paramString);
      Log.d(IronSourceWebView.this.PUB_TAG, "onAdClosed() with type " + localProductType);
      if ((IronSourceWebView.this.shouldNotifyDeveloper(paramString)) && (paramString != null)) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.26(this, localProductType, (String)localObject));
      }
    }
    
    @JavascriptInterface
    public void onGenericFunctionFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGenericFunctionFail(" + paramString + ")");
      if (IronSourceWebView.this.mOnGenericFunctionListener == null)
      {
        Logger.d(IronSourceWebView.this.TAG, "genericFunctionListener was not found");
        return;
      }
      String str = new SSAObj(paramString).getString("errMsg");
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.24(this, str));
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGenericFunctionFail", paramString);
    }
    
    @JavascriptInterface
    public void onGenericFunctionSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGenericFunctionSuccess(" + paramString + ")");
      if (IronSourceWebView.this.mOnGenericFunctionListener == null)
      {
        Logger.d(IronSourceWebView.this.TAG, "genericFunctionListener was not found");
        return;
      }
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.23(this));
      IronSourceWebView.this.responseBack(paramString, true, null, null);
    }
    
    @JavascriptInterface
    public void onGetApplicationInfoFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetApplicationInfoFail(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetApplicationInfoFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetApplicationInfoSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetApplicationInfoSuccess(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetApplicationInfoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetCachedFilesMapFail(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetCachedFilesMapFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetCachedFilesMapSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetCachedFilesMapSuccess(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetCachedFilesMapSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetDeviceStatusFail(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetDeviceStatusFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetDeviceStatusSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetDeviceStatusSuccess(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetDeviceStatusSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onGetUDIAFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetUDIAFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUDIASuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetUDIASuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUserCreditsFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetUserCreditsFail(" + paramString + ")");
      String str = new SSAObj(paramString).getString("errMsg");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.25(this, str));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onGetUserCreditsFail", paramString);
    }
    
    @JavascriptInterface
    public void onGetUserUniqueIdFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetUserUniqueIdFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onGetUserUniqueIdSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onGetUserUniqueIdSuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onInitBannerFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitBannerFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      if (TextUtils.isEmpty((CharSequence)localObject))
      {
        Logger.i(IronSourceWebView.this.TAG, "onInitBannerFail failed with no demand source");
        return;
      }
      DemandSource localDemandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.Banner, (String)localObject);
      if (localDemandSource != null) {
        localDemandSource.setDemandSourceInitState(3);
      }
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Banner.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.20(this, str, (String)localObject));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onInitBannerFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitBannerSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitBannerSuccess()");
      IronSourceWebView.this.toastingErrMsg("onInitBannerSuccess", "true");
      paramString = new SSAObj(paramString).getString("demandSourceName");
      if (TextUtils.isEmpty(paramString)) {
        Logger.i(IronSourceWebView.this.TAG, "onInitBannerSuccess failed with no demand source");
      }
      while (!IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Banner.toString())) {
        return;
      }
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.19(this, paramString));
    }
    
    @JavascriptInterface
    public void onInitInterstitialFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      if (TextUtils.isEmpty((CharSequence)localObject))
      {
        Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess failed with no demand source");
        return;
      }
      DemandSource localDemandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.Interstitial, (String)localObject);
      if (localDemandSource != null) {
        localDemandSource.setDemandSourceInitState(3);
      }
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.11(this, str, (String)localObject));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onInitInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitInterstitialSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess()");
      IronSourceWebView.this.toastingErrMsg("onInitInterstitialSuccess", "true");
      paramString = new SSAObj(paramString).getString("demandSourceName");
      if (TextUtils.isEmpty(paramString)) {
        Logger.i(IronSourceWebView.this.TAG, "onInitInterstitialSuccess failed with no demand source");
      }
      while (!IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        return;
      }
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.10(this, paramString));
    }
    
    @JavascriptInterface
    public void onInitOfferWallFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitOfferWallFail(" + paramString + ")");
      IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(false);
      String str = new SSAObj(paramString).getString("errMsg");
      if (IronSourceWebView.this.mSavedState.reportInitOfferwall())
      {
        IronSourceWebView.this.mSavedState.setOfferwallReportInit(false);
        if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
          IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.15(this, str));
        }
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onInitOfferWallFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitOfferWallSuccess(String paramString)
    {
      IronSourceWebView.this.toastingErrMsg("onInitOfferWallSuccess", "true");
      IronSourceWebView.this.mSavedState.setOfferwallInitSuccess(true);
      if (IronSourceWebView.this.mSavedState.reportInitOfferwall())
      {
        IronSourceWebView.this.mSavedState.setOfferwallReportInit(false);
        if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
          IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.14(this));
        }
      }
    }
    
    @JavascriptInterface
    public void onInitRewardedVideoFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitRewardedVideoFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      DemandSource localDemandSource = IronSourceWebView.this.mDemandSourceManager.getDemandSourceByName(SSAEnums.ProductType.RewardedVideo, (String)localObject);
      if (localDemandSource != null) {
        localDemandSource.setDemandSourceInitState(3);
      }
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.6(this, str, (String)localObject));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onInitRewardedVideoFail", paramString);
    }
    
    @JavascriptInterface
    public void onInitRewardedVideoSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onInitRewardedVideoSuccess(" + paramString + ")");
      SSABCParameters localSSABCParameters = new SSABCParameters(paramString);
      IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSSABCParameters(localSSABCParameters);
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onInitRewardedVideoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onLoadBannerFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onLoadBannerFail()");
      Object localObject = new SSAObj(paramString);
      String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (TextUtils.isEmpty((CharSequence)localObject)) {}
      while (!IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Banner.toString())) {
        return;
      }
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.22(this, str, (String)localObject));
    }
    
    @JavascriptInterface
    public void onLoadBannerSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onLoadBannerSuccess()");
      String str = new SSAObj(paramString).getString("demandSourceName");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Banner.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.21(this, str));
      }
    }
    
    @JavascriptInterface
    public void onLoadInterstitialFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onLoadInterstitialFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (TextUtils.isEmpty((CharSequence)localObject)) {
        return;
      }
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.17(this, str, (String)localObject));
      }
      IronSourceWebView.this.toastingErrMsg("onLoadInterstitialFail", "true");
    }
    
    @JavascriptInterface
    public void onLoadInterstitialSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onLoadInterstitialSuccess(" + paramString + ")");
      String str = new SSAObj(paramString).getString("demandSourceName");
      setInterstitialAvailability(str, true);
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.16(this, str));
      }
      IronSourceWebView.this.toastingErrMsg("onLoadInterstitialSuccess", "true");
    }
    
    @JavascriptInterface
    public void onOfferWallGeneric(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onOfferWallGeneric(" + paramString + ")");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.mOnOfferWallListener.onOWGeneric("", "");
      }
    }
    
    @JavascriptInterface
    public void onShowInterstitialFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      if (TextUtils.isEmpty((CharSequence)localObject)) {
        return;
      }
      setInterstitialAvailability((String)localObject, false);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.18(this, str, (String)localObject));
      }
      IronSourceWebView.this.toastingErrMsg("onShowInterstitialFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowInterstitialSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialSuccess(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      String str = new SSAObj(paramString).getString("demandSourceName");
      if (TextUtils.isEmpty(str))
      {
        Logger.i(IronSourceWebView.this.TAG, "onShowInterstitialSuccess called with no demand");
        return;
      }
      IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.Interstitial.ordinal());
      IronSourceWebView.this.mSavedState.setDisplayedDemandSourceName(str);
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.Interstitial.toString()))
      {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.13(this, str));
        IronSourceWebView.this.toastingErrMsg("onShowInterstitialSuccess", paramString);
      }
      setInterstitialAvailability(str, false);
    }
    
    @JavascriptInterface
    public void onShowOfferWallFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowOfferWallFail(" + paramString + ")");
      String str = new SSAObj(paramString).getString("errMsg");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.9(this, str));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onShowOfferWallFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowOfferWallSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowOfferWallSuccess(" + paramString + ")");
      IronSourceWebView.this.mSavedState.adOpened(SSAEnums.ProductType.OfferWall.ordinal());
      String str = SDKUtils.getValueFromJsonObject(paramString, "placementId");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.OfferWall.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.8(this, str));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onShowOfferWallSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onShowRewardedVideoFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowRewardedVideoFail(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      String str = ((SSAObj)localObject).getString("errMsg");
      localObject = ((SSAObj)localObject).getString("demandSourceName");
      if (IronSourceWebView.this.shouldNotifyDeveloper(SSAEnums.ProductType.RewardedVideo.toString())) {
        IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.7(this, str, (String)localObject));
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onShowRewardedVideoFail", paramString);
    }
    
    @JavascriptInterface
    public void onShowRewardedVideoSuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onShowRewardedVideoSuccess(" + paramString + ")");
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      IronSourceWebView.this.toastingErrMsg("onShowRewardedVideoSuccess", paramString);
    }
    
    @JavascriptInterface
    public void onUDIAFail(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onUDIAFail(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onUDIASuccess(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "onUDIASuccess(" + paramString + ")");
    }
    
    @JavascriptInterface
    public void onVideoStatusChanged(String paramString)
    {
      Log.d(IronSourceWebView.this.TAG, "onVideoStatusChanged(" + paramString + ")");
      paramString = new SSAObj(paramString);
      String str = paramString.getString("productType");
      if ((IronSourceWebView.this.mVideoEventsListener != null) && (!TextUtils.isEmpty(str)) && (SSAEnums.ProductType.RewardedVideo.toString().equalsIgnoreCase(str)))
      {
        paramString = paramString.getString("status");
        if ("started".equalsIgnoreCase(paramString)) {
          IronSourceWebView.this.mVideoEventsListener.onVideoStarted();
        }
      }
      else
      {
        return;
      }
      if ("paused".equalsIgnoreCase(paramString))
      {
        IronSourceWebView.this.mVideoEventsListener.onVideoPaused();
        return;
      }
      if ("playing".equalsIgnoreCase(paramString))
      {
        IronSourceWebView.this.mVideoEventsListener.onVideoResumed();
        return;
      }
      if ("ended".equalsIgnoreCase(paramString))
      {
        IronSourceWebView.this.mVideoEventsListener.onVideoEnded();
        return;
      }
      if ("stopped".equalsIgnoreCase(paramString))
      {
        IronSourceWebView.this.mVideoEventsListener.onVideoStopped();
        return;
      }
      Logger.i(IronSourceWebView.this.TAG, "onVideoStatusChanged: unknown status: " + paramString);
    }
    
    @JavascriptInterface
    public void openUrl(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "openUrl(" + paramString + ")");
      Object localObject1 = new SSAObj(paramString);
      String str = ((SSAObj)localObject1).getString("url");
      Object localObject2 = ((SSAObj)localObject1).getString("method");
      localObject1 = IronSourceWebView.this.getCurrentActivityContext();
      try
      {
        if (((String)localObject2).equalsIgnoreCase("external_browser"))
        {
          UrlHandler.openUrl((Context)localObject1, str);
          return;
        }
        if (((String)localObject2).equalsIgnoreCase("webview"))
        {
          localObject2 = new Intent((Context)localObject1, OpenUrlActivity.class);
          ((Intent)localObject2).putExtra(IronSourceWebView.EXTERNAL_URL, str);
          ((Intent)localObject2).putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, true);
          ((Intent)localObject2).putExtra("immersive", IronSourceWebView.this.mIsImmersive);
          ((Context)localObject1).startActivity((Intent)localObject2);
          return;
        }
      }
      catch (Exception localException)
      {
        IronSourceWebView.this.responseBack(paramString, false, localException.getMessage(), null);
        localException.printStackTrace();
        return;
      }
      if (((String)localObject2).equalsIgnoreCase("store"))
      {
        localObject2 = new Intent((Context)localObject1, OpenUrlActivity.class);
        ((Intent)localObject2).putExtra(IronSourceWebView.EXTERNAL_URL, localException);
        ((Intent)localObject2).putExtra(IronSourceWebView.IS_STORE, true);
        ((Intent)localObject2).putExtra(IronSourceWebView.SECONDARY_WEB_VIEW, true);
        ((Context)localObject1).startActivity((Intent)localObject2);
      }
    }
    
    @JavascriptInterface
    public void permissionsAPI(String paramString)
    {
      try
      {
        Logger.i(IronSourceWebView.this.TAG, "permissionsAPI(" + paramString + ")");
        paramString = new SSAObj(paramString);
        IronSourceWebView.this.mPermissionsJsAdapter.call(paramString.toString(), new IronSourceWebView.JSInterface.JSCallbackTask(this));
        return;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        Logger.i(IronSourceWebView.this.TAG, "permissionsAPI failed with exception " + paramString.getMessage());
      }
    }
    
    @JavascriptInterface
    public void postAdEventNotification(String paramString)
    {
      try
      {
        Logger.i(IronSourceWebView.this.TAG, "postAdEventNotification(" + paramString + ")");
        Object localObject = new SSAObj(paramString);
        String str1 = ((SSAObj)localObject).getString("eventName");
        if (TextUtils.isEmpty(str1))
        {
          IronSourceWebView.this.responseBack(paramString, false, "eventName does not exist", null);
          return;
        }
        String str2 = ((SSAObj)localObject).getString("dsName");
        JSONObject localJSONObject = (JSONObject)((SSAObj)localObject).get("extData");
        String str3 = ((SSAObj)localObject).getString("productType");
        localObject = IronSourceWebView.this.getStringProductTypeAsEnum(str3);
        if (IronSourceWebView.this.shouldNotifyDeveloper(str3))
        {
          paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
          if (!TextUtils.isEmpty(paramString))
          {
            str3 = IronSourceWebView.this.parseToJson("productType", str3, "eventName", str1, "demandSourceName", str2, null, null, null, false);
            paramString = IronSourceWebView.this.generateJSToInject(paramString, str3, "postAdEventNotificationSuccess", "postAdEventNotificationFail");
            IronSourceWebView.this.injectJavascript(paramString);
          }
          IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.27(this, (SSAEnums.ProductType)localObject, str2, str1, localJSONObject));
          return;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return;
      }
      IronSourceWebView.this.responseBack(paramString, false, "productType does not exist", null);
    }
    
    @JavascriptInterface
    public void removeCloseEventHandler(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "removeCloseEventHandler(" + paramString + ")");
      if (IronSourceWebView.this.mCloseEventTimer != null) {
        IronSourceWebView.this.mCloseEventTimer.cancel();
      }
      IronSourceWebView.access$902(IronSourceWebView.this, true);
    }
    
    @JavascriptInterface
    public void saveFile(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "saveFile(" + paramString + ")");
      SSAFile localSSAFile = new SSAFile(paramString);
      if (DeviceStatus.getAvailableMemorySizeInMegaBytes(IronSourceWebView.this.mCacheDirectory) <= 0L)
      {
        IronSourceWebView.this.responseBack(paramString, false, "no_disk_space", null);
        return;
      }
      if (!SDKUtils.isExternalStorageAvailable())
      {
        IronSourceWebView.this.responseBack(paramString, false, "sotrage_unavailable", null);
        return;
      }
      if (IronSourceStorageUtils.isFileCached(IronSourceWebView.this.mCacheDirectory, localSSAFile))
      {
        IronSourceWebView.this.responseBack(paramString, false, "file_already_exist", null);
        return;
      }
      if (!ConnectivityService.isConnected(IronSourceWebView.this.getContext()))
      {
        IronSourceWebView.this.responseBack(paramString, false, "no_network_connection", null);
        return;
      }
      IronSourceWebView.this.responseBack(paramString, true, null, null);
      paramString = localSSAFile.getLastUpdateTime();
      String str;
      if (paramString != null)
      {
        str = String.valueOf(paramString);
        if (!TextUtils.isEmpty(str))
        {
          paramString = localSSAFile.getPath();
          if (!paramString.contains("/")) {
            break label233;
          }
          paramString = localSSAFile.getPath().split("/");
          paramString = paramString[(paramString.length - 1)];
        }
      }
      label233:
      for (;;)
      {
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setCampaignLastUpdate(paramString, str);
        IronSourceWebView.this.downloadManager.downloadFile(localSSAFile);
        return;
      }
    }
    
    @JavascriptInterface
    public void setAllowFileAccessFromFileURLs(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setAllowFileAccessFromFileURLs(" + paramString + ")");
      boolean bool = new SSAObj(paramString).getBoolean("allowFileAccess");
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.3(this, bool));
    }
    
    @JavascriptInterface
    public void setBackButtonState(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setBackButtonState(" + paramString + ")");
      paramString = new SSAObj(paramString).getString("state");
      IronSourceSharedPrefHelper.getSupersonicPrefHelper().setBackButtonState(paramString);
    }
    
    @JavascriptInterface
    public void setForceClose(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setForceClose(" + paramString + ")");
      paramString = new SSAObj(paramString);
      String str1 = paramString.getString("width");
      String str2 = paramString.getString("height");
      IronSourceWebView.access$602(IronSourceWebView.this, Integer.parseInt(str1));
      IronSourceWebView.access$702(IronSourceWebView.this, Integer.parseInt(str2));
      IronSourceWebView.access$802(IronSourceWebView.this, paramString.getString("position"));
    }
    
    @JavascriptInterface
    public void setMixedContentAlwaysAllow(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setMixedContentAlwaysAllow(" + paramString + ")");
      IronSourceWebView.this.runOnUiThread(new IronSourceWebView.JSInterface.2(this));
    }
    
    @JavascriptInterface
    public void setOrientation(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setOrientation(" + paramString + ")");
      paramString = new SSAObj(paramString).getString("orientation");
      IronSourceWebView.this.setOrientationState(paramString);
      int i = DeviceStatus.getApplicationRotation(IronSourceWebView.this.getCurrentActivityContext());
      if (IronSourceWebView.this.mChangeListener != null) {
        IronSourceWebView.this.mChangeListener.onOrientationChanged(paramString, i);
      }
    }
    
    @JavascriptInterface
    public void setStoreSearchKeys(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setStoreSearchKeys(" + paramString + ")");
      IronSourceSharedPrefHelper.getSupersonicPrefHelper().setSearchKeys(paramString);
    }
    
    @JavascriptInterface
    public void setUserData(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setUserData(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("key"))
      {
        IronSourceWebView.this.responseBack(paramString, false, "key does not exist", null);
        return;
      }
      if (!((SSAObj)localObject).containsKey("value"))
      {
        IronSourceWebView.this.responseBack(paramString, false, "value does not exist", null);
        return;
      }
      String str = ((SSAObj)localObject).getString("key");
      localObject = ((SSAObj)localObject).getString("value");
      if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUserData(str, (String)localObject))
      {
        paramString = IronSourceWebView.this.extractSuccessFunctionToCall(paramString);
        str = IronSourceWebView.this.parseToJson(str, (String)localObject, null, null, null, null, null, null, null, false);
        paramString = IronSourceWebView.this.generateJSToInject(paramString, str);
        IronSourceWebView.this.injectJavascript(paramString);
        return;
      }
      IronSourceWebView.this.responseBack(paramString, false, "SetUserData failed writing to shared preferences", null);
    }
    
    @JavascriptInterface
    public void setUserUniqueId(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setUserUniqueId(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if ((!((SSAObj)localObject).containsKey("userUniqueId")) || (!((SSAObj)localObject).containsKey("productType")))
      {
        IronSourceWebView.this.responseBack(paramString, false, "uniqueId or productType does not exist", null);
        return;
      }
      String str = ((SSAObj)localObject).getString("userUniqueId");
      localObject = ((SSAObj)localObject).getString("productType");
      if (IronSourceSharedPrefHelper.getSupersonicPrefHelper().setUniqueId(str, (String)localObject))
      {
        IronSourceWebView.this.responseBack(paramString, true, null, null);
        return;
      }
      IronSourceWebView.this.responseBack(paramString, false, "setUserUniqueId failed", null);
    }
    
    @JavascriptInterface
    public void setWebviewBackgroundColor(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "setWebviewBackgroundColor(" + paramString + ")");
      IronSourceWebView.this.setWebviewBackground(paramString);
    }
    
    @JavascriptInterface
    public void toggleUDIA(String paramString)
    {
      Logger.i(IronSourceWebView.this.TAG, "toggleUDIA(" + paramString + ")");
      Object localObject = new SSAObj(paramString);
      if (!((SSAObj)localObject).containsKey("toggle")) {
        IronSourceWebView.this.responseBack(paramString, false, "toggle key does not exist", null);
      }
      int i;
      do
      {
        return;
        i = Integer.parseInt(((SSAObj)localObject).getString("toggle"));
      } while (i == 0);
      localObject = Integer.toBinaryString(i);
      if (TextUtils.isEmpty((CharSequence)localObject))
      {
        IronSourceWebView.this.responseBack(paramString, false, "fialed to convert toggle", null);
        return;
      }
      if (localObject.toCharArray()[3] == '0')
      {
        IronSourceSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(true);
        return;
      }
      IronSourceSharedPrefHelper.getSupersonicPrefHelper().setShouldRegisterSessions(false);
    }
  }
  
  public static enum State
  {
    Display,  Gone;
    
    private State() {}
  }
}