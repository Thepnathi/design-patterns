// • Lets you build complex objects using simple objects and using a step by step approach
// • The Builder class builds the final object step by step
// * Creational Pattern 

// Suppose you want to build a house. It will need various classes such as roof, door,
//  windows. However, if we want to build a bigger house or one with different properties. How can we do this.

// One way is to extend the classes. But this can get too big,
//  if the window has to be bigger, different shape, style and so on.
// Another way is to create House with many parameters. But most time a lots of
//  parameter is not needed and will be ignored.

// The diagram is big
// https://www.tutorialspoint.com/design_pattern/builder_pattern.htm

// ======================================================================

import java.util.ArrayList;
import java.util.List;

// Interface and concrete class to represent food item and packaging
interface Item {
    public String name();
    public Packing packing();
    public float price();
}

interface Packing {
    public String pack();
}

class Wrapper implements Packing {
    @Override
    public String pack() { return "Wrapper"; }
}

class Bottle implements Packing {
    @Override
    public String pack() { return "Bottle "; }
}

// Abstract classes implementing the item interface providing default functionalities
abstract class Burger implements Item {
    @Override 
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}

abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();
}

// The concrete class or the real burger and drink products
class VegBurger extends Burger {
    @Override
    public float price() {
        return 25.0f;
    }
    
    @Override
    public String name() {
        return "Veg Burger";
    }
}

class ChickenBurger extends Burger {
    @Override
    public float price() {
        return 50.0f;
    }
    
    @Override
    public String name() {
        return "Chicken Filo Burger";
    }
}

class Coke extends ColdDrink {
    @Override
    public float price() {
        return 10.0f;
    }
    
    @Override
    public String name() {
        return "CocaCola";
    }
}

class Pepsi extends ColdDrink {
    @Override
    public float price() {
        return 9.0f;
    }
    
    @Override
    public String name() {
        return "Pepsi";
    }
}

// The meal class that will consists of the different food items
class Meal {
    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float getCost() {
        float cost = 0.0f;

        for (Item item : items) {
            cost += item.price();
        }
        return cost;
    }

    public void showItems() {
        for (Item item : items) {
            System.out.println("Item : " + item.name());
            System.out.println(", Packing : " + item.packing().pack());
            System.out.println(", Price: " + item.price());
        }
    }
}

// The MealBuilder class the main builder class
class MealBuilder {
    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegBurger());
        meal.addItem(new Pepsi());
        return meal;
    }

    public Meal prepareMeatMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Coke());
        return meal;
    }
}

public class Builder {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        vegMeal.showItems();
        System.out.println("Total cost: " + vegMeal.getCost());
    }
}
