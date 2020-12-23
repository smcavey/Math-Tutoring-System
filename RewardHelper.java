//RewardHelper contains the required conditions to earn rewards and if met, changes the earned boolean to true
import java.util.Map.Entry;
public class RewardHelper
{
	public static void calculateRewards()
	{
		int numberOfTestsOver80 = 0;
		int numberOfGeometryTestsPassed = 0;
		int numberOfAlgebraTestsPassed = 0;
		int numberOfTestsOver90 = 0;
		int numberofPerfectTests = 0;
		int numberOfAnglesTestsPassed = 0;
		int numberOfBase10TestsPassed = 0;
		int numberOfGradeKTestsPassed = 0;
		int numberOfGrade1TestsPassed = 0;
		int numberOfGrade2TestsPassed = 0;
		int numberOfGrade3TestsPassed = 0;
		int numberOfGrade4TestsPassed = 0;
		
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
			if(kvp.getValue()>=60 && tempModule.topic .equals(GUI.geometry))
			{
				numberOfGeometryTestsPassed++;
				if(tempModule.getGrade() .equals("k"))
				{
					numberOfGradeKTestsPassed++;
				}
				else if(tempModule.getGrade() .equals("1"))
				{
					numberOfGrade1TestsPassed++;
				}
				else if(tempModule.getGrade() .equals("2"))
				{
					numberOfGrade2TestsPassed++;
				}
				else if(tempModule.getGrade() .equals("3"))
				{
					numberOfGrade3TestsPassed++;
				}
				else
				{
					numberOfGrade4TestsPassed++;
				}
			}
			if(kvp.getValue()>=60 && tempModule.topic .equals(GUI.algebra))
			{
				numberOfAlgebraTestsPassed++;
				if(tempModule.getGrade() .equals("k"))
				{
					numberOfGradeKTestsPassed++;
				}
				else if(tempModule.getGrade() .equals("1"))
				{
					numberOfGrade1TestsPassed++;
				}
				else if(tempModule.getGrade() .equals("2"))
				{
					numberOfGrade2TestsPassed++;
				}
				else if(tempModule.getGrade() .equals("3"))
				{
					numberOfGrade3TestsPassed++;
				}
				else
				{
					numberOfGrade4TestsPassed++;
				}
			}
			if(kvp.getValue()>=90)
			{
				numberOfTestsOver90++;
			}
			if(kvp.getValue()>99)
			{
				numberofPerfectTests++;
			}
			if(kvp.getValue()>=60 && tempModule.topic .equals(GUI.angles))
			{
				numberOfAnglesTestsPassed++;
				if(tempModule.getGrade() .equals("k"))
				{
					numberOfGradeKTestsPassed++;
				}
				else if(tempModule.getGrade() .equals("1"))
				{
					numberOfGrade1TestsPassed++;
				}
				else if(tempModule.getGrade() .equals("2"))
				{
					numberOfGrade2TestsPassed++;
				}
				else if(tempModule.getGrade() .equals("3"))
				{
					numberOfGrade3TestsPassed++;
				}
				else
				{
					numberOfGrade4TestsPassed++;
				}
			}
			if(kvp.getValue()>=60 && tempModule.topic .equals(GUI.counting))
			{
				numberOfBase10TestsPassed++;
				if(tempModule.getGrade() .equals("k"))
				{
					numberOfGradeKTestsPassed++;
				}
				else if(tempModule.getGrade() .equals("1"))
				{
					numberOfGrade1TestsPassed++;
				}
				else if(tempModule.getGrade() .equals("2"))
				{
					numberOfGrade2TestsPassed++;
				}
				else if(tempModule.getGrade() .equals("3"))
				{
					numberOfGrade3TestsPassed++;
				}
				else
				{
					numberOfGrade4TestsPassed++;
				}
			}
		}
		
		if(numberOfTestsOver80>=3)
		{
			RewardLayout.rewards.get("3over80").earned = true;
			//setImagePath("3over80");
		}
		if(numberOfTestsOver90>=3)
		{
			RewardLayout.rewards.get("3over90").earned = true;
			//setImagePath("3over90");
		}
		if(numberofPerfectTests>=3)
		{
			RewardLayout.rewards.get("3over99").earned = true;
			//setImagePath("3over99");
		}
		if(numberOfGeometryTestsPassed>=3)
		{
			RewardLayout.rewards.get("3geometryPassed").earned = true;
			//setImagePath("3geometryPassed");
		}
		if(numberOfAlgebraTestsPassed>=3)
		{
			RewardLayout.rewards.get("3algebraPassed").earned = true;
			//setImagePath("3algebraPassed");
		}
		if(numberOfAnglesTestsPassed>=3)
		{
			RewardLayout.rewards.get("3anglesPassed").earned = true;
			//setImagePath("3anglesPassed");
		}
		if(numberOfBase10TestsPassed>=3)
		{
			RewardLayout.rewards.get("3countingPassed").earned = true;
			//setImagePath("3countingPassed");
		}
		if(numberOfGradeKTestsPassed>=3)
		{
			RewardLayout.rewards.get("3KPassed").earned = true;
			//setImagePath("3KPassed");
		}
		if(numberOfGrade1TestsPassed>=3)
		{
			RewardLayout.rewards.get("31Passed").earned = true;
			//setImagePath("31Passed");
		}
		if(numberOfGrade2TestsPassed>=3)
		{
			RewardLayout.rewards.get("32Passed").earned = true;
			//setImagePath("32Passed");
		}
		if(numberOfGrade3TestsPassed>=3)
		{
			RewardLayout.rewards.get("33Passed").earned = true;
			//setImagePath("33Passed");
		}
		if(numberOfGrade4TestsPassed>=3)
		{
			RewardLayout.rewards.get("34Passed").earned = true;
			//setImagePath("34Passed");
		}
	}
/* 	public static void setImagePath(String rewardKey)
	{
		RewardLayout.rewards.get(rewardKey).imagePath = "reward_g.png";
	} */
}