import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel{
    int LarguraBorda = 360;
    int AlturaBorda = 640;

    Image birdImage;
    Image backgroundImage;
    Image bottomPipeImage;
    Image topPipeImage;
    

    int birdX = LarguraBorda / 8;
    int birdY = AlturaBorda / 2;
    int birdWidth = 34;
    int birdHeight = 24;

    class Bird {
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird (Image img){
        this.img = img;
        }
        
    }



    Bird bird;

    FlappyBird(){
        setPreferredSize(new Dimension(LarguraBorda, AlturaBorda));
       

        backgroundImage = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();



        bird = new Bird(birdImage);
    }
    

    public void paintComponent (Graphics g) {

        super.paintComponent(g);
        draw(g);
    }

    public void draw (Graphics g) {

        g.drawImage(backgroundImage, 0, 0, LarguraBorda, AlturaBorda,  null);

        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
    }
}
