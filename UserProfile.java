import java.util.HashMap;
import java.nio.file.Paths;

public class UserProfile
{
	//add profile pic reference/name?
	public static String profileImagePath = "user_default_image.png";
	//add username
	public static String userName = "Cletus Jackson"; //TODO: Either make TextField or create user profile edit dialog box
	
	public  static HashMap<String, Integer> scores = new HashMap<String, Integer>();
	
	public static void addScore(String key, int value) 
	{
		if(scores.containsKey(key) && value <= scores.get(key)) {
			return;	//if higher score already exists, do not change
		}
		else {
			scores.put(key, value);
			RewardHelper.calculateRewards();
		}
	}
	
	public static int getScore(String key) {
		if(!scores.containsKey(key)) {
			return 0;
		}
		else {
			return scores.get(key);
		}
	}
}