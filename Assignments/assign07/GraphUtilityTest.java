package assign07;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class GraphUtilityTest {

    @Test
    void testAreConnected() {

        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("B");
        sources.add("B");
        sources.add("A");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("D");
        destinations.add("E");
        destinations.add("C");
        destinations.add("F");


        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "F"));

    }

    @Test
    void testAreConnectedCyclic() {
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("A");
        sources.add("B");
        sources.add("C");
        sources.add("B");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("C");
        destinations.add("C");
        destinations.add("B");
        destinations.add("A");
        destinations.add("A");

        assertTrue(GraphUtility.areConnected(sources, destinations, "A", "C"));
    }

    @Test
    void testCyclicListBreadthSearch(){
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("B");
        sources.add("C");
        sources.add("B");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("C");
        destinations.add("A");
        destinations.add("D");

        List<String> path = new LinkedList<>();
        path.add("A");
        path.add("B");
        path.add("D");

        assertEquals(path, GraphUtility.shortestPath(sources, destinations, "A", "D"));
    }

    @Test
    void testBreadthSearchNoDestination(){
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("D");

        assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "A", "C"));
    }

    @Test
    void testShortestPath() {
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("B");
        sources.add("B");
        sources.add("A");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("D");
        destinations.add("E");
        destinations.add("C");
        destinations.add("F");

        List<String> path = new LinkedList<>();
        path.add("A");
        path.add("C");
        path.add("F");

        assertEquals(path, GraphUtility.shortestPath(sources, destinations, "A", "F"));
    }

    @Test
    void testSortBasic() {
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("B");
        sources.add("B");
        sources.add("A");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("D");
        destinations.add("E");
        destinations.add("C");
        destinations.add("F");

        List<String> sorted = new LinkedList<>();
        sorted.add("A");
        sorted.add("B");
        sorted.add("C");
        sorted.add("D");
        sorted.add("E");
        sorted.add("F");

        assertEquals(sorted, GraphUtility.sort(sources, destinations));
    }

    @Test
    void testDepthSearchForNonExistentValues(){
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("B");
        sources.add("B");
        sources.add("A");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("D");
        destinations.add("E");
        destinations.add("C");
        destinations.add("F");


        assertThrows(IllegalArgumentException.class, () -> GraphUtility.areConnected(sources, destinations, "X", "Y"));
    }

    @Test
    void testBreadthSearchForNonExistentValues(){
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("B");
        sources.add("B");
        sources.add("A");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("D");
        destinations.add("E");
        destinations.add("C");
        destinations.add("F");


        assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "X", "Y"));
    }

    @Test
    void testPossibleInfiniteLoopBreadthSearch(){
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("B");
        sources.add("C");
        sources.add("A");
        sources.add("D");
        sources.add("E");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("C");
        destinations.add("A");
        destinations.add("D");
        destinations.add("E");
        destinations.add("F");

        List<String> path = new LinkedList<>();
        path.add("A");
        path.add("D");
        path.add("E");
        path.add("F");

        assertEquals(path, GraphUtility.shortestPath(sources, destinations, "A", "F"));
    }

    @Test
    void testTopologicalSortCyclic(){
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("B");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("C");
        destinations.add("A");

        assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
    }

    @Test
    void testUnachievablePathTopologicalSearch(){
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("B");

        List<String> path = new LinkedList<>();
        path.add("A");
        path.add("C");
        path.add("B");

        assertEquals(path, GraphUtility.sort(sources, destinations));
    }

    @Test
    void testSeperatedVerticesTopologicalSort(){
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("B");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("D");
        destinations.add("E");
        destinations.add("F");

        List<String> sorted = new LinkedList<>();
        sorted.add("A");
        sorted.add("B");
        sorted.add("C");
        sorted.add("D");
        sorted.add("E");
        sorted.add("F");

        assertEquals(sorted, GraphUtility.sort(sources, destinations));
    }

    @Test
    void testDepthSearchForEmptyGraph(){
        List<String> sources = new LinkedList<>();
        List<String> destinations = new LinkedList<>();

        assertThrows(IllegalArgumentException.class, () -> GraphUtility.areConnected(sources, destinations, "A", "B"));
    }

    @Test
    void testBreadthSearchForEmptyGraph(){
        List<String> sources = new LinkedList<>();
        List<String> destinations = new LinkedList<>();

        assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "A", "B"));
    }

    @Test
    void testTopologicalSortForEmptyGraph(){
        List<String> sources = new LinkedList<>();
        List<String> destinations = new LinkedList<>();

        assertThrows(IllegalArgumentException.class, () -> GraphUtility.sort(sources, destinations));
    }

    @Test
    void testUnachievablePathBreadthSearch(){
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("B");

        assertThrows(IllegalArgumentException.class, () -> GraphUtility.shortestPath(sources, destinations, "A", "C"));
    }

    @Test
    void testDepthSearchTwoSeperatePaths(){
        List<String> sources = new LinkedList<>();
        sources.add("A");
        sources.add("C");

        List<String> destinations = new LinkedList<>();
        destinations.add("B");
        destinations.add("D");

        assertTrue(GraphUtility.areConnected(sources, destinations, "C", "D"));
    }

}
