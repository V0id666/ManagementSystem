package DataStructureManagementSystem;

public class Item {
    public int id;
    public String name;
    public String description;
    public String category;

    public Item(int id, String name, String description, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Category: " + category + "\nDescription: " + description;
    }
}

