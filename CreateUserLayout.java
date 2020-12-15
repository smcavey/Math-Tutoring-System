import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.util.*;

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
	
	public static ErrorLabel[] errorLabels = new ErrorLabel[4];
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
		title.setLocation( (GUI.SCREEN_WIDTH / 2) - ((textFieldWidth + GUI.BUFFER_SIZE_LG) / 2), (GUI.SCREEN_HEIGHT / 2) - (textFieldHeight * 4) - GUI.BUFFER_SIZE * 6 );
		title.setForeground( Color.white );
		GUI.addComponent(title);
		
		int gradeFilterYPosOffset = 100;
		JPanel createUserButtonContainer = new JPanel();
		createUserButtonContainer.setBackground( Color.black );
		
		GridLayout gridLayout = new GridLayout(5,1);
		gridLayout.setVgap( GUI.BUFFER_SIZE_LG );
		
		createUserButtonContainer.setLayout(gridLayout);
		createUserPanelYOffset = (GUI.SCREEN_HEIGHT / 2) - (textFieldHeight * 4); 
		createUserPanelXOffset = (GUI.SCREEN_WIDTH / 2) - ((textFieldWidth + GUI.BUFFER_SIZE_LG * 3) / 2);
		createUserButtonContainer.setLocation(createUserPanelXOffset, createUserPanelYOffset);
		createUserButtonContainer.setSize(textFieldWidth + (GUI.BUFFER_SIZE_LG * 3), (textFieldHeight * 7));
		
		String[] fieldNames = { "User Name", "Password", "Confirm Password", "Optional Recovery Email" };
		int btn_groupStartY = GUI.SCREEN_HEIGHT / 5;
		int btn_groupStartX = (GUI.SCREEN_WIDTH / 5);
		int btn_groupOffsetY = btn_groupStartY;
		for(int i = 0; i < 4; i++){
			JTextField field = new JTextField(fieldNames[i]);
			credentials[i] = field;
			//field.setSize( textFieldWidth, textFieldHeight );
			int offset = btn_groupStartY + (textFieldHeight + GUI.BUFFER_SIZE_LG) * i;
			field.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					field.setText("");
				}
			});
			//field.setLocation( btn_groupStartX, offset );
			field.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
			createUserButtonContainer.add( field );
			btn_groupOffsetY += textFieldHeight + GUI.BUFFER_SIZE_LG;
		}
		
		JPanel twoButtonsContainer = new JPanel();
		twoButtonsContainer.setBackground( Color.black );
		
		GridLayout othergridLayout = new GridLayout(1,2);
		othergridLayout.setHgap( textFieldWidth - (btnWidth * 2) );
		
		twoButtonsContainer.setLayout(othergridLayout);
		twoButtonsContainer.setLocation((GUI.SCREEN_WIDTH / 2) - (textFieldWidth / 2), (GUI.SCREEN_HEIGHT / 2) - (textFieldHeight * 5) + (btn_groupOffsetY * 5));
		twoButtonsContainer.setSize(textFieldWidth, btnHeight);
		
		JButton backbtn = new JButton("Back");
		//btn.setSize( btnWidth, btnHeight );
		//btn.setLocation( (GUI.SCREEN_WIDTH / 2) - (textFieldWidth / 2), (GUI.SCREEN_HEIGHT / 2) - (textFieldHeight * 5) + (btn_groupOffsetY * 5));
		backbtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Manager.loadPage(GUI.LOGIN_ID);
			}
		} );
		twoButtonsContainer.add( backbtn );
		
		JButton createbtn = new JButton("Create");
		createbtn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AccountHelper.createUser(credentials[0].getText(), credentials[1].getText(), credentials[2].getText());
			}
		} );
		twoButtonsContainer.add( createbtn );
		createUserButtonContainer.add( twoButtonsContainer );
		GUI.addComponent( createUserButtonContainer );
		
		errorContainer = new JPanel();
		errorContainer.setBackground( Color.black );
		GridLayout errorGridLayout = new GridLayout(4,1);
		errorGridLayout.setVgap( GUI.BUFFER_SIZE_LG );
		errorContainer.setLayout( errorGridLayout );
		errorContainer.setLocation( GUI.SCREEN_WIDTH/2 + (textFieldWidth + GUI.BUFFER_SIZE), (GUI.SCREEN_HEIGHT / 2) - (textFieldHeight * 4));
		errorContainer.setSize( textFieldWidth + (GUI.BUFFER_SIZE_LG * 3), (textFieldHeight * 7) );
		
		errorLabels[0] = new ErrorLabel();
		errorContainer.add(errorLabels[0]);
		errorLabels[1] = new ErrorLabel();
		errorContainer.add(errorLabels[1]);
		errorLabels[2] = new ErrorLabel();
		errorContainer.add(errorLabels[2]);
		errorLabels[3] = new ErrorLabel();
		errorContainer.add(errorLabels[3]);
		
		GUI.addComponent(errorContainer);
	}
	public static void errGenerator(String[] errMsgs)
	{
		for(int i = 0; i < errMsgs.length; i++)
		{
			System.out.println(errMsgs[i]);
			errorLabels[i].makeVisible( errMsgs[i] );
			//errorLabels[i].revalidate();
		}
		errorContainer.revalidate();
	}
	/*public static void errGenerator(boolean usernameErr, boolean passwordErr, boolean confirmPasswordErr, boolean emailErr)
	{
		//errorContainer = new JPanel(); //this will be black boxes next to the username, password,...etc boxes that should populate text when error conditions are met
		JLabel usernameErrLabel = new JLabel("User Name Already Exists");
		errorContainer.add(usernameErrLabel);
		if(usernameErr)
		{
			usernameErrLabel.setForeground( Color.white );
			usernameErrLabel.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
			usernameErrLabel.setVisible(false);
		}
		else
		{
			usernameErrLabel.setVisible(false);
		}
		
		
		JLabel passwordErrLabel = new JLabel("Invalid Password");
		errorContainer.add(passwordErrLabel);
		if(passwordErr)
		{
			passwordErrLabel.setForeground( Color.white );
			passwordErrLabel.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		}
		else
		{
			passwordErrLabel.setVisible(false);
		}
		
		JLabel confirmPasswordErrLabel = new JLabel("Invalid Password");
		errorContainer.add(confirmPasswordErrLabel);
		if(confirmPasswordErr)
		{
			confirmPasswordErrLabel.setForeground( Color.white );
			confirmPasswordErrLabel.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		}
		else
		{
			confirmPasswordErrLabel.setVisible(false);
		}
		
		JLabel emailErrLabel = new JLabel("Invalid Email");
		errorContainer.add(emailErrLabel);
		if(emailErr)
		{
			emailErrLabel.setForeground( Color.white );
			emailErrLabel.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		}
		else
		{
			emailErrLabel.setVisible(false);
		}
		
		GUI.addComponent(errorContainer);
		
	}*/
}

class ErrorLabel extends JLabel
{
	public ErrorLabel(){
		setForeground( Color.white );
		//setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
	}
	
	public void makeVisible( String str )
	{
		if( str != null )
		{
			setText(str);
			setVisible(true);
		}
		else{
			setVisible(false);
		}
		revalidate();
	}
}
