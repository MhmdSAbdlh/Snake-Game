import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GFrame extends JFrame{

	GFrame(){
		
		this.add(new GPanel());
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("snake.png")).getImage());
		
	}
}
