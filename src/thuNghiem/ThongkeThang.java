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
import com.toedter.components.JLocaleChooser;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThongkeThang extends JPanel {
	private JTable table;
	private Connection conn=null;
	private DefaultTableModel model =new DefaultTableModel();
	private int M=0;
	private int Y=0;
	private String ThangNam=new String();
	private JLabel lblTongThang;
	private Locale locale =new Locale("vi","VN");
	private NumberFormat format = NumberFormat.getCurrencyInstance(locale);
	private NumberFormat format1 = NumberFormat.getInstance(locale);
	/**
	 * Create the panel.
	 */
	public ThongkeThang() {
		setBorder(new MatteBorder(5, 5, 1, 1, (Color) new Color(112, 128, 144)));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 419, 265);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
		
		JLabel lblThngKThng = new JLabel("CHI TI\u1EBET DOANH THU TH\u00C1NG");
		lblThngKThng.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblThngKThng.setBounds(49, 25, 340, 37);
		add(lblThngKThng);
		table.setRowHeight(25);
		model.addColumn("Tên tài khoản");
		model.addColumn("Số tiền nạp");
		model.addColumn("Ngày nạp");
		table.setModel(model);
		
		JComboBox comboBoxThang = new JComboBox();
		comboBoxThang.setModel(new DefaultComboBoxModel(new String[] {"Tháng", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxThang.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBoxThang.setBounds(134, 72, 85, 30);
		add(comboBoxThang);
		
		JComboBox comboBoxNam = new JComboBox();
		comboBoxNam.setModel(new DefaultComboBoxModel(new String[] {"Năm", "2021", "2020", "2019", "2018", "2017", "2016", "2015"}));
		comboBoxNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBoxNam.setBounds(237, 72, 85, 30);
		add(comboBoxNam);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxThang.getSelectedIndex() >0 && comboBoxNam.getSelectedIndex()>0) {
					
				}
			
				String Mo= (String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex());
				String Ye= (String)comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex());
				
				int nam= Integer.parseInt(Ye);
				int	thang= Integer.parseInt(Mo);
				
				model.setRowCount(0);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				 Calendar c1 = Calendar.getInstance();
				 try {
						PreparedStatement pstm = conn.prepareStatement("select * from dbo.CTDTThang(?,?)");// em truyền câu truy vấn vô đây nha
						pstm.setInt(1, thang);
						pstm.setInt(2,nam);
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
						PreparedStatement pstm = conn.prepareStatement("select dbo.TDTThang(?,?)'a'");
						pstm.setInt(1, thang);
						pstm.setInt(2, nam);
						
						ResultSet rs = pstm.executeQuery();
						while(rs.next()) {
							lblTongThang.setText( "Tổng số tiền: "+format.format(rs.getInt("a"))); 
						
							
						}
					} catch (SQLException e1) {						
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 
				 
				 
				
			}
		});
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(10, 72, 85, 30);
		add(btnTim);
		
		lblTongThang = new JLabel("Tổng : ");
		lblTongThang.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTongThang.setBounds(189, 405, 226, 26);
		add(lblTongThang);
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
		 SimpleDateFormat dateFormat1 = new SimpleDateFormat("MM-yyyy");
		
		
		try {
			PreparedStatement pstm = conn.prepareStatement("select * from dbo.CTdoanhThuThang()");
			
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				//lblThang.setText("Tháng "+dateFormat1.format(c1.getTime()));
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
			PreparedStatement pstm = conn.prepareStatement("select dbo.TongdoanhThuThang() 'a'");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String x= format.format(rs.getInt("a"));
					lblTongThang.setText("Tổng số tiền: "+x);
			
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		
		
		
		
	}
}
