package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BFSUnitWeightTest {

    @Test
    public void testSimpleGraph() {
        BFSUnitWeight.Graph graph = new BFSUnitWeight.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        int[] distances = graph.shortestPath(0);
        int[] expected = {0, 1, 2, 3};

        assertArrayEquals(expected, distances, "Simple linear graph should calculate distances correctly.");
    }

    @Test
    public void testDisconnectedGraph() {
        BFSUnitWeight.Graph graph = new BFSUnitWeight.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        int[] distances = graph.shortestPath(0);
        int[] expected = {0, 1, 2, -1, -1};

        assertArrayEquals(expected, distances, "Disconnected nodes should have a distance of -1.");
    }

    @Test
    public void testSingleNodeGraph() {
        BFSUnitWeight.Graph graph = new BFSUnitWeight.Graph(1);

        int[] distances = graph.shortestPath(0);
        int[] expected = {0};

        assertArrayEquals(expected, distances, "A single-node graph should have a distance of 0 to itself.");
    }

    @Test
    public void testGraphWithCycle() {
        BFSUnitWeight.Graph graph = new BFSUnitWeight.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // Cycle
        graph.addEdge(2, 3);

        int[] distances = graph.shortestPath(0);
        int[] expected = {0, 1, 1, 2};

        assertArrayEquals(expected, distances, "Graph with cycles should still calculate shortest paths correctly.");
    }

    @Test
    public void testLargeGraph() {
        int vertices = 100;
        BFSUnitWeight.Graph graph = new BFSUnitWeight.Graph(vertices);

        for (int i = 0; i < vertices - 1; i++) {
            graph.addEdge(i, i + 1); // Linear chain
        }

        int[] distances = graph.shortestPath(0);
        for (int i = 0; i < vertices; i++) {
            assertEquals(i, distances[i], "Distances in a linear graph should increment correctly.");
        }
    }

    @Test
    public void testGraphWithIsolatedNodes() {
        BFSUnitWeight.Graph graph = new BFSUnitWeight.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        int[] distances = graph.shortestPath(3);
        int[] expected = {-1, -1, -1, 0, -1};

        assertArrayEquals(expected, distances, "Starting from an isolated node should produce correct distances.");
    }

    @Test
    public void testMultipleComponents() {
        BFSUnitWeight.Graph graph = new BFSUnitWeight.Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        int[] distancesFrom0 = graph.shortestPath(0);
        int[] distancesFrom3 = graph.shortestPath(3);

        assertArrayEquals(new int[]{0, 1, 2, -1, -1, -1}, distancesFrom0, "Distances from 0 in a multi-component graph should be correct.");
        assertArrayEquals(new int[]{-1, -1, -1, 0, 1, 2}, distancesFrom3, "Distances from 3 in a multi-component graph should be correct.");
    }
}
