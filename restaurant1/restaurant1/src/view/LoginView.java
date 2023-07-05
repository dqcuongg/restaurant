package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import connect.ThreadClient;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import connect.Server;
import javax.swing.JComboBox;

public class LoginView extends JFrame {

	private  Server server;

	private JPanel loginPanel;
	private JTextField tfUserName;
	private JPasswordField pfPassword;
	private static final Object lock = new Object();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView loginView = new LoginView();
					loginView.setLocationRelativeTo(null);
					loginView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {
		initComponents();
	}

	private void initComponents() {
		this.setTitle("Đăng nhập");

		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\QuanLyNhaHang\\nhahang.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 464, 300);

		loginPanel = new JPanel();
		loginPanel.setBackground(new Color(176, 211, 238));
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginPanel);
		loginPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(26, 29, 393, 203);
		panel.setBackground(new Color(255, 255, 255));
		loginPanel.add(panel);
		panel.setLayout(null);

		JLabel lblLogin = new JLabel("Đăng nhập");
		lblLogin.setBounds(135, 5, 125, 27);
		lblLogin.setForeground(new Color(21, 39, 162));
		lblLogin.setFont(new Font("Sitka Subheading", Font.BOLD, 21));
		panel.add(lblLogin);

		tfUserName = new JTextField();
		tfUserName.setBounds(104, 56, 178, 20);
		panel.add(tfUserName);
		tfUserName.setColumns(10);

		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 try { 
			     String username = tfUserName.getText();
			     String password = new String(pfPassword.getPassword());
			     String loginInfo = username + ";" + password;
			        	Socket socket = new Socket("localhost", 2802); 
			        	ThreadClient clientThread = new ThreadClient(socket,username, password,loginInfo);
			        		clientThread.start();
		        } catch (IOException ex) {
               	 JOptionPane.showMessageDialog(null, "chưa kết nối với máy chủ");
		        }
		    }
		});
          
		btnLogin.setBackground(new Color(255, 255, 255));
		btnLogin.setForeground(new Color(21, 39, 162));
		btnLogin.setFont(btnLogin.getFont().deriveFont(btnLogin.getFont().getSize() + 2f));
		btnLogin.setBounds(135, 134, 120, 35);
		panel.add(btnLogin);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(104, 93, 178, 20);
		panel.add(pfPassword);

		JLabel lblUser = new JLabel("Tên đăng nhập");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUser.setBounds(10, 59, 90, 14);
		panel.add(lblUser);

		JLabel lblPassword = new JLabel("Mật khẩu");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(10, 96, 90, 14);
		panel.add(lblPassword);

		JToggleButton toggleShowPass = new JToggleButton("Hiện mật khẩu");
		toggleShowPass.setBackground(new Color(255, 255, 255));
		toggleShowPass.setHorizontalAlignment(SwingConstants.LEFT);
		toggleShowPass.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean showPassword = toggleShowPass.isSelected();

				if (showPassword) {
					pfPassword.setEchoChar((char) 0);
				} else {
					pfPassword.setEchoChar('*');
				}
			}
		});
		toggleShowPass.setFont(new Font("Cambria", Font.PLAIN, 11));
		toggleShowPass.setBounds(278, 93, 120, 20);
		toggleShowPass.setBorderPainted(false);
		panel.add(toggleShowPass);
		
		String[] position = {"Nhân viên ", "Quản lý"};
	}

}