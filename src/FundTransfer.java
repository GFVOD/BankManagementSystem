import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class FundTransfer extends JPanel {

	/**
	 * Create the panel.
	 */
	public FundTransfer(String username,UserPage_FourthFrame user) {
		
		JButton button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int p=JOptionPane.showConfirmDialog(getParent(), "are you sure you want to logout??", "Logout", JOptionPane.YES_NO_OPTION);
				if(p==JOptionPane.YES_OPTION)
				{
					(new LoginSignup_SecondFrame()).setVisible(true);
					user.dispose();
				}
			}
		});
		
		panel_AccountFunds panel_AccountFunds_ = new panel_AccountFunds(username);
		ImageIcon bank=new ImageIcon("image.jpg");
		JLabel label = new JLabel(bank);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add("Transfer Funds", new panel_TransferMoney(username));
		tabbedPane.add("Show My Account Balance", panel_AccountFunds_);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap(507, Short.MAX_VALUE)
							.addComponent(label)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 321, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(button)))
					.addContainerGap(44, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}
}
