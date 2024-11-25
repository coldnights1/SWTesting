package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FloydWarshallTest {

    @Test
    public void testSingleNodeGraph() {
        FloydWarshall.Graph graph = new FloydWarshall.Graph(1);

        int[][] expected = {{0}};
        assertArrayEquals(expected, graph.floydWarshall());
    }

    @Test
    public void testGraphWithNoEdges() {
        FloydWarshall.Graph graph = new FloydWarshall.Graph(3);

        int[][] expected = {
                {0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        assertArrayEquals(expected, graph.floydWarshall());
    }

    @Test
    public void testGraphWithPositiveEdges() {
        FloydWarshall.Graph graph = new FloydWarshall.Graph(4);
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 1);
        graph.addEdge(0, 3, 10);

        int[][] expected = {
                {0, 5, 8, 9},
                {Integer.MAX_VALUE, 0, 3, 4},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        assertArrayEquals(expected, graph.floydWarshall());
    }

    @Test
    public void testGraphWithNegativeEdges() {
        FloydWarshall.Graph graph = new FloydWarshall.Graph(4);
        graph.addEdge(0, 1, 3);
        graph.addEdge(1, 2, -2);
        graph.addEdge(2, 3, 2);

        int[][] expected = {
                {0, 3, 1, 3},
                {Integer.MAX_VALUE, 0, -2, 0},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 2},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        assertArrayEquals(expected, graph.floydWarshall());
    }

    @Test
    public void testGraphWithNegativeCycle() {
        FloydWarshall.Graph graph = new FloydWarshall.Graph(3);
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, -1);
        graph.addEdge(2, 0, -1);

        assertThrows(IllegalArgumentException.class, graph::floydWarshall);
    }

    @Test
    public void testDisconnectedGraph() {
        FloydWarshall.Graph graph = new FloydWarshall.Graph(4);
        graph.addEdge(0, 1, 2);
        graph.addEdge(2, 3, 4);

        int[][] expected = {
                {0, 2, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 4},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        assertArrayEquals(expected, graph.floydWarshall());
    }


}
