public class Order {
    private static int nextOrderNumber = 10000;
    private final int orderNumber;
    private final int customerNumber;
    private final Pizza[] pizzas;
    private int pizzaCount;

    public Order(int customerNumber) {
        this.customerNumber = customerNumber;
        this.orderNumber = ++nextOrderNumber;
        this.pizzas = new Pizza[10];
        this.pizzaCount = 0;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public int getCustomerNumber() {
        return this.customerNumber;
    }

    public Pizza[] getPizzas() {
        return this.pizzas;
    }

    public int getPizzaCount() {
        return this.pizzaCount;
    }

    public boolean addPizza(Pizza pizza) {
        if (this.pizzaCount >= this.pizzas.length) {
            return false;
        } else {
            this.pizzas[this.pizzaCount++] = pizza;
            return true;
        }
    }
}
