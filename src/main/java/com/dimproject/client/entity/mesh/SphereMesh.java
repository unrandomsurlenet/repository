package com.dimproject.client.entity.mesh;

import java.util.ArrayList;
import java.util.List;

public class SphereMesh {

    public record Vertex(float x, float y, float z, float u, float v) {}

    public static final List<Vertex[]> TRIANGLES = buildTriangles(30f, 32, 32);

    private static List<Vertex[]> buildTriangles(float radius, int stacks, int slices) {
        List<Vertex[]> triangles = new ArrayList<>();

        for (int i = 0; i < stacks; i++) {
            float phi0 = (float)(Math.PI * i / stacks);
            float phi1 = (float)(Math.PI * (i + 1) / stacks);

            for (int j = 0; j < slices; j++) {
                float theta0 = (float)(2 * Math.PI * j / slices);
                float theta1 = (float)(2 * Math.PI * (j + 1) / slices);

                Vertex v00 = vertex(radius, phi0, theta0);
                Vertex v01 = vertex(radius, phi0, theta1);
                Vertex v10 = vertex(radius, phi1, theta0);
                Vertex v11 = vertex(radius, phi1, theta1);

                triangles.add(new Vertex[]{v00, v10, v11});
                triangles.add(new Vertex[]{v00, v11, v01});
            }
        }
        return triangles;
    }

    private static Vertex vertex(float r, float phi, float theta) {
        float x = r * (float)(Math.sin(phi) * Math.cos(theta));
        float y = r * (float) Math.cos(phi);
        float z = r * (float)(Math.sin(phi) * Math.sin(theta));
        float u = (float)(theta / (2 * Math.PI));
        float v = (float)(phi / Math.PI);
        return new Vertex(x, y, z, u, v);
    }
}