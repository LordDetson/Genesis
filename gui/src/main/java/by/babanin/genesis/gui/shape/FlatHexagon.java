package by.babanin.genesis.gui.shape;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableFloatArray;
import javafx.geometry.Point3D;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class FlatHexagon extends MeshView {

    private final float radius;
    private final float minLeg;
    private final float bigLeg;
    private final TriangleMesh mesh = new TriangleMesh();
    private final List<Point3D> vertices = new ArrayList<>();

    public final Translate t = new Translate();
    public final Rotate rx = new Rotate(0, Rotate.X_AXIS);

    public FlatHexagon(float radius) {
        this.radius = radius;
        this.minLeg = (float) (radius / 2.0);
        this.bigLeg = (float) Math.ceil(Math.sqrt(Math.pow(radius, 2) - Math.pow(minLeg, 2)));

        initializeVertices();
        updatePoints();
        initializeTexCoords();
        initializeFaces();

        setMesh(mesh);

        getTransforms().addAll(t, rx);
    }

    private void initializeVertices() {
        vertices.add(new Point3D(0, 0, 0));         // 0
        vertices.add(new Point3D(0, -radius, 0));       // 1
        vertices.add(new Point3D(-bigLeg, -minLeg, 0));     // 2
        vertices.add(new Point3D(-bigLeg, minLeg, 0));      // 3
        vertices.add(new Point3D(0, radius, 0));        // 4
        vertices.add(new Point3D(bigLeg, minLeg, 0));       // 5
        vertices.add(new Point3D(bigLeg, -minLeg, 0));      // 6
    }

    private void initializeTexCoords() {
        //[0, 1, 2], [0, 2, 3], [0, 3, 4], [0, 4, 5], [0, 5, 6], [0, 6, 1]
        mesh.getTexCoords().addAll(
                0.5f, 0.5f,     //0
                0.5f, 0,     //1
                0, 0.25f,    //2
                0, 0.75f,   //3
                0.5f, 1,   //4
                1, 0.75f,   //5
                1, 0.25f     //6
        );
    }

    private void initializeFaces() {
        mesh.getFaces().addAll(
0, 0, 1, 1, 2, 2,
                0, 0, 2, 2, 3, 3,
                0, 0, 3, 3, 4, 4,
                0, 0, 4, 4, 5, 5,
                0, 0, 5, 5, 6, 6,
                0, 0, 6, 6, 1, 1
        );
    }

    public float getRadius() {
        return radius;
    }

    public float getMinLeg() {
        return minLeg;
    }

    public float getBigLeg() {
        return bigLeg;
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
