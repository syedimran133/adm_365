package com.graph.adm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graph.adm.R;
import com.graph.adm.Utils.Utils;
import com.graph.adm.model.documents.myDocuments.MyDocumentValue;
import com.graph.adm.model.documents.sharedDocuments.SharedDocumentsValue;

import java.util.List;

public class ShareDocumentsAdapter extends RecyclerView.Adapter<ShareDocumentsAdapter.ViewHolder> {

    private Context context;
    List<SharedDocumentsValue> data;
    private IonItemSelect ionItemSelect;

    public ShareDocumentsAdapter(Context context, List<SharedDocumentsValue> data) {
        this.context = context;
        this.data = data;
    }

    public void registerOnItemClickListener(IonItemSelect ionItemSelect) {
        this.ionItemSelect = ionItemSelect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.documents_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_file_name.setText(data.get(position).getName());
        holder.tv_user_nm.setText(data.get(position).getRemoteItem().getShared().getSharedBy().getUser().getDisplayName());
        ///holder.tv_cat.setText(data.get(position).getParentReference().getDriveType());
        holder.tv_time_days.setText(Utils.printDifference(Utils.getStringToDate(data.get(position).getFileSystemInfo().getCreatedDateTime())) + " ago");
        if (data.get(position).getFile() == null) {
            holder.iv_type.setImageDrawable(context.getResources().getDrawable(R.drawable.folder_icon));
        } else {
            holder.iv_type.setImageDrawable(context.getResources().getDrawable(R.drawable.file_earmark));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_time_days, tv_cat, tv_user_nm, tv_file_name;
        ImageView iv_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_time_days = itemView.findViewById(R.id.tv_time_days);
            tv_cat = itemView.findViewById(R.id.tv_cat);
            tv_user_nm = itemView.findViewById(R.id.tv_user_nm);
            tv_file_name = itemView.findViewById(R.id.tv_file_name);
            iv_type = itemView.findViewById(R.id.iv_type);
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
