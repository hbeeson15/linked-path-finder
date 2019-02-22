package linkedpathfinder;

/*
This class defines a node class that can accept any data type. These nodes are linked
to form a linked list.
*/
public class Node<T> {
   
    T id;
    Node next; 
    
    Node(){
        
    }
    
    public void setID (T i) {
        this.id = i;
    }
    
    public T getID() {
        return this.id;
    }
    
    public Node getNext (Node nodeID) {
        return nodeID.next;
    }
    
    public void setNext (Node nodeID) {
        next = nodeID;
    }
}