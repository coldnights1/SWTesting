package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoopDetectionTest {

    @Test
    public void testDirectedGraphWithCycle() {
        LoopDetection.DirectedGraph graph = new LoopDetection.DirectedGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // Cycle here
        graph.addEdge(3, 2);

        assertTrue(graph.hasLoop());
    }

    @Test
    public void testDirectedGraphWithoutCycle() {
        LoopDetection.DirectedGraph graph = new LoopDetection.DirectedGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(3, 2);

        assertFalse(graph.hasLoop());
    }

    @Test
    public void testDirectedGraphWithSelfLoop() {
        LoopDetection.DirectedGraph graph = new LoopDetection.DirectedGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 2); // Self-loop

        assertTrue(graph.hasLoop());
    }

    @Test
    public void testUndirectedGraphWithCycle() {
        LoopDetection.UndirectedGraph graph = new LoopDetection.UndirectedGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // Cycle here

        assertTrue(graph.hasLoop());
    }

    @Test
    public void testUndirectedGraphWithoutCycle() {
        LoopDetection.UndirectedGraph graph = new LoopDetection.UndirectedGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        assertFalse(graph.hasLoop());
    }

    @Test
    public void testUndirectedGraphDisconnectedWithCycle() {
        LoopDetection.UndirectedGraph graph = new LoopDetection.UndirectedGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0); // Cycle in component 1
        graph.addEdge(3, 4);

        assertTrue(graph.hasLoop());
    }

    @Test
    public void testUndirectedGraphDisconnectedWithoutCycle() {
        LoopDetection.UndirectedGraph graph = new LoopDetection.UndirectedGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(3, 4);

        assertFalse(graph.hasLoop());
    }

    @Test
    public void testLargeGraphWithCycle() {
        int vertices = 100;
        LoopDetection.UndirectedGraph graph = new LoopDetection.UndirectedGraph(vertices);
        for (int i = 0; i < vertices - 1; i++) {
            graph.addEdge(i, i + 1);
        }
        graph.addEdge(vertices - 1, 0); // Add a cycle

        assertTrue(graph.hasLoop());
    }

    @Test
    public void testLargeGraphWithoutCycle() {
        int vertices = 100;
        LoopDetection.UndirectedGraph graph = new LoopDetection.UndirectedGraph(vertices);
        for (int i = 0; i < vertices - 1; i++) {
            graph.addEdge(i, i + 1);
        }

        assertFalse(graph.hasLoop());
    }
}
