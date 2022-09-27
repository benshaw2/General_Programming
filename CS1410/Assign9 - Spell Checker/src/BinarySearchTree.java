public class BinarySearchTree<E extends Comparable<E>> {
    private class TreeNode {
        E value;
        TreeNode left;
        TreeNode right;

        public TreeNode(E value) {
            this.value = value;
        }
    }

    private TreeNode root;


    public boolean insert(E value){
        boolean found = search(value);
        boolean inserted = false;
        if (!found) {
            if (root == null) {
                root = new TreeNode(value);
            } else {
                TreeNode parent = null;
                TreeNode node = root;
                while (node != null) {
                    parent = node;
                    if (node.value.compareTo(value) < 0) {
                        node = node.right;
                    } else {
                        node = node.left;
                    }
                }

                TreeNode newNode = new TreeNode(value);
                if (parent.value.compareTo(value) < 0) {
                    parent.right = newNode;
                    inserted = true;
                } else if (parent.value.compareTo(value) > 0) {
                    parent.left = newNode;
                    inserted = true;
                }
            }
        }
        return inserted;
    }

    public boolean remove(E value) {
        boolean found = search(value);
        //First we'll do a search to see if the value is in the Tree.
        if (found) {
            TreeNode parent = null;
            TreeNode node = root;
            boolean done = false;
            while (!done) {
                if (node.value.compareTo(value) < 0) {
                    parent = node;
                    node = node.right;
                }
                else if (node.value.compareTo(value) > 0){
                    parent = node;
                    node = node.left;
                }
                else {
                    done = true;
                }
            }
            //no left child
            if (node.left == null) {
                if (parent == null) {
                    root = node.right;
                }
                else {
                    if (parent.value.compareTo(value) < 0) {
                        parent.right = node.right;
                    } else {
                        parent.left = node.right;
                    }
                }
            }
            //Other case
            else {
                TreeNode parentOfRight = node;
                TreeNode rightMost = node.left;
                while (rightMost.right != null) {
                    parentOfRight = rightMost;
                    rightMost = rightMost.right;
                }
                node.value = rightMost.value;
                if (parentOfRight.right == rightMost) {
                    parentOfRight.right = rightMost.left;
                } else {
                    parentOfRight.left = rightMost.left;
                }
            }
        }
        return found;
    }

    public boolean search(E value){
        boolean found = false;
        TreeNode node = root;

        while (!found &&node !=null){
            //System.out.println(node.value.getClass().getName());
            if (node.value.compareTo(value) == 0){
                found = true;
            }
            else if (node.value.compareTo(value) < 0) /*(node.value < value)*/ {
                node = node.right;
            }
            else {
                node = node.left;
            }
        }
        return found;
    }

    public void display(String message) {
        System.out.println(message);
        display(root);
    }

    private void display(TreeNode node) {
        if (node == null) {
            return;
        }
        else {
            display(node.left);
            System.out.println(node.value);
            display(node.right);
        }
    }

    public int numberNodes() {
        return numberNodes(root);
    }

    private int numberNodes(TreeNode node){
        int count = 0;
        if (node == null) {
            count += 0;
        }
        else {
            count += 1 + numberNodes(node.left) + numberNodes(node.right);
        }
        return count;
    }

    public int numberLeafNodes() {
        return numberLeafNodes(root);
    }

    private int numberLeafNodes(TreeNode node) {
        int count = 0;
        if (node == null) {
            count += 0;
        }
        else if (node.left == null && node.right == null) {
            count += 1;
        }
        else {
            count += numberLeafNodes(node.left) + numberLeafNodes(node.right);
        }
        return count;
    }

    public int height() {
        return height(root) - 1; //We're subtracting 1 due to height conventions.
    }

    private int height(TreeNode node) {
        int count = 0;
        if (node == null) {
            count += 0;
        }
        else {
            count += 1 + Math.max(height(node.left), height(node.right));
        }
        return count;
    }
}