package linkedpathfinder;

/*
This class defines my graphs. The adjecency matrix and its size from the input file are 
passed in. 
*/
public class Graph{
    
    public int size;
    int[][] matrix;
    int[] vertices;
    LinkList edges;         //a linked list of nodes of type edge
    
    Graph(int[][] mat, int matSize){
        
        matrix = mat;
        size = matSize;
    }
    
    //This method creates the graph from the adjecency matrix
    public void buildGraph() {
        vertices = new int[size];
        edges = new LinkList();
        
        for(int i =  1; i < size; i++){
            //populate the vertices of the graph
            vertices[i] = i;       
        }
        
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++){
                //if [i][j] in the adjacency matrix is 1, create an Edge between those 2 vertices
                if(matrix[i][j] == 1) {
                    Edge e = new Edge(i, j);         
                    Node n = new Node<Edge>();
                    n.setID(e);
                    edges.addNode(n);
                }
            }
        }
    }    
    
    /*This function checks if an edge exists in the graph. Two vertices are passed in and
    if there is an edge in the graph between those two vertices, it returns true*/
    public boolean hasEdge(int vert1, int vert2) {        
        Node current = edges.head;
        Node previous = null;

        //Traverse through the linked list of nodes of type edge
        while(current != null) {
            Edge e = (Edge) current.getID();
            
            //if the current edge's start and destination vertices match vert1 and vert2 
            if(e.getStart() == vert1 && e.getDest() == vert2 ) {
                return true;
            }
            
            previous = current;
            current = current.next;
        }
        return false;
    }
}



