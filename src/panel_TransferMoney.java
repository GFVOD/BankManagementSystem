import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

@SuppressWarnings("serial")
public class panel_TransferMoney extends JPanel {
	private JTextField custID;
	private JTextField amount;
	private JTextField BalAfterTransc;
	int balance=0;
	int balanceReceiver=0;
	int amountDeducted=0;
	/**
	 * Create the panel.
	 * 
	 */
	public panel_TransferMoney(String username) {
		
		JLabel lblEnterCustomerid = new JLabel("Receiver's CustomerID :-");
		
		JLabel lblTransactionAmount = new JLabel("Transaction Amount :-");
		BalAfterTransc = new JTextField();
		custID = new JTextField();
		custID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				if(!(Character.isDigit(c)))
				{
					e.consume();
				}
			}
		});
		custID.setHorizontalAlignment(SwingConstants.CENTER);
		custID.setColumns(10);
		
		String tranferBy="select balance from profile where username=?";
		PreparedStatement ps;
		try {
			ps = DBInfo.con.prepareStatement(tranferBy);
			ps.setString(1, username);
			ResultSet res=ps.executeQuery();
			if(res.next())
			{
				balance=Integer.parseInt(res.getString("balance"));
				BalAfterTransc.setText(String.valueOf(balance));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		amount = new JTextField();
		amount.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					amountDeducted=Integer.parseInt(amount.getText());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				BalAfterTransc.setText(String.valueOf(balance-amountDeducted));
			}
		});
		amount.setText("0");
		amount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)))
				{
					evt.consume();
				}
			}
		});
		amount.setHorizontalAlignment(SwingConstants.CENTER);
		amount.setColumns(10);
		
		JLabel lblAfterTransactionAmount = new JLabel("After Transaction Bal:-");
		
		
		BalAfterTransc.setEditable(false);
		BalAfterTransc.setHorizontalAlignment(SwingConstants.CENTER);
		BalAfterTransc.setColumns(10);
		
		JLabel lblPressTab = new JLabel("(Press Tab)");
		lblPressTab.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnTransfer = new JButton("Transfer");
		
		btnTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String transferTo="select name,balance from profile where coustmerid=?";
				try {
					PreparedStatement ps1=DBInfo.con.prepareStatement(transferTo);
					ps1.setString(1, custID.getText());
					ResultSet res1=ps1.executeQuery();
					boolean b=res1.next();
					if(b)
					{
						String receiverName=res1.getString("name");
						balanceReceiver=res1.getInt("balance");
						if(Integer.parseInt(BalAfterTransc.getText())<0)
						{
							JOptionPane.showMessageDialog(getParent(), "Amount Transferred Cannot Be More Than Account Balance", "Not Enough Funds", JOptionPane.ERROR_MESSAGE);
							BalAfterTransc.setText(String.valueOf(balance));
							amount.setText("0");
						}
						else
						{
							int choice=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to transfer the amount?", "Confirmation!!", JOptionPane.YES_NO_OPTION);
							if(choice==JOptionPane.YES_OPTION)
							{
								String q2="insert into transactions values(?,?,?,?)";
								String q1="update profile set balance=? where username=?";
								String q3="update profile set balance=? where coustmerid=?";
								try 
								{
									PreparedStatement p2=DBInfo.con.prepareStatement(q2);
									p2.setString(1, custID.getText());
									p2.setString(2, receiverName);
									p2.setString(3, username);
									p2.setInt(4, amountDeducted);
									p2.executeUpdate();
									
									PreparedStatement p3=DBInfo.con.prepareStatement(q3);
									p3.setInt(1, Integer.parseInt(amount.getText())+balanceReceiver);
									p3.setString(2, custID.getText());
									p3.executeUpdate();
									
									PreparedStatement p1=DBInfo.con.prepareStatement(q1);
									p1.setInt(1, Integer.parseInt(BalAfterTransc.getText()));
									p1.setString(2, username);
									p1.executeUpdate();
									custID.setText("");
									amount.setText("0");
									JOptionPane.showMessageDialog(getParent(), "Money Added Successfully");
								}
								catch (SQLException e1) 
								{
									e1.printStackTrace();
								}
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(getParent(), "Please Check the Customer Id Entered", "No Such Customer Id", JOptionPane.ERROR_MESSAGE);
						custID.setText("");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
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
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(10)
									.addComponent(lblPressTab, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblTransactionAmount, Alignment.LEADING)
											.addComponent(lblEnterCustomerid, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(custID, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
											.addComponent(amount, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
									.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
										.addComponent(lblAfterTransactionAmount, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(BalAfterTransc, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(118)
							.addComponent(btnTransfer)))
					.addContainerGap(68, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTransactionAmount)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(custID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEnterCustomerid))
							.addGap(18)
							.addComponent(amount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPressTab)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAfterTransactionAmount)
						.addComponent(BalAfterTransc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addComponent(btnTransfer)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
