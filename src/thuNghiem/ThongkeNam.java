package thuNghiem;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThongkeNam extends JPanel {
	private JTable table;
	private Connection conn=null;
	private DefaultTableModel model =new DefaultTableModel();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private int Y=0;
	private String Nam= new String();
	private JComboBox comboBoxNam;
	private JLabel lblTongNam;
	private Locale locale =new Locale("vi","VN");
	private NumberFormat format = NumberFormat.getCurrencyInstance(locale);
	private NumberFormat format1 = NumberFormat.getInstance(locale);
	/**
	 * Create the panel.
	 */
	public ThongkeNam() {
		setBorder(new MatteBorder(5, 5, 1, 1, (Color) new Color(112, 128, 144)));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 419, 250);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
		
		JLabel lblThngKNm = new JLabel("CHI TI\u1EBET DOANH THU N\u0102M");
		lblThngKNm.setFont(new Font("Times New Roman", Font.ITALIC, 27));
		lblThngKNm.setBounds(45, 25, 348, 37);
		add(lblThngKNm);
		table.setRowHeight(25);
		
		
		
		model.addColumn("Tên tài khoản");
		model.addColumn("Số tiền nạp");
		model.addColumn("Ngày nạp");
		table.setModel(model);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if (comboBoxNam.getSelectedIndex()>0) {
					
				}
				
				String t =(String)comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex());
				int	Y= Integer.parseInt(t);
			
				model.setRowCount(0);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				 Calendar c1 = Calendar.getInstance();
				 try {
						PreparedStatement pstm = conn.prepareStatement("select * from dbo.CTDTNam(?)");
						pstm.setInt(1,Y);
						
						ResultSet rs = pstm.executeQuery();
						while(rs.next()) {
							c1.setTime(rs.getDate("date"));
							c1.roll(Calendar.DATE, 0);
							model.addRow(new Object [] {rs.getString("MbName"),format1.format(rs.getInt("SoTienNap")),dateFormat.format(c1.getTime())});
						}
						table.setModel(model);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				 try {
						PreparedStatement pstm = conn.prepareStatement("select dbo.TDTNam(?)'a'");
						
						pstm.setInt(1, Y);
						
						ResultSet rs = pstm.executeQuery();
						while(rs.next()) {
							lblTongNam.setText( "Tổng số tiền: "+format.format(rs.getInt("a"))); 
						
							
						}
					} catch (SQLException e1) {						
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				
				
				
				
			}
		});
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(22, 76, 85, 30);
		add(btnTim);
		
		comboBoxNam = new JComboBox();
		comboBoxNam.setModel(new DefaultComboBoxModel(new String[] {"Năm", "2021", "2020", "2019", "2018", "2017", "2016", "2015"}));
		comboBoxNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBoxNam.setBounds(146, 76, 85, 30);
		add(comboBoxNam);
		
		lblTongNam = new JLabel("Tổng : ");
		lblTongNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTongNam.setBounds(191, 406, 226, 26);
		add(lblTongNam);
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		 Calendar c1 = Calendar.getInstance();
		 SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy");
		
		
		try {
			PreparedStatement pstm = conn.prepareStatement("select * from dbo.CTdoanhThuNam()");
			
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				//lblNam.setText("Năm "+dateFormat1.format(c1.getTime()));
				c1.setTime(rs.getDate("date"));
				c1.roll(Calendar.DATE, 0);
				model.addRow(new Object [] {rs.getString("MbName"),format1.format(rs.getInt("SoTienNap")),dateFormat.format(c1.getTime())});
			}
			table.setModel(model);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			PreparedStatement pstm = conn.prepareStatement("select dbo.TongdoanhThuNam() 'a'");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String x= format.format(rs.getInt("a"));
					lblTongNam.setText("Tổng số tiền: "+x);
			
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	

}
