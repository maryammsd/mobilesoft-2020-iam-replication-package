package jp.line.android.sdk.a.c;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import jp.line.android.sdk.LineSdkContext;
import jp.line.android.sdk.LineSdkContextManager;
import jp.line.android.sdk.a.a;
import jp.line.android.sdk.activity.WebLoginActivity;
import jp.line.android.sdk.api.ApiClient;
import jp.line.android.sdk.api.ApiRequestFuture;
import jp.line.android.sdk.api.ApiRequestFutureListener;
import jp.line.android.sdk.exception.LineSdkLoginError;
import jp.line.android.sdk.exception.LineSdkLoginException;
import jp.line.android.sdk.login.LineAuthManager;
import jp.line.android.sdk.login.LineLoginFuture;
import jp.line.android.sdk.login.LineLoginFuture.ProgressOfLogin;
import jp.line.android.sdk.login.LineLoginFutureProgressListener;
import jp.line.android.sdk.login.OnAccessTokenChangedListener;
import jp.line.android.sdk.model.AccessToken;
import jp.line.android.sdk.model.Otp;
import jp.line.android.sdk.model.RequestToken;

public final class b
  implements LineAuthManager, LineLoginFutureProgressListener
{
  d a;
  private final ExecutorService b = Executors.newCachedThreadPool();
  private List<OnAccessTokenChangedListener> c;
  
  public b() {}
  
  private final LineLoginFuture a(Activity paramActivity, boolean paramBoolean)
  {
    int i = 1;
    int j = 1;
    for (;;)
    {
      try
      {
        if (this.a == null) {
          break;
        }
        if (this.a.isForceLoginByOtherAccount() != paramBoolean) {
          break label168;
        }
        i = j;
        switch (1.a[this.a.getProgress().ordinal()])
        {
        case 4: 
        case 7: 
          if (this.a != null) {
            break label179;
          }
          this.a = new d(paramBoolean);
          this.a.addProgressListener(this);
          localD = this.a;
          label118:
          localD = this.a;
          if (i != 0) {
            this.b.execute(new f(paramActivity, localD));
          }
          return localD;
        }
      }
      finally {}
      this.a = null;
      i = j;
      continue;
      label168:
      this.a = null;
      i = j;
      continue;
      label179:
      localD = this.a;
    }
    d localD = g.a().b();
    if ((localD != null) && (localD.isForceLoginByOtherAccount() == paramBoolean)) {
      switch (1.a[localD.getProgress().ordinal()])
      {
      }
    }
    for (;;)
    {
      if (this.a == null)
      {
        this.a = new d(paramBoolean);
        this.a.addProgressListener(this);
        localD = this.a;
        break label118;
        this.a = localD;
        this.a.addProgressListener(this);
        continue;
      }
      localD = this.a;
      break label118;
      i = 0;
      break;
    }
  }
  
  static void a(d paramD, Activity paramActivity)
  {
    try
    {
      switch (1.a[paramD.getProgress().ordinal()])
      {
      case 1: 
      case 2: 
        paramD.a(LineLoginFuture.ProgressOfLogin.REQUESTED_OTP);
        g.a().a(paramD);
        LineSdkContextManager.getSdkContext().getApiClient().getOtp(new d(paramActivity, paramD));
        return;
      }
    }
    catch (Throwable paramActivity)
    {
      paramD.a(paramActivity);
      return;
    }
    g.a().a(paramD);
    if (paramActivity == null)
    {
      paramD.a(new LineSdkLoginException(LineSdkLoginError.FAILED_START_LOGIN_ACTIVITY));
      return;
    }
    Object localObject1;
    if (!paramD.isForceLoginByOtherAccount())
    {
      localObject1 = LineSdkContextManager.getSdkContext().getApplicationContext();
      Object localObject2 = ((Context)localObject1).getPackageManager().getInstalledApplications(128).iterator();
      while (((Iterator)localObject2).hasNext()) {
        if ("jp.naver.line.android".equals(((ApplicationInfo)((Iterator)localObject2).next()).packageName))
        {
          i = 1;
          if (i == 0) {
            break label401;
          }
          localObject2 = new Intent("jp.naver.line.android.intent.action.APPAUTH", null);
          if (((Context)localObject1).getPackageManager().queryIntentActivities((Intent)localObject2, 0).isEmpty()) {
            break label401;
          }
        }
      }
    }
    label401:
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        paramD.a(LineLoginFuture.ProgressOfLogin.STARTED_A2A_LOGIN);
        localObject1 = new Intent("jp.naver.line.android.intent.action.APPAUTH");
        ((Intent)localObject1).putExtra("channelId", String.valueOf(LineSdkContextManager.getSdkContext().getChannelId()));
        ((Intent)localObject1).putExtra("otpId", paramD.getOtp().id);
        ((Intent)localObject1).putExtra("appPackage", paramActivity.getPackageName());
        ((Intent)localObject1).putExtra("authScheme", LineSdkContextManager.getSdkContext().getAuthScheme());
        ((Intent)localObject1).addFlags(65536);
        try
        {
          paramActivity.startActivity((Intent)localObject1);
          return;
        }
        catch (Throwable paramActivity)
        {
          paramD.a(paramActivity);
          return;
        }
      }
      paramD.a(LineLoginFuture.ProgressOfLogin.STARTED_WEB_LOGIN);
      WebLoginActivity.a(paramActivity, paramD);
      return;
      paramD.a(LineLoginFuture.ProgressOfLogin.REQUESTED_ACCESS_TOKEN);
      g.a().a(paramD);
      LineSdkContextManager.getSdkContext().getApiClient().getAccessToken(paramD.getRequestToken().requestToken, paramD.getOtp().password, new c(paramD));
      return;
      return;
      i = 0;
      break;
    }
  }
  
  public final void a(AccessToken paramAccessToken)
  {
    ArrayList localArrayList = null;
    try
    {
      if (this.c != null) {
        localArrayList = new ArrayList(this.c);
      }
      if ((localArrayList != null) && (localArrayList.size() > 0)) {
        this.b.execute(new a(localArrayList, paramAccessToken));
      }
      return;
    }
    finally {}
  }
  
  public final boolean a()
  {
    return this.a != null;
  }
  
  public final boolean addOnAccessTokenChangedListener(OnAccessTokenChangedListener paramOnAccessTokenChangedListener)
  {
    try
    {
      if (this.c == null) {
        this.c = new ArrayList();
      }
      if (!this.c.contains(paramOnAccessTokenChangedListener))
      {
        boolean bool = this.c.add(paramOnAccessTokenChangedListener);
        return bool;
      }
      return false;
    }
    finally {}
  }
  
  public final LineLoginFuture b()
  {
    int i = 0;
    int j = 0;
    d localD2 = this.a;
    d localD1 = localD2;
    if (localD2 == null) {
      i = j;
    }
    for (;;)
    {
      try
      {
        if (this.a == null)
        {
          this.a = g.a().b();
          i = j;
          if (this.a != null) {
            this.a.addProgressListener(this);
          }
        }
        switch (1.a[this.a.getProgress().ordinal()])
        {
        case 1: 
          localD1 = this.a;
          if (i != 0) {
            this.b.execute(new c(this, localD1));
          }
          return localD1;
        }
      }
      finally {}
      i = 1;
      continue;
      i = j;
    }
  }
  
  public final Future<?> clearLocalLoginInfo()
  {
    b localB = new b();
    this.b.execute(localB);
    return localB;
  }
  
  public final AccessToken getAccessToken()
  {
    return a.a().b();
  }
  
  public final LineLoginFuture login(Activity paramActivity)
  {
    return a(paramActivity, false);
  }
  
  public final LineLoginFuture loginByAccount(Activity paramActivity)
  {
    return a(paramActivity, true);
  }
  
  public final Future<?> logout()
  {
    e localE = new e();
    this.b.execute(localE);
    return localE;
  }
  
  public final void onUpdateProgress(LineLoginFuture paramLineLoginFuture)
  {
    int j = 0;
    int k = 1;
    i = 1;
    paramLineLoginFuture = (d)paramLineLoginFuture;
    for (;;)
    {
      try
      {
        int m = 1.a[paramLineLoginFuture.getProgress().ordinal()];
        switch (m)
        {
        case 4: 
        case 6: 
        case 7: 
        default: 
          i = 0;
        case 9: 
        case 10: 
          k = 0;
          j = i;
          i = k;
        }
      }
      catch (Throwable localThrowable2)
      {
        i = 0;
        continue;
      }
      if (i != 0) {
        a(paramLineLoginFuture, null);
      }
      if (j != 0)
      {
        g.a();
        g.c();
      }
      return;
      g.a().a(paramLineLoginFuture);
      i = 0;
      continue;
      try
      {
        g.a().a(paramLineLoginFuture);
        i = k;
      }
      catch (Throwable localThrowable1)
      {
        i = k;
      }
      continue;
      i = 0;
      j = 1;
    }
  }
  
  public final boolean removeOnAccessTokenChangedListener(OnAccessTokenChangedListener paramOnAccessTokenChangedListener)
  {
    try
    {
      if ((this.c == null) || (!this.c.contains(paramOnAccessTokenChangedListener))) {
        return false;
      }
      boolean bool = this.c.remove(paramOnAccessTokenChangedListener);
      return bool;
    }
    finally {}
  }
  
  private static final class a
    implements Runnable
  {
    private List<OnAccessTokenChangedListener> a;
    private AccessToken b;
    
    public a(List<OnAccessTokenChangedListener> paramList, AccessToken paramAccessToken)
    {
      this.a = paramList;
      this.b = paramAccessToken;
    }
    
    public final void run()
    {
      Iterator localIterator = this.a.iterator();
      while (localIterator.hasNext())
      {
        OnAccessTokenChangedListener localOnAccessTokenChangedListener = (OnAccessTokenChangedListener)localIterator.next();
        AccessToken localAccessToken = this.b;
        try
        {
          localOnAccessTokenChangedListener.onAccessTokenChanged(localAccessToken);
        }
        catch (Throwable localThrowable) {}
      }
    }
  }
  
  static class b
    implements Runnable, Future<Object>
  {
    private final CountDownLatch a = new CountDownLatch(1);
    private boolean b;
    private ExecutionException c;
    
    public b() {}
    
    static AccessToken b()
    {
      AccessToken localAccessToken = a.a().b();
      if (localAccessToken != null) {
        a.a().c();
      }
      g.a();
      g.c();
      return localAccessToken;
    }
    
    void a()
      throws Exception
    {
      b();
    }
    
    public boolean cancel(boolean paramBoolean)
    {
      return false;
    }
    
    public Object get()
      throws InterruptedException, ExecutionException
    {
      this.a.await();
      if (this.c == null) {
        return null;
      }
      throw this.c;
    }
    
    public Object get(long paramLong, TimeUnit paramTimeUnit)
      throws InterruptedException, ExecutionException, TimeoutException
    {
      this.a.await(paramLong, paramTimeUnit);
      if (this.c == null) {
        return null;
      }
      throw this.c;
    }
    
    public boolean isCancelled()
    {
      return false;
    }
    
    public boolean isDone()
    {
      return this.b;
    }
    
    public void run()
    {
      try
      {
        a();
        return;
      }
      catch (Throwable localThrowable)
      {
        this.c = new ExecutionException(localThrowable);
        return;
      }
      finally
      {
        this.b = true;
        this.a.countDown();
      }
    }
  }
  
  private static final class c
    implements ApiRequestFutureListener<AccessToken>
  {
    private final d a;
    
    c(d paramD)
    {
      this.a = paramD;
    }
    
    public final void requestComplete(ApiRequestFuture<AccessToken> paramApiRequestFuture)
    {
      for (;;)
      {
        try
        {
          switch (b.1.b[paramApiRequestFuture.getStatus().ordinal()])
          {
          case 1: 
            this.a.a(new LineSdkLoginException(LineSdkLoginError.UNKNOWN, "Unknown ApiReqeustFuture.status. status = " + paramApiRequestFuture.getStatus()));
            return;
          }
        }
        catch (Throwable paramApiRequestFuture)
        {
          this.a.a(paramApiRequestFuture);
          return;
        }
        this.a.a((AccessToken)paramApiRequestFuture.getResponseObject());
        return;
        this.a.a();
        return;
        this.a.a(paramApiRequestFuture.getCause());
        return;
      }
    }
  }
  
  private static final class d
    implements ApiRequestFutureListener<Otp>
  {
    private final WeakReference<Activity> a;
    private final d b;
    
    d(Activity paramActivity, d paramD)
    {
      this.a = new WeakReference(paramActivity);
      this.b = paramD;
    }
    
    public final void requestComplete(ApiRequestFuture<Otp> paramApiRequestFuture)
    {
      for (;;)
      {
        try
        {
          switch (b.1.b[paramApiRequestFuture.getStatus().ordinal()])
          {
          case 1: 
            this.b.a(new LineSdkLoginException(LineSdkLoginError.UNKNOWN, "Unknown ApiReqeustFuture.status. status = " + paramApiRequestFuture.getStatus()));
            return;
          }
        }
        catch (Throwable paramApiRequestFuture)
        {
          this.b.a(paramApiRequestFuture);
          return;
        }
        this.b.a((Otp)paramApiRequestFuture.getResponseObject());
        b.a(this.b, (Activity)this.a.get());
        return;
        this.b.a();
        return;
        this.b.a(paramApiRequestFuture.getCause());
        return;
      }
    }
  }
  
  static final class e
    extends b.b
  {
    e() {}
    
    public final void a()
    {
      AccessToken localAccessToken = b.b.b();
      if (localAccessToken != null) {}
      try
      {
        LineSdkContextManager.getSdkContext().getApiClient().logout(localAccessToken.accessToken, null);
        return;
      }
      catch (Throwable localThrowable) {}
    }
  }
  
  private static final class f
    implements Runnable
  {
    private final Activity a;
    private final d b;
    
    f(Activity paramActivity, d paramD)
    {
      this.a = paramActivity;
      this.b = paramD;
    }
    
    public final void run()
    {
      if (!this.b.isForceLoginByOtherAccount())
      {
        AccessToken localAccessToken = a.a().b();
        if (localAccessToken != null)
        {
          this.b.a(localAccessToken);
          return;
        }
      }
      if ((this.a == null) && (this.b.getProgress().flowNumber < LineLoginFuture.ProgressOfLogin.GOT_REQUEST_TOKEN.flowNumber))
      {
        this.b.a(new LineSdkLoginException(LineSdkLoginError.FAILED_START_LOGIN_ACTIVITY, "activity is null"));
        return;
      }
      b.a(this.b, this.a);
    }
  }
}
