// V4 : Assistment's "bare minimum" part is done but OOPS concepts are not implemented yet. 

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
    int sixers;
    int fours;
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
        this.sixers = 0;
        this.fours = 0;
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

    public void displayScore() {
        System.out.println("\n~~~~~~~~~~~~~ Player-Wise Scoreboard ~~~~~~~~~~~~~\n");
        for (Player player : players) {
            if (player != null) {
                System.out.println(player.name + ": " + player.runs + " runs,\t\t Balls Faced: " + player.ballsFaced + (player.isOut ? " (Out)" : " (Not Out)"));
                System.out.println("_________________________________________________________________");
            }
        }
        System.out.println("\nTotal Runs: " + runs + " | Total Wickets: " + wickets);
        System.out.println("Average Runs: " + (float) (runs / totalBalls));
        System.out.println("No Balls: " + noballs + " | Wide Balls: " + wideballs);
        System.out.println("Total Sixers (6s): " + sixers + " | Total Fours (4s): " + fours);
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
        String[] teamAPlayers = new String[11];
        String[] teamBPlayers = new String[11];
        String teamA = "";
        String teamB = "";

        // Team Names and Players based on Match Type
        if (matchType == 1) { // ODI
            matchTypeName = "ODI";
            String[] odiTeams = {"India", "Australia", "England", "Pakistan", "South Africa", "New Zealand", "West Indies", "Sri Lanka"};
            System.out.println("\nAvailable ODI Teams:");
            for (int i = 0; i < odiTeams.length; i++) {
                System.out.println((i + 1) + ". " + odiTeams[i]);
            }
            System.out.print("Select Team A for Batting (1 to " + odiTeams.length + "): ");
            int teamAIndex = sc.nextInt() - 1;
            teamA = odiTeams[teamAIndex];

            System.out.print("Select Team B for Bowling (1 to " + odiTeams.length + "): ");
            int teamBIndex = sc.nextInt() - 1;
            teamB = odiTeams[teamBIndex];

            // Fixed players for ODI
            teamAPlayers = new String[]{"Virat Kohli", "Rohit Sharma", "Shubman Gill", "KL Rahul", "Hardik Pandya", "Ravindra Jadeja", "Jasprit Bumrah", "Mohammed Shami", "Bhuvneshwar Kumar", "Rishabh Pant", "Yuzvendra Chahal"};
            teamBPlayers = new String[]{"David Warner", "Steve Smith", "Pat Cummins", "Glenn Maxwell", "Mitchell Starc", "Travis Head", "Kane Richardson", "Josh Hazlewood", "Adam Zampa", "Marcus Stoinis", "Marnus Labuschagne"};
        } else if (matchType == 2) { // T20
            matchTypeName = "T20";
            String[] t20Teams = {"India", "England", "Australia", "South Africa", "New Zealand", "Pakistan", "West Indies", "Sri Lanka"};
            System.out.println("\nAvailable T20 Teams:");
            for (int i = 0; i < t20Teams.length; i++) {
                System.out.println((i + 1) + ". " + t20Teams[i]);
            }
            System.out.print("Select Team A for Batting (1 to " + t20Teams.length + "): ");
            int teamAIndex = sc.nextInt() - 1;
            teamA = t20Teams[teamAIndex];

            System.out.print("Select Team B for Bowling (1 to " + t20Teams.length + "): ");
            int teamBIndex = sc.nextInt() - 1;
            teamB = t20Teams[teamBIndex];

            // Fixed players for T20
            teamAPlayers = new String[]{"Rohit Sharma", "KL Rahul", "Virat Kohli", "Hardik Pandya", "Rishabh Pant", "Suryakumar Yadav", "Jasprit Bumrah", "Bhuvneshwar Kumar", "Yuzvendra Chahal", "Ravi Ashwin", "Shreyas Iyer"};
            teamBPlayers = new String[]{"Jos Buttler", "Jonny Bairstow", "Ben Stokes", "Eoin Morgan", "Jason Roy", "Jofra Archer", "Mark Wood", "Adil Rashid", "Moeen Ali", "Sam Curran", "Chris Woakes"};
        } else if (matchType == 3) { // IPL
            matchTypeName = "IPL";
            String[] iplTeams = {"Chennai Super Kings", "Mumbai Indians", "Royal Challengers Bangalore", "Kolkata Knight Riders", "Delhi Capitals", "Sunrisers Hyderabad", "Rajasthan Royals", "Gujarat Titans"};

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

            // Fixed names for IPL players
            teamAPlayers = new String[]{"Virat Kohli", "AB de Villiers", "Glenn Maxwell", "Yuzvendra Chahal", "Harshal Patel", "Mohammed Siraj", "Devdutt Padikkal", "Dinesh Karthik", "Kohli", "Shahbaz Ahmed", "Josh Hazlewood"};
            teamBPlayers = new String[]{"Rohit Sharma", "Jasprit Bumrah", "Kieron Pollard", "Quinton de Kock", "Suryakumar Yadav", "Trent Boult", "Hardik Pandya", "Ishan Kishan", "Rahul Chahar", "Aditya Tare", "Dewald Brevis"};
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
            System.out.println("Total Runs: " + game1.runs + " | Total Wickets: " + game1.wickets + " | Balls Bowled: " + game1.balls + "/" + game1.totalBalls);
            System.out.println("------------------------------------------------\n");
        }

        // Final Scoreboard for Team A
        System.out.println("\n************************************************");
        System.out.println("            Innings Ended. Team A Score:          ");
        System.out.println("************************************************");
        game1.displayScore();

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
            System.out.println("Total Runs: " + game2.runs + " | Total Wickets: " + game2.wickets + " | Balls Bowled: " + game2.balls + "/" + game2.totalBalls);
            System.out.println("------------------------------------------------\n");
        }

        // FINAL SCOREBOARD for Team B
        System.out.println("\n************************************************");
        System.out.println("            Innings Ended. Team B Score:          ");
        System.out.println("************************************************");
        game2.displayScore();

        // Determine the winning team
        System.out.println("\n************************************************");
        System.out.println("          Match Result:          ");
        System.out.println("************************************************");
        if (game1.runs > game2.runs) {
            System.out.println(teamA + " wins by " + (game1.runs - game2.runs) + " runs!");
        } else if (game1.runs < game2.runs) {
            System.out.println(teamB + " wins by " + (11 - game2.wickets) + " wickets!");
        } else {
            System.out.println("The match is a tie!");
        }

        //SHOW MATCH STATISTICS 
        System.out.println("");
    }
}
