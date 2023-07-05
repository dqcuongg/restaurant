package manage;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import constants.Constant;

import javax.swing.JTextArea;

public class Table extends JFrame {
	

	private JPanel contentPane;
	private JTextField txtTableName;
	private JComboBox cbbArea;
	private JComboBox cbbStatus;
	private JTable table;
	private JPanel panel;
	private JScrollPane scrollPane;
	 Connection conn = null;
	 private JTextField txtStatus;
	 private String idTable;
	 private JTextField txtID;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Table tableView = new Table();
					tableView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Table () {
		DefaultTableModel defaultTableModel;
		 setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\QuanLyNhaHang\\nhahang.jpg"));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(300, 100,908, 539);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(176, 211, 238));			
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblTable = new JLabel("Bàn");
			lblTable.setBounds(313, 11, 73, 32);
			contentPane.add(lblTable);
			lblTable.setForeground(new Color(21, 39, 162));
			lblTable.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
			
		
			JLabel lblStatus = new JLabel("Trạng thái ");
			lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblStatus.setBounds(554, 180, 73, 23);
			contentPane.add(lblStatus);
		
			JButton btnDelete = new JButton("Xóa");
			btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) {
					    String name = (String) table.getValueAt(selectedRow, 0);
					    
					    String sql = "DELETE FROM ta_vip_table WHERE T_TableName = ?";
					    try {
					    	 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	
					        PreparedStatement pstmt = conn.prepareStatement(sql);
					        pstmt.setString(1, name);
					        pstmt.executeUpdate();
					    } catch (SQLException ex) {
					        ex.printStackTrace();
					    }
					    
					    ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
														
				}else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một bàn để tạo hóa đơn.");
		        }
				}
				
			});
			btnDelete.setBounds(690, 233, 89, 23);
			contentPane.add(btnDelete);
		
			panel = new JPanel();
			panel.setBounds(23, 54, 520, 446);
			contentPane.add(panel);
			panel.setLayout(null);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 519, 439);
			panel.add(scrollPane);
			
		    table = new JTable();
			table.setBackground(new Color(255, 255, 255));
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"ID","Tên bàn", "Tr\u1EA1ng th\u00E1i "
				}
			));
			 scrollPane.setViewportView(table);
			 
			 table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				    @Override
				    public void valueChanged(ListSelectionEvent e) {
				        if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
				            int selectedRow = table.getSelectedRow();
				            Object id = table.getValueAt(selectedRow, 0);
				            Object name = table.getValueAt(selectedRow, 1);
				            Object status = table.getValueAt(selectedRow , 2);
				            txtTableName.setText(name.toString());
				            txtStatus.setText(status.toString());
				            txtID.setText(id.toString());
				        }
				    }
				});
			 
			 JLabel lblNameTable = new JLabel("ID ");
		     lblNameTable.setBounds(554, 86, 73, 23);
			 contentPane.add(lblNameTable);
			 
			 txtStatus = new JTextField();
			 txtStatus.setColumns(10);
			 txtStatus.setBounds(625, 182, 86, 20);
			 contentPane.add(txtStatus);
			 
			 txtTableName = new JTextField();
			 txtTableName.setBounds(625, 126, 86, 20);
			 contentPane.add(txtTableName);
			 txtTableName.setColumns(10);
			 
			 JLabel lbTableName = new JLabel("Tên bàn");
			 lbTableName.setFont(new Font("Tahoma", Font.PLAIN, 13));
			 lbTableName.setBounds(554, 129, 61, 14);
			 contentPane.add(lbTableName);
			 
			 JButton btnAdd = new JButton("Thêm bàn");
			 btnAdd.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		String tableName = txtTableName.getText();
			 		String status = txtStatus.getText();
			 		try {
			 			DefaultTableModel defaultTableModel;
			 			 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	
			            String sql = "INSERT INTO ta_vip_table (T_TableName,T_Status )" + "VALUES"
	            		+ "('" + tableName + "', '"+ status +"')";
			            PreparedStatement pstmt =conn.prepareStatement(sql);
			            pstmt.executeUpdate(sql);
			            sql = "select * from ta_vip_table";
						ResultSet rs = pstmt.executeQuery(sql);
					    defaultTableModel = (DefaultTableModel) table.getModel();
						defaultTableModel.setRowCount(0);
						while (rs.next()) {
							defaultTableModel.addRow(new Object[] { rs.getString("I_IDTable"),rs.getString("T_TableName"), rs.getString("T_Status")});
						};
			 		}catch (Exception ex) {
			 			ex.printStackTrace();
						// TODO: handle exception
					}
			 	}
			 });
			 btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
			 btnAdd.setBounds(577, 233, 89, 23);
			 contentPane.add(btnAdd);
			 
			 txtID = new JTextField();
			 txtID.setEditable(false);
			 txtID.setBounds(625, 87, 86, 20);
			 contentPane.add(txtID);
			 txtID.setColumns(10);
			 
			 
			 JButton btnHome = new JButton("Home");
				btnHome.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						new Manage().setVisible(true);
						dispose();
					}
				});
				btnHome.setFont(new Font("Tahoma", Font.PLAIN, 12));
				btnHome.setBounds(10, 11, 83, 23);
				contentPane.add(btnHome);
				
			
			try {
				defaultTableModel = (DefaultTableModel) table.getModel();
				 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	
	            Statement stmt = conn.createStatement();
				 String sql = "select * from ta_vip_table";
				ResultSet rs = stmt.executeQuery(sql);
				defaultTableModel.setRowCount(0);
				while (rs.next()) {
					defaultTableModel.addRow(new Object[] { rs.getString("I_IDTable"), rs.getString("T_TableName"), rs.getString("T_Status")  });
				}
				;
//	            conn.close();
	        } catch (SQLException e1) {
	        	JOptionPane.showMessageDialog(null, e1.getMessage());
	        }
			
		}
	}
