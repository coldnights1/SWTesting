package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class KruskalTest {

    @Test
    public void testSimpleGraph() {
        Kruskal.Graph graph = new Kruskal.Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);

        List<Kruskal.Edge> mst = graph.kruskalMST();

        assertEquals(3, mst.size());
        assertTrue(mst.contains(new Kruskal.Edge(0, 1, 1)));
        assertTrue(mst.contains(new Kruskal.Edge(1, 2, 2)));
        assertTrue(mst.contains(new Kruskal.Edge(2, 3, 3)));
    }

    @Test
    public void testDisconnectedGraph() {
        Kruskal.Graph graph = new Kruskal.Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(2, 3, 2);

        assertThrows(IllegalArgumentException.class, graph::kruskalMST);
    }

    @Test
    public void testGraphWithMultipleEdges() {
        Kruskal.Graph graph = new Kruskal.Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 1, 2); // Duplicate edge with higher weight
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);

        List<Kruskal.Edge> mst = graph.kruskalMST();

        assertEquals(3, mst.size());
        assertTrue(mst.contains(new Kruskal.Edge(0, 1, 1)));
        assertTrue(mst.contains(new Kruskal.Edge(1, 2, 2)));
        assertTrue(mst.contains(new Kruskal.Edge(2, 3, 3)));
    }

    @Test
    public void testGraphWithCycle() {
        Kruskal.Graph graph = new Kruskal.Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(3, 0, 4); // Forms a cycle

        List<Kruskal.Edge> mst = graph.kruskalMST();

        assertEquals(3, mst.size());
        assertFalse(mst.contains(new Kruskal.Edge(3, 0, 4)));
    }

    @Test
    public void testLargeGraph() {
        int vertices = 100;
        Kruskal.Graph graph = new Kruskal.Graph(vertices);

        for (int i = 0; i < vertices - 1; i++) {
            graph.addEdge(i, i + 1, i + 1);
        }

        List<Kruskal.Edge> mst = graph.kruskalMST();

        assertEquals(vertices - 1, mst.size());
    }

    @Test
    public void testGraphWithNegativeWeights() {
        Kruskal.Graph graph = new Kruskal.Graph(4);
        graph.addEdge(0, 1, -1);
        graph.addEdge(1, 2, -2);
        graph.addEdge(2, 3, -3);

        List<Kruskal.Edge> mst = graph.kruskalMST();

        assertEquals(3, mst.size());
        assertTrue(mst.contains(new Kruskal.Edge(0, 1, -1)));
        assertTrue(mst.contains(new Kruskal.Edge(1, 2, -2)));
        assertTrue(mst.contains(new Kruskal.Edge(2, 3, -3)));
    }

    @Test
    public void testGraphWithIsolatedNodes() {
        Kruskal.Graph graph = new Kruskal.Graph(5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);

        assertThrows(IllegalArgumentException.class, graph::kruskalMST);
    }
}
