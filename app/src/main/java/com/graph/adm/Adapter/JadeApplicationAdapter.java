package com.graph.adm.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.graph.adm.R;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.DownloadImageTask;
import com.graph.adm.Utils.MSGraphRequestWrapper;
import com.graph.adm.model.announcement.ImageData;
import com.graph.adm.model.announcement.image.ImageDataInner;
import com.graph.adm.model.jadeapp.JadeAppValue;

import java.util.List;

public class JadeApplicationAdapter extends RecyclerView.Adapter<JadeApplicationAdapter.ViewHolder> {

    private Context context;
    List<JadeAppValue> data;
    private IonItemSelect ionItemSelect;

    public JadeApplicationAdapter(Context context, List<JadeAppValue> data) {
        this.context = context;
        this.data = data;
    }

    public void registerOnItemClickListener(IonItemSelect ionItemSelect) {
        this.ionItemSelect = ionItemSelect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jade_app_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ImageData imgUrlData = new Gson().fromJson(data.get(position).getFields().getIcon(), ImageData.class);
        getPhotoCallBack(holder.img_bg, imgUrlData.getFileName());
        holder.appTitle.setText(data.get(position).getFields().getTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView img_bg;
        TextView appTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_bg = itemView.findViewById(R.id.tv_app_img);
            appTitle = itemView.findViewById(R.id.tv_app_title);
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

    private void getPhotoCallBack(ImageView ivProfile, String id) {
        String url = "https://graph.microsoft.com/v1.0/sites/166e8b83-655e-4859-9811-329965d77859/drives/b!g4tuFl5lWUiYETKZZdd4WeuiIhnVUSFEkc1UsB6UJqD_xK9-pBwCSJDHbS4RTgoN/root:/Lists/b43b6506-5505-4c9b-98fc-f2df9d1125ba:/children?$filter=name eq '" + id + "'";
        MSGraphRequestWrapper.callGraphAPIUsingVolley(
                context,
                url,
                AppSingle.getInstance().getmAccessToken(),
                response -> {
                    try {
                        ImageDataInner imgUrlData = new Gson().fromJson(response.toString(), ImageDataInner.class);
                        String urlimg = imgUrlData.getValue().get(0).getMicrosoftGraphDownloadUrl();
                        new DownloadImageTask(context, ivProfile).execute(urlimg);
                    } catch (Exception e) {
                    }
                },
                error -> {
                    Log.d("TAG", "Error: " + error.toString());
                    //displayError(error);
                });
    }
}
