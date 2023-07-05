package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import constants.Constant;

import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Bill1View extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodeBill;
	private JTextField txtIDTable;
	private JTextField txtTotal;
	private JTextField txtTime;
	private JTextField txtIDStaff;
	private JButton btnBill;
	private Connection conn;
   
    private static List<Integer> generatedNumbers = new ArrayList<>();
    private static Random random = new Random();

    public static int generateUniqueRandomNumber(int minValue, int maxValue) {
        int randomNumber;
        do {
            randomNumber = random.nextInt(maxValue - minValue + 1) + minValue;
        } while (generatedNumbers.contains(randomNumber));
        generatedNumbers.add(randomNumber);
        return randomNumber;
    }

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bill1View frame = new Bill1View();
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
	public Bill1View() {
		this.setTitle("Bill");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCodeBill = new JTextField();
		txtCodeBill.setEditable(false);
		txtCodeBill.setBounds(159, 53, 155, 20);
		contentPane.add(txtCodeBill);
		txtCodeBill.setColumns(10);
		
		txtIDTable = new JTextField();
		txtIDTable.setText("");
		txtIDTable.setBounds(159, 93, 155, 20);
		contentPane.add(txtIDTable);
		txtIDTable.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setText("");
		txtTotal.setBounds(159, 134, 155, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		txtTime = new JTextField();
		txtTime.setBounds(254, 11, 155, 20);
		contentPane.add(txtTime);
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
		
		txtIDStaff = new JTextField();
		txtIDStaff.setBounds(159, 173, 155, 20);
		contentPane.add(txtIDStaff);
		txtIDStaff.setColumns(10);
		
		
		
		btnBill = new JButton("Tạo hóa đơn ");
		btnBill.addActionListener(new ActionListener() {
			int minValue;
			int maxValue;
			public void actionPerformed(ActionEvent e) {
				
				int randomNum = generateUniqueRandomNumber(1, 10000);
				 String code = Integer.toString(randomNum);
	             txtCodeBill.setText(code);
			     String idStaff =txtIDStaff.getText();
			     String idTable = txtIDTable.getText();
			     String datetime = txtTime.getText();
			     double totalSum = 0.0;
			     try {
			    	 conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);		
			    	 String sql = "INSERT INTO ta_vip_bill (I_CodeBill,I_IDTable,D_Date,F_Total,I_CodeStaff )" + "VALUES"
			            		+ "('" + code + "', '"+ idTable + "','"+datetime+ "','"+ totalSum +"','"+idStaff+"')";
			            Statement stmt = conn.createStatement();
			            
			            stmt.executeUpdate(sql);
			            
                       }catch (Exception ex) {
					// TODO: handle exception
			    	 ex.printStackTrace();
				}
			     new FoodView(txtCodeBill.getText()).setVisible(true);
			     dispose();
//			     txtTotal.setText(" " );
			     
//			     txtIDTable.setText(" " );
			     txtCodeBill.setText("");
//			     txtIDStaff.setText("");
			}
			
		});
		btnBill.setBounds(170, 215, 120, 23);
		contentPane.add(btnBill);
		
		JLabel lblCodeBilll = new JLabel("Mã hóa đơn");
		lblCodeBilll.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCodeBilll.setBounds(64, 53, 73, 17);
		contentPane.add(lblCodeBilll);
		
		JLabel lblTableID = new JLabel("Mã bàn");
		lblTableID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTableID.setBounds(64, 96, 73, 17);
		contentPane.add(lblTableID);
		
		JLabel lblStaffID = new JLabel("Tổng hóa đơn ");
		lblStaffID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblStaffID.setBounds(64, 137, 95, 17);
		contentPane.add(lblStaffID);
		
		JLabel lblTngHan = new JLabel("Mã nhân viên");
		lblTngHan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTngHan.setBounds(64, 176, 95, 17);
		contentPane.add(lblTngHan);
	}
}
