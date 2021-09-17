import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;

public class PongRunner{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Pong by Reggie Red");
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		barDrawer barComp = new barDrawer();

		class MousePressListener implements MouseListener{
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1)
					barComp.moveBy(-5);
				if(e.getButton() == MouseEvent.BUTTON3)
					barComp.moveBy(5);
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		}
		class barMover implements KeyListener{
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				barComp.moveBy(-5);
			}
			public void keyReleased(KeyEvent e) {}
		}
		class TimerListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				barComp.ballMotion();
			}
		}
		ActionListener ballListen = new TimerListener();
		MouseListener listener = new MousePressListener();
		barComp.addMouseListener(listener);
		Timer t = new Timer(100, ballListen);
		t.start();
		frame.add(barComp);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
class barDrawer extends JComponent {
	//bar
	private static final int barX = 125;
	private static final int barY = 300;
	public Rectangle bar;
	//ball
	private static final int ballX = 147;
	private static final int ballY = 0;
	private Rectangle ball;

	public barDrawer(){
		//bar
		bar = new Rectangle(barX, barY, 50, 5);
		//ball
		ball = new Rectangle(ballX, ballY, 6, 6);
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(bar);
		//ball
		g2.fill(ball);
	}
	public void moveBy(int dx){
		bar.translate(dx, 0);
		repaint();
	}

	public int ball_dx = 8;
	public int ball_dy = 5;
	public void ballMotion() {
		if(ball.getX() < 0 || ball.getX() > 278)
			ball_dx = -ball_dx;
		if(ball.getY() < 0 || bar.getBounds().intersects(ball))
			ball_dy = -ball_dy;
		if(ball.getY() > 320)
			ball.setLocation(147, 0);
		ball.translate(ball_dx, ball_dy);
		repaint();
	}
}