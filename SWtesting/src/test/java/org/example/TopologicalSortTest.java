package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {

    @Test
    public void testSingleNodeGraph() {
        TopologicalSort.Graph graph = new TopologicalSort.Graph(1);

        List<Integer> expected = List.of(0);
        assertEquals(expected, graph.topologicalSortKahn());
        assertEquals(expected, graph.topologicalSortDFS());
    }

    @Test
    public void testLinearGraph() {
        TopologicalSort.Graph graph = new TopologicalSort.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        List<Integer> expected = List.of(0, 1, 2, 3);
        assertEquals(expected, graph.topologicalSortKahn());
        assertEquals(expected, graph.topologicalSortDFS());
    }

    @Test
    public void testDisconnectedGraph() {
        TopologicalSort.Graph graph = new TopologicalSort.Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);

        List<Integer> kahnResult = graph.topologicalSortKahn();
        List<Integer> dfsResult = graph.topologicalSortDFS();

        assertEquals(6, kahnResult.size());
        assertEquals(6, dfsResult.size());
    }

    @Test
    public void testGraphWithCycle() {
        TopologicalSort.Graph graph = new TopologicalSort.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // Cycle

        assertThrows(IllegalArgumentException.class, graph::topologicalSortKahn);
    }

    @Test
    public void testGraphWithMultipleEdges() {
        TopologicalSort.Graph graph = new TopologicalSort.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        List<Integer> kahnResult = graph.topologicalSortKahn();
        List<Integer> dfsResult = graph.topologicalSortDFS();

        assertEquals(5, kahnResult.size());
        assertEquals(5, dfsResult.size());
    }

    @Test
    public void testLargeGraph() {
        int vertices = 100;
        TopologicalSort.Graph graph = new TopologicalSort.Graph(vertices);
        for (int i = 0; i < vertices - 1; i++) {
            graph.addEdge(i, i + 1);
        }

        List<Integer> expected = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            expected.add(i);
        }

        assertEquals(expected, graph.topologicalSortKahn());
        assertEquals(expected, graph.topologicalSortDFS());
    }

    @Test
    public void testReverseOrderGraph() {
        TopologicalSort.Graph graph = new TopologicalSort.Graph(4);
        graph.addEdge(3, 2);
        graph.addEdge(2, 1);
        graph.addEdge(1, 0);

        List<Integer> expected = List.of(3, 2, 1, 0);
        assertEquals(expected, graph.topologicalSortKahn());
        assertEquals(expected, graph.topologicalSortDFS());
    }

    @Test
    public void testGraphWithIsolatedNodes() {
        TopologicalSort.Graph graph = new TopologicalSort.Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);

        List<Integer> kahnResult = graph.topologicalSortKahn();
        List<Integer> dfsResult = graph.topologicalSortDFS();

        assertEquals(6, kahnResult.size());
        assertEquals(6, dfsResult.size());
    }
}
