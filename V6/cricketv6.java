// V6 : Implemented Package, Custom Exception. all the assignment parts are done now.
package V6; //package ✅

import java.util.Scanner;

//custom excption for overs ✅
class InvalidOversException extends Exception {
    InvalidOversException(String msg) {
        super(msg);
    }
}

public class cricketv6 {

    public static void main(String[] args) throws InvalidOversException {
        Scanner sc = new Scanner(System.in);

        System.out.println("************************************************");
        System.out.println("          Welcome to the Cricket Game!          ");
        System.out.println("************************************************\n");

        // Match Type Selection
        MatchType matchTypenew = null;
        System.out.print("Select Match Type (1 - ODI, 2 - T20, 3 - IPL): ");
        int matchType = sc.nextInt();
        String matchTypeName = "";

        String teamA = "";
        String teamB = "";

        String[] teamPlayers = { "Virat Kohli", "Rohit Sharma", "Shubman Gill", "KL Rahul",
                "Hardik Pandya",
                "Ravindra Jadeja", "Jasprit Bumrah", "Mohammed Shami", "Bhuvneshwar Kumar", "Rishabh Pant",
                "Yuzvendra Chahal" };
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

        CricketGame overobj = new CricketGame();
        int overs = overobj.getovers();

        CricketGame game1 = new CricketGame(overs);
        CricketGame game2 = new CricketGame(overs);

        // Adding players to batting team
        for (int i = 0; i < teamPlayers.length; i++) {
            game1.addPlayer(i, teamPlayers[i]);
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
        for (int i = 0; i < teamPlayers.length; i++) {
            game2.addPlayer(i, teamPlayers[i]);
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
        System.out.println("          Match Result:         ");
        System.out.println("************************************************");

        if (game1.runs > game2.runs) {
            System.out.println(teamA + " Runs : " + game1.runs + " | " + teamB + " Runs : " + game2.runs);
            System.out.println(teamA + " Wins !!!");
        } else if (game1.runs < game2.runs) {
            System.out.println(teamA + " Runs : " + game1.runs + " | " + teamB + " Runs : " + game2.runs);
            System.out.println(teamB + " Wins !!!");
        } else {
            System.out.println("The match is a tie!");
        }
        System.out.println("************************************************");
    }
}
