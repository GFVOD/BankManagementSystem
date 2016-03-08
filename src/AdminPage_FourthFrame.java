import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class AdminPage_FourthFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AdminPage_FourthFrame(String username) throws SQLException {
		setResizable(false);
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 575);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setLayout(new GridLayout(5, 1));
		toolBar.setEnabled(false);
		toolBar.setOrientation(SwingConstants.VERTICAL);
		splitPane.setLeftComponent(toolBar);
		ImageIcon delete=new ImageIcon("Delete_Icon.png");
		ImageIcon bank=new ImageIcon("image.jpg");
		ImageIcon users=new ImageIcon("User.jpg");
		ImageIcon money=new ImageIcon("money.jpg");
		ImageIcon userdetails=new ImageIcon("userdetails.jpg");
		
		ImageIcon male=new ImageIcon("Maleicon.jpg");
		ImageIcon female=new ImageIcon("Femaleicon.jpg");
		ImageIcon transactions=new ImageIcon("Transactions.png");
		JLabel lblgender=new JLabel("");
		JPanel panel_Info = new panel_Info(username,this);
		JPanel panel_Money = new panel_Money(username,this);
		JPanel panel_UserDetails = new panel_UserDetails(username,this);
		JPanel panel_Delete = new panel_Delete(username,this);
		JPanel transactionsAdmin = new TransactionsAdmin(username,this);
		JButton btnDeleteUser = new JButton(delete);
		JPanel panel = new JPanel();
		JButton btnUserDetails = new JButton(users);
		JLabel lblWelcome = new JLabel("");
		JLabel lblBankicon = new JLabel(bank);
		lblBankicon.setText("");
		JButton btnYourDetails = new JButton(userdetails);
		JButton btnMoney = new JButton(money);
		JButton btnTransactions = new JButton(transactions);
		
		splitPane.setRightComponent(panel);
	
		String query="select * from profile where username = ? ";
		PreparedStatement ps=DBInfo.con.prepareStatement(query);
			ps.setString(1, username);
			ResultSet res=ps.executeQuery();
		
		while(res.next())
		{
			if((res.getString("gender")).equalsIgnoreCase("male"))
				lblgender=new JLabel(male);
			else
				lblgender=new JLabel(female);
			lblWelcome = new JLabel("WELCOME, "+res.getString("name").toUpperCase()+"!!");
		}
		ps.close();
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int p=JOptionPane.showConfirmDialog(getParent(), "are you sure you want to logout??", "Logout", JOptionPane.YES_NO_OPTION);
				if(p==JOptionPane.YES_OPTION)
				{
					(new LoginSignup_SecondFrame()).setVisible(true);
					dispose();
				}
			}
		});
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(658, Short.MAX_VALUE)
					.addComponent(lblBankicon)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblgender)
					.addContainerGap(658, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblWelcome)
					.addContainerGap(658, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(593, Short.MAX_VALUE)
					.addComponent(btnLogout)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblgender)
						.addComponent(lblBankicon))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLogout)
						.addComponent(lblWelcome))
					.addContainerGap(484, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

		
		btnYourDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				splitPane.setRightComponent(panel.add(panel_Info));				
			}
		});
		toolBar.add(btnYourDetails);
		
		btnMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				splitPane.setRightComponent(panel_Money);				
			}
		});
		toolBar.add(btnMoney);
		
		
		btnUserDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				splitPane.setRightComponent(panel_UserDetails);				
			}
		});
		
		
		btnTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitPane.setRightComponent(transactionsAdmin);	
			}
		});
		toolBar.add(btnTransactions);
		
		toolBar.add(btnUserDetails);
		
		
		
		
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				splitPane.setRightComponent(panel_Delete);				
			}
		});
		toolBar.add(btnDeleteUser);
		}
}
