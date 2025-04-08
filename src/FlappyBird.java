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

    //Canos
    int PipeX = LarguraBorda;
    int PipeY = 0;
    int PipeWidth = 64;
    int PipeHeight = 512;

    class Pipe {
        int x = PipeX;
        int y = PipeY;
        int width = PipeWidth;
        int height = PipeHeight;
        Image img;
        boolean  Passed = false;

        Pipe(Image img){
            this.img = img;
        }
    }


    //Logica do jogo

    Bird bird;
    int VelocityX = -4;
    int VelocityY = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer placePipesTimer;


    FlappyBird(){
        setPreferredSize(new Dimension(LarguraBorda, AlturaBorda));
        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImage = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImage = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();



        bird = new Bird(birdImage);
        pipes = new ArrayList<Pipe>();
        placePipesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        } );

        placePipesTimer.start();
        gameLoop = new Timer(1000/60, null);
        gameLoop.start();
    }
    

    public void paintComponent (Graphics g) {

        super.paintComponent(g);
        draw(g);
    }

    public void placePipes() {
        Pipe topPipe = new Pipe(topPipeImage);
        pipes.add(topPipe);
    } 


    public void draw (Graphics g) {

        g.drawImage(backgroundImage, 0, 0, LarguraBorda, AlturaBorda,  null);

        g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);

        for (int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x, pipe.y, pipe.width, pipe.height, null);

        }
    }

    public void move(){
        VelocityY += gravity;
        bird.y += VelocityY;
        bird.y = Math.max(bird.y, 0);

        for (int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.x += VelocityX;
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }



    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            VelocityY = -6;
        }
    }
}
