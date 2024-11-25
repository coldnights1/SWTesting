package org.example;

import java.util.*;

public class LoopDetection {

    // Directed Graph Implementation
    static class DirectedGraph {
        private final int vertices;
        private final List<List<Integer>> adjacencyList;

        public DirectedGraph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination) {
            adjacencyList.get(source).add(destination);
        }

        public boolean hasLoop() {
            boolean[] visited = new boolean[vertices];
            boolean[] recursionStack = new boolean[vertices];

            for (int i = 0; i < vertices; i++) {
                if (detectCycleDFS(i, visited, recursionStack)) {
                    return true;
                }
            }
            return false;
        }

        private boolean detectCycleDFS(int node, boolean[] visited, boolean[] recursionStack) {
            if (recursionStack[node]) {
                return true; // Node is part of a cycle
            }
            if (visited[node]) {
                return false; // Already visited and no cycle found earlier
            }

            visited[node] = true;
            recursionStack[node] = true;

            for (int neighbor : adjacencyList.get(node)) {
                if (detectCycleDFS(neighbor, visited, recursionStack)) {
                    return true;
                }
            }

            recursionStack[node] = false;
            return false;
        }
    }

    // Undirected Graph Implementation
    static class UndirectedGraph {
        private final int vertices;
        private final List<List<Integer>> adjacencyList;

        public UndirectedGraph(int vertices) {
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

        public boolean hasLoop() {
            boolean[] visited = new boolean[vertices];

            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    if (detectCycleDFS(i, -1, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean detectCycleDFS(int node, int parent, boolean[] visited) {
            visited[node] = true;

            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    if (detectCycleDFS(neighbor, node, visited)) {
                        return true;
                    }
                } else if (neighbor != parent) {
                    return true; // Back edge found
                }
            }

            return false;
        }
    }
}
