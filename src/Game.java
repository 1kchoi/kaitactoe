import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener, MouseListener {

	@SuppressWarnings("unused")
	private int w, h;
	private Timer t = new Timer(5, this);
	
	
	private int player;
	private int winner;
	int state[][] = new int[3][3];
	int games = -1;
	
	Rectangle background;
	Rectangle board;
	Rectangle resetButton;
	Rectangle tiles[][] = new Rectangle[3][3];
	
	Color backgroundColor = Color.BLACK;
	Color accentColor = Color.BLACK;
	Color unpickedColor = Color.WHITE;
	Color kaiYellow = new Color(16777164);
	Color kaiBlue = new Color(12576498);
	Color kaiRed = Color.decode("#FFA959");
	Color kaiCyan = new Color (892927);
	Color kaiGreen = new Color(5018700);
	Color kaiMagenta = new Color(16244991);
	
	Color p1Colors[] = {kaiYellow, kaiRed, kaiGreen};
	Color p2Colors[] = {kaiBlue, kaiCyan, kaiMagenta};
	Color p1Color;
	Color p2Color;
	
	public Game(int w, int h)
	{
		this.w = w;
		this.h = h;
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		t.start();
		resetGame();
		
		
		background = new Rectangle(0, 0, w, h);
		board = new Rectangle(100, 80, 640, 640);
		resetButton = new Rectangle(840, 370, 60, 60);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int tileX = 140 + 5 * 40 * j;
				int tileY = 120 + 5 * 40 * i;
				int tileW = 160;
				int tileH = 160;
				tiles[i][j] = new Rectangle(tileX, tileY, tileW, tileH);
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		if (winner == 1) {
			g2.setColor(p1Color);
		}
		else if (winner == 2) {
			g2.setColor(p2Color);
		}
		else {
			g2.setColor(backgroundColor);
		}
		g2.fill(background);
		
		g2.setColor(accentColor);
		g2.fill(board);
		
		if (winner > 0) {
			g2.setColor(backgroundColor);
		}
		else {
			g2.setColor(unpickedColor);
		}
		g2.fill(resetButton);
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (state[i][j] == 1) {
					g2.setColor(p1Color);
				}
				else if (state[i][j] == 2) {
					g2.setColor(p2Color);
				}
				else {
					if (winner > 0) {
						g2.setColor(accentColor);
					}
					else {
						g2.setColor(unpickedColor);
					}
					
				}
				g2.fill(tiles[i][j]);
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mouseX = e.getX();
		int mouseY = e.getY();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (tiles[i][j].contains(mouseX, mouseY) && state[i][j] == 0 && winner == 0) {
					state[i][j] = player;
					player = 3 - player;
					winner = checkWin();
				}
			}
		}
		if (resetButton.contains(mouseX, mouseY)) {
			resetGame();
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int checkWin() {
		for (int i = 0; i < 3; i++) {
			int x = state[i][0];
			boolean flag = true;
			for (int j = 1; j < 3 && flag; j++) {
				flag = (x == state[i][j]);
			}
			if (flag && x > 0) return x; 
		}
		
		for (int j = 0; j < 3; j++) {
			int x = state[0][j];
			boolean flag = true;
			for (int i = 1; i < 3 && flag; i++) {
				flag = (x == state[i][j]);
			}
			if (flag && x > 0) return x;
		}
		
		int x = state[0][0];
		boolean flag = true;
		for (int j = 1; j < 3 && flag; j++) {
			flag = (x == state[j][j]);
		}
		if (flag && x > 0) return x;
		
		x = state[2][0];
		flag = true;
		for (int i = 1; i < 3 && flag; i++) {
			flag = (x == state[2 - i][i]);
		}
		if (flag && x > 0) return x;
		
		return 0;
	}
	
	public void resetGame() {
		player = 1;
		winner = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				state[i][j] = 0;
			}
		}
		++games;
		p1Color = p1Colors[games%3];
		p2Color = p2Colors[games%3];
	}

}
