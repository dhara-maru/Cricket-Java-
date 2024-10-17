package V6;

import java.util.Scanner;

public class CricketGame implements CricketGameOperations {

    public int overs;
    public int totalBalls;
    public int balls;
    public int wickets;
    public int runs;
    public int noballs;
    public int wideballs;
    public int sixers;
    public int fours;
    public Player[] players; // Array of Player objects ✅
    public int currentPlayerIndex;
    Scanner sc = new Scanner(System.in);

    public final int NUMBER_OF_PLAYERS = 11; // 'final' keyword ✅
    public static final int MAX_OVERS = 50;

    public CricketGame() {
        // default bcoz getovers() method uses this class's obj which doesn't have any
        // args
    }

    public CricketGame(int overs) {
        this.overs = overs; // 'this' keyword ✅
        this.totalBalls = overs * 6;
        this.balls = 0;
        this.wickets = 0;
        this.runs = 0;
        this.noballs = 0;
        this.wideballs = 0;
        this.sixers = 0;
        this.fours = 0;
        this.players = new Player[NUMBER_OF_PLAYERS];
        this.currentPlayerIndex = 0;
    }

    // Static block ✅
    static {
        System.out.println("Cricket Game loaded. Max Overs: " + MAX_OVERS);
    }

    public void addPlayer(int index, String name) {
        players[index] = new Player(name);
    }

    public static boolean isValidRun(int run) { // static method
        return run == 1 || run == 2 || run == 3 || run == 4 || run == 6;
    }

    public int getovers() {
        int overs = 0;
        try {
            System.out.print("Enter the number of overs: ");
            overs = sc.nextInt();

            if (overs > 50) {
                throw new InvalidOversException("Only upto 50 overs are allowed!!!");
            }
        } catch (InvalidOversException msg) {
            System.out.println(msg.toString());

            try {
                System.out.print("Please re-enter the number of overs [max : 50 overs]: ");
                overs = sc.nextInt();

                if (overs > 50) {
                    throw new InvalidOversException("Overs must be 50 or less. Ending the prg!");
                }
            } catch (InvalidOversException msg2) {
                System.out.println("Caught exception again: " + msg2.getMessage());
                System.exit(1);
            }
        }
        return overs;
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
                    if (run == 6) {
                        sixers++;
                    } else if (run == 4) {
                        fours++;
                    }
                } else {
                    System.out.println("Enter valid runs. (1/2/3/4/6)");
                }
                break;
            case "wicket":
                currentPlayer.out();
                wickets++;
                currentPlayerIndex++; // Move to next player
                balls++;
                break;
            case "noball":
                System.out.println("NO BALL! You got a Free-hit and extra ball !");
                noballs++;
                runs++;
                break;
            case "wide":
                System.out.println("WIDE BALL! You got an extra run and extra ball !");
                wideballs++;
                runs++;
                break;
            default:
                System.out.println("Enter Valid Operation!!!");
        }
    }

    public void displayScore(MatchType matchType) {
        System.out.println("\n~~~~~~~~~~~~~ Player-Wise Scoreboard ~~~~~~~~~~~~~\n");
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            System.out.println(player.name + ": " + player.runs + " runs,\t\t Balls Faced: " + player.ballsFaced
                    + (player.isOut ? " (Out)" : " (Not Out)"));
            System.out.println("_________________________________________________________________");
        }

        System.out.println("\n");
        matchType.displayType(); // Dynamic method dispatch done ✅
        System.out.println("\nTotal Runs: " + runs + " | Total Wickets: " + wickets);
        System.out.println("Average Runs: " + (float) (runs / totalBalls));
        System.out.println("No Balls: " + noballs + " | Wide Balls: " + wideballs);
        System.out.println("Total Sixers (6s): " + sixers + " | Total Fours (4s): " + fours);
    }
}
