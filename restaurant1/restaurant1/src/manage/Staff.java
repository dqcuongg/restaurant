package manage;

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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import constants.Constant;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Staff extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtDate;
	private JTextField txtAddress;
	private JComboBox cbbSex;
	Connection conn = null;
	private JTextField txtUser;
	private JTextField txtPassword;
	private JTextField txtID;
	private JTextField txtPosition;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Staff frame = new Staff();
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
	public Staff() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\QuanLyNhaHang\\nhahang.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100,908, 539);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 211, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String[] Sex = {"Nam", "Nữ"}; 
	    final JComboBox cbbSex = new JComboBox(Sex);
	    cbbSex.setBounds(145, 181, 96, 22);
		contentPane.add(cbbSex);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 317, 862, 183);
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 872, 183);
		panel.add(scrollPane);
		
		JTable table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null,null, null, null, null, null,null},
				},
			new String[] {
					 "ID","Tên nhân viên ","Giới tính","Ngày sinh",  "Địa chỉ  ","Tên đăng nhập ","Mật khẩu ","Chức vụ"
			}
		));
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
		            int selectedRow = table.getSelectedRow();
		            Object idname = table.getValueAt(selectedRow, 0);
		            Object staffname = table.getValueAt(selectedRow, 1);
		            Object sex = table.getValueAt(selectedRow, 2);
		            Object day = table.getValueAt(selectedRow, 3);
		            Object address = table.getValueAt(selectedRow, 4);
		            Object username = table.getValueAt(selectedRow, 5);
		            Object password = table.getValueAt(selectedRow, 6);
		            Object position = table.getValueAt(selectedRow, 7);
		            txtID.setText(idname.toString());
		            txtName.setText(staffname.toString());
		            cbbSex.setSelectedItem(sex.toString());
		            txtDate.setText(day.toString());
		            txtAddress.setText(address.toString());
		            txtUser.setText(username.toString());
		            txtPassword.setText(password.toString());
		            txtPosition.setText(position.toString());
		            
		        }
		    }
		});
		
		scrollPane.setViewportView(table);
		
		JLabel lblStaff = new JLabel("Danh sách nhân viên  ");
		lblStaff.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStaff.setBounds(326, 11, 292, 59);
		contentPane.add(lblStaff);
		
		JButton btnAdd = new JButton("Bổ sung ");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				  String id  = txtID.getText();
				  String position =txtPosition.getText();
				  String name = txtName.getText();
				  String sex = (String) cbbSex.getSelectedItem();
                  String date = txtDate.getText();
                  String address = txtAddress.getText();
                  String user = txtUser.getText();
                  String pass = txtPassword.getText();
				
			        try {
			        	 DefaultTableModel defaultTableModel;
			        	 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	
			            Statement stmt = conn.createStatement();
						String sql = "INSERT INTO ta_vip_infor (T_Name, T_Sex ,D_DateOfDay,T_Address, T_UserName,T_Password,T_Position ) " + "VALUES "
								+ "('" +  name + "', '"+ sex + "','" + date + "','" + address + "','" + user + "','" + pass + "','" + position +"')";
						stmt.executeUpdate(sql);
						
						sql = "select * from ta_vip_infor";
						ResultSet rs = stmt.executeQuery(sql);
					    defaultTableModel = (DefaultTableModel) table.getModel();
						defaultTableModel.setRowCount(0);
						while (rs.next()) {
							defaultTableModel.addRow(new Object[] {rs.getString("I_CodeStaff"),rs.getString("T_Name"), rs.getString("T_Sex"),
									rs.getString("D_DateOfDay") ,rs.getString("T_Address"),rs.getString("T_UserName"),
									rs.getString("T_Password"),rs.getString("T_Position") });
						};
			            conn.close();
			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        }
			    
			        txtName.setText("");
			        txtDate.setText("");
			        txtAddress.setText("");
			        txtUser.setText("");
			        txtPassword.setText("");
			        txtPosition.setText("");
			}
		});
		btnAdd.setBounds(201, 271, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("Xóa ");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
				    String name = (String) table.getValueAt(selectedRow, 0);
				    
				    String sql = "DELETE FROM ta_vip_infor WHERE T_Name = ?";
				    try {
				        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant6", "root", "28042004");
				        PreparedStatement pstmt = conn.prepareStatement(sql);
				        pstmt.setString(1, name);
				        pstmt.executeUpdate();
				    } catch (SQLException ex) {
				        ex.printStackTrace();
				    }
				    
				    ((DefaultTableModel) table.getModel()).removeRow(selectedRow);
				}
				
				
				
			}
		});
		btnDelete.setBounds(530, 271, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnUpdate = new JButton("Chỉnh  sửa");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idString = txtID.getText();
				int id = Integer.parseInt(idString);
				 String position =txtPosition.getText();
				  String name = txtName.getText();
				  String sex = (String) cbbSex.getSelectedItem();
                 String day = txtDate.getText();
                 String address = txtAddress.getText();
                 String user = txtUser.getText();
                 String pass = txtPassword.getText();
				
			        try {
			        	 DefaultTableModel defaultTableModel;
			        	 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	
			            Statement stmt = conn.createStatement();
			            String sql = "UPDATE `ta_vip_infor` SET `T_Name`= '"+ name +"', `T_Sex` = '"+ sex +"', `D_DateOfDay` = '"+ day +"',"
			            		+ "`T_Address`= '"+address +"',`T_UserName`  = '"+user+"', `T_Password` = '"+pass +"', "
			            				+ "`T_Position` = '"+position +"' WHERE (`I_CodeStaff` = '"+id+"')";

			            PreparedStatement statement = conn.prepareStatement(sql);
			            stmt.executeUpdate(sql);
						
						sql = "select * from ta_vip_infor";
						ResultSet rs = stmt.executeQuery(sql);
					    defaultTableModel = (DefaultTableModel) table.getModel();
						defaultTableModel.setRowCount(0);
						while (rs.next()) {
							defaultTableModel.addRow(new Object[] {rs.getInt("I_CodeStaff"),rs.getString("T_Name"), rs.getString("T_Sex"),
									rs.getString("D_DateOfDay") ,rs.getString("T_Address"),rs.getString("T_UserName"),
									rs.getString("T_Password"),rs.getString("T_Position") });
						};
			            conn.close();
			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        }
			        txtID.setText("");
			        txtName.setText("");
			        txtDate.setText("");
			        txtAddress.setText("");
			        txtUser.setText("");
			        txtPassword.setText("");
			        txtPosition.setText("");
			}
		});

		btnUpdate.setBounds(375, 271, 89, 23);
		contentPane.add(btnUpdate);
		
		JLabel lblName = new JLabel("Tên ");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(81, 136, 54, 29);
		contentPane.add(lblName);
		
		JLabel lblSex = new JLabel("Giới tính");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSex.setBounds(81, 176, 54, 29);
		contentPane.add(lblSex);
		
		JLabel lblDateOfDay = new JLabel("Ngày sinh");
		lblDateOfDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDateOfDay.setBounds(386, 89, 109, 29);
		contentPane.add(lblDateOfDay);
		
		JLabel lblAddress = new JLabel("Địa chỉ ");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(386, 136, 54, 29);
		contentPane.add(lblAddress);
		
		txtName = new JTextField();
		txtName.setBounds(141, 139, 203, 26);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(469, 89, 203, 26);
		contentPane.add(txtDate);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(469, 136, 203, 26);
		contentPane.add(txtAddress);
		
		JLabel lblUser = new JLabel("Tên đăng nhập ");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(363, 170, 116, 29);
		contentPane.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(469, 173, 203, 26);
		contentPane.add(txtUser);
		
		JLabel lblPassword = new JLabel("Mật khẩu ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(363, 214, 116, 29);
		contentPane.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(469, 217, 203, 26);
		contentPane.add(txtPassword);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblID.setBounds(81, 89, 54, 29);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBounds(141, 95, 203, 26);
		contentPane.add(txtID);
		
		JLabel lblPosition = new JLabel("Chức vụ ");
		lblPosition.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPosition.setBounds(81, 216, 72, 29);
		contentPane.add(lblPosition);
		
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
		
		
		
		txtPosition = new JTextField();
		txtPosition.setColumns(10);
		txtPosition.setBounds(141, 220, 203, 26);
		contentPane.add(txtPosition);
		
		try {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	
			 Statement stmt = conn.createStatement();
             String sql = "select * from ta_vip_infor";
			 
			ResultSet rs = stmt.executeQuery(sql);
			defaultTableModel.setRowCount(0);
			while (rs.next()) {
				defaultTableModel.addRow(new Object[] {rs.getString("I_CodeStaff") ,rs.getString("T_Name"), rs.getString("T_Sex"),
						rs.getString("D_DateOfDay") ,rs.getString("T_Address"),rs.getString("T_UserName"),rs.getString("T_Password"),rs.getString("T_Position") });		
				};
//            conn.close();
        } catch (SQLException e1) {
        	JOptionPane.showMessageDialog(null, e1.getMessage());
        }
//		
	}
}
