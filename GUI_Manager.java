//GUI_Manager contains the function loadPage()
//GUI_Manager initializes all pages and puts the page ID into the guiPages hashmap
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.File;

public class GUI_Manager
{	
	public static Map<String, GUI_Page> guiPages = new HashMap<String, GUI_Page>();
        
	public static GUI_Page activePage;

	public static void Init()
	{
		System.out.print("GUI_Manager Init\n");		
		//Topic:Grade:Type
		/*
		Topic - Geometry = G, Counting = C, ...
		Grade - K, 1, 2, 3, 4
		Type - Tutorial = T, Practice Test = P, Unit Test = U
		Page
		*/
		LoginLayout login_page = new LoginLayout();
		guiPages.put(GUI.LOGIN_ID, login_page);
		
		CreateUserLayout create_page = new CreateUserLayout();
		guiPages.put(GUI.CREATE_ID, create_page);
		
		HomePageLayout home_page = new HomePageLayout();
		guiPages.put(GUI.HOME_ID, home_page);
		
		TestResultLayout results_page = new TestResultLayout();
		guiPages.put(GUI.RESULTS_ID, results_page);
		
		ModuleGridLayout grid_selection_page = new ModuleGridLayout();
		guiPages.put(GUI.GRID_ID, grid_selection_page);
		
		ProgressLayout progress_page = new ProgressLayout();
		guiPages.put(GUI.PROGRESS_ID, progress_page);
		
		RewardLayout reward_page = new RewardLayout();
		guiPages.put(GUI.REWARD_ID, reward_page);

		
		//Module geo_4_tutorial = new Module("g4t", "Angle Tutorial", 6);//, ModuleType.TUTORIAL, Subject.GEOMETRY, 4);
		//Geometry, Grade 4, Tutorial
		/*TutorialLayout g4t1 = new TutorialLayout("g4t1", "Geometry Tutorial 1", "", "g4t2", "g4p1");
		guiPages.put("g4t1", g4t1);
		
		TutorialLayout g4t2 = new TutorialLayout("g4t2", "Geometry Tutorial 2", "g4t1", "g4t3", "g4p1");
		guiPages.put("g4t2", g4t2);
		
		TutorialLayout g4t3 = new TutorialLayout("g4t3", "Geometry Tutorial 3", "g4t2", "g4t4", "g4p1");
		guiPages.put("g4t3", g4t3);
		
		TutorialLayout g4t4 = new TutorialLayout("g4t4", "Geometry Tutorial 4", "g4t3", "g4t5", "g4p1");
		guiPages.put("g4t4", g4t4);
                
		TutorialLayout g4t5 = new TutorialLayout("g4t5", "Geometry Tutorial 5", "g4t4", "g4t6", "g4p1");
		guiPages.put("g4t5", g4t5);
		
		TutorialLayout g4t6 = new TutorialLayout("g4t6", "Geometry Tutorial 6", "g4t5", "g4t7", "g4p1");
		guiPages.put("g4t6", g4t6);
		
		TutorialLayout g4t7 = new TutorialLayout("g4t7", "Geometry Tutorial 7", "g4t6", "", "g4p1");
		guiPages.put("g4t7", g4t7);*/
		
		/*TestLayout g4p1 = new TestLayout("g4p1", "Geometry Practice Problem 1", "", "g4p2");
		guiPages.put("g4p1", g4p1);
		
		TestLayout g4p2 = new TestLayout("g4p2", "Geometry Practice Problem 2", "g4p1", "g4p3");
		guiPages.put("g4p2", g4p2);
		
		TestLayout g4p3 = new TestLayout("g4p3", "Geometry Practice Problem 3", "g4p2", "g4p4");
		guiPages.put("g4p3", g4p3);
		
		TestLayout g4p4 = new TestLayout("g4p4", "Geometry Practice Problem 4", "g4p3", "g4p5");
		guiPages.put("g4p4", g4p4);
		
		TestLayout g4p5 = new TestLayout("g4p5", "Geometry Practice Problem 5", "g4p4", "g4p6");
		guiPages.put("g4p5", g4p5);
		
		TestLayout g4p6 = new TestLayout("g4p6", "Geometry Practice Problem 6", "g4p5", "done");
		guiPages.put("g4p6", g4p6);*/
		
	}

	public static void loadPage(String pageIndex)
	{
		System.out.printf("loadPage( %s )\n", pageIndex);
		GUI.clearRootFrame();
		//System.out.println(guiPages.keySet()); //print keys in HashMap
		if(guiPages.containsKey(pageIndex))
		{
			activePage = guiPages.get(pageIndex);
			activePage.activate();
			
			//System.out.printf("Refreshing content pane\n");
			GUI.refreshRootFrame();
		}
		else
		{
			System.out.printf("Error: page not found in guiPages\n");
		}
	}
}