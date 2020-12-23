//GUI page
//Test type displays randomly generated equations by operations
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.util.*;
import java.util.Random;
import java.util.ArrayList;

public class TestMathLayout extends GUI_Page
{
	public static ArrayList<AnswerKey> currentTest;
	
	private int algContainerWidth = 600;
	private int algContainerHeight = 250;
	private int altContainerWidth = 450;
	private int altContainerHeight = 75;
	
	int expandedBorderSize = 3;
	int defaultBorderSize = 2;
	
	private int x;
	private int y;
	private String operator;
	
	private static String sub = "-";
	private static String add = "+";
	private static String div = "/";
	private static String mul = "x";
	
	int maxEquationValue;
	
	private int numQuestions;
	private int curQuestion;
	
	private JTextField answerTextField;
	private JPanel algContainer;
	private JLabel answerGraphic;
	private int answerGraphicSize = 128;
	
	public TestMathLayout(String pageKey, String pageTitle, String operator, int maxEquationValue, int numQuestions){
        this.pageTitle = pageTitle;
        this.pageKey = pageKey;
		this.operator = operator;
		this.maxEquationValue = maxEquationValue;
		this.numQuestions = numQuestions;
     }
	
	public void activate()
	{
		curQuestion = 0;
		currentTest = new ArrayList<AnswerKey>();
		
		GUI.addComponent( new HomeButton() );

		algContainer = new JPanel();
		algContainer.setLocation( GUI.SCREEN_WIDTH/2 - algContainerWidth/2, GUI.SCREEN_HEIGHT/2 - algContainerHeight/2 );
		algContainer.setSize( algContainerWidth, algContainerHeight );
		algContainer.setBackground( Color.black );
		GUI.addComponent( algContainer );
		
		GridLayout gridLayout = new GridLayout(1,3);
		algContainer.setLayout( gridLayout );
		
		JPanel altContainer = new JPanel();
		altContainer.setLocation( GUI.SCREEN_WIDTH/2 - altContainerWidth/2, GUI.SCREEN_HEIGHT - (GUI.BUFFER_SIZE_LG*4 + altContainerHeight/2) );
		altContainer.setSize( altContainerWidth, altContainerHeight );
		altContainer.setLayout( gridLayout );
		altContainer.setBackground( Color.black );
		GUI.addComponent( altContainer );
		
		answerGraphic = new JLabel(Resource_Manager.loadImageLocal("ans_image.png"));
		answerGraphic.setSize( answerGraphicSize, answerGraphicSize );
		answerGraphic.setLocation( GUI.SCREEN_WIDTH/2 - answerGraphicSize/2, GUI.SCREEN_HEIGHT - (GUI.SCREEN_HEIGHT/3 + answerGraphicSize/2));
		GUI.addComponent( answerGraphic );
		setAnswerGraphic( false );
		
		answerTextField = new JTextField();
		answerTextField.setFont( new Font("Calibri", Font.BOLD, 24));
		answerTextField.setHorizontalAlignment(JTextField.CENTER);
		altContainer.add( answerTextField );
		
		JLabel submitAnswerButton = new JLabel("Submit", SwingConstants.CENTER);
		submitAnswerButton.setForeground( Color.white );
		submitAnswerButton.setFont( new Font("Calibri", Font.BOLD, 24));
		submitAnswerButton.setBorder(new LineBorder(Color.pink, defaultBorderSize));
		submitAnswerButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mousePressAction();
			}
			public void mouseEntered(MouseEvent evt) {
				submitAnswerButton.setBorder(new LineBorder(Color.white, expandedBorderSize));
			}
			public void mouseExited(MouseEvent evt) {
				submitAnswerButton.setBorder(new LineBorder(Color.pink, defaultBorderSize));
			}		
		});
		altContainer.add( submitAnswerButton );
		
		JLabel nextQuestionButton = new JLabel(">>>", SwingConstants.CENTER);
		nextQuestionButton.setForeground( Color.white );
		nextQuestionButton.setFont( new Font("Calibri", Font.BOLD, 24));
		nextQuestionButton.setBorder(new LineBorder(Color.pink, defaultBorderSize));
		nextQuestionButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mousePressAction();
			}
			public void mouseEntered(MouseEvent evt) {
				nextQuestionButton.setBorder(new LineBorder(Color.white, expandedBorderSize));
			}
			public void mouseExited(MouseEvent evt) {
				nextQuestionButton.setBorder(new LineBorder(Color.pink, defaultBorderSize));
			}
		});
		altContainer.add( nextQuestionButton );
		nextQuestion(); //call at end of activate
	}
	public String getStartPage(){
		return pageKey;
	}
	private void mousePressAction(){
		int userAns = -1;
				try {
					userAns = Integer.parseInt( answerTextField.getText() );
				} catch(NumberFormatException numForEx){
					userAns = -1;
				}
				currentTest.add( 
					new AnswerKey(
						String.valueOf(x) + " " + operator + " " + String.valueOf(y),
						userAns,
						calcAnswer() ));
				nextQuestion();
	}
	private void nextQuestion(){
		curQuestion++;
		
		if(curQuestion > numQuestions){
			TestResultLayout.testType = TestType.RNG;
			GUI_Manager.loadPage( GUI.RESULTS_ID );
		}
		else
		{
			setAnswerGraphic( false );
			if(algContainer.getComponentCount() > 0) {
				System.out.printf("Clearing Alg:\n");// exists? %b", GUI.root.con);
				algContainer.removeAll();
			}
			Random rng = new Random();
			
			if(sub.equals(add)){
				x = rng.nextInt( maxEquationValue ) + 1;
				y = rng.nextInt( maxEquationValue ) + 1;
			}
			else{
				int val = maxEquationValue/2;
				x = rng.nextInt( val ) + val;		// 5-9
				y = rng.nextInt( val );			// 0-4
			}
			
			algContainer.add( createAlgLabel( Integer.toString(x) ));
			algContainer.add( createAlgLabel( operator ));
			algContainer.add( createAlgLabel( Integer.toString(y) ));
			
			answerTextField.setText("");
			
			algContainer.revalidate();
			algContainer.repaint();
		}
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
	
/* 	boolean checkAnswer(){
		int answer = 0;
		try {
			answer = Integer.parseInt(answerTextField.getText());
		} catch (NumberFormatException e) {
			return false;
		}
		return calcAnswer() == answer;
	} */
	
	private int calcAnswer(){
		switch (operator)
		{
			case "+":
				return x + y;
			case "-":
				return x - y;
			case "x":
				return x * y;
			case "/":
				return x / y;
			default:
				return -1;
		}
	}
	
	private void setAnswerGraphic(boolean enable){
			answerGraphic.setVisible( enable );
			answerGraphic.revalidate();
	}
	
	private JLabel createAlgLabel(String s){
		JLabel label = new JLabel(s, SwingConstants.CENTER);
		label.setFont(new Font("Calibri", Font.BOLD, 72));
		//label.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		label.setBackground(Color.black);
		label.setForeground(Color.white);
		return label;
	}
}