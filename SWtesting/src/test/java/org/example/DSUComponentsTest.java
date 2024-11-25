package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DSUComponentsTest {

    @Test
    public void testSingleNodeGraph() {
        DSUComponents.Graph graph = new DSUComponents.Graph(1);

        assertEquals(1, graph.findComponents(), "Single-node graph should have 1 component.");
    }

    @Test
    public void testNoEdgesGraph() {
        DSUComponents.Graph graph = new DSUComponents.Graph(5);

        assertEquals(5, graph.findComponents(), "Graph with no edges should have all nodes as separate components.");
    }

    @Test
    public void testFullyConnectedGraph() {
        DSUComponents.Graph graph = new DSUComponents.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        assertEquals(1, graph.findComponents(), "Fully connected graph should have 1 component.");
    }

    @Test
    public void testDisconnectedGraph() {
        DSUComponents.Graph graph = new DSUComponents.Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);

        assertEquals(3, graph.findComponents(), "Graph with disconnected components should return correct count.");
    }

    @Test
    public void testCycleGraph() {
        DSUComponents.Graph graph = new DSUComponents.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0); // Forms a cycle

        assertEquals(1, graph.findComponents(), "Graph with a cycle should have 1 component.");
    }

    @Test
    public void testDuplicateEdges() {
        DSUComponents.Graph graph = new DSUComponents.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 1); // Duplicate edge
        graph.addEdge(1, 2);

        assertEquals(2, graph.findComponents(), "Duplicate edges should not affect component count.");
    }

    @Test
    public void testIsolatedNodes() {
        DSUComponents.Graph graph = new DSUComponents.Graph(6);
        graph.addEdge(0, 1);

        assertEquals(5, graph.findComponents(), "Graph with isolated nodes should return correct component count.");
    }

    @Test
    public void testLargeGraph() {
        int vertices = 100;
        DSUComponents.Graph graph = new DSUComponents.Graph(vertices);

        for (int i = 0; i < vertices - 1; i++) {
            graph.addEdge(i, i + 1); // Connect all vertices sequentially
        }

        assertEquals(1, graph.findComponents(), "Large sequentially connected graph should have 1 component.");
    }

    @Test
    public void testDisconnectedLargeGraph() {
        int vertices = 100;
        DSUComponents.Graph graph = new DSUComponents.Graph(vertices);

        for (int i = 0; i < vertices; i += 2) {
            if (i + 1 < vertices) {
                graph.addEdge(i, i + 1); // Pairwise connections
            }
        }

        assertEquals(vertices / 2, graph.findComponents(), "Disconnected large graph should return correct component count.");
    }

    @Test
    public void testMultipleEdgesBetweenSameNodes() {
        DSUComponents.Graph graph = new DSUComponents.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 2);

        assertEquals(2, graph.findComponents(), "Multiple edges between the same nodes should not affect result.");
    }
}
