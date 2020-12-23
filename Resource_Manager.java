//Stores answer keys to all tests
//Stores images
//Puts modules into hashmap
//Stores motivational quotes
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
import java.util.Random;
import java.awt.image.BufferedImage;

public class Resource_Manager
{
	public static String MULTIPLY = "x";
	public static String ADDITION = "+";
	public static String SUBTRACTION = "-";
	public static String DIVISION = "/";
	
	private static String RESC_PATH = "resc/";
	private static String FILE_EXT = ".png";
	static Map<String, String> guiText = new HashMap<String, String>();
	static Map<String, ImageIcon> images = new HashMap<String, ImageIcon>();
	public static HashMap<String, Module> modules = new HashMap<String, Module>();
	
	private static String IMG_URL = "https://i.imgur.com/";
	
	private static Map<String, AnswerKey> answerKeys = new HashMap<String, AnswerKey>();
	
	public static ArrayList<String> rewardGraphics = new ArrayList<String>();
	
	private static String[] motivationalQuotes;
	
	public static HashMap<String, Color> colorTable = new HashMap<String, Color>();
	
	public static void Init()
	{
		System.out.print("Initiallizing Resource Manager\n");
		InitModules();
		//InitImages();
		InitRewards();
		InitAnswerKeys();
		InitQuotes();
		InitColors();
	}
	
