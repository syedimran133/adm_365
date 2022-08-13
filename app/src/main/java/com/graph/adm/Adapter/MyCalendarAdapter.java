package com.graph.adm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.graph.adm.R;
import com.graph.adm.Utils.AppSingle;
import com.graph.adm.Utils.Utils;
import com.graph.adm.model.calendar.MyCalValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCalendarAdapter extends RecyclerView.Adapter<MyCalendarAdapter.ViewHolder> {

    private Context context;
    List<MyCalValue> data;
    private IonItemSelect ionItemSelect;

    public MyCalendarAdapter(Context context, List<MyCalValue> data) {
        this.context = context;
        this.data = data;
    }

    public void registerOnItemClickListener(IonItemSelect ionItemSelect) {
        this.ionItemSelect = ionItemSelect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_event_name.setText(data.get(position).getSubject());
        String[] start = Utils.getDateString(Utils.getStringToDate(data.get(position).getStart().getDateTime().split("\\.")[0] + "Z"), "EEE, dd MMM YY-hh:mm aa").split("-");
        String[] end = Utils.getDateString(Utils.getStringToDate(data.get(position).getEnd().getDateTime().split("\\.")[0] + "Z"), "EEE, dd MMM YY-hh:mm aa").split("-");
        if (start[0].trim().equals(end[0].trim())) {
            holder.tv_date.setText(start[0]);
            holder.tv_time.setText(start[1] + " - " + end[1]);
        } else {
            holder.tv_date.setText(Utils.getDateString(Utils.getStringToDate(data.get(position).getStart().getDateTime().split("\\.")[0] + "Z"), "EEE, dd MMM YY-hh:mm aa").replace("-", " "));
            holder.tv_time.setText(Utils.getDateString(Utils.getStringToDate(data.get(position).getEnd().getDateTime().split("\\.")[0] + "Z"), "EEE, dd MMM YY-hh:mm aa").replace("-", " "));
        }
        try {
            if (data.get(position).getIsOnlineMeeting()) {
                holder.btn_join.setVisibility(View.VISIBLE);
                holder.btn_join.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Uri uri = Uri.parse(data.get(position).getOnlineMeeting().getJoinUrl()); // missing 'http://' will cause crashed.
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        context.startActivity(intent);
                    }
                });
            } else {
                holder.btn_join.setVisibility(View.INVISIBLE);
            }
            holder.tv_event_name.setOnClickListener(v -> {
                Uri uri = Uri.parse(data.get(position).getWebLink()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            });

            holder.tv_user_name.setText(data.get(position).getOrganizer().getEmailAddress().getName());
            holder.tv_created_time.setText("Created " + Utils.printDifference(Utils.getStringToDate(data.get(position).getCreatedDateTime().split("\\.")[0] + "Z")) + " ago");
            getPhotoCallBack(holder.iv_profile, data.get(position).getOrganizer().getEmailAddress().getAddress());
        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_profile;
        TextView btn_join, tv_created_time, tv_user_name, tv_event_name, tv_time, tv_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn_join = itemView.findViewById(R.id.btn_join);
            tv_created_time = itemView.findViewById(R.id.tv_created_time);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            tv_event_name = itemView.findViewById(R.id.tv_event_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_date = itemView.findViewById(R.id.tv_date);
            iv_profile = itemView.findViewById(R.id.iv_profile);

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
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://graph.microsoft.com/v1.0/users/" + id + "/photo/$value";
        ImageRequest request = new ImageRequest(url,
                bitmap -> {
                    ivProfile.setImageBitmap(bitmap);
                }, 0, 0, null,
                error -> ivProfile.setImageDrawable(context.getDrawable(R.drawable.joss))) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + AppSingle.getInstance().getmAccessToken());
                return headers;
            }
        };
        queue.add(request);
    }
}
