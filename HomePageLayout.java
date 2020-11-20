import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

public class HomePageLayout extends GUI_Page
{
	private static int PROFILE_PIC_WIDTH = 200;
	private static int LEARN_BTN_WIDTH = 150;
	private static int ROW_0_HEIGHT = 200;
	private static int ROW_1_HEIGHT = 150;	//
	private static int ROW_2_HEIGHT;

	public HomePageLayout(){
		ROW_2_HEIGHT = GUI.SCREEN_HEIGHT - (GUI.BUFFER_SIZE_LG*5 + ROW_0_HEIGHT + ROW_1_HEIGHT);
	}
	
	public void activate(){
		
		//JPanel homepage_container = new JPanel();
		//GUI.addComponent(homepage_container);
		/*
		+
		-profile_pic
		+
		-progress
		+
		-rewards
		+
		*/
		JLabel title = new JLabel("Home Page");
		title.setSize( GUI.TITLE_LABEL_WIDTH, GUI.TITLE_LABEL_HEIGHT );
		title.setLocation( (GUI.SCREEN_WIDTH/2) - (GUI.TITLE_LABEL_WIDTH/2), GUI.BUFFER_SIZE );
		title.setForeground( Color.white );
		GUI.addComponent( title );
		
		//Profile Pic
		JLabel profile_pic = new JLabel("ProfilePic");
		profile_pic.setSize(PROFILE_PIC_WIDTH, ROW_0_HEIGHT);
		profile_pic.setLocation(GUI.BUFFER_SIZE_LG, GUI.BUFFER_SIZE_LG);
		profile_pic.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		GUI.addComponent( profile_pic );
		//homepage_container.add(profile_pic, gbc);
		
		//Profile Desc
		JLabel profile_desc = new JLabel("ProfileDesc");
		profile_desc.setSize(GUI.SCREEN_WIDTH - (PROFILE_PIC_WIDTH + (GUI.BUFFER_SIZE_LG)*3), ROW_0_HEIGHT);
		profile_desc.setLocation(PROFILE_PIC_WIDTH + (GUI.BUFFER_SIZE_LG)*2, GUI.BUFFER_SIZE_LG );
		profile_desc.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		GUI.addComponent( profile_desc );
		//homepage_container.add(profile_desc, gbc);
		
		//Progress
		JLabel progress = new JLabel("Progress");
		int progressWidth = GUI.SCREEN_WIDTH - (GUI.BUFFER_SIZE_LG*3 + LEARN_BTN_WIDTH);
		int progressStartY = ROW_0_HEIGHT + GUI.BUFFER_SIZE_LG*2;
		progress.setSize(progressWidth, ROW_1_HEIGHT);
		progress.setLocation(GUI.BUFFER_SIZE_LG, progressStartY);
		progress.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		GUI.addComponent( progress );
		//homepage_container.add(progress, gbc);
		
		//Learning
		DashPart learning = new DashPart("Learning", GUI.GRID_ID);
		learning.setSize(LEARN_BTN_WIDTH, ROW_1_HEIGHT);
		learning.setLocation(progressWidth + GUI.BUFFER_SIZE_LG*2, progressStartY);
		GUI.addComponent( learning );
		//homepage_container.add(learning, gbc);
		
		//Rewards
		JLabel rewards = new JLabel("Rewards");
		rewards.setSize( GUI.SCREEN_WIDTH - GUI.BUFFER_SIZE_LG*2, ROW_2_HEIGHT );
		rewards.setLocation( GUI.BUFFER_SIZE_LG, progressStartY + ROW_1_HEIGHT + GUI.BUFFER_SIZE_LG );
		rewards.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		GUI.addComponent( rewards );
		//homepage_container.add(rewards, gbc);
		
		//Exit button
		
		//homepage_container.revalidate();	//refresh grid container panel
		//homepage_container.repaint();
		//GUI.refreshRootFrame();
		//homepage_container.setVisible(true);
		//.setBorder(new LineBorder(Color.LIGHT_GRAY, 2)); //for each panel
		/*// Tutorial Button - For Testing
		JButton tutorial_button = new JButton("Geometry Tutorial 1");
		tutorial_button.addActionListener( new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				GUI_Manager.loadPage("g4t1"); //hard coded for testing
			}
		} );
		tutorial_button.setSize( 150, 150 );
		tutorial_button.setLocation( 100, 100 );
		tutorial_button.setForeground( Color.green );
		GUI.addComponent( tutorial_button );*/
	}
}

class DashPart extends JPanel
{
	private String pageKey;
	private Color defaultBgColor;
	private Color defaultFgColor;
	
	public DashPart(String buttonName, String pageKey) {
		add(new JLabel(buttonName));
		this.pageKey = pageKey;
		
		defaultBgColor = getBackground();
		defaultFgColor = getForeground();
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GUI_Manager.loadPage(pageKey);
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				setBackground(Color.GRAY);
				setForeground(Color.white);
			}
			public void mouseExited(MouseEvent evt) {
				setBackground(defaultBgColor);
				setForeground(defaultFgColor);
			}
      });
	  setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
	}
}