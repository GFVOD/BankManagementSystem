import javax.swing.JPanel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;

@SuppressWarnings("serial")
public class panel_UserDetails extends JPanel {
	private JTextField textField_Username;
	private JTextField textField_Name;
	private JTextField textField_CustID;
	private JTextField textField_Mobile;
	private JTextField textField_Email;
	private JTextField textField_Usertype;
	private JTextField textField_Bal;
	private JTextField textField_Gender;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public panel_UserDetails(String username,AdminPage_FourthFrame admin) throws SQLException {
		ImageIcon bank=new ImageIcon("image.jpg");
		JLabel label = new JLabel(bank);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int p=JOptionPane.showConfirmDialog(getParent(), "are you sure you want to logout??", "Logout", JOptionPane.YES_NO_OPTION);
				if(p==JOptionPane.YES_OPTION)
				{
					(new LoginSignup_SecondFrame()).setVisible(true);
					admin.dispose();
				}
			}
		});
		
		JLabel lblEnterUsername = new JLabel("Enter Username :-");
		
		textField_Username = new JTextField();
		textField_Username.setColumns(10);
		
		JButton btnShow = new JButton("Show");
		
		JLabel lblName = new JLabel("Name :-");
		
		JLabel lblCoustmerId = new JLabel("Coustmer ID :-");
		
		JLabel lblMobileNum = new JLabel("Mobile Number :-");
		
		JLabel lblEmailId = new JLabel("Email ID :-");
		
		JLabel lblUsertype = new JLabel("Usertype :-");
		
		JLabel lblBalance = new JLabel("Balance :-");
		
		JLabel lblGender = new JLabel("Gender :-");
		
		textField_Name = new JTextField();
		textField_Name.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_Name.setEditable(false);
		textField_Name.setColumns(10);
		
		textField_CustID = new JTextField();
		textField_CustID.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_CustID.setEditable(false);
		textField_CustID.setColumns(10);
		
		textField_Mobile = new JTextField();
		textField_Mobile.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_Mobile.setEditable(false);
		textField_Mobile.setColumns(10);
		
		textField_Email = new JTextField();
		textField_Email.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_Email.setEditable(false);
		textField_Email.setColumns(10);
		
		textField_Usertype = new JTextField();
		textField_Usertype.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_Usertype.setEditable(false);
		textField_Usertype.setColumns(10);
		
		textField_Bal = new JTextField();
		textField_Bal.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_Bal.setEditable(false);
		textField_Bal.setColumns(10);
		
		textField_Gender = new JTextField();
		textField_Gender.setFont(new Font("Tahoma", Font.BOLD, 12));
		textField_Gender.setEditable(false);
		textField_Gender.setColumns(10);
		
		btnShow.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String query="select * from profile where username=?";
				try 
				{
					PreparedStatement ps=DBInfo.con.prepareStatement(query);
					ps.setString(1, textField_Username.getText());
					ResultSet res=ps.executeQuery();
					boolean b = res.next();
					if(b)
					{
						textField_Bal.setText(res.getString("balance"));
						textField_CustID.setText(res.getString("coustmerid"));
						textField_Email.setText(res.getString("emailid"));
						textField_Gender.setText(res.getString("gender"));
						textField_Mobile.setText(res.getString("mobilenum"));
						textField_Name.setText(res.getString("name"));
						textField_Usertype.setText(res.getString("usertype"));
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "No such user exists", "User Not Found", JOptionPane.ERROR_MESSAGE);
						textField_Username.setText(null);
					}
						
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMobileNum, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblName, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblEnterUsername, Alignment.LEADING))
								.addComponent(lblCoustmerId, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmailId, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsertype, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBalance, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
							.addGap(33)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnShow, Alignment.LEADING)
								.addComponent(textField_Name, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
								.addComponent(textField_CustID)
								.addComponent(textField_Mobile)
								.addComponent(textField_Email)
								.addComponent(textField_Usertype)
								.addComponent(textField_Bal)
								.addComponent(textField_Gender)
								.addComponent(textField_Username))
							.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label)
									.addGap(11))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnLogout)
									.addContainerGap())))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEnterUsername)
								.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnShow)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblName)
								.addComponent(textField_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCoustmerId)
								.addComponent(textField_CustID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMobileNum)
								.addComponent(textField_Mobile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmailId))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_Usertype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsertype))
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblBalance)
								.addComponent(textField_Bal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblGender)
								.addComponent(textField_Gender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnLogout))
					.addContainerGap(144, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		

	}
}
