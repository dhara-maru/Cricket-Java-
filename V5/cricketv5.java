// V5 : Implemented Inheritance (Matchtype : ODI, T20, IPL), Interface for CricketGame class's methods, Abstract class MatchType and DMD (uses MatchType dynamically based on user input matchtype.)

import java.util.Scanner;

interface CricketGameOperations { // Interface ✅ (CricketGame class's methods)
    void addPlayer(int index, String name);

    void playBall(String ballResult, int run);

    void displayScore(MatchType matchType);
    // boolean isValidRun(int run);
}

class Player {

    String name;
    int runs;
    int ballsFaced;
    boolean isOut;

    public Player(String name) { // Constructor ✅
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

// Base & Abstract class for Match Types ✅
abstract class MatchType {
    abstract void displayType();
}

// Subclass for ODI
class ODI extends MatchType {
    public void displayType() {
        System.out.println("Selected Match Type : ODI");
    }
}

// Subclass for T20
class T20 extends MatchType {
    public void displayType() {
        System.out.println("Selected Match Type : T20");
    }
}

// Subclass for IPL
class IPL extends MatchType {
    public void displayType() {
        System.out.println("Selected Match Type : IPL");
    }
}

class CricketGame implements CricketGameOperations {

    int overs;
    int totalBalls;
    int balls;
    int wickets;
    int runs;
    int noballs;
    int wideballs;
    int sixers;
    int fours;
    Player[] players; // Array of Player objects ✅
    int currentPlayerIndex;

    final int NUMBER_OF_PLAYERS = 11; // 'final' keyword ✅
    static final int MAX_OVERS = 50;

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

public class cricketv5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ODI mtype = new ODI();

        System.out.println("************************************************");
        System.out.println("          Welcome to the Cricket Game!          ");
        System.out.println("************************************************\n");

        // Match Type Selection
        MatchType matchTypenew = null;
        System.out.print("Select Match Type (1 - ODI, 2 - T20, 3 - IPL): ");
        int matchType = sc.nextInt();
        String matchTypeName = "";
        String[] teamAPlayers = new String[11];
        String[] teamBPlayers = new String[11];
        String teamA = "";
        String teamB = "";
        teamAPlayers = new String[] { "Virat Kohli", "Rohit Sharma", "Shubman Gill", "KL Rahul", "Hardik Pandya",
                "Ravindra Jadeja", "Jasprit Bumrah", "Mohammed Shami", "Bhuvneshwar Kumar", "Rishabh Pant",
                "Yuzvendra Chahal" };
        teamBPlayers = new String[] { "David Warner", "Steve Smith", "Pat Cummins", "Glenn Maxwell",
                "Mitchell Starc", "Travis Head", "Kane Richardson", "Josh Hazlewood", "Adam Zampa",
                "Marcus Stoinis", "Marnus Labuschagne" };
        String[] InternationalTeams = { "India", "Australia", "England", "Pakistan", "South Africa", "New Zealand",
                "West Indies", "Sri Lanka" };
        String[] iplTeams = { "Chennai Super Kings", "Mumbai Indians", "Royal Challengers Bangalore",
                "Kolkata Knight Riders", "Delhi Capitals", "Sunrisers Hyderabad", "Rajasthan Royals",
                "Gujarat Titans" };

        // Team Names and Players based on Match Type
        if (matchType == 1) { // ODI
            matchTypenew = new ODI();
            matchTypeName = "ODI";

            System.out.println("\nAvailable ODI Teams:");
            for (int i = 0; i < InternationalTeams.length; i++) {
                System.out.println((i + 1) + ". " + InternationalTeams[i]);
            }
            System.out.print("Select Team A for Batting (1 to " + InternationalTeams.length + "): ");
            int teamAIndex = sc.nextInt() - 1;
            teamA = InternationalTeams[teamAIndex];

            System.out.print("Select Team B for Bowling (1 to " + InternationalTeams.length + "): ");
            int teamBIndex = sc.nextInt() - 1;
            teamB = InternationalTeams[teamBIndex];

        } else if (matchType == 2) { // T20

            matchTypenew = new T20();

            matchTypeName = "T20";

            System.out.println("\nAvailable T20 Teams:");
            for (int i = 0; i < InternationalTeams.length; i++) {
                System.out.println((i + 1) + ". " + InternationalTeams[i]);
            }
            System.out.print("Select Team A for Batting (1 to " + InternationalTeams.length + "): ");
            int teamAIndex = sc.nextInt() - 1;
            teamA = InternationalTeams[teamAIndex];

            System.out.print("Select Team B for Bowling (1 to " + InternationalTeams.length + "): ");
            int teamBIndex = sc.nextInt() - 1;
            teamB = InternationalTeams[teamBIndex];

        } else if (matchType == 3) { // IPL
            matchTypenew = new IPL();

            matchTypeName = "IPL";

            System.out.println("\nAvailable IPL Teams:");
            for (int i = 0; i < iplTeams.length; i++) {
                System.out.println((i + 1) + ". " + iplTeams[i]);
            }
            System.out.print("Select Team A for Batting (1 to " + iplTeams.length + "): ");
            int teamAIndex = sc.nextInt() - 1;
            teamA = iplTeams[teamAIndex];

            System.out.print("Select Team B for Bowling (1 to " + iplTeams.length + "): ");
            int teamBIndex = sc.nextInt() - 1;
            teamB = iplTeams[teamBIndex];

        } else {
            System.out.println("Invalid match type selected. Exiting.");
            return;
        }

        // Get number of overs
        System.out.print("Enter the number of overs: ");
        int overs = sc.nextInt();

        CricketGame game1 = new CricketGame(overs);
        CricketGame game2 = new CricketGame(overs);

        // Adding players to batting team
        for (int i = 0; i < teamAPlayers.length; i++) {
            game1.addPlayer(i, teamAPlayers[i]);
        }

        System.out.println("\n--- First Innings Starts Now! ---\n");

        // First Innings
        while (game1.balls < game1.totalBalls && game1.wickets < 11) {
            System.out.print("Enter result of ball (run, wicket, no ball, wide, exit): ");
            String ballResult = sc.next().toLowerCase();

            if (ballResult.equals("exit")) {
                break;
            }

            int run = 0;
            if (ballResult.equals("run")) {
                System.out.print("Enter runs: ");
                run = sc.nextInt();
            }

            game1.playBall(ballResult, run);

            System.out.println("------------------------------------------------");
            System.out.println("Total Runs: " + game1.runs + " | Total Wickets: " + game1.wickets + " | Balls Bowled: "
                    + game1.balls + "/" + game1.totalBalls);
            System.out.println("------------------------------------------------\n");
        }

        // Final Scoreboard for Team A
        System.out.println("\n************************************************");
        System.out.println("            Innings Ended. Team A Score:          ");
        System.out.println("************************************************");
        game1.displayScore(matchTypenew);

        // Adding players to bowling team
        for (int i = 0; i < teamBPlayers.length; i++) {
            game2.addPlayer(i, teamBPlayers[i]);
        }

        System.out.println("\n--- Second Innings Starts Now! ---\n");

        // Second Innings
        while (game2.balls < game2.totalBalls && game2.wickets < 11) {
            System.out.print("Enter result of ball (run, wicket, no ball, wide, exit): ");
            String ballResult = sc.next().toLowerCase();

            if (ballResult.equals("exit")) {
                break;
            }

            int run = 0;
            if (ballResult.equals("run")) {
                System.out.print("Enter runs: ");
                run = sc.nextInt();
            }

            game2.playBall(ballResult, run);

            System.out.println("------------------------------------------------");
            System.out.println("Total Runs: " + game2.runs + " | Total Wickets: " + game2.wickets + " | Balls Bowled: "
                    + game2.balls + "/" + game2.totalBalls);
            System.out.println("------------------------------------------------\n");
        }

        // FINAL SCOREBOARD for Team B
        System.out.println("\n************************************************");
        System.out.println("            Innings Ended. Team B Score:          ");
        System.out.println("************************************************");
        game2.displayScore(matchTypenew);

        // show the winning team
        System.out.println("\n************************************************");
        System.out.print("   Match Result:   ");

        if (game1.runs > game2.runs) {
            System.out.println(teamA + " wins by " + (game1.runs - game2.runs) + " runs!");
        } else if (game1.runs < game2.runs) {
            System.out.println(teamB + " wins by " + (11 - game2.wickets) + " wickets!");
        } else {
            System.out.println("The match is a tie!");
        }
        System.out.println("************************************************");

    }
}
