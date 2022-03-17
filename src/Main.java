import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame {
	
	public Main(int w, int h)
	{
		add(new Game(w, h));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(w, h);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setTitle("KAITACTOE");
	}
	
	public static void main(String[] args) {	
		// System.out.println(Arrays.deepToString(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
		new Main(1000, 800);
		
	}
	
}
