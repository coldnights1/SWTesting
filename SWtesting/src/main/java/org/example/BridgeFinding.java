package org.example;

import java.util.*;

public class BridgeFinding {

    static class Graph {
        private final int vertices;
        private final List<List<Integer>> adjacencyList;
        private int time; // Time counter for discovery and low values

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

        public List<int[]> findBridges() {
            List<int[]> bridges = new ArrayList<>();
            boolean[] visited = new boolean[vertices];
            int[] discovery = new int[vertices];
            int[] low = new int[vertices];
            int[] parent = new int[vertices];
            Arrays.fill(parent, -1); // Initialize parent as -1

            time = 0; // Initialize time counter

            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    dfs(i, visited, discovery, low, parent, bridges);
                }
            }
            return bridges;
        }

        private void dfs(int node, boolean[] visited, int[] discovery, int[] low, int[] parent, List<int[]> bridges) {
            visited[node] = true;
            discovery[node] = low[node] = ++time; // Set discovery and low values

            for (int neighbor : adjacencyList.get(node)) {
                // If neighbor is not visited, recurse
                if (!visited[neighbor]) {
                    parent[neighbor] = node;
                    dfs(neighbor, visited, discovery, low, parent, bridges);

                    // Update the low value of the current node
                    low[node] = Math.min(low[node], low[neighbor]);

                    // Check if the edge is a bridge
                    if (low[neighbor] > discovery[node]) {
                        bridges.add(new int[]{node, neighbor});
                    }
                } else if (neighbor != parent[node]) {
                    // Update low value for back edge
                    low[node] = Math.min(low[node], discovery[neighbor]);
                }
            }
        }
    }
}
