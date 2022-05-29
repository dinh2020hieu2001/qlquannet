package thuNghiem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChiTietMay extends JFrame {

	private JPanel contentPane;
	private Connection conn=null;
	private JLabel lblName;
	private JLabel lblMoney;
	private JLabel lblTime;
	private int ST =0;
	private String name = new String();
	private JLabel lblLoi;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiTietMay frame = new ChiTietMay();
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
	public ChiTietMay() {}
	public ChiTietMay(String LmayID) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Chi ti\u1EBFt m\u00E1y tr\u1EA1m");
		setBounds(100, 100, 497, 631);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(220, 220, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(LmayID);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Stencil", Font.PLAIN, 62));
		lblNewLabel.setBounds(99, 36, 250, 80);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(29, 131, 425, 341);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("T\u00EAn ng\u01B0\u1EDDi d\u00F9ng : ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 35, 159, 50);
		panel.add(lblNewLabel_1);
		
		lblName = new JLabel("MEMBER1");
		lblName.setBorder(new LineBorder(new Color(169, 169, 169)));
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(167, 38, 223, 50);
		panel.add(lblName);
		
		JLabel lblNewLabel_1_1 = new JLabel("S\u1ED1 gi\u1EDD c\u00F2n l\u1EA1i :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(27, 219, 159, 50);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("S\u1ED1 ti\u1EC1n c\u00F2n l\u1EA1i :");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(20, 138, 159, 50);
		panel.add(lblNewLabel_1_1_1);
		
		lblTime = new JLabel("MEMBER1");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTime.setBorder(new LineBorder(new Color(169, 169, 169)));
		lblTime.setBounds(167, 219, 223, 50);
		panel.add(lblTime);
		
		lblMoney = new JLabel("MEMBER1");
		lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMoney.setBorder(new LineBorder(new Color(169, 169, 169)));
		lblMoney.setBounds(167, 138, 223, 50);
		panel.add(lblMoney);
		
		lblLoi = new JLabel("");
		lblLoi.setBounds(188, 98, 45, 13);
		panel.add(lblLoi);
		
		JButton btnNewButton = new JButton("T\u1EAFt m\u00E1y");
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int a =JOptionPane.showConfirmDialog(lblLoi,"Chắc chắn tắt máy?");
				if(a== JOptionPane.YES_OPTION) {
					
					
					
				try {
					PreparedStatement pstm = conn.prepareStatement("update QLMayTinh set status = 0	 where mayid = ? ");
					pstm.setString(1, LmayID);
					pstm.executeUpdate();
					dispose();
					JOptionPane.showMessageDialog(lblLoi,"Tắt máy thành công");
					
			} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
				
				
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(70, 130, 180));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnNewButton.setBounds(62, 508, 159, 57);
		contentPane.add(btnNewButton);
		
		JButton btnThot = new JButton("Tho\u00E1t");
		btnThot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnThot.setBackground(new Color(176, 196, 222));
		btnThot.setBorder(new LineBorder(new Color(169, 169, 169)));
		btnThot.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnThot.setBounds(272, 508, 159, 57);
		contentPane.add(btnThot);
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QUAN_NET;user=sa;password=Son862001");
		    
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
		try {
			PreparedStatement pstm2 = conn.prepareStatement("select * from QLMayTinh where MayID = ?");
			pstm2.setString(1,LmayID );
			
			ResultSet rs = pstm2.executeQuery();
			if(rs.next()) {
				name = rs.getString("MbName");	
			}
	
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		
		
		
		final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	    	
	        @Override
	        public void run() {
	        	
	        	
	        	
	    			
	           truycap(name);
	        	
	        	
	        }
	      
	    }, 0, 1, TimeUnit.SECONDS);
	}
		
		
	
	
	
	public void truycap(String name) {
		String STN = new String();
		lblName.setText(name);
		try {
			PreparedStatement pstm2 = conn.prepareStatement("select sotiennap from AcountMember where MbName = ?");
			pstm2.setString(1,name );
			
			ResultSet rs = pstm2.executeQuery();
			if(rs.next()) {
				ST=rs.getInt("SoTienNap");
				STN = String.valueOf(ST);
				lblMoney.setText(STN);
				
				
			}
	
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		
		int gio,phut,giay;
		float time;
		time= (float)ST/10000;
		
		
		gio=(int)time;
		time=time-gio;
		time=time*60;
		phut= (int)time;
		time=time-phut;
		time=time*60;
		giay=(int)time;
		
		String hiengio=String.valueOf(gio)+":"+String.valueOf(phut)+":"+String.valueOf(giay);
		lblTime.setText(hiengio);
	}
	
}
