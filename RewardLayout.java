//RewardLayout sets up the GUI elements of the rewards page
//RewardLayout contains the buttons to populate the more detailed information of rewards
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.border.LineBorder;
import java.util.Map.Entry;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RewardLayout extends GUI_Page
{
	private JPanel awardDesc;
	public static int achievementWidth = 300;
	public static int achievementHeight = 60;
	private int recentAwardsWidth = 500;
	private int recentAwardsHeight = 300;
	private int awardDescWidth = 500;
	private int awardDescHeight = 300;
	private String tempImage = "scrsht.png";
	
	private int scaledRewardSize = 200; //make relative
	
	public static HashMap<String, Reward> rewards = new HashMap <String, Reward>();
	
	public RewardLayout()
	{
		
	}
	public void activate()
	{
		// Page Title
		JLabel title = new JLabel("Rewards");
		title.setSize( GUI.TITLE_LABEL_WIDTH, GUI.TITLE_LABEL_HEIGHT );
		title.setFont(new Font("Calibri", Font.BOLD, 24));
		title.setLocation( (GUI.SCREEN_WIDTH/2) - (GUI.TITLE_LABEL_WIDTH/2), GUI.BUFFER_SIZE );
		title.setForeground( Color.white );
		GUI.addComponent(title);
		
		//home button
		GUI.addComponent( new HomeButton() );
		
		int xOff = GUI.BUFFER_SIZE_LG*13;
		int yOff = GUI.BUFFER_SIZE_LG*3 + GUI.BUFFER_SIZE*2;
		
		int i = 0;
		int k = 0;
		for(Entry<String, Reward> entry : rewards.entrySet())
		{
			JPanel rewardsPanel = new JPanel();
			rewardsPanel.setLayout(new BorderLayout());
			
			//Text component of the Reward button
			JButton rewardDesc = new JButton(entry.getValue().rewardDesc);
			rewardDesc.setFont(new Font("Dialog", Font.BOLD, 16));
			if(entry.getValue().earned)
			{
				rewardDesc.setBackground( Color.black );
				rewardDesc.setForeground( Color.white );
			}
			else
			{
				rewardDesc.setBackground( Color.gray );
				rewardDesc.setForeground( Color.darkGray );
			}
			String rewardKey = entry.getKey();
			rewardDesc.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				loadDescrption(rewardKey);
				}
			} );
			rewardsPanel.add(rewardDesc);
			
			//Image component of the reward panel
			JLabel rewardsImage = new JLabel(entry.getValue().getImage());
			rewardsImage.setPreferredSize(new Dimension(achievementHeight,achievementHeight));
			rewardsPanel.add(rewardsImage, BorderLayout.EAST);
			rewardsPanel.setLocation(GUI.BUFFER_SIZE_LG +GUI.BUFFER_SIZE*3 + xOff*k,GUI.BUFFER_SIZE_LG*2 + yOff*i);
			rewardsPanel.setSize(achievementWidth, achievementHeight);
			GUI.addComponent(rewardsPanel);
			
			i++;
			if(i>=6)
			{
				i = 0;
				k = 1;
			}
			System.out.println("k: " + k);
		}
		
		JPanel btnContainer = new JPanel();
		btnContainer.setBackground( Color.black );
		
		GridLayout btnLayout = new GridLayout(1,3);
		btnLayout.setHgap(GUI.BUFFER_SIZE_LG);
		btnLayout.setVgap(GUI.BUFFER_SIZE_LG);
		
		btnContainer.setLayout(btnLayout);
		btnContainer.setLocation(GUI.SCREEN_WIDTH/3 - (GUI.NAVBUTTON_WIDTH*2 + GUI.BUFFER_SIZE_LG*3 + GUI.BUFFER_SIZE*3), GUI.SCREEN_HEIGHT - GUI.BUFFER_SIZE_LG*5);
		btnContainer.setSize(GUI.NAVBUTTON_WIDTH*4 + GUI.BUFFER_SIZE_LG, GUI.NAVBUTTON_HEIGHT + GUI.BUFFER_SIZE_LG);
		
		ActionButton backbtn = new ActionButton("BCK");
		backbtn.addIcon( "back_image" );
		backbtn.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//TODO nav action
			}
		} );
		btnContainer.add( backbtn );
		
		ActionButton nextbtn = new ActionButton("NEXT");
		nextbtn.addIcon( "forward_image" );
		nextbtn.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//TODO nav action
			}
		} );
		btnContainer.add( nextbtn );
		
		ActionButton printbtn = new ActionButton("Print");
		printbtn.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try 
				{
					Robot robot = new Robot();
					String format = ".png";
					Rectangle screenRect = GUI.root.getBounds();//new Rectangle(5,5,GUI.SCREEN_WIDTH-5, GUI.SCREEN_HEIGHT-5);
					BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
					ImageIO.write(screenFullImage, format, new File(tempImage + format));
					if(GUI.DEBUG) System.out.println("Screenshot captured");
					new Thread(new PrintActionListener(screenFullImage)).start();
				} catch (AWTException | IOException ex) {
					System.err.println(ex);
				}
			}
		} );
		btnContainer.add( printbtn );
		GUI.addComponent(btnContainer);
		
		JPanel recentAwards = new JPanel();
		recentAwards.setLayout(new BoxLayout(recentAwards, BoxLayout.Y_AXIS));
		recentAwards.setBackground( Color.black );
		recentAwards.setBorder(new LineBorder(Color.PINK, 2));
		recentAwards.setSize(recentAwardsWidth, recentAwardsHeight);
		recentAwards.setLocation(GUI.SCREEN_WIDTH/2 + GUI.BUFFER_SIZE_LG*3 ,GUI.SCREEN_HEIGHT/4 - GUI.BUFFER_SIZE_LG*5 - GUI.BUFFER_SIZE);
		JLabel recAwd = new JLabel("Recent Awards");
		recAwd.setAlignmentX(Component.CENTER_ALIGNMENT);
		recAwd.setForeground( Color.PINK );
		recentAwards.add(recAwd);
		int numberOfAwardsInRecentAwardsPanel = 1;
		for(Entry<String, Reward> entry : rewards.entrySet())
		{
			if(numberOfAwardsInRecentAwardsPanel == 3)
			{
				break;
			}
			
			JPanel rewardsPanel = new JPanel();
			recentAwards.add(Box.createRigidArea(new Dimension(0,GUI.BUFFER_SIZE)));
			//rewardsPanel.setBorder(new LineBorder( Color.PINK, 2));
			rewardsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
			rewardsPanel.setPreferredSize(new Dimension(achievementWidth,achievementHeight));
			rewardsPanel.setBackground( Color.black );
			rewardsPanel.setLayout(new BoxLayout(rewardsPanel, BoxLayout.X_AXIS));
			JLabel rewardDesc = new JLabel(entry.getValue().rewardDesc);
			if(entry.getValue().earned)
			{
				rewardDesc.setBackground( Color.black );
				rewardDesc.setForeground( Color.white );
				numberOfAwardsInRecentAwardsPanel++;
				String rewardKey = entry.getKey();
				rewardsPanel.add(rewardDesc);
				JLabel rewardsImage = new JLabel(entry.getValue().getImage());
				rewardsImage.setPreferredSize(new Dimension(achievementHeight,achievementHeight));
				rewardsPanel.add(rewardsImage, BorderLayout.EAST);
				recentAwards.add(rewardsPanel);
			}
		}
		//comp.setMinimumSize(new Dimension(50, 25));
		GUI.addComponent(recentAwards);		
		
		awardDesc = new JPanel();
		awardDesc.setLayout(new BoxLayout(awardDesc, BoxLayout.Y_AXIS));
		awardDesc.setBackground( Color.black );
		awardDesc.setForeground( Color.white );
		awardDesc.setBorder(new LineBorder(Color.PINK, 2));
		awardDesc.setSize(awardDescWidth, awardDescHeight);
		awardDesc.setLocation(GUI.SCREEN_WIDTH/2 + GUI.BUFFER_SIZE_LG*3 ,GUI.SCREEN_HEIGHT/2 - GUI.BUFFER_SIZE);
		GUI.addComponent(awardDesc);
		
	}
	public void loadDescrption(String rewardKey)//on reward button press function, populates reward description
	{
		if(awardDesc.getComponentCount() > 0) {
            System.out.printf("Clearing awardDesc:\n");// exists? %b", GUI.root.con);
            awardDesc.removeAll();
        }
		Reward tempReward = rewards.get(rewardKey);
		JLabel rewardDescriptionLabel = new JLabel(tempReward.rewardDesc);
		rewardDescriptionLabel.setForeground( Color.PINK );
		rewardDescriptionLabel.setFont(new Font("Calibri", Font.BOLD, 36));
		rewardDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		awardDesc.add(rewardDescriptionLabel);
		
		JLabel fullDesc = new JLabel(tempReward.rewardDescFull);
		fullDesc.setForeground( Color.pink );
		fullDesc.setFont(new Font("Calibri", Font.BOLD, 26));
		fullDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		awardDesc.add( fullDesc );//makeTextArea(tempReward.rewardDescFull, 20, 2, 22));
		
		//awardDesc.setHorizontalAlignment(JLabel.CENTER);
		awardDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		ImageIcon rewardImage = Resource_Manager.loadResourceScaled(tempReward.getAbsolutePath(), scaledRewardSize,scaledRewardSize);
		
		JLabel rewardImageLabel = new JLabel();
		rewardImageLabel.setHorizontalAlignment(JLabel.CENTER);
		rewardImageLabel.setIcon(rewardImage);
		rewardImageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		rewardDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		awardDesc.add(Box.createRigidArea(new Dimension(0,5)));
		awardDesc.add(rewardImageLabel);
		awardDesc.add(Box.createRigidArea(new Dimension(0,5)));
		awardDesc.revalidate();
		awardDesc.repaint();
	}
	private JPanel makeTextArea(String text, int textSize, int row, int col){
		JPanel textAreaContainer = new JPanel();
		textAreaContainer.setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea(text, row, col);
		//textArea.setAlignment(java.awt.Component.CENTER);
		textArea.setFont(new Font("Calibri", Font.BOLD, textSize));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBackground( Color.black );
		textArea.setForeground( Color.PINK );
		textAreaContainer.add(textArea);
		return textAreaContainer;
	}

	public static void init()
	{
		rewards.put("3over80", new Reward("3 Tests Over 80%", "reward_01", false, "Pass 3 tests with over an 80% average"));
		rewards.put("3over90", new Reward("3 Tests Over 90%", "reward_02", false, "Pass 3 tests with over a 90% average"));
		rewards.put("3over99", new Reward("3 Perfect Tests", "reward_03", false, "Pass 3 tests with a perfect 100% average"));
		rewards.put("3algebraPassed", new Reward("3 Algebra Tests Passed", "reward_04", false, "Pass 3 algebra tests"));
		rewards.put("3geometryPassed", new Reward("3 Geometry Tests Passed", "reward_05", false, "Pass 3 geometry tests"));
		rewards.put("3countingPassed", new Reward("3 Counting Tests Passed", "reward_06", false, "Pass 3 counting tests"));
		rewards.put("3anglesPassed", new Reward("3 Angles Tests Passed", "reward_07", false, "Pass 3 angles tests"));
		rewards.put("3KPassed", new Reward("3 Grade K Tests Passed", "reward_08", false, "Pass 3 Kindergarten tests"));
		rewards.put("31Passed", new Reward("3 Grade 1 Tests Passed", "reward_09", false, "Pass 3 First grade tests"));
		rewards.put("32Passed", new Reward("3 Grade 2 Tests Passed", "reward_10", false, "Pass 3 Second grade tests"));
		rewards.put("33Passed", new Reward("3 Grade 3 Tests Passed", "reward_11", false, "Pass 3 Third grade tests"));
		rewards.put("34Passed", new Reward("3 Grade 4 Tests Passed", "reward_12", false, "Pass 3 Fourth grade tests"));
	}
}