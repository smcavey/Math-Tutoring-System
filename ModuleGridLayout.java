import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.util.*;
import java.util.Map.Entry;

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
		
		//Home button
		GUI.addComponent( new HomeButton() );
		
		//Starting X position of grid container and filter button groups
		int xOffset = GUI.BUFFER_SIZE_LG * 2 + GUI.FILTER_BUTTON_WIDTH;	//+BUF+BTN+BUF+
		
		
		//Filter Button Section Start
		JPanel filterButtonContainer = new JPanel();
		filterButtonContainer.setBackground( Color.black );
		
		GridLayout gridLayout = new GridLayout(1,3);
		gridLayout.setHgap( GUI.BUFFER_SIZE_LG );
		
		filterButtonContainer.setLayout(gridLayout);
		filterButtonContainer.setLocation( xOffset, GUI.BUFFER_SIZE_LG );
		int filterContainerWidth = GUI.FILTER_BUTTON_WIDTH*3 + GUI.BUFFER_SIZE_LG*4;
		filterButtonContainer.setSize( filterContainerWidth, GUI.FILTER_BUTTON_HEIGHT );
		
		for(int i = 0; i < FILTER_BUTTONS.length; i++)
		{			
			FilterButton topicFilterButton = new FilterButton( FILTER_BUTTONS[i], filterKeys[i] );
			filterButtonContainer.add(topicFilterButton);
		}
		GUI.addComponent(filterButtonContainer);
		//Filter Button Section End
		
		JPanel gradeFilterButtonContainer = new JPanel();
		
		gridLayout = new GridLayout(5,1);
		gridLayout.setVgap( GUI.BUFFER_SIZE_LG );
		gradeFilterButtonContainer.setLayout( gridLayout );
		gradeFilterButtonContainer.setLocation( GUI.BUFFER_SIZE_LG, yOffset);
		gradeFilterButtonContainer.setSize( GUI.FILTER_BUTTON_WIDTH, GUI.FILTER_BUTTON_HEIGHT*5 + GUI.BUFFER_SIZE_LG*5 );
		gradeFilterButtonContainer.setBackground( Color.black );
		//gridLayout.setHgap( GUI.BUFFER_SIZE_LG );
		//Vert Filter Row Buttons
		int offestStartIndex = 3;
		for(int i = 0; i < 5; i++)
		{
			String buttonName = (i == 0) ? "K" : "Grade " + i;
			FilterButton tutorialFilterBtn = new FilterButton( buttonName, filterKeys[i + offestStartIndex] );
			//tutorialFilterBtn.setSize( GUI.FILTER_BUTTON_WIDTH, GUI.FILTER_BUTTON_HEIGHT );
			//tutorialFilterBtn.setLocation( GUI.BUFFER_SIZE_LG, (GUI.FILTER_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG)*i + yOffset );
			//GUI.addComponent( tutorialFilterBtn );
			gradeFilterButtonContainer.add( tutorialFilterBtn );
		}
		
		GUI.addComponent( gradeFilterButtonContainer );
		
		
		gridContainer = new JPanel();
		gridContainer.setSize( filterContainerWidth, GUI.SCREEN_HEIGHT - (GUI.FILTER_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG) * 3 );
		gridContainer.setLocation( xOffset, GUI.BUFFER_SIZE_LG*2+GUI.FILTER_BUTTON_HEIGHT );
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
				System.out.print(key);
			}
			System.out.println();
		}
		
		//Set<String> keys = Resource_Manager.modules.keySet();	//Set<String> of Hashmap keys
		
		//if(GUI.DEBUG)System.out.printf("Keys: Initial cnt - %d\n", keys.size());

		/*HashSet<String> hashSet = new HashSet<String>();  	//temp hashset
		for(String k : keys){
			
			if(k == GUI.HOME_ID || k == GUI.GRID_ID || k == GUI.RESULTS_ID || k == GUI.PROGRESS_ID || k == GUI.LOGIN_ID)
				continue;
				
			hashSet.add(k.substring(0,3));					//removes trailing char from key
		}*/
		
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
		int pad = 5;					//padding between grid cells
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

	private static int START_PAGE = 1;
	
	private Color defaultBgColor;
	private Color defaultFgColor;

	private String pageKey;
	
	private int dimensionX, dimensionY;	//prefered dimensions
	
	public Cell(Module module, int dimensionX, int dimensionY) {
		this.pageKey = module.pageKey;
		add(new JLabel(module.topic));
		add(new JLabel(module.subTopic));
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
		setBackground(getBackgroundColor(module.topic));
		defaultBgColor = getBackground();
		defaultFgColor = getForeground();
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Module.setActiveModule(pageKey);
				GUI_Manager.loadPage(Module.getActiveModule().getCurrentPageKey());
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
	
	private static Color getBackgroundColor(String topic)
	{
		if(topic.equals("Geometry"))
		{
			return Color.blue;
		}
		else if(topic.equals("Algebra"))
		{
			return Color.red;
		}
		else
		{
			return Color.cyan;
		}
	}
}


class FilterButton extends JLabel
{
	private String filterKey;
	private boolean isPressed = false;
	private Color defaultColor = Color.black;
	private Color selectedColor = Color.LIGHT_GRAY;
	
	public FilterButton(String buttonName, String filterKey){
		super(buttonName, SwingConstants.CENTER);
		this.filterKey = filterKey;
		setVerticalTextPosition(JLabel.CENTER);
		setVerticalAlignment(JLabel.CENTER);
		setBackground(Color.white);
		setForeground(Color.black);
		setOpaque(true);
		setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
		setFont(new Font("Calibri", Font.PLAIN, 24));
		defaultColor = getBackground();
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(isPressed){
					setBackground(defaultColor);
					setForeground(Color.black);
					ModuleGridLayout.removeFilter(filterKey);
				}
				else{
					setBackground(selectedColor);
					setForeground(Color.DARK_GRAY);
					ModuleGridLayout.addFilter(filterKey);
				}
				isPressed = !isPressed;
			}
		});
	}
}
