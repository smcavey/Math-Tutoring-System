import java.util.HashMap;
import javax.swing.ImageIcon;
public class Reward
{
	public boolean earned = false;
	public String rewardDesc;
	public String imagePath;
	public String rewardDescFull;
	public Reward(String rewardDesc, String imagePath, boolean earned, String rewardDescFull)
	{
		this.rewardDesc = rewardDesc;
		this.imagePath = imagePath;
		this.earned = earned;
		this.rewardDescFull = rewardDescFull;
	}
	public ImageIcon getImage()
	{
		return Resource_Manager.loadImageLocal(imagePath);
	}
	
}