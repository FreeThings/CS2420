package assign07;

public class Edge <T> {

    private Vertex<T> dst;

    public Edge(Vertex<T> dst) {
        this.dst = dst;
        dst.incrementInDegree();
    }

    public Vertex<T> getDst(){
        return dst;
    }


    public String toString(){
        return dst.toString();
    }


}
