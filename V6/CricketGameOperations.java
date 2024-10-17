package V6;

public interface CricketGameOperations { // Interface ✅ (CricketGame class's methods)
    public void addPlayer(int index, String name);

    public void playBall(String ballResult, int run);

    public void displayScore(MatchType matchType);

    public int getovers();

}
