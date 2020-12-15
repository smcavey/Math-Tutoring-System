import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.border.LineBorder;
import java.util.Map.Entry;

public class RewardLayout extends GUI_Page
{
	private JPanel awardDesc;
	private int achievementWidth = 300;
	private int achievementHeight = 60;
	private int recentAwardsWidth = 500;
	private int recentAwardsHeight = 300;
	private int awardDescWidth = 500;
	private int awardDescHeight = 300;
	
	public static HashMap<String, Reward> rewards = new HashMap <String, Reward>();
	
	public RewardLayout()
	{
		
	}
	public void activate()
	{
		// Page Title
		JLabel title = new JLabel("Rewards");
		title.setSize( GUI.TITLE_LABEL_WIDTH, GUI.TITLE_LABEL_HEIGHT );
		title.setLocation( (GUI.SCREEN_WIDTH/2) - (GUI.TITLE_LABEL_WIDTH/2), GUI.BUFFER_SIZE );
		title.setForeground( Color.white );
		GUI.addComponent(title);
		
		//home button
		GUI.addComponent( new HomeButton() );
		
		int xOff = GUI.BUFFER_SIZE_LG*13;
		int yOff = GUI.BUFFER_SIZE_LG*3 + GUI.BUFFER_SIZE*2;
		
		/* for(int i = 0; i < 6; i++)
		{
			for(int k= 0; k < 2; k++)
			{
				JPanel rewardsPanel = new JPanel();
				rewardsPanel.setLayout(new BorderLayout());
				Reward reward = rewards.get("testKey");
				JButton rewardDesc = new JButton(reward.rewardDesc);
				rewardDesc.setBackground( Color.gray );
				rewardsPanel.add(rewardDesc);
				JLabel rewardsImage = new JLabel(reward.getImage());
				rewardsImage.setPreferredSize(new Dimension(achievementHeight,achievementHeight));
				rewardsPanel.add(rewardsImage, BorderLayout.EAST);
				rewardsPanel.setLocation(GUI.BUFFER_SIZE_LG +GUI.BUFFER_SIZE*3 + xOff*k,GUI.BUFFER_SIZE_LG*2 + yOff*i);
				rewardsPanel.setSize(achievementWidth,achievementHeight);
				GUI.addComponent(rewardsPanel);
			}
		} */
		int i = 0;
		int k = 0;
		for(Entry<String, Reward> entry : rewards.entrySet())
		{
			
			JPanel rewardsPanel = new JPanel();
			rewardsPanel.setLayout(new BorderLayout());
			JButton rewardDesc = new JButton(entry.getValue().rewardDesc);
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
			JLabel rewardsImage = new JLabel(entry.getValue().getImage());
			rewardsImage.setPreferredSize(new Dimension(achievementHeight,achievementHeight));
			rewardsPanel.add(rewardsImage, BorderLayout.EAST);
			rewardsPanel.setLocation(GUI.BUFFER_SIZE_LG +GUI.BUFFER_SIZE*3 + xOff*k,GUI.BUFFER_SIZE_LG*2 + yOff*i);
			rewardsPanel.setSize(achievementWidth,achievementHeight);
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
		
		JButton backbtn = new JButton("Back");
		backbtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO nav action
			}
		} );
		btnContainer.add( backbtn );
		
		JButton nextbtn = new JButton("Next");
		backbtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO nav action
			}
		} );
		btnContainer.add( nextbtn );
		
		JButton printbtn = new JButton("Print");
		backbtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO nav action
			}
		} );
		btnContainer.add( printbtn );
		GUI.addComponent(btnContainer);
		
		JPanel recentAwards = new JPanel();
		recentAwards.setBackground( Color.black );
		recentAwards.setForeground( Color.white );
		recentAwards.setBorder(new LineBorder(Color.PINK, 2));
		recentAwards.setSize(recentAwardsWidth, recentAwardsHeight);
		recentAwards.setLocation(GUI.SCREEN_WIDTH/2 + GUI.BUFFER_SIZE_LG*3 ,GUI.SCREEN_HEIGHT/4 - GUI.BUFFER_SIZE_LG*5 - GUI.BUFFER_SIZE);
		JLabel recAwd = new JLabel("Recent Awards");
		recAwd.setForeground( Color.PINK );
		recentAwards.add(recAwd);
		GUI.addComponent(recentAwards);		
		
		awardDesc = new JPanel();
		awardDesc.setBackground( Color.black );
		awardDesc.setForeground( Color.white );
		awardDesc.setBorder(new LineBorder(Color.PINK, 2));
		awardDesc.setSize(awardDescWidth, awardDescHeight);
		awardDesc.setLocation(GUI.SCREEN_WIDTH/2 + GUI.BUFFER_SIZE_LG*3 ,GUI.SCREEN_HEIGHT/2 - GUI.BUFFER_SIZE);
		JLabel awdDesc = new JLabel("Award Description");
		awdDesc.setForeground( Color.pink );
		awardDesc.add(awdDesc);
		GUI.addComponent(awardDesc);
		
	}
	public void loadDescrption(String rewardKey)
	{
		if(awardDesc.getComponentCount() > 0) {
            System.out.printf("Clearing awardDesc:\n");// exists? %b", GUI.root.con);
            awardDesc.removeAll();
        }
		Reward tempReward = rewards.get(rewardKey);
		//add labels populate with temp reward info
		
		awardDesc.add(new JLabel(tempReward.rewardDescFull));
		awardDesc.revalidate();
	}
	public static void init()
	{
		rewards.put("testKey", new Reward("Test Reward Title", "reward_g.png", false, "You did so much geometry"));
		rewards.put("testKey1", new Reward("Test Reward Title", "reward_d.png", false, "You did so much algebra"));
		rewards.put("testKey2", new Reward("Test Reward Title", "reward_g.png", false, "You did so much division"));
		rewards.put("testKey3", new Reward("Test Reward Title", "reward_d.png", false, "You looked at so many triangles"));
		rewards.put("testKey4", new Reward("Test Reward Title", "reward_d.png", false, "You failed so bad"));
		rewards.put("testKey5", new Reward("Test Reward Title", "reward_g.png", false, "You mathed so hard"));
		rewards.put("testKey6", new Reward("Test Reward Title", "reward_g.png", false, "You completed 50000000 problems"));
		rewards.put("testKey7", new Reward("Test Reward Title", "reward_g.png", false, "todo"));
		rewards.put("testKey8", new Reward("Test Reward Title", "reward_g.png", false, "todo"));
		rewards.put("testKey9", new Reward("Test Reward Title", "reward_g.png", false, "todo"));
		rewards.put("testKey10", new Reward("Test Reward Title", "reward_g.png", false, "todo"));
		rewards.put("testKey11", new Reward("Test Reward Title", "reward_g.png", false, "todo"));
	}
}
