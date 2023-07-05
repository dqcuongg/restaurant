package view;


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

public class TableView extends JFrame {
	

	private JPanel contentPane;
	private JTextField txtNameTable;
	private JComboBox cbbArea;
	private JComboBox cbbStatus;
	private JTable table;
	private JPanel panelTable;
	private JScrollPane scrollPane;
	 Connection conn = null;
	 private JTextField txtStatus;
	 private String idTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableView tableView = new TableView();
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
	public TableView () {
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
			
			txtNameTable = new JTextField();
			txtNameTable.setEditable(false);
			txtNameTable.setBounds(415, 55, 86, 20);
			contentPane.add(txtNameTable);
			txtNameTable.setColumns(10);
			
			JLabel lblStatus = new JLabel("Trạng thái ");
			lblStatus.setBounds(346, 122, 73, 23);
			contentPane.add(lblStatus);
		
			JButton btnDelete = new JButton("Xóa");
			btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = txtNameTable.getText();
					if(idTable !=null && !idTable.isEmpty()) {
			 			String billTableId = idTable;
					 try {
				        	DefaultTableModel defaultTableModel;
				        	conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);
				            Statement stmt = conn.createStatement();
				            String sql = " UPDATE `ta_vip_table`SET`T_Status` = 'Trống ' WHERE (`I_IDTable` = '"+id+"')";
				            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.executeUpdate();
							
							sql = "select * from ta_vip_table";
							ResultSet rs = stmt.executeQuery(sql);
						    defaultTableModel = (DefaultTableModel) table.getModel();
							defaultTableModel.setRowCount(0);
							while (rs.next()) {
								defaultTableModel.addRow(new Object[] { rs.getString("I_IDTable"),rs.getString("T_TableName"), rs.getString("T_Status")});
							};
				            conn.close();
							
				        } catch (SQLException e1) {
				            e1.printStackTrace();
				        }
					 txtNameTable.setText("");
					
					 txtStatus.setText("");
				}else {
		            JOptionPane.showMessageDialog(null, "Vui lòng chọn một bàn để tạo hóa đơn.");
		        }
				}
				
			});
			btnDelete.setBounds(445, 218, 89, 23);
			contentPane.add(btnDelete);
			
			JButton btnFix = new JButton("Chọn bàn ");
			btnFix.setFont(new Font("Tahoma", Font.PLAIN, 13));
			btnFix.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
                String id = txtNameTable.getText();
                if(idTable !=null && !idTable.isEmpty()) {
		 			String billTableId = idTable;		
					 try {
				        	DefaultTableModel defaultTableModel;
				        	conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);				           
				        	String sql = " UPDATE `ta_vip_table` "
				            		+ "SET `T_Status` = 'Có Khách ' WHERE (`I_IDTable` = '"+id+"')";
				            PreparedStatement pstmt = conn.prepareStatement(sql);
				            pstmt.executeUpdate();
							sql = "select * from ta_vip_table";
							ResultSet rs = pstmt.executeQuery(sql);
						    defaultTableModel = (DefaultTableModel) table.getModel();
							defaultTableModel.setRowCount(0);
							while (rs.next()) {
								defaultTableModel.addRow(new Object[] { rs.getString("I_IDTable"),
										rs.getString("T_TableName"),rs.getString("T_Status")  });
							};
				            conn.close();
				        } catch (SQLException e1) {
				            e1.printStackTrace();
				        }
                }else {
				            JOptionPane.showMessageDialog(null, "Vui lòng chọn một bàn để tạo hóa đơn.");
				        }
					 txtNameTable.setText("");
				     txtStatus.setText("");
				     
				}
			});
			btnFix.setBounds(346, 218, 89, 23);
			contentPane.add(btnFix);
			
			JButton btnHome = new JButton("Home ");
			btnHome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ManageView ().setVisible(true);
					dispose();
				}
			});
			btnHome.setBounds(34, 406, 89, 23);
			contentPane.add(btnHome);
			
			panelTable = new JPanel();
			panelTable.setBounds(23, 54, 313, 255);
			contentPane.add(panelTable);
			panelTable.setLayout(null);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 313, 275);
			panelTable.add(scrollPane);
			
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
				            Object status = table.getValueAt(selectedRow , 2);
				            txtNameTable.setText(id.toString());
				            txtStatus.setText(status.toString());
				            idTable = id.toString();
				        }
				    }
				});
			 
			 JLabel lblNameTable = new JLabel("ID ");
		     lblNameTable.setBounds(346, 54, 73, 23);
			 contentPane.add(lblNameTable);
			 
			 txtStatus = new JTextField();
			 txtStatus.setEditable(false);
			 txtStatus.setColumns(10);
			 txtStatus.setBounds(415, 123, 86, 20);
			 contentPane.add(txtStatus);
			 
			 JButton btnFood = new JButton("Tạo hóa đơn ");
			 btnFood.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent e) {
			 		if(idTable !=null && !idTable.isEmpty()) {
			 			String billTableId = idTable;
			 			new Bill1View().setVisible(true);
			 			dispose();
						
			 		}else {
			            JOptionPane.showMessageDialog(null, "Vui lòng chọn một bàn để tạo hóa đơn.");
			        }
			 	}
			 });
			 btnFood.setBounds(258, 363, 117, 23);
			 contentPane.add(btnFood);

			try {
				defaultTableModel = (DefaultTableModel) table.getModel();
				conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	            Statement stmt = conn.createStatement();
				 String sql = "select * from ta_vip_table";
				ResultSet rs = stmt.executeQuery(sql);
				defaultTableModel.setRowCount(0);
				while (rs.next()) {
					defaultTableModel.addRow(new Object[] { rs.getString("I_IDTable"), rs.getString("T_TableName"), rs.getString("T_Status")  });
				}
				;
	            conn.close();
	        } catch (SQLException e1) {
	        	JOptionPane.showMessageDialog(null, e1.getMessage());
	        }
			
		}
	}
