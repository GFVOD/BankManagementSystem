import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LoginPage_ThirdFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField_Username;

	/**
	 * Create the frame.
	 */
	public LoginPage_ThirdFrame() {
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 314);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel label = new JLabel("Enter Username");
		
		JLabel label_1 = new JLabel("Enter Password");
		
		passwordField = new JPasswordField();
		
		textField_Username = new JTextField();
		textField_Username.setColumns(10);
		
		JLabel label_2 = new JLabel("New User? ");
		
		JButton button_Signup = new JButton("Signup");
		button_Signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				(new SignUpPage_ThirdFrame()).setVisible(true); 
				dispose();
			}
		});
		
		JButton button_Login = new JButton("Login");
		button_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String user=textField_Username.getText();
				String pass=String.copyValueOf(passwordField.getPassword());
				String usertype="";
				String query="select * from profile";
				try 
				{
					PreparedStatement ps=DBInfo.con.prepareStatement(query);
					
					ResultSet res=ps.executeQuery();
					int flag=0;
					String username;
					while(res.next())
					{
						 usertype=res.getString("usertype");
						 username=res.getString("username");
						String password=res.getString("password");
						if(user.equals(username) && pass.equals(password) && usertype.equalsIgnoreCase("admin"))
						{
							flag=1;
							AdminPage_FourthFrame ap=new AdminPage_FourthFrame(user);
							dispose();
							ap.setVisible(true);
						}
						else if(user.equals(username) && pass.equals(password) && usertype.equalsIgnoreCase("user"))
						{
							flag=1;
							UserPage_FourthFrame up=new UserPage_FourthFrame(user);
							dispose();
							up.setVisible(true);
						}
						
					}
					if(flag==0)
					{
						JOptionPane.showMessageDialog(getParent(),"user does not exist","error",JOptionPane.ERROR_MESSAGE);
						textField_Username.setText(null);
						passwordField.setText(null);
					}
					ps.close();					
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int p=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to Exit??", "Confirm", JOptionPane.YES_NO_OPTION);
				if(p==JOptionPane.YES_OPTION)
				{

					System.exit(0);
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(180)
					.addComponent(button_Login)
					.addContainerGap(189, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnExit)
							.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
							.addComponent(label_2)
							.addGap(18)
							.addComponent(button_Signup)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textField_Username, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
							.addContainerGap(111, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(passwordField, 165, 165, 165)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(button_Login)
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(button_Signup)
						.addComponent(btnExit))
					.addGap(24))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
