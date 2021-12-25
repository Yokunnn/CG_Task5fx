package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


import static sample.FunDotsTools.SIZE;

public class FunDot {
    private Circle cnst;
    private Circle back;

    public FunDot(Color color, double x, double y){
        cnst = new Circle(SIZE/2);
        cnst.setFill(color);
        cnst.setStroke(color);
        cnst.translateXProperty().set(x);
        cnst.translateYProperty().set(y);

        back = new Circle(SIZE/2);
        back.setStroke(color);
        back.setFill(Color.TRANSPARENT);
        back.translateXProperty().set(x);
        back.translateYProperty().set(y);
    }

    public Circle getCnst() {
        return cnst;
    }
    public Circle getBack() {
        return back;
    }
}
