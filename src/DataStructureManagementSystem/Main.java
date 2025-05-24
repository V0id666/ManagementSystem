package DataStructureManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedListManager listManager = new LinkedListManager();
        BST bst = new BST();
        UndoStack undoStack = new UndoStack();
        PriorityQueue pq = new PriorityQueue();

        while (true) {
            System.out.println("\n==== Management System ====");
            System.out.println("1. Add Item");
            System.out.println("2. View Items");
            System.out.println("3. Update Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Undo Delete");
            System.out.println("6. Search by ID (BST)");
            System.out.println("7. Manage Priority Queue");
            System.out.println("8. Save to File");
            System.out.println("9. Load from File");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter Category: ");
                    String cat = sc.nextLine();

                    Item item = new Item(id, name, desc, cat);
                    listManager.addItem(item);
                    bst.insert(item);
                    System.out.println("Item added successfully.");
                }

                case 2 -> listManager.viewItems();

                case 3 -> {
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("New Name: ");
                    String name = sc.nextLine();
                    System.out.print("New Description: ");
                    String desc = sc.nextLine();
                    System.out.print("New Category: ");
                    String cat = sc.nextLine();
                    listManager.updateItem(id, name, desc, cat);
                    System.out.println("Item updated.");
                }

                case 4 -> {
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt();
                    Item deleted = listManager.getItemById(id);
                    if (deleted != null && listManager.deleteItem(id)) {
                        undoStack.push(deleted);
                        System.out.println("Item deleted and pushed to undo stack.");
                    } else {
                        System.out.println("Item not found.");
                    }
                }

                case 5 -> {
                    Item restored = undoStack.pop();
                    if (restored != null) {
                        listManager.addItem(restored);
                        System.out.println("Undo complete: Item restored.");
                    } else {
                        System.out.println("Nothing to undo.");
                    }
                }

                case 6 -> {
                    System.out.print("Enter ID to search: ");
                    int id = sc.nextInt();
                    Item result = bst.search(id);
                    System.out.println(result != null ? result : "Item not found.");
                }

                case 7 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    Item item = listManager.getItemById(id);
                    if (item != null) {
                        System.out.print("Is this item urgent? (true/false): ");
                        boolean urgent = sc.nextBoolean();
                        pq.enqueue(item, urgent);
                        System.out.println("Item added to priority queue.");
                    } else {
                        System.out.println("Item not found.");
                    }
                    pq.display();
                }

                case 8 -> {
                    try {
                        FileHandler.save(listManager);
                        System.out.println("Items saved to file.");
                    } catch (Exception e) {
                        System.out.println("Save error: " + e.getMessage());
                    }
                }

                case 9 -> {
                    try {
                        listManager = FileHandler.load();
                        System.out.println("Items loaded from file.");
                    } catch (Exception e) {
                        System.out.println("Load error: " + e.getMessage());
                    }
                }

                case 10 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
