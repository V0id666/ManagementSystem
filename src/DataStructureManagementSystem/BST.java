package DataStructureManagementSystem;

public class BST {
    class Node {
        Item item;
        Node left, right;

        Node(Item item) {
            this.item = item;
        }
    }

    private Node root;

    public void insert(Item item) {
        root = insertRec(root, item);
    }

    private Node insertRec(Node root, Item item) {
        if (root == null) return new Node(item);
        if (item.id < root.item.id) root.left = insertRec(root.left, item);
        else root.right = insertRec(root.right, item);
        return root;
    }

    public Item search(int id) {
        return searchRec(root, id);
    }

    private Item searchRec(Node root, int id) {
        if (root == null) return null;
        if (id == root.item.id) return root.item;
        return id < root.item.id ? searchRec(root.left, id) : searchRec(root.right, id);
    }
}
