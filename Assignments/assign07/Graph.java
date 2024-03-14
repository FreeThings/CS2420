package assign07;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Graph <T> {

//    private List<T> sources;
//    private List<T> destinations;
    private Stack<Vertex<T>> vertexStack;

    private HashMap<T, Vertex<T>> vertices;

    public Graph() {
//        this.sources = sources;
//        this.destinations = destinations;
        this.vertexStack = new Stack<>();
        vertices = new HashMap<>();
    }

    public void addEdge(T source, T destination){
        Vertex<T> vertex1;

        if(vertices.containsKey(source)){
            vertex1 = vertices.get(source);
        } else {
            vertex1 = new Vertex<>(source);
            vertices.put(source, vertex1);
        }

        Vertex<T> vertex2;
        if(vertices.containsKey(destination)){
            vertex2 = vertices.get(destination);
        } else {
            vertex2 = new Vertex<>(destination);
            vertices.put(destination, vertex2);
        }

        vertex1.addEdge(vertex2);
    }

    public int getVerticesSize(){
        return vertices.size();
    }

    public Vertex<T> getDestination(T source, T destination){
        Vertex<T> vertex1;
        if (vertices.containsKey(source)){
            vertex1 = vertices.get(source);
        } else {
            return null;
        }
        Iterator<Edge<T>> iterator = vertex1.edges();
        while (iterator.hasNext()){
            Edge<T> edge = iterator.next();
            System.out.println(edge.toString());
            if (edge.getDst().equals(destination))
                return edge.getDst();
        }
        return null;
    }

    public HashMap<T, Vertex<T>> getVertices() {
        return vertices;
    }


    public boolean depthFirstSearchDriver(T source, T destination){
        if (checkVerticesExist(source) && checkVerticesExist(destination))
            return depthFirstSearch(source, destination);
        throw new IllegalArgumentException("Vertices do not exist in the graph!");
    }
    public boolean depthFirstSearch(T source, T destination){
        if(source.equals(destination))
            return true;

        Vertex<T> vertexSource = vertices.get(source);
        vertexSource.setVisitedTrue();

        if (vertexStack.isEmpty())
            vertexStack.push(vertexSource);
        else if (!vertexStack.peek().equals(vertexSource))
            vertexStack.push(vertexSource);

        Iterator<Edge<T>> iterator = vertexSource.edges();
        while(iterator.hasNext()){
            Edge<T> edge = iterator.next();
            if(!edge.getDst().isVisited())
                return depthFirstSearch(edge.getDst().getData(), destination);
        }

        vertexStack.pop();
        if (!vertexStack.isEmpty())
            return depthFirstSearch(vertexStack.peek().getData(), destination);
        return false;
    }

    public List<T> breadthFirstSearch(T source, T destination) {

        if (!checkVerticesExist(source) || !checkVerticesExist(destination))
            throw new IllegalArgumentException("Vertices do not exist in the graph!");

        LinkedList<Vertex<T>> queue =  new LinkedList<>();
        LinkedList<T> path = new LinkedList<>();
        queue.add(vertices.get(source));
        vertices.get(source).setVisitedTrue();

        if(source.equals(destination)) {
            path.add(source);
            return path;
        }
        while(!queue.isEmpty()){
            Vertex<T> vertex = queue.poll();
            Iterator<Edge<T>> iterator = vertex.edges();
            while (iterator.hasNext()) {
                Vertex<T> nextVertex = iterator.next().getDst();

                if (!nextVertex.isVisited()){
                    nextVertex.setCameFrom(vertex);
                    nextVertex.setVisitedTrue();
                    queue.add(nextVertex);
                    if (nextVertex.getData().equals(destination)){
                        path.add(nextVertex.getData());
                        while (nextVertex.getCameFrom() != null){
                            nextVertex = nextVertex.getCameFrom();
                            path.addFirst(nextVertex.getData());
                        }
                        return path;
                    }
                }
            }

        }

        throw new IllegalArgumentException("There is no path between the given source and the destination.");
//        return null;
    }

    public List<T> topologicalSort(){

        List<T> sortedList = new LinkedList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();

        for (Vertex<T> vertex : vertices.values()){
            if (vertex.getInDegree() == 0)
                queue.offer(vertex);
        }
        if(queue.isEmpty())
            throw new IllegalArgumentException("Graph is cyclical");
        while (!queue.isEmpty()){
            Vertex<T> vertex = queue.poll();
            sortedList.add(vertex.getData());
            Iterator<Edge<T>> iterator = vertex.edges();
            while (iterator.hasNext()){
                Vertex<T> nextVertex = iterator.next().getDst();
                nextVertex.decrementInDegree();
                if (nextVertex.getInDegree() == 0)
                    queue.offer(nextVertex);
            }
        }

        return sortedList;
    }

    public void setVerticesToUnvisited(){
        for(Vertex<T> vertex : vertices.values())
            vertex.setVisitedFalse();
    }

    public boolean checkVerticesExist(T data){
        return vertices.containsKey(data);
    }
}
