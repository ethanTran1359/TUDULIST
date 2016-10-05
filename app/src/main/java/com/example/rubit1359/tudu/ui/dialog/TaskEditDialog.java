package com.example.rubit1359.tudu.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rubit1359.tudu.R;
import com.example.rubit1359.tudu.ui.DetailedTaskActivity;
import com.example.rubit1359.tudu.ui.TaskEditDialogListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.id;

/**
 * Created by rubit1359 on 10/4/2016.
 */

public class TaskEditDialog extends DialogFragment {
    private TaskEditDialogListener mListerner;

    public void setListerner(TaskEditDialogListener listerner) {
        mListerner = listerner;
    }


    @BindView(R.id.dialog_TaskEdt)
    EditText mTaskEdt;
    @BindView(R.id.dialog_NotesEdt)
    EditText mNoteEdt;
    @BindView(R.id.dialog_MonthEdt)
            EditText mMonthEdt;
    @BindView(R.id.dialog_DayEdt)
            EditText mDayEdt;
    @BindView(R.id.dialog_YearEdt)
            EditText mYearEdt;
    @BindView(R.id.dialog_PriorityEdt)
            EditText mPriority;
    @BindView(R.id.dialog_StatusEdt)
            EditText mStatusEdt;

    LayoutInflater inflater;
    View v;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.task_edit_dialog, null);
        ButterKnife.bind(this, v);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                String taskTitle = mTaskEdt.getText().toString();
                String note = mNoteEdt.getText().toString();
                String month = mMonthEdt.getText().toString();
                String day = mDayEdt.getText().toString();
                String year = mYearEdt.getText().toString();
                String priority = mPriority.getText().toString();
                String status = mStatusEdt.getText().toString();

                ((DetailedTaskActivity)getActivity()).passTask(taskTitle,note,month,day,year,
                        priority,status);

            }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();

    }
}

