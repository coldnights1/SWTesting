package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BellmanFordTest {

    @Test
    public void testBasicFunctionality() {
        BellmanFord.Edge[] edges = {
                new BellmanFord.Edge(0, 1, 4),
                new BellmanFord.Edge(0, 2, 2),
                new BellmanFord.Edge(1, 3, 5),
                new BellmanFord.Edge(2, 1, 1),
                new BellmanFord.Edge(2, 3, 8)
        };

        int vertices = 4;
        int[] expected = {0, 3, 2, 8};
        int[] result = BellmanFord.bellmanFord(vertices, edges, 0);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testNegativeWeightEdge() {
        BellmanFord.Edge[] edges = {
                new BellmanFord.Edge(0, 1, 1),
                new BellmanFord.Edge(1, 2, -2),
                new BellmanFord.Edge(2, 3, 2)
        };

        int vertices = 4;
        int[] expected = {0, 1, -1, 1};
        int[] result = BellmanFord.bellmanFord(vertices, edges, 0);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testNegativeWeightCycle() {
        BellmanFord.Edge[] edges = {
                new BellmanFord.Edge(0, 1, 1),
                new BellmanFord.Edge(1, 2, -2),
                new BellmanFord.Edge(2, 0, -1)
        };

        int vertices = 3;

        assertThrows(IllegalArgumentException.class, () -> BellmanFord.bellmanFord(vertices, edges, 0));
    }

    @Test
    public void testDisconnectedGraph() {
        BellmanFord.Edge[] edges = {
                new BellmanFord.Edge(0, 1, 4),
                new BellmanFord.Edge(1, 2, 5)
        };

        int vertices = 4;
        int[] expected = {0, 4, 9, Integer.MAX_VALUE};
        int[] result = BellmanFord.bellmanFord(vertices, edges, 0);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testGraphWithNoEdges() {
        BellmanFord.Edge[] edges = {}; // No edges in the graph

        int vertices = 4;
        int[] expected = {0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        int[] result = BellmanFord.bellmanFord(vertices, edges, 0);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testLargeGraph() {
        int vertices = 100;
        BellmanFord.Edge[] edges = new BellmanFord.Edge[vertices - 1];
        for (int i = 0; i < vertices - 1; i++) {
            edges[i] = new BellmanFord.Edge(i, i + 1, 1); // Linear graph
        }

        int[] expected = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            expected[i] = i;
        }

        int[] result = BellmanFord.bellmanFord(vertices, edges, 0);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMultipleShortestPaths() {
        BellmanFord.Edge[] edges = {
                new BellmanFord.Edge(0, 1, 2),
                new BellmanFord.Edge(0, 2, 2),
                new BellmanFord.Edge(1, 3, 1),
                new BellmanFord.Edge(2, 3, 1)
        };

        int vertices = 4;
        int[] expected = {0, 2, 2, 3};
        int[] result = BellmanFord.bellmanFord(vertices, edges, 0);

        assertArrayEquals(expected, result);
    }

    @Test
    public void testAlternatePaths() {
        BellmanFord.Edge[] edges = {
                new BellmanFord.Edge(0, 1, 1),
                new BellmanFord.Edge(0, 2, 10),
                new BellmanFord.Edge(1, 2, 2),
                new BellmanFord.Edge(2, 3, 1),
                new BellmanFord.Edge(1, 3, 5)
        };

        int vertices = 4;
        int[] expected = {0, 1, 3, 6}; // Shortest path goes thr

    }
}