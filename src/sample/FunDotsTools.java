package sample;

import javafx.animation.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

import static sample.Main.HEIGHT;
import static sample.Main.WIDTH;

public class FunDotsTools {

    public static final int ALPHA = 50;
    public static final int SIZE = 15;

    public static List<Transition> pulsation(FunDot[][] circles){
        List<Transition> transitions = new ArrayList<>();
        transitions.addAll(pulseColor(circles, Color.BLUE));
        transitions.addAll(pulseColor(circles, Color.RED));
        transitions.addAll(pulseColor(circles, Color.YELLOW));
        return transitions;
    }

    private static List<Transition> pulseColor(FunDot[][] circles, Color color){
        List<Transition> list = new ArrayList<>();
        list.add(scale(circles, color, true));
        list.add(stroke(circles, color, false));
        list.add(scale(circles, color, false));
        list.add(stroke(circles, color, true));

        return list;
    }

    private static ParallelTransition scale(FunDot[][] circles, Color color, boolean visible){
        ParallelTransition pt = new ParallelTransition();
        for(int i = 0; i < circles.length; i++) {
            for (int j = 0; j < circles[0].length; j++) {
                if (circles[i][j].getCnst().getFill() == color) {
                    ScaleTransition scale = new ScaleTransition();
                    scale.setDuration(Duration.millis(visible ? 500 : 1));
                    scale.setNode(circles[i][j].getBack());
                    scale.setToX(visible ? 6 : 1/6);
                    scale.setToY(visible ? 6 : 1/6);
                    pt.getChildren().add(scale);
                }
            }
        }
        return pt;
    }

    private static ParallelTransition stroke(FunDot[][] circles, Color color, boolean visible){
        ParallelTransition pt = new ParallelTransition();
        for(int i = 0; i < circles.length; i++) {
            for (int j = 0; j < circles[0].length; j++) {
                if (circles[i][j].getCnst().getFill() == color) {
                    StrokeTransition stroke = new StrokeTransition();
                    stroke.setDuration(Duration.millis(1));
                    stroke.setShape(circles[i][j].getBack());
                    stroke.setToValue(visible ? (Color) circles[i][j].getCnst().getStroke() : Color.TRANSPARENT);
                    pt.getChildren().add(stroke);
                }
            }
        }
        return pt;
    }

    public static FunDot[][] createCircleField(){
        FunDot[][] circles = new FunDot[(int) (HEIGHT/ALPHA*Math.sqrt(3)+1)][WIDTH/ALPHA+2];
        double x, y;
        y = 0;
        for(int i = 0; i < circles.length; i++){
            x = 0;
            if(i%2==1){
                x = -2*ALPHA;
                x += ALPHA/2;
            }
            for(int j = 0; j < circles[0].length; j++){
                Color color;
                if(j%3==0){
                    color = Color.BLUE;
                } else if(j%3==1){
                    color = Color.YELLOW;
                } else {
                    color = Color.RED;
                }

                FunDot circle = new FunDot(color, x, y);
                circles[i][j] = circle;

                x += ALPHA;
            }
            y += ALPHA * Math.sqrt(3) / 2;
        }
        return circles;
    }

}
