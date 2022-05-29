package thuNghiem;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;

public class QLMT extends JPanel {
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel();
	private JPopupMenu popupMenu;
	private JPopupMenu popupMenu_1;
	private Connection conn = null;
	private String LMay= new String();
	public String LMayID= new String();
	public String LTen= new String();
	/**
	 * Create the panel.
	 */
	public QLMT() {
		setLayout(null);
		
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(null);
		panelMenu.setBorder(new MatteBorder(3, 2, 2, 2, (Color) new Color(169, 169, 169)));
		panelMenu.setBounds(0, 0, 1043, 487);
		add(panelMenu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setBackground(new Color(169, 169, 169));
		scrollPane.setBounds(5, 65, 461, 419);
		panelMenu.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String key = new String ();	
				key=(String)(table.getValueAt(table.getSelectedRow(), 1)) 	;
				
				if(key.equals("<html><font color=green>Online</font>")) {
			table.setComponentPopupMenu(popupMenu);   }
				else {
					table.setComponentPopupMenu(popupMenu_1);
				}
			
				LMay=(String)(table.getValueAt(table.getSelectedRow(), 1)) ;
				LMayID=(String)(table.getValueAt(table.getSelectedRow(), 0)) ;
				
			}
		});
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scrollPane.setViewportView(table);
		
		popupMenu_1 = new JPopupMenu();
		popupMenu_1.setBounds(0, 0, 200, 50);
		panelMenu.add(popupMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Xoá máy");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a =JOptionPane.showConfirmDialog(mntmNewMenuItem_3,"Chắc chắn xoá máy?");
				if(a==JOptionPane.YES_OPTION ) {
				
				try {
					PreparedStatement pstm = conn.prepareStatement("delete QLMayTinh where mayid = ? ");
					pstm.setString(1, LMayID);
					pstm.executeUpdate();
					LMayID="";
					loadData();
					table.setModel(model);
					JOptionPane.showMessageDialog(mntmNewMenuItem_3,"Máy đã được xoá");
					
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		popupMenu_1.add(mntmNewMenuItem_3);
		
		popupMenu = new JPopupMenu();
		popupMenu.setBounds(0, 0, 200, 50);
		panelMenu.add(popupMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Chi tiết máy");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChiTietMay CTM = new ChiTietMay(LMayID);
				CTM.setVisible(true);
			}
		});
		popupMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Tắt máy");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a =JOptionPane.showConfirmDialog(mntmNewMenuItem_2,"Chắc chắn tắt máy?");
				if(a==JOptionPane.YES_OPTION) {
				try {
					
					PreparedStatement pstm = conn.prepareStatement("update QLMayTinh set status = 0	 where mayid = ? ");
					pstm.setString(1, LMayID);
					pstm.executeUpdate();
					LMayID="";
					loadData();
					table.setModel(model);
					JOptionPane.showMessageDialog(mntmNewMenuItem_2,"Tắt máy thành công");
					
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
		});
		
		JSeparator separator = new JSeparator();
		popupMenu.add(separator);
		popupMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Xoá máy");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a =JOptionPane.showConfirmDialog(mntmNewMenuItem_1,"Chắc chắn xoá máy");
				if(a==JOptionPane.YES_OPTION) {
				
				try {
					PreparedStatement pstm = conn.prepareStatement("delete QLMayTinh where mayid = ? ");
					pstm.setString(1, LMayID);
					pstm.executeUpdate();
					LMayID="";
					loadData();
					table.setModel(model);
					JOptionPane.showMessageDialog(mntmNewMenuItem_1,"Máy đã được xoá");
					
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		popupMenu.add(separator_1);
		popupMenu.add(mntmNewMenuItem_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(192, 192, 192)));
		panel_5.setBackground(new Color(135, 206, 235));
		panel_5.setBounds(3, 4, 1037, 35);
		panelMenu.add(panel_5);
		
		JLabel lblNewLabel_4 = new JLabel("Qu\u1EA3n l\u00FD m\u00E1y tr\u1EA1m");
		lblNewLabel_4.setIcon(new ImageIcon(getClass().getResource("/image/computer (4).png")));
		lblNewLabel_4.setIconTextGap(10);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(10, 1, 300, 32);
		panel_5.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Th\u00EAm");
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNewButton.setIconTextGap(3);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddMay m = new AddMay();
				m.setVisible(true);
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/image/add.png")));
		btnNewButton.setBackground(new Color(32, 178, 170));
		btnNewButton.setBounds(491, 98, 85, 30);
		panelMenu.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("T\u1EAFt");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(LMayID.equals("")){
					JOptionPane.showMessageDialog(btnNewButton_2,"Vui lòng chọn máy muốn tắt");
				}
				else if(LMay.equals("<html><font color=red>Offline</font>")) {
					JOptionPane.showMessageDialog(btnNewButton_2,"Máy đã offline");
				}
				else {
					int a =JOptionPane.showConfirmDialog(btnNewButton_2,"Chắc chắn tắt máy?");
					if(a== JOptionPane.YES_OPTION) {
						
						
						LMay="";
					try {
						PreparedStatement pstm = conn.prepareStatement("update QLMayTinh set status = 0	 where mayid = ? ");
						pstm.setString(1, LMayID);
						pstm.executeUpdate();
						LMayID="";
						loadData();
						table.setModel(model);
						JOptionPane.showMessageDialog(btnNewButton_2,"Tắt máy thành công");
						
				} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
				
				
				
				}
				
				
				
				
				
				
			}
		});
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_2.setIcon(new ImageIcon(getClass().getResource("/image/on-off-button.png")));
		btnNewButton_2.setBackground(new Color(32, 178, 170));
		btnNewButton_2.setBounds(491, 274, 85, 30);
		panelMenu.add(btnNewButton_2);
		
		JButton btnNewButton_1 = new JButton("Xóa");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(LMayID.equals("")){
					JOptionPane.showMessageDialog(btnNewButton_2,"Vui lòng chọn máy muốn xóa");
				}
				
				else {
					int a =JOptionPane.showConfirmDialog(btnNewButton_2,"Chắc chắn xóa máy?");
					if(a==JOptionPane.YES_OPTION) {
					
				try {
					PreparedStatement pstm = conn.prepareStatement("delete QLMayTinh where mayid = ? ");
					pstm.setString(1, LMayID);
					pstm.executeUpdate();
					LMayID="";
					loadData();
					table.setModel(model);
					JOptionPane.showMessageDialog(btnNewButton_2,"Máy đã được xóa");
					
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				LMayID="";
				
				}
				}
				
				
				
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1.setIcon(new ImageIcon(getClass().getResource("/image/bin.png")));
		btnNewButton_1.setBackground(new Color(32, 178, 170));
		btnNewButton_1.setBounds(491, 366, 85, 30);
		panelMenu.add(btnNewButton_1);
		
		JButton btnNapTin = new JButton("Nạp");
		
		btnNapTin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					PreparedStatement pstm = conn.prepareStatement("Select * from QLMayTinh where MayID = ?");
					pstm.setString(1, LMayID);
					
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {
						LTen = rs.getString("MbName");
						
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
				NapTien nt = new NapTien(LTen);
				nt.setVisible(true);
			}
		});
		btnNapTin.setIcon(new ImageIcon(getClass().getResource("/image/money.png")));
		btnNapTin.setIconTextGap(3);
		btnNapTin.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNapTin.setHorizontalAlignment(SwingConstants.LEFT);
		btnNapTin.setBackground(new Color(32, 178, 170));
		btnNapTin.setBounds(491, 180, 85, 30);
		panelMenu.add(btnNapTin);
		model.addColumn("ID Máy Tính");
		model.addColumn("Tình Trạng");
		table.setModel(model);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(586, 65, 439, 419);
		panelMenu.add(panel);
		
		JLabel lblNewLabel_3_4 = new JLabel("Số Giờ");
		lblNewLabel_3_4.setForeground(Color.RED);
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.ITALIC, 23));
		lblNewLabel_3_4.setBounds(217, 217, 134, 30);
		panel.add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_3_3_1_1 = new JLabel("20 giờ");
		lblNewLabel_3_3_1_1.setForeground(Color.RED);
		lblNewLabel_3_3_1_1.setFont(new Font("Tahoma", Font.ITALIC, 23));
		lblNewLabel_3_3_1_1.setBounds(225, 307, 126, 30);
		panel.add(lblNewLabel_3_3_1_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("5 giờ");
		lblNewLabel_3_1_1.setForeground(Color.RED);
		lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.ITALIC, 23));
		lblNewLabel_3_1_1.setBounds(225, 246, 126, 30);
		panel.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_3_1 = new JLabel("200.000");
		lblNewLabel_3_3_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_3_3_1.setBounds(92, 307, 103, 30);
		panel.add(lblNewLabel_3_3_1);
		
		JLabel lblNewLabel_3_3_2 = new JLabel("10 giờ");
		lblNewLabel_3_3_2.setForeground(Color.RED);
		lblNewLabel_3_3_2.setFont(new Font("Tahoma", Font.ITALIC, 23));
		lblNewLabel_3_3_2.setBounds(225, 276, 126, 30);
		panel.add(lblNewLabel_3_3_2);
		
		JLabel lblNewLabel_3_1 = new JLabel("50.000");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_3_1.setBounds(92, 246, 92, 30);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_3 = new JLabel("100.000");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_3_3.setBounds(92, 276, 103, 30);
		panel.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3 = new JLabel("Số tiền nạp");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_3.setBounds(84, 217, 126, 30);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("BẢNG GIÁ DỊCH VỤ");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 38));
		lblNewLabel.setBounds(44, 10, 368, 60);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Giá chơi của hội viên: 10000/giờ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblNewLabel_1.setBounds(180, 63, 235, 18);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(getClass().getResource("/image/2S.png")));
		lblNewLabel_2.setBounds(0, 40, 461, 412);
		panel.add(lblNewLabel_2);
		
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
		String TinhTrang=new String();
		
		
		
		try {
			PreparedStatement pstm = conn.prepareStatement("Select * from QLMayTinh order by Status desc");
			
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				
				if(rs.getInt("Status")==0) {
					TinhTrang="<html><font color=red>Offline</font>";
					
				}
				else {TinhTrang="<html><font color=green>Online</font>";
				
				}
				model.addRow(new Object [] {rs.getString("MayID"),TinhTrang});
			}
			table.setModel(model);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
