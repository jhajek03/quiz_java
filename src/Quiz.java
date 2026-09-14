import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

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

    private ArrayList<Integer> questionMaker() {
        ArrayList<Integer> draw = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int drawnQuestion = random.nextInt(questions.size());
            if (!draw.contains(drawnQuestion)) {
                draw.add(drawnQuestion);
            }
        }
        return draw;
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

    public void logout(){
        loggedPlayer = null;
    }

    public void addQuestion(String question, String answer) {
        Question q = new Question(question, answer);
        questions.add(q);
    }

    public Question removeQuestion(int index) {
        return questions.remove(index);
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public void listScores(Quiz quiz) {
        ArrayList<Player> players = registeredPlayers;
        players.sort(Comparator.comparing(Player::getBestScore));
        for (Player player : players) {
            System.out.println(player.getUsername() + " " + player.getBestScore());
        }
    }

    public void startQuiz() {
        if (isLoggedIn()) {
            int score = 0;
            ArrayList<Integer> draw = questionMaker();
            for (int i = 0; i < draw.size(); i++) {
                System.out.println(getQuestion(draw.get(i)).question());
                Scanner answerScanner = new Scanner(System.in);
                System.out.print("Your answer: ");
                String answer = answerScanner.nextLine();
                if(answer.equals(getQuestion(draw.get(i)).answer())) {
                    score++;
                    System.out.println("Correct!");
                }
            }
            System.out.println("Your score is " + score);
            if (score > getLoggedPlayer().getBestScore()) {
                getLoggedPlayer().setBestScore(score);
            }
        }
    }
}
