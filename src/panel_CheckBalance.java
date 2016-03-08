import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class panel_CheckBalance extends JPanel {

	/**
	 * Create the panel.
	 */
	public panel_CheckBalance() {
		
		JLabel lblSelect = new JLabel("Select an option :-");
		JRadioButton rdbtnIndividualUsersBalance = new JRadioButton("Individual User's Balance");
		
		JRadioButton rdbtnTotalBankBalance = new JRadioButton("Total Bank Balance");
		ButtonGroup bg=new ButtonGroup();
		bg.add(rdbtnIndividualUsersBalance);
		bg.add(rdbtnTotalBankBalance);
		
		JPanel panel_add = new JPanel();
		
		rdbtnIndividualUsersBalance.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				panel_add.add(new panel_IndiBal());
				panel_add.revalidate();
			}
		});
		
		rdbtnTotalBankBalance.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				panel_add.add(new panel_TotalBal());
				panel_add.revalidate();
			}
		});
		
		JButton btnRefresh = new JButton("Clear / Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_add.add(new JPanel());
				panel_add.revalidate();
				revalidate();
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addComponent(rdbtnIndividualUsersBalance)
							.addGap(41)
							.addComponent(rdbtnTotalBankBalance))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_add, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(140)
							.addComponent(lblSelect, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(147)
							.addComponent(btnRefresh)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(lblSelect)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnIndividualUsersBalance)
						.addComponent(rdbtnTotalBankBalance))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRefresh)
					.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
					.addComponent(panel_add, GroupLayout.PREFERRED_SIZE, 213, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		panel_add.setLayout(new BorderLayout(0, 0));
		setLayout(groupLayout);

	}
}
