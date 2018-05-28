package ir.maktabsharif.view;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ir.maktabsharif.controller.NetClientPost;
import ir.maktabsharif.model.pojo.Contact;

public class AddContactPageGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_sname;
	private JTextField txt_hnumber;
	private JTextField txt_mobile;
	private JTextField txt_email;
	private NetClientPost ncp1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContactPageGUI frame = new AddContactPageGUI();
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
	public AddContactPageGUI() {
		setTitle("Add contact page");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 152);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 196));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbl_name = new JLabel("Name :");
		lbl_name.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_name.setBounds(10, 24, 46, 14);
		contentPane.add(lbl_name);

		JLabel lbl_surname = new JLabel("Surname :");
		lbl_surname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_surname.setBounds(140, 24, 79, 14);
		contentPane.add(lbl_surname);

		JLabel lbl_hNumber = new JLabel("Home number :");
		lbl_hNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_hNumber.setBounds(310, 24, 92, 14);
		contentPane.add(lbl_hNumber);

		JLabel lbl_mobile = new JLabel("Mobile :");
		lbl_mobile.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_mobile.setBounds(510, 24, 59, 14);
		contentPane.add(lbl_mobile);

		JLabel lbl_email = new JLabel("Email :");
		lbl_email.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbl_email.setBounds(674, 24, 46, 14);
		contentPane.add(lbl_email);

		txt_name = new JTextField();
		txt_name.setBounds(54, 21, 79, 20);
		contentPane.add(txt_name);
		txt_name.setColumns(10);

		txt_sname = new JTextField();
		txt_sname.setText("");
		txt_sname.setBounds(207, 21, 93, 20);
		contentPane.add(txt_sname);
		txt_sname.setColumns(10);

		txt_hnumber = new JTextField();
		txt_hnumber.setText("");
		txt_hnumber.setBounds(401, 21, 99, 20);
		contentPane.add(txt_hnumber);
		txt_hnumber.setColumns(10);

		txt_mobile = new JTextField();
		txt_mobile.setText("");
		txt_mobile.setBounds(561, 21, 103, 20);
		contentPane.add(txt_mobile);
		txt_mobile.setColumns(10);

		txt_email = new JTextField();
		txt_email.setText("");
		txt_email.setBounds(717, 21, 101, 20);
		contentPane.add(txt_email);
		txt_email.setColumns(10);

		JButton btn_add = new JButton("add Contact");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txt_name.getText().isEmpty()) {//if all fields have been filled
					if(txt_hnumber.getText().matches("[0-9]+")){
						if(txt_mobile.getText().matches("^09(1[0-9]|3[1-9]|2[1-9])-?[0-9]{3}-?[0-9]{4}$")){
							if(txt_email.getText().toUpperCase().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")){
								Contact contact=new Contact(txt_name.getText(),txt_sname.getText(),txt_hnumber.getText(),txt_mobile.getText(),txt_email.getText());
								String result;
								try {
									ncp1 = new NetClientPost(SignInPageGUI.txt_serverIP.getText(), SignInPageGUI.txt_serverPort.getText(), contact, "contact");
									result=ncp1.post();
								} catch (MalformedURLException e) {
									result="Error: Malformed URL!";
								}

								if(result==null){
									JOptionPane.showMessageDialog(null,"contact added successfully!");
									setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
									dispose();
									new UsersPageGUI().setVisible(true); 
								}else
									JOptionPane.showMessageDialog(null,
											result);
							}else
								JOptionPane.showMessageDialog(null,
										"Email format is not valid!"); 
						}else
							JOptionPane.showMessageDialog(null,
									"Mobile number format is not valid!"); 
					}else 
						JOptionPane.showMessageDialog(null,
								"Home number format is not valid!");
				}
				else
					JOptionPane.showMessageDialog(null,
							"You must fill contact name field to continue!");
			}
		});
		btn_add.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn_add.setBounds(233, 80, 340, 23);
		contentPane.add(btn_add);
		
	}

}
