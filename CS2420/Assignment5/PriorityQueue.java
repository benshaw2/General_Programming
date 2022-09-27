public class PriorityQueue<E extends Comparable<? super E>>{
    private Node<E> root;


    public PriorityQueue(E val){
        this.root = new Node<>(val);
    }


    public PriorityQueue() {
        this.root = null;
    }


    public E getRoot(){
        Node<E> tempNode = this.root;
        return tempNode.value;
    }


    private int getNpl(Node<E> t) { //This is from the lecture slides.
        if (t == null) return -1;
        return t.npl;
    }


    private void swapkids(Node<E> t){
        Node<E> tempNode = t.right;
        t.right = t.left;
        t.left = tempNode;
    }


    private int setNullPathLength(Node<E> t){
        if (t.right==null || t.left==null){
            return 0;
        }
        int smallerOfTwo = Math.min(setNullPathLength(t.right),setNullPathLength(t.left));
        t.npl = 1 + smallerOfTwo;
        return t.npl;
    }


    public void enqueue(E value){
        PriorityQueue<E> newQueue = new PriorityQueue<>(value);
        this.root = merge(this.root, newQueue.root);
    }

    public void printTree(){
        printTree(this.root);
    }

    private void printTree(Node<E> t){
        if (t == null){
            return;
        }
        System.out.println(t.value + " " + t.npl);
        printTree(t.right);
        printTree(t.left);
    }


    public E dequeue(){
        Node<E> tempNode = this.root;
        this.root = merge(this.root.left, this.root.right);
        return tempNode.value;
    }

    public boolean isEmpty(){
        return this.root == null;
    }


    private Node<E> merge(Node<E> t1, Node<E> t2) { //This code is just ripped from the lecture slides.
        Node<E> small;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        if (t1.value.compareTo(t2.value) < 0) { // Changed "element" to "value."
            t1.right = merge(t1.right, t2);
            small = t1;
        }
        else {
            t2.right = merge(t2.right, t1);
            small = t2;
        }
        if (getNpl(small.left) < getNpl(small.right)) {
            swapkids(small);
        }
        setNullPathLength(small);
        return small;
    }



    private class Node<E> { //This code is just ripped from the lecture slides.
        Node(E value) {
            this(value, null, null);
        }
        Node(E value, Node<E> left, Node<E> right) {
            this.value = value;
            this.left = left;
            this.right = right;
            npl = 0;
        }

        public E value;        // The data in the node
        public Node<E> left;   // Left child
        public Node<E> right;  // Right child
        public int npl;        // null path length
    }
}