import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class Game extends Canvas implements Runnable, KeyListener {
    public static Player player;
    public static ArrayList<Block> blocks = new ArrayList<>();

    public Game () {
        this.setPreferredSize(new Dimension(480, 480));
        this.addKeyListener(this);

        new Shape();
        player = new Player(0, 0);
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame("Tetris");
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {
        while (true) {
            tick();
            render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void render() {
        requestFocus();
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,480,480);

        player.render(g);

        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).render(g);
        }

        bs.show();
    }

    public void tick() {
        player.tick();

        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).tick();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.changeShape = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }
    }
}
