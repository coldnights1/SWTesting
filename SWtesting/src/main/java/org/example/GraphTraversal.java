package org.example;

import java.util.*;

public class GraphTraversal {

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
            adjacencyList.get(destination).add(source); // For undirected graph
        }

        public Pair<List<Integer>, List<Integer>> traverse(int start) {
            List<Integer> dfsResult = new ArrayList<>();
            List<Integer> bfsResult = new ArrayList<>();
            boolean[] visited = new boolean[vertices];

            dfs(start, visited, dfsResult);
            bfs(start, bfsResult);

            return new Pair<>(dfsResult, bfsResult);
        }

        private void dfs(int node, boolean[] visited, List<Integer> result) {
            visited[node] = true;
            result.add(node);

            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    dfs(neighbor, visited, result);
                }
            }
        }

        private void bfs(int start, List<Integer> result) {
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited[start] = true;

            while (!queue.isEmpty()) {
                int current = queue.poll();
                result.add(current);

                for (int neighbor : adjacencyList.get(current)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
        }
    }

    // Helper class for returning pairs
    static class Pair<U, V> {
        private final U first;
        private final V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        public U getFirst() {
            return first;
        }

        public V getSecond() {
            return second;
        }
    }
}
