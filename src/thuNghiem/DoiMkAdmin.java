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
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DoiMkAdmin extends JFrame {

	private JPanel contentPane;
	private JPasswordField psMoi;
	private JPasswordField psCu;
	private JPasswordField psXacNhan;
	private JLabel lblLoi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoiMkAdmin frame = new DoiMkAdmin();
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

	
	
	public DoiMkAdmin(String name) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("\u0110\u1ED5i m\u1EADt kh\u1EA9u Admin");
		setBounds(100, 100, 526, 619);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(210, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/image/panda.png")));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(145, 10, 257, 257);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMtKhu = new JLabel(" M\u1EADt kh\u1EA9u m\u1EDBi :");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMtKhu.setBounds(33, 334, 151, 50);
		contentPane.add(lblMtKhu);
		
		psMoi = new JPasswordField();
		psMoi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		psMoi.setBorder(new LineBorder(new Color(169, 169, 169)));
		psMoi.setBounds(212, 340, 260, 45);
		contentPane.add(psMoi);
		
		JButton btnLogin = new JButton("X\u00E1c nh\u1EADn");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mkCu = new String(psCu.getText());
				String mkMoi = new String(psMoi.getText());
				String mkXacNhan = new String(psXacNhan.getText());
				
				if(mkCu.equals("")|mkMoi.equals("")|mkXacNhan.equals("")) {
					JOptionPane.showMessageDialog(lblLoi, "Các ô không được để trống");
				}
				else if(mkMoi.length()<8) {
					JOptionPane.showMessageDialog(lblLoi, "Mật khẩu dài tối thiểu 8 ký tự");
					
				}
				else if(mkCu.equals(mkMoi)) {
					JOptionPane.showMessageDialog(lblLoi, "Mật khẩu mới không được trùng với mật khẩu cũ");
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
					PreparedStatement pstm1;	
					try {
						pstm1 = conn.prepareStatement("Select Passwords from AcountAdmin where Passwords=?");
						
						pstm1.setString(1, mkCu);
						
						
						ResultSet rs = pstm1.executeQuery();
						
						if(rs.next()) {
							if(mkMoi.equals(mkXacNhan)) {
								//thuáº­t toĂ¡n Ä‘á»•i pass
								pstm1 = conn.prepareStatement("Update AcountAdmin set Passwords=? where AdName = ?");
								pstm1.setString(1, mkMoi);
								pstm1.setString(2,name);
								pstm1.executeUpdate();
								JOptionPane.showMessageDialog(lblLoi, "Đổi mật khẩu thành công");
								adminPane ad = new adminPane(name);
								ad.setVisible(true);
								dispose();

								
								
								
							}
								
							
							else {
								JOptionPane.showMessageDialog(lblLoi, "Mật khẩu xác nhận không chính xác");
							}
							
							
							
						}
					
						else {
							JOptionPane.showMessageDialog(lblLoi, "Mật khẩu cũ không đúng");
						}
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					
					
				}
				
				
				
			}
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLogin.setBorder(new LineBorder(new Color(233, 150, 122), 4, true));
		btnLogin.setBackground(new Color(255, 160, 122));
		btnLogin.setBounds(214, 484, 120, 40);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Thoát");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnExit.setBorder(new LineBorder(new Color(169, 169, 169), 3));
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.setBounds(360, 484, 107, 40);
		contentPane.add(btnExit);
		
		JLabel lblMtKhuCux = new JLabel(" M\u1EADt kh\u1EA9u c\u0169 :");
		lblMtKhuCux.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMtKhuCux.setBounds(33, 263, 151, 50);
		contentPane.add(lblMtKhuCux);
		
		psCu = new JPasswordField();
		psCu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		psCu.setBorder(new LineBorder(new Color(169, 169, 169)));
		psCu.setBounds(212, 269, 260, 45);
		contentPane.add(psCu);
		
		JLabel lblXcNhnLi = new JLabel("X\u00E1c nh\u1EADn l\u1EA1i m\u1EADt kh\u1EA9u :");
		lblXcNhnLi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXcNhnLi.setBounds(21, 407, 179, 50);
		contentPane.add(lblXcNhnLi);
		
		psXacNhan = new JPasswordField();
		psXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		psXacNhan.setBorder(new LineBorder(new Color(169, 169, 169)));
		psXacNhan.setBounds(212, 410, 260, 45);
		contentPane.add(psXacNhan);
		
		lblLoi = new JLabel("");
		lblLoi.setBounds(222, 324, 45, 13);
		contentPane.add(lblLoi);
		
		}
	public DoiMkAdmin() {
		setTitle("Đổi mật khẩu Admin");}
	}

