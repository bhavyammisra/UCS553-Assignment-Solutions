
// ---------- Interface ----------
interface Printable {
    void printDetails();
}

// ---------- Abstract class ----------
abstract class LibraryResource {
    // private data members
    private int resourceId;
    private String title;
    private String author;

    // static members
    public static String libraryName = "Central University Library";
    private static int resourceCounter = 0;

    public LibraryResource(int resourceId, String title, String author) {
        this.resourceId = resourceId;
        this.title = title;
        this.author = author;
        resourceCounter++;
    }

    // getters and setters
    public int getResourceId() { return resourceId; }
    public void setResourceId(int resourceId) { this.resourceId = resourceId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    // protected helper method
    protected String getResourceInfo() {
        return "ID: " + resourceId + ", Title: " + title + ", Author: " + author;
    }

    public static int getTotalResources() { return resourceCounter; }
    public static void displayTotalResources() {
        System.out.println("Total Resources Created: " + resourceCounter);
    }

    // abstract method
    public abstract double calculateFine(int overdueDays);
}

// ---------- Book ----------
class Book extends LibraryResource implements Printable {
    private static final double FINE_PER_DAY = 5.0;

    public Book(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    @Override
    public double calculateFine(int overdueDays) {
        if (overdueDays <= 0) return 0.0;
        return overdueDays * FINE_PER_DAY;
    }

    @Override
    public void printDetails() {
        System.out.println("[Book] " + getResourceInfo());
    }
}

// ---------- DigitalResource ----------
class DigitalResource extends LibraryResource implements Printable {
    private static final double FINE_PER_DAY = 2.0;

    public DigitalResource(int resourceId, String title, String author) {
        super(resourceId, title, author);
    }

    @Override
    public double calculateFine(int overdueDays) {
        if (overdueDays <= 0) return 0.0;
        return overdueDays * FINE_PER_DAY;
    }

    @Override
    public void printDetails() {
        System.out.println("[Digital Resource] " + getResourceInfo());
    }
}

// ---------- Utility class ----------
class InputValidator {
    public static boolean validateResourceId(int resourceId) {
        return resourceId > 0;
    }

    public static boolean validateFineDays(int days) {
        return days >= 0;
    }
}

// ---------- Driver (main) class ----------
public class librarysystem {
    public static void main(String[] args) {

        // (i) & (ii) create at least five objects, stored in an array
        LibraryResource[] resources = new LibraryResource[5];
        resources[0] = new Book(1, "Introduction to Java", "James Gosling");
        resources[1] = new DigitalResource(2, "Data Structures E-Book", "Robert Sedgewick");
        resources[2] = new Book(3, "Operating System Concepts", "Abraham Silberschatz");
        resources[3] = new DigitalResource(4, "Computer Networks PDF", "Andrew Tanenbaum");
        resources[4] = new Book(5, "Database Management Systems", "Raghu Ramakrishnan");

        int[] overdueDays = {3, 0, 7, 5, 10};

        System.out.println("Library Name: " + LibraryResource.libraryName);
        System.out.println("=================================================\n");

        double totalFine = 0.0;

        for (int i = 0; i < resources.length; i++) {
            LibraryResource resource = resources[i];
            int days = overdueDays[i];

            if (!InputValidator.validateResourceId(resource.getResourceId())) {
                System.out.println("Invalid Resource ID, skipping...");
                continue;
            }
            if (!InputValidator.validateFineDays(days)) {
                System.out.println("Invalid overdue days, skipping...");
                continue;
            }

            // (iii) display complete details
            if (resource instanceof Printable) {
                ((Printable) resource).printDetails();
            }

            // (iv) calculate fine (polymorphic call)
            double fine = resource.calculateFine(days);
            totalFine += fine;

            System.out.println("Overdue Days: " + days + ", Fine: Rs." + fine);
            System.out.println("-------------------------------------------------");
        }

        System.out.println("\nTotal Fine Collected on All Overdue Resources: Rs." + totalFine);

        LibraryResource.displayTotalResources();
    }
}