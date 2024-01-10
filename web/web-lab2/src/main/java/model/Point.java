package model;

public final class Point {
    private double x;
    private double y;
    private double r;
    private String time;
    private String scriptTime;
    private String flag;

    public Point(double x, double y, double r, String flag, String time, String scriptTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.flag = flag;
        this.time = time;
        this.scriptTime = scriptTime;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getR() {
        return r;
    }
    public void setR(double r) {
        this.r = r;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getScriptTime() {
        return scriptTime;
    }
    public void setScriptTime(String scriptTime) {
        this.scriptTime = scriptTime;
    }
}
