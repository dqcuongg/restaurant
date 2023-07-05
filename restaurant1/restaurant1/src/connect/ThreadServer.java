package connect;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import constants.Constant;

public class ThreadServer extends Thread {
        private Socket clientSocket;
        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket socket;
        public ThreadServer(Socket socket)  {
            this.socket = socket;
            try {
    			dos = new DataOutputStream(socket.getOutputStream());
    			dis = new DataInputStream(socket.getInputStream());
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            
        }

        @Override
        public void run() {
            try {
            	

                byte[] buffer = new byte[1024];
                int bytesRead = dis.read(buffer);

                String receivedData = new String(buffer, 0, bytesRead);
                String[] loginInfo = receivedData.split(";");
                String username = loginInfo[0];
                String password = loginInfo[1];
                

                boolean loginResult = performLogin(username, password);

                if (loginResult) {
                    dos.write("OK".getBytes());
                    
                    } 
                else {
                    dos.write("KO".getBytes());
                 }

                } catch (IOException e) {
                e.printStackTrace();
            }
        }
    

    private boolean performLogin(String username, String password) {
        boolean loginResult = false;

        try {
        	
	    	Connection conn = DriverManager.getConnection(Constant.JDBC_URL, Constant.JDBC_USERNAME, Constant.JDBC_PASSWORD);		
            String query = "SELECT* FROM ta_vip_infor WHERE T_UserName = ? AND T_Password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	 
                loginResult = true;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loginResult;
    }
    
    }
    