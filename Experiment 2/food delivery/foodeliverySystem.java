
// ---------- Interface ----------
interface Discountable {
    double applyDiscount();
}

// ---------- Abstract class ----------
abstract class FoodOrder {
    // private data members
    private int orderId;
    private String customerName;
    private double amount;

    // static members
    public static String restaurantName = "Foodie Express";
    private static int orderCounter = 0;

    public FoodOrder(int orderId, String customerName, double amount) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.amount = amount;
        orderCounter++;
    }

    // getters and setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public static int getTotalOrders() { return orderCounter; }
    public static void displayTotalOrders() {
        System.out.println("Total Orders Placed So Far: " + orderCounter);
    }

    // abstract method
    public abstract double calculateDeliveryCharge();

    @Override
    public String toString() {
        return "Order ID: " + orderId +
                ", Customer: " + customerName +
                ", Bill Amount: Rs." + amount;
    }
}

// ---------- RegularOrder ----------
class RegularOrder extends FoodOrder implements Discountable {
    private static final double DELIVERY_CHARGE = 80.0;
    private static final double DISCOUNT_PERCENT = 10.0;

    public RegularOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    @Override
    public double calculateDeliveryCharge() {
        return DELIVERY_CHARGE;
    }

    @Override
    public double applyDiscount() {
        return (getAmount() * DISCOUNT_PERCENT) / 100.0;
    }

    @Override
    public String toString() {
        return "[Regular Order] " + super.toString();
    }
}

// ---------- PremiumOrder ----------
class PremiumOrder extends FoodOrder implements Discountable {
    private static final double DELIVERY_CHARGE = 50.0;
    private static final double DISCOUNT_PERCENT = 15.0;

    public PremiumOrder(int orderId, String customerName, double amount) {
        super(orderId, customerName, amount);
    }

    @Override
    public double calculateDeliveryCharge() {
        return DELIVERY_CHARGE;
    }

    @Override
    public double applyDiscount() {
        return (getAmount() * DISCOUNT_PERCENT) / 100.0;
    }

    @Override
    public String toString() {
        return "[Premium Order] " + super.toString();
    }
}

// ---------- Utility class ----------
class OrderUtility {
    public static boolean validateAmount(double amount) {
        return amount > 0;
    }

    public static boolean validateCustomerName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    public static String generateOrderSummary(int orderId, String customerName, double amount,
                                               double discount, double deliveryCharge,
                                               double finalAmount) {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------------------------\n");
        sb.append("Order ID        : ").append(orderId).append("\n");
        sb.append("Customer Name   : ").append(customerName).append("\n");
        sb.append("Bill Amount     : Rs.").append(amount).append("\n");
        sb.append("Discount Applied: Rs.").append(String.format("%.2f", discount)).append("\n");
        sb.append("Delivery Charge : Rs.").append(deliveryCharge).append("\n");
        sb.append("Final Payable   : Rs.").append(String.format("%.2f", finalAmount)).append("\n");
        sb.append("-------------------------------------------");
        return sb.toString();
    }
}

// ---------- Driver (main) class ----------
public class foodeliverySystem {
    public static void main(String[] args) {

        // (i) & (ii) create at least six orders, stored in an array of FoodOrder
        FoodOrder[] orders = new FoodOrder[6];
        orders[0] = new RegularOrder(101, "Amit Sharma", 500);
        orders[1] = new PremiumOrder(102, "Neha Verma", 1200);
        orders[2] = new RegularOrder(103, "Rohit Singh", 350);
        orders[3] = new PremiumOrder(104, "Priya Kapoor", 900);
        orders[4] = new RegularOrder(105, "Karan Mehta", 620);
        orders[5] = new PremiumOrder(106, "Simran Kaur", 1500);

        System.out.println("Restaurant Name: " + FoodOrder.restaurantName);
        System.out.println("=============================================\n");

        for (FoodOrder order : orders) {

            if (!OrderUtility.validateAmount(order.getAmount())) {
                System.out.println("Skipping order " + order.getOrderId() + " - invalid amount.");
                continue;
            }
            if (!OrderUtility.validateCustomerName(order.getCustomerName())) {
                System.out.println("Skipping order " + order.getOrderId() + " - invalid customer name.");
                continue;
            }

            // (v) delivery charge - polymorphic call
            double deliveryCharge = order.calculateDeliveryCharge();

            // (iv) discount - via Discountable interface
            double discount = 0.0;
            if (order instanceof Discountable) {
                discount = ((Discountable) order).applyDiscount();
            }

            // (vi) final payable amount
            double finalAmount = order.getAmount() - discount + deliveryCharge;

            // (iii) display bill + full summary
            System.out.println(order);
            System.out.println(OrderUtility.generateOrderSummary(
                    order.getOrderId(), order.getCustomerName(), order.getAmount(),
                    discount, deliveryCharge, finalAmount));
            System.out.println();
        }

        FoodOrder.displayTotalOrders();
    }
}