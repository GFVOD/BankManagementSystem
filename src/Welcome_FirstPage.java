import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Welcome_FirstPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 Welcome_FirstPage frame = new Welcome_FirstPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public  Welcome_FirstPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 255);
		contentPane = new JPanel();
		setUndecorated(true);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				LoginSignup_SecondFrame ls=new LoginSignup_SecondFrame();
				ls.setVisible(true);
				dispose();
			}
		});
		setLocationRelativeTo(this);
		contentPane.setBackground(new Color(250, 235, 215));
		setContentPane(contentPane);
		
		ImageIcon image=new ImageIcon("image.jpg");
		JLabel lblNewLabel = new JLabel(image);
		
		
		JLabel lblWelcomeToYourbank = new JLabel("Welcome To YourBank");
		lblWelcomeToYourbank.setForeground(new Color(0, 0, 0));
		lblWelcomeToYourbank.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToYourbank.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28));
		
		JLabel lblWhereYouAre = new JLabel("Where you are the priority...");
		lblWhereYouAre.setFont(new Font("Times New Roman", Font.ITALIC, 15));
		
		JLabel lblClickToContinue = new JLabel("click to continue..");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(180)
					.addComponent(lblNewLabel)
					.addContainerGap(270, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(78)
					.addComponent(lblWelcomeToYourbank, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(99, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(210, Short.MAX_VALUE)
					.addComponent(lblWhereYouAre)
					.addGap(61))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(314, Short.MAX_VALUE)
					.addComponent(lblClickToContinue)
					.addGap(51))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblWelcomeToYourbank, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblWhereYouAre)
					.addGap(45)
					.addComponent(lblClickToContinue)
					.addGap(105))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
