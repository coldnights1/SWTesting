package org.example;

import java.util.*;

public class Kruskal {

    // Edge class to represent a graph edge
    static class Edge implements Comparable<Edge> {
        int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Edge edge = (Edge) obj;
            return source == edge.source &&
                    destination == edge.destination &&
                    weight == edge.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, destination, weight);
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "source=" + source +
                    ", destination=" + destination +
                    ", weight=" + weight +
                    '}';
        }
    }

    // Disjoint Set Union (DSU) class
    static class DSU {
        private final int[] parent;
        private final int[] rank;

        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
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
                return true;
            }
            return false; // Already in the same set
        }
    }

    // Graph class for Kruskal's Algorithm
    static class Graph {
        private final int vertices;
        private final List<Edge> edges;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.edges = new ArrayList<>();
        }

        public void addEdge(int source, int destination, int weight) {
            edges.add(new Edge(source, destination, weight));
        }

        public List<Edge> kruskalMST() {
            Collections.sort(edges); // Sort edges by weight
            DSU dsu = new DSU(vertices);

            List<Edge> mst = new ArrayList<>();
            for (Edge edge : edges) {
                if (dsu.union(edge.source, edge.destination)) {
                    mst.add(edge);
                }
            }

            if (mst.size() != vertices - 1) {
                throw new IllegalArgumentException("Graph is disconnected, MST not possible.");
            }

            return mst;
        }
    }
}
