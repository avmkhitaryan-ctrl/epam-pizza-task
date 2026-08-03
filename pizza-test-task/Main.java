public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Pizza pizza = new Pizza("Margarita", PizzaType.CALZONE, 8);
        System.out.println("Name: " + pizza.getName());
        System.out.println("Type: " + String.valueOf(pizza.getType()));
        System.out.println("Quantity: " + pizza.getQuantity());
        Order order = new Order(7717);
        order.addPizza(pizza);
        System.out.println("Order number: " + order.getOrderNumber());
        System.out.println("Customer: " + order.getCustomerNumber());
        System.out.println("Pizza count: " + order.getPizzaCount());
    }
}