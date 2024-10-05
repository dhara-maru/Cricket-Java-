// V3 | everything from prev v + Static method. 

import java.util.Scanner;

class Player {

    String name;
    int runs;
    int ballsFaced;
    boolean isOut;

    public Player(String name) {
        this.name = name;
        this.runs = 0;
        this.ballsFaced = 0;
        this.isOut = false;
    }

    public void scoreRuns(int run) {
        runs += run;
        ballsFaced++;
    }

    public void out() {
        isOut = true;
        ballsFaced++;
    }
}

class CricketGame {

    int overs;
    int totalBalls;
    int balls;
    int wickets;
    int runs;
    int noballs;
    int wideballs;
    Player[] players;
    int currentPlayerIndex;

    public CricketGame(int overs, int playerCount) {
        this.overs = overs;
        this.totalBalls = overs * 6;
        this.balls = 0;
        this.wickets = 0;
        this.runs = 0;
        this.noballs = 0;
        this.wideballs = 0;
        this.players = new Player[playerCount];
        this.currentPlayerIndex = 0;
    }

    public void addPlayer(int index, String name) {
        players[index] = new Player(name);
    }

    public static boolean isValidRun(int run) {     //static method
        return run == 1 || run == 2 || run == 3 || run == 4 || run == 6;
    }

    public void playBall(String ballResult, int run) {
        if (currentPlayerIndex >= players.length) {
            System.out.println("All players are out!");
            return;
        }

        Player currentPlayer = players[currentPlayerIndex];
        switch (ballResult) {
            case "run":
                if (isValidRun(run)) {
                    currentPlayer.scoreRuns(run);
                    runs += run;
                    balls++;
                } else {
                    System.out.println("Enter valid runs. (0/1/2/3/4/6)");
                }
                break;
            case "wicket":
                currentPlayer.out();
                wickets++;
                currentPlayerIndex++; // Move to next player, but only if more players are available
                balls++;
                break;
            case "noball":
                if (isValidRun(run)) {
                    runs += run;
                    noballs++;
                } else {
                    System.out.println("Enter valid runs. (0/1/2/3/4/6)");
                }
                break;
            case "wide":
                if (isValidRun(run)) {
                    runs += run;
                    wideballs++;
                } else {
                    System.out.println("Enter valid runs. (0/1/2/3/4/6)");
                }
                break;
            default:
                System.out.println("Enter Valid Operation!!!");
        }
    }

    public void displayScore() {
        System.out.println("\n~~~~~~~~~~~~~ Player-Wise Scoreboard ~~~~~~~~~~~~~\n");
        for (Player player : players) {
            if (player != null) {
                System.out.println(player.name + ": " + player.runs + " runs,\t\t Balls Faced: " + player.ballsFaced + (player.isOut ? " (Out)" : " (Not Out)"));
                System.out.println("_________________________________________________________________");
            }
        }
        System.out.println("\nTotal Runs: " + runs + " | Total Wickets: " + wickets);
        System.out.println("No Balls: " + noballs + " | Wide Balls: " + wideballs);
    }

}

public class cricketv3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("************************************************");
        System.out.println("          Welcome to the Cricket Game!          ");
        System.out.println("************************************************\n");

        System.out.print("Enter the number of overs: ");
        int overs = sc.nextInt();
        System.out.print("Enter the number of players: ");
        int playerCount = sc.nextInt();
        sc.nextLine();

        CricketGame game = new CricketGame(overs, playerCount);

        // Adding players
        for (int i = 0; i < playerCount; i++) {
            System.out.print("Enter player " + (i + 1) + " name: ");
            String playerName = sc.nextLine();
            game.addPlayer(i, playerName);
        }

        System.out.println("\n--- Game Starts Now! ---\n");

        while (game.balls < game.totalBalls && game.wickets < playerCount) {
            System.out.print("Enter result of ball (run, wicket, no ball, wide, exit): ");
            String ballResult = sc.next().toLowerCase();

            if (ballResult.equals("exit")) {
                break;
            }

            int run = 0;
            if (ballResult.equals("run") || ballResult.equals("noball") || ballResult.equals("wide")) {
                System.out.print("Enter runs: ");
                run = sc.nextInt();
            }

            game.playBall(ballResult, run);

            System.out.println("------------------------------------------------");
            System.out.println("Total Runs: " + game.runs + " | Total Wickets: " + game.wickets + " | Balls Bowled: " + game.balls + "/" + game.totalBalls);
            System.out.println("------------------------------------------------\n");
        }

        // FINAL SCOREBOARD
        System.out.println("\n************************************************");
        System.out.println("            Innings Ended. Final Score:          ");
        System.out.println("************************************************");
        game.displayScore();
    }
}
