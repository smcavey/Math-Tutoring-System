import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;

public class LoginLayout extends GUI_Page
{
	private int textFieldWidth = 250;
	private int textFieldHeight = 40;
	private int btnHeight = 40;
	private int btnWidth = 110;
	
	private int titleHeight = 150;
	private int titleWidth = 450;
	
	private int IMG_ICON_SIZE = 256;
	
	private static String IMG_A = "login_img_a.png";
	private static String IMG_B = "login_img_b.png";
	private static String IMG_C = "login_img_c.png";
	private static JTextField [] textFields = new JTextField [2];

	public void activate()
	{
		int graphicWidth = GUI.SCREEN_WIDTH/2;// - (GUI.BUFFER_SIZE_LG * 2);  	//area width for login images
		//int graphicHeight = GUI.SCREEN_HEIGHT - (GUI.BUFFER_SIZE_LG * 3);		//area heigth for login images
		
		//Top Left
		JLabel graphicA = new JLabel(Resource_Manager.loadImageLocal(IMG_A));
		graphicA.setSize( IMG_ICON_SIZE, IMG_ICON_SIZE );
		graphicA.setLocation( GUI.BUFFER_SIZE_LG, GUI.BUFFER_SIZE_LG );
		GUI.addComponent( graphicA );
		
		//Top Right
		JLabel graphicB = new JLabel(Resource_Manager.loadImageLocal(IMG_B));
		graphicB.setSize( IMG_ICON_SIZE, IMG_ICON_SIZE );
		graphicB.setLocation( graphicWidth - IMG_ICON_SIZE, GUI.BUFFER_SIZE_LG );
		GUI.addComponent( graphicB );
		
		//Center Bottom
		JLabel graphicC = new JLabel(Resource_Manager.loadImageLocal(IMG_C));
		graphicC.setSize( IMG_ICON_SIZE, IMG_ICON_SIZE );
		graphicC.setLocation( (GUI.SCREEN_WIDTH/4) - IMG_ICON_SIZE/2, GUI.SCREEN_HEIGHT - (GUI.SCREEN_HEIGHT/4) - IMG_ICON_SIZE/2);
		GUI.addComponent( graphicC );
		
		
		JPanel titleContainer = new JPanel();
		//titleContainer.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		GridLayout gridLayout = new GridLayout(2,1);
		
		titleContainer.setLayout( gridLayout );
		titleContainer.setSize( titleWidth, titleHeight );
		titleContainer.setLocation( graphicWidth/2 - titleWidth/2, GUI.SCREEN_HEIGHT/2 - titleHeight/2 );
		titleContainer.setBackground( Color.black );
		GUI.addComponent( titleContainer );
		
		//Company Name
		JLabel companyName = new JLabel("TUWE", SwingConstants.CENTER);
		companyName.setFont( new Font("Ariel", Font.PLAIN, 40) );
		companyName.setForeground( Color.white );
		companyName.setBackground( Color.black );
		titleContainer.add( companyName );
		
		//Application Title
		JLabel applicationName = new JLabel( "Math Tutor System", SwingConstants.CENTER );
		applicationName.setFont( new Font("Calibri", Font.PLAIN, 36) );
		applicationName.setForeground( Color.white );
		applicationName.setBackground( Color.black );
		titleContainer.add( applicationName );
		
		int mid_graphicStartX = graphicWidth + (GUI.BUFFER_SIZE_LG * 2);
		int mid_graphicHeight = GUI.SCREEN_HEIGHT - (GUI.SCREEN_HEIGHT / 2);
		JLabel mid_graphic = new JLabel();
		mid_graphic.setSize( GUI.BUFFER_SIZE_LG, mid_graphicHeight );
		mid_graphic.setLocation( GUI.SCREEN_WIDTH/2, GUI.SCREEN_HEIGHT/2 - mid_graphicHeight/2 );
		mid_graphic.setBackground( Color.white );
		GUI.addComponent( mid_graphic );
			
		JPanel loginContainer = new JPanel();
		//loginContainer.setBorder(new LineBorder(Color.LIGHT_GRAY, 2));
		gridLayout = new GridLayout(3,1);
		gridLayout.setVgap( GUI.BUFFER_SIZE_LG );
		loginContainer.setLayout( gridLayout );
		int loginContainerHeight = textFieldHeight*3 + GUI.BUFFER_SIZE_LG*2;
		loginContainer.setSize( textFieldWidth, loginContainerHeight );
		loginContainer.setLocation( GUI.SCREEN_WIDTH - GUI.SCREEN_WIDTH/4 - textFieldWidth/2, GUI.SCREEN_HEIGHT/2 - loginContainerHeight/2 );
		loginContainer.setBackground( Color.black );
		GUI.addComponent( loginContainer );
		
		String[] fieldNames = { "User Name", "Password" };
		for(int i = 0; i < textFields.length; i++){
			JTextField field = new JTextField(fieldNames[i]);
			textFields[i] = field;
			/* if(i == 0){
				credentials[i] = field.getText();
				System.out.println(credentials[i]);
			} else{
				credentials[i] = field.getText();
				System.out.println(credentials[i]);
			} */
			field.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
						field.setText("");
				}
			});
			loginContainer.add( field );
		}
		
		JPanel accountButtonContainer = new JPanel();
		gridLayout = new GridLayout(1,2);
		gridLayout.setHgap( GUI.BUFFER_SIZE_LG );
		accountButtonContainer.setLayout( gridLayout );
		accountButtonContainer.setBackground( Color.black );
		loginContainer.add( accountButtonContainer );
		
		
		JLabel loginButton = new JLabel("LOGIN");
		loginButton.setForeground( Color.white );
		loginButton.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		loginButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//put in if block so if credential verification is completed then go to home_id
				//if(AccountHelper.IsValidPassword(AccountHelper.userCredentials[0], AccountHelper.userCredentials[1])){
				if(AccountHelper.isVaildPassword(textFields[0].getText(), textFields[1].getText())){
					GUI_Manager.loadPage( GUI.HOME_ID );
				}
			}
			public void mouseEntered(MouseEvent evt) {
				loginButton.setBorder( new LineBorder(Color.white, 2) );
			}
			public void mouseExited(MouseEvent evt) {
				loginButton.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
			}
		});
		accountButtonContainer.add( loginButton );
		
		JLabel createUserButton = new JLabel("CREATE");
		createUserButton.setForeground( Color.white );
		createUserButton.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		createUserButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GUI_Manager.loadPage( GUI.CREATE_ID );
			}
			public void mouseEntered(MouseEvent evt) {
				createUserButton.setBorder( new LineBorder(Color.white, 2) );
			}
			public void mouseExited(MouseEvent evt) {
				createUserButton.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
			}
		});
		accountButtonContainer.add( createUserButton );
			//field.setLocation( btn_groupStartX, offset );
		//field.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
		/*String[] fieldNames = { "User Name", "Password" };
		int btn_groupStartY = GUI.SCREEN_HEIGHT / 3;
		int btn_groupStartX = GUI.SCREEN_WIDTH - mid_graphicStartX + ((mid_graphicStartX/2) - (textFieldWidth/2));
		int btn_groupOffsetY = btn_groupStartY;
		for(int i = 0; i < 2; i++){
			JTextField field = new JTextField(fieldNames[i]);
			field.setSize( textFieldWidth, textFieldHeight );
			int offset = btn_groupStartY + (textFieldHeight + GUI.BUFFER_SIZE_LG) * i;
			field.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					field.setText("");
				}
			});
			field.setLocation( btn_groupStartX, offset );
			field.setBorder( new LineBorder(Color.LIGHT_GRAY, 2) );
			GUI.addComponent( field );
			btn_groupOffsetY += textFieldHeight + GUI.BUFFER_SIZE_LG;
		}
		
		
		
		String[] btnNames = { "Login", "Create" };
		for(int i = 0; i < 2; i++){
			JButton btn = new JButton(btnNames[i]);
			btn.setSize( btnWidth, btnHeight );
			btn.setLocation( btn_groupStartX + (GUI.BUFFER_SIZE_LG + btnWidth) * i, btn_groupOffsetY);
			btn.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUI_Manager.loadPage(GUI.HOME_ID);
				}
			} );
			GUI.addComponent( btn );
		}*/
				//Mid screen dividing line

	}
}