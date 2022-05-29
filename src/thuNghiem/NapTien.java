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
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class NapTien extends JFrame {

	private JPanel contentPane;
	private JTextField txtTenTK;
	private JTextField txtTien;
	private JLabel lblLoi;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NapTien frame = new NapTien();
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
	public NapTien( ) {}
	public NapTien(String Lten) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("N\u1EA1p ti\u1EC1n");
		setBounds(100, 100, 607, 519);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTenTK = new JTextField(Lten);
		txtTenTK.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtTenTK.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtTenTK.setBounds(224, 206, 297, 45);
		contentPane.add(txtTenTK);
		txtTenTK.setColumns(10);
		
		txtTien = new JTextField();
		txtTien.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtTien.setBorder(new LineBorder(new Color(171, 173, 179)));
		txtTien.setColumns(10);
		txtTien.setBounds(224, 298, 297, 45);
		contentPane.add(txtTien);
		
		JLabel lblNewLabel = new JLabel("T\u00EAn t\u00E0i kho\u1EA3n :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(79, 206, 135, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblTi = new JLabel("S\u1ED1 ti\u1EC1n n\u1EA1p :");
		lblTi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTi.setBounds(98, 294, 135, 38);
		contentPane.add(lblTi);
		
		JLabel lblNewLabel_1 = new JLabel("Nhi\u1EC1u nhi\u1EC1u nha b\u1EA1n");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 28));
		lblNewLabel_1.setIconTextGap(10);
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/image/panda (5).png")));
		lblNewLabel_1.setBounds(75, 28, 442, 151);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNap = new JButton("N\u1EA1p ti\u1EC1n");
		btnNap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name = txtTenTK.getText();
				String tien = txtTien.getText();
				int t = Integer.parseInt(tien);
				int TS=0;
				if(Name.equals("")|tien.equals("")) {
					JOptionPane.showMessageDialog(lblLoi,"Các ô không được để trống");
				}
				else if(t%1000!=0) {
					JOptionPane.showMessageDialog(lblLoi, "Nhập đúng định dạng tiền");
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
						PreparedStatement pstm1=conn.prepareStatement(" select MbName,SoTienNap from AcountMember where MbName=?");
						pstm1.setString(1,Name);																												
						ResultSet rs = pstm1.executeQuery();
						
						
						if(rs.next()) {
							TS=rs.getInt("SoTienNap");
							TS=TS+t;
							pstm1=conn.prepareStatement("update AcountMember set SoTienNap = ? where MbName = ?");
							pstm1.setInt(1, TS);
							pstm1.setString(2,Name);
							pstm1.executeUpdate();
							pstm1 = conn.prepareStatement("insert into ThongKe values (?,'"+java.time.LocalDate.now()+"',?)");
							pstm1.setString(1,Name);
							pstm1.setInt(2,t);
							
						
							pstm1.executeUpdate();
							
							
							
							
							
							JOptionPane.showMessageDialog(lblLoi, "Nạp thành công");
							dispose();
							
							
						}
						else {
							JOptionPane.showMessageDialog(lblLoi, "Tài khoản không tồn tại");
						}
					
						
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					
					
				}
				
				
				
			}
		});
		btnNap.setBorder(new LineBorder(new Color(169, 169, 169)));
		btnNap.setBackground(new Color(32, 178, 170));
		btnNap.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNap.setIcon(new ImageIcon(getClass().getResource("/image/money.png")));
		btnNap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNap.setBounds(244, 377, 135, 45);
		contentPane.add(btnNap);
		
		JButton btnThoat = new JButton("Tho\u00E1t");
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnThoat.setBackground(new Color(176, 196, 222));
		btnThoat.setBorder(new LineBorder(new Color(169, 169, 169)));
		btnThoat.setIcon(new ImageIcon(getClass().getResource("/image/logout.png")));
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnThoat.setBounds(404, 377, 109, 45);
		contentPane.add(btnThoat);
		
		lblLoi = new JLabel("");
		lblLoi.setBounds(276, 261, 45, 13);
		contentPane.add(lblLoi);
	}// dong ham
}

