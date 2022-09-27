import java.util.*;

public class Tree<E extends Comparable<? super E>> {
    private BinaryTreeNode root;  // Root of tree
    private String name;     // Name of tree

    /**
     * Create an empty tree
     *
     * @param label Name of tree
     */
    public Tree(String label) {
        name = label;
    }

    /**
     * Create BST from ArrayList
     *
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public Tree(ArrayList<E> arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**
     * Create BST from Array
     *
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        name = label;
        for (E key : arr) {
            insert(key);
        }
    }

    /**
     * Return a string containing the tree contents as a tree with one node per line
     */
    public String toString() {
        // TODO:
        String msg = this.name + ": \n";
        System.out.print(msg);
        return toString(this.root,-1);
    }

    private String toString(BinaryTreeNode node, int progress){
        String msg = "";
        if (node != null) {
            progress++;
            toString(node.right,progress);
            if (node.parent != null) {
                String tempMsg = node.key + " [" + node.parent.key + "] ";
                System.out.print(tempMsg.indent(2*progress));
            }
            else{
                String tempMsg = node.key + " [no parent] ";
                System.out.print(tempMsg.indent(2*progress));
            }
            //System.out.println();
            toString(node.left,progress);
        }
        return msg;
    }


    private String inOrderToString(BinaryTreeNode node) {
        String msg = "";
        if (node != null) {
            inOrderToString(node.left);
            System.out.print(node.key + " ");
            inOrderToString(node.right);
        }
        return msg;
    }


    /**
     * Return a string containing the tree contents as a single line
     */
    public String inOrderToString() {
        // TODO:
        String msg = this.name + ": ";
        System.out.print(msg);
        return inOrderToString(this.root);
    }

    /**
     * reverse left and right children recursively
     */
    public void flip() {
        // TODO:
        System.out.print(flip(this.root));
    }

    private String flip(BinaryTreeNode node){
        if (node.right != null){
            flip(node.right);
        }
        if (node.left != null){
            flip(node.left);
        }

        BinaryTreeNode temp1 = node.right;
        node.right = node.left;
        node.left = temp1;
        return "\n";
    }

    /**
     * Returns the in-order successor of the specified node
     * @param node node from which to find the in-order successor
     */
    public BinaryTreeNode inOrderSuccessor(BinaryTreeNode node) {
        // TODO:
        BinaryTreeNode successor = new BinaryTreeNode(null);
        if (node == null){
            return successor;
        }
        if (node.right != null){
            BinaryTreeNode currNode = node.right;
            while (currNode.left != null){
                currNode = currNode.left;
            }
            successor = currNode;
        }
        else {
            if (node.parent.right != null && node.parent.right != node) {
                successor = node.parent;
            }
            else {
                boolean cond1 = true;
                boolean cond2 = true;
                BinaryTreeNode currNode = node;
                do {
                    if (currNode.parent != null){
                        if (currNode.parent.right == currNode) {
                            currNode = currNode.parent;
                        }
                        else{
                            cond2 = false;
                            currNode = currNode.parent;
                        }
                    }
                    else{
                        cond1 = false;
                        currNode = null;
                    }
                } while (cond1 && cond2);
                successor = currNode;
            }
        }
        return successor;
    }

    /**
     * Counts number of nodes in specified level
     *
     * @param level Level in tree, root is zero
     * @return count of number of nodes at specified level
     */
    public int nodesInLevel(int level) {
        // TODO:
        ArrayList<Integer> empty = new ArrayList<>();
        return nodesInLevel(level, this.root, -1,empty);
    }

    private int nodesInLevel(int level, BinaryTreeNode node, int progress, ArrayList<Integer> count){
        if (node != null){
            progress++;
            nodesInLevel(level, node.right, progress, count);
            nodesInLevel(level, node.left, progress, count);
            count.add(progress);
        }

        return Collections.frequency(count,level);
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        // TODO:
        ArrayList<BinaryTreeNode> empty = new ArrayList<>();
        ArrayList<BinaryTreeNode> leafNodes = printAllPaths(this.root,empty);
        //Now we have a list of all of the leaf nodes. Now we find our way back to the root for each.
        for (int i = 0; i < leafNodes.size(); i++){
            ArrayList<E> path = new ArrayList<>();
            path.add(leafNodes.get(i).key);
            BinaryTreeNode par = leafNodes.get(i).parent;
            while(par != null){
                path.add(par.key);
                par = par.parent;
            }
            //Now we need to reverse the path.
            ArrayList<E> Path = new ArrayList<>();
            for (int j=0; j< path.size(); j++){
                //Path.add(path.get(path.size()-1-j));
                System.out.print(path.get(path.size()-1-j) + " ");
            }
            System.out.println();
            //System.out.println(Path);
        }
    }

