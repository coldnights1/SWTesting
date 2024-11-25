package org.example;

import java.util.*;

public class BipartiteGraph {

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

        public void addEdge(int u, int v) {
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u); // Undirected graph
        }

        public boolean isBipartite() {
            int[] colors = new int[vertices];
            Arrays.fill(colors, -1); // -1 means uncolored

            for (int i = 0; i < vertices; i++) {
                if (colors[i] == -1) { // If not yet visited
                    if (!bfsCheck(i, colors)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean bfsCheck(int start, int[] colors) {
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            colors[start] = 0; // Assign the first color

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int neighbor : adjacencyList.get(node)) {
                    if (colors[neighbor] == -1) {
                        // Assign opposite color to the neighbor
                        colors[neighbor] = 1 - colors[node];
                        queue.offer(neighbor);
                    } else if (colors[neighbor] == colors[node]) {
                        // If the neighbor has the same color, the graph is not bipartite
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
