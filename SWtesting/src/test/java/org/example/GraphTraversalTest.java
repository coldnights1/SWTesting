package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTraversalTest {
    @Test
    public void testSimpleGraph() {
        GraphTraversal.Graph graph = new GraphTraversal.Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        GraphTraversal.Pair<List<Integer>, List<Integer>> result = graph.traverse(0);

        List<Integer> expectedDFS = Arrays.asList(0, 1, 2);
        List<Integer> expectedBFS = Arrays.asList(0, 1, 2);

        assertEquals(expectedDFS, result.getFirst());
        assertEquals(expectedBFS, result.getSecond());
    }
    @Test
    public void testDisconnectedGraph() {
        GraphTraversal.Graph graph = new GraphTraversal.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 3);

        GraphTraversal.Pair<List<Integer>, List<Integer>> result = graph.traverse(0);

        List<Integer> expectedDFS = Arrays.asList(0, 1);
        List<Integer> expectedBFS = Arrays.asList(0, 1);

        assertEquals(expectedDFS, result.getFirst());
        assertEquals(expectedBFS, result.getSecond());
    }
    @Test
    public void testCompleteGraph() {
        GraphTraversal.Graph graph = new GraphTraversal.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        GraphTraversal.Pair<List<Integer>, List<Integer>> result = graph.traverse(0);

        List<Integer> expectedDFS = Arrays.asList(0, 1, 2, 3);
        List<Integer> expectedBFS = Arrays.asList(0, 1, 2, 3);

        assertEquals(expectedDFS, result.getFirst());
        assertEquals(expectedBFS, result.getSecond());
    }
    @Test
    public void testSingleNodeGraph() {
        GraphTraversal.Graph graph = new GraphTraversal.Graph(1);

        GraphTraversal.Pair<List<Integer>, List<Integer>> result = graph.traverse(0);

        List<Integer> expectedDFS = Collections.singletonList(0);
        List<Integer> expectedBFS = Collections.singletonList(0);

        assertEquals(expectedDFS, result.getFirst());
        assertEquals(expectedBFS, result.getSecond());
    }
    @Test
    public void testLinearGraph() {
        GraphTraversal.Graph graph = new GraphTraversal.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        GraphTraversal.Pair<List<Integer>, List<Integer>> result = graph.traverse(0);

        List<Integer> expectedDFS = Arrays.asList(0, 1, 2, 3, 4);
        List<Integer> expectedBFS = Arrays.asList(0, 1, 2, 3, 4);

        assertEquals(expectedDFS, result.getFirst());
        assertEquals(expectedBFS, result.getSecond());
    }
    @Test
    public void testCyclicGraph() {
        GraphTraversal.Graph graph = new GraphTraversal.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);

        GraphTraversal.Pair<List<Integer>, List<Integer>> result = graph.traverse(0);

        List<Integer> expectedDFS = Arrays.asList(0, 1, 2, 3);
        List<Integer> expectedBFS = Arrays.asList(0, 1, 2, 3);

        assertEquals(expectedDFS, result.getFirst());
        assertEquals(expectedBFS, result.getSecond());
    }
    @Test
    public void testStarGraph() {
        GraphTraversal.Graph graph = new GraphTraversal.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);

        GraphTraversal.Pair<List<Integer>, List<Integer>> result = graph.traverse(0);

        List<Integer> expectedDFS = Arrays.asList(0, 1, 2, 3, 4);
        List<Integer> expectedBFS = Arrays.asList(0, 1, 2, 3, 4);

        assertEquals(expectedDFS, result.getFirst());
        assertEquals(expectedBFS, result.getSecond());
    }

}