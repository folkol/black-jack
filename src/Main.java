import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main extends JPanel implements ActionListener {

    private Image table = new BufferedImage(800, 550, BufferedImage.TYPE_INT_RGB);
    private Dimension size = new Dimension();
    private Kortlek kortlek;
    private int nextPaintLocation = 0;
    private JButton drawButton = new JButton();
    private JButton passButton = new JButton();
    private JButton resetButton = new JButton();
    private Vector<Kort> myCards = new Vector<Kort>();
    private Vector<Kort> dealersCards = new Vector<Kort>();

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("21");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new Main());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Main() throws IOException {
        size.width = table.getWidth(null);
        size.height = table.getHeight(null);
        setPreferredSize(size);

        drawButton.setText("Draw!");
        drawButton.addActionListener(this);
        add(drawButton);

        passButton.setText("Pass!");
        passButton.addActionListener(this);
        add(passButton);

        resetButton.setText("New game!");
        resetButton.addActionListener(this);
        add(resetButton);

        newGame();
    }

    private void newGame() {
        drawButton.setEnabled(true);
        passButton.setEnabled(true);

        table = new BufferedImage(800, 550, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = table.getGraphics();
        graphics.setColor(new Color(0, 125, 0));
        graphics.fillRect(0, 0, 800, 550);
        graphics.dispose();

        kortlek = new Kortlek();
        kortlek.shuffle();

        nextPaintLocation = 0;

        myCards.clear();
        dealersCards.clear();

        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g); // Run the paint in the parent class (JPanel) which will paint children etc (Buttons)
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(table, 0, 40, 800, 550, 0, 0, 800, 550, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(drawButton)) {
                drawCard();
            } else if (e.getSource().equals(passButton)){
                done();
            } else {
                newGame();
            }
        } catch (IOException ioe) {
        }
    }

    private void done() throws IOException {
        int sum = 0;
        int nextPaintLocation = 0;

        while (sum < 17) {
            Kort draw = kortlek.draw();
            dealersCards.add(draw);

            sum = hand(dealersCards);

            BufferedImage cardImage = draw.getCardImage();
            Graphics graphics = table.getGraphics();
            graphics.drawImage(cardImage, nextPaintLocation + 100, 300, null);
            graphics.dispose();
            nextPaintLocation += 100;

            repaint();
        }

        gameOver();
    }

    private void gameOver() {
        int myPoints = hand(myCards);
        int dealerPoints = hand(dealersCards);

        String message;

        if (dealerPoints > 21 || myPoints > dealerPoints) {
            message = "You won!";
        } else {
            message = "You lost!";
        }

        message += " (" + myPoints + " vs " + dealerPoints + ")";

        JOptionPane.showMessageDialog(null, message);

        drawButton.setEnabled(false);
        passButton.setEnabled(false);
    }

    /**
     * This method will add the value of the cards, treating aces as either 1 or 14,
     * whichever gets the highest sum still below or equal to 21. All dressed cards
     * are considered being worth 10 points
     * @param cards
     * @return the value of the hand of cards
     */
    private int hand(Vector<Kort> cards) {
        int numAces = 0;
        int sum = 0;

        for (Kort kort : cards) {
            int rank = kort.getRank();
            if (rank > 10) {
                rank = 10;
            }
            sum += rank;
            if (kort.isAce()) {
                numAces++;
            }
        }

        while(numAces > 0 && sum + 13 <= 21) {
            numAces--;
            sum += 13;
        }

        return sum;
    }

    private void drawCard() throws IOException {
        Kort draw = kortlek.draw();
        myCards.add(draw);
        BufferedImage cardImage = draw.getCardImage();
        Graphics graphics = table.getGraphics();
        graphics.drawImage(cardImage, nextPaintLocation + 100, 100, null);
        graphics.dispose();
        nextPaintLocation += 100;

        repaint();

        if (hand(myCards) > 21) {
            done();
        }
    }
}