public class Player {
    private final String Name;
    private int Score;
    private Card card[] = new Card[11];
    private int cardCount = 0;
    private boolean Lose;

    public Player(String name) {
        Name = name;
        Score = 0;
        Lose = false;
    }

    public Card[] getCard() {
        return card;
    }

    public Card getLastCard() {
        return card[cardCount - 1];
    }

    public void setCard(Card c) {
        card[cardCount] = new Card(c);
        cardCount++;
    }

    public void addScore(int score) {
        Score += score;
    }

    public String getName() {
        return Name;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public boolean isLose() {
        return Lose;
    }

    public void setLose(boolean lose) {
        Lose = lose;
    }

}
