package DataStructureManagementSystem;

public class PriorityQueue {
    private static class Node {
        Item data;
        Node next;

        Node(Item data) {
            this.data = data;
        }
    }

    private Node urgentFront, urgentRear;
    private Node normalFront, normalRear;

    public void enqueue(Item item, boolean isUrgent) {
        Node newNode = new Node(item);
        if (isUrgent) {
            if (urgentRear == null) urgentFront = urgentRear = newNode;
            else {
                urgentRear.next = newNode;
                urgentRear = newNode;
            }
        } else {
            if (normalRear == null) normalFront = normalRear = newNode;
            else {
                normalRear.next = newNode;
                normalRear = newNode;
            }
        }
    }

    public Item dequeue() {
        if (urgentFront != null) {
            Item item = urgentFront.data;
            urgentFront = urgentFront.next;
            if (urgentFront == null) urgentRear = null;
            return item;
        }
        if (normalFront != null) {
            Item item = normalFront.data;
            normalFront = normalFront.next;
            if (normalFront == null) normalRear = null;
            return item;
        }
        return null;
    }

    public void display() {
        System.out.println("Urgent Queue:");
        Node temp = urgentFront;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }

        System.out.println("Normal Queue:");
        temp = normalFront;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}
