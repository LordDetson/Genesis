package by.babanin.genesis.gui.shape;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableFloatArray;
import javafx.geometry.Point3D;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class HexagonPillarBase extends MeshView {

    private final float radius;
    private final float minLeg;
    private final float bigLeg;
    private final float height;
    private final TriangleMesh mesh = new TriangleMesh();
    private final List<Point3D> vertices = new ArrayList<>();

    public HexagonPillarBase(float radius, float height) {
        this.radius = radius;
        this.minLeg = (float) (radius / 2.0);
        this.bigLeg = (float) Math.ceil(Math.sqrt(Math.pow(radius, 2) - Math.pow(minLeg, 2)));
        this.height = height;

        initializeVertices();
        updatePoints();
        initializeTexCoords();
        initializeFaces();

        setMesh(mesh);
    }

    private void initializeVertices() {
        vertices.add(new Point3D(0, -height, radius));     // 0
        vertices.add(new Point3D(-bigLeg, -height, minLeg));     // 1
        vertices.add(new Point3D(-bigLeg, -height, -minLeg));    // 2
        vertices.add(new Point3D(0, -height, -radius));    // 3
        vertices.add(new Point3D(bigLeg, -height, -minLeg));    // 4
        vertices.add(new Point3D(bigLeg, -height, minLeg));    // 5
        vertices.add(new Point3D(0, 0, radius));     // 6
        vertices.add(new Point3D(-bigLeg, 0, minLeg));     // 7
        vertices.add(new Point3D(-bigLeg, 0, -minLeg));    // 8
        vertices.add(new Point3D(0, 0, -radius));    // 9
        vertices.add(new Point3D(bigLeg, 0, -minLeg));    // 10
        vertices.add(new Point3D(bigLeg, 0, minLeg));    // 11
    }

    private void initializeTexCoords() {
        int steps = 7;
        float offset = 1f / steps;
        ObservableFloatArray texCoords = mesh.getTexCoords();
        for(int v = 0; v <= 1; v++) {
            float u = 0;
            for(int j = 0; j < steps; j++) {
                texCoords.addAll(u, v);
                u += offset;
            }
        }
    }

    private void initializeFaces() {
        mesh.getFaces().addAll(
                1, 1, 0, 0, 6, 7,
                6, 7, 7, 8, 1, 1,
                2, 2, 1, 1, 7, 8,
                7, 8, 8, 9, 2, 2,
                3, 3, 2, 2, 8, 9,
                8, 9, 9, 10, 3, 3,
                4, 4, 3, 3, 9, 10,
                9, 10, 10, 11, 4, 4,
                5, 5, 4, 4, 10, 11,
                10, 11, 11, 12, 5, 5,
                0, 6, 5, 5, 11, 12,
                11, 12, 6, 13, 0, 6
        );
    }

    public List<Point3D> getVertices() {
        return vertices;
    }

    public void updatePoints() {
        ObservableFloatArray points = mesh.getPoints();
        points.clear();
        vertices.forEach(vertex -> points.addAll((float) vertex.getX(), (float) vertex.getY(), (float) vertex.getZ()));
    }
}
