import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.*;
import java.util.Map.Entry;
import javax.swing.border.LineBorder;

public class ProgressLayout extends GUI_Page
{
	private static int GRADE_BUTTON_WIDTH = 50;
	private static int GRADE_BUTTON_HEIGHT = 50;
	private static int GRADE_BUTTON_X_ORIGIN = 50;
	private static int GRADE_BUTTON_Y_ORIGIN = 50;
	private static int INFO_LABEL_WIDTH = 800;
	private static int INFO_LABEL_HEIGHT = 50;
	private static int INFO_LABEL_X_ORIGIN = 400;
	private static int INFO_LABEL_Y_ORIGIN = 100;
	
	private int tileHeight = 50;
	private int titleWidth = 400;
	
	private JPanel resultsContainer;
	private GridLayout gridLayout;
	
	public ProgressLayout()
	{

	}
	
	public void activate()
	{
		// Page Title
		JLabel title = new JLabel(UserProfile.userName + "'s Progress", SwingConstants.CENTER);
		title.setFont(new Font("Calibri", Font.PLAIN, 36));
		title.setSize( titleWidth, tileHeight );
		title.setLocation( (GUI.SCREEN_WIDTH/2) - (titleWidth/2), GUI.BUFFER_SIZE_LG );
		title.setForeground( Color.white );
		title.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) ); //for debug
		GUI.addComponent(title);
		
		//home button
		GUI.addComponent( new HomeButton() );
		
		
		String [] grades = new String[]{"4", "3", "2", "1", "k"};
		JPanel filterPanel = new JPanel();
		filterPanel.setBackground(Color.black);
		filterPanel.setLayout(new java.awt.GridLayout(grades.length,1,10,10));
		filterPanel.setSize(GUI.SCREEN_WIDTH/8, GUI.SCREEN_HEIGHT - (GUI.SCREEN_HEIGHT/4) );
		filterPanel.setLocation(GUI.BUFFER_SIZE_LG, tileHeight + GUI.BUFFER_SIZE_LG*2);
		GUI.addComponent(filterPanel);
		//filterPanel.setOpaque(true);
		
		JPanel progressBars = new JPanel();
		progressBars.setBackground(Color.black);
		progressBars.setLayout(new java.awt.GridLayout(grades.length,1,10,10));
		progressBars.setSize(GUI.SCREEN_WIDTH/4, GUI.SCREEN_HEIGHT - (GUI.SCREEN_HEIGHT/4) );
		progressBars.setLocation(GUI.SCREEN_WIDTH/6,tileHeight + GUI.BUFFER_SIZE_LG*2);
		//progressBars.setBorder( new LineBorder(Color.blue, 2));
		GUI.addComponent(progressBars);
		for(int i = 0; i < 5; i++)
		{
			JLabel bar = new JLabel();
			bar.setBackground( Color.white );
			bar.setBorder(new LineBorder(Color.blue, 2));
			progressBars.add(bar);
		}
		
		//grade filtering buttons		

		for(int i = 0; i < grades.length; i++)
		{
			JLabel grade = new JLabel(grades[i], SwingConstants.CENTER);
			//grade.setSize(GRADE_BUTTON_WIDTH, GRADE_BUTTON_HEIGHT);
			//grade.setLocation(GRADE_BUTTON_X_ORIGIN, GRADE_BUTTON_Y_ORIGIN * (i+1) + (GUI.BUFFER_SIZE_LG*(i+1)));
			//grade.setOpaque(true);
			grade.setForeground( Color.white );
			grade.setBackground( Color.black );
			grade.setFont(new Font("Calibri", Font.PLAIN, 48));
			grade.setBorder(new LineBorder(Color.blue, 2));
			
			String temp = grades[i];
			grade.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					loadGradeStats(temp);
				}
				public void mouseEntered(MouseEvent evt) {
					grade.setForeground( Color.yellow );
					grade.setBorder(new LineBorder(Color.green, 3));
				}
				public void mouseExited(MouseEvent evt) {
					grade.setForeground( Color.white );
					grade.setBorder(new LineBorder(Color.blue, 2));
				}
			});
			filterPanel.add( grade );
		}

		
		resultsContainer = new JPanel();
		resultsContainer.setBackground( Color.black );
		resultsContainer.setBorder( new LineBorder(Color.PINK, 2) );
		resultsContainer.setLocation(GUI.SCREEN_WIDTH/2 - GUI.BUFFER_SIZE_LG*3 - GUI.BUFFER_SIZE*2, tileHeight + GUI.BUFFER_SIZE_LG*2 );
		resultsContainer.setSize(GUI.SCREEN_WIDTH/2 + GUI.BUFFER_SIZE_LG*2, GUI.SCREEN_HEIGHT - (GUI.SCREEN_HEIGHT/4));
		GUI.addComponent(resultsContainer);
	}
	
	private void loadGradeStats(String grade)
	{
		if(resultsContainer.getComponentCount() > 0) {
			resultsContainer.removeAll();
		}
		
		System.out.printf("Loading Grade %s stats...\nNum Scores: %d\n", grade, UserProfile.scores.size());
		ArrayList <ResultInfo> results = new ArrayList<ResultInfo>();
		
		//gridLayout = new GridLayout(results.size(), 1);
		//gridLayout.setVgap( GUI.BUFFER_SIZE );
		resultsContainer.setLayout(new BoxLayout( resultsContainer, BoxLayout.Y_AXIS));
		
		for (Entry<String, Integer> entry : UserProfile.scores.entrySet())
		{
			System.out.println(entry.getKey());
			String grd = entry.getKey().substring(1,2); //Grade
			//String typ = entry.getKey().substring(2,3);
			String top = entry.getKey().substring(0,1);	//Topic
			System.out.printf("Grade: %s  Topic: %s\n", grd, top);
			int scr = entry.getValue();
			if(grd.equals( grade ))
			{
				results.add(new ResultInfo(top, scr));
			}
		}

		for(int i = 0; i < results.size(); i++)
		{
			ResultInfo r = results.get(i);
			JLabel info = new JLabel("Topic: " + r.topic + " Score: " + r.score);
			info.setSize(INFO_LABEL_WIDTH, INFO_LABEL_HEIGHT);
			info.setLocation(INFO_LABEL_X_ORIGIN, INFO_LABEL_Y_ORIGIN + (i * INFO_LABEL_HEIGHT));
			info.setFont(new Font("Serif", Font.PLAIN, 36));
			//info.setOpaque(true);
			info.setBackground( Color.black );
			info.setForeground( Color.white );
			resultsContainer.add( info );
		}

		resultsContainer.revalidate();
	}
}

class ResultInfo
{
	public String topic;
	public int score;
	public ResultInfo(String topic, int score)
	{
		this.topic = topic;
		this.score = score;
	}
}
