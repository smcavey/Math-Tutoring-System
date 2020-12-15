import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.nio.file.Paths;
import java.net.URL;
import java.net.URI; 
import java.nio.file.Path; 
import java.nio.file.Paths;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HomePageLayout extends GUI_Page
{
	private static int PROFILE_PIC_WIDTH = 200;
	private static int LEARN_BTN_WIDTH = 150;
	private static int ROW_0_HEIGHT = 200;
	private static int ROW_1_HEIGHT = 150;
	private static int MAGIC_OFFSET = 15;
	private static int ROW_2_HEIGHT;
	
	private int userNameWidth = 400;
	private int userNameHeight = 40;
	
	private int defaultBorderSize = 2;
	private int expandedBorderSize = 3;

	private String learnImage = "learn_image.png";
	
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
		
		//Profile Pic
		JLabel profile_pic = new JLabel(Resource_Manager.loadImageLocal(UserProfile.profileImagePath));
		profile_pic.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser(System.getProperty("java.class.path"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE FILES", "png", "jpeg", "jpg");
				fileChooser.setFileFilter(filter);
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					java.io.File f = fileChooser.getSelectedFile();
					UserProfile.profileImagePath = f.getName();
					GUI_Manager.loadPage(GUI.HOME_ID);
				}
			}
			public void mouseEntered(MouseEvent evt) {
				profile_pic.setBorder(new LineBorder(Color.white, expandedBorderSize));
			}
			public void mouseExited(MouseEvent evt) {
				profile_pic.setBorder(new LineBorder(Color.LIGHT_GRAY, defaultBorderSize));
			}
		});
			
		profile_pic.setSize(PROFILE_PIC_WIDTH, ROW_0_HEIGHT);
		profile_pic.setLocation(GUI.BUFFER_SIZE_LG, GUI.BUFFER_SIZE_LG);
		profile_pic.setBorder(new LineBorder(Color.LIGHT_GRAY, defaultBorderSize));
		GUI.addComponent( profile_pic );
		
		//Profile Desc
		JPanel profile_desc = new JPanel();
		profile_desc.setSize( GUI.SCREEN_WIDTH - (PROFILE_PIC_WIDTH + GUI.BUFFER_SIZE_LG * 3 + MAGIC_OFFSET), ROW_0_HEIGHT );
		profile_desc.setLocation( PROFILE_PIC_WIDTH + GUI.BUFFER_SIZE_LG*2, GUI.BUFFER_SIZE_LG );
		profile_desc.setBorder( new LineBorder(Color.LIGHT_GRAY, defaultBorderSize) );
		profile_desc.setLayout(new BoxLayout( profile_desc, BoxLayout.Y_AXIS));
		profile_desc.setBackground(Color.black);
		profile_desc.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//TODO: Implement 
			}
			public void mouseEntered(MouseEvent evt) {
				profile_desc.setBorder(new LineBorder(Color.white, expandedBorderSize));
			}
			public void mouseExited(MouseEvent evt) {
				profile_desc.setBorder(new LineBorder(Color.LIGHT_GRAY, defaultBorderSize));
			}
		});
		GUI.addComponent( profile_desc );
		
		//user name label
		JLabel user_name = new JLabel(UserProfile.userName, SwingConstants.LEFT);
		user_name.setFont(new Font("Calibri", Font.PLAIN, 38));
		user_name.setForeground(Color.white);
		profile_desc.add( user_name );
		
		profile_desc.add(Box.createVerticalGlue());
		
		//user name label
		JLabel m_msg = new JLabel(Resource_Manager.getRandomMessage(), SwingConstants.LEFT);
		m_msg.setFont(new Font("Calibri", Font.PLAIN, 26));
		m_msg.setForeground(Color.white);
		profile_desc.add( m_msg );
		
		//Progress
		DashPart progress = new DashPart("Progress", GUI.PROGRESS_ID);
		int progressWidth = GUI.SCREEN_WIDTH - (GUI.BUFFER_SIZE_LG*3 + LEARN_BTN_WIDTH);
		int progressStartY = ROW_0_HEIGHT + GUI.BUFFER_SIZE_LG*2;
		progress.setSize(progressWidth, ROW_1_HEIGHT);
		progress.setLocation(GUI.BUFFER_SIZE_LG, progressStartY);
		progress.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		GUI.addComponent( progress );
		//homepage_container.add(progress, gbc);
		
		//Learning
		DashPart learning = new DashPart("Learning", GUI.GRID_ID);
		learning.setSize(LEARN_BTN_WIDTH - MAGIC_OFFSET, ROW_1_HEIGHT);
		learning.setLocation(progressWidth + GUI.BUFFER_SIZE_LG*2, progressStartY);
		learning.add(new JLabel(Resource_Manager.loadImageLocal(learnImage)));
		GUI.addComponent( learning );
		//homepage_container.add(learning, gbc);
		
		//Rewards
		DashPart rewards = new DashPart("Rewards", GUI.REWARD_ID);
		rewards.setSize( GUI.SCREEN_WIDTH - (GUI.BUFFER_SIZE_LG * 2 + MAGIC_OFFSET), ROW_2_HEIGHT - MAGIC_OFFSET );
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
		setBackground(Color.black); //Need to change if adding images
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