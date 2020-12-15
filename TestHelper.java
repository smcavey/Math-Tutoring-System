import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map.Entry;

public class TestHelper {
	
    public static HashMap<String, Integer> currentTestAnswers = new HashMap<String, Integer>(); // at the start of every test we need to clear this
    public static ArrayList<String> testAnswers;
	public static int currentScore;
    public static void addAnswer(String pageKey, int pressedButton){
        currentTestAnswers.put(pageKey, pressedButton);
    }
    public static int gradeTest(){
        //int currTestAnswersSize = currentTestAnswers.size();
		String testKey = "";
		int totalCorrect = 0;
		testAnswers = new ArrayList<String>(currentTestAnswers.keySet());
		java.util.Collections.sort(testAnswers);
        for( String key : testAnswers){
            AnswerKey ak = Resource_Manager.getAnswerKeys(key);
			System.out.printf("%s, Answer: %d Correct: %d\n", key, currentTestAnswers.get(key), ak.correctAnswerIndex);
			if(ak.correctAnswerIndex == currentTestAnswers.get(key))
			{
				totalCorrect++;
			}
        }
		double finalScore = 100*((double)totalCorrect/currentTestAnswers.size());
		System.out.printf("Final Grade: %.2f\nNum Correct: %d of %d\n", finalScore, totalCorrect, currentTestAnswers.size());
		currentScore = (int)finalScore;
		return currentScore;
	}
}
