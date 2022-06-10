package by.babanin.genesis.gui.shape;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Group;
import javafx.scene.paint.Material;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;

public class HexagonPillar extends Group {

    private final double x;
    private final double z;
    private final FlatHexagon hexagonTop;
    private final HexagonPillarBase hexagonPillarBase;

    private final List<Shape3D> shapes = new ArrayList<>();

    public HexagonPillar(double x, double z, float radius, float height) {
        this.x = x;
        this.z = z;
        hexagonTop = new FlatHexagon(radius);
        hexagonTop.t.setY(-height);
        hexagonTop.rx.setAngle(-90);
        shapes.add(hexagonTop);

        hexagonPillarBase = new HexagonPillarBase(radius, height);
        shapes.add(hexagonPillarBase);

        getChildren().addAll(hexagonTop, hexagonPillarBase);
        setDrawMode(DrawMode.FILL);
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public FlatHexagon getHexagonTop() {
        return hexagonTop;
    }

    public HexagonPillarBase getHexagonPillarBase() {
        return hexagonPillarBase;
    }

    public void setDrawMode(DrawMode drawMode) {
        shapes.forEach(shape3D -> shape3D.setDrawMode(drawMode));
    }

    public void setMaterial(Material material) {
        shapes.forEach(shape3D -> shape3D.setMaterial(material));
    }
}
