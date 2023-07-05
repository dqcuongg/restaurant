package connect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import manage.Manage;
import view.BillView;
import view.ManageView;
import view.TableView;


public class ThreadClient extends Thread{
	private Socket socket;
	private DataOutputStream dos ;
	private DataInputStream dis; 
	private String username;
	private String password;
	private String loginInfo = username + ";" + password;
	private Manage t = new Manage();
	private ManageView t1 = new ManageView();
	private static final Object lock = new Object();
	public ThreadClient(Socket socket, String username, String password,String loginInfo) {
		this.socket = socket;
		this.username = username;
		this.password = password;
		this.loginInfo = loginInfo;
		try {
			dos =new DataOutputStream(socket.getOutputStream());
			dis =new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	                   @Override
			            public void run() {
			                try {
			                				      
                                      dos.write(loginInfo.getBytes());                               
			                    	  byte[] buffer = new byte[1024];
				                      int bytesRead = dis.read(buffer);
			                    	  String receivedData = new String(buffer, 0, bytesRead);
			                    	  
			                    	  if(checkValidate( username, password)) { 
			                    	  
			                    		  if (receivedData.equals("OK")){
			                    			  
			                    			  if(username.equals("admin")) {
			                    				  JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                                                  SwingUtilities.invokeLater(() -> {
			                                     t.setVisible(true); });
                                                  }  else {
                                                	  JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
	                                                  SwingUtilities.invokeLater(() -> {
				                                     t1.setVisible(true); });
	                                                  }
			                    			  }
			                    		  else {
			                    			  JOptionPane.showMessageDialog(null, "Sai Name hoac Password");
			                    	      }
			                    		  } 
                                   
			                    dis.close();
			                    dos.close();
			                    socket.close();
			                } catch (IOException ex) {
			                	 JOptionPane.showMessageDialog(null, "chưa kết nối với máy chủ");
			                }
			            }
	  private boolean checkValidate(String username, String password) {
	              if (username.equals("") || password.equals("")) {
	               			
	               		 JOptionPane.showMessageDialog(null, "Bạn chưa nhập tên đăng nhập hoặc mật khẩug");
	               			return false;
	               		}
	              return true;
	               	}
	  }
