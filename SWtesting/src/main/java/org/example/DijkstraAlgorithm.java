package org.example;

import java.util.*;

public class DijkstraAlgorithm {

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
            adjacencyList.get(source).add(new Edge(destination, weight));
            adjacencyList.get(destination).add(new Edge(source, weight)); // For undirected graph
        }

        public int[] dijkstra(int start) {
            int[] distances = new int[vertices];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[start] = 0;

            PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.weight));
            priorityQueue.add(new Edge(start, 0));

            boolean[] visited = new boolean[vertices];

            while (!priorityQueue.isEmpty()) {
                Edge current = priorityQueue.poll();
                int currentNode = current.destination;

                if (visited[currentNode]) continue;
                visited[currentNode] = true;

                for (Edge neighbor : adjacencyList.get(currentNode)) {
                    int newDistance = distances[currentNode] + neighbor.weight;
                    if (newDistance < distances[neighbor.destination]) {
                        distances[neighbor.destination] = newDistance;
                        priorityQueue.add(new Edge(neighbor.destination, newDistance));
                    }
                }
            }

            return distances;
        }

        static class Edge {
            int destination;
            int weight;

            public Edge(int destination, int weight) {
                this.destination = destination;
                this.weight = weight;
            }
        }
    }
}
