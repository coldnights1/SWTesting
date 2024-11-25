package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BipartiteGraphTest {

    @Test
    public void testSimpleBipartiteGraph() {
        BipartiteGraph.Graph graph = new BipartiteGraph.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertTrue(graph.isBipartite(), "Simple linear graph should be bipartite.");
    }

    @Test
    public void testSimpleNonBipartiteGraph() {
        BipartiteGraph.Graph graph = new BipartiteGraph.Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        assertFalse(graph.isBipartite(), "Triangle graph should not be bipartite.");
    }

    @Test
    public void testDisconnectedBipartiteGraph() {
        BipartiteGraph.Graph graph = new BipartiteGraph.Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);

        assertTrue(graph.isBipartite(), "Disconnected components, all bipartite, should return true.");
    }

    @Test
    public void testDisconnectedNonBipartiteGraph() {
        BipartiteGraph.Graph graph = new BipartiteGraph.Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // Non-bipartite component
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        assertFalse(graph.isBipartite(), "Graph with a non-bipartite component should return false.");
    }

    @Test
    public void testSingleNodeGraph() {
        BipartiteGraph.Graph graph = new BipartiteGraph.Graph(1);

        assertTrue(graph.isBipartite(), "A single-node graph should be bipartite.");
    }

    @Test
    public void testGraphWithSelfLoop() {
        BipartiteGraph.Graph graph = new BipartiteGraph.Graph(3);
        graph.addEdge(0, 0); // Self-loop
        graph.addEdge(1, 2);

        assertFalse(graph.isBipartite(), "Graph with a self-loop should not be bipartite.");
    }

    @Test
    public void testLargeBipartiteGraph() {
        int vertices = 100;
        BipartiteGraph.Graph graph = new BipartiteGraph.Graph(vertices);
        for (int i = 0; i < vertices - 1; i += 2) {
            graph.addEdge(i, i + 1);
        }

        assertTrue(graph.isBipartite(), "Large bipartite graph should return true.");
    }

}
