package Oyun;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ates {
	private int x;
	private int y;
	public Ates(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}

public class OyunPanel extends JPanel implements KeyListener, ActionListener{
	Timer timer = new Timer(1,this);
	private int gecenSure =0;
	private int harcananAtes=0;
	private BufferedImage image;
	private ArrayList<Ates> atesler = new ArrayList<Ates>();
	private int atesdirY = 3;
	private int topX = 0;
	private int topdirX = 2; 
	private int uzayGemisiX=0;
	private int dirUzayX = 20;
	
	public OyunPanel() {
		try {
			image = ImageIO.read(new FileImageInputStream(new File("uzaygemisi.png")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setBackground(Color.BLACK);
		timer.start();
	}			

	
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}

	public boolean kontrolEt() {
		for (Ates ates : atesler) {
			if(new Rectangle(ates.getX(), ates.getY(), 10, 20).intersects(new Rectangle(topX,0,20,20))) {
				return true;
			}
		}
		return false;
	}
	@Override
	public void paint(Graphics g) {
		gecenSure+=1;
		super.paint(g);
		
		g.setColor(Color.RED);
		g.fillOval(topX, 0, 20, 20);
		g.drawImage(image, uzayGemisiX, 680, image.getWidth()/10 , image.getHeight() /10 , this);
		
		for (Ates ates : atesler) {
			if(ates.getY()<0) {
				atesler.remove(ates);
			}
		}
		g.setColor(Color.blue);
		for (Ates ates : atesler) {
			g.fillRect(ates.getX(), ates.getY(), 10, 20);
		}
		if(kontrolEt()) {
			timer.stop();
			String messageString = "Kazandınız     \n"+
								   "Harcanan Ateş :" + harcananAtes+ "\n"+ 
								   "Geçen Süre    :" + gecenSure/1000.0 ;
			JOptionPane.showMessageDialog(this, messageString);
			System.exit(0);
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		for (Ates ates : atesler) {
			ates.setY(ates.getY()- atesdirY);
		}
		topX += topdirX;
		if(topX >= 750) {
			topdirX = -topdirX;
		}if(topX<=0) {
			topdirX=-topdirX;
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if(c == KeyEvent.VK_LEFT) {
			if(uzayGemisiX <=0) {
				uzayGemisiX =0;
			}else {
				uzayGemisiX -= dirUzayX;
			}
		}
		else if(c == KeyEvent.VK_RIGHT) {
			if(uzayGemisiX >=720) {
				uzayGemisiX =720;
			}else {
				uzayGemisiX += dirUzayX;
			}
		}else if(c == e.VK_CONTROL) {
			atesler.add(new Ates(uzayGemisiX+15, 650));
			harcananAtes++;
			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
 
}
