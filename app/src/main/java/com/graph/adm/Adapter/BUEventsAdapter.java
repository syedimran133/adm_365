package com.graph.adm.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graph.adm.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    //ArrayList<Value> data, dataFiltered;
    private IonItemSelect ionItemSelect;

    public NotificationAdapter(Context context) {
        this.context = context;
        //this.data = data;
    }

    public void registerOnItemClickListener(IonItemSelect ionItemSelect) {
        this.ionItemSelect = ionItemSelect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notofication_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (position % 2 == 0) {
            holder.li_bg.setBackgroundColor(Color.parseColor("#D3EEF7"));
            holder.tvIsread.setText("Unread");
        } else {
            holder.li_bg.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tvIsread.setText("Read");
        }

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout li_bg;
        TextView tvSub, tvDetails,tvIsread;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSub = itemView.findViewById(R.id.tv_subject);
            tvDetails = itemView.findViewById(R.id.tv_description);
            tvIsread = itemView.findViewById(R.id.tv_isread);
            li_bg = itemView.findViewById(R.id.li_bg);
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
