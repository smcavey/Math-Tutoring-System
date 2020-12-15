import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class TestLayout extends GUI_Page
{
	private static int NUM_ANS_BTNS = 4;
	private static int PRESSED_BTN_DEFAULT = -1;
	private static int quesTextSize = 25;
	private static String font = "Serif";

    public String nextPage;
    public String previousPage;
    public AnswerKey currentAnswerKey;
    public static int pressedButton;
	private int answerButtonHeight = 50;
	
     public TestLayout(String pageKey, String pageTitle){//, String previousPage, String nextPage){
        this.pageTitle = pageTitle;
        this.nextPage = nextPage;
        this.previousPage = previousPage;
        this.pageKey = pageKey;
     }
     
     public void activate(){
         
         pressedButton = PRESSED_BTN_DEFAULT; //set selected button to 'none pressed'
         // Page Title
		JLabel title = new JLabel(pageTitle);
		title.setSize( GUI.TITLE_LABEL_WIDTH, GUI.TITLE_LABEL_HEIGHT );
		title.setLocation( (GUI.SCREEN_WIDTH/2) - (GUI.TITLE_LABEL_WIDTH/2), GUI.BUFFER_SIZE );
		title.setForeground( Color.white );
		GUI.addComponent(title);
		
		//Home button
		GUI.addComponent( new HomeButton() );
                
      currentAnswerKey = Resource_Manager.getAnswerKeys(pageKey);
	  if(currentAnswerKey == null){
		  System.out.printf("Add answer key for: %s", pageKey);
		  return;
	  }
	  
		//Create place holder for text
		JLabel pageText = new JLabel(currentAnswerKey.question, SwingConstants.CENTER);
		pageText.setFont(new Font(font,Font.PLAIN, quesTextSize));
		pageText.setSize(GUI.TEXT_FRAME_WIDTH, GUI.TEXT_FRAME_HEIGHT);

		int textPanelStartX = GUI.BUFFER_SIZE_LG*3;
		pageText.setLocation(textPanelStartX, GUI.TEST_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG);
		pageText.setForeground( Color.WHITE );
		GUI.addComponent( pageText );

		//Create place holder for image
		JLabel pageImage = new JLabel(Resource_Manager.getImage(pageKey), SwingConstants.CENTER);
		pageImage.setSize(GUI.IMAGE_FRAME_WIDTH, GUI.IMAGE_FRAME_HEIGHT);
		pageImage.setLocation(textPanelStartX + GUI.TEXT_FRAME_WIDTH + GUI.BUFFER_SIZE_LG, GUI.TEST_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG);
		GUI.addComponent( pageImage );
		
		//Answer Button Container
		int buttonContainerYStartOffset = GUI.IMAGE_FRAME_HEIGHT + GUI.BUFFER_SIZE_LG*2;
		int buttonContainerHeight = answerButtonHeight*2 + GUI.BUFFER_SIZE_LG;
		JPanel buttonContainer = new JPanel();
		buttonContainer.setLayout(new java.awt.GridLayout(2,2,10,10));
		buttonContainer.setSize(GUI.SCREEN_WIDTH - (GUI.BUFFER_SIZE_LG*3), buttonContainerHeight);
		buttonContainer.setLocation(GUI.BUFFER_SIZE_LG, buttonContainerYStartOffset);
		buttonContainer.setBackground(Color.black);
			
		for(int i = 0; i < 4; i++){
			buttonContainer.add( new TestButton(currentAnswerKey.answers[i], i) );
		}
		
		GUI.addComponent(buttonContainer);
		
		int navCOntainerYStartOffset = buttonContainerYStartOffset + buttonContainerHeight + GUI.BUFFER_SIZE_LG;
		int navContainerWidth = GUI.NAVBUTTON_WIDTH*2 + GUI.BUFFER_SIZE_LG*3;
		JPanel navContainerPanel = new JPanel();
		navContainerPanel.setBackground(Color.black);
		GridLayout gridLayout = new GridLayout(1,2);
		gridLayout.setHgap(GUI.BUFFER_SIZE_LG);
		navContainerPanel.setLayout(gridLayout);
		navContainerPanel.setSize(navContainerWidth, GUI.NAVBUTTON_HEIGHT);
		System.out.printf("Y Offset: %d", buttonContainerYStartOffset);
		navContainerPanel.setLocation(GUI.SCREEN_WIDTH - (navContainerWidth + GUI.BUFFER_SIZE_LG*2), navCOntainerYStartOffset + GUI.BUFFER_SIZE_LG);
		GUI.addComponent(navContainerPanel);

		//Previous Page Button
		JButton prev_button = new JButton( "Previous Page" );
			prev_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUI_Manager.loadPage( Module.getPreviousPageKey() );
				}
			} );
		if(Module.currentPage == 1){
			prev_button.setVisible(false);
		}
		navContainerPanel.add(prev_button);
		
		//Next Page Button
		boolean isLastPage = Module.currentPage >= Module.getActiveModule().numPages;
		JButton next_button = new JButton((isLastPage) ? "Finish" : "Next" );
		next_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						if(GUI.DEBUG)System.out.printf("adding answer for: %s : %d\n", pageKey, pressedButton);
						TestHelper.addAnswer(pageKey, pressedButton);
						
						if(isLastPage){
							TestHelper.gradeTest();
							UserProfile.addScore( Module.getActiveModule().pageKey, TestHelper.currentScore );
							GUI_Manager.loadPage( GUI.RESULTS_ID );
						} 
						else{
							GUI_Manager.loadPage( Module.getNextPageKey() );
						}
				}
		} );
		//next_button.setSize( GUI.NAVBUTTON_WIDTH, GUI.NAVBUTTON_HEIGHT );
		//next_button.setLocation( GUI.SCREEN_WIDTH - (GUI.NAVBUTTON_WIDTH + GUI.BUFFER_SIZE_LG) * 2 - offset, GUI.SCREEN_HEIGHT - (GUI.NAVBUTTON_HEIGHT*3 + GUI.BUFFER_SIZE)  );
		//GUI.addComponent( next_button );
		navContainerPanel.add( next_button );
	}
}

class TestButton extends JLabel
{
	private static JLabel selectedButton;
	//private static Color defaultBgColor;
	//private static Color defaultFgColor;
	private int buttonNumber;
	
	public TestButton(String buttonName, int buttonNumber) {
		super(buttonName, SwingConstants.CENTER);
		this.buttonNumber = buttonNumber;
		setBackground(Color.black);
		setForeground(Color.white);
		setOpaque(true);
		//setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		//defaultBgColor = getBackground();
		//defaultFgColor = getForeground();
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				onPress();
			}
		});
	}
	void onPress() {
		if(selectedButton != null) {
			selectedButton.setBackground(Color.black);
			selectedButton.setForeground(Color.white);
		}
		selectedButton = this;
		TestLayout.pressedButton = buttonNumber;
		selectedButton.setForeground(Color.black);
		selectedButton.setBackground(Color.white);
		//selectedButton.revalidate();
	}
}