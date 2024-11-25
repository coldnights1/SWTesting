package org.example;

import java.util.*;

public class FloydWarshall {

    static class Graph {
        private final int vertices;
        private final int[][] distanceMatrix;

        public Graph(int vertices) {
            this.vertices = vertices;
            distanceMatrix = new int[vertices][vertices];

            // Initialize distance matrix
            for (int i = 0; i < vertices; i++) {
                Arrays.fill(distanceMatrix[i], Integer.MAX_VALUE);
                distanceMatrix[i][i] = 0; // Distance to self is 0
            }
        }

        public void addEdge(int source, int destination, int weight) {
            distanceMatrix[source][destination] = weight;
        }

        public int[][] floydWarshall() {
            int[][] distances = new int[vertices][vertices];

            // Initialize distances with the distance matrix
            for (int i = 0; i < vertices; i++) {
                System.arraycopy(distanceMatrix[i], 0, distances[i], 0, vertices);
            }

            // Floyd-Warshall Algorithm
            for (int k = 0; k < vertices; k++) {
                for (int i = 0; i < vertices; i++) {
                    for (int j = 0; j < vertices; j++) {
                        if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
                            distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                        }
                    }
                }
            }

            // Check for negative weight cycles
            for (int i = 0; i < vertices; i++) {
                if (distances[i][i] < 0) {
                    throw new IllegalArgumentException("Graph contains a negative weight cycle");
                }
            }

            return distances;
        }

        public void printDistanceMatrix(int[][] distances) {
            for (int i = 0; i < distances.length; i++) {
                for (int j = 0; j < distances[i].length; j++) {
                    if (distances[i][j] == Integer.MAX_VALUE) {
                        System.out.print("INF ");
                    } else {
                        System.out.print(distances[i][j] + " ");
                    }
                }
                System.out.println();
            }
        }
    }
}
