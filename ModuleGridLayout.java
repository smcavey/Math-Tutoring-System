//ModuleGridLayout contains the filter functions for sorting modules displayed
//ModuleGridLayout has wrapper class Cell which contains the Module, title, and image
//getBackgroundColor() uses rgb coloring to color code the modules
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.util.*;
import java.util.Map.Entry;
import javax.swing.ImageIcon;

public class ModuleGridLayout extends GUI_Page
{
	private static ArrayList<String> appliedFilters = new ArrayList<String>();

	private String[] FILTER_BUTTONS = { "Tutorial", "Practice Test", "Unit Test" };
	
	static int ROW_CNT = 3;
	static int COL_CNT = 6;

	private String[] filterKeys;
	
	private static JPanel gridContainer;

	int yOffset = 100;
	
	//Adds filterKey from applied filters
	public static void addFilter(String filterKey) {
		appliedFilters.add(filterKey);
		if(GUI.DEBUG){
			for(String str : appliedFilters){
				System.out.println(str);
			}
		}
		rePopulateGrid();
	}
	
	//Removes filterKey from applied filters
	public static void removeFilter(String filterKey) { 
		if(appliedFilters.remove(filterKey)) {
			rePopulateGrid();
		}
		else {
			System.out.printf("Error: %s did not exist in applied Filters", filterKey);
		}

	}
	
	//Constructor
	public ModuleGridLayout(){
		filterKeys = new String[] { GUI.TUTORIAL, GUI.PRAC_TEST, GUI.UNIT_TEST, 
									GUI.GRADE_K, GUI.GRADE_1, GUI.GRADE_2, GUI.GRADE_3, GUI.GRADE_4 };
	}
	
	public void activate()
	{
		System.out.println("Grid Layout activate");
		appliedFilters.clear();
		
		//Home button
		GUI.addComponent( new HomeButton() );
		
		//Starting X position of grid container and filter button groups
		int xOffset = GUI.BUFFER_SIZE_LG * 2 + GUI.FILTER_BUTTON_WIDTH;	//+BUF+BTN+BUF+
		
		//Filter Button Section Start
		JPanel filterButtonContainer = new JPanel();
		filterButtonContainer.setBackground( Color.black );
		
		GridLayout gridLayout = new GridLayout(1,3);
		gridLayout.setHgap( GUI.BUFFER_SIZE_LG );
		
		filterButtonContainer.setLayout( gridLayout );
		filterButtonContainer.setLocation( xOffset, GUI.BUFFER_SIZE_LG );
		
		int mainWidth = GUI.MODULE_GRID_CELL_WIDTH*6 + GUI.BUFFER_SIZE_LG*3; //6 cells w/ padding left/+right for each
		
		filterButtonContainer.setSize( mainWidth, GUI.FILTER_BUTTON_HEIGHT + GUI.BUFFER_SIZE*2);
		
		for(int i = 0; i < FILTER_BUTTONS.length; i++)
		{
			JPanel container = new JPanel();
			container.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
			container.setOpaque(false);
			FilterButton topicFilterButton = new FilterButton( FILTER_BUTTONS[i], filterKeys[i] );
			//gridLayout = new GridLayout(1,2);
			//container.setLayout( gridLayout );
			//container.setLayout( new BoxLayout(container, BoxLayout.X_AXIS));
			//container.add(Box.createRigidArea(new Dimension(GUI.BUFFER_SIZE_LG, 0)));
			container.add(topicFilterButton);
			
			//container.add(Box.createHorizontalGlue());
			//ImageIcon topicFilterButtonImage = new ImageIcon(MATTYPE_BUTTON_IMAGES[i]);
			JLabel imageHolder = new JLabel(Resource_Manager.loadResourceScaled(Module.MATTYPE_BUTTON_IMAGES[i], GUI.FILTER_BUTTON_HEIGHT, GUI.FILTER_BUTTON_HEIGHT));
			imageHolder.setBackground( Color.black );
			imageHolder.setOpaque( true );
			container.add(imageHolder);
			filterButtonContainer.add( container );
		}
		GUI.addComponent( filterButtonContainer );
		//Filter Button Section End
		
		JPanel gradeFilterButtonContainer = new JPanel();
		
		gridLayout = new GridLayout(5,1);
		gridLayout.setVgap( GUI.BUFFER_SIZE_LG );
		gradeFilterButtonContainer.setLayout( gridLayout );
		gradeFilterButtonContainer.setLocation( GUI.BUFFER_SIZE_LG, yOffset );
		gradeFilterButtonContainer.setSize( GUI.FILTER_BUTTON_WIDTH, GUI.FILTER_BUTTON_HEIGHT*5 + GUI.BUFFER_SIZE_LG*5 );
		gradeFilterButtonContainer.setBackground( Color.black );
		
		//gridLayout.setHgap( GUI.BUFFER_SIZE_LG );
		//Vert Filter Row Buttons
		int offestStartIndex = 3;
		for(int i = 0; i < 5; i++)
		{
			String buttonName = (i == 0) ? GUI.GRADE_K : "Grade " + i;
			FilterButton tutorialFilterBtn = new FilterButton( buttonName, filterKeys[i + offestStartIndex] );
			tutorialFilterBtn.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
			//tutorialFilterBtn.setSize( GUI.FILTER_BUTTON_WIDTH, GUI.FILTER_BUTTON_HEIGHT );
			//tutorialFilterBtn.setLocation( GUI.BUFFER_SIZE_LG, (GUI.FILTER_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG)*i + yOffset );
			//GUI.addComponent( tutorialFilterBtn );
			gradeFilterButtonContainer.add( tutorialFilterBtn );
		}
		
		GUI.addComponent( gradeFilterButtonContainer );
		
		gridContainer = new JPanel();
		gridContainer.setSize( mainWidth, GUI.SCREEN_HEIGHT - (GUI.FILTER_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG) * 3 );
		gridContainer.setLocation( xOffset, GUI.BUFFER_SIZE_LG*2 + GUI.FILTER_BUTTON_HEIGHT );
		gridContainer.setBackground( Color.black );
		gridContainer.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		GUI.addComponent( gridContainer );
		
		rePopulateGrid();
	}
	
