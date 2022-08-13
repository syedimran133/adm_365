package com.graph.adm.Adapter;

import android.annotation.SuppressLint;
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
import com.graph.adm.Utils.Utils;
import com.graph.adm.model.notifications.Value;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private List<Value> data;
    private IonItemSelect ionItemSelect;

    public NotificationAdapter(Context context,List<Value> data) {
        this.context = context;
        this.data = data;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvSub.setText(data.get(position).getFields().getTitle());
        holder.tvDetails.setText(data.get(position).getFields().getDescription());
        holder.note_user.setText(data.get(position).getFields().getNotificationAuthor());
        if(data.get(position).getFields().getIsRead().equalsIgnoreCase("No")){
            holder.tvIsread.setText("Unead");
            holder.li_bg.setBackgroundColor(Color.parseColor("#D3EEF7"));
        }else{
            holder.tvIsread.setText("Read");
            holder.li_bg.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        holder.note_time.setText(Utils.getDateString(Utils.getStringToDate(data.get(position).getFields().getExpireDate()), "EEE, dd MMM"));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout li_bg;
        private TextView tvSub, tvDetails,tvIsread,note_user,note_time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSub = itemView.findViewById(R.id.note_title);
            tvDetails = itemView.findViewById(R.id.note_disc);
            tvIsread = itemView.findViewById(R.id.tv_isread);
            note_user = itemView.findViewById(R.id.note_user);
            note_time = itemView.findViewById(R.id.note_time);
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
