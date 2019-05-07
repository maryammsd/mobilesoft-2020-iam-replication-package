package com.view.application.newproject;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemAppsApplicationActivity
  extends AppCompatActivity
{
  ArrayAdapter adapter;
  ListView applicationListView;
  private SearchView editsearch;
  List<String> your_array_list;
  
  public SystemAppsApplicationActivity() {}
  
  @NonNull
  private static Bitmap getBitmapFromDrawable(@NonNull Drawable paramDrawable)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
    paramDrawable.draw(localCanvas);
    return localBitmap;
  }
  
  private ArrayList<Applications.PInfo> getInstalledApps(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = getPackageManager();
    int i = 0;
    localObject = ((PackageManager)localObject).getInstalledPackages(0);
    while (i < ((List)localObject).size())
    {
      PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
      if (((localPackageInfo.applicationInfo.flags & 0x1) != 0) && ((paramBoolean) || (localPackageInfo.versionName != null)))
      {
        Applications.PInfo localPInfo = new Applications.PInfo();
        localPInfo.appname = localPackageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
        localPInfo.pname = localPackageInfo.packageName;
        localPInfo.versionName = localPackageInfo.versionName;
        localPInfo.versionCode = localPackageInfo.versionCode;
        localPInfo.lastDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(localPackageInfo.firstInstallTime));
        localPInfo.icon = localPackageInfo.applicationInfo.loadIcon(getPackageManager());
        localArrayList.add(localPInfo);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  protected void onCreate(final Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361821);
    this.applicationListView = ((ListView)findViewById(2131230826));
    paramBundle = getInstalledApps(false);
    this.editsearch = ((SearchView)findViewById(2131230868));
    this.adapter = new ArrayAdapter(this, paramBundle);
    this.applicationListView.setAdapter(this.adapter);
    this.editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener()
    {
      public boolean onQueryTextChange(String paramAnonymousString)
      {
        Log.i("well", " this worked");
        SystemAppsApplicationActivity.this.adapter.getFilter().filter(paramAnonymousString);
        return false;
      }
      
      public boolean onQueryTextSubmit(String paramAnonymousString)
      {
        return false;
      }
    });
    this.applicationListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (Applications.PInfo)paramBundle.get(paramAnonymousInt);
        paramAnonymousAdapterView = new Intent(SystemAppsApplicationActivity.this, DetailActivity.class);
        paramAnonymousAdapterView.putExtra("appname", ((Applications.PInfo)paramBundle.get(paramAnonymousInt)).appname);
        paramAnonymousAdapterView.putExtra("lastDate", ((Applications.PInfo)paramBundle.get(paramAnonymousInt)).lastDate);
        paramAnonymousAdapterView.putExtra("pname", ((Applications.PInfo)paramBundle.get(paramAnonymousInt)).pname);
        paramAnonymousAdapterView.putExtra("versionCode", ((Applications.PInfo)paramBundle.get(paramAnonymousInt)).versionCode);
        paramAnonymousAdapterView.putExtra("versionName", ((Applications.PInfo)paramBundle.get(paramAnonymousInt)).versionName);
        paramAnonymousView = SystemAppsApplicationActivity.getBitmapFromDrawable(((Applications.PInfo)paramBundle.get(paramAnonymousInt)).icon);
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        paramAnonymousView.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
        paramAnonymousAdapterView.putExtra("icon", localByteArrayOutputStream.toByteArray());
        SystemAppsApplicationActivity.this.startActivity(paramAnonymousAdapterView);
      }
    });
  }
}
