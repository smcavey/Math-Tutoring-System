import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TutorialLayout extends GUI_Page
{
	public String nextPage;
	public String previousPage;
	public String practiceTest;
	
	public TutorialLayout(String pageKey, String pageTitle, String previousPage, String nextPage, String practiceTest){
		this.pageTitle = pageTitle;
		this.nextPage = nextPage;
		this.previousPage = previousPage;
        this.pageKey = pageKey;
		this.practiceTest = practiceTest;
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
		if(nextPage != ""){
			JButton next_button = new JButton( "Next Page" );
			next_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.printf("Next Page: %s", nextPage);
					GUI_Manager.loadPage( nextPage );
				}
			} );
			next_button.setSize( GUI.NAVBUTTON_WIDTH, GUI.NAVBUTTON_HEIGHT );
			next_button.setLocation( GUI.SCREEN_WIDTH - (GUI.NAVBUTTON_WIDTH + GUI.BUFFER_SIZE) * 2, GUI.SCREEN_HEIGHT - (GUI.NAVBUTTON_HEIGHT*3 + GUI.BUFFER_SIZE)  );
			GUI.addComponent( next_button );
		}
		
		//Practice Test Button
		JButton practice_test_button = new JButton( "Practice Test" );
		practice_test_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Manager.loadPage(practiceTest); //TODO: Change Me
			}
		} );
		practice_test_button.setSize( GUI.NAVBUTTON_WIDTH, GUI.NAVBUTTON_HEIGHT );
		practice_test_button.setLocation( GUI.SCREEN_WIDTH - (GUI.NAVBUTTON_WIDTH + GUI.BUFFER_SIZE), GUI.SCREEN_HEIGHT - (GUI.NAVBUTTON_HEIGHT*3 + GUI.BUFFER_SIZE) );
		GUI.addComponent( practice_test_button );
	}
}