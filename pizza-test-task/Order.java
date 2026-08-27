import java.time.LocalTime;
import java.util.Locale;

public class Order {

    private static int nextOrderNumber = 10000;
    private static final int MAX_PIZZAS = 10;

    private final int orderNumber;
    private final int customerNumber;

    private final Pizza[] pizzas;
    private int pizzaCount;

    private final LocalTime orderTime;

    public Order(int customerNumber) {
        this.customerNumber = customerNumber;
        this.orderNumber = ++nextOrderNumber;
        this.pizzas = new Pizza[MAX_PIZZAS];
        this.pizzaCount = 0;
        this.orderTime = LocalTime.now();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public Pizza[] getPizzas() {
        return pizzas;
    }

    public int getPizzaCount() {
        return pizzaCount;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public boolean addPizza(Pizza pizza) {
        if (pizzaCount >= pizzas.length) {
            System.out.println("Order " + orderNumber + " already has the maximum of "
                    + pizzas.length + " pizzas, \"" + pizza.getName() + "\" was not added.");
            return false;
        }

        int indexInOrder = pizzaCount + 1;
        pizza.applyFallbackNameIfInvalid(customerNumber, indexInOrder);

        pizzas[pizzaCount++] = pizza;
        System.out.println(pizza.displayAttributes(orderNumber, customerNumber));
        return true;
    }

    /**
     * Part 3: prints the full invoice for this order.
     */
    public void printCheck() {
        StringBuilder sb = new StringBuilder();
        double totalAmount = 0.0;

        sb.append("********************************\n");
        for (int i = 0; i < pizzaCount; i++) {
            Pizza pizza = pizzas[i];

            sb.append("Order: ").append(orderNumber).append('\n');
            sb.append("Client: ").append(customerNumber).append('\n');
            sb.append("Name: ").append(pizza.getName()).append('\n');
            sb.append("--------------------------------\n");

            String baseLabel = pizza.getType() == PizzaType.CALZONE ? "Pizza Base (Calzone)" : "Pizza Base";
            double basePrice = pizza.getType() == PizzaType.CALZONE ? 1.5 : 1.0;
            sb.append(formatLine(baseLabel, basePrice));

            for (Ingredient ingredient : pizza.getIngredients()) {
                if (ingredient == null) {
                    continue;
                }
                sb.append(formatLine(ingredient.getDisplayName(), ingredient.getPrice()));
            }

            sb.append("--------------------------------\n");
            sb.append(String.format(Locale.US, "Amount: %.2f $%n", pizza.getUnitPrice()));
            sb.append("Quantity: ").append(pizza.getQuantity()).append('\n');
            sb.append("--------------------------------\n");

            totalAmount += pizza.getTotalPrice();
        }
        sb.append(String.format(Locale.US, "Total amount: %.2f $%n", totalAmount));
        sb.append("********************************");

        System.out.println(sb);
    }

    private String formatLine(String label, double price) {
        return String.format(Locale.US, "%-20s %.2f $%n", label, price);
    }
}