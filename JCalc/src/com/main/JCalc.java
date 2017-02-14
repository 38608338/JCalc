package com.main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.*;

public class JCalc {
	public static void main(String[] args){  		
        JFrame frame = new JFrame();  
        JPanel panel = new JPanel();  
        JTextArea textArea = new JTextArea();  
        panel.setLayout(new GridLayout());  
        //textArea.setText("test");  
        //��TextArea������ݹ���ʱ���ɹ�����
        panel.add(new JScrollPane(textArea));  
        
        frame.add(panel);  
        frame.setSize(640,480);  
        frame.setVisible(true);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); // �����ʾ����С����
        Dimension frameSize = frame.getSize();             // ��ô��ڴ�С����
        if (frameSize.width > displaySize.width)  
        frameSize.width = displaySize.width;           // ���ڵĿ�Ȳ��ܴ�����ʾ���Ŀ��
        if (frameSize.height > displaySize.height)  
        frameSize.height = displaySize.height;          // ���ڵĸ߶Ȳ��ܴ�����ʾ���ĸ߶�
        frame.setLocation((displaySize.width - frameSize.width) / 2,  
        (displaySize.height - frameSize.height) / 2); // ���ô��ھ�����ʾ����ʾ
        
        //������ֱ�ӵ�������ǣ�getClass()��һ�����ʵ�����߱��ķ���
        //��class������һ����ķ�����getClass()��������ʱ��ȷ���ģ�
        //��class�������ڱ���ʱ��ȷ����
        System.out.println(JCalc.class.getClass().getResource(""));
        System.out.println(JCalc.class.getResource(""));
        System.out.println(JCalc.class.getClass().getResource("/"));
        System.out.println(JCalc.class.getResource("/"));
        frame.setIconImage (Toolkit.getDefaultToolkit ().createImage(JCalc.class.getClass().getResource ("/logo.png" )));

		InputStream inStream=JCalc.class.getResourceAsStream("/config.properties");

		Properties prop=new Properties();
		try {
			prop.load(inStream);
			frame.setTitle(prop.get("title").toString());
			textArea.setText(prop.get("text").toString()); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+JCalc.class.getResource("/")+"test.db");
			System.out.println("Opened database successfully");
			
			Statement stmt = c.createStatement();
			String sql="SELECT * from user";
			ResultSet rs = stmt.executeQuery(sql);
			StringBuffer sb=new StringBuffer();
			while (rs.next()) {
				sb.append(rs.getString("name"));
				sb.append("\n");
			}
			rs.close();
			stmt.close();
			
			textArea.setText(prop.get("text").toString()+"\n"+sb.toString()); 
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				if (c != null){
					c.close();
					System.out.println("Closeed database successfully");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }  
}
