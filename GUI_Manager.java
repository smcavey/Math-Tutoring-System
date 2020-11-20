import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class GUI_Manager
{	
	static Map<String, ImageIcon> images = new HashMap<String, ImageIcon>();
	
	public static Map<String, GUI_Page> guiPages = new HashMap<String, GUI_Page>();
        
	static Map<String, String> guiText = new HashMap<String, String>();
	
	static Map<String, AnswerKey> answerKeys = new HashMap<String, AnswerKey>();
	static GUI_Page activePage;
	
	public static void Init()
	{
		System.out.print("GUI_Manager Init\n");
		
		System.out.print("Initiallizing Iamge Repository\n");
		InitImages();
		InitAnswerKeys();
		
		//Topic:Grade:Type
		/*
		Topic - Geometry = G, Counting = C, ...
		Grade - K, 1, 2, 3, 4
		Type - Tutorial = T, Practice Test = P, Unit Test = U
		Page
		*/
		HomePageLayout home_page = new HomePageLayout();
		guiPages.put(GUI.HOME_ID, home_page);
		
		TestResultLayout results_page = new TestResultLayout();
		guiPages.put(GUI.RESULTS_ID, results_page);
		
		ModuleGridLayout grid_selection_page = new ModuleGridLayout();
		guiPages.put(GUI.GRID_ID, grid_selection_page);
		
		//Geometry, Grade 4, Tutorial
		TutorialLayout g4t1 = new TutorialLayout("g4t1", "Geometry Tutorial 1", "", "g4t2", "g4p1");
		guiPages.put("g4t1", g4t1);
		
		TutorialLayout g4t2 = new TutorialLayout("g4t2", "Geometry Tutorial 2", "g4t1", "g4t3", "g4p1");
		guiPages.put("g4t2", g4t2);
		
		TutorialLayout g4t3 = new TutorialLayout("g4t3", "Geometry Tutorial 3", "g4t2", "g4t4", "g4p1");
		guiPages.put("g4t3", g4t3);
		
		TutorialLayout g4t4 = new TutorialLayout("g4t4", "Geometry Tutorial 4", "g4t3", "g4t5", "g4p1");
		guiPages.put("g4t4", g4t4);
                
		TutorialLayout g4t5 = new TutorialLayout("g4t5", "Geometry Tutorial 5", "g4t4", "", "g4p1");
		guiPages.put("g4t5", g4t5);
		
		TestLayout g4p1 = new TestLayout("g4p1", "Geometry Practice Problem 1", "", "g4p2");
		guiPages.put("g4p1", g4p1);
		
		TestLayout g4p2 = new TestLayout("g4p2", "Geometry Practice Problem 2", "g4p1", "g4p3");
		guiPages.put("g4p2", g4p2);
		
		TestLayout g4p3 = new TestLayout("g4p3", "Geometry Practice Problem 3", "g4p2", "g4p4");
		guiPages.put("g4p3", g4p3);
		
		TestLayout g4p4 = new TestLayout("g4p4", "Geometry Practice Problem 4", "g4p3", "g4p5");
		guiPages.put("g4p4", g4p4);
		
		TestLayout g4p5 = new TestLayout("g4p5", "Geometry Practice Problem 5", "g4p4", "done");
		guiPages.put("g4p5", g4p5);
		
	}
        //Initialize/Load correct answers HashMap
	public static void InitAnswerKeys()
	{
		answerKeys.put("g4p1", new AnswerKey(
			"What is this Shape?", 1,
			"square", "triangle", "circle", "diamond" ));
		answerKeys.put("g4p2", new AnswerKey(
			"What is 5 + 6?", 1,
			"square", "one", "circle", "diamond" ));
		answerKeys.put("g4p3", new AnswerKey(
			"When is?", 2,
			"square", "triangle", "now", "diamond" ));
		answerKeys.put("g4p4", new AnswerKey(
			"What is up with...?", 3,
			"orange", "triangle", "circle", "diamond"
		));
		answerKeys.put("g4p5", new AnswerKey(
			"Which is?", 3,
			"square", "triangle", "circle", "green"
		));
	}
	//Returns from answer hashMap
	//Initialize/Load Iamge HashMap
	public static AnswerKey getAnswerKeys(String key)
	{
		AnswerKey temp = null;
		if(answerKeys.containsKey(key))
		{
			return answerKeys.get(key);
		}
		return temp;
	}
	public static void InitImages()
	{
		images.put("g4p1", getImage_URL("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQL982UhQJLyWnpbRWuWIZh40vH71ycACa89g&usqp=CAU"));
	}
	
	//Loads mage to image hashMap using url
	private static ImageIcon getImage_URL(String url){ //using in initilization
		ImageIcon image = null;
		try{
			image = new ImageIcon(ImageIO.read(new URL(url)));
			} catch(IOException ex) {
			ex.printStackTrace();
		}
		return image;
	}
	
	//Retuns from image hashMap
	public static ImageIcon getImage(String imageName)
	{
		return images.containsKey(imageName) ? images.get(imageName) : null;
	}
        
	//Loads mage to image hashMap using url
	public static String getText(String key){ //using in initilization
		String temp = "";
		if(guiText.containsKey(key))
		{
			return guiText.get(key);
		}
		return temp;
	}
	
	public static void loadPage(String pageIndex)
	{
		System.out.printf("loadPage( %s )\n", pageIndex);
		//unloadPage();//clears all GUI elements from current page
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
	/*public static void unloadPage()
	{
		System.out.printf("Unloading page\n");
		//get components currently attached to content panel of root pane
		Component[] componemts = GUI.root.getContentPane().getComponents();

		for(Component c : componemts) {				//loop through them
			 GUI.root.remove(c);	//remove from content panel, garbage collected
		}
	}*/
}