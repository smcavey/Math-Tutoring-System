//CreateUserLayout extends GUI_Page
//CreateUserLayout is the create user page
//The username, password, confirm password, and email text fields are contained in CreateUserLayout
//CreateUserLayout takes the information input in text fields and checks for validity passing them to AccountHelper
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.util.*;
import java.awt.image.BufferedImage;

public class CreateUserLayout extends GUI_Page
{
	private static int textFieldWidth = 250;
	private static int textFieldHeight = 40;
	private int btnHeight = 40;
	private int btnWidth = 110;
	
	private int tileHeight = 50;
	private int titleWidth = 400;
	
	private int IMG_ICON_SIZE = 256;
	private static int graphicWidth = (GUI.SCREEN_WIDTH / 2) - (GUI.BUFFER_SIZE_LG * 2);
	
	private static JTextField [] credentials = new JTextField [4];
	
	private static int createUserPanelYOffset;
	private static int createUserPanelXOffset;

	private static JPanel errorContainer;
	
	public CreateUserLayout(){
		
	}
	
	public void activate()
	{
		JLabel tuwe = new JLabel("TUWE", SwingConstants.CENTER);
		tuwe.setFont(new Font("Calibri", Font.PLAIN, 36));
		tuwe.setSize( textFieldWidth + GUI.BUFFER_SIZE_LG, textFieldHeight );
		tuwe.setLocation( (GUI.SCREEN_WIDTH/2) - (textFieldWidth/2), GUI.BUFFER_SIZE_LG);
		tuwe.setForeground( Color.white );
		GUI.addComponent( tuwe );
		
		JLabel mts = new JLabel("Math Tutoring System", SwingConstants.CENTER);
		mts.setFont(new Font("Calibri", Font.PLAIN, 36));
		mts.setSize( textFieldWidth + GUI.BUFFER_SIZE_LG *4, textFieldHeight );
		mts.setLocation( (GUI.SCREEN_WIDTH/2) - ((textFieldWidth + GUI.BUFFER_SIZE_LG *3)/2), GUI.BUFFER_SIZE_LG * 3);
		mts.setForeground( Color.white );
		GUI.addComponent( mts );
		
		JLabel title = new JLabel("Create username and password", SwingConstants.CENTER);
		title.setFont(new Font("Calibri", Font.PLAIN, 20));
		title.setSize( textFieldWidth + GUI.BUFFER_SIZE_LG, textFieldHeight );
		title.setLocation( (GUI.SCREEN_WIDTH / 2) - (textFieldWidth / 2), (GUI.SCREEN_HEIGHT / 2) - (textFieldHeight * 4) - GUI.BUFFER_SIZE * 6 );
		title.setForeground( Color.white );
		GUI.addComponent(title);
		
		JPanel createUserButtonContainer = new JPanel();
		createUserButtonContainer.setBackground( Color.black );
		
		GridLayout gridLayout = new GridLayout(5,1);
		gridLayout.setVgap( GUI.BUFFER_SIZE_LG );
		
		int panelContainerHeight = textFieldHeight*4 + btnHeight + GUI.BUFFER_SIZE_LG*4;
		createUserButtonContainer.setLayout(gridLayout);
		createUserPanelYOffset = GUI.SCREEN_HEIGHT/2 - panelContainerHeight/2;//(GUI.SCREEN_HEIGHT / 2) - (textFieldHeight * 4); 
		createUserPanelXOffset = (GUI.SCREEN_WIDTH / 2) - (textFieldWidth / 2);
		createUserButtonContainer.setLocation(createUserPanelXOffset, createUserPanelYOffset);
		createUserButtonContainer.setSize(textFieldWidth, (textFieldHeight * 7));
		
		String[] fieldNames = { "User Name", "Password", "Confirm Password", "Optional Recovery Email" };
/* 		int btn_groupStartY = GUI.SCREEN_HEIGHT / 5;
		int btn_groupStartX = (GUI.SCREEN_WIDTH / 5);
		int btn_groupOffsetY = btn_groupStartY; */
		for(int i = 0; i < 4; i++){
			JTextField field = new JTextField(fieldNames[i]);
			credentials[i] = field;
			//field.setSize( textFieldWidth, textFieldHeight );
			//int offset = btn_groupStartY + (textFieldHeight + GUI.BUFFER_SIZE_LG) * i;
			field.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					field.setText("");
				}
			});
			//field.setLocation( btn_groupStartX, offset );
			field.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
			createUserButtonContainer.add( field );
			//btn_groupOffsetY += textFieldHeight + GUI.BUFFER_SIZE_LG;
		}
		
		JPanel twoButtonsContainer = new JPanel();
		twoButtonsContainer.setBackground( Color.black );
		
		GridLayout othergridLayout = new GridLayout(1,2);
		othergridLayout.setHgap( textFieldWidth - (btnWidth * 2) );
		
		twoButtonsContainer.setLayout(othergridLayout);
		//twoButtonsContainer.setLocation((GUI.SCREEN_WIDTH / 2) - (textFieldWidth / 2), (GUI.SCREEN_HEIGHT / 2) - (textFieldHeight * 5) + (btn_groupOffsetY * 5));
		//twoButtonsContainer.setSize(textFieldWidth, btnHeight);
		
		ActionButton backbtn = new ActionButton("Back");
		//TODO: backbtn.addIcon("add back button image here");
		//btn.setSize( btnWidth, btnHeight );
		//btn.setLocation( (GUI.SCREEN_WIDTH / 2) - (textFieldWidth / 2), (GUI.SCREEN_HEIGHT / 2) - (textFieldHeight * 5) + (btn_groupOffsetY * 5));
		backbtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GUI_Manager.loadPage(GUI.LOGIN_ID);
			}
		} );
		twoButtonsContainer.add( backbtn );
		
		ActionButton createbtn = new ActionButton("Create");
		//TODO: createbtn.addIcon("add create button image here");
		createbtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				AccountHelper.createUser(credentials[0].getText(), credentials[1].getText(), credentials[2].getText());
			}
		} );
		twoButtonsContainer.add( createbtn );
		createUserButtonContainer.add( twoButtonsContainer );
		GUI.addComponent( createUserButtonContainer );
		
		//******************************Error Message Container*********************//
		errorContainer = new JPanel();
		errorContainer.setBackground( Color.black );
		errorContainer.setLayout(new BoxLayout(errorContainer, BoxLayout.Y_AXIS));
		errorContainer.setLocation( createUserPanelXOffset + textFieldWidth + GUI.BUFFER_SIZE_LG, createUserPanelYOffset);
		errorContainer.setSize( textFieldWidth, textFieldHeight*3 + GUI.BUFFER_SIZE_LG*2 );

		GUI.addComponent(errorContainer);
	}

	public static void errGenerator(String[] errMsgs)
	{
		errorContainer.removeAll();
		
		for(int i = 0; i < errMsgs.length; i++)
		{
			System.out.println(errMsgs[i]);
			if(errMsgs[i] == null)
			{
				errorContainer.add(Box.createRigidArea(new Dimension(0,textFieldHeight + GUI.BUFFER_SIZE_LG)));
			}
			else
			{
				JLabel label = new JLabel(errMsgs[i]);
				label.setForeground( Color.white );
				errorContainer.add( label );//errorLabels[i].setText( errMsgs[i] );//makeVisible( errMsgs[i] );
			}
			//errorLabels[i].revalidate();
		}
		errorContainer.revalidate();
		errorContainer.repaint();
	}
}
