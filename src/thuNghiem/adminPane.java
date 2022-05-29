package thuNghiem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;
import javax.swing.JMenu;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import rojerusan.RSMetroTextPlaceHolder;
//import rojerusan.RSMaterialButtonCircleBeanInfo;
//import rojerusan.RSMaterialButtonCircle;
//import rojerusan.RSPasswordTextPlaceHolder;
import rojerusan.RSFotoCircleBeanInfo;
import rojerusan.RSButtonTriangle;

public class adminPane extends JFrame {

	
	
	private JPanel contentPane;
	private JPanel panelQLMT;
	private JLabel lblQLMT;
	private JLabel lblQLKH;
	private JLabel lblTK;
	private JPanel panelTK;
	private JPanel panelQLKH;
	private JPanel panelMenu;
	private JLabel lblLoi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminPane frame = new adminPane();
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
	public adminPane() {}
	
	public adminPane(String userName) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1319, 675);
		setLocationRelativeTo(null); 
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(170,177,180));
		panel.setBounds(5, 0, 1303, 134);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setFont(new Font("Brush Script MT", Font.ITALIC, 29));
		lblNewLabel_3.setIcon(new ImageIcon(getClass().getResource("/image/r1.png")));
		lblNewLabel_3.setBounds(492, 0, 112, 134);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Qu\u00E1n n\u00E9t Animals");
		lblNewLabel_3_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3_1.setFont(new Font("Forte", Font.PLAIN, 50));
		lblNewLabel_3_1.setBounds(614, 25, 458, 50);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("K\u00EAnh gi\u1EDBi thi\u1EC7u \u0111\u1ED9ng v\u1EADt l\u1EDBn nh\u1EA5t c\u1EA3 n\u01B0\u1EDBc");
		lblNewLabel_3_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3_1_1.setForeground(Color.BLACK);
		lblNewLabel_3_1_1.setFont(new Font("Monospaced", Font.ITALIC, 16));
		lblNewLabel_3_1_1.setBounds(597, 81, 483, 50);
		panel.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/image/google-panda-circular-symbol.png")));
		lblNewLabel_1.setBounds(40, 0, 135, 134);
		panel.add(lblNewLabel_1);
		
		RSButtonTriangle buttonTriangle = new RSButtonTriangle();
		buttonTriangle.setColorHover(Color.GRAY);
		buttonTriangle.setBackground(Color.BLACK);
		buttonTriangle.setBorder(null);
		
		buttonTriangle.setBounds(143, 112, 16, 16);
		panel.add(buttonTriangle);
		
		JPopupMenu popupMenu_1 = new JPopupMenu();
		addPopup(buttonTriangle, popupMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Đổi mật khẩu");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoiMkAdmin doiMk= new DoiMkAdmin(userName);
				doiMk.setVisible(true);
			}
		});
		
		popupMenu_1.add(mntmNewMenuItem_2);
		
		JSeparator separator_2 = new JSeparator();
		popupMenu_1.add(separator_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Đăng xuất");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int a =JOptionPane.showConfirmDialog(lblLoi,"Chắc chắn muốn đăng xuất?");
				if(a== JOptionPane.YES_OPTION) {
					dispose();
				
				}
				
			}
		});
		
		
		popupMenu_1.add(mntmNewMenuItem_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(3, 0, 1, 3, (Color) new Color(192, 192, 192)));
		panel_1.setBackground(new Color(100, 136, 153));
		panel_1.setBounds(5, 137, 251, 496);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		panelQLKH = new JPanel();
		panelQLKH.setLayout(null);
		panelQLKH.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(192, 192, 192)));
		panelQLKH.setBackground(new Color(100, 136, 153));
		panelQLKH.setBounds(0, 131, 251, 75);
		panel_1.add(panelQLKH);
		
		lblQLKH = new JLabel("Qu\u1EA3n l\u00FD kh\u00E1ch h\u00E0ng");
		lblQLKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelQLMT.setBackground(new Color(100,136,153));
				panelQLKH.setBackground(new Color(70,130,180));
				panelTK.setBackground(new Color(100,136,153));
				
				panelMenu.removeAll();
				QLKH TrinhQLKH = new QLKH();
				TrinhQLKH.setBounds(0,0,1043,487);
				panelMenu.add(TrinhQLKH);
				repaint();
				revalidate();
				
				
			}
		});
		lblQLKH.setIconTextGap(7);
		lblQLKH.setIcon(new ImageIcon(getClass().getResource("/image/user.png")));
		lblQLKH.setToolTipText("");
		lblQLKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQLKH.setBounds(10, 8, 241, 55);
		panelQLKH.add(lblQLKH);
		
		panelTK = new JPanel();
		panelTK.setLayout(null);
		panelTK.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(192, 192, 192)));
		panelTK.setBackground(new Color(100, 136, 153));
		panelTK.setBounds(0, 216, 251, 75);
		panel_1.add(panelTK);
		
		lblTK = new JLabel("Th\u1ED1ng k\u00EA\r\n");
		lblTK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelQLMT.setBackground(new Color(100,136,153));
				panelQLKH.setBackground(new Color(100,136,153));
				panelTK.setBackground(new Color(70,130,180));
				
				panelMenu.removeAll();
				ThongKe TK =new ThongKe();
				TK.setBounds(0,0,1043,487);
				panelMenu.add(TK);
				repaint();
				revalidate();
				
			}
		});
		lblTK.setIconTextGap(9);
		lblTK.setIcon(new ImageIcon(getClass().getResource("/image/file.png")));
		lblTK.setToolTipText("");
		lblTK.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTK.setBounds(10, 8, 241, 55);
		panelTK.add(lblTK);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(147, 112, 219));
		panel_4.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		panel_4.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_4.setBounds(13, 121, 225, 2);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		panelQLMT = new JPanel();
		
		panelQLMT.setBounds(0, 46, 251, 75);
		panel_1.add(panelQLMT);
		panelQLMT.setBorder(new MatteBorder(0, 0, 0, 3, (Color) new Color(192, 192, 192)));
		panelQLMT.setBackground(new Color(70,130,180));
		panelQLMT.setLayout(null);
		
		lblQLMT = new JLabel("Qu\u1EA3n l\u00FD m\u00E1y tr\u1EA1m");
		lblQLMT.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelQLMT.setBackground(new Color(70,130,180));
				panelQLKH.setBackground(new Color(100,136,153));
				panelTK.setBackground(new Color(100,136,153));
				
				panelMenu.removeAll();
				QLMT TrinhQLMT = new QLMT();
				TrinhQLMT.setBounds(0,0,1043,487);
				panelMenu.add(TrinhQLMT);
				repaint();
				revalidate();
			}
		});
		lblQLMT.setBounds(10, 10, 241, 55);
		lblQLMT.setBackground(new Color(0, 0, 0));
		lblQLMT.setIcon(new ImageIcon(getClass().getResource("/image/computer (4).png")));
		lblQLMT.setToolTipText("");
		lblQLMT.setIconTextGap(8);
		lblQLMT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelQLMT.add(lblQLMT);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setLayout(null);
		panel_4_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		panel_4_1.setBackground(new Color(255, 255, 255));
		panel_4_1.setAlignmentX(1.0f);
		panel_4_1.setBounds(13, 206, 225, 2);
		panel_1.add(panel_4_1);
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setBounds(10, 294, 225, 2);
		panel_1.add(panel_4_1_1);
		panel_4_1_1.setLayout(null);
		panel_4_1_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		panel_4_1_1.setBackground(new Color(255, 255, 255));
		panel_4_1_1.setAlignmentX(1.0f);
		
		panelMenu = new JPanel();
		panelMenu.setBorder(new MatteBorder(3, 2, 2, 2, (Color) new Color(169, 169, 169)));
		panelMenu.setBounds(261, 144, 1043, 487);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		QLMT TrinhQLMT = new QLMT();
		TrinhQLMT.setBounds(0,0,1043,487);
		panelMenu.add(TrinhQLMT);
		
		lblLoi = new JLabel("");
		lblLoi.setBounds(480, 194, 45, 13);
		TrinhQLMT.add(lblLoi);
		repaint();
		revalidate();
		
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
