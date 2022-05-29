package thuNghiem;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.ComponentOrientation;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.LineBorder;

public class thongkeNgay extends JPanel {
	private JTable table;
	private Connection conn=null;
	private DefaultTableModel model =new DefaultTableModel();
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private JLabel lblTongNgay;
	private Locale locale =new Locale("vi","VN");
	private NumberFormat format = NumberFormat.getCurrencyInstance(locale);
	private NumberFormat format1 = NumberFormat.getInstance(locale);
	/**
	 * Create the panel.
	 */
	public thongkeNgay() {
		setBorder(new MatteBorder(5, 5, 1, 1, (Color) new Color(112, 128, 144)));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 120, 419, 275);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("CHI TI\u1EBET DOANH THU NG\u00C0Y");
		lblNewLabel.setBounds(114, 13, 257, 34);
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		add(lblNewLabel);
		table.setRowHeight(25);
		model.addColumn("Tên Tài Khoản");
		model.addColumn("Số Tiền Nạp");
		model.addColumn("Ngày nạp");
		table.setModel(model);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBorder(new LineBorder(new Color(169, 169, 169)));
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 17));
		dateChooser.setBounds(161, 57, 163, 30);
		add(dateChooser);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				 Calendar c1 = Calendar.getInstance();
				try {
					PreparedStatement pstm = conn.prepareStatement("select * from ThongKe where date =?");
					pstm.setString(1, df.format(dateChooser.getDate()));
					
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
					PreparedStatement pstm = conn.prepareStatement("select dbo.TDTNgay(?)'a'");
					pstm.setString(1,df.format(dateChooser.getDate()));
					
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {
						lblTongNgay.setText( "Tổng số tiền: "+format.format(rs.getInt("a")) ); 
					
						
					}
				} catch (SQLException e1) {						
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(25, 57, 85, 30);
		add(btnTim);
		
		lblTongNgay = new JLabel("Tổng : ");
		lblTongNgay.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTongNgay.setBounds(175, 405, 226, 26);
		add(lblTongNgay);
		//ngay=ng;
		
		
		
		
		
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
		try {
			PreparedStatement pstm = conn.prepareStatement("select * from dbo.CTdoanhThuNgay()");
			
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				
				c1.setTime(rs.getDate("date"));
				c1.roll(Calendar.DATE, 0);
				model.addRow(new Object [] {rs.getString("MbName"),format1.format(rs.getInt("SoTienNap")) ,dateFormat.format(c1.getTime())});
			}
			table.setModel(model);
		} catch (SQLException e1) {						
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			PreparedStatement pstm = conn.prepareStatement("select dbo.TDTNgay(?)'a'");
			pstm.setString(1,df.format(c1.getTime()));
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				lblTongNgay.setText( "Tổng số tiền: "+format.format(rs.getInt("a")) ); 
			
				
			}
		} catch (SQLException e1) {						
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
}
}
