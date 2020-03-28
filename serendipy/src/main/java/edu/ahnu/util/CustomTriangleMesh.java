package edu.ahnu.util;

import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

/**
 * 自定义四棱锥
 */
public class CustomTriangleMesh {
    public static MeshView generatePyramid(double side, double height) {

        float[] points = new float[]{
                0, 0, 0,                                        // Point 0 - Top
                0, (float) height, (float) (-side / 2),         // Point 1 - Front
                (float) (-side / 2), (float) height, 0,            // Point 2 - Left
                (float) (side / 2), (float) height, 0,            // Point 3 - Back
                0, (float) height, (float) (side / 2)           // Point 4 - Right
        };

        float[] texCoords = new float[]{
                0, 0,

        };

        int[] faces = new int[]{
                0, 0, 2, 0, 1, 0,          // Front left face
                0, 0, 1, 0, 3, 0,          // Front right face
                0, 0, 3, 0, 4, 0,          // Back right face
                0, 0, 4, 0, 2, 0,          // Back left face
                4, 0, 1, 0, 2, 0,          // Bottom rear face
                4, 0, 3, 0, 1, 0           // Bottom front face
        };


        TriangleMesh tm = new TriangleMesh();
        tm.getPoints().addAll(points);
        tm.getTexCoords().addAll(texCoords);
        tm.getFaces().addAll(faces);


        MeshView mv = new MeshView(tm);

        return mv;
    }

}
