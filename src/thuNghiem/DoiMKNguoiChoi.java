package thuNghiem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DoiMKNguoiChoi extends JFrame {

	private JPanel contentPane;
	private JPasswordField psMoi;
	private JPasswordField psXacNhan;
	private JPasswordField psCu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoiMKNguoiChoi frame = new DoiMKNguoiChoi();
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
	public DoiMKNguoiChoi() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Đổi mật khẩu người chơi");}
	
	public DoiMKNguoiChoi(String name) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 661);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(210, 220, 220));
		contentPane_1.setBounds(0, 0, 498, 629);
		contentPane.add(contentPane_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/image/pig (1).png")));
		lblNewLabel_1.setBounds(135, 0, 257, 257);
		contentPane_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Mật khẩu cũ :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(68, 281, 181, 50);
		contentPane_1.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel(" Mật khẩu mới :");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMtKhu.setBounds(52, 368, 150, 50);
		contentPane_1.add(lblMtKhu);
		
		JLabel lblLoi = new JLabel("");
		lblLoi.setBounds(288, 339, 45, 13);
		contentPane_1.add(lblLoi);
		
		psMoi = new JPasswordField();
		psMoi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		psMoi.setBorder(new LineBorder(new Color(169, 169, 169)));
		psMoi.setBounds(196, 371, 250, 45);
		contentPane_1.add(psMoi);
		
		JButton btnNewButton = new JButton("Xác nhận");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mkCu = new String(psCu.getText());
				String mkMoi = new String(psMoi.getText());
			String mkXacNhan = new String(psXacNhan.getText());
				
				if(mkCu.equals("")|mkMoi.equals("")|mkXacNhan.equals("")) {
					JOptionPane.showMessageDialog(lblLoi, "Các ô không được để trống");
				}
				else if(mkMoi.length()<8) {
					JOptionPane.showMessageDialog(lblLoi, "Mật khẩu tối thiểu 8 ký tự");
					
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
						pstm1 = conn.prepareStatement("Select Passwords from AcountMember where Passwords=?");
						
						pstm1.setString(1, mkCu);
						
						
						ResultSet rs = pstm1.executeQuery();
						
						if(rs.next()) {
							if(mkMoi.equals(mkXacNhan)) {
								//thuáº­t toĂ¡n Ä‘á»•i pass
								pstm1 = conn.prepareStatement("Update AcountMember set Passwords=? where MbName = ?");
								pstm1.setString(1, mkMoi);
								pstm1.setString(2,name);
								pstm1.executeUpdate();
								JOptionPane.showMessageDialog(lblLoi, "Đổi mật khẩu thành công");
								HTnguoiChoi ad = new HTnguoiChoi(name);
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
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBorder(new LineBorder(new Color(255, 130, 122), 3));
		btnNewButton.setBackground(new Color(255, 160, 122));
		btnNewButton.setBounds(198, 542, 120, 40);
		contentPane_1.add(btnNewButton);
		
		JButton btnThot = new JButton("Thoát");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HTnguoiChoi ht = new HTnguoiChoi(name);
				ht.setVisible(true);
				dispose();
				
			}
		});
		btnThot.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnThot.setBorder(new LineBorder(new Color(169, 169, 169), 3));
		btnThot.setBackground(Color.LIGHT_GRAY);
		btnThot.setBounds(336, 542, 110, 40);
		contentPane_1.add(btnThot);
		
		JLabel lblXcNhnMt = new JLabel(" Xác nhận mật khẩu :");
		lblXcNhnMt.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblXcNhnMt.setBounds(22, 451, 164, 50);
		contentPane_1.add(lblXcNhnMt);
		
		psXacNhan = new JPasswordField();
		psXacNhan.setFont(new Font("Tahoma", Font.PLAIN, 17));
		psXacNhan.setBorder(new LineBorder(new Color(169, 169, 169)));
		psXacNhan.setBounds(196, 454, 250, 45);
		contentPane_1.add(psXacNhan);
		
		psCu = new JPasswordField();
		psCu.setFont(new Font("Tahoma", Font.PLAIN, 17));
		psCu.setBorder(new LineBorder(new Color(169, 169, 169)));
		psCu.setBounds(196, 286, 250, 45);
		contentPane_1.add(psCu);
	}
}
