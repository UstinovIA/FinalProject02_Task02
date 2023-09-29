public class Toy {
    private static int nextId = 1;

    private int id;
    private String name;
    private double weightWinning; // вес выигрыша (не % от 100)
    private int count;

    public Toy(String name, double chanceWinning, int count) {
        this.name = name;
        this.weightWinning = chanceWinning;
        this.id = nextId++;
        this.count = count;
    }

    public Toy(String name) {
        this(name, 0, 0);
    }

    public void setChanceWinning(double chanceWinning) {
        this.weightWinning = chanceWinning;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getChanceWinning() {
        return weightWinning;
    }

    public String getName() {
        return this.name;
    }

    public boolean getToy() {
        if(count > 1)
        {
            count--;
            return true;
        }
        return false;
    }

    public int getCount() {
        return count;
    }
}
