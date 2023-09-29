import javax.imageio.IIOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RaffleMachine {
    private List<Toy> listToy;
    private Queue<Toy> listPrizes;
    private String fileGetPrizes;

    public RaffleMachine(List<Toy> listToy, String fileGetPrizes) {
        this.listToy = listToy;
        this.listPrizes = new ArrayDeque<>();
        this.fileGetPrizes = fileGetPrizes;
    }

    public RaffleMachine() {
        this(new ArrayList<>(), null);
    }

    public void addToy(Toy toy) {
        if(toy.getCount() > 0) {
            listToy.add(toy);
        }
    }

    public void setFileGetPrizes(String path) {
        this.fileGetPrizes = path;
    }

    private Toy raffle() {
        Random random = new Random();
        double winTicket = random.nextDouble() * getOverallChanceWinning();
        double currentTicket = 0;
        for (Toy toy: listToy) {
            currentTicket += toy.getChanceWinning();
            if(currentTicket > winTicket) {
                if(!toy.getToy()) {
                    if(toy.getCount() <= 0) {
                        throw new RuntimeException("Выпала игрушка, которой уже нет в наличии. " +
                                "Возможно ее количество было изменено после добавления в игровой автомат.");
                    }
                    removeToy(toy);
                }
                return toy;
            }
        }
        return null;
    }

    private double getOverallChanceWinning() {
        double result = 0;
        for(Toy toy: listToy) {
            result += toy.getChanceWinning();
        }
        return result;
    }

    public void raffleToys(int countRaffle) {
        while(countRaffle-->0) {
            Toy winningToy = raffle();
            if(winningToy == null) {
                System.out.println("Все призы разыграны!");
                return;
            }
            listPrizes.add(winningToy);
        }
    }

    public void removeToy(Toy toy) {
        listToy.remove(toy);
    }

    public void getPrize() {
        if(fileGetPrizes != null) {
            try(FileWriter writer = new FileWriter(fileGetPrizes)){
                writer.write(listPrizes.poll().getName());
                writer.write("\n");
                writer.flush();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void getAllPrizes() {
        if(fileGetPrizes != null) {
            try(FileWriter writer = new FileWriter(fileGetPrizes)){
                while(listPrizes.peek() != null) {
                    writer.write(listPrizes.poll().getName());
                    writer.write("\n");
                }
                writer.flush();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("Не введен путь выдачи призов! Используете метод setFileGetPrizes()");
        }
    }
}
