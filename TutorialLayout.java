//Gui page for tutorial materials
//Displays text and image, identifies resources by pageKey
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
/* 		JLabel title = new JLabel(pageTitle);
		title.setSize( GUI.TITLE_LABEL_WIDTH, GUI.TITLE_LABEL_HEIGHT );
		title.setLocation( (GUI.SCREEN_WIDTH/2) - (GUI.TITLE_LABEL_WIDTH/2), GUI.BUFFER_SIZE );
		title.setForeground( Color.white );
		GUI.addComponent(title); */
		
				 // Page Title
		 JPanel titlePanel = new JPanel();
		 titlePanel.setBackground( Color.black );
		 titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
		 titlePanel.setSize( GUI.TITLE_LABEL_WIDTH, GUI.TITLE_LABEL_HEIGHT*2 );
		 titlePanel.setLocation( (GUI.SCREEN_WIDTH/2) - (GUI.TITLE_LABEL_WIDTH/2), GUI.BUFFER_SIZE );
		 GUI.addComponent(titlePanel);
		 
		 JLabel title = new JLabel(Module.getActiveModule().topic, SwingConstants.CENTER);
		 title.setAlignmentX( Component.CENTER_ALIGNMENT );
		 title.setForeground( Color.white );
		 title.setFont(new Font("Dialog", Font.BOLD, 16));
		 titlePanel.add(title);
		 
		 JLabel subTitle = new JLabel(Module.getActiveModule().subTopic, SwingConstants.CENTER);
		 subTitle.setAlignmentX( Component.CENTER_ALIGNMENT );
		 //subTitle.setSize( GUI.TITLE_LABEL_WIDTH, GUI.TITLE_LABEL_HEIGHT );
		 //title.setLocation( (GUI.SCREEN_WIDTH/2) - (GUI.TITLE_LABEL_WIDTH/2), GUI.BUFFER_SIZE );
		 subTitle.setForeground( Color.white );
		 titlePanel.add(subTitle);
		
		//Home button
		GUI.addComponent( new HomeButton() );
		
		int navContainerYStartOffset = IMG_FRAME_HEIGHT + GUI.NAVBUTTON_HEIGHT + GUI.BUFFER_SIZE_LG;
		int navContainerWidth = GUI.NAVBUTTON_WIDTH*3 + GUI.BUFFER_SIZE_LG*4;
		JPanel navContainerPanel = new JPanel();
		navContainerPanel.setBackground(Color.black);
		GridLayout gridLayout = new GridLayout(1,3);
		gridLayout.setHgap(GUI.BUFFER_SIZE_LG);
		navContainerPanel.setLayout(gridLayout);
		navContainerPanel.setSize(navContainerWidth, GUI.NAVBUTTON_HEIGHT);
		//System.out.printf("Y Offset: %d", buttonContainerYStartOffset);
		navContainerPanel.setLocation(GUI.SCREEN_WIDTH - (navContainerWidth + GUI.BUFFER_SIZE_LG*2), navContainerYStartOffset + GUI.BUFFER_SIZE_LG);
		GUI.addComponent( navContainerPanel );

		//Previous Page Button
		ActionButton prev_button = new ActionButton( "BACK" );
		//TODO: prev_button.addIcon("add prev_button image here");
			/* prev_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) { */
				prev_button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
					GUI_Manager.loadPage( Module.getPreviousPageKey() );
				}
			} );
		if(Module.currentPage == 1){
			prev_button.setVisible(false);
		}
		prev_button.addIcon( "back_image" );
		navContainerPanel.add( prev_button );
		
		//Next Page Button
		ActionButton next_button = new ActionButton("NEXT");
		//TODO: next_button.addIcon("add next_button image here");
		next_button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
		//next_button.addActionListener( new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {
					GUI_Manager.loadPage( Module.getNextPageKey() );
			}
		});
		if(Module.currentPage >= Module.getActiveModule().numPages) {
			next_button.setVisible(false);
		}
		next_button.addIcon( "forward_image" );
		navContainerPanel.add( next_button );
		
		//Practice Test Button
		ActionButton practice_test_button = new ActionButton( "Practice Test" );
		//TODO: practice_test_button.addIcon("add practice_test_button image here");
		practice_test_button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
		/* practice_test_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { */
				System.out.println(practiceTest);
				Module.setActiveModule(practiceTest); //Duplicate in modulegrid, should combine
				System.out.println(Module.getActiveModule().getFirstPage());
				GUI_Manager.loadPage(Module.getActiveModule().getFirstPage());	//gets pageKey + "1"
			}
		} );
		navContainerPanel.add( practice_test_button );
		
		//Create place holder for text

		//Create place holder for image
		//System.out.printf("Requesting image for page key: %s\n", pageKey);
		JLabel pageImage = new JLabel( Resource_Manager.loadResourceScaled(pageKey, IMG_FRAME_WIDTH, IMG_FRAME_HEIGHT)); //.getImage(pageKey), SwingConstants.CENTER );
		pageImage.setSize( IMG_FRAME_WIDTH, IMG_FRAME_HEIGHT );
		pageImage.setLocation( GUI.BUFFER_SIZE_LG, GUI.TEST_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG );
		//pageImage.setOpaque( true);
		GUI.addComponent( pageImage );
	}
}