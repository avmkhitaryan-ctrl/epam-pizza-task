import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            boolean placeAnotherOrder = true;
            while (placeAnotherOrder) {
                Order order = buildOrderFromConsole(reader);
                order.printCheck();
                String fileName = "receipt_" + order.getOrderNumber() + ".txt";
                order.printCheckToFile(fileName);

                System.out.println();
                placeAnotherOrder = readYesNo(reader, "Place another order? (y/n): ");
            }

            System.out.println("Thank you for ordering at Pizzeria Palmetto!");
        } catch (EOFException e) {
            System.out.println();
            System.out.println("No more input received. Exiting.");
        }
    }

    private static Order buildOrderFromConsole(BufferedReader reader) throws IOException {
        int customerNumber = readInt(reader, "Enter customer number: ");
        Order order = new Order(customerNumber);

        boolean addAnotherPizza = true;
        while (addAnotherPizza) {
            String name = readLine(reader, "Enter pizza name (4-20 Latin letters, or leave blank for auto-name): ");
            PizzaType type = readPizzaType(reader, "Enter pizza type (REGULAR/CALZONE): ");
            int quantity = readInt(reader, "Enter quantity (1-10): ");

            Pizza pizza = new Pizza(name, type, quantity);

            String ingredientsLine = readLine(reader,
                    "Enter ingredients separated by comma (e.g. TOMATO_PASTE,CHEESE,BACON), or leave blank: ");
            addIngredientsFromInput(pizza, ingredientsLine);

            order.addPizza(pizza);

            addAnotherPizza = readYesNo(reader, "Add another pizza to this order? (y/n): ");
        }

        return order;
    }

    private static void addIngredientsFromInput(Pizza pizza, String ingredientsLine) {
        if (ingredientsLine == null || ingredientsLine.isBlank()) {
            return;
        }
        String[] tokens = ingredientsLine.split(",");
        for (String token : tokens) {
            String normalized = token.trim().toUpperCase().replace(' ', '_');
            if (normalized.isEmpty()) {
                continue;
            }
            try {
                Ingredient ingredient = Ingredient.valueOf(normalized);
                pizza.addIngredient(ingredient);
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown ingredient \"" + token.trim() + "\" was skipped.");
            }
        }
    }

    private static String readLine(BufferedReader reader, String prompt) throws IOException {
        System.out.print(prompt);
        String line = reader.readLine();
        if (line == null) {
            throw new EOFException("No more input available.");
        }
        return line.trim();
    }

    private static int readInt(BufferedReader reader, String prompt) throws IOException {
        while (true) {
            String line = readLine(reader, prompt);
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static PizzaType readPizzaType(BufferedReader reader, String prompt) throws IOException {
        while (true) {
            String line = readLine(reader, prompt);
            try {
                return PizzaType.valueOf(line.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Please enter either REGULAR or CALZONE.");
            }
        }
    }

    private static boolean readYesNo(BufferedReader reader, String prompt) throws IOException {
        while (true) {
            String line = readLine(reader, prompt).toLowerCase();
            if (line.equals("y") || line.equals("yes")) {
                return true;
            }
            if (line.equals("n") || line.equals("no")) {
                return false;
            }
            System.out.println("Please answer y or n.");
        }
    }
}