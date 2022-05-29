package thuNghiem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dangNhapAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField txtAdName;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dangNhapAdmin frame = new dangNhapAdmin();
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
	
		
	
	public dangNhapAdmin() {
		setTitle("\u0110\u0103ng nh\u1EADp Admin");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 528, 553);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(" Tên đăng nhập :");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/google-panda-circular-symbol (2).png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(20, 242, 180, 50);
		contentPane.add(lblNewLabel);
		
		txtAdName = new JTextField();
		txtAdName.setBorder(new LineBorder(new Color(169, 169, 169)));
		txtAdName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtAdName.setBounds(199, 246, 260, 45);
		contentPane.add(txtAdName);
		txtAdName.setColumns(10);
		
		JLabel lblMtKhu = new JLabel(" Mật khẩu :");
		lblMtKhu.setIcon(new ImageIcon(getClass().getResource("/image/door-key (1).png")));
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMtKhu.setBounds(20, 324, 151, 50);
		contentPane.add(lblMtKhu);
		

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(248, 307, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JButton btnLogin = new JButton("\u0110\u0103ng nh\u1EADp");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String AdName= txtAdName.getText();			//láº¥y giĂ¡ trá»‹ cá»§a tĂªn admin
				String Password = new String( txtPassword.getText()); // láº¥y giĂ¡ trá»‹ cá»§a pass
				
				if(txtAdName.getText().equals("")|txtPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(lblNewLabel_2, "Các ô không được để trống");
				}
			
				else {
				Connection conn = null; // khai bĂ¡o connection
				
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUAN_NET;user=sa;password=Son862001");
				    
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				PreparedStatement pstm1;	
				try {
					pstm1 = conn.prepareStatement("Select AdName,Passwords from AcountAdmin where AdName=? and Passwords=?");
					
					pstm1.setString(1,AdName);
					pstm1.setString(2,Password);
					
					ResultSet rs = pstm1.executeQuery();
					
					if(rs.next()) {
						dispose();
					adminPane ah = new adminPane(AdName);
					
						ah.setVisible(true);
						
					}
				
					else {
						JOptionPane.showMessageDialog(lblNewLabel_2, "Tài khoản hoặc mật khẩu sai");
					}
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
				
			}
		});
		btnLogin.setBackground(new Color(255, 160, 122));
		btnLogin.setBorder(new LineBorder(new Color(233, 150, 122), 4, true));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLogin.setBounds(199, 416, 120, 40);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Tho\u00E1t");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Home thoat = new Home();
				thoat.setVisible(true);
			}
		});
		btnExit.setBackground(new Color(192, 192, 192));
		btnExit.setBorder(new LineBorder(new Color(169, 169, 169), 3));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnExit.setBounds(352, 416, 107, 40);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/image/panda.png")));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(132, 0, 257, 257);
		contentPane.add(lblNewLabel_1);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtPassword.setBorder(new LineBorder(new Color(169, 169, 169)));
		txtPassword.setBounds(199, 330, 260, 45);
		contentPane.add(txtPassword);
		
	}
}
