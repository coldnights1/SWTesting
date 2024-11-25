package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class PrimsTest {

    @Test
    public void testSimpleGraph() {
        Prims.Graph graph = new Prims.Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);

        List<Prims.Edge> mst = graph.primsMST();

        assertEquals(3, mst.size());
        assertTrue(mst.contains(new Prims.Edge(0, 1, 1)));
        assertTrue(mst.contains(new Prims.Edge(1, 2, 2)));
        assertTrue(mst.contains(new Prims.Edge(2, 3, 3)));
    }

    @Test
    public void testGraphWithNegativeWeights() {
        Prims.Graph graph = new Prims.Graph(4);
        graph.addEdge(0, 1, -1);
        graph.addEdge(1, 2, -2);
        graph.addEdge(2, 3, -3);

        List<Prims.Edge> mst = graph.primsMST();

        assertEquals(3, mst.size());
        assertTrue(mst.contains(new Prims.Edge(0, 1, -1)));
        assertTrue(mst.contains(new Prims.Edge(1, 2, -2)));
        assertTrue(mst.contains(new Prims.Edge(2, 3, -3)));
    }

    @Test
    public void testGraphWithMultipleEdges() {
        Prims.Graph graph = new Prims.Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 1, 2); // Duplicate edge with higher weight
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);

        List<Prims.Edge> mst = graph.primsMST();

        assertEquals(3, mst.size());
        assertTrue(mst.contains(new Prims.Edge(0, 1, 1)));
        assertTrue(mst.contains(new Prims.Edge(1, 2, 2)));
        assertTrue(mst.contains(new Prims.Edge(2, 3, 3)));
    }

    @Test
    public void testDisconnectedGraph() {
        Prims.Graph graph = new Prims.Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(2, 3, 2);

        assertThrows(IllegalArgumentException.class, graph::primsMST);
    }

    @Test
    public void testGraphWithCycle() {
        Prims.Graph graph = new Prims.Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 0, 4); // Forms a cycle

        List<Prims.Edge> mst = graph.primsMST();

        assertEquals(3, mst.size());
        assertFalse(mst.contains(new Prims.Edge(3, 0, 4)));
    }

    @Test
    public void testLargeGraph() {
        int vertices = 100;
        Prims.Graph graph = new Prims.Graph(vertices);

        for (int i = 0; i < vertices - 1; i++) {
            graph.addEdge(i, i + 1, i + 1);
        }

        List<Prims.Edge> mst = graph.primsMST();

        assertEquals(vertices - 1, mst.size());
    }

    @Test
    public void testGraphWithIsolatedNodes() {
        Prims.Graph graph = new Prims.Graph(5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);

        assertThrows(IllegalArgumentException.class, graph::primsMST);
    }
}
