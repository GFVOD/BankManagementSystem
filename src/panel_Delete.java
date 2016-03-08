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

@SuppressWarnings("serial")
public class panel_Delete extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public panel_Delete(String username,AdminPage_FourthFrame admin) throws SQLException {
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
		
		JLabel lblEnterUsername = new JLabel("Enter Username :");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String user=textField.getText();
				String query="delete from profile where username=?";
				String query2="select username from profile where username=?";
				
				if(user.equals(username))
				{
					int n=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to delete your own account??", "Confirm", JOptionPane.WARNING_MESSAGE);
					if(n==JOptionPane.OK_OPTION)
					{
						try 
						{
							PreparedStatement ps=DBInfo.con.prepareStatement(query);
							ps.setString(1,user);
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
					try 
					{
						PreparedStatement ps1=DBInfo.con.prepareStatement(query2);
						ps1.setString(1, user);
						ResultSet res=ps1.executeQuery();
						PreparedStatement ps=DBInfo.con.prepareStatement(query);
						ps.setString(1,user);
						if(res.next())
						{
							int n=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to delete this account??", "Confirm", JOptionPane.WARNING_MESSAGE);
							if(n==JOptionPane.OK_OPTION)
							{
								ps.executeUpdate();
								admin.dispose();
								new LoginSignup_SecondFrame().setVisible(true);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(getParent(), "No such user exists!!");
							textField.setText(null);
						}
						
					}
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
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
							.addGap(52)
							.addComponent(lblEnterUsername)
							.addGap(18)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
							.addComponent(btnLogout))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(183)
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
					.addGap(18)
					.addComponent(btnDelete)
					.addContainerGap(208, Short.MAX_VALUE))
		);
		
		setLayout(groupLayout);
		
		

	}
}
