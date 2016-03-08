import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class panel_IndiBal extends JPanel {
	private JTextField textField_Username;
	private JTextField textField_bal;

	/**
	 * Create the panel.
	 */
	public panel_IndiBal() {
		
		JLabel lblEnterUsername = new JLabel("Enter Username :-");
		
		textField_Username = new JTextField();
		textField_Username.setColumns(10);
		textField_bal = new JTextField();
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String query="select * from profile where username = ?";
				try 
				{
					PreparedStatement ps=DBInfo.con.prepareStatement(query);
					ps.setString(1, textField_Username.getText());
					ResultSet res=ps.executeQuery();
					boolean b=res.next();
					if(b)
					{
						String bal=res.getString("balance");
						textField_bal.setText(bal);
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "No such user exists", "User Not Found", JOptionPane.ERROR_MESSAGE);
						textField_Username.setText(null);
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		
		textField_bal.setEditable(false);
		textField_bal.setColumns(10);
		
		JLabel lblAmountInRs = new JLabel("Amount in Rs. :-");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(54, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblAmountInRs, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblEnterUsername, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnShow)
						.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_bal, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
					.addGap(114))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEnterUsername))
					.addGap(18)
					.addComponent(btnShow)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAmountInRs)
						.addComponent(textField_bal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(150, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
