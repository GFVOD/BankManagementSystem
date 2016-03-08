import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class SignUpPage_ThirdFrame extends JFrame {

	private JPanel contentPane_Signup;
	private JTextField textField_Name;
	private JTextField textField_MobileNum;
	private JTextField textField_Email;
	private JTextField textField_Username;
	private JPasswordField passwordField;
	private JPasswordField passwordField_Reenter;
	private JTextField textField_CustomerID;

	/**
	 * Create the frame.
	 */
	public SignUpPage_ThirdFrame() {
		setResizable(false);
		setTitle("SignUp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 617);
		setLocationRelativeTo(this);
		contentPane_Signup = new JPanel();
		contentPane_Signup.setBackground(new Color(240, 240, 240));
		contentPane_Signup.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_Signup);
		
		ImageIcon image=new ImageIcon("image.jpg");
		JLabel ImageLabel = new JLabel(image);
		
		JLabel lblName = new JLabel("Name * :-");
		
		JLabel lblMobileNumber = new JLabel("Mobile Number * :-");
		
		JLabel lblUsername = new JLabel("Username * :-");
		
		JLabel lblPassword = new JLabel("Password * :-");
		
		JLabel lblReenterPassword = new JLabel("Re-enter Password * :-");
		
		JLabel lblUserType = new JLabel("User Type * :-");
		
		JLabel lblEmailId = new JLabel("Email ID * :-");
		
		textField_Name = new JTextField();
		textField_Name.setColumns(10);
		
		textField_MobileNum = new JTextField();
		textField_MobileNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char c=evt.getKeyChar();
				if(!(Character.isDigit(c)))
				{
					evt.consume();
				}
			}
		});
		textField_MobileNum.setColumns(10);
		
		textField_Email = new JTextField();
		textField_Email.setColumns(10);
		
		textField_Username = new JTextField();
		textField_Username.setColumns(10);
		
		passwordField = new JPasswordField();
		
		passwordField_Reenter = new JPasswordField();
		JLabel lblCustomerId = new JLabel("Customer ID * :-");

		JLabel lblGender = new JLabel("Gender * :-");
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setSelected(true);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		ButtonGroup bg=new ButtonGroup();
		bg.add(rdbtnMale);
		bg.add(rdbtnFemale);
		

		JRadioButton rdbtnUser = new JRadioButton("User");
		rdbtnUser.setSelected(true);
		
		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		ButtonGroup bg1=new ButtonGroup();
		bg1.add(rdbtnUser);
		bg1.add(rdbtnAdmin);
		
		String customerid="";
		for(int i=0;i<9;i++)
		{
			customerid+=(int)(Math.random()*9)+1;
		}
		
		textField_CustomerID = new JTextField(customerid);
		textField_CustomerID.setEditable(false);
		textField_CustomerID.setColumns(10);
		JButton btnSignup = new JButton("SignUp");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int flag=1;
				String query1="select username from profile";
				PreparedStatement ps1;
				try 
				{
					ps1 = DBInfo.con.prepareStatement(query1);

					ResultSet res=ps1.executeQuery();
					while(res.next())
					{
						String user=res.getString("username");
						if(user.equals(textField_Username.getText()))
								flag=0;
							
					}
				} 
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
				if(textField_Email.getText().equals("")||textField_MobileNum.getText().equals("")||textField_Name.getText().equals("")
						||textField_Username.getText().equals("")||passwordField.getPassword().toString().equals("")
						||passwordField_Reenter.getPassword().toString().equals(""))
					{
						JOptionPane.showMessageDialog(getParent(), "Fill Mandatory Fields", "Empty Fileds", JOptionPane.ERROR_MESSAGE);
					}
				else
				{
					if(flag==1)
					{
						if(String.copyValueOf(passwordField.getPassword()).equals(String.copyValueOf(passwordField_Reenter.getPassword())))
							{
								int option=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to signup	?","Confirmation", JOptionPane.YES_NO_OPTION);
								if(option==JOptionPane.YES_OPTION)
								{
									String query="insert into profile values(?,?,?,?,?,?,?,?,?)";
									try
									{
										String id=textField_CustomerID.getText();
										PreparedStatement ps=DBInfo.con.prepareStatement(query);
										int bal=0;
										ps.setString(1, id);
										ps.setString(2, textField_Name.getText());
										ps.setString(3, textField_MobileNum.getText());
										ps.setString(4, textField_Email.getText());
										ps.setString(5, textField_Username.getText());
										ps.setString(6, String.copyValueOf(passwordField.getPassword()));
										ps.setInt(9, bal);
										
										if(rdbtnUser.isSelected())
											ps.setString(7, rdbtnUser.getText());
										else
											ps.setString(7, rdbtnAdmin.getText());
										
										if(rdbtnMale.isSelected())
											ps.setString(8, rdbtnMale.getText());
										else
											ps.setString(8, rdbtnFemale.getText());
										ps.executeUpdate();
										ps.close();
									} catch (SQLException e) 
									{					
										e.printStackTrace();
									}
									textField_Email.setText(null);
									textField_MobileNum.setText(null);
									textField_Name.setText(null);
									textField_Username.setText(null);
									passwordField.setText(null);
									passwordField_Reenter.setText(null);
									textField_CustomerID.setText(null);
									
									JOptionPane.showMessageDialog(getParent(), "Successfully Signed Up!!");						
									dispose();
									(new LoginSignup_SecondFrame()).setVisible(true);
								}
								
							}
								
							else
								{
									JOptionPane.showMessageDialog(getParent(), "Re-enter Password Correctly!!", "Password Incorrect", JOptionPane.ERROR_MESSAGE);
									passwordField.setText(null);
									passwordField_Reenter.setText(null);
								}
								
					}
						
					else 
					{
						JOptionPane.showMessageDialog(getParent(), "Username already Exists", "Re-Enter Username", JOptionPane.ERROR_MESSAGE);
						textField_Username.setText(null);
					}
						
					}
				}
				
				
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				(new LoginSignup_SecondFrame()).setVisible(true);
				dispose();
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int p=JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to Exit??", "Confirm", JOptionPane.YES_NO_OPTION);
				if(p==JOptionPane.YES_OPTION)
				{

					System.exit(0);
				}
			}
		});
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_Email.setText(null);
				textField_MobileNum.setText(null);
				textField_Name.setText(null);
				textField_Username.setText(null);
				passwordField.setText(null);
				passwordField_Reenter.setText(null);
			}
		});
		
		JLabel lblAll = new JLabel("All ( * ) Marked Fileds are Mandatory ");
		
		GroupLayout gl_contentPane_Signup = new GroupLayout(contentPane_Signup);
		gl_contentPane_Signup.setHorizontalGroup(
			gl_contentPane_Signup.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane_Signup.createSequentialGroup()
					.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane_Signup.createSequentialGroup()
							.addGap(45)
							.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane_Signup.createSequentialGroup()
									.addGap(123)
									.addComponent(ImageLabel))
								.addGroup(gl_contentPane_Signup.createSequentialGroup()
									.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.LEADING)
										.addComponent(lblGender, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnSignup)
										.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.LEADING, false)
											.addComponent(lblMobileNumber, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
											.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblReenterPassword, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
											.addComponent(lblEmailId, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
											.addComponent(lblUsername, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblUserType, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblName)
											.addComponent(lblCustomerId)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.LEADING, false)
											.addComponent(textField_CustomerID, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
											.addComponent(passwordField_Reenter, 197, 197, 197)
											.addComponent(textField_Username, 197, 197, 197)
											.addComponent(textField_Email, 197, 197, 197)
											.addComponent(textField_MobileNum, 197, 197, 197)
											.addComponent(passwordField, 197, 197, 197)
											.addComponent(textField_Name, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane_Signup.createSequentialGroup()
													.addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
													.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_contentPane_Signup.createSequentialGroup()
													.addComponent(rdbtnMale)
													.addGap(18)
													.addComponent(rdbtnFemale)
													.addPreferredGap(ComponentPlacement.RELATED, 90, Short.MAX_VALUE))))
										.addGroup(gl_contentPane_Signup.createSequentialGroup()
											.addComponent(rdbtnUser, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(rdbtnAdmin, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))))))
						.addGroup(gl_contentPane_Signup.createSequentialGroup()
							.addGap(52)
							.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAll)
								.addComponent(btnExit))))
					.addContainerGap(65, Short.MAX_VALUE))
		);
		gl_contentPane_Signup.setVerticalGroup(
			gl_contentPane_Signup.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane_Signup.createSequentialGroup()
					.addContainerGap()
					.addComponent(ImageLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomerId)
						.addComponent(textField_CustomerID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(textField_Name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMobileNumber)
						.addComponent(textField_MobileNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmailId)
						.addComponent(textField_Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(textField_Username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblReenterPassword)
						.addComponent(passwordField_Reenter, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserType)
						.addComponent(rdbtnUser)
						.addComponent(rdbtnAdmin))
					.addGap(20)
					.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGender)
						.addComponent(rdbtnMale)
						.addComponent(rdbtnFemale))
					.addGap(29)
					.addGroup(gl_contentPane_Signup.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnReset)
						.addComponent(btnSignup)
						.addComponent(btnBack))
					.addGap(18)
					.addComponent(btnExit)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAll)
					.addGap(98))
		);
		contentPane_Signup.setLayout(gl_contentPane_Signup);
	}
}
