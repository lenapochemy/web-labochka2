public class Dot {

    private final Double x;
    private final Double y;
    private final Double r;
    private final boolean hit;

    public Dot(Double x, Double y, Double r){
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = isInArea();
    }

    public boolean isInArea(){
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

    public boolean isHit() {
        return hit;
    }
}
