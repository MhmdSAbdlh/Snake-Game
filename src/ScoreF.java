import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ScoreF extends JFrame{
	

	ScoreF(){
		//Frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(640, 800);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setResizable(false);
		this.setTitle("Snake Top Scores");
		this.getContentPane().setBackground(GameIntro.greenC);
		this.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("snake.png")).getImage());
		
		//Label
		JLabel gameName = new JLabel("Scores");
		JLabel credit = new JLabel("Created with Love by MhmdSAbdlh�");
		JLabel scores[] = new JLabel[5];
		gameName.setBounds(0, 50, 640, 100);
		gameName.setHorizontalAlignment(0);
		gameName.setFont(GameIntro.myFont2);
		gameName.setForeground(GameIntro.whiteC);
		credit.setHorizontalAlignment(0);
		credit.setBounds(0, 730, 640, 20);
		credit.setForeground(Color.white);
		
		//Scores
		ArrayList<Integer> scoreNum = new ArrayList<Integer>();
		ArrayList<String> scoreName = new ArrayList<String>();
		BufferedReader SavedScore=null;
		String line ="";
		try{
			SavedScore = new BufferedReader(new FileReader(new File("Snake_Scores.csv")));
			while((line = SavedScore.readLine())!=null){
				String[] row = line.split(",");
				scoreNum.add(Integer.valueOf(row[0]));
				scoreName.add(row[1]);
			}
			SavedScore.close();
		} catch (Exception e) {}
		for (int i = 0; i < scoreNum.size(); i++)
	        for (int j = 0; j < scoreNum.size(); j++)
	            if (scoreNum.get(i) >= scoreNum.get(j)) {
	                int x = scoreNum.get(i);
	                String y = scoreName.get(i);
	                scoreNum.set(i, scoreNum.get(j));
	                scoreName.set(i, scoreName.get(j));
	                scoreNum.set(j, x);
	                scoreName.set(j, y);
	            }
		
		//Button
		JButton back = new JButton("Back");
		back.setFont(GameIntro.myFont);
		back.setBackground(GameIntro.whiteC);
		back.setForeground(GameIntro.greenC);
		back.setBorder(GameIntro.border);
		back.setBounds(120, 600, 400, 50);
		back.setFocusable(false);
		back.addActionListener((event) -> {
			this.dispose();
			new GameIntro();
			this.setVisible(false);
			});
		if(scoreNum.size()>=5)
			for(int i=0;i<5;i++) {
				scores[i] = new JLabel("");
				scores[i].setFont(new Font("Tahoma",Font.BOLD,25));
				scores[i].setBounds(50, 200+i*70, 700, 100);
				scores[i].setFocusable(false);
				scores[i].setForeground(GameIntro.whiteC);
				scores[i].setText(i+1+") "+scoreName.get(i)+" -->"+ scoreNum.get(i));
				this.add(scores[i]);
			}
		else
			for(int i=0;i<scoreNum.size();i++) {
				scores[i] = new JLabel("");
				scores[i].setFont(new Font("Tahoma",Font.BOLD,25));
				scores[i].setForeground(GameIntro.whiteC);
				scores[i].setBounds(50, 200+i*70, 700, 100);
				scores[i].setFocusable(false);
				scores[i].setText(i+1+") "+scoreName.get(i)+"-->"+ scoreNum.get(i));
				this.add(scores[i]);
			}
		
		//Add to frame
		this.add(gameName);
		this.add(back);
		this.add(credit);
		
		//Show frame
		this.getRootPane().setDefaultButton(back);
		this.setVisible(true);
		}
	}