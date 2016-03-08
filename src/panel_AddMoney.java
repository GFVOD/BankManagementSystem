import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JSeparator;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class panel_AddMoney extends JPanel {
	private JTextField textField_AddBal;
	private JTextField textField_CurrBal;
	private JTextField textField_Username;
	private JTextField textField_FinalBal;
	int bal;
	int n,addBal,finalBal;
	String user;

	/**
	 * Create the panel.
	 */
	public panel_AddMoney(String username) {
		JLabel lblPreviousBalance = new JLabel("Current Balance :-");
		
		textField_AddBal = new JTextField();
		textField_AddBal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)))
				{
					evt.consume();
				}
			}
		});
		
		textField_AddBal.setColumns(10);
		
		textField_CurrBal = new JTextField();
		textField_CurrBal.setEditable(false);
		textField_CurrBal.setColumns(10);
		
		textField_Username = new JTextField();
		
		textField_Username.setColumns(10);
		JLabel lblFinalBalance = new JLabel("Final Balance :-");
		
		textField_FinalBal = new JTextField();
		textField_FinalBal.setEditable(false);
		textField_FinalBal.setColumns(10);
		
		JLabel lblToAdd = new JLabel("/* To Add Money To User Account */");
		lblToAdd.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblEnterUsername = new JLabel("Enter Username :-");
		
		JLabel lblEnterBalanceAmount = new JLabel("Enter Amount Value To Add :-");
		
		JButton btnUpdate = new JButton("Update");
		
		textField_Username.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) 
			{
				String query="select * from profile where username = ? ";
				try
				{
					user=textField_Username.getText();
					PreparedStatement ps=DBInfo.con.prepareStatement(query);
					ps.setString(1, user);
					ResultSet res=ps.executeQuery();
					boolean b=res.next();
					if(b)
					{
						bal=res.getInt("balance");
						String balance=res.getString("balance");
						textField_CurrBal.setText(balance);
					}
					if(!b)
					{
						if(!textField_Username.getText().equals(""))
							JOptionPane.showMessageDialog(getParent(), "No such user exists", "User Not Found", JOptionPane.ERROR_MESSAGE);
					}
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		});
		
		textField_AddBal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) 
			{
				try {

					addBal=Integer.parseInt(textField_AddBal.getText());
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				finalBal=bal+addBal;
				textField_FinalBal.setText(String.valueOf(finalBal));
			}
		});
		
		btnUpdate.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(textField_AddBal.getText().equals("")||textField_Username.getText().equals(""))
					JOptionPane.showMessageDialog(getParent(), "Fill Mandatory Fields", "Empty Fileds", JOptionPane.ERROR_MESSAGE);
				else if(textField_Username.getText().equals(username))
				{
					JOptionPane.showMessageDialog(getParent(), "You Cannot Add Money To Your Own Account", "Permission Denied"
							, JOptionPane.ERROR_MESSAGE);
					textField_Username.setText(null);
					textField_CurrBal.setText(null);
					textField_FinalBal.setText(null);
					textField_AddBal.setText(null);					
				}
				else
					{
						int opt=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to add money to this account??", "Confirm!", JOptionPane.YES_NO_OPTION);
						if(opt==JOptionPane.YES_OPTION)
						{
							String query1="update profile set balance=? where username=?";
							try 
							{
								PreparedStatement ps1=DBInfo.con.prepareStatement(query1);
								ps1.setInt(1, finalBal);
								ps1.setString(2, user);
								ps1.executeUpdate();
								textField_AddBal.setText("");
								textField_CurrBal.setText("");
								textField_FinalBal.setText("");
								textField_Username.setText("");
								JOptionPane.showMessageDialog(getParent(), "Money Added Successfully");
							}
							catch (SQLException e) 
							{
								e.printStackTrace();
							}
							
						}
					}
				
			}
		});
		
		JLabel lblPressEnter = new JLabel("( Press Tab)");
		
		JSeparator separator = new JSeparator();
		
		JLabel label = new JLabel("( Press Tab)");
		
		JLabel lblEnterNegative = new JLabel("Note:- Enter negative value to deduct money");
		lblEnterNegative.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterNegative.setFont(new Font("Tahoma", Font.PLAIN, 13));
		

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(179)
							.addComponent(btnUpdate))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 421, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(90)
							.addComponent(lblToAdd, GroupLayout.PREFERRED_SIZE, 255, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(42)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblFinalBalance, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEnterBalanceAmount, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
										.addComponent(label, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(36)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEnterUsername, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPreviousBalance)
										.addComponent(lblPressEnter))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_FinalBal, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_AddBal, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField_CurrBal, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addComponent(lblEnterNegative, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(53))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblToAdd)
					.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
					.addComponent(lblEnterNegative, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterUsername)
						.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addComponent(lblPressEnter)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_CurrBal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPreviousBalance))
					.addGap(18)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_FinalBal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEnterBalanceAmount)
								.addComponent(textField_AddBal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label)
							.addGap(18)
							.addComponent(lblFinalBalance)))
					.addGap(18)
					.addComponent(btnUpdate)
					.addGap(62))
		);
		setLayout(groupLayout);

	}
}
