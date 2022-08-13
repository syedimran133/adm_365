package com.graph.adm.model.todolist;

public class Progressdata {

    int total;
    double blocked;
    double Completed;
    double InProgress;
    double NotStarted;
    int dueDayToday;

    public Progressdata(int total, double blocked, double completed, double inProgress, double notStarted, int dueDayToday) {
        this.total = total;
        this.blocked = blocked;
        Completed = completed;
        InProgress = inProgress;
        NotStarted = notStarted;
        this.dueDayToday = dueDayToday;
    }

    public Progressdata(double blocked, double completed, double inProgress, double notStarted, int dueDayToday) {
        this.blocked = blocked;
        Completed = completed;
        InProgress = inProgress;
        NotStarted = notStarted;
        this.dueDayToday = dueDayToday;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getBlocked() {
        return blocked;
    }

    public void setBlocked(double blocked) {
        this.blocked = blocked;
    }

    public double getCompleted() {
        return Completed;
    }

    public void setCompleted(double completed) {
        Completed = completed;
    }

    public double getInProgress() {
        return InProgress;
    }

    public void setInProgress(double inProgress) {
        InProgress = inProgress;
    }

    public double getNotStarted() {
        return NotStarted;
    }

    public void setNotStarted(double notStarted) {
        NotStarted = notStarted;
    }

    public int getDueDayToday() {
        return dueDayToday;
    }

    public void setDueDayToday(int dueDayToday) {
        this.dueDayToday = dueDayToday;
    }
}
