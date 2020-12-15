import java.util.Map.Entry;
public class RewardHelper
{
	public static void calculateRewards()
	{
		int numberOfTestsOver80 = 0;
		int numberOfAlgebraTestsPassed = 0;
		//loop through all of the users rewards...
		//todo add a bunch of conditions
		//todo add a bunch of if blocks to check if they've been earned
		for(Entry<String, Integer> kvp : UserProfile.scores.entrySet())
		{
			Module tempModule = Resource_Manager.modules.get(kvp.getKey());
			if(kvp.getValue()>80)
			{
				numberOfTestsOver80++;
			}
		}
		
		if(numberOfTestsOver80>3)
		{
			
		}
	}
}