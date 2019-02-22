package linkedpathfinder;

/*
This class defines an edge. An edge in the graph corresponds to a 1 in the adjacency
matrix.
*/
public class Edge {
    
    int start;
    int dest;
    
    //The edge constructor. Start and destination nodes are passed in
    Edge(int s, int d) {
        start = s;
        dest = d;
    }
    
    public int getStart() {
        return start;
    }
    
    public int getDest() {
        return dest;
    }
    
    //creates a string from the edge data
    public String toString(){
        return (start + " --> " + dest + "\n");
    }
}