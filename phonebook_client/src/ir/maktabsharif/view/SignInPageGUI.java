package ir.maktabsharif.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import ir.maktabsharif.controller.NetClientPost;
import ir.maktabsharif.model.pojo.User;

public class SignInPageGUI extends JFrame {


	private JPanel contentPane;
	private JTextField textField_user;
	private JPasswordField passwordField_pass;
	public static JTextField txt_serverIP;
	public static JTextField txt_serverPort;
	public static NetClientPost ncp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {// main method of the whole program
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInPageGUI frame = new SignInPageGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignInPageGUI() {
		setResizable(false);
		setTitle("SignIn Page");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		dispose();
		setBounds(100, 100, 699, 625);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, new Color(32, 178, 170), null));
		panel.setBackground(new Color(0, 206, 209));
		panel.setBounds(173, 138, 343, 385);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lbl_username = new JLabel("Username :");
		lbl_username.setForeground(new Color(0, 51, 51));
		lbl_username.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_username.setBounds(40, 19, 97, 31);
		panel.add(lbl_username);
		JLabel lbl_password = new JLabel("Password :");
		lbl_password.setForeground(new Color(0, 51, 51));
		lbl_password.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbl_password.setBounds(40, 71, 97, 28);
		panel.add(lbl_password);

		textField_user = new JTextField();
		textField_user.setBounds(163, 21, 145, 31);
		panel.add(textField_user);
		textField_user.setColumns(10);

		JButton btn_signIn = new JButton("Sign in");
		btn_signIn.setFont(new Font("Tahoma", Font.BOLD, 16));
		btn_signIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textField_user.getText().trim().isEmpty() & passwordField_pass.getPassword().length != 0 & !txt_serverIP.getText().isEmpty() & !txt_serverPort.getText().isEmpty()) {//if all fields have been filled
					if(txt_serverIP.getText().matches("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
							"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
							"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
							"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")){
						if(txt_serverPort.getText().matches("^(6553[0-5]|655[0-2]\\d|65[0-4]\\d\\d|6[0-4]\\d{3}|[1-5]\\d{4}|[1-9]\\d{0,3}|0)$")){
							String passText = new String(passwordField_pass.getPassword());
							String userText = new String(textField_user.getText());
							User user=new User(userText,passText);
							String result;
							try {
								ncp = new NetClientPost(txt_serverIP.getText(), txt_serverPort.getText(), user, "user/signin");
								result=ncp.post();
							} catch (MalformedURLException e) {
								result="Error: Malformed URL!";
							}

							if(result==null){
								setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
								dispose();
								new UsersPageGUI().setVisible(true);//directing the user to his/her page 
							}else//if the authentication of the username & password have been failed
								JOptionPane.showMessageDialog(null,
										result);
						}else
							JOptionPane.showMessageDialog(null,
									"server port format is not valid!"); 
					}else
						JOptionPane.showMessageDialog(null,
								"server IP format is not valid!"); 
				}else //if one or more than one of the fields are empty
					JOptionPane.showMessageDialog(null,
							"You must fill all fields of the form to continue!");
			}
		});
		btn_signIn.setBounds(199, 319, 115, 34);
		panel.add(btn_signIn);

		passwordField_pass = new JPasswordField();
		passwordField_pass.setBounds(163, 72, 145, 31);
		panel.add(passwordField_pass);
		passwordField_pass.setToolTipText("Password must contain at least 4 characters");

		JButton btnSignup = new JButton("Sign up");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txt_serverIP.getText().isEmpty() & !txt_serverPort.getText().isEmpty()) {//if server ip and server port fields have been filled
					setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					dispose();
					new SignUpPageGUI().setVisible(true);
				}else
					JOptionPane.showMessageDialog(null,
							"You must fill server IP and server port fields to continue!");
			}
		});
		btnSignup.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSignup.setBounds(38, 319, 115, 34);
		panel.add(btnSignup);

		JLabel lblServerIp = new JLabel("Server IP :");
		lblServerIp.setForeground(new Color(0, 51, 51));
		lblServerIp.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblServerIp.setBounds(40, 122, 97, 28);
		panel.add(lblServerIp);

		JLabel lblServerPort = new JLabel("Server port :");
		lblServerPort.setForeground(new Color(0, 51, 51));
		lblServerPort.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblServerPort.setBounds(40, 174, 113, 28);
		panel.add(lblServerPort);

		JTextPane txtNote = new JTextPane();
		txtNote.setEditable(false);
		txtNote.setText("Note: sign up to our system and then sign in to your account,otherwise if you don't like to register you can sign in as a guest with \"guest\" keyword as username & password");
		txtNote.setForeground(new Color(0, 51, 51));
		txtNote.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtNote.setBackground(new Color(0, 204, 204));
		txtNote.setBounds(40, 223, 268, 67);
		panel.add(txtNote);

		txt_serverIP = new JTextField();
		txt_serverIP.setColumns(10);
		txt_serverIP.setBounds(163, 123, 145, 31);
		panel.add(txt_serverIP);

		txt_serverPort = new JTextField();
		txt_serverPort.setColumns(10);
		txt_serverPort.setBounds(163, 171, 145, 31);
		panel.add(txt_serverPort);

		JLabel lbl_welcome = new JLabel(
				"Welcome To The Public Phonebook System");
		lbl_welcome.setForeground(new Color(0, 102, 102));
		lbl_welcome.setFont(new Font("Snap ITC", Font.PLAIN, 25));
		lbl_welcome.setBounds(46, 40, 607, 75);
		contentPane.add(lbl_welcome);
	}

}
