package thuNghiem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 750);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(197, 255, 410, 203);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("ADMIN");
		btnNewButton.setIcon(new ImageIcon(getClass().getResource("/image/panda (3).png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangNhapAdmin ad = new dangNhapAdmin();
				ad.setVisible(true);
		
				
			}
		});
		btnNewButton.setForeground(new Color(255, 250, 240));
		btnNewButton.setBackground(new Color(0, 128, 128));
		btnNewButton.setFont(new Font("Segoe UI Semilight", Font.BOLD, 20));
		btnNewButton.setBounds(20, 60, 138, 62);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("M\u1EDDi ch\u1ECDn \u0111\u1ED1i t\u01B0\u1EE3ng qu\u1EA3n l\u00FD :");
		lblNewLabel_2.setBounds(85, 0, 261, 63);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		
		JButton btnNgiChi = new JButton("NG\u01AF\u1EDCI CH\u01A0I");
		btnNgiChi.setIcon(new ImageIcon(getClass().getResource("/image/chick (1).png")));
		btnNgiChi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangNhapNguoiChoi nc = new dangNhapNguoiChoi();
				nc.setVisible(true);
				
			}
		});
		btnNgiChi.setForeground(new Color(255, 250, 240));
		btnNgiChi.setFont(new Font("Segoe UI Semilight", Font.BOLD, 20));
		btnNgiChi.setBackground(new Color(0, 128, 128));
		btnNgiChi.setBounds(179, 97, 208, 62);
		panel.add(btnNgiChi);
		
		JLabel lblNewLabel_3_1 = new JLabel("Qu\u00E1n n\u00E9t Animals");
		lblNewLabel_3_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3_1.setForeground(Color.BLACK);
		lblNewLabel_3_1.setFont(new Font("Forte", Font.PLAIN, 53));
		lblNewLabel_3_1.setBounds(215, 126, 432, 76);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("K\u00EAnh gi\u1EDBi thi\u1EC7u \u0111\u1ED9ng v\u1EADt l\u1EDBn nh\u1EA5t c\u1EA3 n\u01B0\u1EDBc");
		lblNewLabel_3_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3_1_1.setForeground(Color.BLACK);
		lblNewLabel_3_1_1.setFont(new Font("Monospaced", Font.ITALIC, 16));
		lblNewLabel_3_1_1.setBounds(215, 195, 483, 50);
		contentPane.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getResource("/image/cartoon-prehistoric-nature-frame-dinosaurs-beautiful-colorful-illustration-children-different-usage-fairy-92965874.jpg")));
		lblNewLabel.setBounds(0, -44, 946, 800);
		contentPane.add(lblNewLabel);
	}
}
