import java.util.ArrayList;

public class Quiz {
    private static ArrayList<Player> registeredPlayers = new ArrayList<Player>();
    private static ArrayList<Question> questions = new ArrayList<Question>();
    private static Player loggedPlayer = null;

    public Player getLoggedPlayer() {
        return loggedPlayer;
    }

    private Boolean isLoggedIn() {
        return loggedPlayer != null;
    }

    public Player registerPlayer(String username, String password, String passwordAgain) {
        if (password.equals(passwordAgain)) {
            Player player = new Player(username, password);
            registeredPlayers.add(player);
            return player;
        }
        else {
            System.out.println("Passwords do not match");
            return null;
        }
    }

    public Player login(String username, String password) {
        if (isLoggedIn()) {
            System.out.println("Already logged in");
            return null;
        }
        for (Player player : registeredPlayers) {
            if (player.getUsername().equals(username) && player.getPassword().equals(password)) {
                loggedPlayer = player;
            }
            else {
                System.out.println("Incorrect Username or Password");
                return null;
            }
        }
        return loggedPlayer;
    }
}
