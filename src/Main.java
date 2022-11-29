import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Thief thief = new Thief(new Bag(4));
        Owner owner = new Owner();

        while (true) {
            System.out.print("\nВыберите действие: ");
            int action = scanner.nextInt();

            switch (action) {
                case 0:
                    Menu();
                    break;
                case 1:
                    System.out.print("Введите вес предмета: ");
                    int weight = scanner.nextInt();
                    System.out.print("Введите цену предмета: ");
                    int price = scanner.nextInt();

                    if(owner.AddItem(new Item(weight, price)))
                        System.out.println("Вещь добавлена.");
                    else System.out.println("Вещь не добавлена.");
                    break;
                case 2:
                    if (thief.StealItem(owner))
                        System.out.println("Вещь украдена.");
                    else System.out.println("Вещь не украдена.");
                    break;
                case 3:
                    owner.DisplayItems();
                    break;
                case 4:
                    thief.DisplayBag();
                    break;
                case 5:
                    thief.ReturnItems(owner);
                    System.out.println("Вы погладили Вора, он вернул все украденные вещи.");
                    break;
                case 6:
                    System.out.println("""
                                                    Вы проиграли..
                            Вы оказались слишком жестоким человеком. Вас приговорили к смертной казни.
                            Вор празднует..""");
                    return;
                default:
                    System.out.println("У тебя свободы нет. Выбирай из того, что есть.");
                    break;
            }
        }
    }

    private static void Menu() {
        System.out.println("""
                0. Меню.
                1. Добавить вещь овнеру.
                2. Украсть вещь.
                3. Что у овнера в корзине?
                4. Что у Вора в мешке?
                5. Погладить Вора.
                6. Убить Вора.""");
    }
}