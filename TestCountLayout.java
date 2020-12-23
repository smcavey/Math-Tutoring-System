//GUI page
//Generates a randomly generated number of shapes for counting or operations
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.util.*;
import java.util.Random;
import java.util.ArrayList;

public class TestCountLayout extends GUI_Page
{
	public static ArrayList<AnswerKey> currentTest;
		
	private int inputContainerHeight = 50;
	private int inputContainerWidth;// = 400;
	private int shapeContainerWidth = 345; //5x64 + 5
	private int shapeContainerHeight = 138;//2x64 + 5
	
	private int numPips;
	private int maxNumPips;
	
	private int numQuestions;
	private int curQuestion;
	
	private JTextField answerTextField;
	private JPanel shapeContainer;
	
	private int defaultBorderSize = 2;
	private int expandedBorderSize = 3;
	
	public TestCountLayout(String pageKey, String pageTitle, int numQuestions, int maxNumPips){
        this.pageTitle = pageTitle;
        this.pageKey = pageKey;
		this.numQuestions = numQuestions;
		this.maxNumPips = maxNumPips;
	}
	
	public void activate(){
		/*JPanel mainContainer = new JPanel();
		mainContainer.setLocation( GUI.BUFFER_SIZE_LG );
		mainContainer.setSize( GUI.SCREEN_WIDTH - GUI.BUFFER_SIZE_LG*3, GUI.SCREEN_HEIGHT - (inputContainerHeight + GUI.BUFFER_SIZE_LG*3) );
		mainContainer.setLayout( new BoxLayout(mainContainer, BoxLayout.X_AXIS));
		mainContainer.setOpaque(true);
		GUI.addComponent( mainContainer );*/
		GUI.addComponent(new HomeButton());
		
		curQuestion = 0;
		currentTest = new ArrayList<AnswerKey>();
		
		shapeContainer = new JPanel();
		shapeContainer.setBackground(Color.black);
		shapeContainer.setLocation( GUI.SCREEN_WIDTH/2 - shapeContainerWidth/2, GUI.SCREEN_HEIGHT/2 - shapeContainerHeight );
		shapeContainer.setSize( shapeContainerWidth, shapeContainerHeight );
		
		GridLayout gridLayout = new GridLayout(2,5);
		shapeContainer.setLayout( gridLayout );
		GUI.addComponent( shapeContainer );
		
		int inputContainerWidth = GUI.NAVBUTTON_WIDTH*2 + GUI.BUFFER_SIZE_LG*3;
		JPanel inputContainer = new JPanel();
		inputContainer.setLocation( GUI.SCREEN_WIDTH/2 - inputContainerWidth/2, GUI.SCREEN_HEIGHT - (GUI.BUFFER_SIZE_LG*4 + inputContainerHeight/2) );
		inputContainer.setSize( inputContainerWidth, inputContainerHeight );
		GridLayout inputLayout = new GridLayout(1,3);
		inputContainer.setLayout( inputLayout );
		inputContainer.setBackground( Color.black );
		GUI.addComponent( inputContainer );
		
		answerTextField = new JTextField();
		answerTextField.setFont( new Font("Calibri", Font.BOLD, 24));
		inputContainer.add( answerTextField );
		
		
		JLabel submitAnswerButton = new JLabel("Check Ans", SwingConstants.CENTER);
		submitAnswerButton.setForeground( Color.white );
		submitAnswerButton.setFont( new Font("Calibri", Font.BOLD, 24));
		submitAnswerButton.setBorder( new LineBorder(Color.LIGHT_GRAY, defaultBorderSize));
		submitAnswerButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mousePressAction();
			}
			public void mouseEntered(MouseEvent evt) {
				submitAnswerButton.setBorder(new LineBorder(Color.white, expandedBorderSize));
			}
			public void mouseExited(MouseEvent evt) {
				submitAnswerButton.setBorder(new LineBorder(Color.LIGHT_GRAY, defaultBorderSize));
			}		
		});
		inputContainer.add( submitAnswerButton );
		
		JLabel nextQuestionButton = new JLabel(">>>", SwingConstants.CENTER);
		nextQuestionButton.setForeground( Color.white );
		nextQuestionButton.setFont( new Font("Calibri", Font.BOLD, 24));
		nextQuestionButton.setBorder(new LineBorder(Color.LIGHT_GRAY, defaultBorderSize));
		nextQuestionButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mousePressAction();
			}
			public void mouseEntered(MouseEvent evt) {
				nextQuestionButton.setBorder(new LineBorder(Color.white, expandedBorderSize));
			}
			public void mouseExited(MouseEvent evt) {
				nextQuestionButton.setBorder(new LineBorder(Color.LIGHT_GRAY, defaultBorderSize));
			}
		});
		inputContainer.add( nextQuestionButton );
				
		nextQuestion();
	}
	
	private void mousePressAction(){
		int userAns = -1;
			try {
				userAns = Integer.parseInt( answerTextField.getText() );
			} catch(NumberFormatException numForEx){
				//userAns = -1;
			}
			currentTest.add( new AnswerKey( userAns, numPips )); 
			nextQuestion();
	}
	private void nextQuestion(){
		if(curQuestion > numQuestions){
			TestResultLayout.testType = TestType.RNG_SHAPE;
			GUI_Manager.loadPage( GUI.RESULTS_ID );
		}
		if(shapeContainer.getComponentCount() > 0){
			shapeContainer.removeAll();
		}

		curQuestion++;
		numPips = getRndomInt();
		
		for(int i = 0; i < numPips; i++){
			JLabel img = new JLabel(Resource_Manager.loadImageLocal("pip_image.png"));
			shapeContainer.add(img);
		}
		answerTextField.setText("");
		shapeContainer.revalidate();
		shapeContainer.repaint();
	}
	
	private int getRndomInt(){
		Random rng = new Random();
		return rng.nextInt(maxNumPips) + 1;
	}
	public static int getScore(){
		int numCorrect = 0;
		for(AnswerKey ak : currentTest)
		{
			if(ak.userAnswer == ak.correctAnswer)
			{
				numCorrect++;
			}
		}
		double finalScore = 100*((double)numCorrect/currentTest.size());
		//System.out.printf("Final Grade: %.2f\nNum Correct: %d of %d\n", finalScore, totalCorrect, currentTestAnswers.size());
		return (int)finalScore; 
	}
}