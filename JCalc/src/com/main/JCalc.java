package com.main;

import java.awt.GridLayout;

import javax.swing.*;

public class JCalc {
	public static void main(String[] args){  
        JFrame frame = new JFrame();  
        JPanel panel = new JPanel();  
        JTextArea textArea = new JTextArea();  
        panel.setLayout(new GridLayout());  
        textArea.setText("test");  
        //��TextArea������ݹ���ʱ���ɹ�����
        panel.add(new JScrollPane(textArea));  
        frame.setTitle("First App");
        frame.add(panel);  
        frame.setSize(200,200);  
        frame.setVisible(true);  
    }  
}
