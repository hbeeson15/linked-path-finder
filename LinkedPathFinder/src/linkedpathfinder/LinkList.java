package linkedpathfinder;

/* 
This class defines a linked list. In this class, we are keeping track of the list's
head node, the current Node, and the size of the list. There is a method to add a new
node to the list and to print the list. 
*/
public class LinkList {
    
    public Node head;
    Node currNode;
    int size = 0; 
    
    public void LinkList() {
 
    }
    
    public void addNode(Node n) { 
        if(head == null) {          //checks if this will be the first node in the list
            head = n;
            currNode = head;
            currNode.setNext(null);
            size++;
        } else {                //this node will be added to an existing list
            currNode.setNext(n);
            n.setNext(null);
            currNode = n;
            size++;
        } 
    }
    
    public void printList() {
        Node temp = currNode;
        
        while(temp != null) {
            System.out.print(temp.id.toString());
            temp = temp.next;
        }
    }
    
}