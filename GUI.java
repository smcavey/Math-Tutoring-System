import javax.swing.*;
//import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;

public class GUI
{
	private static JFrame root;
	
	public static int SCREEN_WIDTH = 1280;
	public static int SCREEN_HEIGHT = 720;
	public static int TEST_BUTTON_WIDTH = 150;
    public static int TEST_BUTTON_HEIGHT = 50;
	public static int NAVBUTTON_WIDTH = 80;
	public static int NAVBUTTON_HEIGHT = 20;
	public static int HOMEBUTTON_SIZE = 35;
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
	
	public static boolean DEBUG;
	
	public static void main(String[] args)
	{
		DEBUG = (args.length > 0 && args[0] == "Debug");
		
		CreateMainWindow();
		GUI_Manager.Init();
		
		GUI_Manager.loadPage(HOME_ID); // starting page
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
	}
	
	public static void addComponent(JComponent comp) { 
		root.getContentPane().add(comp);
	}
	public static void removeComponent(JComponent comp) { 
		int count = root.getContentPane().getComponentCount();
		if(DEBUG)System.out.printf("Root dnt: %d\n", count);
		root.getContentPane().remove(comp);
		if(root.getContentPane().getComponentCount() == count) {
			System.out.println("Error removing component...");
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
		root.setVisible(true);
	}
}