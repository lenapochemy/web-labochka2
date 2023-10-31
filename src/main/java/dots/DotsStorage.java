package dots;

import dots.Dot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class DotsStorage implements Serializable {
    private final List<Dot> dots = new ArrayList<>();

    public void addDot(Dot newDot){
        dots.add(newDot);
    }

    public List<Dot> getDots() {
        List<Dot> list = dots;
        return list;
    }
}
