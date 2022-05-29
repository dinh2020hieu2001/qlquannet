package thuNghiem;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.ImageIcon;


public class ThongKe extends JPanel {
	private JPanel panelTK;
	private Connection conn=null;
	private JLabel lblTngay;
	private JLabel lblTthang;
	private JLabel lblNhanQuy;
	private JLabel lblTquy;
	private JLabel lblNam;
	/**
	 * Create the panel.
	 */
	public ThongKe() {
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 196, 222));
		panel_1.setBorder(new MatteBorder(3, 2, 2, 2, (Color) new Color(169, 169, 169)));
		panel_1.setBounds(0, 0, 1043, 487);
		add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBorder(new MatteBorder(6, 4, 1, 1, (Color) new Color(47, 79, 79)));
		panel.setBounds(508, 83, 227, 157);
		panel_1.add(panel);
		panel.setLayout(null);
		
		JLabel lblNhanNgay = new JLabel("");
		lblNhanNgay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelTK.removeAll();
				thongkeNgay TKngay= new thongkeNgay();
				TKngay.setBounds(0,0,439,441);
				panelTK.add(TKngay);	
				repaint();
				revalidate();
				
				
			}
		});
		lblNhanNgay.setBounds(0, 0, 227, 157);
		panel.add(lblNhanNgay);
		
		JLabel lblNewLabel_1 = new JLabel("T\u1ED5ng thu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(59, 32, 107, 37);
		panel.add(lblNewLabel_1);
		
		lblTngay = new JLabel("000000000");
		lblTngay.setHorizontalAlignment(SwingConstants.CENTER);
		lblTngay.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblTngay.setBounds(42, 79, 143, 45);
		panel.add(lblTngay);
		
		panelTK = new JPanel();
		panelTK.setBackground(new Color(245, 245, 245));
		panelTK.setBounds(30, 23, 439, 441);
		panel_1.add(panelTK);
		panelTK.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("H\u00F4m nay");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNewLabel.setBounds(523, 52, 117, 37);
		panel_1.add(lblNewLabel);
		
		JLabel lblThngNy = new JLabel("Th\u00E1ng n\u00E0y");
		lblThngNy.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblThngNy.setBounds(796, 52, 150, 37);
		panel_1.add(lblThngNy);
		
		JLabel lblQuNy = new JLabel("Qu\u00FD n\u00E0y");
		lblQuNy.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblQuNy.setBounds(523, 277, 117, 37);
		panel_1.add(lblQuNy);
		
		JLabel lblNmNy = new JLabel("N\u0103m n\u00E0y");
		lblNmNy.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblNmNy.setBounds(796, 277, 117, 37);
		panel_1.add(lblNmNy);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new MatteBorder(6, 4, 1, 1, (Color) new Color(47, 79, 79)));
		panel_2.setBackground(SystemColor.menu);
		panel_2.setBounds(783, 83, 227, 157);
		panel_1.add(panel_2);
		
		JLabel lblNhanThang = new JLabel("");
		lblNhanThang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelTK.removeAll();
				ThongkeThang TKthang= new ThongkeThang();
				TKthang.setBounds(0,0,439,441);
				panelTK.add(TKthang);	
				repaint();
				revalidate();
				
			}
		});
		lblNhanThang.setBounds(0, 0, 227, 157);
		panel_2.add(lblNhanThang);
		
		JLabel lblNewLabel_1_1 = new JLabel("T\u1ED5ng thu");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(59, 32, 107, 37);
		panel_2.add(lblNewLabel_1_1);
		
		lblTthang = new JLabel("000000000");
		lblTthang.setHorizontalAlignment(SwingConstants.CENTER);
		lblTthang.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblTthang.setBounds(42, 80, 143, 45);
		panel_2.add(lblTthang);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new MatteBorder(6, 4, 1, 1, (Color) new Color(47, 79, 79)));
		panel_3.setBackground(SystemColor.menu);
		panel_3.setBounds(508, 307, 227, 157);
		panel_1.add(panel_3);
		
		lblNhanQuy = new JLabel("");
		lblNhanQuy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelTK.removeAll();
				ThongkeQuy TKquy= new ThongkeQuy();
				TKquy.setBounds(0,0,439,441);
				panelTK.add(TKquy);	
				repaint();
				revalidate();
				
			}
		});
		lblNhanQuy.setBounds(0, 0, 227, 157);
		panel_3.add(lblNhanQuy);
		
		JLabel lblNewLabel_1_2 = new JLabel("T\u1ED5ng thu");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_2.setBounds(59, 32, 107, 37);
		panel_3.add(lblNewLabel_1_2);
		
		lblTquy = new JLabel("000000000");
		lblTquy.setHorizontalAlignment(SwingConstants.CENTER);
		lblTquy.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblTquy.setBounds(42, 80, 143, 45);
		panel_3.add(lblTquy);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBorder(new MatteBorder(6, 4, 1, 1, (Color) new Color(47, 79, 79)));
		panel_4.setBackground(SystemColor.menu);
		panel_4.setBounds(783, 307, 227, 157);
		panel_1.add(panel_4);
		
		JLabel lblNhanNam = new JLabel("");
		lblNhanNam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelTK.removeAll();
				ThongkeNam TKnam= new ThongkeNam();
				TKnam.setBounds(0,0,439,441);
				panelTK.add(TKnam);	
				repaint();
				revalidate();
			}
		});
		lblNhanNam.setBounds(0, 0, 227, 157);
		panel_4.add(lblNhanNam);
		
		JLabel lblNewLabel_1_3 = new JLabel("T\u1ED5ng thu");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1_3.setBounds(59, 32, 107, 37);
		panel_4.add(lblNewLabel_1_3);
		
		lblNam = new JLabel("000000000");
		lblNam.setHorizontalAlignment(SwingConstants.CENTER);
		lblNam.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNam.setBounds(42, 80, 143, 45);
		panel_4.add(lblNam);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUAN_NET;user=sa;password=Son862001");
		    
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		Tongdoanhthu();
		panelTK.removeAll();
		thongkeNgay thongkeNgay= new thongkeNgay();
		thongkeNgay.setBounds(0,0,439,441);
		panelTK.add(thongkeNgay);
		
	
		
		
	}
	
	
	public void Tongdoanhthu() {
		Locale locale =new Locale("vi","VN");
		NumberFormat format = NumberFormat.getCurrencyInstance(locale);
		
		
		
		try {
			PreparedStatement pstm = conn.prepareStatement("select dbo.TongdoanhThuNgay() 'a'");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String x= format.format(rs.getInt("a")) ;
					lblTngay.setText(x);
			
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement pstm = conn.prepareStatement("select dbo.TongdoanhThuThang() 'a'");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String x= format.format(rs.getInt("a")) ;
					lblTthang.setText(x);
			
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement pstm = conn.prepareStatement("select dbo.TongDoanhthuQuy() 'a'");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String x= format.format(rs.getInt("a")) ;
					lblTquy.setText(x);
			
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			PreparedStatement pstm = conn.prepareStatement("select dbo.TongdoanhThuNam() 'a'");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String x= format.format(rs.getInt("a")) ;
					lblNam.setText(x);
			
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	
	
}


