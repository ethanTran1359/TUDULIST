package com.example.rubit1359.tudu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.example.rubit1359.tudu.R;
import com.example.rubit1359.tudu.model.Task;
import com.vstechlab.easyfonts.EasyFonts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    // Try to send multiple tasks without leaving the main Activity but FAILED ~
    private static final String TAG = MainActivity.class.getSimpleName();
    private String mPriority = "LOW IMPORTANT";
    private Task mTask;


    // Bind the widget
    @BindView(R.id.mainAct_UpdateTitleTv)
    TextView mUpdateTitleTv;
    @BindView(R.id.mainAct_UpdateTaskTv)
    TextView mUpdateTaskTv;
    @BindView(R.id.mainAct_ListTaskBtn)
    ImageView mListTaskBtn;
    @BindView(R.id.mainAct_AddTaskBtn)
    ImageView mAddTaskBtn;
    @BindView(R.id.mainAct_TuDuImg)
    ImageView mTuduImg;
    @BindView(R.id.mainAct_GoBtn)
    ImageView mGoBtn;
    @BindView(R.id.mainAct_QuickTaskBackgroundImg)
    ImageView mQuickTaskBackgroundImg;
    @BindView(R.id.mainAct_ImpotantSw)
    Switch mImportantSw;
    @BindView(R.id.mainAct_QuickTaskEdt)
    EditText mQuickTaskEdt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        ActiveAndroid.initialize(this);

        ButterKnife.bind(this);

        // Change the font to CaviarDream
        mUpdateTitleTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mUpdateTaskTv.setTypeface(EasyFonts.caviarDreams(this));
        mQuickTaskEdt.setTypeface(EasyFonts.caviarDreamsBold(this));
        handleViews();
        handleEvent();

        updateImportance();

    }

    private void updateImportance() {
        List<Model> tasks = new Select().from(Task.class).where("priority = ?", "IMPORTANT")
                .execute();

        for (int i = 0; i < tasks.size(); i++) {
            Random randomGenerator = new Random();
            Task task = (Task) tasks.get(randomGenerator.nextInt(tasks.size()));
            mUpdateTaskTv.setText(task.getTaskTitle());
        }
    }

    private void handleViews() {
        // Set the default state of the Switch
        mImportantSw.setChecked(false);
    }

    private void handleEvent() {
        //Switch event
        mImportantSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPriority = "IMPORTANT";
                } else
                    mPriority = "LOW IMPORTANT";

            }
        });
    }


    // Open the AddTask Activity
    // No info parsed

    public void openListTask(View view) {
        Intent intent = new Intent(this, TaskListActivity.class);
        startActivity(intent);

    }

    // Open the AddTask Activity
    // No info parsed

    public void openAddTask(View view) {
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    public void addQuickTask(View view) {

        // Task name required before click Go
        if (mQuickTaskEdt.getText().toString().equals("")) {
            Toast.makeText(this, "Please first add your mTask", Toast.LENGTH_SHORT).show();
            mGoBtn.animate().scaleX(1).setDuration(300).scaleY(1);
        } else {

            // Add the mTask to a text file
            // Currently only mTask name parsed
            // TODO: 10/2/2016 Add the Important and Date using natural language
            mTask = new Task();
            mTask.setTaskTitle(mQuickTaskEdt.getText().toString());
            mTask.setNotes("");
            mTask.setDueDate("");
            mTask.setPriority(mPriority);
            mTask.setStatus("");
            mTask.save();

        }
        mQuickTaskEdt.setText("");
        mImportantSw.setChecked(false);
        mPriority = "LOW IMPORTANT";
        updateImportance();

    }

}



