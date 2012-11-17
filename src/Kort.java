import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Kort {
    private String suit;
    private String rank;
    public int x, y;
    public BufferedImage b;

    public Kort(int i, int j) {
        x = i;
        y = j + 1;
        if (i == 0) {
            suit = "Spader";
        } else if (i == 1) {
            suit = "Klöver";
        } else if (i == 2) {
            suit = "Hjärter";
        } else if (i == 3) {
            suit = "Ruter";
        }
        if (j == 0) {
            rank = "Ess";
        } else if (j == 1) {
            rank = "Två";
        } else if (j == 2) {
            rank = "Tre";
        } else if (j == 3) {
            rank = "Fyra";
        } else if (j == 4) {
            rank = "Fem";
        } else if (j == 5) {
            rank = "Sex";
        } else if (j == 6) {
            rank = "Sju";
        } else if (j == 7) {
            rank = "Åtta";
        } else if (j == 8) {
            rank = "Nio";
        } else if (j == 9) {
            rank = "Tio";
        } else if (j == 10) {
            rank = "Knekt";
        } else if (j == 11) {
            rank = "Dam";
        } else if (j == 12) {
            rank = "Kung";
        }
    }

    public String toString() {
        return "Kortet är: " + suit + " " + rank;
    }

    public int getSuit() {
        return x;
    }

    public int getRank() {
        return y;
    }

    public BufferedImage getCardImage() throws IOException {
        b = new BufferedImage(64, 96, BufferedImage.TYPE_INT_ARGB);
        Graphics g = b.getGraphics();
        BufferedImage bi = null;
        JLabel label = new JLabel(new ImageIcon(b));
        g.setColor(Color.white);
        g.fillRect(0, 0, 64, 96);
        if (suit == "Hjärter") {
            bi = ImageIO.read(new File("images/heartsbig.jpg"));
            g.setColor(Color.red);
        } else if (suit == "Ruter") {
            bi = ImageIO.read(new File("images/diamondsbig.jpg"));
            g.setColor(Color.red);
        } else if (suit == "Spader") {
            bi = ImageIO.read(new File("images/spadesbig.jpg"));
            g.setColor(Color.black);
        } else if (suit == "Klöver") {
            bi = ImageIO.read(new File("images/clubsbig.jpg"));
            g.setColor(Color.black);
        }
        g.drawImage(bi, 10, 2, 12, 12, null);
        g.drawImage(bi, 42, 80, 12, 12, null);
        g.drawImage(bi, 16, 32, 32, 32, null);
        if (y == 1) {
            g.drawString("A", 2, 12);
            g.drawString("A", 56, 88);
        } else if (y > 1 && y < 11) {
            g.drawString(Integer.toString(y), 2, 12);
            g.drawString(Integer.toString(y), 56, 92);
        } else if (y == 11) {
            g.drawString("J", 2, 12);
            g.drawString("J", 56, 92);
        } else if (y == 12) {
            g.drawString("D", 2, 12);
            g.drawString("D", 56, 92);
        } else if (y == 13) {
            g.drawString("K", 2, 12);
            g.drawString("K", 56, 92);
        }
        return b;
    }

    public boolean isAce() {
        return y == 1;
    }
}
