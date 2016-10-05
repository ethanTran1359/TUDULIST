package com.example.rubit1359.tudu.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by rubit1359 on 10/2/2016.
 */

@Table(name = "Tasks ")
public class Task extends Model implements Parcelable {
    @Column(name = "task_title")
    private String taskTitle;
    @Column(name = "note")
    private String notes;
    @Column(name = "date")
    private String dueDate;
    @Column(name = "priority")
    private String priority;
    @Column(name = "status")
    private String status;
    private long taskId;

    public long getTaskId() {
        return taskId;
    }


    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }




    public static List<Task> getAllTasks() {
        return new Select().from(Task.class).execute();
    }


    public Task() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.taskTitle);
        dest.writeString(this.notes);
        dest.writeString(this.dueDate);
        dest.writeString(this.priority);
        dest.writeString(this.status);
        dest.writeLong(this.taskId);
    }

    protected Task(Parcel in) {
        this.taskTitle = in.readString();
        this.notes = in.readString();
        this.dueDate = in.readString();
        this.priority = in.readString();
        this.status = in.readString();
        this.taskId = in.readLong();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}