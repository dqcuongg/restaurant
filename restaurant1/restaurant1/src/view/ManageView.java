package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class ManageView extends JFrame {
	private JLabel lblName;
	private String name;
	private String dataContructor;
	private String code;
	
	
/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 new ManageView(). setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	



	public ManageView() {
		this.setTitle("Quản lý nhà hàng");

				setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
				setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\QuanLyNhaHang\\nhahang.jpg"));
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setBounds(300, 100,908, 539);
				JPanel cpManage = new JPanel();
				cpManage.setBackground(new Color(176, 211, 238));
				cpManage.setBorder(new EmptyBorder(5, 5, 5, 5));

				setContentPane(cpManage);
				cpManage.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBounds(44, 76, 173, 392);
				panel.setBackground(new Color(255, 255, 255));
				cpManage.add(panel);
				panel.setLayout(null);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBackground(new Color(35, 84, 120));
				panel_1.setBounds(0, 0, 173, 392);
				panel.add(panel_1);
				panel_1.setLayout(null);
				
				JButton btnDisplay = new JButton("Trang chủ  ");
				btnDisplay.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new DisplayView().setVisible(true);
						dispose();
					}
				});
				btnDisplay.setBounds(35, 63, 98, 25);
				btnDisplay.setBackground(new Color(255, 255, 255));
				btnDisplay.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
				btnDisplay.setForeground(new Color(35, 84, 120));
				panel_1.add(btnDisplay);
				
				JButton btnBill = new JButton("Hóa đơn");
				btnBill.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new BillView().setVisible(true);
						dispose();
					}
				});
				btnBill.setForeground(new Color(35, 84, 120));
				btnBill.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
				btnBill.setBackground(Color.WHITE);
				btnBill.setBounds(35, 306, 98, 25);
				panel_1.add(btnBill);
				
				JButton btnTable = new JButton("Bàn");
				btnTable.setBounds(35, 151, 98, 25);
				panel_1.add(btnTable);
				btnTable.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new TableView().setVisible(true);
						dispose();
					}
				});
				btnTable.setForeground(new Color(35, 84, 120));
				btnTable.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
				btnTable.setBackground(Color.WHITE);
				
				JButton btnFood = new JButton("Món ăn");
				btnFood.setBounds(35, 231, 98, 25);
				panel_1.add(btnFood);
				btnFood.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new FoodView(code).setVisible(true);
						dispose();
					}
				});
				btnFood.setForeground(new Color(35, 84, 120));
				btnFood.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
				btnFood.setBackground(Color.WHITE);
				
				JLabel lblManage = new JLabel("Quản lý nhà hàng ");
				lblManage.setForeground(new Color(0, 64, 128));
				lblManage.setFont(new Font("Sitka Subheading", Font.BOLD, 27));
				lblManage.setBounds(338, 11, 290, 45);
				cpManage.add(lblManage);
				
				JPanel panel_2 = new JPanel();
				panel_2.setBackground(new Color(255, 255, 255));
				panel_2.setBounds(218, 76, 642, 392);
				cpManage.add(panel_2);
				panel_2.setLayout(null);

				
				
			}
			
}