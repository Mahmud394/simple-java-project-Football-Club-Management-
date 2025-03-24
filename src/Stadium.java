import java.io.Serializable;

public class Stadium implements Serializable {
    private String name;
    private String location;
    private int capacity;

    public Stadium(String name, String location, int capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

    public void displayStadiumDetails() {
        System.out.println("\nStadium: " + name);
        System.out.println("Location: " + location);
        System.out.println("Capacity: " + capacity + " seats");
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }
}