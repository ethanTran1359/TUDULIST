package com.example.rubit1359.tudu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.example.rubit1359.tudu.R;
import com.example.rubit1359.tudu.adapters.TaskAdapter;
import com.example.rubit1359.tudu.model.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.id;
import static android.R.string.ok;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class TaskListActivity extends AppCompatActivity {
    private Task[] mTasks;
    private long mPosition;


    @BindView(android.R.id.list)
    ListView mTaskListView;
    @BindView(android.R.id.empty)
    TextView mEmptyTextView;
    // Modify the ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_button, menu);
        return true;
    }

    // Add the AddButton to the right side
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Use Add Button to move to AddTaskActivity
            case R.id.add_task_button:
                Intent intent = new Intent(this, AddTaskActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // Add the logo and Title to the Left Side ActionBar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Modify the ActionBar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("   LIST");
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        setContentView(R.layout.activity_task_list);

        ActiveAndroid.initialize(this);
        ButterKnife.bind(this);

        // Get Task from the database

        List<Task> storedTasks = Task.getAllTasks();

        final ArrayList<Task> mTasks = new ArrayList<>(storedTasks);
        final TaskAdapter adapter = new TaskAdapter(this, mTasks);
        mTaskListView.setAdapter(adapter);
        mTaskListView.setEmptyView(mEmptyTextView);
        mTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = new Task();
                task.setTaskTitle(mTasks.get(i).getTaskTitle());
                task.setNotes(mTasks.get(i).getNotes());
                task.setDueDate(mTasks.get(i).getDueDate());
                task.setPriority(mTasks.get(i).getPriority());
                task.setStatus(mTasks.get(i).getStatus());
                task.setTaskId(mTasks.get(i).getId());

                Intent intent = new Intent(TaskListActivity.this,DetailedTaskActivity.class);
                intent.putExtra("task",task);
                startActivity(intent);

            }
        });

//        mTaskListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//               Task task = mTasks.get(i);
//                long id = task.getId();
//                Task.delete(Task.class,task.getId());
//                adapter.deleteTask(task.getId());
//                return true;
//
//            }
//
//        });
//
//    }


//    public void deleteSelectedTask(View view) {
//
    }
}

