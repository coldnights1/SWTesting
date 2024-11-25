package org.example;

import java.util.*;

public class Prims {

    // Edge class to represent a graph edge
    static class Edge {
        int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
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

    // Graph class for Prim's Algorithm
    static class Graph {
        private final int vertices;
        private final List<List<Edge>> adjacencyList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }

        public void addEdge(int source, int destination, int weight) {
            adjacencyList.get(source).add(new Edge(source, destination, weight));
            adjacencyList.get(destination).add(new Edge(destination, source, weight)); // Undirected graph
        }

        public List<Edge> primsMST() {
            boolean[] inMST = new boolean[vertices];
            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
            List<Edge> mst = new ArrayList<>();
            int totalEdges = 0;

            // Start with vertex 0
            inMST[0] = true;
            pq.addAll(adjacencyList.get(0));

            while (!pq.isEmpty() && totalEdges < vertices - 1) {
                Edge edge = pq.poll();

                if (inMST[edge.destination]) {
                    continue;
                }

                inMST[edge.destination] = true;
                mst.add(edge);
                totalEdges++;

                // Add all edges from the new vertex to the priority queue
                for (Edge nextEdge : adjacencyList.get(edge.destination)) {
                    if (!inMST[nextEdge.destination]) {
                        pq.offer(nextEdge);
                    }
                }
            }

            if (totalEdges != vertices - 1) {
                throw new IllegalArgumentException("Graph is disconnected, MST not possible.");
            }

            return mst;
        }
    }
}
