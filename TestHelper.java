import java.util.HashMap;

public class TestHelper {
    public static HashMap<String, Integer> currentTestAnswers = new HashMap<String, Integer>(); // at the start of every test we need to clear this
    
    public static void addAnswer(String pageKey, int pressedButton){
        currentTestAnswers.put(pageKey, pressedButton);
    }
    public static double gradeTest(){
        //int currTestAnswersSize = currentTestAnswers.size();
		int totalCorrect = 0;
        for( String key : currentTestAnswers.keySet()){
            AnswerKey ak = GUI_Manager.getAnswerKeys(key);
            System.out.printf("%s, Answer: %d Correct: %d\n", key, currentTestAnswers.get(key), ak.correctAnswerIndex);
			if(ak.correctAnswerIndex == currentTestAnswers.get(key))
			{
				totalCorrect++;
			}
        }
		double finalScore = 100*((double)totalCorrect/currentTestAnswers.size());
		System.out.printf("Final Grade: %.2f\nNum Correct: %d of %d\n", finalScore, totalCorrect, currentTestAnswers.size());
		return finalScore;
    }
}
