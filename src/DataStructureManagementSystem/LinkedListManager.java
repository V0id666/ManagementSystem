package DataStructureManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class LinkedListManager {
    private static class Node {
        Item data;
        Node next;

        Node(Item data) {
            this.data = data;
        }
    }

    private Node head;

    public void addItem(Item item) {
        Node newNode = new Node(item);
        if (head == null) head = newNode;
        else {
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
    }

    public boolean deleteItem(int id) {
        if (head == null) return false;
        if (head.data.id == id) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.data.id != id) {
            current = current.next;
        }

        if (current.next == null) return false;

        current.next = current.next.next;
        return true;
    }

    public void updateItem(int id, String name, String desc, String category) {
        Node current = head;
        while (current != null) {
            if (current.data.id == id) {
                current.data.name = name;
                current.data.description = desc;
                current.data.category = category;
                return;
            }
            current = current.next;
        }
    }

    public void viewItems() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public Item getItemById(int id) {
        Node current = head;
        while (current != null) {
            if (current.data.id == id) return current.data;
            current = current.next;
        }
        return null;
    }

    // METHOD to expose list of Items safely
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        Node current = head;
        while (current != null) {
            items.add(current.data);
            current = current.next;
        }
        return items;
    }

    // Optional: clear the list (used during load)
    public void clear() {
        head = null;
    }
}
