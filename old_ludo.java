import java.util.*;

class ludo
{
    public static int snakes()
    {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String col1 = "", col2 = "", col3 = "";
        String pl1 = "", pl2 = "", pl3 = "";
        
        int p1 = 0, p2 = 0, p3 = 0;
        int players;

        System.out.println("WELCOME TO SNAKES AND LADDER :)");
        System.out.print("How many players are going to play (2 or 3): ");
        players = sc.nextInt();
        sc.nextLine();  // Clear buffer

        // ---------------- INPUT FOR PLAYERS ----------------
        if (players == 2)
        {
            System.out.print("Enter Player 1 name: ");
            pl1 = sc.nextLine();

            System.out.print("Enter Player 2 name: ");
            pl2 = sc.nextLine();

            System.out.print("Choose colour for Player 1 (Blue/Red): ");
            col1 = sc.nextLine();

            if (col1.equalsIgnoreCase("Blue"))
                col2 = "Red";
            else if (col1.equalsIgnoreCase("Red"))
                col2 = "Blue";
            else
            {
                System.out.println("Invalid colour! Setting default colours.");
                col1 = "Blue";
                col2 = "Red";
            }

            System.out.println(pl1 + " is " + col1);
            System.out.println(pl2 + " is " + col2);
        }
        else if (players == 3)
        {
            System.out.print("Enter Player 1 name: ");
            pl1 = sc.nextLine();

            System.out.print("Enter Player 2 name: ");
            pl2 = sc.nextLine();

            System.out.print("Enter Player 3 name: ");
            pl3 = sc.nextLine();

            System.out.print("Choose colour for Player 1 (Blue/Red/Yellow): ");
            col1 = sc.nextLine();

            System.out.print("Choose colour for Player 2: ");
            col2 = sc.nextLine();

            // Auto-set Player 3 colour
            List<String> colours = Arrays.asList("Blue", "Red", "Yellow", "Green");
            for (String c : colours)
            {
                if (!c.equalsIgnoreCase(col1) && !c.equalsIgnoreCase(col2))
                {
                    col3 = c;
                    break;
                }
            }

            System.out.println(pl1 + " is " + col1);
            System.out.println(pl2 + " is " + col2);
            System.out.println(pl3 + " is " + col3);
        }
        else
        {
            System.out.println("Invalid number of players!");
            return 0;
        }

        // ---------------- GAME START ----------------
        System.out.println("\nGAME STARTS NOW!");
        System.out.println("Reach 100 to WIN.\n");

        boolean win = false;

        while (!win)
        {
            // --------------- PLAYER 1 TURN -----------------
            System.out.println(pl1 + " press Enter to roll dice...");
            sc.nextLine();
            int dice = rand.nextInt(6) + 1;
            System.out.println(pl1 + " rolled: " + dice);

            if (p1 + dice <= 100)
                p1 += dice;

            int newp1 = checkSnakeLadder(p1);
            if (newp1 > p1)
                System.out.println("🎉 Ladder! " + p1 + " → " + newp1);
            else if (newp1 < p1)
                System.out.println("🐍 Snake! " + p1 + " → " + newp1);
            p1 = newp1;

            System.out.println(pl1 + " is now at: " + p1);

            if (p1 == 100)
            {
                System.out.println("\n🎉 WINNER: " + pl1);
                break;
            }

            // --------------- PLAYER 2 TURN -----------------
            System.out.println(pl2 + " press Enter to roll dice...");
            sc.nextLine();
            dice = rand.nextInt(6) + 1;
            System.out.println(pl2 + " rolled: " + dice);

            if (p2 + dice <= 100)
                p2 += dice;

            int newp2 = checkSnakeLadder(p2);
            if (newp2 > p2)
                System.out.println("🎉 Ladder! " + p2 + " → " + newp2);
            else if (newp2 < p2)
                System.out.println("🐍 Snake! " + p2 + " → " + newp2);
            p2 = newp2;

            System.out.println(pl2 + " is now at: " + p2);

            if (p2 == 100)
            {
                System.out.println("\n🎉 WINNER: " + pl2);
                break;
            }

            // --------------- PLAYER 3 TURN -----------------
            if (players == 3)
            {
                System.out.println(pl3 + " press Enter to roll dice...");
                sc.nextLine();
                dice = rand.nextInt(6) + 1;
                System.out.println(pl3 + " rolled: " + dice);

                if (p3 + dice <= 100)
                    p3 += dice;

                int newp3 = checkSnakeLadder(p3);
                if (newp3 > p3)
                    System.out.println("🎉 Ladder! " + p3 + " → " + newp3);
                else if (newp3 < p3)
                    System.out.println("🐍 Snake! " + p3 + " → " + newp3);
                p3 = newp3;

                System.out.println(pl3 + " is now at: " + p3);

                if (p3 == 100)
                {
                    System.out.println("\n🎉 WINNER: " + pl3);
                    break;
                }
            }
        }

        return 0;
    }

    // -------- SNAKES & LADDERS --------
    public static int checkSnakeLadder(int pos)
    {
        switch(pos)
        {
            case 4: return 14;
            case 9: return 31;
            case 20: return 38;
            case 28: return 84;

            case 17: return 7;
            case 54: return 34;
            case 62: return 19;
            case 98: return 79;
        }
        return pos;
    }

    public static void main(String[] args)
    {
        snakes();
    }
}
