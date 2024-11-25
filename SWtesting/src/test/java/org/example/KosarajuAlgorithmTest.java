package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class KosarajuAlgorithmTest {

    @Test
    public void testSingleSCC() {
        KosarajuAlgorithm.Graph graph = new KosarajuAlgorithm.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);

        List<List<Integer>> expected = Arrays.asList(Arrays.asList(0, 3, 2, 1));
        List<List<Integer>> result = graph.findSCCs();

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testMultipleSCCs() {
        KosarajuAlgorithm.Graph graph = new KosarajuAlgorithm.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(2, 4, 3)
        );
        List<List<Integer>> result = graph.findSCCs();

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testDisconnectedGraph() {
        KosarajuAlgorithm.Graph graph = new KosarajuAlgorithm.Graph(5);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0),
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4)
        );
        List<List<Integer>> result = graph.findSCCs();

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testSingleNodeGraph() {
        KosarajuAlgorithm.Graph graph = new KosarajuAlgorithm.Graph(1);

        List<List<Integer>> expected = Arrays.asList(Arrays.asList(0));
        List<List<Integer>> result = graph.findSCCs();

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testGraphWithNoEdges() {
        KosarajuAlgorithm.Graph graph = new KosarajuAlgorithm.Graph(4);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0),
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3)
        );
        List<List<Integer>> result = graph.findSCCs();

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testStarGraph() {
        KosarajuAlgorithm.Graph graph = new KosarajuAlgorithm.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0),
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList(4)
        );
        List<List<Integer>> result = graph.findSCCs();

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }

    @Test
    public void testCyclicGraph() {
        KosarajuAlgorithm.Graph graph = new KosarajuAlgorithm.Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 3);

        List<List<Integer>> expected = Arrays.asList(
                Arrays.asList(0, 2, 1),
                Arrays.asList(3, 5, 4)
        );
        List<List<Integer>> result = graph.findSCCs();

        assertEquals(expected.size(), result.size());
        assertTrue(result.containsAll(expected));
    }
}
