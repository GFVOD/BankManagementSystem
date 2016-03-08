import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class panel_UserInfo extends JPanel {
	private JTextField textField_Name;
	private JTextField textField_ID;
	private JTextField textField_Username;
	private JTextField textField_Password;
	private JTextField textField_UserType;
	private JTextField textField_Email;
	private JTextField textField_MobileNum;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public panel_UserInfo(String username,UserPage_FourthFrame admin) throws SQLException {
		
		ImageIcon bank=new ImageIcon("image.jpg");
		
		JLabel label = new JLabel(bank);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int p=JOptionPane.showConfirmDialog(getParent(), "are you sure you want to logout??", "Logout", JOptionPane.YES_NO_OPTION);
				if(p==JOptionPane.YES_OPTION)
				{
					(new LoginSignup_SecondFrame()).setVisible(true);
					admin.dispose();
				}
				
			}
		});
		JButton btnSave = new JButton("Save");

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setEnabled(false);
		
		JLabel lblName = new JLabel("Name :-");
		
		JLabel lblCoustmerId = new JLabel("Coustmer ID :-");
		
		JLabel lblUsername = new JLabel("Username :-");
		
		JLabel lblPassword = new JLabel("Password :-");
		
		JLabel lblUserType = new JLabel("User Type :-");
		
		JLabel lblEmail = new JLabel("Email :-");
		
		JLabel lblMobileNumber = new JLabel("Mobile Number:-");
		
		JLabel lblGender = new JLabel("Gender :-");
		
		textField_Name = new JTextField();
		textField_Name.setEditable(false);
		textField_Name.setColumns(10);
		
		textField_ID = new JTextField();
		textField_ID.setEditable(false);
		textField_ID.setColumns(10);
		
		textField_Username = new JTextField();
		textField_Username.setEditable(false);
		textField_Username.setColumns(10);
		
		textField_Password = new JTextField();
		textField_Password.setEditable(false);
		textField_Password.setColumns(10);
		
		textField_UserType = new JTextField();
		textField_UserType.setEditable(false);
		textField_UserType.setColumns(10);
		
		textField_Email = new JTextField();
		textField_Email.setEditable(false);
		textField_Email.setColumns(10);
		
		textField_MobileNum = new JTextField();
		textField_MobileNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)))
				{
					evt.consume();
				}
			}
		});
		textField_MobileNum.setEditable(false);
		textField_MobileNum.setColumns(10);
		
		JButton btnEdit = new JButton("Edit");
		

		String query="select * from profile where username = ?";
		PreparedStatement ps=DBInfo.con.prepareStatement(query);
		ps.setString(1, username);
		
		ResultSet res=ps.executeQuery();
		while(res.next())
		{
			textField_Name.setText(res.getString("name"));
			textField_ID.setText(res.getString("coustmerid"));
			String gender=res.getString("gender");
			comboBox.addItem(gender);
			if(gender.equalsIgnoreCase("male"))
				comboBox.addItem("Female");
			else
				comboBox.addItem("Male");
			textField_Email.setText(res.getString("emailid"));
			textField_MobileNum.setText(res.getString("mobilenum"));
			textField_Password.setText(res.getString("password"));
			textField_UserType.setText(res.getString("usertype"));
			textField_Username.setText(res.getString("username"));
			
		}		
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_Email.setEditable(true);
				comboBox.setEnabled(true);
				textField_MobileNum.setEditable(true);
				textField_Name.setEditable(true);
				textField_Password.setEditable(true);
				textField_UserType.setEditable(true);
			}
		});
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int opt=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to make changes??", "Confirm!", JOptionPane.YES_NO_OPTION);
				if(opt==JOptionPane.YES_OPTION)
				{
					String query1="update profile set name = ?,mobilenum=?,emailid=?,password=?,usertype=?,gender=? where username=?";
					PreparedStatement ps1;
					try 
					{
						ps1 = DBInfo.con.prepareStatement(query1);
						ps1.setString(1, textField_Name.getText());
						ps1.setString(2, textField_MobileNum.getText());
						ps1.setString(3, textField_Email.getText());
						ps1.setString(4, textField_Password.getText());
						ps1.setString(5, textField_UserType.getText());
						ps1.setString(6, comboBox.getSelectedItem().toString());
						ps1.setString(7, username);
						ps1.executeUpdate();
						textField_Email.setEditable(false);
						textField_UserType.setEditable(false);
						textField_Password.setEditable(false);
						textField_Name.setEditable(false);
						textField_MobileNum.setEditable(false);
					} catch (SQLException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(53)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnLogout)
						.addComponent(label))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMobileNumber, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_MobileNum, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_Email, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUserType, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_UserType, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_Password, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCoustmerId, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_ID, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblName, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField_Name, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnEdit)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnSave))
								.addComponent(comboBox, 0, 188, Short.MAX_VALUE))))
					.addGap(150))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(18)
					.addComponent(btnLogout)
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCoustmerId)
						.addComponent(textField_ID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(textField_Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserType)
						.addComponent(textField_UserType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(textField_Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMobileNumber)
						.addComponent(textField_MobileNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGender)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEdit)
						.addComponent(btnSave))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		

	}
}
