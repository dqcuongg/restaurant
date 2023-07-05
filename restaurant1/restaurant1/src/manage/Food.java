package manage;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import constants.Constant;
import view.FoodView;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Food extends JFrame {

	private JPanel cpFood;
	private JTable table;
	private JLabel lblFoodName;
	private JTextField txtFoodName;
	private JLabel lblPrice;
	private JLabel lblNumber;
	private JTextField txtPrice;
	private JTextField txtNumber;
	private JLabel lblIDFood;
	private JTextField txtIDFood;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnFix;
	private Connection conn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Food frame = new Food();
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
	public Food() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100,908, 539);
		cpFood = new JPanel();
		cpFood.setBackground(new Color(176, 211, 238));
		cpFood.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(cpFood);
		cpFood.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 63, 573, 426);
		
		table= new JTable();
		table.setBounds(10, 63, 573, 426);
		table.setModel(new DefaultTableModel(
				new Object[][]  {
					{null,null,null,null},
					
				},
				new String[] {
					"ID", "Tên món", "Giá tiền ","Số lượng"
				}
			));
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
		            int selectedRow = table.getSelectedRow();
		            Object idFood = table.getValueAt(selectedRow, 0);
		            Object namefood = table.getValueAt(selectedRow, 1);
		            Object price = table.getValueAt(selectedRow, 2);
		            Object number = table.getValueAt(selectedRow, 3);
		            txtIDFood.setText(idFood.toString());
		            txtFoodName.setText(namefood.toString());
		            txtPrice.setText(price.toString());
		            txtNumber.setText(number.toString());
		        }
		    }
		});
			
		scrollPane.setViewportView(table);
		cpFood.add(scrollPane);
		
		lblFoodName = new JLabel("Tên món ăn");
		lblFoodName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFoodName.setBounds(623, 104, 75, 26);
		cpFood.add(lblFoodName);
		
		txtFoodName = new JTextField();
		txtFoodName.setBounds(696, 108, 196, 20);
		cpFood.add(txtFoodName);
		txtFoodName.setColumns(10);
		
		lblPrice = new JLabel("Giá tiền");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPrice.setBounds(623, 146, 75, 26);
		cpFood.add(lblPrice);
		
		lblNumber = new JLabel("Số lượng");
		lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumber.setBounds(623, 191, 75, 26);
		cpFood.add(lblNumber);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(696, 150, 196, 20);
		cpFood.add(txtPrice);
		
		txtNumber = new JTextField();
		txtNumber.setColumns(10);
		txtNumber.setBounds(696, 195, 196, 20);
		cpFood.add(txtNumber);
		
		lblIDFood = new JLabel("Mã món ăn ");
		lblIDFood.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIDFood.setBounds(623, 63, 75, 26);
		cpFood.add(lblIDFood);
		
		txtIDFood = new JTextField();
		txtIDFood.setEditable(false);
		txtIDFood.setColumns(10);
		txtIDFood.setBounds(696, 67, 196, 20);
		cpFood.add(txtIDFood);
		
		btnAdd = new JButton("Thêm ");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String foodname = txtFoodName.getText();
		 		String price  = txtPrice.getText();
		 		String number = txtNumber.getText();

		 		try {
		 			DefaultTableModel defaultTableModel;
		 			 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	
		            String sql = "INSERT INTO ta_vip_food (T_FoodName,F_Price, I_Number)" + "VALUES"
		            		+ "('" +  foodname + "', '"+ price + "','" + number  +"')";
		            PreparedStatement pstmt =conn.prepareStatement(sql);
		            pstmt.executeUpdate(sql);
		            sql = "select * from ta_vip_food";
					ResultSet rs = pstmt.executeQuery(sql);
				    defaultTableModel = (DefaultTableModel) table.getModel();
					defaultTableModel.setRowCount(0);
					while (rs.next()) {
						defaultTableModel.addRow(new Object[] { rs.getString("I_IDFood"),rs.getString("T_FoodName"),
								rs.getString("F_Price"), rs.getString("I_Number")});
					};
		 		}catch (Exception ex) {
		 			ex.printStackTrace();
					// TODO: handle exception
				}
		 		txtIDFood.setText("");
		        txtFoodName.setText("");
		 		 txtPrice.setText("");
		 		 txtNumber.setText("");
		 	}
		 });
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAdd.setBounds(623, 252, 75, 23);
		cpFood.add(btnAdd);
		
		btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
				    String name = (String) table.getValueAt(selectedRow, 0);
				    
				    String sql = "DELETE FROM ta_vip_food WHERE T_FoodName = ?";
				    try {
				    	 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	
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
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnDelete.setBounds(720, 252, 75, 23);
		cpFood.add(btnDelete);
		
		btnFix = new JButton("Sửa");
		btnFix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idString = txtIDFood.getText();
				int id = Integer.parseInt(idString);
				String foodname = txtFoodName.getText();
		 		String price  = txtPrice.getText();
		 		String number = txtNumber.getText();
                 
				
			        try {
			        	 DefaultTableModel defaultTableModel;
			        	 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	
			            Statement stmt = conn.createStatement();
			            String sql = "UPDATE `ta_vip_food` SET `T_FoodName`= '"+ foodname +"', `F_Price` = '"+ price +"', `I_Number` = '"+ number +"' WHERE (`I_IDFood` = '"+id+"')";

			            PreparedStatement statement = conn.prepareStatement(sql);
			            stmt.executeUpdate(sql);
						
						sql = "select * from ta_vip_food";
						ResultSet rs = stmt.executeQuery(sql);
					    defaultTableModel = (DefaultTableModel) table.getModel();
						defaultTableModel.setRowCount(0);
						while (rs.next()) {
							defaultTableModel.addRow(new Object[] { rs.getString("I_IDFood"),rs.getString("T_FoodName"),
									rs.getString("F_Price"), rs.getString("I_Number")});
						};
			            conn.close();
			        } catch (SQLException e1) {
			            e1.printStackTrace();
			        }
			        txtIDFood.setText("");
			        txtFoodName.setText("");
			 		 txtPrice.setText("");
			 		 txtNumber.setText("");
			}
		});

		btnFix.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnFix.setBounds(817, 252, 75, 23);
		cpFood.add(btnFix);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Manage().setVisible(true);
				dispose();
			}
		});
		btnHome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnHome.setBounds(10, 11, 83, 23);
		cpFood.add(btnHome);
		
	
		try {
			DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
			 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);	
			 Statement stmt = conn.createStatement();
             String sql = "select * from ta_vip_food";
			 
			ResultSet rs = stmt.executeQuery(sql);
			defaultTableModel.setRowCount(0);
			while (rs.next()) {
				defaultTableModel.addRow(new Object[] { rs.getString("I_IDFood"),rs.getString("T_FoodName"),
						rs.getString("F_Price"), rs.getString("I_Number")});
				};
            conn.close();
        } catch (SQLException e1) {
        	JOptionPane.showMessageDialog(null, e1.getMessage());
        }	
		
		
	}
}
