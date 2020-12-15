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

public class Resource_Manager
{
	private static String RESC_PATH = "resc/";
	static Map<String, String> guiText = new HashMap<String, String>();
	static Map<String, ImageIcon> images = new HashMap<String, ImageIcon>();
	public static HashMap<String, Module> modules = new HashMap<String, Module>();
	
	private static String IMG_URL = "https://i.imgur.com/";
	
	private static Map<String, AnswerKey> answerKeys = new HashMap<String, AnswerKey>();
	
	public static ArrayList<String> rewardGraphics = new ArrayList<String>();
	
	private static String[] motivationalQuotes;
	public static void Init()
	{
		System.out.print("Initiallizing Resource Manager\n");
		InitModules();
		InitImages();
		InitRewards();
		InitAnswerKeys();
		InitQuotes();
	}
	private static void InitQuotes()
	{
		motivationalQuotes = new String[] {
			"Do or do not, there is no try",
			"Education is the key to unlocking the world",
			"The future depends on what you do today",
			"A person who never made a mistake never tried anythign new"
		};
	}
	public static String getRandomMessage(){
		Random rng = new Random();
		return motivationalQuotes[rng.nextInt(motivationalQuotes.length)];
	}
	private static void InitModules()
	{
		//moduleKey, new module( pageKey, Topic, module title, num pages )
		modules.put("g4p", new Module("g4p", "Geometry", "Angles", 6));
		modules.put("g4t", new Module("g4t", "Geometry", "Angles", 6));
		modules.put("g3p", new Module("g4p", "Operations", "Multiplication", 6));
		modules.put("gkp", new Module("g4p", "Numbers", "Base 10", 6));
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
			"Two lines share a similar point but go off in different directions.",
			"Two lines never touch", 
			"A line passes through 2 points", 
			"Two lines are overlapped" ));
		answerKeys.put("g4p2", new AnswerKey(
			"An obtuse triangle has:", 2,
			"at least two obtuse angles",
			"one angle at 90 degrees and two angles smaller than 90 degrees",
			"one obtuse angle",
			"all three angles smaller than 90 degrees." ));
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
			"is a connection between two points, it doesn’t  matter how"
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
	public static void InitImages()
	{
		// Grade 4 Geometry Tutorial Images
		images.put("g4t1", getImage_URL(IMG_URL + "oYu5W6B.png")); // Points
		images.put("g4t2", getImage_URL(IMG_URL + "V1YV0o5.png")); // Lines
		images.put("g4t3", getImage_URL(IMG_URL + "jSKFgRr.png")); // Rays
		images.put("g4t4", getImage_URL(IMG_URL + "hz7H3b6.png")); // Angles
		images.put("g4t5", getImage_URL(IMG_URL + "B7VYwKG.png")); // Angles 2
		images.put("g4t6", getImage_URL(IMG_URL + "bPwBfzz.png")); // Angles 3
		//images.put("g4t7", getImage_URL(IMG_URL + "Z5yvvjs.png")); // Parallel And Perpendicular Lines Note: only 6 questions for g4t
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
	
	//Loads mage to image hashMap using url
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