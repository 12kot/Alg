import java.util.ArrayList;
import java.util.List;

public class Owner extends Person {
    private ArrayList<Item> items = new ArrayList<>();

    public ArrayList<Item> getItems() {
        return items;
    }

    public Owner() {

    }

    public boolean AddItem(Item item) {
        if(item == null) return false;

        items.add(item);

        return true;
    }

    public boolean RemoveItem(Item item) {
        return items.remove(item);
    }

    public void DisplayItems() {
        if(items.size() == 0) {
            System.out.println("Вещи отсутствуют.");
            return;
        }

        int i = 1;
        System.out.println("№ | Вес | Стоимость");
        for (Item item : items) {
            System.out.println((i++) + " | " + item.getWeight() + " | " + item.getPrice());
        }
    }
}
