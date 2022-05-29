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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import java.awt.Dimension;

public class DKtaiKhoanNguoiChoi extends JFrame {
	
	

	private JPanel contentPane;
	private JButton btnSingup;
	private JTextField textField;
	private JLabel lblTenDK_2;
	private JButton btnExit;
	private JLabel lblSTinNp;
	private JTextField textField_3;
	private JLabel lblNewLabel;
	private JPasswordField passwordFieldDk;
	private JPasswordField passwordFieldf;
	private JLabel lblNewLabel_1;
	private JCheckBox CBreturn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DKtaiKhoanNguoiChoi frame = new DKtaiKhoanNguoiChoi();
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
	public DKtaiKhoanNguoiChoi() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\u0110\u0103ng k\u00FD t\u00E0i kho\u1EA3n ng\u01B0\u1EDDi ch\u01A1i");
		setBounds(100, 100, 555, 643);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtAdNameDK = new JLabel("T\u00EAn \u0111\u0103ng nh\u1EADp :");
		txtAdNameDK.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtAdNameDK.setBounds(62, 202, 200, 50);
		contentPane.add(txtAdNameDK);
		
		JLabel lblTenDK_1 = new JLabel("M\u1EADt kh\u1EA9u :");
		lblTenDK_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTenDK_1.setBounds(105, 268, 200, 50);
		contentPane.add(lblTenDK_1);
		

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(315, 317, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		btnSingup = new JButton("\u0110\u0103ng k\u00FD");
		
		btnSingup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int x=0;
					String addName = textField.getText();
					String addPassWord = new String( passwordFieldDk.getText());
					String confimPassWord = new String( passwordFieldf.getText());
					String tien = textField_3.getText();
					
					int t = Integer.parseInt(tien);
					
					if(String.valueOf(t).equals("")|addName.equals("")|addPassWord.equals("")|confimPassWord.equals("")) {
						JOptionPane.showMessageDialog(lblNewLabel_1, "Các ô không được để trống");
					 	}
					else if(addPassWord.length()<8) {
						JOptionPane.showMessageDialog(lblTenDK_1, "Mật khẩu dài tối thiểu 8 ký tự");
						
					}
					else if(t%1000!=0) {
						JOptionPane.showMessageDialog(lblTenDK_1, "Nhập đúng định dạng tiền");
						
					}
					else {														
						Connection conn = null;
							try {
								Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
								conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUAN_NET;user=sa;password=Son862001");
								
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}																						
							try {
								PreparedStatement pstm1=conn.prepareStatement("select MbName from AcountMember where MbName=?");
								pstm1.setString(1, addName);																												
								ResultSet rs = pstm1.executeQuery();
								
								
								if(rs.next()) {
									JOptionPane.showMessageDialog(lblNewLabel_1, "Tài khoản đã tồn tại");
								}
								else if(addPassWord.equals(confimPassWord)) {								
									try {
										
										pstm1 = conn.prepareStatement("insert into AcountMember values (?,?,?)");
										pstm1.setString(1, addName);
										pstm1.setString(2,addPassWord);
										pstm1.setInt(3,t );
										pstm1.executeUpdate();
										
										pstm1 = conn.prepareStatement("insert into ThongKe values (?,'"+java.time.LocalDate.now()+"',?)");
										pstm1.setString(1, addName);
										pstm1.setInt(2,t );
										pstm1.executeUpdate();
										
										
										
										
										JOptionPane.showMessageDialog(lblNewLabel_1, "Đăng ký thành công");
										dispose();
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
									
									
									
									
									
									if(CBreturn.isSelected()) {
											DKtaiKhoanNguoiChoi dk = new DKtaiKhoanNguoiChoi();
											dk.setVisible(true);
											dispose();
									}
									
									
									}
								
								else {
										JOptionPane.showMessageDialog(lblNewLabel_1,"Mật khẩu xác nhận không chính xác");										
								}
							} catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
				
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(lblNewLabel_1, "Các ô không được để trống");
				}
		
			}
		});
		btnSingup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSingup.setBorder(new LineBorder(new Color(95, 158, 160), 4));
		btnSingup.setBackground(new Color(32, 178, 170));
		btnSingup.setBounds(201, 515, 120, 40);
		contentPane.add(btnSingup);
		
		textField = new JTextField();
		textField.setBorder(new LineBorder(new Color(169, 169, 169)));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(222, 206, 250, 45);
		contentPane.add(textField);
		
		lblTenDK_2 = new JLabel("X\u00E1c nh\u1EADn m\u1EADt kh\u1EA9u :");
		lblTenDK_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTenDK_2.setBounds(32, 332, 200, 50);
		contentPane.add(lblTenDK_2);
		
		btnExit = new JButton("Tho\u00E1t");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//			
				dispose();

			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExit.setBorder(new LineBorder(new Color(169, 169, 169), 3));
		btnExit.setBackground(new Color(192, 192, 192));
		btnExit.setBounds(365, 515, 110, 40);
		contentPane.add(btnExit);
		
		lblSTinNp = new JLabel("S\u1ED1 ti\u1EC1n n\u1EA1p :");
		lblSTinNp.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSTinNp.setBounds(89, 395, 200, 50);
		contentPane.add(lblSTinNp);
		
		textField_3 = new JTextField();
		textField_3.setBorder(new LineBorder(new Color(169, 169, 169)));
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(222, 399, 250, 45);
		contentPane.add(textField_3);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/pig.png")));
		lblNewLabel.setBounds(201, 0, 139, 211);
		contentPane.add(lblNewLabel);
		
		passwordFieldDk = new JPasswordField();
		passwordFieldDk.setBorder(new LineBorder(new Color(169, 169, 169)));
		passwordFieldDk.setBounds(222, 274, 250, 45);
		contentPane.add(passwordFieldDk);
		
		passwordFieldf = new JPasswordField();
		passwordFieldf.setBorder(new LineBorder(new Color(169, 169, 169)));
		passwordFieldf.setBounds(222, 337, 250, 45);
		contentPane.add(passwordFieldf);
		
		CBreturn = new JCheckBox("Tiếp tục đăng ký tài khoản mới?");
		CBreturn.setFocusable(false);
		CBreturn.setHorizontalAlignment(SwingConstants.LEFT);
		CBreturn.setBackground(new Color(210, 220, 220));
		CBreturn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		CBreturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		CBreturn.setBounds(196, 466, 300, 21);
		contentPane.add(CBreturn);
		
	}
}
