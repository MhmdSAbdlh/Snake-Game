import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class GameIntro extends JFrame{
	
	static Font myFont = new Font("Tahoma",Font.BOLD,25);
	static Font myFont2 = new Font("Tahoma",Font.BOLD,75);
	static Color greenC = new Color(6, 68, 32);
	static Color whiteC = new Color(228, 239, 231);
	static Border border = new LineBorder(Color.white, 1);
	public static JTextField user_name;
	
	GameIntro(){
		
		//Frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 800);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setTitle("Snake");
		this.getContentPane().setBackground(greenC);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("snake.png")).getImage());
		
		//Label
		JLabel gameName = new JLabel("Snake");
		JLabel credit = new JLabel("Created with Love by MhmdSAbdlh�");
		gameName.setBounds(0, 50, 640, 100);
		gameName.setHorizontalAlignment(0);
		gameName.setFont(myFont2);
		gameName.setForeground(whiteC);
		credit.setHorizontalAlignment(0);
		credit.setBounds(0, 730, 640, 20);
		credit.setForeground(Color.white);
		
		//TextField
		user_name = new JTextField("Your Name here");
		user_name.setHorizontalAlignment(0);
		user_name.setFont(new Font("Tahoma",Font.ITALIC,20));
		user_name.setBounds(200	, 200, 220, 50);
		user_name.setBackground(whiteC);
		user_name.setForeground(greenC);
		user_name.setBorder(border);
		user_name.setSelectionStart(0);
		user_name.setSelectionEnd(14);
		
		//Button
		JButton newGame = new JButton("NEW GAME");
		JButton scores = new JButton("SCORES");
		JButton Exit = new JButton("EXIT GAME");
		newGame.setFont(myFont);
		newGame.setBounds(150, 350, 320, 50);
		newGame.setBorder(border);
		newGame.setBackground(whiteC);
		newGame.setForeground(greenC);
		newGame.setFocusable(false);
		newGame.addActionListener((event) -> {
			this.dispose();
			new GFrame();
			this.setVisible(false);
			});
		scores.setFont(myFont);
		scores.setBounds(150, 450, 320, 50);
		scores.setBorder(border);
		scores.setBackground(whiteC);
		scores.setForeground(greenC);
		scores.setFocusable(false);
		scores.addActionListener((event) -> {
			this.dispose();
			new ScoreF();
			this.setVisible(false);
		});
		Exit.setFont(myFont);
		Exit.setBounds(150, 550, 320, 50);
		Exit.setBorder(border);
		Exit.setBackground(whiteC);
		Exit.setForeground(greenC);
		Exit.setFocusable(false);
		Exit.addActionListener((event) -> System.exit(0));
		
		//Add to frame
		this.add(gameName);
		this.add(user_name);
		this.add(newGame);
		this.add(scores);
		this.add(Exit);
		this.add(credit);
		
		//Show frame
		this.getRootPane().setDefaultButton(newGame);
		this.setVisible(true);
	}
}