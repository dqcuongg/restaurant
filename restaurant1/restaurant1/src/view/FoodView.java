package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import constants.Constant;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

public class FoodView extends JFrame {

	private JPanel cpFood;
	private JTextField txtNumber;
	private JTable table;
	private JTextField txtCodeBill;
	private JTextField txtFood;
	private JTextField txtPrice;
	Connection conn = null;
	private JTextField txtStaff;
	private JComboBox cbbArea;
	private JTextField txtTime;
	private JTextField txtIDFood;
	private String dataCodeBill;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bill1View foodView = new Bill1View();
					foodView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FoodView(String codeBill)  {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\QuanLyNhaHang\\nhahang.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100,908, 539);
		cpFood = new JPanel();
		cpFood.setBackground(new Color(176, 211, 238));
		cpFood.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(cpFood);
		cpFood.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(21, 57, 845, 432);
		panel.setBackground(new Color(255, 255, 255));
		cpFood.add(panel);
		panel.setLayout(null);
				
		JLabel lblNumber = new JLabel("Số lượng ");
		lblNumber.setForeground(new Color(26, 123, 145));
		lblNumber.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		lblNumber.setBounds(20, 322, 64, 17);
		panel.add(lblNumber);
		
		txtCodeBill = new JTextField();
		txtCodeBill.setEditable(false);
		txtCodeBill.setBounds(587, 235, 152, 20);
		panel.add(txtCodeBill);
		txtCodeBill.setColumns(10);
		
		this.dataCodeBill = codeBill;
		this.txtCodeBill.setText(codeBill);
		
		txtNumber = new JTextField();
		txtNumber.setColumns(10);
		txtNumber.setBounds(109, 318, 226, 20);
		panel.add(txtNumber);
		
	
		txtFood =  new JTextField();
		txtFood.setEditable(false);
		txtFood.setBounds(109, 256, 226, 20);
		panel.add(txtFood);
		txtFood.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		txtPrice.setColumns(10);
		txtPrice.setBounds(109, 287, 226, 20);
		panel.add(txtPrice);
		
		String[] Area = {"A ", "B "};
		
