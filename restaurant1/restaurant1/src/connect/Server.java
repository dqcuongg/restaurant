package connect;
import javax.swing.*;

import constants.Constant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server extends JFrame {

    private JTextArea logTextArea;
    private Connection conn;

    public Server() {
        setTitle("Socket Server");
        setSize(484, 332);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Server");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                        
                }).start();
                startButton.setEnabled(false);
            }
        });
        panel.add(startButton, BorderLayout.SOUTH);
        getContentPane().add(panel);
    }

    private void startServer() {
    	int port = 2802;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            log("Server bắt đầu ở cổng " +port +" \n");

            while (true) {
                Socket socket = serverSocket.accept();
                log("Client kết nối thành công \n");
                ThreadServer threadServer = new ThreadServer(socket);
					 threadServer.start();
				
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 private void log(String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                logTextArea.append(message + "\n");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	Server serverGUI = new Server();
                serverGUI.setVisible(true);
            }
        });
    }
}
