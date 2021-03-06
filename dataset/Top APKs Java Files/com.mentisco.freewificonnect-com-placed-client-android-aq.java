package com.placed.client.android;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class aq
{
  aq() {}
  
  public static Map<String, String> a(Context paramContext)
  {
    for (;;)
    {
      try
      {
        Object localObject = paramContext.getPackageManager().getInstalledPackages(128);
        paramContext = new HashMap();
        localObject = ((List)localObject).iterator();
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (localPackageInfo.packageName != null) {
          if (localPackageInfo.versionName != null) {
            paramContext.put(localPackageInfo.packageName, localPackageInfo.versionName);
          } else {
            paramContext.put(localPackageInfo.packageName, "0");
          }
        }
      }
      catch (Exception paramContext)
      {
        q.a("PackageGatherer", "Unable to get packages", paramContext);
        return null;
      }
    }
    return paramContext;
  }
  
  public static void a(Context paramContext, ah paramAh)
  {
    if (!o.an) {
      return;
    }
    for (;;)
    {
      try
      {
        paramContext = (ActivityManager)paramContext.getSystemService("activity");
        Object localObject2 = paramContext.getRunningAppProcesses();
        paramContext = paramContext.getRunningTasks(100);
        Object localObject1 = new ArrayList(((List)localObject2).size());
        ArrayList localArrayList = new ArrayList(paramContext.size());
        localObject2 = ((List)localObject2).iterator();
        int i = 0;
        Object localObject3;
        if (((Iterator)localObject2).hasNext())
        {
          localObject3 = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
          JSONObject localJSONObject = new JSONObject();
          try
          {
            localJSONObject.put("processName", ((ActivityManager.RunningAppProcessInfo)localObject3).processName);
            ((List)localObject1).add(new ar("process_" + i, localJSONObject.toString()));
            q.a("PackageGatherer", "Running process: " + ((ActivityManager.RunningAppProcessInfo)localObject3).processName + ": " + localJSONObject);
            i += 1;
          }
          catch (JSONException localJSONException2)
          {
            q.a("PackageGatherer", "Error saving running package list");
            continue;
          }
        }
        paramAh.a("running_tasks", localArrayList, true);
      }
      catch (SecurityException paramContext)
      {
        q.a("PackageGatherer", "SecurityException: Do you have the right permission?", paramContext);
        return;
        paramAh.a("running_apps", (List)localObject1, true);
        localObject1 = paramContext.iterator();
        i = 0;
        if (((Iterator)localObject1).hasNext())
        {
          paramContext = (ActivityManager.RunningTaskInfo)((Iterator)localObject1).next();
          localObject2 = new JSONObject();
          localObject3 = paramContext.baseActivity.toString();
          if (paramContext.description == null) {
            break label423;
          }
          paramContext = paramContext.description.toString();
          try
          {
            ((JSONObject)localObject2).put("activityName", localObject3);
            ((JSONObject)localObject2).put("description", paramContext);
            localArrayList.add(new ar("task_" + i, ((JSONObject)localObject2).toString()));
            q.a("PackageGatherer", "Running task: " + (String)localObject3 + ": " + paramContext);
            i += 1;
          }
          catch (JSONException localJSONException1)
          {
            q.a("PackageGatherer", "Error saving running package list");
            continue;
          }
        }
      }
      catch (Exception paramContext)
      {
        q.a("PackageGatherer", "Error, unable to get package data", paramContext);
        return;
      }
      return;
      label423:
      paramContext = null;
    }
  }
  
  public static void b(Context paramContext, ah paramAh)
  {
    try
    {
      long l = paramContext.getSharedPreferences("placed_pg_prefs", 0).getLong("placed_pg_lpf", 0L);
      if (System.currentTimeMillis() - l <= 86400000L) {
        return;
      }
      Map localMap = a(paramContext);
      if (localMap == null) {
        return;
      }
      ArrayList localArrayList = new ArrayList(localMap.size());
      Iterator localIterator = localMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        localArrayList.add(new ar(str, (String)localMap.get(str)));
      }
      paramAh.a("packages", localArrayList, true);
    }
    catch (Exception paramContext)
    {
      q.a("PackageGatherer", "Unable to log packages", paramContext);
      return;
    }
    paramContext.getSharedPreferences("placed_pg_prefs", 0).edit().putLong("placed_pg_lpf", System.currentTimeMillis()).commit();
  }
}
