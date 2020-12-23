//AccountHelper assits with LoginLayout and CreateUserLayout
//AccountHelper stores user credentials and also is where new account credentials are sent to the database
//LoginLayout checks userExists() and isValidPassword() for login
//Error messages are also stored and processed
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.util.*;
import java.util.HashMap;

public class AccountHelper
{
	public static HashMap<String, String> userCredentials = new HashMap<String, String>();
	
	public static boolean createUser(String userName, String password, String chk_password) // return boolean if successful account creation
	{
		String[] errMsgs = new String[4];
		//If user Exists
		if(userExists(userName)){
			errMsgs[0] = "User Name Already Exists";
		}
		else if("".equals(password))
		{
			errMsgs[1] = "Invalid Password";
		}
		else if(password.equals(chk_password))
		{
			userCredentials.put(userName, password);
			errMsgs[0] = "User Created";
			CreateUserLayout.errGenerator(errMsgs);
			return true;
		}
		else
		{
			errMsgs[1] = "Passwords do not match";
		}
		
		CreateUserLayout.errGenerator(errMsgs);
		return false;
		/*
		if(userExists(array[0].getText())){
			System.out.println(array[0].getText());
			return false;
		}
		if(!array[1].getText() .equals(""))
		{
			System.out.println(array[0].getText());
			System.out.println(array[1].getText());
			if(!array[2].getText().equals(""))
			{
				if(array[1].getText().equals( array[2].getText()))//for some reason this is true even if !array[1].equals array[2]
				{
					System.out.println(array[2].getText());
					userCredentials.put(array[0].getText(), array[1].getText());
				}
			}
		}
		else 
			return false;
		
		CreateUserLayout.errGenerator(usernameErr, passwordErr, confirmPasswordErr, emailErr);
		return true;*/
	}

	public static void init(){
        userCredentials.put("Joe", "pw");
        userCredentials.put("Sam", "oq");
    }
	public static boolean userExists(String userName){
        return userCredentials.containsKey(userName);
    }
    public static boolean isVaildPassword(String userName, String password){
		if(userCredentials.containsKey(userName))
		{
			return userCredentials.get(userName).equals( password );
		}
		else
		{
			return false;
		}
    }
}
