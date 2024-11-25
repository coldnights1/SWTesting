package org.example;

import java.util.*;

public class TopologicalSort {

    // Directed Graph Class
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
        }

        // Kahn's Algorithm for Topological Sort
        public List<Integer> topologicalSortKahn() {
            int[] inDegree = new int[vertices];
            for (int i = 0; i < vertices; i++) {
                for (int neighbor : adjacencyList.get(i)) {
                    inDegree[neighbor]++;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < vertices; i++) {
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }

            List<Integer> topologicalOrder = new ArrayList<>();
            while (!queue.isEmpty()) {
                int node = queue.poll();
                topologicalOrder.add(node);

                for (int neighbor : adjacencyList.get(node)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) {
                        queue.offer(neighbor);
                    }
                }
            }

            if (topologicalOrder.size() != vertices) {
                throw new IllegalArgumentException("Graph has a cycle, topological sort not possible.");
            }

            return topologicalOrder;
        }

        // DFS-based Topological Sort
        public List<Integer> topologicalSortDFS() {
            boolean[] visited = new boolean[vertices];
            Stack<Integer> stack = new Stack<>();

            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    dfs(i, visited, stack);
                }
            }

            List<Integer> topologicalOrder = new ArrayList<>();
            while (!stack.isEmpty()) {
                topologicalOrder.add(stack.pop());
            }

            return topologicalOrder;
        }

        private void dfs(int node, boolean[] visited, Stack<Integer> stack) {
            visited[node] = true;

            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    dfs(neighbor, visited, stack);
                }
            }

            stack.push(node);
        }
    }
}
