package com.example.imagelover.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.imagelover.retrofit.ImageModel;
import com.example.imagelover.R;
import com.example.imagelover.activity.downloadActivity;

import java.util.List;

public class  ImageAdapter  extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context context;
    private List<ImageModel> list;
    public ImageAdapter(Context context, List<ImageModel> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getUrls().getRegular()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            imageView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {

            int position=this.getAdapterPosition();
            Intent intent=new Intent(context, downloadActivity.class);
            intent.putExtra("link",list.get(position).getUrls().getRegular());
            context.startActivity(intent);
           }
    }
}
