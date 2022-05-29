package thuNghiem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dangNhapNguoiChoi extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dangNhapNguoiChoi frame = new dangNhapNguoiChoi();
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
	public dangNhapNguoiChoi() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setTitle("\u0110\u0103ng nh\u1EADp ng\u01B0\u1EDDi ch\u01A1i");
		setBounds(100, 100, 512, 553);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210,220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBorder(new LineBorder(new Color(169, 169, 169)));
		textField.setBounds(196, 254, 250, 45);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(" Tên đăng nhập :");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/profile-user (1).png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(22, 251, 181, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel(" Mật khẩu :");
		lblMtKhu.setIcon(new ImageIcon(getClass().getResource("/image/door-key (1).png")));
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMtKhu.setBounds(22, 338, 150, 50);
		contentPane.add(lblMtKhu);
		

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(288, 309, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		passwordField.setBorder(new LineBorder(new Color(169, 169, 169)));
		passwordField.setBounds(196, 341, 250, 45);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/image/pig (1).png")));
		lblNewLabel_1.setBounds(135, 0, 257, 257);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\u0110\u0103ng nh\u1EADp");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String MbName= textField.getText();			//láº¥y giĂ¡ trá»‹ cá»§a tĂªn admin
				String Password = new String( passwordField.getText()); // láº¥y giĂ¡ trá»‹ cá»§a pass
				Connection conn = null; // khai bĂ¡o connection
				if(MbName.equals("")|Password.equals("")) {
					JOptionPane.showMessageDialog(lblNewLabel_2, "Các ô không được để trống");
				}
				else {
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUAN_NET;user=sa;password=Son862001");
				    
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				PreparedStatement pstm1;	
				try {
					pstm1 = conn.prepareStatement("Select MbName,Passwords from AcountMember where MbName=? and Passwords=?");
					
					pstm1.setString(1, MbName);
					pstm1.setString(2,Password);
					
					ResultSet rs = pstm1.executeQuery();
					
					if(rs.next()) {
						dispose();
					HTnguoiChoi ah=new HTnguoiChoi(MbName);
					
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
		btnNewButton.setBorder(new LineBorder(new Color(255, 130, 122), 3));
		btnNewButton.setBackground(new Color(255, 160, 122));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(188, 413, 120, 40);
		contentPane.add(btnNewButton);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				Home thoat = new Home();
				thoat.setVisible(true);
			}
		});
		btnThot.setBorder(new LineBorder(new Color(169, 169, 169), 3));
		btnThot.setBackground(new Color(192, 192, 192));
		btnThot.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnThot.setBounds(336, 413, 110, 40);
		contentPane.add(btnThot);
		
	}

}
