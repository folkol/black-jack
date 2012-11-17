import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Kort {
    public int suit, rank;
    public BufferedImage b;

    public Kort(int i, int j) {
        suit = i;
        rank = j + 1;
    }

    public String toString() {
        return getRankName() + " of " + getSuitName();
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }
    public int getValue() {
        int value = rank + 1;
        return value > 10 ? 10 : value;
    }

    public String getSuitName() {
        String suitName = "";
        if (suit == 0) {
            suitName = "Hearts";
        } else if (suit == 1) {
            suitName = "Diamonds";
        } else if (suit == 2) {
            suitName = "Clubs";
        } else if (suit == 3) {
            suitName = "Spades";
        }
        return suitName;
    }

    public String getRankName() {
        String rankName = "";
        if (rank == 0) {
            rankName = "Ace";
        } else if (rank == 1) {
            rankName = "Two";
        } else if (rank == 2) {
            rankName = "Three";
        } else if (rank == 3) {
            rankName = "Four";
        } else if (rank == 4) {
            rankName = "Five";
        } else if (rank == 5) {
            rankName = "Six";
        } else if (rank == 6) {
            rankName = "Seven";
        } else if (rank == 7) {
            rankName = "Eight";
        } else if (rank == 8) {
            rankName = "Nine";
        } else if (rank == 9) {
            rankName = "Ten";
        } else if (rank == 10) {
            rankName = "Knave";
        } else if (rank == 11) {
            rankName = "Queen";
        } else if (rank == 12) {
            rankName = "King";
        }
        return rankName;
    }

    public BufferedImage getCardImage() throws IOException {
        int cardWidth = 40;
        int cardHeight = 58;

        BufferedImage cards = ImageIO.read(new File("images/cards.png"));
        int x = (rank - 1) * cardWidth;
        int y = suit * cardHeight;

        BufferedImage b = cards.getSubimage(x, y, cardWidth, cardHeight);
        return b;
    }


    public boolean isAce() {
        return rank == 1;
    }
}