	private static void InitQuotes()
	{
		motivationalQuotes = new String[] {
			"Do or do not, there is no try",
			"Education is the key to unlocking the world",
			"The future depends on what you do today",
			"A person who never made a mistake never tried anything new"
		};
	}
	public static String getRandomMessage(){
		Random rng = new Random();
		return motivationalQuotes[rng.nextInt(motivationalQuotes.length)];
	}
	private static void InitModules()
	{
		//moduleKey, new module( pageKey, Topic, module title, num pages )
										//pageKey, Topic, Subtopic, Material Type, # questions
		modules.put("g4p", new Module("g4p", "Geometry", "Angles", ModuleType.PRAC_TEST_MULTI, 6));
		modules.put("g4t", new Module("g4t", "Geometry", "Angles", ModuleType.TUTORIAL, 6));
		
/* 		//pageKey, Topic, Subtopic, Material Type, # questions, Operator, Max Equation Value
		modules.put("o3p", new Module("o3p", "Operations", "Multiplication", ModuleType.PRAC_TEST_RNG, 6, MULTIPLY, 10));
		modules.put("o1p", new Module("o3p", "Operations", "Addition", ModuleType.PRAC_TEST_RNG, 6, SUBTRACTION, 10));
		modules.put("okp", new Module("o3p", "Operations", "Addition", ModuleType.PRAC_TEST_RNG, 6, ADDITION, 10));
		modules.put("okp", new Module("o3p", "Operations", "Addition", ModuleType.PRAC_TEST_RNG, 6, ADDITION, 10));
		modules.put("okp", new Module("okp", "Operations", "Addition", ModuleType.PRAC_TEST_RNG, 6, ADDITION, 10));
		modules.put("okp", new Module("okp", "Operations", "Addition", ModuleType.PRAC_TEST_RNG, 6, ADDITION, 10)); */
		modules.put("a4pm", new Module("a4pm", "Algebra", "Multiplication", ModuleType.PRAC_TEST_RNG, 6, MULTIPLY, 10));
		modules.put("a4pd", new Module("a4pd", "Algebra", "Division", ModuleType.PRAC_TEST_RNG, 6, DIVISION, 10));
		modules.put("o4ps", new Module("o4ps", "Operations", "Subtraction", ModuleType.PRAC_TEST_RNG, 6, SUBTRACTION, 10));
		modules.put("o4pa", new Module("o4pa", "Operations", "Addition", ModuleType.PRAC_TEST_RNG, 6, ADDITION, 10));
		//modules.put("g4p", new Module("g4p", "Geometry", "Angles", ModuleType.PRAC_TEST_MULTI, 6));
		
		modules.put("a3pm", new Module("a3pm", "Algebra", "Multiplication", ModuleType.PRAC_TEST_RNG, 6, MULTIPLY, 10));
		modules.put("a3pd", new Module("a3pd", "Algebra", "Division", ModuleType.PRAC_TEST_RNG, 6, DIVISION, 10));
		modules.put("o3ps", new Module("o3ps", "Operations", "Subtraction", ModuleType.PRAC_TEST_RNG, 6, SUBTRACTION, 10));
		modules.put("o3pa", new Module("o3pa", "Operations", "Addition", ModuleType.PRAC_TEST_RNG, 6, ADDITION, 10));
		modules.put("g3p", new Module("g3p", "Geometry", "Angles", ModuleType.PRAC_TEST_MULTI, 6));
		
		modules.put("o2ps", new Module("o2ps", "Operations", "Subtraction", ModuleType.PRAC_TEST_RNG, 6, SUBTRACTION, 10));
		modules.put("o2pa", new Module("o2pa", "Operations", "Addition", ModuleType.PRAC_TEST_RNG, 6, ADDITION, 10));
		modules.put("g2p", new Module("g2p", "Geometry", "Shapes", ModuleType.PRAC_TEST_MULTI, 6));
		
		modules.put("c1p", new Module("c1p", "Counting", "Shapes", ModuleType.PRAC_TEST_RNG_SHAPE, 6, 10));
		modules.put("o1ps", new Module("o1ps", "Operations", "Subtraction", ModuleType.PRAC_TEST_RNG, 6, SUBTRACTION, 5));
		modules.put("o1pa", new Module("o1pa", "Operations", "Addition", ModuleType.PRAC_TEST_RNG, 6, ADDITION, 5));
		modules.put("g1p", new Module("g1p", "Geometry", "Shapes", ModuleType.PRAC_TEST_MULTI, 6));
		
		modules.put("cKp", new Module("cKp", "Counting", "Shapes", ModuleType.PRAC_TEST_RNG_SHAPE, 6, 5));
		modules.put("oKp", new Module("oKp", "Operations", "Addition", ModuleType.PRAC_TEST_RNG, 6, ADDITION, 5));
		modules.put("gKp", new Module("gKp", "Geometry", "Counting", ModuleType.PRAC_TEST_MULTI, 6));
	}
	public static void populateUserProfileWithTestValues(){
		//a, g, c, o 
	   //Populate User Profile with scores from various modules
	   //WARNING - Modules must exist
		UserProfile.scores.put("g4p", 80);
		UserProfile.scores.put("o2ps", 90);
		UserProfile.scores.put("o2pa", 75);
		UserProfile.scores.put("c1p", 89);
		UserProfile.scores.put("o1ps", 78);
		UserProfile.scores.put("cKp", 94);
		UserProfile.scores.put("oKp", 56);
		//UserProfile.scores.put("g4t", 76);
	}
	private static void InitRewards()
	{
		rewardGraphics.add("reward_image1.png");
		rewardGraphics.add("reward_image2.png");
		rewardGraphics.add("reward_image3.png");
	}
	
