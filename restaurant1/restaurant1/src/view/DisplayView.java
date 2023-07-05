package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class DisplayView extends JFrame {

	private JPanel ppDisplay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayView displayView = new DisplayView();
					displayView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public DisplayView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\QuanLyNhaHang\\nhahang.jpg"));
		setBounds(300, 100, 624, 386);
		ppDisplay = new JPanel();
		ppDisplay.setBorder(new EmptyBorder(5, 5, 5, 5));
		ppDisplay.setBackground(new Color(176, 211, 238));
		setContentPane(ppDisplay);
		ppDisplay.setLayout(null);

		JLabel lblManage = new JLabel("Quản Lý nhà hàng ");
		lblManage.setBackground(Color.LIGHT_GRAY);
		lblManage.setForeground(new Color(255, 255, 255));
		lblManage.setFont(new Font("Sitka Subheading", Font.BOLD, 30));
		lblManage.setBounds(162, 122, 326, 63);
		ppDisplay.add(lblManage);

		JButton btnLogin = new JButton("Đăng nhập ");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginView().setVisible(true);
				
			}
		});
		btnLogin.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		btnLogin.setBounds(162, 185, 147, 35);
		ppDisplay.add(btnLogin);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Sitka Subheading", Font.BOLD, 20));
		btnExit.setBounds(343, 185, 137, 35);
		ppDisplay.add(btnExit);

		
	
	}

}
