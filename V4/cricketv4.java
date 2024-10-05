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

    public CricketGame(int overs) {
        this.overs = overs;
        this.totalBalls = overs * 6;
        this.balls = 0;
        this.wickets = 0;
        this.runs = 0;
        this.noballs = 0;
        this.wideballs = 0;
        this.players = new Player[11]; // Fixed number of players
        this.currentPlayerIndex = 0;
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
                if (isValidRun(run)) {
                    runs += run;
                    noballs++;
                } else {
                    System.out.println("Enter valid runs. (1/2/3/4/6)");
                }
                break;
            case "wide":
                if (isValidRun(run)) {
                    runs += run;
                    wideballs++;
                } else {
                    System.out.println("Enter valid runs. (1/2/3/4/6)");
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

public class cricketv4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("************************************************");
        System.out.println("          Welcome to the Cricket Game!          ");
        System.out.println("************************************************\n");

        // Match Type Selection
        System.out.print("Select Match Type (1 - ODI, 2 - T20, 3 - IPL): ");
        int matchType = sc.nextInt();
        String matchTypeName = "";
        String[] teamAPlayers = {"Player A1", "Player A2", "Player A3", "Player A4", "Player A5", "Player A6", "Player A7", "Player A8", "Player A9", "Player A10", "Player A11"};
        String[] teamBPlayers = {"Player B1", "Player B2", "Player B3", "Player B4", "Player B5", "Player B6", "Player B7", "Player B8", "Player B9", "Player B10", "Player B11"};
        
        if (matchType == 1) {
            matchTypeName = "ODI";
        } else if (matchType == 2) {
            matchTypeName = "T20";
        } else if (matchType == 3) {
            matchTypeName = "IPL";
        } else {
            System.out.println("Invalid match type selected. Defaulting to ODI.");
            matchTypeName = "ODI";
        }

        // Team Selection
        System.out.println("\nSelect Teams for the Match:");
        System.out.println("1. Team A");
        System.out.println("2. Team B");
        
        System.out.print("Select Team for Batting (1 or 2): ");
        int battingTeamIndex = sc.nextInt();
        int bowlingTeamIndex = battingTeamIndex == 1 ? 2 : 1; // Determine the bowling team

        System.out.print("Enter the number of overs: ");
        int overs = sc.nextInt();

        CricketGame game1 = new CricketGame(overs);
        CricketGame game2 = new CricketGame(overs); // Second game instance for the second innings

        // Adding players to batting team
        if (battingTeamIndex == 1) {
            for (int i = 0; i < teamAPlayers.length; i++) {
                game1.addPlayer(i, teamAPlayers[i]);
            }
        } else {
            for (int i = 0; i < teamBPlayers.length; i++) {
                game1.addPlayer(i, teamBPlayers[i]);
            }
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
            if (ballResult.equals("run") || ballResult.equals("noball") || ballResult.equals("wide")) {
                System.out.print("Enter runs: ");
                run = sc.nextInt();
            }

            game1.playBall(ballResult, run);

            System.out.println("------------------------------------------------");
            System.out.println("Total Runs: " + game1.runs + " | Total Wickets: " + game1.wickets + " | Balls Bowled: " + game1.balls + "/" + game1.totalBalls);
            System.out.println("------------------------------------------------\n");
        }

        // FINAL SCOREBOARD for First Innings
        System.out.println("\n************************************************");
        System.out.println("            First Innings Ended. Final Score:          ");
        System.out.println("************************************************");
        game1.displayScore();

        // Adding players to second innings (the other team)
        if (bowlingTeamIndex == 1) {
            for (int i = 0; i < teamAPlayers.length; i++) {
                game2.addPlayer(i, teamAPlayers[i]);
            }
        } else {
            for (int i = 0; i < teamBPlayers.length; i++) {
                game2.addPlayer(i, teamBPlayers[i]);
            }
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
            if (ballResult.equals("run") || ballResult.equals("noball") || ballResult.equals("wide")) {
                System.out.print("Enter runs: ");
                run = sc.nextInt();
            }

            game2.playBall(ballResult, run);

            System.out.println("------------------------------------------------");
            System.out.println("Total Runs: " + game2.runs + " | Total Wickets: " + game2.wickets + " | Balls Bowled: " + game2.balls + "/" + game2.totalBalls);
            System.out.println("------------------------------------------------\n");
        }

        // FINAL SCOREBOARD for Second Innings
        System.out.println("\n************************************************");
        System.out.println("            Second Innings Ended. Final Score:          ");
        System.out.println("************************************************");
        game2.displayScore();

        // Announce the winning team based on total runs scored
        String winningTeam = game1.runs > game2.runs ? (battingTeamIndex == 1 ? "Team A" : "Team B") : (battingTeamIndex == 1 ? "Team B" : "Team A");
        System.out.println("\n********************************************");
        System.out.println("             Winning Team: " + winningTeam);
        System.out.println("********************************************");
    }
}
