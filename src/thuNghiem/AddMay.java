package thuNghiem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddMay extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMay frame = new AddMay();
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
	public AddMay() {
		setTitle("Th\u00EAm m\u00E1y m\u1EDBi");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 463, 394);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID M\u00C1Y:");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/computer (4).png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(23, 109, 113, 64);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField.setBounds(146, 121, 268, 48);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tr\u1EA1ng th\u00E1i : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(35, 52, 119, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("OFFLINE");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(164, 52, 119, 42);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Th\u00EAm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String may = textField.getText();
				
				if(may.equals("")) {
					JOptionPane.showMessageDialog(btnNewButton, "Chưa nhập id máy");
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
						PreparedStatement pstm1=conn.prepareStatement("select MayID from QLMayTinh where MayID=?");
						pstm1.setString(1, may);																												
						ResultSet rs = pstm1.executeQuery();
						
						
						if(rs.next()) {
							JOptionPane.showMessageDialog(lblNewLabel_1, "ID máy đã tồn tại,nhập ID máy mới");
						}											
						else {
								pstm1 = conn.prepareStatement("insert into QLMayTinh(MayID,Status) values(?,'0')");
								pstm1.setString(1,may);
								pstm1.executeUpdate();
								JOptionPane.showMessageDialog(btnNewButton, "Thêm máy thành công");
								dispose();
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				}
			}
			
		});
		btnNewButton.setBackground(new Color(95, 158, 160));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(115, 233, 113, 54);
		contentPane.add(btnNewButton);
		
		JButton btnThot = new JButton("Tho\u00E1t");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnThot.setBackground(new Color(220, 220, 220));
		btnThot.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnThot.setBounds(270, 233, 113, 54);
		contentPane.add(btnThot);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(23, 46, 255, 54);
		contentPane.add(lblNewLabel_2);
	}
}
