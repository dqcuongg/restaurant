package manage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import constants.Constant;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class Revenue extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField id;
	Connection conn = null;
	private JTextField txtTotal;
	private JComboBox cbbDay;
	private JRadioButton rdbtnDay;
	private JRadioButton rdbtnMonth;
	private JComboBox cbbMonth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Revenue frame = new Revenue();
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
	public Revenue()  {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\phamk\\Downloads\\nhahang.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100,908, 539);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 211, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 202, 845, 275);
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		String[] Month = {"1 ", "2 ","3","4","5","6","7","8","9","10","11","12"};
		final JComboBox cbbMonth = new JComboBox(Month);
		cbbMonth.setBounds(443, 86, 53, 25);
		contentPane.add(cbbMonth);
        ButtonGroup buttonGroup = new ButtonGroup();
		
		JRadioButton rdbtnDay = new JRadioButton("Ngày ");
		rdbtnDay.setBounds(281, 86, 73, 23);
		rdbtnDay.setBackground(new Color(176, 211, 238));
		contentPane.add(rdbtnDay);
		
		String[] day = {"1 ", "2 ","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17"
				,"18","19","20","21","22","23","24","25","26","27","28","29","30","31"}; 
		final JComboBox cbbDay = new JComboBox(day);
		cbbDay.setBounds(354, 86, 53, 25);
		contentPane.add(cbbDay);
		
		buttonGroup.add(rdbtnDay);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 4, 845, 271);
		panel.add(scrollPane);
		
		table= new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
					
					
				},
			new String[] {
					"Mã hóa đơn "," Mã bàn ăn ", "Ngày thanh toán   ", "Tổng tiền", "Tên nhân viên  "
			}
		));
		scrollPane.setViewportView(table);
		
	
		
		JLabel lblRevenue = new JLabel("Doanh thu");
		lblRevenue.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		lblRevenue.setBounds(318, 31, 129, 33);
		contentPane.add(lblRevenue);
		
		JButton btnSearch = new JButton("Tra cứu");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 if (rdbtnDay.isSelected()) {
				        String month = (String) cbbMonth.getSelectedItem();
				        String day = (String) cbbDay.getSelectedItem();	                   
				        double total=0.000;				     
				        String Total;
				        
				        try {
				            DefaultTableModel model = (DefaultTableModel) table.getModel();
				            conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);
				            Statement stmt = conn.createStatement();

//				            String sql = "SELECT SUM(F_Total) FROM ta_vip_bill WHERE DAY(D_Date) = " + day + " AND MONTH(D_Date) = " + month;
				           String sql = "SELECT * FROM ta_vip_bill WHERE DAY(D_Date) = " + day + " AND MONTH(D_Date) = " + month;
				            ResultSet rs = stmt.executeQuery(sql);
				            model.setRowCount(0);

				            while (rs.next()) {
				               
				            	 model.addRow(new Object[] { rs.getString("I_CodeBill"), rs.getString("I_IDTable"),
										 rs.getString("D_Date"), rs.getDouble("F_Total"),rs.getInt(("I_CodeStaff"))
					                		  
				                });
				            }
				        } catch (SQLException ex) {
				            ex.getMessage();
				            System.out.println(ex.getMessage());
				            JOptionPane.showMessageDialog(null, ex.getMessage());
				        }

						
				    }  else {
		                	JOptionPane.showMessageDialog(Revenue.this, "vui lòng chọn để thống kê.", "Error", JOptionPane.ERROR_MESSAGE);

		                	
		                }
	                 
	                
			}
		});
		btnSearch.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		btnSearch.setBounds(367, 145, 96, 33);
		contentPane.add(btnSearch);
		

		
		
//		JLabel lbltTotal = new JLabel("Tổng tiền");
//		lbltTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		lbltTotal.setBounds(652, 90, 64, 18);
//		contentPane.add(lbltTotal);
//		
//		txtTotal = new JTextField();
//		txtTotal.setBounds(714, 90, 109, 20);
//		contentPane.add(txtTotal);
//		txtTotal.setColumns(10);
//		
//		JLabel lblVND = new JLabel("VND");
//		lblVND.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		lblVND.setBounds(833, 90, 43, 18);
//		contentPane.add(lblVND);
		
		JLabel lblMonth = new JLabel("Tháng ");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMonth.setBounds(406, 86, 46, 23);
		contentPane.add(lblMonth);
		
		
		try {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
       	   conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	
            Statement stmt = conn.createStatement();
			String sql = "select * from ta_vip_bill";
			ResultSet rs = stmt.executeQuery(sql);
			model.setRowCount(0);

			 while (rs.next()) {
				 model.addRow(new Object[] { rs.getString("I_CodeBill"), rs.getString("I_IDTable"),
						 rs.getString("D_Date"), rs.getDouble("F_Total"),rs.getInt(("I_CodeStaff"))
	                		  });
	            
			}
			;
//            conn.close();
        } catch (SQLException e1) {
        	JOptionPane.showMessageDialog(null, e1.getMessage());
        }
		
		
		
		
	}
}
