import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map.Entry;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

public class TestResultLayout extends GUI_Page
{
	private static int SCORE_WIDTH = 250;
	private static int SCORE_HEIGHT = 30;
	
	public static int TEST_RESULT_WIDTH = 300;
	public static int TEST_RESULT_HEIGHT = 500;
	
	private static int QUES_LABEL_WIDTH = 250;
	private static int RES_LABEL_WIDTH = 300;
	private static int RES_LABEL_HEIGHT = 100;
	
	private int defaultTextSize = 22;
	
	public void activate()
	{
		GUI.addComponent( new HomeButton() );
		
		/*JLabel title = new JLabel();
		title.setSize( GUI.TITLE_LABEL_WIDTH, GUI.TITLE_LABEL_HEIGHT );
		title.setLocation( (GUI.SCREEN_WIDTH/2), GUI.BUFFER_SIZE );
		title.setForeground( Color.white );
		GUI.addComponent(title);*/
		
		
		JPanel mainContainer = new JPanel();
		mainContainer.setLocation( GUI.BUFFER_SIZE_LG, GUI.BUFFER_SIZE_LG*2 );
		mainContainer.setSize( GUI.SCREEN_WIDTH - GUI.BUFFER_SIZE_LG*3, GUI.SCREEN_HEIGHT - GUI.BUFFER_SIZE_LG*4 );
		mainContainer.setBackground( Color.black );
		
		GridLayout mainLayout = new GridLayout(1,3);
		mainLayout.setHgap( GUI.BUFFER_SIZE_LG );
		mainContainer.setLayout( mainLayout );
		GUI.addComponent( mainContainer );
		
		JPanel scoreContainer = new JPanel();
		scoreContainer.setLayout( new BoxLayout(scoreContainer, BoxLayout.Y_AXIS ));
		scoreContainer.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		scoreContainer.setBackground( Color.black );
		
		//add empty rigidbody as blank filler
		scoreContainer.add( Box.createRigidArea(new Dimension(0,GUI.BUFFER_SIZE_LG)) );
		
		JLabel header = makeLabel( "YOU DID GREAT!!", "Calibri", defaultTextSize);
		header.setAlignmentX( Component.CENTER_ALIGNMENT );
		scoreContainer.add( header );
		
		//add empty rigidbody as blank filler
		scoreContainer.add( Box.createRigidArea(new Dimension(0,GUI.BUFFER_SIZE_LG)) );
		
		JLabel rewardGraphic = new JLabel( Resource_Manager.getRandomRewardImage());//"Add Reward Graphic");
		rewardGraphic.setAlignmentX( Component.CENTER_ALIGNMENT );
		scoreContainer.add( rewardGraphic );
		
		//add empty rigidbody as blank filler
		scoreContainer.add( Box.createRigidArea(new Dimension(0,GUI.BUFFER_SIZE_LG)) );
		
		JLabel topic = makeLabel(Module.getActiveModule().topic + ":", "Verdana", 22);
		topic.setAlignmentX( Component.CENTER_ALIGNMENT );
		scoreContainer.add( topic );
		
		JLabel subTopic = makeLabel(Module.getActiveModule().subTopic, "Verdana", 22);
		subTopic.setAlignmentX( Component.CENTER_ALIGNMENT );
		scoreContainer.add( subTopic );
		
		//add empty rigidbody as blank filler
		scoreContainer.add( Box.createRigidArea(new Dimension(0,GUI.BUFFER_SIZE_LG)) );
		
		JLabel scoreText = makeLabel("Score: ", "Calibri", 36);
		scoreText.setAlignmentX( Component.CENTER_ALIGNMENT );
		scoreContainer.add( scoreText );
		
		JLabel scoreValue = makeLabel( String.valueOf(TestHelper.currentScore), "Calibri", 38);
		scoreValue.setAlignmentX( Component.CENTER_ALIGNMENT );
		scoreContainer.add( scoreValue );

		mainContainer.add( scoreContainer );
		
		//Question Container
		JPanel questionContainer = new JPanel();
		questionContainer.setBackground(Color.black);
		GridLayout gridLayout = new GridLayout(TestHelper.testAnswers.size(),1);
		gridLayout.setHgap( GUI.BUFFER_SIZE_LG );
		questionContainer.setLayout(gridLayout);
		mainContainer.add( questionContainer );
		
		//Answer Container
		JPanel answerContainer = new JPanel();
		answerContainer.setBackground(Color.black);
		answerContainer.setLayout( gridLayout );	
		mainContainer.add( answerContainer );
		
		//User Answer Container
		JPanel userAnswerContainer = new JPanel();
		userAnswerContainer.setBackground( Color.black );
		userAnswerContainer.setLayout( gridLayout );
		mainContainer.add( userAnswerContainer );		
		
		int i = 0;
		
		for (String key : TestHelper.testAnswers)
		{
			questionContainer.add( makeTextArea("Quesiton: " + String.valueOf(i+1) ));
			
			AnswerKey ak = Resource_Manager.getAnswerKeys(key); 	//Get answers for question
			int userAnser = TestHelper.currentTestAnswers.get(key);	//Get user entered answer for question

			if(userAnser < 0)
			{
				answerContainer.add( makeTextArea("Not Answered") );
			}
			else
			{
				if(ak.correctAnswerIndex != userAnser)
				{
					JPanel correctAnswerPanel = makeTextArea( "Correct Answer: " + ak.answers[ak.correctAnswerIndex] );
					correctAnswerPanel.setBorder( new LineBorder(Color.green, 2) );
					answerContainer.add( correctAnswerPanel );
					userAnswerContainer.add( makeTextArea( "Your Answer: " + ak.answers[userAnser] ));
				}
				else
				{
					answerContainer.add( makeTextArea( "Correct!" ));
				}
			}
			i++;
		}
	}
	
	private JPanel makeTextArea(String text){
		JPanel textAreaContainer = new JPanel();
		textAreaContainer.setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea(text, 3, 25);
		textArea.setFont(new Font("Calibri", Font.BOLD, defaultTextSize));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setBackground( Color.black );
		textArea.setForeground( Color.white );
		textAreaContainer.add(textArea);
		return textAreaContainer;
	}
	
	private JLabel makeLabel(String text, String fontName, int fontSize){
		JLabel label = new JLabel(text);
		label.setBackground( Color.black );
		label.setForeground( Color.white );
		label.setFont( new Font(fontName, Font.BOLD, fontSize));
		return label;
	}
}