import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.util.*;

public class ModuleGridLayout extends GUI_Page
{
	private static ArrayList<String> appliedFilters = new ArrayList<String>();

	private String[] FILTER_BUTTONS = { "Tutorial", "Practice Test", "Unit Test" };
	
	final String TUTORIAL = "t";
	final String PRAC_TEST = "p";
	final String UNIT_TEST = "u";
	final String GRADE_K = "K";
	final String GRADE_1 = "1";
	final String GRADE_2 = "2";
	final String GRADE_3 = "3";
	final String GRADE_4 = "4";
	
	static int ROW_CNT = 3;
	static int COL_CNT = 6;

	private String[] filterKeys;
	
	private static JPanel gridContainer;
	
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
		filterKeys = new String[] { TUTORIAL, PRAC_TEST, UNIT_TEST, GRADE_K, GRADE_1, GRADE_2, GRADE_3, GRADE_4 };
	}
	
	public void activate()
	{
		System.out.println("Grid Layout activate");
		
		//Home button
		GUI.addComponent( new HomeButton() );
		
		//Horiz Filter Row Buttons - { Tutorial, Prctice Test, Unit Test}
		int topicFilterXOffset = GUI.BUFFER_SIZE_LG * 2 + GUI.FILTER_BUTTON_WIDTH;
		for(int i = 0; i < FILTER_BUTTONS.length; i++)
		{
			String buttonName = FILTER_BUTTONS[i]; // included for debug print;
			
			FilterButton topicFilterButton = new FilterButton( FILTER_BUTTONS[i], filterKeys[i] );
			topicFilterButton.setSize( GUI.FILTER_BUTTON_WIDTH, GUI.FILTER_BUTTON_HEIGHT );
			topicFilterButton.setLocation( (GUI.FILTER_BUTTON_WIDTH + GUI.BUFFER_SIZE_LG) * i + topicFilterXOffset, GUI.BUFFER_SIZE_LG );
			GUI.addComponent( topicFilterButton );
		}
		
		//Vert Filter Row Buttons
		int gradeFilterYPosOffset = 100;
		int offestStartIndex = 3;
		for(int i = 0; i < 5; i++)
		{
			String buttonName = (i == 0) ? "K" : "Grade " + i;
			FilterButton tutorialFilterBtn = new FilterButton( buttonName, filterKeys[i + offestStartIndex] );
			tutorialFilterBtn.setSize( GUI.FILTER_BUTTON_WIDTH, GUI.FILTER_BUTTON_HEIGHT );
			tutorialFilterBtn.setLocation( GUI.BUFFER_SIZE_LG, (GUI.FILTER_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG)*i + gradeFilterYPosOffset );
			GUI.addComponent( tutorialFilterBtn );
		}
		
		gridContainer = new JPanel();
		GUI.addComponent(gridContainer);
		rePopulateGrid();
	}
	
	//Repopulates grid with cells depending on applied filters
	private static void rePopulateGrid() {
		if(gridContainer.getComponentCount() > 0) {
			System.out.printf("Removing Grid:\n");// exists? %b", GUI.root.con);
			gridContainer.removeAll();
		}
		
		System.out.print("Populating Grid ");

		gridContainer.setSize( GUI.SCREEN_WIDTH - GUI.GRID_CONTAINER_OFFSET, GUI.SCREEN_HEIGHT - (GUI.FILTER_BUTTON_HEIGHT + GUI.BUFFER_SIZE_LG) * 3 );
		gridContainer.setLocation( GUI.BUFFER_SIZE_LG * 2 + GUI.FILTER_BUTTON_WIDTH, GUI.BUFFER_SIZE_LG*2+GUI.FILTER_BUTTON_HEIGHT );
		gridContainer.setBackground(Color.black);
		gridContainer.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		//gridContainer.setOpaque( true );
		//gridContainer.setBackground(Color.white);
		
		for(String key : appliedFilters){
			System.out.print(key);
		}
		System.out.println();
		
		Set<String> keys = GUI_Manager.guiPages.keySet();	//Set<String> of Hashmap keys
		if(GUI.DEBUG)System.out.printf("Keys: Initial cnt - %d\n", keys.size());

		ArrayList<String> tempArrayList = new ArrayList<String>();	//Stores valid pageKeys
		
		for(String k : keys){
			String mat_type = k.substring(2,3); // Extract 3rd character - Material Type
			String grade = k.substring(1,2);	//Extract 2nd character - Grade
			if(GUI.DEBUG)System.out.printf("Key: %s, is? %b\n", mat_type, appliedFilters.contains(mat_type));
			if(!appliedFilters.contains(mat_type) && !appliedFilters.contains(grade) ) {	// if filter not active, add
				tempArrayList.add(k);
			}
		}
		//tempArrayList.remove(GUI.HOME_ID);	//remove main/static pages - included for testing
		//tempArrayList.remove(GUI.GRID_ID);
		
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

	private Color defaultBgColor;
	private Color defaultFgColor;

	private String nameKey;
	
	private int dimensionX, dimensionY;	//prefered dimensions
	
	public Cell(String nameKey, int dimensionX, int dimensionY) {
		this.nameKey = nameKey;
		add(new JLabel(nameKey));
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
		defaultBgColor = getBackground();
		defaultFgColor = getForeground();
		
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GUI_Manager.loadPage(nameKey);
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
}


class FilterButton extends JButton
{
	private String filterKey;
	private boolean isPressed = false;
	private Color defaultColor = Color.DARK_GRAY;
	private Color selectedColor = Color.LIGHT_GRAY;
	
	public FilterButton(String buttonName, String filterKey){
		super(buttonName);
		this.filterKey = filterKey;
		defaultColor = getBackground();
		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(isPressed){
					setBackground(defaultColor);
					ModuleGridLayout.removeFilter(filterKey);
				}
				else{
					setBackground(selectedColor);
					ModuleGridLayout.addFilter(filterKey);
				}
				isPressed = !isPressed;
			}
		} );
	}
}
