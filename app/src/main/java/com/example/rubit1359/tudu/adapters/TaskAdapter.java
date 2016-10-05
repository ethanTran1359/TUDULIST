package com.example.rubit1359.tudu.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rubit1359.tudu.R;
import com.example.rubit1359.tudu.model.Task;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.list;
import static android.support.v7.widget.AppCompatDrawableManager.get;

/**
 * Created by rubit1359 on 10/2/2016.
 */

public class TaskAdapter extends BaseAdapter {
    private Context mContext;
    // Add mVar of resource, an array for example
    private ArrayList<Task> mTasks;


    // Automatically create constructor for all mVar


    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        mContext = context;
        mTasks = tasks;
    }

    // Automatically create required methods and override them with mVar
    @Override
    public int getCount() {
        return mTasks.size();
    }

    @Override
    public Object getItem(int position) {
        return mTasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0; // Use to easier get position reference
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            // If the ListView is brand new

            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_task_items, null);
            // Get layout from the context and inflate it the daily_list_item.

            // Use ViewHolder to create smooth scrolling list

            holder = new ViewHolder(convertView);

            // Set the tag for reuse the View
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //Set content to holderVar from get
        //Create the ModelClass object as an element in the array
        // Remember even the Image need to be set (different from MainAct)
        final Task task = mTasks.get(position);
        if (task.getPriority().equals("IMPORTANT")) {
            holder.ImportantImg.setImageResource(R.drawable.list_act_important_mark);
        } else if (task.getPriority().equals("LOW IMPORTANT"))
            holder.ImportantImg.setImageResource(R.drawable.list_act_not_important_mark);
        holder.TasksTv.setText(task.getTaskTitle());

//        holder.ChangePriorityBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (task.getPriority().equals("IMPORTANT")) {
//                    task.setPriority("LOW IMPORTANT");
//                    holder.ImportantImg.setImageResource(R.drawable.list_act_not_important_mark);
//                } else {
//                    task.getPriority().equals("LOW IMPORTANT");
//                    holder.ImportantImg.setImageResource(R.drawable.list_act_important_mark);
//
//                }
//            }
//        });


        return convertView;
    }

    // Create class ViewHolder with Widget as variable based on Model class
    static class ViewHolder {
        // Using ButterKnife to create and hook Widget (remember the Image, too)
        @BindView(R.id.listTaskAct_ImportantImg)
        ImageView ImportantImg;
        @BindView(R.id.listTaskAct_TaskTv)
        TextView TasksTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void deleteTask(long id) {
        //todo code to delete task from mTasks
        for (Task task : mTasks) {
            if (task.getId() == id) {
                mTasks.remove(task);
                notifyDataSetChanged();
            }
        }


    }

    public void notifyChange(long id) {
        //todo code to delete task from mTasks
        for (Task task : mTasks) {
            if (task.getId() == id) {
                notifyDataSetChanged();
            }
        }


    }
}
