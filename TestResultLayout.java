//Displays results from various test types
//calculates scores and disiplays correct answers
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map.Entry;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TestResultLayout extends GUI_Page
{
	private static int SCORE_WIDTH = 250;
	private static int SCORE_HEIGHT = 30;
	
	public static int TEST_RESULT_WIDTH = 300;
	public static int TEST_RESULT_HEIGHT = 500;
	
	private static int QUES_LABEL_WIDTH = 250;
	private static int RES_LABEL_WIDTH = 300;
	private static int RES_LABEL_HEIGHT = 100;
	
	private int printButtonWidth = 100;
	
	private int defaultTextSize = 22;
	private int scoreTextSize = 36;
	
	public static TestType testType;
	private String pipImagePath = "pip_image.png";
	
	public void activate()
	{
		GUI.addComponent( new HomeButton() );
		
		ActionButton printbtn = new ActionButton("Print");
		//TODO: printbtn.addIcon("add print image path");
		printbtn.setLocation( GUI.SCREEN_WIDTH - (printButtonWidth + HomeButton.home_btn_size + GUI.BUFFER_SIZE_LG*3), GUI.BUFFER_SIZE );
		printbtn.setSize(printButtonWidth, HomeButton.home_btn_size);
		printbtn.addMouseListener( new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try 
				{
					Robot robot = new Robot();
					String format = ".png";
					String fileName = "tuwe_sc" + new java.util.Date() + format;
					Rectangle screenRect = GUI.root.getBounds();//new Rectangle(5,5,GUI.SCREEN_WIDTH-5, GUI.SCREEN_HEIGHT-5);
					BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
					ImageIO.write(screenFullImage, format, new File(fileName));
					System.out.println("Screenshot captured");
					new Thread(new PrintActionListener(screenFullImage)).start();
				} catch (AWTException | IOException ex) {
					System.err.println(ex);
				}
			}
		} );
		GUI.addComponent(printbtn);
		
		JPanel mainContainer = new JPanel();
		mainContainer.setLocation( GUI.BUFFER_SIZE_LG, GUI.BUFFER_SIZE_LG*2 );
		mainContainer.setSize( GUI.SCREEN_WIDTH - GUI.BUFFER_SIZE_LG*3, GUI.SCREEN_HEIGHT - GUI.BUFFER_SIZE_LG*4 );
		mainContainer.setBackground( Color.black );
		
		GridLayout mainLayout = new GridLayout(1,3);
		mainLayout.setHgap( GUI.BUFFER_SIZE_LG );
		mainContainer.setLayout( mainLayout );
		GUI.addComponent( mainContainer );
		
		
		//******************** Score Panel - Start - ****************************************//
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
		
		int score = 0;
		if(testType == TestType.MULTI)
		{
			score = TestHelper.currentScore;
		}
		else if(testType == TestType.RNG)
		{
			score = TestMathLayout.getScore();
		}
		else //if(testType == TestType.RNG_SHAPE)
		{
			score = TestCountLayout.getScore();
		}
		JLabel scoreValue = makeLabel( String.valueOf(score), "Calibri", 38);
		//Add Score to user ProfileAddr
		UserProfile.addScore( Module.getActiveModule().pageKey, score);
		
		scoreValue.setAlignmentX( Component.CENTER_ALIGNMENT );
		scoreContainer.add( scoreValue );

		mainContainer.add( scoreContainer );
		
		
		//******************** Score Panel - End - ****************************************//
		
		//Question Container
		JPanel questionContainer = new JPanel();
		questionContainer.setBackground(Color.black);
		
		GridLayout gridLayout;
		if(testType == TestType.MULTI)
		{
			gridLayout = new GridLayout(TestHelper.testAnswers.size(),1);
		}
		else if(testType == TestType.RNG)
		{
			gridLayout = new GridLayout(TestMathLayout.currentTest.size(),1);
		}
		else //testType == RNG_SHAPE
		{
			gridLayout = new GridLayout(TestCountLayout.currentTest.size(),1);
		}
		
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
		
		if( testType == TestType.MULTI )
		{
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
		else if( testType == TestType.RNG)
		{
			//System.out.printf("Implement This  %d", TestMathLayout.currentTest.size());
			
			int i = 0;
			for(AnswerKey ak : TestMathLayout.currentTest)
			{
				questionContainer.add( makeTextArea(String.valueOf(++i) + ". " + ak.question ));
				answerContainer.add( makeTextArea(" = " + ak.correctAnswer ));
				
				if(ak.userAnswer < 0)
				{
					userAnswerContainer.add( makeTextArea("Not Answered") );
				}
				else if( ak.userAnswer == ak.correctAnswer )
				{
					userAnswerContainer.add( makeTextArea( "Correct!" ));
				}
				else
				{
					JPanel textPanel = makeTextArea( "X  - " + ak.correctAnswer );
					textPanel.setBorder( new LineBorder(Color.red, 2) );
					userAnswerContainer.add( textPanel );
				}
			}
		}
		else if(testType == TestType.RNG_SHAPE)
		{
			for(AnswerKey ak : TestCountLayout.currentTest)
			{
				questionContainer.add(makeShapeContainer(ak.correctAnswer));
				
				if(ak.userAnswer < 0)
				{
					userAnswerContainer.add( makeTextArea("Not Answered") );
				}
				else if( ak.userAnswer == ak.correctAnswer )
				{
					userAnswerContainer.add( makeTextArea( "Correct!" ));
				}
				else
				{
					JPanel textPanel = makeTextArea( "X  - " + ak.correctAnswer );
					textPanel.setBorder( new LineBorder(Color.red, 2) );
					userAnswerContainer.add( textPanel );
				}
			}
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
	
	private JPanel makeShapeContainer(int amt){
		JPanel container = new JPanel();
		container.setBackground( Color.black );
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		for(int i = 0; i < amt; i++){
			container.add(new JLabel(Resource_Manager.getScaledImageFromPath(pipImagePath, 32, 32)));
		}
		return container;
	}
}

enum TestType { MULTI, RNG, RNG_SHAPE }