//GUI contains the Main method
//GUI contains all public global variables related to sizing
//GUI also has the page IDs for all pages
//GUI activates all GUI pages
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Color;

public class GUI
{
	public static JFrame root;
	
	public static int SCREEN_WIDTH = 1280;
	public static int SCREEN_HEIGHT = 720;
	public static int TEST_BUTTON_WIDTH = 750;
    public static int TEST_BUTTON_HEIGHT = 35;
	public static int NAVBUTTON_WIDTH = 150;
	public static int NAVBUTTON_HEIGHT = 30;

	public static int TITLE_LABEL_WIDTH = 250;
	public static int TITLE_LABEL_HEIGHT = 30;
	public static int BUFFER_SIZE = 5;
	public static int BUFFER_SIZE_LG = 25;
    public static int TEXT_FRAME_WIDTH = 500;
	public static int TEXT_FRAME_HEIGHT = 400;
	public static int IMAGE_FRAME_WIDTH = 500;
	public static int IMAGE_FRAME_HEIGHT = 400;
	
	public static int FILTER_BUTTON_HEIGHT = 40;
	public static int FILTER_BUTTON_WIDTH = 250;
	public static int GRID_CONTAINER_OFFSET = 480;
	public static int MODULE_GRID_CELL_WIDTH = 120;
	public static int MODULE_GRID_CELL_HEIGHT = 160;
	
	public static String HOME_ID = "HOME";
	public static String GRID_ID = "GRID";
	public static String RESULTS_ID = "RESULTS";
	public static String PROGRESS_ID = "PROGRESS";
	public static String LOGIN_ID = "LOGIN";
	public static String CREATE_ID = "CREATE";
	public static String REWARD_ID = "REWARD";
	
	public static String TUTORIAL = "t";
	public static String PRAC_TEST = "p";
	public static String UNIT_TEST = "u";
	public static String GRADE_K = "K";
	public static String GRADE_1 = "1";
	public static String GRADE_2 = "2";
	public static String GRADE_3 = "3";
	public static String GRADE_4 = "4";
	
	public static String geometry = "Geometry";
	public static String algebra = "Algebra";
	public static String counting = "Counting";
	public static String angles = "Angles";
	
	public static boolean DEBUG;
	
	public static void main(String[] args)
	{
		DEBUG = (args.length > 0 && args[0] == "Debug");
		
		CreateMainWindow();
		GUI_Manager.Init();
		Resource_Manager.Init();
		AccountHelper.init();
		RewardLayout.init();
		//root.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
		
		//Set for presentation examples - 
		Resource_Manager.populateUserProfileWithTestValues();
		
		GUI_Manager.loadPage(LOGIN_ID); // starting page
	}

	
	static void CreateMainWindow()
	{
		System.out.printf("CreateMainWindow\n");
		root = new JFrame();
		root.setLayout(null);
		root.setTitle("TUWE");
		root.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

		root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		root.getContentPane().setBackground(Color.black);
		
		root.setVisible(true);
	}
	
	public static void addComponent(JComponent comp) { 
		root.getContentPane().add(comp);
	}
	public static void removeComponent(JComponent comp) { 
		int count = root.getContentPane().getComponentCount();
		if(DEBUG)System.out.printf("Root dnt: %d\n", count);
		root.getContentPane().remove(comp);
		if(root.getContentPane().getComponentCount() == count) {
			System.out.println("Error removing component...\n");
		}
	}
	public static void clearRootFrame() { 
		System.out.printf("Clearing root\n");
		root.getContentPane().removeAll();
	}
	public static void refreshRootFrame() {
		System.out.printf("Refreshing page\n");
		root.revalidate();
		root.repaint();
	}
}