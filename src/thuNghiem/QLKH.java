package thuNghiem;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Locale;

import com.mxrck.autocompleter.TextAutoCompleter; 

public class QLKH extends JPanel {
	private JTextField txtSearch;
	private	DefaultTableModel model = new DefaultTableModel();
	private JTable table;
	private String LTen= new String();
	private Connection conn = null;
	private TextAutoCompleter ac;
	private int clickS=0;
	/**
	 * Create the panel.
	 */
	
	private JLabel lblhung;
	
	public QLKH() {
		setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(3, 2, 2, 2, (Color) new Color(169, 169, 169)));
		panel_2.setBounds(0, 0, 1043, 487);
		add(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBackground(new Color(169, 169, 169));
		scrollPane.setBounds(5, 129, 505, 355);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LTen= (String) table.getValueAt(table.getSelectedRow(), 0);
				
			}
		});
		table.setRowHeight(25);
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(null);
		panel_3.setBackground(new Color(220, 220, 220));
		panel_3.setBounds(548, 65, 439, 30);
		panel_2.add(panel_3);
		
		JLabel lblSearch = new JLabel("        ");
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String key1 =new String ();
				key1=txtSearch.getText();
				if(key1.equals("")) {
					loadData();
				}
				else {
				model.setRowCount(0);
				try {
					PreparedStatement pstm = conn.prepareStatement("Select * from AcountMember where MbName = ?");
					pstm.setString(1, key1);
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {
						model.addRow(new Object [] {rs.getString("MbName"),rs.getInt("SoTienNap")});
					}
					table.setModel(model);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		lblSearch.setIcon(new ImageIcon(getClass().getResource("/image/magnifying-glass (2).png")));
		lblSearch.setHorizontalTextPosition(SwingConstants.LEFT);
		lblSearch.setBounds(376, -1, 64, 30);
		panel_3.add(lblSearch);
		
		txtSearch = new JTextField();
		txtSearch.setForeground(Color.GRAY);
		txtSearch.setText("Tìm kiếm theo tên tài khoản");
		txtSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(clickS==0) {
				txtSearch.setText("");
				txtSearch.setForeground(Color.black);
				CargarAutoCompleter();
				clickS=clickS+1;
				}
				
			}
		});
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSearch.setColumns(10);
		txtSearch.setBorder(null);
		txtSearch.setBackground(new Color(220, 220, 220));
		txtSearch.setBounds(10, 0, 356, 30);
		panel_3.add(txtSearch);
		ac = new TextAutoCompleter(txtSearch);
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel_5.setBackground(new Color(135, 206, 235));
		panel_5.setBounds(3, 4, 1037, 35);
		panel_2.add(panel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Quản lý khách hàng");
		lblNewLabel_4.setIcon(new ImageIcon(getClass().getResource("/image/user.png")));
		lblNewLabel_4.setIconTextGap(10);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 1, 300, 32);
		panel_5.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Th\u00EAm");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DKtaiKhoanNguoiChoi ah = new DKtaiKhoanNguoiChoi();
				ah.setVisible(true);
		
				}
			//}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNewButton.setIconTextGap(3);
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/image/add.png")));
		btnNewButton.setBackground(new Color(32, 178, 170));
		btnNewButton.setBounds(41, 65, 85, 30);
		panel_2.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("X\u00F3a");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//XoĂ¡
				if(LTen.equals("")){
					JOptionPane.showMessageDialog(btnNewButton_2,"Vui lòng chọn tài khoản muốn xoá");
				}
				else {
					int a =JOptionPane.showConfirmDialog(btnNewButton_2,"Chắc chắn xoá tài khoản/");
					if(a==JOptionPane.YES_OPTION) {
				try {
					PreparedStatement pstm = conn.prepareStatement("delete from AcountMember where MbName = ? ");
					pstm.setString(1, LTen);
					pstm.executeUpdate();
					
					loadData();
					table.setModel(model);
					JOptionPane.showMessageDialog(btnNewButton_2,"Xoá thành công");
					
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				LTen="";
					}
				}
				
				
			}	
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setIcon(new ImageIcon(getClass().getResource("/image/bin.png")));
		btnNewButton_2.setBackground(new Color(32, 178, 170));
		btnNewButton_2.setBounds(370, 65, 85, 30);
		panel_2.add(btnNewButton_2);
		model.addColumn("Tên tài khoản");
		model.addColumn("Số dư");
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"ID ng\u01B0\u1EDDi ch\u01A1i", "T\u00EAn t\u00E0i kho\u1EA3n", "M\u1EADt kh\u1EA9u", "S\u1ED1 ti\u1EC1n n\u1EA1p", "Action"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Object.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JButton btnNewButton_1_1 = new JButton("Nạp");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				NapTien nt = new NapTien(LTen);
				nt.setVisible(true);
				
			}
		});
		btnNewButton_1_1.setIcon(new ImageIcon(getClass().getResource("/image/money (1).png")));
		btnNewButton_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1_1.setBackground(new Color(32, 178, 170));
		btnNewButton_1_1.setBounds(195, 65, 85, 30);
		panel_2.add(btnNewButton_1_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(558, 105, 475, 372);
		panel_2.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3_4 = new JLabel("Số giờ");
		lblNewLabel_3_4.setBounds(226, 195, 134, 30);
		panel.add(lblNewLabel_3_4);
		lblNewLabel_3_4.setForeground(Color.RED);
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.ITALIC, 23));
		
		JLabel lblNewLabel_3_3_1_1 = new JLabel("20 giờ");
		lblNewLabel_3_3_1_1.setBounds(234, 285, 126, 30);
		panel.add(lblNewLabel_3_3_1_1);
		lblNewLabel_3_3_1_1.setForeground(Color.RED);
		lblNewLabel_3_3_1_1.setFont(new Font("Tahoma", Font.ITALIC, 23));
		
		JLabel lblNewLabel_3_1_1 = new JLabel("5 giơ");
		lblNewLabel_3_1_1.setBounds(234, 224, 126, 30);
		panel.add(lblNewLabel_3_1_1);
		lblNewLabel_3_1_1.setForeground(Color.RED);
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.ITALIC, 23));
		
		JLabel lblNewLabel_3_3_1 = new JLabel("200.000");
		lblNewLabel_3_3_1.setBounds(101, 285, 103, 30);
		panel.add(lblNewLabel_3_3_1);
		lblNewLabel_3_3_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		
		JLabel lblNewLabel_3_3_2 = new JLabel("10 giờ");
		lblNewLabel_3_3_2.setBounds(234, 254, 126, 30);
		panel.add(lblNewLabel_3_3_2);
		lblNewLabel_3_3_2.setForeground(Color.RED);
		lblNewLabel_3_3_2.setFont(new Font("Tahoma", Font.ITALIC, 23));
		
		JLabel lblNewLabel_3_1 = new JLabel("50.000");
		lblNewLabel_3_1.setBounds(101, 224, 92, 30);
		panel.add(lblNewLabel_3_1);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		
		JLabel lblNewLabel_3_3 = new JLabel("100.000");
		lblNewLabel_3_3.setBounds(101, 254, 103, 30);
		panel.add(lblNewLabel_3_3);
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 23));
		
		JLabel lblNewLabel_3 = new JLabel("Số tiền nạp");
		lblNewLabel_3.setBounds(93, 195, 126, 30);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 23));
		
		lblhung = new JLabel("");
		lblhung.setBounds(28, 10, 437, 437);
		panel.add(lblhung);
		lblhung.setIcon(new ImageIcon(getClass().getResource("/image/2S.png")));
		
		JLabel lblNewLabel = new JLabel("Bảng giá dịch vụ");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 38));
		lblNewLabel.setBounds(58, 5, 368, 60);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Giá giờ chơi hội viên: 10000/giờ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1.setBounds(206, 60, 235, 18);
		panel.add(lblNewLabel_1);
		table.getColumnModel().getColumn(4).setResizable(false);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUAN_NET;user=sa;password=Son862001");
		    
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		loadData();

		
	}
	
	
	
	public void loadData() {
		model.setRowCount(0);
		Locale locale =new Locale("vi","VN");
		NumberFormat format = NumberFormat.getInstance(locale);
	try {
		PreparedStatement pstm = conn.prepareStatement("Select * from AcountMember order by MbName ");
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			model.addRow(new Object [] {rs.getString("MbName"),format.format(rs.getInt("SoTienNap"))});
		}
		table.setModel(model);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
}
	
	private void CargarAutoCompleter() {  
		ArrayList<String> valores=new ArrayList<>();
		
		try {
			PreparedStatement pstm = conn.prepareStatement("Select * from AcountMember ");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				
				valores.add(rs.getString("MbName")) ;
			
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for(int i =0; i < valores.size();i++){
			ac.addItem(valores.get(i));
		}	
	}
}
