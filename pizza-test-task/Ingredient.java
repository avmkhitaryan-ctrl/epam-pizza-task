public enum Ingredient {
    TOMATO_PASTE((double)1.0F),
    CHEESE((double)1.0F),
    SALAMI((double)1.5F),
    BACON(1.2),
    GARLIC(0.3),
    CORN(0.7),
    PEPPERONI(0.6),
    OLIVES((double)0.5F);

    private final double price;

    private Ingredient(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}