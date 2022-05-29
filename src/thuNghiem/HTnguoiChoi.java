package thuNghiem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Frame;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Window.Type;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class HTnguoiChoi extends JFrame {

	private JPanel contentPane;
	private JLabel lblLoi;
	private Connection conn=null;
	private JLabel lblSTN;
	private JLabel lblHiengio;
	private int ST =0;
	private String M1="Máy 1";
	private int TT=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					HTnguoiChoi frame = new HTnguoiChoi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * 
	 */
	public HTnguoiChoi() {}
	public HTnguoiChoi(String name) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		
		setBounds(100, 100, 584, 742);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(220, 220, 220));
		panel.setBounds(0, 0, 570, 715);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 4, 4, 4, (Color) new Color(0, 139, 139)));
		panel_1.setBackground(new Color(245, 245, 245));
		panel_1.setBounds(35, 184, 477, 332);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("S\u1ED0 TI\u1EC0N C\u00D2N L\u1EA0I :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(48, 65, 156, 54);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("S\u1ED0 GI\u1EDC C\u00D2N L\u1EA0I :");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(48, 171, 156, 54);
		panel_1.add(lblNewLabel_2_1);
		
		lblSTN = new JLabel("0000000000");
		lblSTN.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblSTN.setBounds(234, 65, 156, 54);
		panel_1.add(lblSTN);
		
		lblHiengio = new JLabel("00:00:00");
		lblHiengio.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblHiengio.setBounds(234, 171, 156, 54);
		panel_1.add(lblHiengio);
		
		lblLoi = new JLabel("");
		lblLoi.setBounds(171, 137, 45, 13);
		panel_1.add(lblLoi);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(3, 3, 1, 3, (Color) new Color(47, 79, 79)));
		panel_2.setBackground(new Color(255, 255, 240));
		panel_2.setBounds(52, 126, 194, 156);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("M\u00C1Y 1");
		lblNewLabel_1.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
		lblNewLabel_1.setBounds(52, 10, 108, 47);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Qu\u00E1n net Animals");
		lblNewLabel.setForeground(new Color(0, 100, 0));
		lblNewLabel.setFont(new Font("Forte", Font.ITALIC, 35));
		lblNewLabel.setBounds(133, 26, 303, 83);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u0110\u0103ng xu\u1EA5t");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int a=JOptionPane.showConfirmDialog(lblLoi, "Bạn muốn đăng xuất?");
				if(a== JOptionPane.YES_OPTION) {
					try {
						PreparedStatement pstm1 = conn.prepareStatement("update QLMayTinh set status = 0 where mayid = ?");
						pstm1.setString(1, M1);
						
						pstm1.executeUpdate();
				
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
					
					
					
				}
			}
		});
		btnNewButton.setBorder(new LineBorder(new Color(128, 128, 128)));
		btnNewButton.setBackground(new Color(169, 169, 169));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(52, 573, 151, 60);
		panel.add(btnNewButton);
		
		JButton btniMtKhu = new JButton("\u0110\u1ED5i m\u1EADt kh\u1EA9u");
		btniMtKhu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoiMKNguoiChoi nc = new DoiMKNguoiChoi(name);
				nc.setVisible(true);
				dispose();
			}
		});
		btniMtKhu.setBorder(new LineBorder(new Color(128, 128, 128)));
		btniMtKhu.setBackground(new Color(216, 191, 216));
		btniMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btniMtKhu.setBounds(290, 573, 151, 60);
		panel.add(btniMtKhu);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUAN_NET;user=sa;password=Son862001");
		    
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		try {
			PreparedStatement pstm1 = conn.prepareStatement("update QLMayTinh set status = 1, MbName = ? where mayid = ?");
			pstm1.setString(1,name );
			pstm1.setString(2, M1);
			
			pstm1.executeUpdate();
			
			
	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	    	
	        @Override
	        public void run() {
	        	truycap(name);
	        	if(TT==1) {
	        		
	        	
	        	ST=ST-3;
	        	if(ST>0) {
	        	try {
	    			PreparedStatement pstm1 = conn.prepareStatement("update AcountMember set SoTienNap = ? where Mbname = ?");
	    			pstm1.setInt(1, ST);
	    			pstm1.setString(2, name);
	 
	    			pstm1.executeUpdate();
	    	
	    		} catch (SQLException e1) {
	    			// TODO Auto-generated catch block
	    			e1.printStackTrace();
	    		}
	         
	           
	        	}
	        	else dispose();
	        }
	        }
	      
	    }, 0, 1, TimeUnit.SECONDS);
	}
	
	
	//////
	
	public void truycap(String nc) {
		
		
		String STN = new String();
		try {
			PreparedStatement pstm2 = conn.prepareStatement("select sotiennap from AcountMember where MbName = ?");
			pstm2.setString(1,nc );
			
			ResultSet rs = pstm2.executeQuery();
			if(rs.next()) {
				ST=rs.getInt("SoTienNap");
				STN = String.valueOf(ST);
				lblSTN.setText(STN);
				
				
			}
	
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		

	// truy váº¥n vĂ o mĂ¡y tĂ­nh
		
		///////////////////////////
			
		try {
			PreparedStatement pstm1 = conn.prepareStatement("select status from QLMayTinh  where mayid = ?");
			pstm1.setString(1, M1);
			ResultSet rs = pstm1.executeQuery();
			if(rs.next()) {
				TT=rs.getInt("status");
			}
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		/// náº¿u mĂ¡y chá»§ táº¯t tháº±ng nĂ y
		if(TT==0) {
			try {
				PreparedStatement pstm1 = conn.prepareStatement("update QLMayTinh set Mbname = NULL where mayid = ?");
	    			
				
				pstm1.setString(1, M1);
	    			pstm1.executeUpdate();
	    	
	    		} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
	    		}
			dispose();
		}int gio,phut;
		float time;
		time= (float)ST/10000;
		gio=(int)time;
		time=time-gio;
		time=time*60;
		phut= (int)time;
		String hiengio=String.valueOf(gio)+":"+String.valueOf(phut);
		lblHiengio.setText(hiengio);
	}
	
	
	
	
	
}
