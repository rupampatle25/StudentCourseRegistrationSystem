package model;

public class Schedule {
    private String days;
    private String time;

    public Schedule(String days, String time) {
        this.days = days;
        this.time = time;
    }

    public String getDays() { return days; }
    public String getTime() { return time; }

    @Override
    public String toString() {
        return days + " " + time;
    }
}