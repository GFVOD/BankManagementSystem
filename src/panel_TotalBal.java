import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class panel_TotalBal extends JPanel {
	private JTextField textField_BankBal;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public panel_TotalBal() {
		
		textField_BankBal = new JTextField();
		textField_BankBal.setHorizontalAlignment(SwingConstants.CENTER);
		textField_BankBal.setEditable(false);
		textField_BankBal.setFont(new Font("Tahoma", Font.BOLD, 13));
		textField_BankBal.setColumns(10);
		int sum=0,i;
		
		String query="select balance from profile";
		PreparedStatement ps;
		try 
		{
			ps = DBInfo.con.prepareStatement(query);
			ResultSet res=ps.executeQuery();
			while(res.next())
			{
				i=res.getInt("balance");
				sum=sum+i;
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		textField_BankBal.setText(String.valueOf(sum));
		
		JLabel lblTotalBankBalance = new JLabel("Total Bank Balance in Indian Rupees");
		lblTotalBankBalance.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotalBankBalance.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(47)
							.addComponent(textField_BankBal, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(86)
							.addComponent(lblTotalBankBalance, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addComponent(lblTotalBankBalance)
					.addGap(48)
					.addComponent(textField_BankBal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(157, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
