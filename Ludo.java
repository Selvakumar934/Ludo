import java.util.*;

class Player {
    private String name;
    private String color;
    private int position;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.position = 0;
    }

    public String getName() { return name; }
    public String getColor() { return color; }
    public int getPosition() { return position; }

    public void updatePosition(int steps) {
        position += steps;
    }

    public void setPosition(int pos) {
        position = pos;
    }
}

class Dice {
    Random rand = new Random();
    public int roll() {
        return rand.nextInt(6) + 1;
    }
}

class Board {
    private Map<Integer, Integer> snakesAndLadders = new HashMap<>();

    public Board() {
        // Ladders
        snakesAndLadders.put(4, 14);
        snakesAndLadders.put(9, 31);
        snakesAndLadders.put(20, 38);
        snakesAndLadders.put(28, 84);

        // Snakes
        snakesAndLadders.put(17, 7);
        snakesAndLadders.put(54, 34);
        snakesAndLadders.put(62, 19);
        snakesAndLadders.put(98, 79);
    }

    public int checkPosition(int pos) {
        return snakesAndLadders.getOrDefault(pos, pos);
    }
}

class Game {
    private List<Player> players = new ArrayList<>();
    private Dice dice = new Dice();
    private Board board = new Board();
    private Scanner sc = new Scanner(System.in);

    public void setupGame() {
        System.out.println("WELCOME TO SNAKES & LADDERS!");

        int n;

        // Allow 2, 3, or 4 players
        while (true) {
            System.out.print("How many players? (2, 3, or 4): ");
            n = sc.nextInt();
            sc.nextLine();

            if (n >= 2 && n <= 4) break;

            System.out.println("Invalid! Enter only 2, 3, or 4.");
        }

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter Player " + i + " name: ");
            String name = sc.nextLine();

            System.out.print("Choose color for Player " + i + ": ");
            String color = sc.nextLine();

            players.add(new Player(name, color));
        }

        System.out.println("\nPlayers:");
        for (Player p : players) {
            System.out.println(p.getName() + " is " + p.getColor());
        }
    }

    public void start() {
        System.out.println("\nGAME STARTS! Reach 100 to win.\n");

        boolean win = false;

        while (!win) {
            for (Player p : players) {
                System.out.println(p.getName() + " press Enter to roll...");
                sc.nextLine();

                int rolled = dice.roll();
                System.out.println(p.getName() + " rolled: " + rolled);

                p.updatePosition(rolled);

                int newPos = board.checkPosition(p.getPosition());
                p.setPosition(newPos);

                System.out.println(p.getName() + " is now at: " + p.getPosition());

                if (p.getPosition() >= 100) {
                    System.out.println("\n🎉 WINNER: " + p.getName() + " (" + p.getColor() + ")");
                    win = true;
                    break;
                }
            }
        }
    }
}

public class Ludo {
    public static void main(String[] args) {
        Game game = new Game();
        game.setupGame();
        game.start();
    }
}
