import java.util.ArrayList;
import java.util.Arrays;

public class Bag {
    private ArrayList<Item> items = new ArrayList<>();
    public int Price;
    public int maxWeight;
    private int Weight;

    public Bag(int weight) {
        maxWeight = weight;
    }

    public Bag(Item[] itemList, int price) {
        items.addAll(Arrays.asList(itemList));

        Price = price;
    }

    public Item[] getItems() {
        return items.toArray(Item[]::new);
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }
    public int getWeight() { return Weight;}

    public boolean AddItem(Bag bag) {
        this.items.addAll(bag.items);

        for (Item item : bag.items) {
            Weight += item.getWeight();
        }

        return true;
    }

    @Override
    public String toString() {
        if(items.size() == 0)
            return "Мешок пуст.";

        StringBuilder str = new StringBuilder("№ | Вес | Стоимость\n");
        int i = 1;
        for (Item item :
             items) {
            str.append(i++).append(" | ").append(item.getWeight()).append(" | ").append(item.getPrice()).append("\n");
        }

        return str.toString();
    }
}
