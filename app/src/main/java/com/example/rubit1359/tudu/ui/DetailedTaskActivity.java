package com.example.rubit1359.tudu.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.example.rubit1359.tudu.R;
import com.example.rubit1359.tudu.model.Task;
import com.example.rubit1359.tudu.ui.dialog.TaskEditDialog;
import com.vstechlab.easyfonts.EasyFonts;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailedTaskActivity extends AppCompatActivity implements TaskEditDialogListener {
    private long mTaskId;

    // Bind the widget
    @BindView(R.id.detailedTaskAct_TaskLabelTv)
    TextView mTaskLabelTv;
    @BindView(R.id.detailedTaskAct_TaskTv)
    TextView mTaskTv;
    @BindView(R.id.detailedTaskAct_DueDateLabelTv)
    TextView mDueDateLabelTv;
    @BindView(R.id.detailedTaskAct_DueDateTv)
    TextView mDueDateTv;
    @BindView(R.id.detailedTaskAct_NotesLabelTv)
    TextView mNotesLabelTv;
    @BindView(R.id.detailedTaskAct_NotesTv)
    TextView mNotesTv;
    @BindView(R.id.detailedTaskAct_PriorityLabelTv)
    TextView mPriorityLabelTv;
    @BindView(R.id.detailedTaskAct_PriorityTv)
    TextView mPriorityTv;
    @BindView(R.id.DetailedTaskAct_StatusLabelTv)
    TextView mStatusLabelTv;
    @BindView(R.id.detailedTaskAct_StatusTv)
    TextView mStatusTv;

    // Add delete button to Actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.delete_task_button:
                new Delete().from(Task.class).where("id=?", mTaskId).execute();
                Intent intent = new Intent(this, TaskListActivity.class);
                startActivity(intent);
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }

    }

    // Add the logo and the Title to the left side of ActionBar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("  TASK");
        getSupportActionBar().setLogo(R.drawable.ic_launcher);
        setContentView(R.layout.activity_detailed_task);

        ActiveAndroid.initialize(this);

        RegisterViews();

        // Get the task sent from click a task item
        Intent intent = getIntent();
        Task task = intent.getParcelableExtra("task");
        mTaskTv.setText(task.getTaskTitle() + "");
        mNotesTv.setText(task.getNotes() + "");
        mDueDateTv.setText(task.getDueDate() + "");
        mPriorityTv.setText(task.getPriority() + "");
        mStatusTv.setText(task.getStatus() + "");
        mTaskId = task.getTaskId();

    }

    private void RegisterViews() {
        ButterKnife.bind(this);


        // Set the Caviar Fonts for all Text
        mTaskLabelTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mTaskTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mDueDateLabelTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mDueDateTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mNotesLabelTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mNotesTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mPriorityLabelTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mPriorityTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mStatusLabelTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mStatusTv.setTypeface(EasyFonts.caviarDreamsBold(this));
    }

    public void editTask(View view) {
        TaskEditDialog dialog = new TaskEditDialog();
        dialog.show(getFragmentManager(), "task_edit_dialog");
    }


    @Override
    public void passTask(String taskTitle, String note, String month, String day, String year,
                         String priority, String status) {
        TaskEditDialog dialog = new TaskEditDialog();
        dialog.setListerner(this);
        mTaskTv.setText(taskTitle);
        mNotesTv.setText(note);
        mDueDateTv.setText(month+"/" + day + "/" + year);
        mPriorityTv.setText(priority);
        mStatusTv.setText(status);


    }


    public void saveTask(View view) {
        Task task = new Select().from(Task.class).where("id = ?", mTaskId)
                .executeSingle();
        task.setTaskTitle(mTaskTv.getText().toString());
        task.setNotes(mNotesTv.getText().toString());
        task.setDueDate(mDueDateTv.getText().toString());
        task.setPriority(mPriorityTv.getText().toString());
        task.setStatus(mStatusTv.getText().toString());
        task.save();
    }
}
