import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TutorialLayout extends GUI_Page
{
	public String nextPage;
	public String previousPage;
	public String practiceTest;
	private int IMG_FRAME_WIDTH = 1200;
	private int IMG_FRAME_HEIGHT = 550;
	
	//public String currentPageKey;
	
	public TutorialLayout(String pageKey, String pageTitle){//, String practiceTest){
		this.pageTitle = pageTitle;
		this.nextPage = nextPage;
		this.previousPage = previousPage;
        this.pageKey = pageKey;
		this.practiceTest = pageKey.substring(0,2) + "p";
		//System.out.printf("Prac Test: %s\n",practiceTest);
	}
	
	public void activate(){
		
		// Page Title
		JLabel title = new JLabel(pageTitle);
		title.setSize( GUI.TITLE_LABEL_WIDTH, GUI.TITLE_LABEL_HEIGHT );
		title.setLocation( (GUI.SCREEN_WIDTH/2) - (GUI.TITLE_LABEL_WIDTH/2), GUI.BUFFER_SIZE );
		title.setForeground( Color.white );
		GUI.addComponent(title);
		
		//Home button
		GUI.addComponent( new HomeButton() );
		
		int navCOntainerYStartOffset = IMG_FRAME_HEIGHT;
		int navContainerWidth = GUI.NAVBUTTON_WIDTH*3 + GUI.BUFFER_SIZE_LG*4;
		JPanel navContainerPanel = new JPanel();
		navContainerPanel.setBackground(Color.black);
		GridLayout gridLayout = new GridLayout(1,3);
		gridLayout.setHgap(GUI.BUFFER_SIZE_LG);
		navContainerPanel.setLayout(gridLayout);
		navContainerPanel.setSize(navContainerWidth, GUI.NAVBUTTON_HEIGHT);
		//System.out.printf("Y Offset: %d", buttonContainerYStartOffset);
		navContainerPanel.setLocation(GUI.SCREEN_WIDTH - (navContainerWidth + GUI.BUFFER_SIZE_LG*2), navCOntainerYStartOffset + GUI.BUFFER_SIZE_LG);
		GUI.addComponent( navContainerPanel );

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
		navContainerPanel.add( prev_button );
		
		//Next Page Button
		JButton next_button = new JButton("Next");
		next_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					GUI_Manager.loadPage( Module.getNextPageKey() );
			}
		});
		if(Module.currentPage >= Module.getActiveModule().numPages) {
			next_button.setVisible(false);
		}
		navContainerPanel.add( next_button );
		/*int offset = GUI.NAVBUTTON_WIDTH - 50;
		
		//Previous Page Button
		if(Module.currentPage != 1){
			JButton prev_button = new JButton( "Previous Page" );
			prev_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUI_Manager.loadPage( Module.getPreviousPageKey() );
				}
			} );
			prev_button.setSize( GUI.NAVBUTTON_WIDTH, GUI.NAVBUTTON_HEIGHT );
			prev_button.setLocation( GUI.SCREEN_WIDTH - (GUI.NAVBUTTON_WIDTH + GUI.BUFFER_SIZE_LG) - offset, GUI.SCREEN_HEIGHT - (GUI.NAVBUTTON_HEIGHT*3 + GUI.BUFFER_SIZE) );
			GUI.addComponent( prev_button );
		}
		
		//Next Page Button
		if(Module.currentPage < Module.getActiveModule().numPages){
			JButton next_button = new JButton( "Next Page" );
			next_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.printf("Next Page: %s", nextPage);
					GUI_Manager.loadPage( Module.getNextPageKey() );
				}
			} );
			next_button.setSize( GUI.NAVBUTTON_WIDTH, GUI.NAVBUTTON_HEIGHT );
			next_button.setLocation( GUI.SCREEN_WIDTH - (GUI.NAVBUTTON_WIDTH + GUI.BUFFER_SIZE_LG) *2 - offset, GUI.SCREEN_HEIGHT - (GUI.NAVBUTTON_HEIGHT*3 + GUI.BUFFER_SIZE)  );
			GUI.addComponent( next_button );
		}*/
		
		//Practice Test Button
		JButton practice_test_button = new JButton( "Practice Test" );
		practice_test_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Module.setActiveModule(practiceTest); //Duplicate in modulegrid, should combine
				GUI_Manager.loadPage(Module.getActiveModule().getCurrentPageKey());
			}
		} );
		//practice_test_button.setSize( GUI.NAVBUTTON_WIDTH, GUI.NAVBUTTON_HEIGHT );
		//practice_test_button.setLocation( GUI.SCREEN_WIDTH - (GUI.NAVBUTTON_WIDTH + GUI.BUFFER_SIZE_LG) *3 - offset, GUI.SCREEN_HEIGHT - (GUI.NAVBUTTON_HEIGHT*3 + GUI.BUFFER_SIZE) );
		navContainerPanel.add( practice_test_button );
		
		//Create place holder for text

		//Create place holder for image
		JLabel pageImage = new JLabel( Resource_Manager.getImage(pageKey), SwingConstants.CENTER );
		pageImage.setSize( IMG_FRAME_WIDTH, IMG_FRAME_HEIGHT );
		pageImage.setLocation( GUI.BUFFER_SIZE_LG, GUI.TEST_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG );
		//pageImage.setOpaque( true);
		GUI.addComponent( pageImage );
	}
}