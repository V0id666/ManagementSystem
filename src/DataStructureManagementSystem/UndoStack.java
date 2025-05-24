package DataStructureManagementSystem;

public class UndoStack {
    private static class Node {
        Item data;
        Node next;

        Node(Item data) {
            this.data = data;
        }
    }

    private Node top;

    public void push(Item item) {
        Node newNode = new Node(item);
        newNode.next = top;
        top = newNode;
    }

    public Item pop() {
        if (top == null) return null;
        Item popped = top.data;
        top = top.next;
        return popped;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
