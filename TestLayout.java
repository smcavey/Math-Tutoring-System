import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestLayout extends GUI_Page
{
	private static int NUM_ANS_BTNS = 4;
	private static int PRESSED_BTN_DEFAULT = -1;

    public String nextPage;
    public String previousPage;
    public AnswerKey currentAnswerKey;
    public static int pressedButton;
   
     public TestLayout(String pageKey, String pageTitle, String previousPage, String nextPage){
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
		
		//Previous Page Button
		if(previousPage != ""){
			JButton prev_button = new JButton( "Previous Page" );
			prev_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUI_Manager.loadPage(previousPage);
				}
			} );
			prev_button.setSize( GUI.NAVBUTTON_WIDTH, GUI.NAVBUTTON_HEIGHT );
			prev_button.setLocation( GUI.SCREEN_WIDTH - (GUI.NAVBUTTON_WIDTH + GUI.BUFFER_SIZE) * 3, GUI.SCREEN_HEIGHT - (GUI.NAVBUTTON_HEIGHT*3 + GUI.BUFFER_SIZE) );
			GUI.addComponent( prev_button );
		}
		
		//Next Page Button
		JButton next_button = new JButton((nextPage == "done") ? "Finish" : "Next" );
		next_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						System.out.printf("adding answer for: %s : %d\n", pageKey, pressedButton);
						TestHelper.addAnswer(pageKey, pressedButton);
						
						if(nextPage == "done"){
							TestHelper.gradeTest();
							GUI_Manager.loadPage( GUI.RESULTS_ID );
						} 
						else{
							GUI_Manager.loadPage( nextPage );
						}
				}
		} );
		next_button.setSize( GUI.NAVBUTTON_WIDTH, GUI.NAVBUTTON_HEIGHT );
		next_button.setLocation( GUI.SCREEN_WIDTH - (GUI.NAVBUTTON_WIDTH + GUI.BUFFER_SIZE) * 2, GUI.SCREEN_HEIGHT - (GUI.NAVBUTTON_HEIGHT*3 + GUI.BUFFER_SIZE)  );
		GUI.addComponent( next_button );
                
      currentAnswerKey = GUI_Manager.getAnswerKeys(pageKey);
	  if(currentAnswerKey == null){
		  System.out.printf("Add answer key for: %s", pageKey);
		  return;
	  }
	  
	  //Create Title Label
	  //add title - GUI.addComponent(title); *do for all*
	  
	  //Create place holder for text
	  JLabel pageText = new JLabel(currentAnswerKey.question, SwingConstants.CENTER);
	  pageText.setSize(GUI.TEXT_FRAME_WIDTH, GUI.TEXT_FRAME_HEIGHT);
	  //int textX = GUI.TEXT_FRAME_WIDTH/3;
	  //int textY = GUI.TEXT_FRAME_HEIGHT/6;
	  int textPanelStartX = GUI.TEST_BUTTON_WIDTH + GUI.BUFFER_SIZE_LG*2;
	  pageText.setLocation(textPanelStartX, GUI.TEST_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG);
	  pageText.setOpaque(true);
	  GUI.addComponent( pageText );
	  //Create four buttons representing answers. ActionListner (track selected answer)
	  //int rowStart = GUI.TEST_BUTTON_WIDTH/2 + GUI.BUFFER_SIZE;
	  //int rowSpacing = GUI.TEST_BUTTON_WIDTH + GUI.BUFFER_SIZE;
	  
	  //Create place holder for image
	  JLabel pageImage = new JLabel(GUI_Manager.getImage(pageKey), SwingConstants.CENTER);
	  pageImage.setSize(GUI.IMAGE_FRAME_WIDTH, GUI.IMAGE_FRAME_HEIGHT);
	  //int imageX = GUI.IMAGE_FRAME_WIDTH + 200;
	  //int imageY = GUI.IMAGE_FRAME_HEIGHT/6;
	  pageImage.setLocation(textPanelStartX + GUI.TEXT_FRAME_WIDTH + GUI.BUFFER_SIZE_LG, GUI.TEST_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG);
	  pageImage.setOpaque(true);
	  GUI.addComponent( pageImage );
	  
	  for(int b = 0; b < NUM_ANS_BTNS; b++){
		  TestButton buttonA = new TestButton(currentAnswerKey.answers[b], b);
		  buttonA.setSize(GUI.TEST_BUTTON_WIDTH, GUI.TEST_BUTTON_HEIGHT);
		  buttonA.setLocation(GUI.BUFFER_SIZE_LG, (GUI.TEST_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG) * (b + 1));
		  GUI.addComponent( buttonA );
		}		  
	}
}

class TestButton extends JButton
{
	private static TestButton selectedButton;
	private static Color defaultBgColor;
	private static Color defaultFgColor;
	private int buttonNumber;
	
	public TestButton(String buttonName, int buttonNumber) {
		super(buttonName);
		this.buttonNumber = buttonNumber;
		defaultBgColor = getBackground();
		defaultFgColor = getForeground();
		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					onPress();
				}
		});
	}
	void onPress() {
		if(selectedButton != null) {
			selectedButton.setBackground(defaultBgColor);
			selectedButton.setForeground(defaultFgColor);
		}
		selectedButton = this;
		TestLayout.pressedButton = buttonNumber;
		setBackground(Color.DARK_GRAY);
		setForeground(Color.WHITE);
	}
}