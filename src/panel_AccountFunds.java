import javax.swing.JPanel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class panel_AccountFunds extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public panel_AccountFunds(String username) {
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.BOLD, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="select * from profile where username = ?";
				try 
				{
					PreparedStatement ps=DBInfo.con.prepareStatement(query);
					ps.setString(1, username);
					ResultSet res=ps.executeQuery();
					boolean b=res.next();
					if(b)
					{
						String bal=res.getString("balance");
						textField.setText(bal);
					}
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}	
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(122)
							.addComponent(btnRefresh)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(71)
					.addComponent(btnRefresh)
					.addGap(18)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(162, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		String query="select * from profile where username = ?";
		try 
		{
			PreparedStatement ps=DBInfo.con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet res=ps.executeQuery();
			boolean b=res.next();
			if(b)
			{
				String bal=res.getString("balance");
				textField.setText(bal);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
