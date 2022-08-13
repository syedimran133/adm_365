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

public class BlogsWhitePapersAdapter extends RecyclerView.Adapter<BlogsWhitePapersAdapter.ViewHolder> {

    private Context context;
    //ArrayList<Value> data, dataFiltered;
    private IonItemSelect ionItemSelect;

    public BlogsWhitePapersAdapter(Context context) {
        this.context = context;
        //this.data = data;
    }

    public void registerOnItemClickListener(IonItemSelect ionItemSelect) {
        this.ionItemSelect = ionItemSelect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_blogs_white_papers_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

/*        if (position == 0) {

            holder.ivAnnouncement.setBackgroundResource(R.drawable.announcement_img);
           // holder.tvSub.setText("Everything you Need to Know About Logic App. That is where Logic App comes handy.");
           // holder.tvDetails.setText("Microsoft services always stays at the top because it innovates rapidly according to the changing times. Suppose there is a ticket that a customer had raised. Through Language Understanding Cognitive Service, the tone of the message could be understood. ");

        } else if (position == 1) {
            holder.ivAnnouncement.setBackgroundResource(R.drawable.announcement2);
           // holder.tvSub.setText("Everything you Need to Know About Logic App. That is where Logic App comes handy.");
           // holder.tvDetails.setText(" The issue could be tracked after creating an item on SharePoint. If the customer already exists in the database, you can add them to your salesforce CRM and sending an acknowledgment email to the customer. How do we connect all the apps? That is where Logic App comes handy.");

        } else if (position == 2) {
            holder.ivAnnouncement.setBackgroundResource(R.drawable.announcement3);
           // holder.tvSub.setText("Everything you Need to Know About Logic App. That is where Logic App comes handy.");
           // holder.tvDetails.setText("Microsoft services always stays at the top because it innovates rapidly according to the changing times. Suppose there is a ticket that a customer had raised. Through Language Understanding Cognitive Service, the tone of the message could be understood. ");

        }*/

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivAnnouncement;
        TextView tvSub, tvDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSub = itemView.findViewById(R.id.tv_subject);
            tvDetails = itemView.findViewById(R.id.tv_description);
            ivAnnouncement = itemView.findViewById(R.id.iv_announcement);
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
