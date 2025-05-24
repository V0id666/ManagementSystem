package DataStructureManagementSystem;

import java.io.*;
import java.util.List;

public class FileHandler {

    // Save items to a text file
    public static void save(LinkedListManager listManager) throws IOException {
        List<Item> items = listManager.getAllItems();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("items.txt"))) {
            for (Item item : items) {
                // Format: ID|Name|Description|Category
                writer.write(item.id + "|" + item.name + "|" + item.description + "|" + item.category);
                writer.newLine();
            }
        }
    }

    // Load items from the text file
    public static LinkedListManager load() throws IOException {
        LinkedListManager manager = new LinkedListManager();
        File file = new File("items.txt");

        if (!file.exists()) {
            System.out.println("No file found.");
            return manager;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String desc = parts[2];
                    String cat = parts[3];
                    manager.addItem(new Item(id, name, desc, cat));
                }
            }
        }

        return manager;
    }
}