	//Repopulates grid with cells depending on applied filters
	private static void rePopulateGrid() {
		if(gridContainer.getComponentCount() > 0) {
			System.out.printf("Removing Grid:\n");// exists? %b", GUI.root.con);
			gridContainer.removeAll();
		}
		
		if(GUI.DEBUG){
			System.out.print("Populating Grid ");

			for(String key : appliedFilters){
				System.out.println(key);
			}
		}
		
		ArrayList<Module> tempArrayList = new ArrayList<Module>();	//Stores valid pageKeys
		//foreach Module in Resource_Manager.modules
		for(Entry<String, Module> entry : Resource_Manager.modules.entrySet())
		{
			//if(GUI.DEBUG)System.out.printf("Key: %s, is? %b\n", mat_type, appliedFilters.contains(mat_type));
			if(!appliedFilters.contains( entry.getValue().getType() ) &&
				!appliedFilters.contains( entry.getValue().getGrade() )) {	// if filter not active, add
				tempArrayList.add( entry.getValue() );
			}
		}
		//if grade & topic match filters, add to teplist
		//loop through temp list and populate grid
		
		int cnt = 0;
		int pad = GUI.BUFFER_SIZE;					//padding between grid cells
		int lim = tempArrayList.size();	
	
		gridContainer.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		for (int y = 0; y < ROW_CNT; y++) {
			gbc.gridy = y;
			for (int x = 0; x < COL_CNT; x++) {
				gbc.gridx = x;
				
				if(cnt < lim) {

					if(cnt==lim-1) { //pushes last cell to top of container panel
						gbc.weighty = 1;
						if(lim <= COL_CNT) //if single row, push left
							gbc.weightx = 1;
					}
					gbc.insets = new Insets(pad,pad,pad,pad);
					gbc.anchor = GridBagConstraints.NORTHWEST;
					gridContainer.add(new Cell(tempArrayList.get(cnt), GUI.MODULE_GRID_CELL_WIDTH, GUI.MODULE_GRID_CELL_HEIGHT), gbc);
					cnt++;
				}
				else {
					break;
				}
			}
		}
		
		if(GUI.DEBUG)System.out.printf("container component cnt: %d\n", gridContainer.getComponentCount());

		gridContainer.revalidate();	//refresh grid container panel
		gridContainer.repaint();
	}
}

