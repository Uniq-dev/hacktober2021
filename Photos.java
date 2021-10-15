 package com.example.imagelover.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.imagelover.retrofit.ApiClient;
import com.example.imagelover.adapters.ImageAdapter;
import com.example.imagelover.retrofit.ImageModel;
import com.example.imagelover.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class Photos extends AppCompatActivity {
private int page=1;
private int pageSize=30;
private boolean isLoading,isLast;
List<ImageModel>  imageModels=new ArrayList<>();
private GridLayoutManager manager;
private ImageAdapter imageAdapter;
private ProgressDialog progressDialog;
private RecyclerView recyclerView;
private static Photos instance;
private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(instance == null){
            instance = this;
        }
        setContentView(R.layout.activity_photos);
        imageAdapter=new ImageAdapter(this,imageModels);
        manager=new GridLayoutManager(this,3);
        recyclerView=findViewById(R.id.reyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(imageAdapter);
        progressDialog=new ProgressDialog(this);
         progressDialog.setMessage("Loading...");
         progressDialog.setCancelable(false);
        progressBar = findViewById(R.id.progressBar);
         progressDialog.show();
        getAllImages();
         /* To work on Pagination*/
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItem=manager.getChildCount();
                int totalItem=manager.getItemCount();
                int firstVisisbleItemPosition=manager.findFirstVisibleItemPosition();
                if(!isLoading&&!isLast){
                    if((visibleItem+firstVisisbleItemPosition>=totalItem)&&(firstVisisbleItemPosition>=0)&&(totalItem>=pageSize)){
                        progressBar.setVisibility(View.VISIBLE);
                        page++;
                        getAllImages();
                    }
                }
            }
        });
    }
//    To get images from API and add in recyler view
    public void  getAllImages(){
        isLoading=true;
        Call<List<ImageModel>> imagemodel= ApiClient.getInterface().getAllImages(page,30);
        imagemodel.enqueue(new Callback<List<ImageModel>>() {
            @Override
            public void onResponse(Call<List<ImageModel>> call, Response<List<ImageModel>> response) {
                 if(response.raw().cacheResponse() != null
                        && response.raw().networkResponse() == null){
                     Toast.makeText(Photos.this, " response is from CACHE...", Toast.LENGTH_SHORT).show();
//                    Log.d("ntwork", "onResponse: response is from CACHE...");
                }
                if(response.body()!=null){
                    progressBar.setVisibility(View.INVISIBLE);
                    imageModels.addAll(response.body());
                    imageAdapter.notifyDataSetChanged();
                    Log.d("sumit", "onResponse: "+response.body());
                }else {
                    Toast.makeText(Photos.this, "Something Went Wrong  ", Toast.LENGTH_SHORT).show();
                }
                isLoading=false;
                progressDialog.dismiss();
                if (imageModels.size()>0){
                    isLast=imageModels.size()<pageSize;
                }else isLast=true;
            }

            @Override
            public void onFailure(Call<List<ImageModel>> call, Throwable t) {
                progressDialog.dismiss();
            String message= t.getLocalizedMessage();
                Toast.makeText(Photos.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
     public boolean hasNetwork(){
         return instance.isNetworkConnected();
     }
     private boolean isNetworkConnected(){
         ConnectivityManager cm =
                 (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

         NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
         return activeNetwork != null &&
                 activeNetwork.isConnectedOrConnecting();
     }
}