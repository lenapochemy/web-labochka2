package dots;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Dot {

    private final Double x;
    private final Double y;
    private final Double r;
    private final boolean hit;
    private final String time;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public Dot(Double x, Double y, Double r){
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = isInArea();
        Date d = new Date();
        this.time = formatter.format(d);
    }

    private boolean isInArea(){
        return (-r <= x && x <= 0 && 0 <= y && y <= r) ||
                (y >= -(x / 2) - (r / 2) && y <= 0 && x <= 0) ||
                (x >= 0 && y <= 0 && (x * x + y * y <= r * r / 4));
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
        return hit;
    }
}
