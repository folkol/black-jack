import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Kort {
    private String suitName;
    private String rankName;
    public int suit, rank;
    public BufferedImage b;

    public Kort(int i, int j) {
        suit = i;
        rank = j + 1;
        if (i == 0) {
            suitName = "Hearts";
        } else if (i == 1) {
            suitName = "Diamonds";
        } else if (i == 2) {
            suitName = "Clubs";
        } else if (i == 3) {
            suitName = "Spades";
        }
        if (j == 0) {
            rankName = "Ess";
        } else if (j == 1) {
            rankName = "Två";
        } else if (j == 2) {
            rankName = "Tre";
        } else if (j == 3) {
            rankName = "Fyra";
        } else if (j == 4) {
            rankName = "Fem";
        } else if (j == 5) {
            rankName = "Sex";
        } else if (j == 6) {
            rankName = "Sju";
        } else if (j == 7) {
            rankName = "Åtta";
        } else if (j == 8) {
            rankName = "Nio";
        } else if (j == 9) {
            rankName = "Tio";
        } else if (j == 10) {
            rankName = "Knekt";
        } else if (j == 11) {
            rankName = "Dam";
        } else if (j == 12) {
            rankName = "Kung";
        }
    }

    public String toString() {
        return "Kortet är: " + suitName + " " + rankName;
    }

    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public BufferedImage getCardImage() throws IOException {
        BufferedImage cards = ImageIO.read(new File("images/cards.png"));
        int x = rank - 1;
        int y = suit * 40;
        int w = 40;
        int h = 60;

        BufferedImage b = cards.getSubimage(x, y, w, h);
        return b;
    }


    public boolean isAce() {
        return rank == 1;
    }
}
