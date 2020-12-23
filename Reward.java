//Reward contains reward descriptions, the earned boolean status, image path, and a full description
import java.util.HashMap;
import javax.swing.ImageIcon;

public class Reward
{
	public boolean earned = false;
	public String rewardDesc;
	private String imagePath;
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
		return Resource_Manager.loadResource(getImagePath());
	}
	public String getImagePath()
	{
		return (earned) ? imagePath : imagePath + "_d";
	}
	public String getAbsolutePath(){
		return imagePath;
	}
}