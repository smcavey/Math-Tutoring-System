//HomePageLayout and DashPart create the user profile image, rewards, progress, learning, and user profile information
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;
import java.nio.file.Paths;
import java.net.URL;
import java.net.URI; 
import java.nio.file.Path; 
import java.nio.file.Paths;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Map.Entry;
import java.awt.Color;

public class HomePageLayout extends GUI_Page
{
	private static int PROFILE_PIC_WIDTH = 200;
	private static int LEARN_BTN_WIDTH = 250;
	private static int ROW_0_HEIGHT = 200;
	private static int ROW_1_HEIGHT = 150;
	private static int MAGIC_OFFSET = 15;
	private static int ROW_2_HEIGHT;
	
	private int rewardSubContainerWidth = 225;
	private int userNameWidth = 400;
	private int userNameHeight = 40;
	
	private int defaultBorderSize = 2;
	private int expandedBorderSize = 3;
	private int textBorderSize = 10;
	
	private int progressTitleWidth = 250;

	private String learnImage = "learn_image.png";
	
	private int exitButtonSize = 36;
	
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
		JLabel profile_pic = new JLabel(Resource_Manager.loadImageLocal(UserProfile.getProfileImagePath()));
		profile_pic.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser(System.getProperty("java.class.path") + "/resc/profile");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE FILES", "png", "jpeg", "jpg");
				fileChooser.setFileFilter(filter);
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					java.io.File f = fileChooser.getSelectedFile();
					UserProfile.setProfileImagePath( f.getName() );
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
		
		
		
		
		//*******************************************Profile Desc**********************************************
		JPanel profile_desc = new JPanel();
		profile_desc.setSize( GUI.SCREEN_WIDTH - (PROFILE_PIC_WIDTH + GUI.BUFFER_SIZE_LG * 3 + MAGIC_OFFSET), ROW_0_HEIGHT );
		profile_desc.setLocation( PROFILE_PIC_WIDTH + GUI.BUFFER_SIZE_LG*2, GUI.BUFFER_SIZE_LG );
		profile_desc.setBorder( new LineBorder(Color.LIGHT_GRAY, defaultBorderSize) );
		profile_desc.setLayout(new BoxLayout( profile_desc, BoxLayout.Y_AXIS));
		profile_desc.setBackground(Color.black);
		profile_desc.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//TODO: if time, add some type of functionality??
			}
			public void mouseEntered(MouseEvent evt) {
				profile_desc.setBorder(new LineBorder(Color.white, expandedBorderSize));
			}
			public void mouseExited(MouseEvent evt) {
				profile_desc.setBorder(new LineBorder(Color.LIGHT_GRAY, defaultBorderSize));
			}
		});
		GUI.addComponent( profile_desc );
		
		profile_desc.add(Box.createRigidArea(new Dimension(0, GUI.BUFFER_SIZE)));//top padding
		
		JPanel profileDescTopContainer = new JPanel();
		profileDescTopContainer.setOpaque(false);
		profileDescTopContainer.setLayout( new BoxLayout(profileDescTopContainer, BoxLayout.X_AXIS) );
		profile_desc.add( profileDescTopContainer );
		//user name label
		
		profileDescTopContainer.add(Box.createRigidArea(new Dimension(GUI.BUFFER_SIZE_LG, 0)));//left edge padding
		JLabel user_name = new JLabel( UserProfile.userName, SwingConstants.LEFT );
		user_name.setFont( new Font("Calibri", Font.BOLD, 40) );
		user_name.setForeground( Color.white );
		user_name.setBorder(new EmptyBorder(textBorderSize, textBorderSize, textBorderSize, textBorderSize));
		//user_name.setLocation(GUI.BUFFER_SIZE_LG, GUI.BUFFER_SIZE_LG);
		profileDescTopContainer.add( user_name );
		
		profileDescTopContainer.add( Box.createHorizontalGlue() );
		
		//Exit Button
		Dimension dim = new Dimension( exitButtonSize, exitButtonSize );
		JButton exitButton = new JButton();
		exitButton.addActionListener( new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				System.exit(0);
		}});
		exitButton.setPreferredSize(dim);
		exitButton.setMinimumSize(dim);
		exitButton.setMaximumSize(dim);
		exitButton.setBackground(Color.black);
		exitButton.setIcon( Resource_Manager.loadResourceScaled("exit", exitButtonSize, exitButtonSize) );
		//exitButton.setSize( exitButtonSize, exitButtonSize );
		//exitButton.setLocation( GUI.SCREEN_WIDTH - (exitButtonSize*2 + GUI.BUFFER_SIZE), GUI.BUFFER_SIZE );
		profileDescTopContainer.add(exitButton);
		
		profileDescTopContainer.add(Box.createRigidArea(new Dimension(GUI.BUFFER_SIZE_LG, 0))); //Right edge padding
		
		//spacing between elements
		profile_desc.add( Box.createVerticalGlue() );
		
		JPanel profileDescBottomContainer = new JPanel();
		profileDescBottomContainer.setOpaque(false);
		profileDescBottomContainer.setLayout( new BoxLayout(profileDescBottomContainer, BoxLayout.X_AXIS) );
		profile_desc.add( profileDescBottomContainer );

		profileDescBottomContainer.add(Box.createRigidArea(new Dimension(GUI.BUFFER_SIZE_LG, 0))); //Left edge padding
		//user name label
		JLabel m_msg = new JLabel(Resource_Manager.getRandomMessage());//, SwingConstants.LEFT);
		m_msg.setFont(new Font("Calibri", Font.PLAIN, 28));
		m_msg.setForeground(Color.white);
		m_msg.setBorder(new EmptyBorder(textBorderSize, textBorderSize, textBorderSize, textBorderSize));
		//m_msg.setBorder(new LineBorder(Color.red, 2));
		//m_msg.setAlignmentX(Component.LEFT_ALIGNMENT);
		profileDescBottomContainer.add( m_msg );
		
		profileDescBottomContainer.add( Box.createHorizontalGlue() );
		
		
		
		//*********************************Progress*********************************
		DashPart progress = new DashPart(GUI.PROGRESS_ID);
		int progressWidth = GUI.SCREEN_WIDTH - (GUI.BUFFER_SIZE_LG*3 + LEARN_BTN_WIDTH);
		int progressStartY = ROW_0_HEIGHT + GUI.BUFFER_SIZE_LG*2;
		progress.setSize(progressWidth, ROW_1_HEIGHT);
		progress.setLocation(GUI.BUFFER_SIZE_LG, progressStartY);
		progress.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		
		progress.setLayout(new BoxLayout(progress, BoxLayout.X_AXIS));
		
		progress.add(Box.createRigidArea(new Dimension(GUI.BUFFER_SIZE_LG,0)));
		
		JLabel progressTitle = new JLabel("Progress");
		progressTitle.setFont(new Font("Calibri", Font.BOLD, 36));
		progressTitle.setForeground(Color.white);
		//progressTitle.setBorder(new LineBorder(Color.red, 2));
		Dimension titleSize = new Dimension(progressTitleWidth, ROW_1_HEIGHT);
		progressTitle.setMaximumSize(titleSize);
		progressTitle.setPreferredSize(titleSize);
		progressTitle.setMinimumSize(titleSize);
		progress.add(progressTitle);
		
		progress.add(Box.createRigidArea(new Dimension(GUI.BUFFER_SIZE_LG*2,0)));
		
		JLabel progressDesc = new JLabel("<html>Recent<br>Activity:</html>");
		progressDesc.setFont(new Font("Calibri", Font.BOLD, 28));
		progressDesc.setForeground(Color.white);
		//progressDesc.setBorder(new LineBorder(Color.red, 2));
		titleSize = new Dimension(progressTitleWidth, ROW_1_HEIGHT);
		progressDesc.setMaximumSize( titleSize );
		progressDesc.setPreferredSize( titleSize );
		progressDesc.setMinimumSize( titleSize );
		progress.add( progressDesc );

		progress.add(Box.createRigidArea(new Dimension(GUI.BUFFER_SIZE_LG*2,0)));
		
		JPanel progressDescContainer = new JPanel();
		progressDescContainer.setLayout(new GridLayout(3,1));
		//progressDescContainer.setBorder(new LineBorder(Color.red, 2));
		progressDescContainer.setOpaque(false);
		progress.add(progressDescContainer);
		

		progress.add(Box.createRigidArea(new Dimension(GUI.BUFFER_SIZE_LG,0)));
		int cnt = 0;
		for(Entry<String, Integer> kv : UserProfile.scores.entrySet()){
			if(cnt++ >= 3) break;
			
			JPanel labelContainer = new JPanel();
			labelContainer.setLayout(new BoxLayout(labelContainer, BoxLayout.X_AXIS));
			labelContainer.setOpaque(false);
			//System.out.println(kv.getKey());
			Module tempModule = Resource_Manager.modules.get(kv.getKey());
			JLabel moduleNameLabel = new JLabel(tempModule.topic + " " + tempModule.subTopic);
			moduleNameLabel.setForeground( Color.white );
			moduleNameLabel.setFont(new Font("Calibri", Font.BOLD, 22));
			//moduleNameLabel.setBorder(new LineBorder(Color.red, 2));
			
			JLabel scoreLabel = new JLabel(kv.getValue().toString());
			scoreLabel.setForeground( Color.white );
			scoreLabel.setFont(new Font("Calibri", Font.BOLD, 24));
			//scoreLabel.setBorder(new LineBorder(Color.red, 2));
			
			labelContainer.add(moduleNameLabel);
			labelContainer.add(Box.createHorizontalGlue());
			labelContainer.add(scoreLabel);


			progressDescContainer.add(labelContainer);
		}
		
		GUI.addComponent( progress );



		
		//*********************************Learning*********************************
		DashPart learning = new DashPart(GUI.GRID_ID); //originally ("Learning, GUI.GRID_ID);
		learning.setSize(LEARN_BTN_WIDTH - MAGIC_OFFSET, ROW_1_HEIGHT);
		learning.setLocation(progressWidth + GUI.BUFFER_SIZE_LG*2, progressStartY);
		
		JLabel learnText = new JLabel("LEARN");
		learnText.setForeground(Color.white);
		learnText.setFont( new Font("Calibri", Font.BOLD, 24));
		learning.add(learnText);
		
		learning.add(new JLabel(Resource_Manager.loadImageLocal(learnImage)));
		GUI.addComponent( learning );
		
		//homepage_container.add(learning, gbc);
		
		//***********************************Rewards***********************************************
		DashPart rewards = new DashPart(GUI.REWARD_ID);
		rewards.setLayout(new BorderLayout());
		int rewardDashpartHeight = ROW_2_HEIGHT - MAGIC_OFFSET;
		int rewardDashpartWidth = GUI.SCREEN_WIDTH - (GUI.BUFFER_SIZE_LG * 2 + MAGIC_OFFSET);
		rewards.setSize( rewardDashpartWidth, rewardDashpartHeight );
		rewards.setLocation( GUI.BUFFER_SIZE_LG, progressStartY + ROW_1_HEIGHT + GUI.BUFFER_SIZE_LG );
		rewards.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		
		JLabel rewardsTitle = new JLabel("Rewards", SwingConstants.CENTER);
		rewardsTitle.setForeground( Color.white );
		rewardsTitle.setFont(new Font("Calibri", Font.BOLD, 24));
		rewards.add(rewardsTitle, BorderLayout.PAGE_START);
		
		JPanel rewardListContainer = new JPanel();
		rewards.add(rewardListContainer, BorderLayout.CENTER);
		
		rewardListContainer.setOpaque(false);
		rewardListContainer.setLayout(new BoxLayout(rewardListContainer, BoxLayout.X_AXIS));
		//rewardListContainer.setBorder( new LineBorder(Color.red, 2) );
		int rewardPanelHeight = rewardDashpartHeight - GUI.BUFFER_SIZE_LG*2;
		//rewardListContainer.setPreferredSize( new Dimension( rewardDashpartWidth - GUI.BUFFER_SIZE_LG, rewardPanelHeight));
		int boundsX = 0;
		int padX = 14;
		int padY = 10;
		//int cnt = 0;
		rewardListContainer.add(Box.createRigidArea(new Dimension(padX,0)));
		Dimension size = new Dimension(rewardSubContainerWidth, rewardPanelHeight);
		for(Entry<String, Reward> entry : RewardLayout.rewards.entrySet())
		{
			//System.out.printf("%d : %d\n", cnt++, boundsX);
			if(boundsX >= rewardDashpartWidth - rewardSubContainerWidth)
			{
				break;
			}
			
			JPanel rewardSubContainer = new JPanel();
			//rewardSubContainer.setOpaque(false);
			rewardSubContainer.setLayout(new BoxLayout(rewardSubContainer, BoxLayout.Y_AXIS));
			rewardSubContainer.setOpaque(false);
			rewardSubContainer.setMaximumSize(size);
			rewardSubContainer.setPreferredSize(size);
			rewardSubContainer.setMinimumSize(size);
			boundsX += rewardSubContainerWidth + padX;
			//rewardSubContainer.setBackground( Color.black );
			rewardSubContainer.setBorder(new LineBorder(Color.PINK, 2));
			
			
			JLabel rewardDesc = new JLabel(entry.getValue().rewardDesc);
			rewardDesc.setForeground(Color.white);
			rewardDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
			rewardDesc.setFont(new Font("Calibri", Font.BOLD, 20));
			
			int rewardImageSize = rewardSubContainerWidth/2;
			JLabel rewardsImage = new JLabel( Resource_Manager.loadResourceScaled( entry.getValue().getImagePath(), rewardImageSize, rewardImageSize ));
			rewardsImage.setAlignmentX(Component.CENTER_ALIGNMENT);
			//rewardsImage.setPreferredSize(new Dimension(RewardLayout.achievementHeight,RewardLayout.achievementHeight));
			if(entry.getValue().earned)
			{
				rewardDesc.setBackground( Color.black );
				rewardDesc.setForeground( Color.PINK );
				rewardsImage.setBackground( Color.black );
			}
			rewardSubContainer.add(Box.createRigidArea(new Dimension(0,padY)));
			rewardSubContainer.add(rewardsImage);
			rewardSubContainer.add(Box.createRigidArea(new Dimension(0,padY)));
			rewardSubContainer.add(rewardDesc);
			rewardListContainer.add(rewardSubContainer);
			rewardListContainer.add(Box.createRigidArea(new Dimension(padX,0)));
			/* else
			{
				rewardDesc.setBackground( Color.gray );
				rewardDesc.setForeground( Color.darkGray );
			} */
			
		}

		GUI.addComponent( rewards ); 
		//homepage_container.add(rewards, gbc);
		
		//Exit button
	}
}

class DashPart extends JPanel
{
	private String pageKey;
	private Color defaultBgColor;
	private Color defaultFgColor;
	
	public DashPart(String pageKey) {
		//add(new JPanel(buttonName));
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