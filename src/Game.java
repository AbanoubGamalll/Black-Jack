import java.util.Random;

public class Game {
    private Player player[] = new Player[4];
    private int playerIndex = 0;
    private Card card[] = new Card[52];
    private int cardCount = 0;
    private Random random = new Random();
    private int highScore = 21;

    public Game() {
        for (int i = 0; i < 52; i++) {
            createCard();
        }
    }

    public Player getPlayer(int i) {
        return player[i];
    }

    public void setInformationPlayer(String name) {
        player[playerIndex] = new Player(name);
        takeNewCard(playerIndex);
        takeNewCard(playerIndex);
        playerIndex++;
    }

    public Card[] getCard() {
        return card;
    }

    public int getHighScore() {
        return highScore;
    }

    public void createCard() {
        int rank = random.nextInt(13);
        int value;
        if (rank >= 0 && rank <= 9) {
            value = rank + 1;
        } else {
            value = 10;
        }
        Card c = new Card(random.nextInt(4), rank, value);
        if (checkCard(c)) {
            createCard();
        } else {
            card[cardCount] = c;
            cardCount++;
        }

    }

    public Card takeNewCard(int playerIndex) {
        int t = random.nextInt(52);
        if (card[t] == null) {
            takeNewCard(playerIndex);
        } else {
            player[playerIndex].setCard(card[t]);
            player[playerIndex].addScore(card[t].getValue());
            card[t] = null;
        }
        return player[playerIndex].getLastCard();
    }

    private boolean checkCard(Card c) {
        for (int i = 0; i < cardCount; i++) {
            if (card[i].getRank() == c.getRank()
                    && card[i].getSuit() == c.getSuit()
                    && card[i].getValue() == c.getValue()) {
                return true;
            }
        }
        return false;
    }

    public void losePlayer(int playerIndex) {
        player[playerIndex].setLose(true);
    }

    public void whoIsWin() {
        boolean max = false;
        Player maxplayer = new Player("test");
        maxplayer.setScore(-1);
        for (int i = -1; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {
                if (!player[j].isLose()) {
                    if (maxplayer.getScore() < player[j].getScore()) {
                        maxplayer = player[j];
                        max = false;
                        break;
                    } else {
                        max = true;
                    }
                }
            }
            if (max) {
                break;
            }
        }

        if (isPushOrWIN(maxplayer)) {
            System.out.println("The Game Is PUSH");
        } else {
            System.out.println("Player: " +
                    maxplayer.getName() + " Is Win" +
                    " ,With Score: " + maxplayer.getScore());
        }
    }

    public boolean isPushOrWIN(Player p) {
        boolean check = false;
        for (int i = 0; i < 4; i++) {
            if (p.getScore() == player[i].getScore()) {
                if (check) {
                    return true;
                }
                check = true;
            }
        }

        return false;
    }

    public boolean checkDealerWin() {
        boolean check = false;
        if (player[0].isLose() && player[1].isLose() && player[2].isLose()) {
            return true;
        } else {
            for (int j = 0; j < 3; j++) {
                if (!player[j].isLose()) {
                    if (player[3].getScore() > player[j].getScore()) {
                        check = true;
                    } else {
                        check = false;
                        break;
                    }
                }
            }
        }
        if (check) {
            return true;
        } else {
            return false;
        }
    }

}
