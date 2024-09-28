
import java.util.Scanner;

public class cricket {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Random random = new Random();

        System.out.println("************************************************");
        System.out.println("          Welcome to the Cricket Game!          ");
        System.out.println("************************************************\n");

        System.out.print("Enter the number of overs: ");
        int overs = sc.nextInt();

        int totalBalls = overs * 6; // Total balls in the game
        int[] ballsInOver = new int[6]; // Array to track 6 balls in each over

        int runs = 0, wickets = 0, balls = 0, currentBall = 0, currentOver = 1, noballs = 0, wideballs = 0;

        System.out.println("\n--- Game Starts Now! ---\n");

        while (balls < totalBalls && wickets < 10) {
            if (currentBall == 6) {  // If 6 balls are completed in an over, reset for the next over
                currentBall = 0;
                currentOver++;
                System.out.println("\n--- Over " + currentOver + " starts ---\n");
            }

            ballsInOver[currentBall] = currentBall + 1;  // Ball number within the over
            System.out.println("Over " + currentOver + " Ball " + ballsInOver[currentBall]);

            System.out.print("Enter result of ball (run, wicket, no ball, wide, exit): ");
            String ballResult = sc.next().toLowerCase();

            // Exit game if user types "exit"
            if (ballResult.equals("exit")) {
                System.out.println("\n************************************************");
                System.out.println("      Thank you for playing! Game Ended.         ");
                System.out.println("************************************************");
                break;
            }

            switch (ballResult) {
                case "run":

                    int run = sc.nextInt();
                    runs += run;
                    System.out.println("\nRuns scored on this ball: " + run);
                    currentBall++;  //within the over
                    balls++; //within the game
                    break;
                case "wicket":
                    wickets++;
                    System.out.println("\nWicket! Total wickets: " + wickets);
                    currentBall++;
                    balls++;
                    break;
                case "noball":
                    System.out.print("Enter runs -");
                    int noballrun = sc.nextInt();
                    runs += noballrun + 1;
                    noballs++;
                    System.out.println("\nNo Ball! Free hit awarded.");
                    // currentBall++;
                    // balls++;
                    // No increment for balls as it doesn't count as a legal ball
                    break;
                case "wide":
                    System.out.print("Enter runs -");
                    int wideballrun = sc.nextInt();
                    runs += wideballrun + 1;
                    System.out.println("\nWide ball! Extra run awarded.");
                    wideballs++;
                    // currentBall++;
                    // balls++;
                    // No increment for balls as it doesn't count as a legal ball
                    break;
                default:
                    System.out.println("\nInvalid input. Please enter 'run', 'wicket', 'noball', 'wide', or 'exit'.");
                    break;
            }

            System.out.println("------------------------------------------------");
            System.out.println("Total Runs: " + runs + " | Total Wickets: " + wickets + " | Balls Bowled: " + balls + "/" + totalBalls);
            System.out.println("------------------------------------------------\n");
        }

        // End message if the game ends by either reaching total balls or wickets
        if (balls >= totalBalls || wickets >= 10) {
            System.out.println("************************************************");
            System.out.println("            Innings Ended. Final Score:          ");
            System.out.println("          " + runs + " runs /" + wickets + " wickets in " + overs + " overs");
            System.out.println("************************************************");
            System.out.println(" TOTAL BALLS : " + balls);
            System.out.println(" TOTAL NO BALLS : " + noballs);
            System.out.println(" TOTAL WIDE BALLS  : " + wideballs);

        }

    }
}
