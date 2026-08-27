public class Main {
    public static void main(String[] args) {

        Order order1 = new Order(7717);

        Pizza margarita = new Pizza("Margarita", PizzaType.CALZONE, 2);
        margarita.addIngredient(Ingredient.TOMATO_PASTE);
        margarita.addIngredient(Ingredient.PEPPERONI);
        margarita.addIngredient(Ingredient.GARLIC);
        margarita.addIngredient(Ingredient.BACON);
        order1.addPizza(margarita);

        Pizza pepperoniOro = new Pizza("PepperoniOro", PizzaType.REGULAR, 3);
        pepperoniOro.addIngredient(Ingredient.TOMATO_PASTE);
        pepperoniOro.addIngredient(Ingredient.CHEESE);
        pepperoniOro.addIngredient(Ingredient.PEPPERONI);
        pepperoniOro.addIngredient(Ingredient.OLIVES);
        order1.addPizza(pepperoniOro);

        // Demonstrate duplicate-ingredient protection
        pepperoniOro.addIngredient(Ingredient.CHEESE);

        // Demonstrate "pizza is full" protection
        Pizza fullPizza = new Pizza("StressTest", PizzaType.REGULAR, 1);
        fullPizza.addIngredient(Ingredient.TOMATO_PASTE);
        fullPizza.addIngredient(Ingredient.CHEESE);
        fullPizza.addIngredient(Ingredient.SALAMI);
        fullPizza.addIngredient(Ingredient.BACON);
        fullPizza.addIngredient(Ingredient.GARLIC);
        fullPizza.addIngredient(Ingredient.CORN);
        fullPizza.addIngredient(Ingredient.PEPPERONI);
        fullPizza.addIngredient(Ingredient.OLIVES);
        fullPizza.addIngredient(Ingredient.TOMATO_PASTE); // already full -> prints message

        // Demonstrate invalid-name fallback ("ab" is shorter than 4 characters)
        Pizza badName = new Pizza("ab", PizzaType.REGULAR, 1);
        order1.addPizza(badName);

        order1.printCheck();

        System.out.println();

        // ---- Customer 4372: 12x "BasePZZ" (Regular) -> exceeds max of 10 ----
        Order order2 = new Order(4372);
        Pizza basePZZ = new Pizza("BasePZZ", PizzaType.REGULAR, 12);
        order2.addPizza(basePZZ);
        order2.printCheck();
    }
}