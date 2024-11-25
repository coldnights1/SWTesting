package org.example;

import java.util.*;

public class BFSUnitWeight {

    static class Graph {
        private final int vertices;
        private final List<List<Integer>> adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination) {
            adjacencyList.get(source).add(destination);
            adjacencyList.get(destination).add(source); // Undirected graph
        }

        public int[] shortestPath(int start) {
            int[] distances = new int[vertices];
            Arrays.fill(distances, -1); // -1 represents unreachable nodes

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            distances[start] = 0;

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int neighbor : adjacencyList.get(node)) {
                    if (distances[neighbor] == -1) { // Not visited
                        distances[neighbor] = distances[node] + 1;
                        queue.offer(neighbor);
                    }
                }
            }

            return distances;
        }
    }
}
