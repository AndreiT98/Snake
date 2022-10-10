import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends JFrame {


    Logic panel;
    JPanel score;
    JPanel gameOver;
    JLabel scoreNumber;
    JLabel gameOverMesasge;
    JFrame frame;
    JButton exit = new JButton();
    JButton restart = new JButton();


    Timer timer;


    public GUI() {
        initGame();


    }

    public void initGame() {
        frame = new JFrame();
        frame.setSize(800, 900);
        frame.setLayout(new BorderLayout());


        panel = new Logic();
        score = new JPanel();
        scoreNumber = new JLabel();


        score.setPreferredSize(new Dimension(800, 100));
        score.setLayout(new GridBagLayout());
        score.setBackground(Color.GRAY);
        score.add(scoreNumber);
        scoreNumber.setFont(new Font("Arial", Font.BOLD, 20));


        panel.addKeyListener(panel);
        panel.setLayout(new BorderLayout());
        panel.setFocusable(true);


        frame.add(score, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        panel.requestFocusInWindow();


        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (panel.isInGame()) {
                    panel.checkAppleStatus();
                    panel.moveSnake();
                    scoreNumber.setText("score: " + panel.getScore());
                    if (panel.bitHimself()) {
                        gameOver();
                        timer.stop();

                    }
                    repaint();
                }

            }
        });
        timer.start();
    }


    public void gameOver() {

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GUI();


            }
        });

        gameOver = new JPanel();
        gameOver.setPreferredSize(new Dimension(panel.getWIDTH(), panel.getHEIGHT()));
        gameOver.setLayout(null);
        gameOver.setBackground(Color.BLACK);

        //GridBagConstraints gbc = new GridBagConstraints();
        //gbc.insets = new Insets(0,20,0,0);


        //exit.setPreferredSize(new Dimension(200, 50));
        exit.setText("Exit");
        exit.setBounds(450, 450, 100, 50);
        gameOver.add(exit);

        //restart.setPreferredSize(new Dimension(200, 50));
        restart.setText("Restart");
        restart.setBounds(250, 450, 100, 50);
        gameOver.add(restart);

        gameOverMesasge = new JLabel();
        gameOverMesasge.setFont(new Font("Arial", Font.BOLD, 30));
        gameOverMesasge.setText("GAME OVER");
        gameOverMesasge.setForeground(Color.red);
        gameOverMesasge.setBounds(320, 100, 500, 50);
        gameOver.add(gameOverMesasge);


        frame.remove(panel);
        frame.add(gameOver);
        frame.revalidate();
        frame.repaint();
    }


}



