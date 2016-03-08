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
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class panel_UserDelete extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public panel_UserDelete(String username,UserPage_FourthFrame admin) throws SQLException {
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
		
		JLabel lblEnterUsername = new JLabel("Enter Your Password :");
		lblEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String pass=textField.getText();
				String query="delete from profile where username=?";
				String query2="select password from profile where username=?";
				try 
				{
					String password = "";
					PreparedStatement ps1=DBInfo.con.prepareStatement(query2);
					ps1.setString(1, username);
					ResultSet res=ps1.executeQuery();
					while(res.next())
					{
						password=res.getString(1);
					}
					if(pass.equals(password))
					{

						int n=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to delete your own account??", "Confirm", JOptionPane.WARNING_MESSAGE);
						if(n==JOptionPane.OK_OPTION)
						{
							try 
							{
								PreparedStatement ps=DBInfo.con.prepareStatement(query);
								ps.setString(1,username);
								ps.executeUpdate();
								admin.dispose();
								new LoginSignup_SecondFrame().setVisible(true);
							}
							catch (SQLException e)
							{
								e.printStackTrace();
							}
							
						}
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Password Incorrect!!");
						textField.setText(null);
					}
					
				}
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(441, Short.MAX_VALUE)
							.addComponent(label))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(34)
							.addComponent(lblEnterUsername, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addComponent(btnLogout))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(155)
							.addComponent(btnDelete)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(label)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogout)
						.addComponent(lblEnterUsername)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDelete)
					.addContainerGap(215, Short.MAX_VALUE))
		);
		
		setLayout(groupLayout);
		
		

	}
}
