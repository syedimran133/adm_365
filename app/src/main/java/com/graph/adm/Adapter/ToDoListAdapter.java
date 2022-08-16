package com.graph.adm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.graph.adm.Fragment.AddNewTaskBottomsheetFragment;
import com.graph.adm.Fragment.ModifyTaskBottomsheetFragment;
import com.graph.adm.Fragment.ToDoList;
import com.graph.adm.R;
import com.graph.adm.Utils.Utils;
import com.graph.adm.model.todolist.list.ToDoListData;
import com.graph.adm.model.todolist.list.ToDoListValue;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private Context context;
    private ToDoList fragment;
    private String id;
    List<ToDoListValue> data;
    private IonItemSelect ionItemSelect;

    public ToDoListAdapter(Context context,ToDoList fragment,String id,List<ToDoListValue> data) {
        this.context = context;
        this.data = data;
        this.fragment=fragment;
        this.id=id;
    }

    public void registerOnItemClickListener(IonItemSelect ionItemSelect) {
        this.ionItemSelect = ionItemSelect;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_list_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_todo_title.setText(data.get(position).getFields().getTitle());
        holder.tv_todo_description.setText(Utils.toTitleCase(data.get(position).getFields().getDescription()));
        holder.tv_todo_status.setText(" "+data.get(position).getFields().getProgress());
        holder.tv_todo_due_date.setText(Utils.getStringDateString(data.get(position).getFields().getDueDate(), "EEE, dd MMM YY"));
    
        holder.btnThreeDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(context, holder.btnThreeDot);
                popup.inflate(R.menu.menu_main);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.modify:
                                if(data.get(position).getFields().getProgress().equalsIgnoreCase("Completed")||data.get(position).getFields().getProgress().equalsIgnoreCase("Blocked")){
                                    Toast.makeText(context, "We can't change the status of completed or blocked events.", Toast.LENGTH_SHORT).show();
                                }else{
                                    ModifyTaskBottomsheetFragment modifyTaskBottomsheetFragment=new ModifyTaskBottomsheetFragment();
                                    modifyTaskBottomsheetFragment.setvalue(context,fragment,id,data.get(position));
                                    modifyTaskBottomsheetFragment.show(fragment.getParentFragmentManager(),modifyTaskBottomsheetFragment.getTag());
                                }

                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popup.show();
                           }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView btnThreeDot;
        TextView tv_todo_title,tv_todo_description,tv_todo_note,tv_todo_status,tv_todo_due_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_todo_title = itemView.findViewById(R.id.tv_todo_title);
            tv_todo_description = itemView.findViewById(R.id.tv_todo_description);
            tv_todo_note = itemView.findViewById(R.id.tv_todo_note);
            tv_todo_status = itemView.findViewById(R.id.tv_todo_status);
            tv_todo_due_date = itemView.findViewById(R.id.tv_todo_due_date);
            btnThreeDot = itemView.findViewById(R.id.btn_three_dot);
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