class Cell extends JPanel {

	private Color defaultBgColor;
	private Color defaultFgColor;

	private String pageKey;
	
	private int dimensionX, dimensionY;	//prefered dimensions
	//private String[] MATTYPE_BUTTON_IMAGES = { "tutorial_small.png", "practice_small.png", "test_small.png" };
	
	public Cell(Module module, int dimensionX, int dimensionY) {
		this.pageKey = module.pageKey;
		//this.setAlignmentX( Component.CENTER_ALIGNMENT );
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel topicLabel = new JLabel(module.topic);
		topicLabel.setAlignmentX( Component.CENTER_ALIGNMENT );
		
		Color col = new Color(255,255,255);
		topicLabel.setFont( new Font("Dialog", Font.BOLD, 14) );
		if(GUI.DEBUG) topicLabel.setBorder( new LineBorder(Color.red, 1) );
		topicLabel.setForeground(col);
		add(topicLabel);
		JLabel subTopicLabel = new JLabel(module.subTopic);
		subTopicLabel.setForeground(col);
		if(GUI.DEBUG) subTopicLabel.setBorder( new LineBorder(Color.red, 1) );
		add(subTopicLabel);
		subTopicLabel.setAlignmentX( Component.CENTER_ALIGNMENT );
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
		setBackground(getBackgroundColor(module.topic, module.getGrade()));
		defaultBgColor = getBackground();
		defaultFgColor = getForeground();
		
		
		int index = 1;
		//String imagePath = Module.MATTYPE_BUTTON_IMAGES[0];
		//ModuleCatagory modCat = module.moduleCatagory;
		String type = module.getType();
		System.out.print(type);
		if(GUI.TUTORIAL.equals(type))
		{
			index = 0;
		}
		else if(GUI.UNIT_TEST.equals(type))
		{
			index = 2;
		}
		String imagePath = Module.MATTYPE_BUTTON_IMAGES[index];
		//ImageIcon moduleImage = new ImageIcon(imagePath);
		JLabel modCatImage = new JLabel(Resource_Manager.loadResource(imagePath));//, GUI.MODULE_GRID_CELL_WIDTH, GUI.MODULE_GRID_CELL_WIDTH));
		modCatImage.setAlignmentX( Component.CENTER_ALIGNMENT );
		if(GUI.DEBUG) modCatImage.setBorder( new LineBorder(Color.red, 1) );
		add(modCatImage);
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Module.setActiveModule(pageKey);
				GUI_Manager.loadPage(Module.getActiveModule().getFirstPage());
/* 				ModuleType moduleType = Module.getActiveModule().moduleType;
				if(moduleType == ModuleType.TUTORIAL || moduleType == ModuleType.PRAC_TEST_MULTI){
					pageKey = pageKey + "1";
				} 
				GUI_Manager.loadPage(pageKey);//Module.getActiveModule().getCurrentPageKey()); */
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent evt) {
				setBackground(Color.GRAY);
				setForeground(Color.white);
			}
			public void mouseExited(MouseEvent evt) {
				setBackground(defaultBgColor);
				setForeground(defaultFgColor);
			}
      });
	  setBorder(new LineBorder(Color.GRAY));
	}

	public Dimension getPreferredSize() {
		return new Dimension(dimensionX, dimensionY);
	}
	
	private static Color getBackgroundColor(String topic, String grade)
	{
		//System.out.println("topic: " + topic + " grade: " + grade);
		//Module.getGrade()
		// return Color.decode("#FFFFFF"); to use hex


		//String[] grades = new String { new Color(0,0,255), new Color(255,0,255), new Color(0,255,0), new Color(255,0,0), new Color(255,255,0) }
		String[] subjects = new String[] { "Geometry", "Algebra", "Counting", "Operations" };
		int modifier = 0;
		int mod = 35;
		Color col = Resource_Manager.colorTable.get(grade);
		//System.out.println(topic);
		for(String str : subjects){
			if(str.equals(topic)){
				int r = col.getRed();
				int g = col.getGreen();
				int b = col.getBlue();
				r = (r > 0) ? r - modifier : 0;
				g = (g > 0) ? g - modifier : 0;
				b = (b > 0) ? b - modifier : 0;
				Color newCol = new Color( r, g, b );
				//System.out.println(newCol.toString());
				return newCol;
			}
			else{
				modifier += mod;
			}
		}
		//System.out.println(col.toString());
		
		return Color.black;
		/* if(topic.equals("Geometry"))
		{
			if(grade.equals("k"))
			{
				return Color.decode("#00DCFF");
			}
			if(grade.equals("1"))
			{
				return Color.decode("#2E00FF");
			}
			if(grade.equals("2"))
			{
				return Color.decode("#23FF00");
			}
			if(grade.equals("3"))
			{
				return Color.decode("#FF0000");
			}
			if(grade.equals("4"))
			{
				return Color.decode("#DCFF00");
			}
		}
		if(topic.equals("Algebra"))
		{
			if(grade.equals("k"))
			{
				return Color.decode("#00B1CD");
			}
			if(grade.equals("1"))
			{
				return Color.decode("#1F00AB");
			}
			if(grade.equals("2"))
			{
				return Color.decode("#1ABC00");
			}
			if(grade.equals("3"))
			{
				return Color.decode("#B00000");
			}
			if(grade.equals("4"))
			{
				return Color.decode("#BDDB00");
			}
		}
		if(topic.equals("Counting"))
		{
			if(grade.equals("k"))
			{
				return Color.decode("#0094AC");
			}
			if(grade.equals("1"))
			{
				return Color.decode("#180083");
			}
			if(grade.equals("2"))
			{
				return Color.decode("#128100");
			}
			if(grade.equals("3"))
			{
				return Color.decode("#5F0000");
			}
			if(grade.equals("4"))
			{
				return Color.decode("#8FA500");
			}
		}
		if(topic.equals("Operations"))
		{
			if(grade.equals("k"))
			{
				return Color.decode("#005A68");
			}
			if(grade.equals("1"))
			{
				return Color.decode("#0B003E");
			}
			if(grade.equals("2"))
			{
				return Color.decode("#083800");
			}
			if(grade.equals("3"))
			{
				return Color.decode("#240000");
			}
			if(grade.equals("4"))
			{
				return Color.decode("#4E5A00");
			}
		}
		return Color.black;
		*/
	} 
}

class FilterButton extends JLabel
{
	private String filterKey;
	private boolean isPressed = false;
	private static Color defaultBgColor = Color.black;
	private static Color selectedBgColor = Color.darkGray;
	private static Color defaultTextColor = Color.white;
	private static Color selectTextColor = Color.lightGray;
	
	public FilterButton(String buttonName, String filterKey){
		super(buttonName, SwingConstants.CENTER);
		this.filterKey = filterKey;
		//setVerticalTextPosition(JLabel.CENTER);
		//setVerticalAlignment(JLabel.CENTER);
		setOpaque(true);
		setBackground(defaultBgColor);
		setForeground(defaultTextColor);
		
		//setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		setFont(new Font("Dialog", Font.PLAIN, 24));
		//defaultBgColor = getBackground();
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				isPressed = !isPressed;
				if(isPressed){ //Selected
					System.out.println(buttonName);
					setBackground(selectedBgColor);
					setForeground(selectTextColor);
					ModuleGridLayout.addFilter(filterKey);
				}
				else{ //deselected
					setBackground(defaultBgColor);
					setForeground(defaultTextColor);
					ModuleGridLayout.removeFilter(filterKey);
				}
			}
		});
	}
}
