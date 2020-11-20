import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.*;

public class HomeButton extends JButton
{
	public HomeButton(){
		super("HOME");
		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Manager.loadPage( GUI.HOME_ID );
			}
		} );
		setSize( GUI.HOMEBUTTON_SIZE, GUI.HOMEBUTTON_SIZE );
		setLocation( GUI.SCREEN_WIDTH - (GUI.HOMEBUTTON_SIZE*2 + GUI.BUFFER_SIZE), GUI.BUFFER_SIZE );
		setForeground( Color.white );
	}
}