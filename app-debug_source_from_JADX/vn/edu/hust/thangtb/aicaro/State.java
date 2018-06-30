package vn.edu.hust.thangtb.aicaro;

import android.graphics.Point;

public class State {
    private Point f17p;
    private int val;

    public State(Point p, int val) {
        this.f17p = new Point(p);
        this.val = val;
    }

    public void Set(Point p, int val) {
        this.f17p = new Point(p);
        this.val = val;
    }

    public Point getP() {
        return this.f17p;
    }

    public void setP(Point p) {
        this.f17p = p;
    }

    public int getVal() {
        return this.val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
