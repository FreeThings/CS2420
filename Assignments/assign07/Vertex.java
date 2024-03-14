package assign07;

import java.util.Iterator;
import java.util.LinkedList;

public class Vertex<T> {

    private T data;
    private boolean visited;
    private Vertex<T> cameFrom;
    private int inDegree;
    private LinkedList<Edge<T>> adjList;

    public Vertex(T data) {
        this.inDegree = 0;
        this.cameFrom = null;
        this.visited = false;
        this.data = data;
        this.adjList = new LinkedList<>();
    }

    public void incrementInDegree(){
        inDegree++;
    }

    public void decrementInDegree(){
        inDegree--;
    }

    public int getInDegree(){
        return inDegree;
    }

    public void setVisitedTrue(){
        visited = true;
    }

    public void setVisitedFalse(){
        visited = false;
    }

    public void setCameFrom(Vertex<T> vertex){
        cameFrom = vertex;
    }

    public Vertex<T> getCameFrom(){
        return cameFrom;
    }

    public boolean isVisited() {
        return visited;
    }

    public void addEdge(Vertex<T> otherVertex) {
        adjList.add(new Edge<T>(otherVertex));
    }

    public T getData() {
        return data;
    }

    public Iterator<Edge<T>> edges(){
        return adjList.iterator();
    }
}
