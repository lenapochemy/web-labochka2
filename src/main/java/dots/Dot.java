package dots;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dot implements Serializable {

    private final Double x;
    private final Double y;
    private final Double r;
    private boolean result;
    private final String time;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public Dot(Double x, Double y, Double r){
        this.x = x;
        this.y = y;
        this.r = r;
        //this.hit = isInArea();
        Date d = new Date();
        this.time = formatter.format(d);
    }


    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getR() {
        return r;
    }

    public String getTime() {
        return time;
    }

    public boolean isHit() {
        return result;
    }
    public void setHit(boolean isInArea){
        this.result = isInArea;
    }



}
