package com.graph.adm.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graph.adm.R;
import com.graph.adm.Utils.Utils;
import com.graph.adm.model.buevents.BUEventValue;
import com.graph.adm.model.buevents.Fields;

import java.util.List;

public class BUEventsAdapter extends RecyclerView.Adapter<BUEventsAdapter.ViewHolder> {

    private Context context;
    List<BUEventValue> data;
    private IonItemSelect ionItemSelect;

    public BUEventsAdapter(Context context, List<BUEventValue> data) {
        this.context = context;
        this.data = data;
    }

    public void registerOnItemClickListener(IonItemSelect ionItemSelect) {
        this.ionItemSelect = ionItemSelect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bu_events_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fields fields=data.get(position).getFields();
        holder.tv_title.setText(fields.getTitle());
        holder.tvDetails.setText(fields.getEventDescription());

        String[] start = Utils.getDateString(Utils.getStringToDate(fields.getEventDate()), "EEE, dd MMM YY-hh:mm aa").split("-");
        String[] end = Utils.getDateString(Utils.getStringToDate(fields.getEndDate()), "EEE, dd MMM YY-hh:mm aa").split("-");
        if (start[0].trim().equals(end[0].trim())) {
            holder.tv_date_buevent.setText(" "+start[0]+" | "+start[1] + " - " + end[1]);
        } else {
            holder.tv_date_buevent.setText(" "+Utils.getDateString(Utils.getStringToDate(fields.getEventDate()), "EEE, dd MMM YY-hh:mm aa").replace("-", " ")+" | "+Utils.getDateString(Utils.getStringToDate(fields.getEndDate()), "EEE, dd MMM YY-hh:mm aa").replace("-", " "));
        }
        //holder.tv_date_buevent.setText(fields.getEventDate());
        holder.tv_buevent_place.setText(" "+fields.getLocation());
        holder.tv_place_details.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_title, tvDetails, tv_date_buevent, tv_buevent_place, tv_place_details;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tvDetails = itemView.findViewById(R.id.tv_description);
            tv_date_buevent = itemView.findViewById(R.id.tv_date_buevent);
            tv_buevent_place = itemView.findViewById(R.id.tv_buevent_place);
            tv_place_details = itemView.findViewById(R.id.tv_place_details);
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
