public class Pizza {
    private String name;
    private PizzaType type;
    private Ingredient[] ingredients;
    private int quantity;

    public Pizza(String name, PizzaType type, int quantity) {
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.ingredients = new Ingredient[8];
    }

    public String getName() {
        return this.name;
    }

    public PizzaType getType() {
        return this.type;
    }

    public Ingredient[] getIngredients() {
        return this.ingredients;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
