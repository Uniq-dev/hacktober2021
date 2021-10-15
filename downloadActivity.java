package com.example.imagelover.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.Progress;
import com.example.imagelover.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.List;

public class downloadActivity extends AppCompatActivity {
private ImageView imageView;
private Button download_btn;
private String url;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PRDownloader.initialize(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        imageView=findViewById(R.id.full);
        download_btn=findViewById(R.id.download);
        Intent intent=getIntent();
         url=intent.getStringExtra("link");

        Glide.with(getApplicationContext()).load(url).into(imageView);
        download_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                first check required permission Using Dexter then call download method don,t forget to  add permission in Manifest also
                Dexter.withContext(v.getContext())
                        .withPermissions(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ).withListener(new MultiplePermissionsListener() {
                    @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if(report.areAllPermissionsGranted()){
                            downloadImage();
                        }else
                        {
                            Toast.makeText(downloadActivity.this, "Permission Not Granted.."+report.toString(), Toast.LENGTH_SHORT).show();
                        }
                        /* ... */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {

                    }

                }).check();
            }
        });
    }
//    method to download from a given link
    private void downloadImage(){
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Downloading..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        File file= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);/*data will be storre in download file*/
//        Its better to define mime type as file which is to be downloaded else can write null.
        PRDownloader.download(url, file.getPath(), URLUtil.guessFileName(url,null,"image/jpeg"))
                .build()
                .setOnStartOrResumeListener(new OnStartOrResumeListener() {
                    @Override
                    public void onStartOrResume() {

                    }
                })
                .setOnPauseListener(new OnPauseListener() {
                    @Override
                    public void onPause() {

                    }
                })
                .setOnCancelListener(new OnCancelListener() {
                    @Override
                    public void onCancel() {

                    }
                })
                .setOnProgressListener(new OnProgressListener() {
                    @Override
                    public void onProgress(Progress progress) {
            long per=progress.currentBytes*100/progress.totalBytes;
            progressDialog.setMessage("Downloading.."+per+"%");
                    }
                })
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        progressDialog.dismiss();
                        Toast.makeText(downloadActivity.this, "Download Completed", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Error error) {
                        Toast.makeText(downloadActivity.this, "Not able to download", Toast.LENGTH_SHORT).show();
                    }

                });
    }

}