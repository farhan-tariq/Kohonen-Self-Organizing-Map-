package SimulationNetworkCore;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

public class BoundLine extends Line {
    private int num;
    private Node to,from;

    public BoundLine(double startX, double startY, double endX, double endY) {
        setStartX(startX);
        setStartY(startY);
        setEndX(endX);
        setEndY(endY);
        setStrokeWidth(2);
        setStroke(Color.WHEAT.deriveColor(0, 1, 1, 0.5));
        setStrokeLineCap(StrokeLineCap.SQUARE);
        getStrokeDashArray().setAll(10.0, 5.0);
    }

    public static double getLineStartX(Circle circle) {
        return circle.getRadius() * Math.cos(Math.toRadians(0)) + circle.getCenterX();
    }

    public static double getLineStartY(Circle circle) {
        return circle.getRadius() * Math.sin(Math.toRadians(0)) + circle.getCenterY();
    }


    public static double getLineEndX(Circle circle) {
        return circle.getRadius() * Math.cos(Math.toRadians(180)) + circle.getCenterX();
    }

    public static double getLineEndY(Circle circle) {
        return circle.getRadius() * Math.sin(Math.toRadians(180)) + circle.getCenterY();
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Node getFrom() {
        return from;
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getTo() {
        return to;
    }

    public void setTo(Node to) {
        this.to = to;
    }
}
