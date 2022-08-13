package com.graph.adm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.graph.adm.R;

public class DocumentsAdapter extends RecyclerView.Adapter<DocumentsAdapter.ViewHolder> {

    private Context context;
    //ArrayList<Value> data, dataFiltered;
    private IonItemSelect ionItemSelect;

    public DocumentsAdapter(Context context) {
        this.context = context;
        //this.data = data;
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


    }

    @Override
    public int getItemCount() {
        return 17;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

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
