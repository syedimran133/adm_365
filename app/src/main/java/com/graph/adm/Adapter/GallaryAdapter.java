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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.graph.adm.R;
import com.graph.adm.model.announcement.Value;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {

    private Context context;
    List<Value> data;
    private IonItemSelect ionItemSelect;

    public AnnouncementAdapter(Context context, List<Value> data) {
        this.context = context;
        this.data = data;
    }

    public void registerOnItemClickListener(IonItemSelect ionItemSelect) {
        this.ionItemSelect = ionItemSelect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvSub.setText(data.get(position).getFields().getTitle());
        holder.tvDetails.setText(data.get(position).getFields().getDescription().trim().replaceAll(" +", " "));
        holder.tv_announ_type.setText(data.get(position).getFields().getAnnouncementType());
        String type = data.get(position).getFields().getAnnouncementType();
        String imgData = data.get(position).getFields().getImage();
        if (imgData != null) {
            new DownloadImageTask(holder.ivAnnouncement).execute(imgData);
        } else {
            holder.ivAnnouncement.setImageDrawable(context.getResources().getDrawable(R.drawable.jade_default));
        }
        if (type.equalsIgnoreCase("Organization")) {
            holder.tv_announ_type.setBackground(ContextCompat.getDrawable(context, R.drawable.announ_type_org));
        } else if (type.equalsIgnoreCase("General")) {
            holder.tv_announ_type.setBackground(ContextCompat.getDrawable(context, R.drawable.announ_type_gen));
        } else if (type.equalsIgnoreCase("Department")) {
            holder.tv_announ_type.setBackground(ContextCompat.getDrawable(context, R.drawable.announ_type));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivAnnouncement;
        TextView tvSub, tvDetails, tv_announ_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSub = itemView.findViewById(R.id.tv_subject);
            tvDetails = itemView.findViewById(R.id.tv_description);
            ivAnnouncement = itemView.findViewById(R.id.iv_announcement);
            tv_announ_type = itemView.findViewById(R.id.tv_announ_type);
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

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null)
                bmImage.setImageBitmap(result);
            else
                bmImage.setImageDrawable(context.getResources().getDrawable(R.drawable.jade_default));
        }
    }
}
