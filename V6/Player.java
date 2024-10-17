package V6;

public class Player {
    public String name;
    public int runs;
    public int ballsFaced;
    public boolean isOut;

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
