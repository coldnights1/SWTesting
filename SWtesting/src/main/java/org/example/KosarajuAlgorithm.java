package org.example;

import java.util.*;

public class KosarajuAlgorithm {

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

        public List<List<Integer>> findSCCs() {
            // Step 1: Fill the stack with nodes based on finish times
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[vertices];
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    fillOrder(i, visited, stack);
                }
            }

            // Step 2: Transpose the graph
            Graph transposedGraph = getTransposedGraph();

            // Step 3: Process all vertices in the order defined by the stack
            Arrays.fill(visited, false);
            List<List<Integer>> sccs = new ArrayList<>();
            while (!stack.isEmpty()) {
                int node = stack.pop();
                if (!visited[node]) {
                    List<Integer> scc = new ArrayList<>();
                    transposedGraph.dfs(node, visited, scc);
                    sccs.add(scc);
                }
            }

            return sccs;
        }

        private void fillOrder(int node, boolean[] visited, Stack<Integer> stack) {
            visited[node] = true;
            for (int neighbor : adjacencyList.get(node)) {
                if (!visited[neighbor]) {
                    fillOrder(neighbor, visited, stack);
                }
            }
            stack.push(node);
        }

        private Graph getTransposedGraph() {
            Graph transposed = new Graph(vertices);
            for (int i = 0; i < vertices; i++) {
                for (int neighbor : adjacencyList.get(i)) {
                    transposed.addEdge(neighbor, i);
                }
            }
            return transposed;
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
    }
}
