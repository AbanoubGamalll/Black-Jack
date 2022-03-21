import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {
        Game game = new Game();
        Card lastCard;
        GUI gui = new GUI();
        Scanner scanner = new Scanner(System.in);
        boolean gameStill = true;
        game.setInformationPlayer("P1");
        game.setInformationPlayer("P2");
        game.setInformationPlayer("P3");
        game.setInformationPlayer("D.");

        gui.runGUI(game.getCard(), game.getPlayer(0).getCard(), game.getPlayer(1).getCard(), game.getPlayer(2).getCard(), game.getPlayer(3).getCard());

        for (int playerindex = 0; playerindex < 4; playerindex++) {
            if (playerindex == 3) {
                if (game.checkDealerWin()) {
                    System.out.println("Dealer is Win By Score :" + game.getPlayer(3).getScore());
                    gameStill = false;
                    break;
                }
            }
            System.out.println(game.getPlayer(playerindex).getName() + " Your Score is : " + game.getPlayer(playerindex).getScore());
            while (true) {
                System.out.print("Do You Want (1) HIT (2) STAND : ");
                int choose = scanner.nextInt();
                if (choose == 1) {
                    lastCard = game.takeNewCard(playerindex);
                    if (playerindex < 3) {
                        gui.updatePlayerHand(lastCard, playerindex);
                    } else {
                        gui.updateDealerHand(lastCard, game.getCard());
                    }
                    System.out.println("Now,Your Score is : " + game.getPlayer(playerindex).getScore());
                    if (game.getHighScore() < game.getPlayer(playerindex).getScore()) {
                        System.out.println("You Lost...");
                        game.losePlayer(playerindex);
                        break;
                    }
                } else if (choose == 2) {
                    System.out.println("Your Final Score is : " + game.getPlayer(playerindex).getScore());
                    if (game.getHighScore() == game.getPlayer(playerindex).getScore()) {
                        System.out.println("You Almost Win The Game And have High Score....");
                    }
                    break;
                } else {
                    System.out.println("Your Choice is Wrong Try Again...");
                }
            }
            System.out.println("------------------------------------");
        }
        if (gameStill) {
            game.whoIsWin();
            System.out.println("------------------------------------");
        }

        System.out.println("The Game Is Finish");

    }

}
