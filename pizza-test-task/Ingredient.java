public enum Ingredient {
    TOMATO_PASTE(1.0),
    CHEESE(1.0),
    SALAMI(1.5),
    BACON(1.2),
    GARLIC(0.3),
    CORN(0.7),
    PEPPERONI(0.6),
    OLIVES(0.5);

    private final double price;

    Ingredient(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
    public String getDisplayName() {
        String lower = name().toLowerCase().replace('_', ' ');
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}