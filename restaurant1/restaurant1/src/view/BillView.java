package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import constants.Constant;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javax.swing.JComboBox;
public class BillView extends JFrame {

	private JPanel cpBill;
	private JTable table;
	private JTable table_1;
	private JTextField txtTime;
	private JTextField txtCodeBill;
	Connection conn = null;
	private JTextField txtTotal;
	private String name;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillView billView = new BillView();
					billView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public BillView() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\QuanLyNhaHang\\nhahang.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100,908, 593);
		cpBill = new JPanel();
		cpBill.setBackground(new Color(176, 211, 238));
		cpBill.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(cpBill);
		cpBill.setLayout(null);
		
		JLabel lblBill = new JLabel("HÓA ĐƠN");
		lblBill.setBounds(403, 11, 112, 27);
		cpBill.add(lblBill);
		lblBill.setForeground(new Color(17, 82, 96));
		lblBill.setFont(new Font("Sitka Subheading", Font.BOLD, 21));
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ManageView().setVisible(true);
				dispose();
			}
		});
		btnHome.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		btnHome.setBounds(0, 8, 94, 23);
		cpBill.add(btnHome);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 87, 555, 171);
		cpBill.add(scrollPane);
		
		
		table_1 = new JTable();
		table_1.setBounds(0, 0, 1, 1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				
			},
			new String[] {
					"Tên món  ", "Giá bán  ", "Số lượng   ", "Tổng tiền  "
			}
		));
		scrollPane.setViewportView(table_1);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(48, 355, 805, 199);
		cpBill.add(scrollPane2);
		
		
		JTable table_2 = new JTable();
		table_2.setBounds(0, 0, 1, 1);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				
				
			},
			new String[] {
				"Mã hóa đơn ", "ID bàn  ", "Thời gian  ", "Tổng tiền ", "ID nhân viên"
			}
		));
		scrollPane2.setViewportView(table_2);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTime.setBounds(599, 9, 46, 14);
		cpBill.add(lblTime);
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setBounds(655, 7, 129, 24);
		cpBill.add(txtTime);
		txtTime.setColumns(10);
		
            Timer timer;
	        ActionListener actionListener = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	 java.util.Date date = new java.util.Date(); 
	                DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                String formattedDateTime = dateTimeFormat.format(date);
	                txtTime.setText(formattedDateTime);  
	            }
	        };
	        timer = new Timer(1000, actionListener);
	        timer.setInitialDelay(0);
	        timer.start();
 
    	 JLabel lblCode_bill = new JLabel("Mã hóa đơn");
    		lblCode_bill.setFont(new Font("Tahoma", Font.PLAIN, 14));
    		lblCode_bill.setBounds(633, 71, 110, 24);
    		cpBill.add(lblCode_bill);
    		
    		txtCodeBill = new JTextField();
    		txtCodeBill.setBounds(725, 74, 86, 21);
    		cpBill.add(txtCodeBill);
    		txtCodeBill.setColumns(10);
    		
    		JButton btnOk = new JButton("OK");
    		btnOk.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				String code_bill = txtCodeBill.getText();
    				double totalSum = 0.000;

    				if (!code_bill.isEmpty()) {
    				    try {
    				    	 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);		
    				        Statement statement = conn.createStatement();
    				        String sql = "SELECT `T_FoodName`, `F_Price`, `I_Number`, `F_Total` FROM ta_vip_billdetail WHERE I_CodeBill = " + code_bill;
    				        ResultSet resultSet = statement.executeQuery(sql);
    				        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
    				        model.setRowCount(0);

    				        while (resultSet.next()) {
    				            Object[] rowData = new Object[model.getColumnCount()];
    				            for (int i = 0; i < rowData.length; i++) {
    				                rowData[i] = resultSet.getObject(i + 1);
    				                if (i == 3) { 
    				                    Object value = rowData[i];
    				                    if (value instanceof Double) {
    				                        totalSum += (double) value;
    				                    }
    				                }
    				            }
    				            model.addRow(rowData);
    				        }
                              txtTotal.setText(Double.toString(totalSum));

    				        statement.close();
    				        conn.close();
    				    } catch (SQLException ex) {
    				        ex.printStackTrace();
    				    }
    				}

    				}
    		});
    		btnOk.setBounds(812, 73, 70, 23);
    		cpBill.add(btnOk);
    		
    		JLabel lblTotal = new JLabel("Tổng hóa đơn ");
    		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
    		lblTotal.setBounds(633, 134, 94, 23);
    		cpBill.add(lblTotal);
    		
    		txtTotal = new JTextField();
    		txtTotal.setEditable(false);
    		txtTotal.setColumns(10);
    		txtTotal.setBounds(725, 137, 86, 21);
    		cpBill.add(txtTotal);
    		
    		JButton btnPay = new JButton("Thanh toán");
    		btnPay.setFont(new Font("Tahoma", Font.PLAIN, 13));
    		btnPay.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				String codebill = txtCodeBill.getText();
    				String total = txtTotal.getText();
    				try {
    					DefaultTableModel model = (DefaultTableModel) table_2.getModel();
				    	 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);		
    		             String sql = " UPDATE `restaurant8`.`ta_vip_bill` "
				            		+ "SET `F_Total` = '"+ total +"'  WHERE (`I_CodeBill` = '"+codebill+"')";
    		             PreparedStatement pstmt = conn.prepareStatement(sql);
    		             pstmt.executeUpdate();
    		             
    		         	sql = "select * from ta_vip_bill";
						ResultSet rs = pstmt.executeQuery(sql);
					    model = (DefaultTableModel) table_2.getModel();
						model.setRowCount(0);
						while (rs.next()) {
							model.addRow(new Object[] { rs.getString("I_CodeBill"),rs.getString("I_IDTable"), rs.getString("D_Date"),
									rs.getString("F_Total"), rs.getString("I_CodeStaff")});
						};
    			}catch (Exception ex) {
					// TODO: handle exception
    				ex.printStackTrace();
				}}
    		});
    		btnPay.setBounds(316, 291, 112, 27);
    		cpBill.add(btnPay);
    		
    		JLabel lblNewLabel = new JLabel("Chi tiết hóa đơn ");
    		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 17));
    		lblNewLabel.setBounds(262, 60, 179, 23);
    		cpBill.add(lblNewLabel);
    		
    		try {
				DefaultTableModel model = (DefaultTableModel) table_2.getModel();
		    	 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);		
	            Statement stmt = conn.createStatement();
				String sql = "select * from ta_vip_bill";
				ResultSet rs = stmt.executeQuery(sql);
				model.setRowCount(0);
				model.setRowCount(0);
				 while (rs.next()) {
					 model.addRow(new Object[] { rs.getString("I_CodeBill"), rs.getString("I_IDTable"),
							 rs.getString("D_Date"), rs.getDouble("F_Total"),rs.getInt(("I_CodeStaff"))
		                		  });
		            
				}
				;
//	            conn.close();
	        } catch (SQLException e1) {
	        	JOptionPane.showMessageDialog(null, e1.getMessage());
	        }
	
}
	}
