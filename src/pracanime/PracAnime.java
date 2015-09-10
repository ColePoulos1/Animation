
package pracanime;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class PracAnime extends JFrame implements Runnable {
    static final int WINDOW_WIDTH = 1000;
    static final int WINDOW_HEIGHT = 800;
    final int XBORDER = 20;
    final int YBORDER = 20;
    final int YTITLE = 25;
    boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;
    int xsink;
    int xsink2;
    int xsink3;
    int xsink4;
    int xsink5;
    int xsink6;
    int ysink;
    int rotsink;
    int timeCount;
    int unrotsink;
    int ysink2;
    int rotsink2;
    double quiver;
    double quiversc;
    int rise;
    int risesmall;
    int wreck;
    boolean textvis;
    boolean textvis2;
    boolean textvis3;
    int boatrot;
    int boatrotsc;
    int boatx;
    int boatxsc;
    int boaty;
    int boatysc;
    int endy;
    int endrot;
    int endrot2;
    int tentx;
    int tentxsc;
    int tentx2;
    int tentxsc2;
    int textrise;
    int spearx;
    int speary;
    int spearosc;
    int spearoscsc;
    static PracAnime frame;
    public static void main(String[] args) {
        frame = new PracAnime();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public PracAnime() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button

// location of the cursor.
                    int xpos = e.getX();
                    int ypos = e.getY();

                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {

        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_UP == e.getKeyCode()) {
                } else if (e.VK_DOWN == e.getKeyCode()) {
                } else if (e.VK_LEFT == e.getKeyCode()) {
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }



////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || xsize != getSize().width || ysize != getSize().height) {
            xsize = getSize().width;
            ysize = getSize().height;
            image = createImage(xsize, ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
        
        
// custom colors
        Color ocean = new Color(50,192,179);
        Color sky = new Color(1,241,235);
        Color boat = new Color(134,119,108);
        Color wave = new Color(61,192,207);
        
//fill background
        g.setColor(sky);
        g.fillRect(0, 0, xsize, ysize);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        } 
        
//text
        g.setColor(Color.white);
        g.fillOval(130,50,200,120);
        g.fillOval(230,60,200,120);
        g.fillOval(330,50,200,120);
        g.fillOval(430,30,200,120);
        g.fillOval(550,50,200,120);
       
        if (textvis)
        {
        g.setColor(Color.black); 
        g.setFont(new Font("Old English Text MT",Font.PLAIN,40));
        g.drawString("             A peaceful boat.", 190, 130); 
        }  
      
       if (textvis2) 
       {
        g.setColor(Color.black); 
        g.setFont(new Font("Old English Text MT",Font.PLAIN,40));
        g.drawString("RELEASE THE KRAKEN!", 170, 130); 
        }  
        
        g.setColor(Color.black); 
        g.setFont(new Font("Old English Text MT",Font.PLAIN,40));
        g.drawString("IT IS THE END.", 355 + tentx, 600 + textrise); 
       
//tentacles
        drawTent(340 + tentx,740-rise + endy,30 +endrot,quiver,5);
        drawTent(230 + tentx2,710-rise + endy,0 + endrot,quiver,5);
        drawTent(460,790+ysink2,rotsink2,quiver,5);        
        drawTentright(840 + tentx,710-rise + endy,0 +endrot2,quiver,5);
        drawTentright(720 + tentx2,750-rise + endy,-30 +endrot2,quiver,5);
        drawTentright(590,790+ysink2,20+unrotsink,quiver,5);
        
//boat  
        drawSpear(510+spearx+spearosc,505+speary,-65,1,1);
        g.setColor(boat);
        drawBoat(500 + boatx,505+ysink +boaty,-3 + rotsink + boatrot,4,4);

//ocean     
        g.setColor(ocean); 
        g.fillRect(0,500,1000,300);
        g.setColor(boat);
        drawWreck(220,750+wreck,-60,4,4);
        g.setColor(boat);
        drawWreck(720,840+wreck,60,4,4);
        g.setColor(ocean);
        g.fillRect(500,700,1000,300);
        g.fillRect(0,600,500,300);
        g.setColor(wave);
        drawWave(200+xsink,600,0,4,4);
        drawWave(500+xsink2,650,0,4,4);
        drawWave(650+xsink3,700,0,4,4);
        drawWave(850+xsink4,550,0,4,4);
        drawWave(259+xsink5,740,0,4,4);
        drawWave(-10+xsink6,700,0,4,4);
        
        gOld.drawImage(image, 0, 0, null);
    }
////////////////////////////////////////////////////////////////////////////
    public void drawCircle(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.fillOval(-10,-10,20,20);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    ////////////////////////////////////////////////////////////////////////////
    public void drawTent(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        Color tent = new Color(204,60,103);
        g.setColor(tent);
        int xval[] ={-8,-6,-4,-3,-2,-2,-3,-4,-5,-5,-4, -2,  1,  3,  8, 13, 13, 10,  7,  6,  4, 2, 1, 0,0,1,2, 3, 5, 6, 7, 7, 6, 5,-8};
        int yval[] ={21,19,18,16,14,10, 8, 6, 1,-3,-8,-10,-12,-13,-14,-13,-12,-12,-11,-11,-10,-8,-6,-3,2,5,7,10,13,15,18,19,20,21,21};
        g.fillPolygon(xval, yval, xval.length);
        
        Color suck = new Color(197,67,197);
        g.setColor(suck);
        g.fillOval(5,17,1,2);
        g.fillOval(2,13,1,2);
        g.fillOval(1,8,1,2);
        g.fillOval(-1,3,1,2);
        g.fillOval(-2,-3,1,2);
        g.fillOval(0,-8,1,2);
        g.fillOval(2,-11,2,1);
        g.fillOval(5,-13,2,1);
        g.fillOval(8,-13,2,1);
         
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
     ////////////////////////////////////////////////////////////////////////////
    public void drawTentright(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
       
        Color tent = new Color(204,60,103);
        g.setColor(tent);
        int xval[] ={ 8, 6, 4, 3, 2, 2,3,4,5, 5, 4,  2, -1, -3, -8,-13,-13,-10, -7, -6, -4,-2,-1, 0,0,-1,-2,-3,-5,-6,-7,-7,-6,-5, 8};
        int yval[] ={21,19,18,16,14,10,8,6,1,-3,-8,-10,-12,-13,-14,-13,-12,-12,-11,-11,-10,-8,-6,-3,2, 5, 7,10,13,15,18,19,20,21,21};
        g.fillPolygon(xval, yval, xval.length);
        
        Color suck = new Color(197,67,197);
        g.setColor(suck);
        g.fillOval(-6,17,1,2);
        g.fillOval(-3,13,1,2);
        g.fillOval(-2,8,1,2);
        g.fillOval(0,3,1,2);
        g.fillOval(1,-3,1,2);
        g.fillOval(0,-8,1,2);
        g.fillOval(-3,-11,2,1);
        g.fillOval(-6,-13,2,1);
        g.fillOval(-9,-13,2,1);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
////////////////////////////////////////////////////////////////////////////
    public void drawBoat(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

//        int xval[] ={  0, 10, 10,-10,-10,  0};
//        int yval[] ={-15,-10, 10, 10,-10,-15};
//wood
        g.fillArc(-30,-30,70,40,180,180);
        g.fillRect(0,-35,3,25);
        g.fillRect(20,-30,3,20);
        g.fillRect(-24,-16,10,7);
 //sails
        g.setColor(Color.white);
        g.fillRect(-5,-33,12,7);
        g.fillRect(-9,-25,20,13);
        int xval[] ={22,35,22};
        int yval[] ={-28,-10,-15};
        g.fillPolygon(xval, yval, xval.length);
        
        g.setColor(Color.gray); 
        g.fillOval(-20,-3,5,5);
        g.fillOval(-5,-3,5,5);
        g.fillOval(10,-3,5,5);
        g.fillOval(25,-3,5,5);
        g.fillRect(-25,-16,12,1);
        g.fillRect(-16,-14,2,4);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
     ////////////////////////////////////////////////////////////////////////////
    public void drawSpear(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        int xval[] ={ 0,-1,-2,-2,-1, 0,1, 2,2,1, 0};
        int yval[] ={15, 2, 1,-6, 0,-5,0,-6,1,2,15};

        g.setColor(Color.LIGHT_GRAY);
        g.fillPolygon(xval, yval, xval.length);
        g.setColor(Color.black);    
        g.fillRect(-1, 5, 2, 4);

        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
   }
    ////////////////////////////////////////////////////////////////////////////
    public void drawWreck(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

//        int xval[] ={  0, 10, 10,-10,-10,  0};
//        int yval[] ={-15,-10, 10, 10,-10,-15};
//wood
        g.fillRect(0,-35,3,25);
        g.fillRect(20,-30,3,20);
        g.fillRect(-24,-16,10,7);
 //sails
        g.setColor(Color.white);
        g.fillRect(-5,-33,12,7);
        g.fillRect(-9,-25,20,13);
        int xval[] ={22,35,22};
        int yval[] ={-28,-10,-15};
        g.fillPolygon(xval, yval, xval.length);
        
        g.setColor(Color.gray); 
        g.fillRect(-25,-16,12,1);
        g.fillRect(-16,-14,2,4);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    ////////////////////////////////////////////////////////////////////////////
    public void drawWave(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        int xval[] ={-15,-14,-12,-10,-11,-11,-9,-6,-6,-5,-5,-3,-2, 0,-2,-3,0, 2, 3, 6, 5,4,5, 8,11, 9,8,9,10,11,13,14,12,13};
        int yval[] ={  2,  0, -2, -3, -1,  1, 0,-3,-1, 0, 2,-2,-2,-1, 0, 2,1,-1,-3,-5,-2,0,2,-2,-3,-1,1,2, 1,-1,-2,-2, 0, 2};
        g.drawPolyline(xval, yval, xval.length);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.04;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
    
     xsink=0;
     xsink2=0;
     xsink3=0;
     xsink4=0;
     xsink5=0;
     xsink6=0;
     ysink=0;
     rotsink=0;
     timeCount=0;
     unrotsink=0;
     ysink2=0;
     rotsink2=0;
     quiver=0.0;
     quiversc=0.0;
     rise=0;
     risesmall=0;
     wreck=0;
     textvis=false;
     textvis2=false;
     textvis3=false;
     boatrot=0;
     boatrotsc=0;
     boatx=0;
     boatxsc=0;
     boaty=0;
     boatysc=0;
     endy=0;
     endrot=0;
     endrot2=0;
     tentx=0;
     tentxsc=0;
     tentx2=0;
     tentxsc2=0;
     textrise=0;
     spearx=0;
     speary=0;
     spearosc=0;
     spearoscsc=0;
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height) {
                xsize = getSize().width;
                ysize = getSize().height;
            }
         
            reset();
        }
     
//boat  
       boatrot+=boatrotsc;
       boatx+=boatxsc;
       boaty+=boatysc;
       
          if (boatrot>7)
           boatrotsc= -1;
          if (boatrot<1)
           boatrotsc= 1;   
          
          if (boatx>10)
           boatxsc= -1;
          if (boatx<1)
           boatxsc= 1; 
         
          if (boaty>10)
           boatysc= -1;
          if (boaty<1)
           boatysc= 1;
       
       if (timeCount < 200)
       {}
       else if (timeCount < 290)
       {
         ysink+=2;
         rotsink--;
       }
       if(timeCount < 250)
       {}
       else if (timeCount < 300)
         wreck--;
      
//tentacles       
       if (timeCount < 50)
       {}
       else if (timeCount < 190)   
       {
         ysink2-=2;
         rise+=2;
       }    
       else if (timeCount < 290)    
       {
         rotsink2++;
         unrotsink--;  
         ysink2++;
       }
      
       quiver+=quiversc;
       if (quiver>6)
           quiversc= -.05;
       if (quiver<4.5)
           quiversc= .05;
       
       tentx+=tentxsc;
       if (tentx > 14)
           tentxsc=-1;
       if (tentx < 1)
           tentxsc=1;
      if (timeCount > 5)
      {  
       tentx2+=tentxsc2;
       if (tentx2 > 14)
           tentxsc2=-1;
       if (tentx2 < 1)
           tentxsc2=1;
      }
      
       if (timeCount < 290)
       {}
       else if (timeCount < 350)
       {
       endy+=2;
       endrot++;
       endrot2--;
       }
       else if (timeCount < 370)
       {}
       else if (timeCount < 430)
       {
       endy-=2;
       endrot--;
       endrot2++;
       } 
       
//waves           
       xsink+=2;  
       xsink2+=2;
       xsink3+=2;
       xsink4+=2;
       xsink5+=2;
       xsink6+=2;
       
       if (xsink > 830)
           xsink = -250;
       
       if (xsink2 > 530)
           xsink2 = -550;
       
       if (xsink3 > 390)
           xsink3 = -700;
       
       if (xsink4 > 190)
           xsink4 = -900;
       
       if (xsink5 > 780)
           xsink5 = -310;
      
       if (xsink6 > 1060)
           xsink6 = -40;
       
//text        
       if (timeCount < 170)  
           textvis = true;
       else if (timeCount < 270)     
       {
           textvis = false;
           textvis2 = true;
       }
       else if (timeCount < 365)
       {}
       else if (timeCount < 430)
       textrise-=2;
       
       timeCount++;
       
       if (timeCount < 90)
       {}
       else if (timeCount < 185)
       {
       spearx-=3;
       speary--;
       }
       else if (timeCount > 187)
       {
           spearosc+=spearoscsc;
       if (spearosc > 14)
           spearoscsc=-1;
       if (spearosc < 1)
           spearoscsc=1;
       }
       
       if (timeCount < 290)
       {}
       else if (timeCount < 350)
       speary+=2;
       else if (timeCount < 370)
       {}
       else if (timeCount < 430)
           speary-=2;
       
       if (timeCount > 310)
           textvis2 = false;
    }
    

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
/////////////////////////////////////////////////////////////////////////
    public int getX(int x) {
        return (x + XBORDER);
    }

    public int getY(int y) {
        return (y + YBORDER + YTITLE);
    }

    public int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }
    
    
    public int getWidth2() {
        return (xsize - getX(0) - XBORDER);
    }

    public int getHeight2() {
        return (ysize - getY(0) - YBORDER);
    }
}
