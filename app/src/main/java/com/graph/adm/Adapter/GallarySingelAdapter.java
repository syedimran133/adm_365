package com.graph.adm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graph.adm.R;
import com.graph.adm.Utils.DownloadImageTask;
import com.graph.adm.model.singalPhoto.Value;

import java.util.List;

public class GallarySingelAdapter extends RecyclerView.Adapter<GallarySingelAdapter.ViewHolder> {

    private Context context;
    List<Value> data;
    private IonItemSelect ionItemSelect;

    public GallarySingelAdapter(Context context, List<Value> data) {
        this.context = context;
        this.data = data;
    }

    public void registerOnItemClickListener(IonItemSelect ionItemSelect) {
        this.ionItemSelect = ionItemSelect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallary_items_singel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String imgData = data.get(position).getThumbnails().get(0).getSmall().getUrl();
        if (imgData != null) {
            new DownloadImageTask(context,holder.img_bg).execute(imgData);
        } else {
            holder.img_bg.setImageDrawable(context.getResources().getDrawable(R.drawable.jade_default));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_bg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_bg = itemView.findViewById(R.id.roundedImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (ionItemSelect != null)
                ionItemSelect.onItemSelect(getAdapterPosition());
        }
    }

    public interface IonItemSelect {
        void onItemSelect(int position);
    }
}
