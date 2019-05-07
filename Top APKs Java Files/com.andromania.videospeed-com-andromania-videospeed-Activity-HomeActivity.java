package com.andromania.videospeed.Activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.andromania.Network.AdFlags;
import com.andromania.Network.AdRequest;
import com.andromania.videospeed.InAppActivity;
import com.andromania.videospeed.outputvideo.OutputActivity;
import java.io.File;
import java.util.List;

public class HomeActivity
  extends AppCompatActivity
{
  public static String[] Activity_name = { "Output_Activity", "ViewVideo_Activity", "home_Activity", "video_Activity", "bucket_Activity", "Audio_Activity", "VidSpeed_Activity", "PreviewAddSelectedAudio_Activity", "searchvideo_Activity", "PreviewOriginalPlusSelectedAudio_Activity" };
  private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 2;
  private static final int RESULT_ACTIVITY = 1111;
  private static final int RESULT_LOAD_VIDEO = 1;
  String camVideoFile;
  File dir;
  String fullscreenadId;
  private String videoPath;
  private Uri videoUri;
  
  public HomeActivity() {}
  
  private void OpenRateDialogOnCounter()
  {
    if ((AdRequest.toBoolean(AdRequest.getPreferencesCustom(this, AdFlags.RatingAleart_Flag_ON_OFF, "Rating_dialog_show"))) && (AdRequest.stringToint(AdRequest.getPreferencesCustom(this, AdFlags.Rating_InappCounter, "firstactivity")) >= AdRequest.stringToint(AdRequest.getPreferencesCustom(this, AdFlags.RatingAleart_ParseCounter, "Rating_dialog_show"))))
    {
      AdRequest.setPreferencesCustom(this, AdFlags.Rating_InappCounter, "0", "firstactivity");
      new CustomDialog().show(getSupportFragmentManager(), "Tag");
    }
  }
  
  public void alertDialogCamera()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Info");
    localBuilder.setMessage("Pl.Record another video. Video should be more than 1 second.");
    localBuilder.setCancelable(false);
    localBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Object localObject = Environment.getExternalStorageDirectory().getAbsolutePath() + "/videoSpeed_CamCapture";
        HomeActivity.this.dir = new File((String)localObject);
        if (!HomeActivity.this.dir.exists()) {
          HomeActivity.this.dir.mkdirs();
        }
        HomeActivity.this.camVideoFile = (HomeActivity.this.dir.getAbsolutePath() + "/1videoSpeed_Cam" + System.currentTimeMillis() + ".mp4");
        localObject = new File(HomeActivity.this.camVideoFile);
        try
        {
          Intent localIntent1 = new Intent("android.media.action.VIDEO_CAPTURE");
          HomeActivity.access$002(HomeActivity.this, Uri.fromFile((File)localObject));
          localIntent1.setPackage(HomeActivity.this.getCamPackage());
          localIntent1.putExtra("output", HomeActivity.this.videoUri);
          HomeActivity.this.startActivityForResult(localIntent1, 2);
          paramAnonymousDialogInterface.cancel();
          return;
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            try
            {
              Intent localIntent2 = new Intent("android.media.action.VIDEO_CAPTURE");
              HomeActivity.access$002(HomeActivity.this, Uri.fromFile((File)localObject));
              localIntent2.putExtra("output", HomeActivity.this.videoUri);
              HomeActivity.this.startActivityForResult(localIntent2, 2);
            }
            catch (Exception localException1)
            {
              Toast.makeText(HomeActivity.this, "There appears to be some issue with Camera. Pl. Check", 1).show();
            }
          }
        }
      }
    });
    localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.create().show();
  }
  
  public String getCamPackage()
  {
    PackageManager localPackageManager = getPackageManager();
    String str2 = "";
    List localList = localPackageManager.getInstalledApplications(8192);
    int i = 0;
    for (;;)
    {
      String str1 = str2;
      if (i < localList.size())
      {
        if (((((ApplicationInfo)localList.get(i)).flags & 0x1) == 1) && (((ApplicationInfo)localList.get(i)).loadLabel(localPackageManager).toString().equalsIgnoreCase("Camera"))) {
          str1 = ((ApplicationInfo)localList.get(i)).packageName;
        }
      }
      else {
        return str1;
      }
      i += 1;
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 2)
    {
      if (paramInt2 != -1) {
        break label118;
      }
      if (this.videoUri == null) {
        Toast.makeText(this, "Something went wrong, please try again!", 1).show();
      }
    }
    else
    {
      return;
    }
    this.videoPath = this.videoUri.getPath().trim();
    label118:
    try
    {
      paramIntent = new MediaMetadataRetriever();
      paramIntent.setDataSource(this.videoPath);
      if (Long.parseLong(paramIntent.extractMetadata(9)) > 1500L)
      {
        paramIntent = new Intent(this, VideoSpeedActivity.class);
        paramIntent.putExtra("path", this.videoPath);
        startActivityForResult(paramIntent, 1111);
        return;
      }
      alertDialogCamera();
      return;
    }
    catch (Exception paramIntent) {}
    if (paramInt2 == 0)
    {
      Toast.makeText(this, "Video recording cancelled.", 1).show();
      return;
    }
    Toast.makeText(this, "Failed to record video. Pl. try again.", 1).show();
    return;
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131361821);
    this.fullscreenadId = getString(2131492864);
    AdRequest.showFirstscreenAdd(this, 112, "main_Activity", this.fullscreenadId);
  }
  
  public void onMoreApps(View paramView)
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=pub:AndroTechMania")));
      return;
    }
    catch (ActivityNotFoundException paramView)
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/search?q=pub:AndroTechMania")));
    }
  }
  
  public void onMyStudio(View paramView)
  {
    paramView = new Intent(this, OutputActivity.class);
    paramView.putExtra("bucketName", "1VideoSpeed");
    startActivity(paramView);
  }
  
  public void onPurchage(View paramView)
  {
    startActivity(new Intent(this, InAppActivity.class));
  }
  
  public void onRateUs(View paramView)
  {
    try
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.andromania.videospeed")));
      return;
    }
    catch (ActivityNotFoundException paramView)
    {
      startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.andromania.videospeed")));
    }
  }
  
  public void onSelectCamera(View paramView)
  {
    this.dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/1videoSpeed_CamCapture");
    if (!this.dir.exists()) {
      this.dir.mkdirs();
    }
    this.camVideoFile = (this.dir.getAbsolutePath() + "/1videoSpeed_Cam" + System.currentTimeMillis() + ".mp4");
    paramView = new File(this.camVideoFile);
    try
    {
      Intent localIntent1 = new Intent("android.media.action.VIDEO_CAPTURE");
      this.videoUri = Uri.fromFile(paramView);
      localIntent1.setPackage(getCamPackage());
      localIntent1.putExtra("output", this.videoUri);
      startActivityForResult(localIntent1, 2);
      return;
    }
    catch (Exception localException)
    {
      try
      {
        Intent localIntent2 = new Intent("android.media.action.VIDEO_CAPTURE");
        this.videoUri = Uri.fromFile(paramView);
        localIntent2.putExtra("output", this.videoUri);
        startActivityForResult(localIntent2, 2);
        return;
      }
      catch (Exception paramView)
      {
        Toast.makeText(this, "There appears to be some issue with Camera. Pl. Check", 1).show();
      }
    }
  }
  
  public void onSelectVideo(View paramView)
  {
    startActivity(new Intent(this, BucketActivity.class));
  }
  
  public void onShare(View paramView)
  {
    paramView = new Intent("android.intent.action.SEND");
    paramView.setType("text/plain");
    paramView.putExtra("android.intent.extra.TEXT", getResources().getString(2131492961) + "https://play.google.com/store/apps/details?id=com.andromania.videospeed");
    startActivity(Intent.createChooser(paramView, getResources().getString(2131492960)));
  }
  
  protected void onStart()
  {
    super.onStart();
    OpenRateDialogOnCounter();
    AdRequest.setQueryFire(this, Activity_name);
  }
  
  public static class CustomDialog
    extends AppCompatDialogFragment
  {
    View.OnClickListener CancelAction = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        HomeActivity.CustomDialog.this.dismiss();
      }
    };
    View.OnClickListener doneAction = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          HomeActivity.CustomDialog.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.andromania.videospeed")));
          HomeActivity.CustomDialog.this.dismiss();
          return;
        }
        catch (ActivityNotFoundException paramAnonymousView)
        {
          for (;;)
          {
            HomeActivity.CustomDialog.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.andromania.videospeed")));
          }
        }
      }
    };
    
    public CustomDialog() {}
    
    public View onCreateView(LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
    {
      paramLayoutInflater = paramLayoutInflater.inflate(2131361852, paramViewGroup, false);
      getDialog().setTitle("Sample");
      paramViewGroup = (Button)paramLayoutInflater.findViewById(2131230723);
      paramBundle = (Button)paramLayoutInflater.findViewById(2131230722);
      paramViewGroup.setOnClickListener(this.doneAction);
      paramBundle.setOnClickListener(this.CancelAction);
      return paramLayoutInflater;
    }
  }
}
