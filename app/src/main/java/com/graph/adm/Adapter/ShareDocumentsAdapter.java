package com.graph.adm.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.graph.adm.R;
import com.graph.adm.Utils.Utils;
import com.graph.adm.model.documents.myDocuments.MyDocumentValue;
import com.graph.adm.model.documents.sharedDocuments.SharedDocumentsValue;

import java.util.ArrayList;
import java.util.List;

public class ShareDocumentsAdapter extends RecyclerView.Adapter<ShareDocumentsAdapter.ViewHolder> implements Filterable {

    private Context context;
    List<SharedDocumentsValue> data,dataFiltered;
    private IonItemSelect ionItemSelect;

    public ShareDocumentsAdapter(Context context, List<SharedDocumentsValue> data) {
        this.context = context;
        this.data = data;
        this.dataFiltered=data;
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
        try {
            holder.tv_user_nm.setText(data.get(position).getRemoteItem().getShared().getSharedBy().getUser().getDisplayName());
        } catch (Exception e) {
            holder.tv_user_nm.setText(data.get(position).getCreatedBy().getUser().getDisplayName());
        } ///holder.tv_cat.setText(data.get(position).getParentReference().getDriveType());
        holder.tv_time_days.setText(Utils.getStringDateString(data.get(position).getFileSystemInfo().getCreatedDateTime(),"EEE, dd MMM YY"));
        if (data.get(position).getFile() == null) {
            holder.iv_type.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_folder));
            holder.tv_file_name.setTypeface(holder.tv_file_name.getTypeface(), Typeface.NORMAL);
        } else {
            Typeface typeface = ResourcesCompat.getFont(context, R.font.roboto);
            holder.tv_file_name.setTypeface(typeface);
            holder.tv_file_name.setTypeface(holder.tv_file_name.getTypeface(), Typeface.BOLD);
            holder.iv_type.setImageDrawable(context.getResources().getDrawable(Utils.getImf(data.get(position).getName().split("\\.")[1])));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    data = dataFiltered;
                } else {
                    ArrayList<SharedDocumentsValue> filteredList = new ArrayList<>();
                    for (SharedDocumentsValue row : dataFiltered) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    data = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = data;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data = (ArrayList<SharedDocumentsValue>) results.values;
                notifyDataSetChanged();
            }
        };
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
