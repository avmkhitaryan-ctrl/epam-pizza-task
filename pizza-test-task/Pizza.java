public class Pizza {

    private static final int MAX_INGREDIENTS = 8;
    private static final int MIN_NAME_LENGTH = 4;
    private static final int MAX_NAME_LENGTH = 20;
    private static final int MAX_QUANTITY = 10;

    private static final double BASE_PRICE = 1.0;
    private static final double CALZONE_EXTRA_PRICE = 0.5;

    private String name;
    private final boolean nameWasValid;
    private final PizzaType type;
    private final Ingredient[] ingredients;
    private int ingredientCount;
    private final int quantity;

    public Pizza(String name, PizzaType type, int quantity) {
        if (type == null) {
            throw new IllegalArgumentException("Pizza type cannot be null.");
        }
        this.type = type;
        this.quantity = validateQuantity(quantity);
        this.nameWasValid = isNameValid(name);
        this.name = name;
        this.ingredients = new Ingredient[MAX_INGREDIENTS];
        this.ingredientCount = 0;
    }

    private static boolean isNameValid(String candidate) {
        return candidate != null
                && candidate.length() >= MIN_NAME_LENGTH
                && candidate.length() <= MAX_NAME_LENGTH
                && candidate.matches("[a-zA-Z]+");
    }

    private static int validateQuantity(int candidateQuantity) {
        if (candidateQuantity < 1 || candidateQuantity > MAX_QUANTITY) {
            System.out.println("Invalid quantity (" + candidateQuantity
                    + "). Quantity must be between 1 and " + MAX_QUANTITY
                    + ". Value has been adjusted.");
            return Math.max(1, Math.min(candidateQuantity, MAX_QUANTITY));
        }
        return candidateQuantity;
    }
    void applyFallbackNameIfInvalid(int customerNumber, int indexInOrder) {
        if (!nameWasValid) {
            this.name = "customer" + customerNumber + "_" + indexInOrder;
        }
    }

    public boolean addIngredient(Ingredient ingredient) {
        if (ingredient == null) {
            System.out.println("Ingredient cannot be null.");
            return false;
        }
        if (ingredientCount >= MAX_INGREDIENTS) {
            System.out.println("Pizza \"" + name + "\" is already full, cannot add \""
                    + ingredient.getDisplayName() + "\".");
            return false;
        }
        for (int i = 0; i < ingredientCount; i++) {
            if (ingredients[i] == ingredient) {
                System.out.println("Ingredient \"" + ingredient.getDisplayName()
                        + "\" is already in pizza \"" + name + "\". Please check your order again.");
                return false;
            }
        }
        ingredients[ingredientCount++] = ingredient;
        return true;
    }

    public double getUnitPrice() {
        double price = BASE_PRICE + (type == PizzaType.CALZONE ? CALZONE_EXTRA_PRICE : 0.0);
        for (int i = 0; i < ingredientCount; i++) {
            price += ingredients[i].getPrice();
        }
        return price;
    }

    public double getTotalPrice() {
        return getUnitPrice() * quantity;
    }

    public String getName() {
        return name;
    }

    public PizzaType getType() {
        return type;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public int getIngredientCount() {
        return ingredientCount;
    }

    public int getQuantity() {
        return quantity;
    }

    public String displayAttributes(int orderNumber, int customerNumber) {
        return "[" + orderNumber + " : " + customerNumber + " : " + name + " : " + quantity + "]";
    }

    @Override
    public String toString() {
        return "Pizza{name='" + name + "', type=" + type + ", quantity=" + quantity + "}";
    }
}