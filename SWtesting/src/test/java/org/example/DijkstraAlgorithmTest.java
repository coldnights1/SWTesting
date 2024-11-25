package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

    public class DijkstraAlgorithmTest {

        @Test
        public void testSimpleGraph() {
            DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(4);
            graph.addEdge(0, 1, 1);
            graph.addEdge(1, 2, 2);
            graph.addEdge(2, 3, 1);

            int[] expected = {0, 1, 3, 4};
            int[] result = graph.dijkstra(0);

            assertArrayEquals(expected, result);
        }

        @Test
        public void testDisconnectedGraph() {
            DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(5);
            graph.addEdge(0, 1, 2);
            graph.addEdge(1, 2, 3);
            // Nodes 3 and 4 are disconnected

            int[] expected = {0, 2, 5, Integer.MAX_VALUE, Integer.MAX_VALUE};
            int[] result = graph.dijkstra(0);

            assertArrayEquals(expected, result);
        }

        @Test
        public void testSingleNodeGraph() {
            DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(1);

            int[] expected = {0};
            int[] result = graph.dijkstra(0);

            assertArrayEquals(expected, result);
        }

        @Test
        public void testCyclicGraph() {
            DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(4);
            graph.addEdge(0, 1, 1);
            graph.addEdge(1, 2, 2);
            graph.addEdge(2, 0, 3);
            graph.addEdge(2, 3, 1);

            int[] expected = {0, 1, 3, 4};
            int[] result = graph.dijkstra(0);

            assertArrayEquals(expected, result);
        }

        @Test
        public void testCompleteGraph() {
            DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(4);
            graph.addEdge(0, 1, 1);
            graph.addEdge(0, 2, 2);
            graph.addEdge(0, 3, 3);
            graph.addEdge(1, 2, 1);
            graph.addEdge(1, 3, 1);
            graph.addEdge(2, 3, 1);

            int[] expected = {0, 1, 2, 2};
            int[] result = graph.dijkstra(0);

            assertArrayEquals(expected, result);
        }

        @Test
        public void testLargeGraph() {
            int vertices = 100;
            DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(vertices);
            for (int i = 0; i < vertices - 1; i++) {
                graph.addEdge(i, i + 1, 1);
            }

            int[] expected = new int[vertices];
            for (int i = 0; i < vertices; i++) {
                expected[i] = i;
            }

            int[] result = graph.dijkstra(0);

            assertArrayEquals(expected, result);
        }

        @Test
        public void testUnequalWeights() {
            DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(4);
            graph.addEdge(0, 1, 1);
            graph.addEdge(0, 2, 10);
            graph.addEdge(1, 2, 2);
            graph.addEdge(1, 3, 8);

            int[] expected = {0, 1, 3, 9};
            int[] result = graph.dijkstra(0);

            assertArrayEquals(expected, result);
        }

        @Test
        public void testStarGraph() {
            DijkstraAlgorithm.Graph graph = new DijkstraAlgorithm.Graph(5);
            graph.addEdge(0, 1, 1);
            graph.addEdge(0, 2, 2);
            graph.addEdge(0, 3, 3);
            graph.addEdge(0, 4, 4);

            int[] expected = {0, 1, 2, 3, 4};
            int[] result = graph.dijkstra(0);

            assertArrayEquals(expected, result);
        }
    }
