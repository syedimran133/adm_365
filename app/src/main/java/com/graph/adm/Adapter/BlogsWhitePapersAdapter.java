package com.graph.adm.Adapter;

import android.annotation.SuppressLint;
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
import com.graph.adm.model.blogs.Value;

import java.util.List;

public class BlogsWhitePapersAdapter extends RecyclerView.Adapter<BlogsWhitePapersAdapter.ViewHolder> {

    private Context context;
    List<Value> data;
    private IonItemSelect ionItemSelect;

    public BlogsWhitePapersAdapter(Context context,List<Value> data) {
        this.context = context;
        this.data = data;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTitle.setText(data.get(position).getFields().getTitle());
        holder.tvDecs.setText(data.get(position).getFields().getBlogsDescription());
        holder.tvUser.setText(data.get(position).getFields().getPublishedBy());
        //holder.tvTime.setText(Utils.printDifference(Utils.getStringToDate(data.get(position).getFields().getPublishedOn()))+" ago");
        holder.tvTime.setText(Utils.getStringDateString(data.get(position).getFields().getPublishedOn(), "EEE, dd MMM YY"));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvDecs,tvUser,tvTime,btn_download;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDecs = itemView.findViewById(R.id.tv_decs);
            tvUser = itemView.findViewById(R.id.tv_user_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            btn_download = itemView.findViewById(R.id.btn_download);
            btn_download.setOnClickListener(this);
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