    private ArrayList<BinaryTreeNode> printAllPaths(BinaryTreeNode node, ArrayList<BinaryTreeNode> tracker){
        //The whole point of this method is to find the leaf nodes.
        if (node.right == null && node.left == null){
            tracker.add(node);
        }
        if (node.right != null){
            printAllPaths(node.right,tracker);
        }
        if (node.left != null){
            printAllPaths(node.left, tracker);
        }
        return tracker;
    }

    /**
     * Counts all non-null binary search trees embedded in tree
     *
     * @return Count of embedded binary search trees
     */
    public int countBST() {
        // TODO:
        ArrayList<Integer> tickMarks = new ArrayList<>();
        return countBST(this.root, tickMarks);
    }

    private int countBST(BinaryTreeNode node, ArrayList<Integer> count){
        if (node.right != null){
            countBST(node.right,count);
            //System.out.println("Moved to the right.");
        }
        if (node.left != null){
            countBST(node.left,count);
            //System.out.println("Moved to the left.");
        }
        if (node.right == null && node.left == null){
            count.add(1);
            //System.out.println("Leaf Node");
        }
        //Now we'll do the case where there are 2-3 nodes in the subtree.
        if (node.right== null && node.left != null){
            if (node.left.key.compareTo(node.key)<0){
                count.add(1);
                //System.out.println("First case of 2");
            }
        }
        if (node.left == null && node.right != null){
            if (node.right.key.compareTo(node.key)>0){
                count.add(1);
                //System.out.println("Second case of 2");
            }
        }
        if (node.left != null && node.right != null){
            if (node.left.key.compareTo(node.key)<0 && node.right.key.compareTo(node.key)>0){
                count.add(1);
                //System.out.println("Case of 3");
            }
        }
        return count.size();
    }

    /**
     * Insert into a bst tree; duplicates are allowed
     *
     * @param x the item to insert.
     */
    public void insert(E x) {
        root = insert(x, root, null);
    }

    public BinaryTreeNode getByKey(E key) {
        // TODO:
        return getByKey(key, this.root);
    }

    private BinaryTreeNode getByKey(E key, BinaryTreeNode node){
        //ripped from contains method.
        if (node == null) {
            return node;
        }
        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            return getByKey(key, node.left);
        }
        else if (compareResult > 0) {
            return getByKey(key, node.right);
        }
        else {
            return node;
        }
    }

    /**
     * Balance the tree
     */
    public void balanceTree() {
        // TODO:
        ArrayList<E> empty1 = new ArrayList<>();
        ArrayList<E> empty2 = new ArrayList<>();
        ArrayList<E> inOrder = inOrderTrav(this.root,empty1);
        ArrayList<E> binaryOrd = binaryTrav(inOrder,empty2,0,inOrder.size()-1);
        //System.out.println(binaryOrd);
        this.root = null;
        insert(binaryOrd.get(0));
        for (int i=1; i<binaryOrd.size(); i++){
            insert(binaryOrd.get(i));
        }
    }

    private ArrayList<E> binaryTrav(ArrayList<E> inord, ArrayList<E> wantBinaryOrd, int low, int high){
        int mid = (high + low)/2;
        if (low > high){
            return wantBinaryOrd;
        }
        wantBinaryOrd.add(inord.get(mid));
        binaryTrav(inord,wantBinaryOrd,low,mid-1);
        binaryTrav(inord,wantBinaryOrd,mid+1,high);
        return wantBinaryOrd;
    }

    private ArrayList<E> inOrderTrav(BinaryTreeNode node, ArrayList<E> old){
        if (node != null) {
            inOrderTrav(node.left, old);
            old.add(node.key);
            inOrderTrav(node.right, old);
        }
        return old;
    }

    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryTreeNode insert(E x, BinaryTreeNode t, BinaryTreeNode parent) {
        if (t == null) return new BinaryTreeNode(x, null, null, parent);

        int compareResult = x.compareTo(t.key);
        if (compareResult < 0) {
            t.left = insert(x, t.left, t);
        } else {
            t.right = insert(x, t.right, t);
        }

        return t;
    }


    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean contains(E x, BinaryTreeNode t) {
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.key);

        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else {
            return true;    // Match
        }
    }

    // Basic node stored in unbalanced binary trees
    public class BinaryTreeNode {
        E key;            // The data/key for the node
        BinaryTreeNode left;   // Left child
        BinaryTreeNode right;  // Right child
        BinaryTreeNode parent; //  Parent node

        // Constructors
        BinaryTreeNode(E theElement) {
            this(theElement, null, null, null);
        }

        BinaryTreeNode(E theElement, BinaryTreeNode lt, BinaryTreeNode rt, BinaryTreeNode pt) {
            key = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Node:");
            sb.append(key);
            if (parent == null) {
                sb.append("<>");
            } else {
                sb.append("<");
                sb.append(parent.key);
                sb.append(">");
            }

            return sb.toString();
        }
    }
}
