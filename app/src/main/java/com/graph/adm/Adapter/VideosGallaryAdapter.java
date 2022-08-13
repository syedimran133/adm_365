package com.graph.adm.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graph.adm.R;
import com.graph.adm.model.photo.Value;
import com.graph.adm.model.videogal.VideosGalValue;

import java.io.InputStream;
import java.util.List;

public class VideosGallaryAdapter extends RecyclerView.Adapter<VideosGallaryAdapter.ViewHolder> {

    private Context context;
    List<VideosGalValue> data;
    private IonItemSelect ionItemSelect;

    public VideosGallaryAdapter(Context context, List<VideosGalValue> data) {
        this.context = context;
        this.data = data;
    }

    public void registerOnItemClickListener(IonItemSelect ionItemSelect) {
        this.ionItemSelect = ionItemSelect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.videos_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getName());
       // holder.tvNumber.setText(data.get(position).getFolder().getChildCount().toString()+" Photos");

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName, tvNumber;
        RelativeLayout gal_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_alb_name);
            //tvNumber = itemView.findViewById(R.id.tv_alb_number);
            //gal_layout = itemView.findViewById(R.id.gal_layout);
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
