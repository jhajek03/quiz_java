public class Player {
    private String username;
    private String password;
    private int bestScore;

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.bestScore = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
