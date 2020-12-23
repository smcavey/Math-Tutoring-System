//HomeButton is the class for which all gui pages with a home button get their home button from
//HomeButton is initialized here and set up with sizing and action listener
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.*;

public class HomeButton extends JLabel
{
	public static int home_btn_size = 36;
	private String image_path = "home_image.png";
	
	public HomeButton(){
		setIcon(Resource_Manager.loadImageLocal(image_path));
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GUI_Manager.loadPage(GUI.HOME_ID);
			}
		});
		setSize( home_btn_size, home_btn_size );
		setLocation( GUI.SCREEN_WIDTH - (home_btn_size*2 + GUI.BUFFER_SIZE), GUI.BUFFER_SIZE );
	}
}