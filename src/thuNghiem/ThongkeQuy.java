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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThongkeQuy extends JPanel {
	private JTable table;
	private Connection conn=null;
	private DefaultTableModel model =new DefaultTableModel();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private int Y=0;
	private int Q=0;
	private String QuyNam= new String();
	private JLabel lblTongQuy;
	private Locale locale =new Locale("vi","VN");
	private NumberFormat format = NumberFormat.getCurrencyInstance(locale);
	private NumberFormat format1 = NumberFormat.getInstance(locale);
	/**
	 * Create the panel.
	 */
	public ThongkeQuy() {
		setBorder(new MatteBorder(5, 5, 1, 1, (Color) new Color(112, 128, 144)));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 145, 419, 253);
		add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setViewportView(table);
		
		JLabel lblThngKQu = new JLabel("CHI TI\u1EBET DOANH THU QU\u00DD");
		lblThngKQu.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		lblThngKQu.setBounds(53, 23, 333, 37);
		add(lblThngKQu);
		table.setRowHeight(25);
		model.addColumn("Tên tài khoản");
		model.addColumn("Số tiền nạp");
		model.addColumn("Ngày nạp");
		table.setModel(model);
		
		JComboBox comboBoxThang = new JComboBox();
		comboBoxThang.setModel(new DefaultComboBoxModel(new String[] {"Quý", "1", "2", "3", "4"}));
		comboBoxThang.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBoxThang.setBounds(147, 70, 85, 30);
		add(comboBoxThang);
		
		JComboBox comboBoxNam = new JComboBox();
		comboBoxNam.setModel(new DefaultComboBoxModel(new String[] {"Năm", "2021", "2020", "2019", "2018", "2017", "2016", "2015"}));
		comboBoxNam.setFont(new Font("Tahoma", Font.PLAIN, 17));
		comboBoxNam.setBounds(250, 70, 85, 30);
		add(comboBoxNam);
		
		JButton btnTim = new JButton("Tìm");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
if (comboBoxThang.getSelectedIndex() >0 && comboBoxNam.getSelectedIndex()>0) {
					
				}
				// anh lấy ra giá trị int cho em nè
				String Q= (String)comboBoxThang.getItemAt(comboBoxThang.getSelectedIndex());
				String Y= (String)comboBoxNam.getItemAt(comboBoxNam.getSelectedIndex());
				//còn đây là kiểu String em dùng cái nào thì dùng à không 
				// cần phải nạp kiểu date ào truy vấn đâu nên cứ dùng bình thường k bt hỏi Quyền nhé
				int nam= Integer.parseInt(Y);
				int	quy= Integer.parseInt(Q);
				
				model.setRowCount(0);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				 Calendar c1 = Calendar.getInstance();
				 try {
						PreparedStatement pstm = conn.prepareStatement("select * from dbo.CTDTQuy(?,?)");// em truyền câu truy vấn vô đây nha
						pstm.setInt(1, quy);
						pstm.setInt(2, nam);
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
						PreparedStatement pstm = conn.prepareStatement("select dbo.TDTQuy(?,?)'a'");
						pstm.setInt(1, quy);
						pstm.setInt(2, nam);
						
						ResultSet rs = pstm.executeQuery();
						while(rs.next()) {
							lblTongQuy.setText( "Tổng số tiền: "+format.format(rs.getInt("a"))); 
						
							
						}
					} catch (SQLException e1) {						
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 
				 
				
			}
				
		
			
		});
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTim.setBounds(23, 70, 85, 30);
		add(btnTim);
		
		lblTongQuy = new JLabel("Tổng : ");
		lblTongQuy.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTongQuy.setBounds(191, 405, 226, 26);
		add(lblTongQuy);
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
			PreparedStatement pstm = conn.prepareStatement("select * from dbo.CTdoanhthuQuy()");
			
			
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				
				c1.setTime(rs.getDate("date"));
				c1.roll(Calendar.DATE, 1);
				model.addRow(new Object [] {rs.getString("MbName"),format1.format(rs.getInt("SoTienNap")),dateFormat.format(c1.getTime())});
			}
			table.setModel(model);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			PreparedStatement pstm = conn.prepareStatement("select dbo.TongDoanhthuQuy() 'a'");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String x=format.format(rs.getInt("a"));
					lblTongQuy.setText("Tổng số tiền: "+x);
			
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
	}
}
