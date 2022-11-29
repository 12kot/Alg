import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Thief extends Person{
    private Bag bag;

    public Thief(Bag bag) {
        this.bag = bag;
    }

    public boolean StealItem(Owner person) {

        int n = person.getItems().size()+1;
        int k = this.bag.maxWeight + 1 - this.bag.getWeight();
        Bag[][] bp = new Bag[n][k];

        System.out.println(n + " " + k);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                if (i == 0 || j == 0) { //нулевую строку и столбец заполняем нулями
                    bp[i][j] = new Bag(new Item[]{}, 0);
                } else if (i == 1) {
                    /*первая строка заполняется просто: первый предмет кладём или не кладём в зависимости от веса*/
                    bp[1][j] = person.getItems().get(0).getWeight() <= j ?
                            new Bag(new Item[]{person.getItems().get(0)}, person.getItems().get(0).getPrice())
                            : new Bag(new Item[]{}, 0);
                } else {
                    if (person.getItems().get(i - 1).getWeight() > j) //если очередной предмет не влезает в рюкзак,
                        bp[i][j] = bp[i - 1][j];    //записываем предыдущий максимум
                    else {
                        /*рассчитаем цену очередного предмета + максимальную цену для (максимально возможный для рюкзака вес − вес предмета)*/
                        int newPrice = person.getItems().get(i - 1).getPrice() + bp[i - 1][j - person.getItems().get(i - 1)
                                .getWeight()].Price;
                        if (bp[i - 1][j].Price > newPrice) //если предыдущий максимум больше
                            bp[i][j] = bp[i - 1][j]; //запишем его
                        else {
                            /*иначе фиксируем новый максимум: текущий предмет + стоимость свободного пространства*/
                            bp[i][j] = new Bag(Stream.concat(Arrays.stream(new Item[]{person.getItems().get(i - 1)}),
                                    Arrays.stream(bp[i - 1][j - person.getItems().get(i - 1).getWeight()].getItems())).toArray(Item[]::new), newPrice);

                        }
                    }
                }
            }
        }


        if(bp[n-1][k-1].getItems().length == 0) return false;
        System.out.print(bp[n-1][k-1]);

        for (Item item :
                bp[n-1][k-1].getItems()) {
            person.RemoveItem(item);
        }

        this.bag.AddItem(bp[n-1][k-1]);

        return true;
    }

    private Item ChooseItem(Owner person) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер предмета: ");
        int n = scanner.nextInt(); n--;

        if(n < 0 || n >= person.getItems().size()) return null;

        return person.getItems().get(n);
    }

    public void ReturnItems(Owner person) {
        for (Item item:
             bag.getItems()) {
            person.AddItem(item);
        }

        bag.setItems(new ArrayList<>());
        bag.setWeight(0);
    }
    public void DisplayBag() {
        System.out.println(bag);
    }
}
