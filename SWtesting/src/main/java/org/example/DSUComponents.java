package org.example;

import java.util.*;

public class DSUComponents {

    static class DSU {
        private final int[] parent;
        private final int[] rank;
        private int components;

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            components = n;

            for (int i = 0; i < n; i++) {
                parent[i] = i; // Each node is its own parent initially
                rank[i] = 0;   // Initial rank is 0
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                components--; // Decrease the number of components
                return true;  // Successfully united
            }
            return false; // Already in the same component
        }

        public int getComponents() {
            return components;
        }
    }

    static class Graph {
        private final int vertices;
        private final List<int[]> edges;

        public Graph(int vertices) {
            this.vertices = vertices;
            edges = new ArrayList<>();
        }

        public void addEdge(int u, int v) {
            edges.add(new int[]{u, v});
        }

        public int findComponents() {
            DSU dsu = new DSU(vertices);

            for (int[] edge : edges) {
                dsu.union(edge[0], edge[1]);
            }

            return dsu.getComponents();
        }
    }
}
