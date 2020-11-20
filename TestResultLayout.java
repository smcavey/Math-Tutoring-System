import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map.Entry;

public class TestResultLayout extends GUI_Page
{
	private static int SCORE_WIDTH = 250;
	private static int SCORE_HEIGHT = 30;
	
	public static int TEST_RESULT_WIDTH = 300;
	public static int TEST_RESULT_HEIGHT = 500;
	public void activate()
	{
		JLabel score = new JLabel("Test Score: " + String.valueOf(TestHelper.gradeTest()), SwingConstants.CENTER);
		score.setSize( SCORE_WIDTH, SCORE_HEIGHT );
		score.setLocation((GUI.SCREEN_WIDTH/2) - (SCORE_WIDTH/2),(GUI.SCREEN_HEIGHT) - (SCORE_HEIGHT + GUI.BUFFER_SIZE_LG*2));
		score.setForeground( Color.black );
		score.setOpaque(true);
		
		GUI.addComponent(score);
		
		System.out.println(TestHelper.gradeTest());
		
		JLabel title = new JLabel("Results");
		title.setSize( GUI.TITLE_LABEL_WIDTH, GUI.TITLE_LABEL_HEIGHT );
		title.setLocation( (GUI.SCREEN_WIDTH/2), GUI.BUFFER_SIZE );
		title.setForeground( Color.white );
		GUI.addComponent(title);
		
		int startLocation = GUI.TITLE_LABEL_HEIGHT + GUI.BUFFER_SIZE_LG*2;
		int i = 0;
		
		for (Entry<String, Integer> entry : TestHelper.currentTestAnswers.entrySet())
		{
			//entry == Key Value Pair. Key = String, Value = Integer
			//entry == <pageKey, UserInputAnswer>
			JLabel label = new JLabel("Quesiton: " + String.valueOf(i+1));
			label.setLocation(GUI.BUFFER_SIZE_LG, startLocation*(i+1));
			label.setSize(TEST_RESULT_WIDTH, TEST_RESULT_HEIGHT/5);
			label.setForeground( Color.black );
			label.setOpaque(true);
			GUI.addComponent(label);
			
			//Get answer key from GUI_Manager
			//AnswerKey ak = GUI_Manager.getAnswerKey( entry.getKey());
			//String anticipatedAns = TestHelper
			JLabel theAnswer = new JLabel("Anticipated Answer: " + String.valueOf(i+1));
			theAnswer.setLocation(GUI.BUFFER_SIZE_LG *2 + TEST_RESULT_WIDTH, startLocation*(i+1));
			theAnswer.setSize(TEST_RESULT_WIDTH, TEST_RESULT_HEIGHT/5);
			theAnswer.setForeground( Color.black );
			theAnswer.setOpaque(true);
			GUI.addComponent(theAnswer);
			
			//User Answered value is already stored in TestHelper.currentTestAnswer.
			//......"Your Snswer: "+ String.valueOf(entry.getValue()));
			JLabel yourAnswer = new JLabel("Your Answer: " + String.valueOf(i+1));
			yourAnswer.setLocation(GUI.BUFFER_SIZE_LG *3 + TEST_RESULT_WIDTH*2, startLocation*(i+1));
			yourAnswer.setSize(TEST_RESULT_WIDTH, TEST_RESULT_HEIGHT/5);
			yourAnswer.setForeground( Color.black );
			yourAnswer.setOpaque(true);
			GUI.addComponent(yourAnswer);
			
			i++;
		}
		
		
		
		
		
		//JLabel testResults = new JLabel("test results here");
		//testResults.setSize(TEST_RESULT_WIDTH, TEST_RESULT_HEIGHT);
		//testResults.setLocation(GUI.BUFFER_SIZE_LG, GUI.TITLE_LABEL_HEIGHT + GUI.BUFFER_SIZE_LG*2);
		//testResults.setForeground( Color.black );
		//testResults.setOpaque(true);
		//GUI.addComponent(testResults);
		
		//TODO: add practice test questions with input answers and the correct answers
		//TODO: add fun rewarding images to the screen to provide the user with a sense of pride and accomplishment for meeting score thresholds
		
		GUI.addComponent( new HomeButton() );
	}
}