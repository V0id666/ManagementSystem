package DataStructureManagementSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedListManager listManager = new LinkedListManager();
        BST bst = new BST();
        UndoStack undoStack = new UndoStack();
        PriorityQueue pq = new PriorityQueue();

        System.out.println("🗂️==============================🗂️");
        System.out.println("   Welcome to System Management!  ");
        System.out.println("🗂️==============================🗂️");

        while (true) {
            System.out.println("📋 MENU:");
            System.out.println("1️⃣ Add Item");
            System.out.println("2️⃣ View Items");
            System.out.println("3️⃣ Update Item ✏️");
            System.out.println("4️⃣ Delete Item 🗑️");
            System.out.println("5️⃣ Undo Last Deletion 🔁");
            System.out.println("6️⃣ Search by ID (BST) 🔍");
            System.out.println("7️⃣ Manage Priority Queue 📤");
            System.out.println("8️⃣ Save to File 💾");
            System.out.println("9️⃣ Load from File 📂");
            System.out.println("🔟 Exit 🚪");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("🆔 Enter ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("📝 Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("📄 Enter Description: ");
                    String desc = sc.nextLine();
                    System.out.print("🏷️ Enter Category: ");
                    String cat = sc.nextLine();

                    Item item = new Item(id, name, desc, cat);
                    listManager.addItem(item);
                    bst.insert(item);
                    System.out.println("✅ Item added successfully!");
                    System.out.println("-----------------------------------------------------------------------------");
                }

                case 2 -> {
                    System.out.println("\n📦 All Items:");
                    listManager.viewItems();
                    System.out.println("-----------------------------------------------------------------------------");
                }

                case 3 -> {
                    System.out.print("🔁 Enter ID to update: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("✏️ New Name: ");
                    String name = sc.nextLine();
                    System.out.print("🆕 New Description: ");
                    String desc = sc.nextLine();
                    System.out.print("🆕 New Category: ");
                    String cat = sc.nextLine();
                    listManager.updateItem(id, name, desc, cat);
                    System.out.println("✅ Item updated.");
                    System.out.println("-----------------------------------------------------------------------------");
                }

                case 4 -> {
                    System.out.print("🗑️ Enter ID to delete: ");
                    int id = sc.nextInt();
                    Item deleted = listManager.getItemById(id);
                    if (deleted != null && listManager.deleteItem(id)) {
                        undoStack.push(deleted);
                        System.out.println("🗂️ Item deleted and pushed to undo stack.");
                        System.out.println("-------------------------------------------------------------------------");
                    } else {
                        System.out.println("❌ Item not found.");
                        System.out.println("-------------------------------------------------------------------------");
                    }
                }

                case 5 -> {
                    Item restored = undoStack.pop();
                    if (restored != null) {
                        listManager.addItem(restored);
                        System.out.println("🔁 Undo complete. Item restored.");
                        System.out.println("-------------------------------------------------------------------------");
                    } else {
                        System.out.println("⚠️ Nothing to undo.");
                        System.out.println("-------------------------------------------------------------------------");
                    }
                }

                case 6 -> {
                    System.out.print("🔍 Enter ID to search: ");
                    int id = sc.nextInt();
                    Item result = bst.search(id);
                    if (result != null) {
                        System.out.println("✅ Item found:\n" + result);
                        System.out.println("-------------------------------------------------------------------------");
                    } else {
                        System.out.println("❌ Item not found.");
                        System.out.println("-------------------------------------------------------------------------");
                    }
                }

                case 7 -> {
                    System.out.print("📦 Enter ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    Item item = listManager.getItemById(id);
                    if (item != null) {
                        System.out.print("⚡ Is this item urgent? (true/false): ");
                        boolean urgent = sc.nextBoolean();
                        pq.enqueue(item, urgent);
                        System.out.println("📤 Item added to priority queue.");
                        System.out.println("-------------------------------------------------------------------------");
                    } else {
                        System.out.println("❌ Item not found.");
                        System.out.println("-------------------------------------------------------------------------");
                    }
                    pq.display();
                }

                case 8 -> {
                    try {
                        FileHandler.save(listManager);
                        System.out.println("💾 Items saved to file successfully!");
                        System.out.println("-------------------------------------------------------------------------");
                    } catch (Exception e) {
                        System.out.println("❌ Save error: " + e.getMessage());
                        System.out.println("-------------------------------------------------------------------------");
                    }
                }

                case 9 -> {
                    try {
                        listManager = FileHandler.load();
                        System.out.println("📂 Items loaded from file.");
                        System.out.println("-------------------------------------------------------------------------");
                    } catch (Exception e) {
                        System.out.println("❌ Load error: " + e.getMessage());
                        System.out.println("-------------------------------------------------------------------------");
                    }
                }

                case 10 -> {
                    System.out.println("👋 Exiting... Have a great day!");
                    return;
                }

                default -> System.out.println("⚠️ Invalid option. Please try again.");
            }
        }
    }
}