		JLabel lblNewLabel = new JLabel("Món ăn");
		lblNewLabel.setBounds(395, 5, 139, 32);
		cpFood.add(lblNewLabel);
		lblNewLabel.setForeground(new Color(21, 39, 162));
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 22));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 372, 212);
		panel.add(scrollPane);
		
		table= new JTable();
		
		table.setBackground(new Color(255, 255, 255));
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
		            Object ID_Food = table.getValueAt(selectedRow, 0);
		            Object Name_food = table.getValueAt(selectedRow, 1);
		            Object price = table.getValueAt(selectedRow, 2);
		            txtIDFood.setText(ID_Food.toString());
		            txtFood.setText(Name_food.toString());
		            txtPrice.setText(price.toString());
		        }
		    }
		});

        scrollPane.setViewportView(table);
		
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(483, 0, 362, 212);
		panel.add(scrollPane1);
		
		JTable table1 = new JTable();
		table1.setBackground(new Color(255, 255, 255));
		table1.setModel(new DefaultTableModel(
			new Object[][]  {
				
				},
			new String[] {
				"ID","Tên món","Giá tiền ","Số lượng","Tổng tiền "
				
			}
		));
		scrollPane1.setViewportView(table1);
		
		JButton btnChoose = new JButton("Chọn món ");	
		btnChoose.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
		    	 String number = txtNumber.getText();
		         int quantity = Integer.parseInt(number);
		         String idfood = txtIDFood.getText();
		         String food = txtFood.getText();
		         String priceValue = txtPrice.getText();
		         double price = Double.parseDouble(priceValue);
		         double total = quantity * price;
		         DefaultTableModel model = (DefaultTableModel) table1.getModel();
		        model.insertRow(0, new Object[]{ idfood, food, priceValue, number, total});
		        txtIDFood.setText("");
		        txtNumber.setText("");
		        txtFood.setText("");
		        txtPrice.setText("");
		    }
		    
		    
		});

		
		btnChoose.setBounds(109, 362, 96, 23);
		panel.add(btnChoose);
		
				
		JLabel lblFood = new JLabel("Tên món");
		lblFood.setForeground(new Color(26, 123, 145));
		lblFood.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		lblFood.setBounds(20, 260, 64, 17);
		panel.add(lblFood);
		
		
		
		JLabel lblPrice = new JLabel("Giá tiền  ");
		lblPrice.setForeground(new Color(26, 123, 145));
		lblPrice.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		lblPrice.setBounds(20, 291, 64, 17);
		panel.add(lblPrice);
		
		
		
		JLabel lblCode_bill = new JLabel("Mã hóa đơn");
		lblCode_bill.setForeground(new Color(26, 123, 145));
		lblCode_bill.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		lblCode_bill.setBounds(498, 239, 104, 17);
		panel.add(lblCode_bill);
		
		JButton btnInFood = new JButton("In món ăn");
		btnInFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    
		        DefaultTableModel model = (DefaultTableModel) table1.getModel();
		        int rowCount = model.getRowCount();
		        int columnCount = model.getColumnCount();
		        
		        try {
		        	conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);		            String sql = "INSERT INTO ta_vip_billdetail (`I_IDFood`,`T_FoodName`,`F_Price`,`I_Number`,`F_Total`,`I_CodeBill`) VALUES (?, ?, ?, ?, ?, ?)";
		            PreparedStatement pstmt = conn.prepareStatement(sql);
		            for (int row = 0; row < rowCount; row++) {
		                for (int column = 0; column < columnCount; column++) {
		                    Object value = model.getValueAt(row, column);
		                    pstmt.setObject(column + 1, value);
		                }
		                String codeBill = txtCodeBill.getText();
		               pstmt.setString(columnCount + 1, codeBill);
		                pstmt.executeUpdate();
		            }
		            
                    String selectSql = "SELECT * FROM ta_vip_billdetail";
		           PreparedStatement selectStatement = conn.prepareStatement(selectSql);
		            ResultSet resultSet = selectStatement.executeQuery();

		            while (resultSet.next()) {
		                int id = resultSet.getInt("I_IDFood"); 
		                String foodName = resultSet.getString("T_FoodName");
		                double price = resultSet.getDouble("F_Price");
		                int number = resultSet.getInt("I_Number");
		                double total = resultSet.getDouble("F_Total");
		                String codeBill = resultSet.getString("I_CodeBill");
		                
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		        model.setRowCount(0);
 
		    }
		});
		btnInFood.setBounds(574, 342, 89, 23);
		panel.add(btnInFood);
		
		JButton btnDelete = new JButton("Xóa ");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table1.getSelectedRow();
				if (selectedRow != -1) {
				    String namefood = (String) table1.getValueAt(selectedRow, 0);
				   
				    try {
				    	conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);				      
				        String sql = "DELETE FROM ta_vip_food WHERE T_FoodName = ?";
				    	PreparedStatement pstmt = conn.prepareStatement(sql);
				    	pstmt.setString(1, namefood);
				        pstmt.executeUpdate();
				    } catch (SQLException ex) {
				        ex.printStackTrace();
				    }
				    
				    ((DefaultTableModel) table1.getModel()).removeRow(selectedRow);
				}
			}
		});
		btnDelete.setBounds(711, 342, 89, 23);
		panel.add(btnDelete);
		
		JLabel lblIDFood = new JLabel("ID món ăn ");
		lblIDFood.setForeground(new Color(26, 123, 145));
		lblIDFood.setFont(new Font("Sitka Subheading", Font.PLAIN, 14));
		lblIDFood.setBounds(20, 223, 86, 17);
		panel.add(lblIDFood);
		
		txtIDFood = new JTextField();
		txtIDFood.setEditable(false);
		txtIDFood.setColumns(10);
		txtIDFood.setBounds(109, 223, 226, 20);
		panel.add(txtIDFood);
		

		JButton btnHome = new JButton("Bàn ");
		btnHome.setBounds(21, 11, 89, 23);
		cpFood.add(btnHome);
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TableView().setVisible(true);
					dispose();
				
			}
		});
		btnHome.setFont(new Font("Sitka Subheading", Font.BOLD, 12));
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTime.setBounds(599, 12, 46, 14);
		cpFood.add(lblTime);
		
		txtTime = new JTextField();
		txtTime.setEditable(false);
		txtTime.setBounds(655, 8, 129, 24);
		cpFood.add(txtTime);
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
		
	
	try {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
    	conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);				      
        Statement stmt = conn.createStatement();
		 String sql = "select * from ta_vip_food";
		ResultSet rs = stmt.executeQuery(sql);
		defaultTableModel.setRowCount(0);
		while (rs.next()) {
			defaultTableModel.addRow(new Object[] { rs.getString("I_IDFood"), rs.getString("T_FoodName"),
					rs.getString("F_Price"),rs.getString("I_Number")});
				};
//        conn.close();
    } catch (SQLException e1) {
    	JOptionPane.showMessageDialog(null, e1.getMessage());
    }
  }

	
}
