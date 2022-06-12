package by.babanin.genesis.gui.shape;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Hexagon2D extends Polygon {

    private final double radius;
    private final double minLeg;
    private final double bigLeg;
    private final double x;
    private final double y;

    private final List<Point2D> vertices = new ArrayList<>();

    public Hexagon2D(double radius, double x, double y) {
        this.radius = radius;
        this.minLeg = radius / 2.0;
        this.bigLeg = Math.sqrt(Math.pow(radius, 2) - Math.pow(minLeg, 2));
        this.x = x;
        this.y = y;

        initializeVertices();
        updatePoints();
    }

    private void initializeVertices() {
        vertices.add(new Point2D(0, -radius));       // 0
        vertices.add(new Point2D(-bigLeg, -minLeg));     // 1
        vertices.add(new Point2D(-bigLeg, minLeg));      // 2
        vertices.add(new Point2D(0, radius));        // 3
        vertices.add(new Point2D(bigLeg, minLeg));       // 4
        vertices.add(new Point2D(bigLeg, -minLeg));      // 5
    }

    private void updatePoints() {
        ObservableList<Double> points = getPoints();
        points.clear();
        vertices.forEach(vertex -> points.addAll(vertex.getX(), vertex.getY()));
    }

    public double getRadius() {
        return radius;
    }

    public double getMinLeg() {
        return minLeg;
    }

    public double getBigLeg() {
        return bigLeg;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