	public static ImageIcon getRandomRewardImage()
	{
		Random rng = new Random();
		return loadImageLocal( RESC_PATH + rewardGraphics.get(rng.nextInt(rewardGraphics.size())) );
	}
	    //Initialize/Load correct answers HashMap
	public static void InitAnswerKeys()
	{
		answerKeys.put("g4p1", new AnswerKey(
			"An Angle is created when?", 0,
			"Two lines share an origin",
			"Two lines never touch", 
			"A line passes through 2 points", 
			"Two lines are overlapped" ));
		answerKeys.put("g4p2", new AnswerKey(
			"An obtuse triangle has:", 2,
			"at least two obtuse angles",
			"1 angle at 90 degrees and 2 angles less than 90 degrees",
			"one obtuse angle",
			"all three angles smaller than 90 degrees" ));
		answerKeys.put("g4p3", new AnswerKey(
			"Perpendicular lines:", 3,
			"Never touch",
			"will touch at some point",
			"create an angle larger than 90 degrees",
			"touch at a 90 degree angle" ));
		answerKeys.put("g4p4", new AnswerKey(
			"Parallel lines:", 0,
			"Never touch",
			"will touch at some point",
			"create an angle smaller than 90 degrees",
			"touch at a 90 degree angle"
		));
		answerKeys.put("g4p5", new AnswerKey(
			"A line:", 0,
			"is a straight connection past two points that continues past both points",
			"is a straight connection that starts at one point and continues past the other",
			"is a straight connection that starts at one point and ends at the other",
			"is a connection between two points, it doesn't  matter how"
		));
		answerKeys.put("g4p6", new AnswerKey(
			"The side of an angle is:", 0,
			"the lines that extend from the shared point",
			"the shared point of the lines",
			"the distance between the lines of an angle",
			"how big the angle is"
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

	public static void InitColors()
	{
		colorTable.put(GUI.GRADE_K, new Color(0,0,255));
		colorTable.put(GUI.GRADE_1, new Color(255,0,255));
		colorTable.put(GUI.GRADE_2, new Color(0,255,0));
		colorTable.put(GUI.GRADE_3, new Color(255,0,0));
		colorTable.put(GUI.GRADE_4, new Color(232,232,0));
	}
	
	public static void InitImages()
	{
		// Grade 4 Geometry Tutorial Images
		images.put("g4t1", getImage_URL(IMG_URL + "oYu5W6B.png")); // Points
		images.put("g4t2", getImage_URL(IMG_URL + "V1YV0o5.png")); // Lines
		images.put("g4t3", getImage_URL(IMG_URL + "jSKFgRr.png")); // Rays
		images.put("g4t4", getImage_URL(IMG_URL + "hz7H3b6.png")); // Angles
		images.put("g4t5", getImage_URL(IMG_URL + "B7VYwKG.png")); // Angles 2
		images.put("g4t6", getImage_URL(IMG_URL + "bPwBfzz.png")); // Angles 3
		// Grade 4 Geometry Tutorial Practice Test Images
		images.put("g4p1", getImage_URL(IMG_URL + "sAfXXL8.png"));
		images.put("g4p2", getImage_URL(IMG_URL + "IfFB3of.png"));
		images.put("g4p3", getImage_URL(IMG_URL + "GBM51uk.png"));
		images.put("g4p4", getImage_URL(IMG_URL + "vFx7PWo.png"));
		images.put("g4p5", getImage_URL(IMG_URL + "mECHqnl.png"));
		images.put("g4p6", getImage_URL(IMG_URL + "R14IoFu.png"));
	}
	//Loads image to image hashMap using url
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
	public static ImageIcon loadResource(String name){
			return loadImageLocal(RESC_PATH + name + FILE_EXT);
	}
	public static ImageIcon loadResourceScaled(String name, int w, int h){
			return getScaledImage(loadImageLocal(RESC_PATH + name + FILE_EXT).getImage(), w, h);
	}
	public static ImageIcon loadImageLocal(String path) 
	{
		java.net.URL imgURL = GUI_Manager.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.out.printf("Couldn't find file: %s----\n", path);
			return null;
		}
	}
	
	public static ImageIcon getScaledImageFromPath(String imagePath, int w, int h){
		return getScaledImage( loadImageLocal(imagePath).getImage(), w, h);
	}
	
	public static ImageIcon getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return new ImageIcon(resizedImg);
    }

	public static String getText(String key)
	{ //using in initilization
		String temp = "";
		if(guiText.containsKey(key))
		{
			return guiText.get(key);
		}
		return temp;
	}
	
}