//Stores user profile information and scores
import java.util.HashMap;
import java.nio.file.Paths;
import java.util.Map.Entry;

public class UserProfile
{
	//add profile pic reference/name?
	public static String rescProfilePath = "resc/profile/";
	public static String profileImagePath = "resc/profile/user_default_image.png"; //only initial var contains folder path
	//add username
	public static String userName = "Cletus Jackson"; //TODO: Either make TextField or create user profile edit dialog box
	
	public static HashMap<String, Integer> scores = new HashMap<String, Integer>();
	
	public static String getProfileImagePath(){
		return profileImagePath;
	}
	public static void setProfileImagePath(String imageName){
		profileImagePath = rescProfilePath + imageName;
	}
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
	public static int[] getProgressByGrade(){
		int[] gradeProgress = new int[5];
		//System.out.println("ee");
		for( Entry<String, Integer> kv : scores.entrySet() ){
			String temp = kv.getKey().substring(1,2);
			if(temp.equals(GUI.GRADE_K))
			{
				gradeProgress[0] += 1;
			}
			else
			{
				gradeProgress[ Integer.parseInt(temp) ] += 1;
			}
		}
		return gradeProgress;
	}
}