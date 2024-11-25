package org.example;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class BridgeFindingTest {

    private Set<Set<Integer>> convertToEdgeSets(List<int[]> edges) {
        Set<Set<Integer>> edgeSets = new HashSet<>();
        for (int[] edge : edges) {
            edgeSets.add(Set.of(edge[0], edge[1])); // Treat (u, v) and (v, u) as equivalent
        }
        return edgeSets;
    }

    @Test
    public void testSimpleGraph() {
        BridgeFinding.Graph graph = new BridgeFinding.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        Set<Set<Integer>> expected = Set.of(
                Set.of(2, 3),
                Set.of(1, 2),
                Set.of(0, 1)
        );

        List<int[]> result = graph.findBridges();
        Set<Set<Integer>> resultAsSets = convertToEdgeSets(result);

        assertEquals(expected, resultAsSets);
    }

    @Test
    public void testFullyConnectedGraph() {
        BridgeFinding.Graph graph = new BridgeFinding.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        List<int[]> result = graph.findBridges();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testDisconnectedGraph() {
        BridgeFinding.Graph graph = new BridgeFinding.Graph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);

        Set<Set<Integer>> expected = Set.of(
                Set.of(1, 2),
                Set.of(0, 1),
                Set.of(3, 4)
        );

        List<int[]> result = graph.findBridges();
        Set<Set<Integer>> resultAsSets = convertToEdgeSets(result);

        assertEquals(expected, resultAsSets);
    }

    @Test
    public void testStarGraph() {
        BridgeFinding.Graph graph = new BridgeFinding.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);

        Set<Set<Integer>> expected = Set.of(
                Set.of(0, 1),
                Set.of(0, 2),
                Set.of(0, 3),
                Set.of(0, 4)
        );

        List<int[]> result = graph.findBridges();
        Set<Set<Integer>> resultAsSets = convertToEdgeSets(result);

        assertEquals(expected, resultAsSets);
    }

    @Test
    public void testCyclicGraph() {
        BridgeFinding.Graph graph = new BridgeFinding.Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 0);

        List<int[]> result = graph.findBridges();

        assertTrue(result.isEmpty());
    }

    @Test
    public void testLinearGraph() {
        BridgeFinding.Graph graph = new BridgeFinding.Graph(5);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        Set<Set<Integer>> expected = Set.of(
                Set.of(3, 4),
                Set.of(2, 3),
                Set.of(1, 2),
                Set.of(0, 1)
        );

        List<int[]> result = graph.findBridges();
        Set<Set<Integer>> resultAsSets = convertToEdgeSets(result);

        assertEquals(expected, resultAsSets);
    }
}
