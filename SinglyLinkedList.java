
import java.util.Iterator;
import java.util.NoSuchElementException;


public class SinglyLinkedList<T> implements Iterable<T> {
    
    public class Node {
    T content;
    Node prev = null;
    Node next = null;

    Node(T content) {
        this.content = content;
    }
}
    
    Node first = null;
    Node last = null;
    int size = 0; //track size
    int capacity;

    public SinglyLinkedList(){}

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    
    public void addFirst(T value){

        if (first == null && last == null) {
            Node newNode = new Node(value);
            first = newNode;
            last = newNode;
            size++;
            return;

        }
        if(first.prev == null){
            Node newNode = new Node(value);
            first.prev = newNode;
            newNode.next = first;
            first = newNode;
           
            size++;
            return;
        }
        
    }
    public void addLast(T value){
        if (first == null && last == null) {
            Node newNode = new Node(value);
            this.first = newNode;
            this.last = newNode;
            size++;
            return;
        }
        if(last.next == null){
            Node newNode = new Node(value);
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
            size++;
        }
    };
    
    public void insertAt(int index, T value){
    
      if(index < 0 || index > size()) {
        throw new IndexOutOfBoundsException("Your chosen insertion point is outside the current index you have made. Please choose another destination, or modify the index before inserting.");    
      }                     
      else if(index == 0) {
        addFirst(value);
        return;
      }
      else if(index == size()) {
        addLast(value);
        return;
      }
        Node newNode = new Node(value);     
        Node currentNode = first;

         for(int i=0;i<index;i++){
            currentNode = currentNode.next;
        }
        newNode.prev = currentNode.prev;
        newNode.next = currentNode;
        currentNode.prev.next = newNode;        //please check this
        currentNode.prev = newNode;
        size++;

    }

    
   
   

    public Node get(int index){
        int trackCurrentIndex = 0;

        Node currentNode = first;

        while (currentNode != null) {
            if (trackCurrentIndex == index) {
                return currentNode;
            }

            trackCurrentIndex += 1;
            currentNode = currentNode.next;
        }

        return null;
        
    }
    
    public T set(int index, T value){
        Node nodeToSet = get(index);
        nodeToSet.content = value;
        return nodeToSet.content;
    }
    public T removeFirst(){
        T firstObj = first.content;
        if(first == null) {
            throw new NoSuchElementException("The list is already empty.");
            } 
            else if(first == last){
                clear();
            }
            else {
                first = first.next;         //pointer moves to 2nd item
                first.prev = null;          //delete 1st
                size--;                     // -1 sa size
            }
            return firstObj;
    }
    public T removeLast(){
        T lastObj = last.content;
        if(last == null) {
            throw new NoSuchElementException("The list is already empty.");
        } else if(last == first){
            clear();
        } else {
            last = last.prev;
            last.next = null;
            size--;
        }
        return lastObj;
    }
    
    public T removeAt(int index){
        
        Node nodeToRemove = get(index);
        T removedObj = nodeToRemove.content;

        Node prevNode = nodeToRemove.prev;
        Node nextNode = nodeToRemove.next;
        size--;
        if (prevNode != null) {
            prevNode.next = nextNode;
        }

        if (nextNode != null) {
            nextNode.prev = prevNode;
        }

        return removedObj;
    }

    public int indexOf(Object o){
        Node currentNode = first;
        int index = 0;
        while (currentNode.content != null) {       //uses the .data thing but changed to content since our constructor is called content
            if (currentNode.content.equals(o)) {     
                return index;                     
            }
            currentNode = currentNode.next;
            index++;
        }
        return index;
    }
    public boolean contains(T o){
        Node currentNode = first;
        while (currentNode != null) {
            if (o == null) {
            if (currentNode.content == null) {
                return true; 
            }
        } 

        else {
            if (o.equals(currentNode.content)) {
                return true; 
            }
        }
        currentNode = currentNode.next;
        }
        

        return false;
    }

    public void clear(){
        first = last = null; // null first and last
        size = 0;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        Node currentNode = first;

        while (currentNode != null) {
            sb.append(currentNode.content);
            if(currentNode.next != null){
                sb.append(", ");
            }
            currentNode = currentNode.next;
        }
        return sb.append("]").toString();
    }

    @Override
    public Iterator<T> iterator(){
        return null;
    }
}