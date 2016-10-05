package com.example.rubit1359.tudu.ui;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.example.rubit1359.tudu.R;
import com.example.rubit1359.tudu.model.Task;
import com.example.rubit1359.tudu.ui.dialog.DateDialogFragment;
import com.vstechlab.easyfonts.EasyFonts;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddTaskActivity extends AppCompatActivity {
    private Task mTask;
    private String mPriority = "LOW IMPORTANT";

    private static final String TAG = AddTaskActivity.class.getSimpleName();
    public static final String DETAILED_TASK = "DETAILED_TASK";

    public static int month;
    public static int date;
    public static int year;

    // Binding the Widget
    @BindView(R.id.addTaskAct_GoBtn)
    ImageView mGoBtn;
    @BindView(R.id.addTaskAct_TaskLabelTv)
    TextView mTaskLabelTv;
    @BindView(R.id.addTaskAct_TaskEdt)
    EditText mTaskEdt;
    @BindView(R.id.addTaskAct_NotesLabelTv)
    TextView mNotesLabelTv;
    @BindView(R.id.addTaskAct_NotesEdt)
    EditText mNotesEdt;
    @BindView(R.id.addTaskAct_DueDateLabelTv)
    TextView mDueDateLabelTv;
    @BindView(R.id.addTaskAct_MonthTv)
    TextView mMonthTv;
    @BindView(R.id.addTaskAct_DayTv)
    TextView mDayTv;
    @BindView(R.id.addTaskAct_YearTv)
    TextView mYearTv;
    @BindView(R.id.addTaskAct_ImportantLabelTv)
    TextView mImportantLabelTv;
    @BindView(R.id.addTaskAct_ImportantSw)
    Switch mImportantSw;
    @BindView(R.id.addTaskAct_StatusLabelTv)
    TextView mStatusLabelTv;
    @BindView(R.id.addTaskAct_StatusEdt)
    EditText mStatusEdt;
    @BindView(R.id.addTaskAct_draftDueDate)
    TextView mDraftDueDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_add_task);

        registerViews();
        handleViews();
        handleEvent();



    }

    private void handleViews() {

        // Set the default state of the Switch
        mImportantSw.setChecked(false);
        // Set the CurrentDate to DueDateBar
        addCurrentDate();
    }

   // Save every
    private void registerViews() {
        ActiveAndroid.initialize(this);

        ButterKnife.bind(this);
// Change the fonts to CaviaDream
        mTaskLabelTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mTaskEdt.setTypeface(EasyFonts.caviarDreamsBold(this));
        mNotesLabelTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mNotesEdt.setTypeface(EasyFonts.caviarDreamsBold(this));
        mDueDateLabelTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mMonthTv.setTypeface(EasyFonts.robotoBold(this));
        mDayTv.setTypeface(EasyFonts.robotoBold(this));
        mYearTv.setTypeface(EasyFonts.robotoBold(this));
        mImportantLabelTv.setTypeface(EasyFonts.caviarDreamsBold(this));
        mStatusLabelTv.setTypeface(EasyFonts.caviarDreamsBold(this));
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

    // Open the DatePicker dialog
    public void addDueDate(View view) {
        DialogFragment newFragment = new DateDialogFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }


    // Handle the DueDate Bar
    // Show the current day on the DatePickerDialog and the Due Date bar
    public void addCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        int currentMonthInt = calendar.get(Calendar.MONTH);
        int currentDateInt = calendar.get(Calendar.DAY_OF_MONTH);
        int currentYearInt = calendar.get(Calendar.YEAR);

        // Convert the date to better symbol
        String currentMonth = new DateFormatSymbols().getShortMonths()[currentMonthInt];
        String currentDate = String.format(Locale.getDefault(), "%02d", currentDateInt);
        String currentYear = String.valueOf(currentYearInt);

        // Set the current date on the date bar
        mMonthTv.setText(currentMonth);
        mDayTv.setText(currentDate);
        mYearTv.setText(currentYear);
    }

    // Get the DueDate input by user
    public void updateDueDate(String year, String month, String date) {
        mMonthTv.setText(month);
        mDayTv.setText(date);
        mYearTv.setText(year);

        // Save the data to an INVISIBLE TextView
        mDraftDueDate.setText(month + "/" + date + "/" + year);

    }


    // Get the priority String value
    public String getPriority() {

        Log.i(TAG, "getPriority: " + mPriority);
        return mPriority;
    }


    // Add the detailed task and save it to the database
    // The name of text file == the name of task
    public void addDetailedTask(View view) {

        // Task name is compulsory
        if (mTaskEdt.getText().toString().equals("")) {
            Toast.makeText(this, "Please first add your task", Toast.LENGTH_SHORT).show();
        } else {

            mTask = new Task();
            mTask.setTaskTitle(mTaskEdt.getText().toString());
            mTask.setNotes(mNotesEdt.getText().toString() + "");
            mTask.setDueDate(mDraftDueDate.getText().toString() + "");
            mTask.setPriority(mPriority);
            mTask.setStatus(mStatusEdt.getText().toString() + "");
            mTask.save();

            mTaskEdt.setText("");
            mNotesEdt.setText("");
            mStatusEdt.setText("");
            mImportantSw.setChecked(false);

            Intent intent = new Intent(this,TaskListActivity.class);
            startActivity(intent);



        }
    }

}
