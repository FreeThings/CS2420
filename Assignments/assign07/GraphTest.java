package assign07;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    @Test
    void testSingleDirectedGraph() {
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B");
        assertEquals(2, graph.getVerticesSize());
        HashMap<String, Vertex<String>> vertices = graph.getVertices();
        Iterator<Edge<String>> itr = vertices.get("A").edges();
        assertEquals("B", itr.next().getDst().getData());

    }

    @Test
    void testDoubleDirectedGraph(){
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        assertEquals(3, graph.getVerticesSize());
        HashMap<String, Vertex<String>> vertices = graph.getVertices();
        Iterator<Edge<String>> itr = vertices.get("A").edges();
        assertEquals("B", itr.next().getDst().getData());
        assertEquals("C", itr.next().getDst().getData());
    }

    @Test
    void testDirectedGraphPointToPoint(){
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        assertEquals(3, graph.getVerticesSize());
        HashMap<String, Vertex<String>> vertices = graph.getVertices();
        Iterator<Edge<String>> itr = vertices.get("A").edges();
        assertEquals("B", itr.next().getDst().getData());
        HashMap<String, Vertex<String>> vertices2 = graph.getVertices();
        Iterator<Edge<String>> itr2 = vertices.get("A").edges();
        assertEquals("C", itr2.next().getDst().getData());
    }

    @Test
    void testUndirectedGraph(){
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B");
        graph.addEdge("B", "A");
        assertEquals(2, graph.getVerticesSize());
        HashMap<String, Vertex<String>> vertices = graph.getVertices();
        Iterator<Edge<String>> itr = vertices.get("A").edges();
        assertEquals("B", itr.next().getDst().getData());
        HashMap<String, Vertex<String>> vertices2 = graph.getVertices();
        Iterator<Edge<String>> itr2 = vertices.get("B").edges();
        assertEquals("A", itr2.next().getDst().getData());
    }
}
