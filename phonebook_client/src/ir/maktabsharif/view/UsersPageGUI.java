package ir.maktabsharif.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ir.maktabsharif.controller.NetClientGet;
import ir.maktabsharif.controller.NetClientPost;
import ir.maktabsharif.model.pojo.Contact;
import ir.maktabsharif.model.pojo.ContactLiteDTO;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class UsersPageGUI extends JFrame {

	private JPanel contentPane;
	private NetClientGet ncg;
	private NetClientPost ncp1;
	private List<ContactLiteDTO> contactLiteList;
	private Contact fullContact;


	/**
	 * Create the frame.
	 */
	public UsersPageGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 241);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton btn_logOut = new JButton("Log out");
		btn_logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dispose();
				new SignInPageGUI().setVisible(true);
			}
		});
		btn_logOut.setBackground(new Color(244, 164, 96));
		btn_logOut.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_logOut.setBounds(275, 34, 100, 36);
		contentPane.add(btn_logOut);

		JButton btn_showContacts = new JButton("Show contacts");
		btn_showContacts.setBackground(new Color(255, 222, 173));
		btn_showContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String result;
				try {
					ncg=new NetClientGet(SignInPageGUI.txt_serverIP.getText(), SignInPageGUI.txt_serverPort.getText(),"contact/getAll/liteContact");
					result=ncg.get();
					contactLiteList=new ArrayList<ContactLiteDTO>();
					contactLiteList=ncg.getLiteContactList();
				} catch (MalformedURLException e) {
					result="Error: Malformed URL!";
				}
				if(result==null){
					String col[] = {"Contact ID","Name","Surname","Home number"};
					DefaultTableModel tableModel1 = new DefaultTableModel(col, 0);// The 0 argument is number rows.
					JTable table1 = new JTable(tableModel1);
					for (int i = 0; i < contactLiteList.size(); i++) {
						Object[] objs = {contactLiteList.get(i).getId(),contactLiteList.get(i).getName(),contactLiteList.get(i).getSurname(),contactLiteList.get(i).getHomeNumber()};
						tableModel1.addRow(objs);
					}
					JScrollPane sp1 = new JScrollPane(table1);
					sp1.setBounds(17, 105, 600, 100);
					contentPane.add(sp1);

					table1.addMouseListener(new java.awt.event.MouseAdapter(){
						public void mouseClicked(MouseEvent e)
						{
							int selectedRowIndex = table1.getSelectedRow();
							String selectedContactID=tableModel1.getValueAt(selectedRowIndex, 0).toString();

							showContact(selectedContactID);
						}
					});
				}else
					JOptionPane.showMessageDialog(null,result);
			}
		});
		btn_showContacts.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_showContacts.setBounds(38, 34, 151, 36);
		contentPane.add(btn_showContacts);

		JButton btn_addContacts = new JButton("Add contacts");
		btn_addContacts.setBackground(new Color(255, 222, 173));
		btn_addContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dispose();

				new AddContactPageGUI().setVisible(true);
			}
		});
		btn_addContacts.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_addContacts.setBounds(458, 34, 151, 36);
		contentPane.add(btn_addContacts);

		String role=SignInPageGUI.ncp.getUserRole().toString();

		if(role.contains("seeContacts")){
			contentPane.add(btn_showContacts);
		}
		if (role.contains("createContacts")) {
			contentPane.add(btn_addContacts);
		}

	}
	public void showContact(String selectedContactID) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 175);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 232, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btn_back = new JButton("back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				dispose();
				new UsersPageGUI().setVisible(true);
			}
		});

		String result;
		try {
			ncg=new NetClientGet(SignInPageGUI.txt_serverIP.getText(), SignInPageGUI.txt_serverPort.getText(),"contact/"+selectedContactID);
			result=ncg.get();
			fullContact=ncg.getContact();
		} catch (MalformedURLException e1) {
			result="Error: Malformed URL!";
		}
		if(result==null){
			String col1[] = {"ID","Name","Surname","Home number","Mobile","Email"};
			DefaultTableModel tableModelFull = new DefaultTableModel(col1, 0);// The 0 argument is number rows.
			JTable tableFull = new JTable(tableModelFull);
			Object[] objsFull = {fullContact.getId(),fullContact.getName(),fullContact.getSurname(),fullContact.getHomeNumber(),fullContact.getMobile(),fullContact.getEmail()};
			tableModelFull.addRow(objsFull);
			JScrollPane spFull = new JScrollPane(tableFull);
			spFull.setBounds(23, 21, 550, 53);
			contentPane.add(spFull);



			btn_back.setBackground(new Color(189, 183, 107));
			btn_back.setBounds(243, 91, 89, 23);
			contentPane.add(btn_back);
		}else
			JOptionPane.showMessageDialog(null,result);
	}

}

