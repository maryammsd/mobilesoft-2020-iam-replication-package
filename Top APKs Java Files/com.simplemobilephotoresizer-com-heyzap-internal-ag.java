package com.heyzap.internal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import com.heyzap.http.JsonHttpResponseHandler;
import com.heyzap.http.RequestParams;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

class ag
  extends JsonHttpResponseHandler
{
  ag(af paramAf, boolean paramBoolean, Activity paramActivity, SharedPreferences paramSharedPreferences) {}
  
  public void onSuccess(int paramInt, Header[] paramArrayOfHeader, JSONObject paramJSONObject)
  {
    int k = 0;
    if ((this.a) && (this.b != null)) {
      this.b.runOnUiThread(new ah(this));
    }
    Object localObject2;
    Object localObject1;
    try
    {
      paramArrayOfHeader = paramJSONObject.optString("version", "0");
      localObject2 = this.d.a.getPackageManager().getInstalledPackages(0);
      localObject1 = new HashSet();
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        ((HashSet)localObject1).add(((PackageInfo)((Iterator)localObject2).next()).packageName);
      }
      localObject2 = paramJSONObject.optJSONArray("packages");
    }
    catch (Exception paramArrayOfHeader)
    {
      Logger.trace(paramArrayOfHeader);
      return;
    }
    int m;
    if (localObject2 != null)
    {
      m = ((JSONArray)localObject2).length();
      paramJSONObject = new byte[(((JSONArray)localObject2).length() + 7) / 8];
      paramInt = 0;
    }
    int j;
    for (int i = 0;; i = j)
    {
      if (paramInt < ((JSONArray)localObject2).length())
      {
        j = i;
        if (((HashSet)localObject1).contains(((JSONArray)localObject2).getString(paramInt)))
        {
          j = paramInt / 8;
          paramJSONObject[j] = ((byte)(paramJSONObject[j] | 1 << paramInt % 8));
          j = i + 1;
          k = 1;
        }
      }
      else
      {
        if (k != 0)
        {
          localObject1 = new RequestParams();
          ((RequestParams)localObject1).put("version", paramArrayOfHeader);
          ((RequestParams)localObject1).put("data", Base64.encodeToString(paramJSONObject, 2));
          APIClient.post(this.d.a, PackageManager.access$000() + "aip", (RequestParams)localObject1, new ai(this, i, m));
        }
        paramArrayOfHeader = this.c.edit();
        paramArrayOfHeader.putLong("last_checked_packages", System.currentTimeMillis());
        paramArrayOfHeader.commit();
        return;
      }
      paramInt += 1;
    }
  }
}
