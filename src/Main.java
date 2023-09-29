public class Main {
    public static void main(String[] args) {
        RaffleMachine rm = new RaffleMachine();
        rm.addToy(new Toy("Машинка", 20, 3));
        rm.addToy(new Toy("Домик", 10, 2));
        rm.addToy(new Toy("Кукла", 30, 5));
        rm.addToy(new Toy("Шарик", 40, 10));
        rm.setFileGetPrizes("ListPrizes.txt");
        rm.raffleToys(20);
        rm.getAllPrizes();
    }
}