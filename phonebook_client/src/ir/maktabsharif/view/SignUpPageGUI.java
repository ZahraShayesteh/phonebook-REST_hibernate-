package ir.maktabsharif.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import ir.maktabsharif.controller.NetClientPost;
import ir.maktabsharif.model.pojo.User;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;

public class SignUpPageGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_username;
	private JPasswordField passwordField_pass;
	private User user;

	/**
	 * Create the frame.
	 */
	public SignUpPageGUI() {
		setTitle("Sign up page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 222, 179));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_username = new JLabel("Username :");
		lbl_username.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_username.setBounds(94, 54, 101, 24);
		contentPane.add(lbl_username);

		JLabel lbl_password = new JLabel("Password :");
		lbl_password.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_password.setBounds(94, 89, 101, 17);
		contentPane.add(lbl_password);

		JLabel lbl_description = new JLabel("Note: you should fill both fields to sign up into our system");
		lbl_description.setBounds(64, 129, 328, 14);
		contentPane.add(lbl_description);

		txt_username = new JTextField();
		txt_username.setBounds(184, 57, 128, 20);
		contentPane.add(txt_username);
		txt_username.setColumns(10);

		passwordField_pass = new JPasswordField();
		passwordField_pass.setBounds(184, 88, 128, 20);
		contentPane.add(passwordField_pass);

		JButton btn_signup = new JButton("Sign up");
		btn_signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txt_username.getText().isEmpty() & passwordField_pass.getPassword().length != 0){
					String passText = new String(passwordField_pass.getPassword());
					String userText = new String(txt_username.getText());
					user= new User(userText,passText);
					NetClientPost ncp;
					String result;
					try {
						ncp = new NetClientPost(SignInPageGUI.txt_serverIP.getText(),SignInPageGUI.txt_serverPort.getText(), user, "user/signup");
						result=ncp.post();
					} catch (MalformedURLException e) {
						result="Error: Malformed URL!";
					}

					if(result==null){
						JOptionPane.showMessageDialog(null,"Congratulations,successfully sing up :) ");
					}else
						JOptionPane.showMessageDialog(null,result);
				}
				else
					lbl_description.setText("You should fill both fields of username & password!");
			}
		});
		btn_signup.setBackground(new Color(222, 184, 135));
		btn_signup.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_signup.setBounds(46, 201, 128, 24);
		contentPane.add(btn_signup);

		JLabel lbl_signupFailed = new JLabel("");
		lbl_signupFailed.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_signupFailed.setForeground(new Color(255, 0, 0));
		lbl_signupFailed.setBounds(139, 160, 46, 14);
		contentPane.add(lbl_signupFailed);

		JButton btn_back = new JButton("back to sign in");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dispose();
				new SignInPageGUI().setVisible(true);
			}
		});
		btn_back.setBackground(new Color(222, 184, 135));
		btn_back.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_back.setBounds(235, 201, 128, 24);
		contentPane.add(btn_back);
	}
}
