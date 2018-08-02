import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class DealUser {
	public	DealUser() {
		 JFrame f1=new JFrame("�û�����");
		 f1.setBounds(350,200,600,450);
		 f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 Container c =f1.getContentPane();
		 JPanel p=new JPanel();
		 JPanel p1=new JPanel();
		 JPanel p2=new JPanel();
		
		 c.add(p);
		 c.add(p1);
		 c.add(p2);
		 p.setBackground(Color.lightGray);
		 p1.setBackground(Color.lightGray);
		 p2.setBackground(Color.lightGray);
		 
			p.setBorder(new EmptyBorder(5, 5, 5, 5));
			p.setLayout(new BorderLayout(0, 0));
			f1.setContentPane(p);
			p.setLayout(null);
			
			
			/*  ����û�  */
			JTabbedPane pane = new JTabbedPane();
			p = new JPanel();
			p.setBorder(new EmptyBorder(5, 5, 5, 5));
			 p.setBackground(Color.lightGray);

			
			
			pane.addTab("����û�", p);
			p.setLayout(null);
			
			JLabel label = new JLabel("�û���");
			label.setBounds(170, 40, 54, 15);
			p.add(label);
			
			JTextField textField = new JTextField();
			textField.setBounds(230, 40, 120, 21);
			p.add(textField);
			textField.setColumns(10);
			
			JLabel label_1 = new JLabel("��ʼ����");
			label_1.setBounds(170, 100, 60, 15);
			p.add(label_1);
			
			JTextField textField_1 = new JTextField();
			textField_1.setBounds(230, 100, 120, 21);
			p.add(textField_1);
			textField_1.setColumns(10);
			
			JLabel label_2 = new JLabel("�û����");
			label_2.setBounds(170, 162, 60, 15);
			p.add(label_2);
			
			JTextField	textField_2 = new JTextField();
			textField_2.setBounds(230, 160, 120, 21);
			p.add(textField_2);
			textField_2.setColumns(10);
			
			JButton	button = new JButton("���");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = textField.getText();
					String password = textField_1.getText();
					String role = textField_2.getText();
					try
					{
						if(true==DataProcessing.insertUser(name, password, role))
						{
							new DealMessage("��ɫ��ӳɹ�");
							f1.setVisible(false);
						}
						
						else
							new DealMessage("��ɫ���ʧ��");
					}
					catch(SQLException e1)
					{
						new DealMessage("��ɫ��ӳ����쳣");
					}
					
				}
			});
			button.setBounds(180, 250, 60, 20);
			p.add(button);
			
			JButton button_1 = new JButton("ȡ��");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f1.setVisible(false);
				}
			});
			button_1.setBounds(320, 250, 60, 20);
			p.add(button_1);
			
			
			/*  ɾ���û�  */
			
			pane.addTab("ɾ���û�", p1);
			p1.setLayout(null);
			String[] str = {"����","����","��ɫ"};
			String[][] s = new String[50][3];
			
			try
			{
				User use;
				Enumeration<User> e  = DataProcessing.getAllUser();
				
				for(int i=0;e.hasMoreElements();i++)
				{
					use = e.nextElement();
					s[i][0] = use.getName();
					s[i][1] = use.getPassword();
					s[i][2] = use.getRole();
				}
			}
			catch(SQLException e)
			{
				new  DealMessage("�û�����ʧ��");
			}
			
			JTable table = new JTable(s,str);
			table.setBounds(120, 60, 300, 150);

			p1.add(table);
			
			JButton button_2 = new JButton("ɾ��");
			button_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int index = table.getSelectedRow();
					String name = s[index][0];
					
					try
					{
						if(true==DataProcessing.deleteUser(name))
							{
								new  DealMessage("ɾ���û��ɹ�");
								f1.setVisible(false);
							}
						else
							new  DealMessage("ɾ���û�ʧ��");		
						}
						catch(SQLException e1)
						{
							new  DealMessage("���ݿ������������");
						}
				}
			});
			button_2.setBounds(180, 250, 60, 20);
			p1.add(button_2);
			
			 JButton button_3 = new JButton("ȡ��");
			button_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f1.setVisible(false);
				}
			});
			button_3.setBounds(320, 250, 60, 20);
			p1.add(button_3);

			/*  �޸��û���Ϣ  */
			
			pane.addTab("�޸��û���Ϣ", p2);
			p2.setLayout(null);
			
			JLabel label_3 = new JLabel("�û���");
			label_3.setBounds(175, 22, 54, 15);
			p2.add(label_3);
			
			JTextField textField_3 = new JTextField();
			textField_3.setBounds(230, 20, 120, 21);
			p2.add(textField_3);
			textField_3.setColumns(10);
			
			 JLabel label_4 = new JLabel("ԭ����");
			label_4.setBounds(175, 72, 54, 15);
			p2.add(label_4);
			
			JTextField textField_4 = new JTextField();
			textField_4.setBounds(230, 70, 120, 21);
			p2.add(textField_4);
			textField_4.setColumns(10);
			
			JLabel label_5 = new JLabel("�û����");
			label_5.setBounds(175, 132, 54, 15);
			p2.add(label_5);
			
			JTextField textField_5 = new JTextField();
			textField_5.setBounds(230, 130, 120, 21);
			p2.add(textField_5);
			textField_5.setColumns(10);
			
			JLabel label_6 = new JLabel("������");
			label_6.setBounds(175, 182, 54, 15);
			p2.add(label_6);
			
			JTextField textField_6 = new JTextField();
			textField_6.setBounds(230, 180, 120, 21);
			p2.add(textField_6);
			textField_6.setColumns(10);
			
			JButton button_4 = new JButton("ȷ��");
			button_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = textField_3.getText();
					String password = textField_4.getText();
					String role = textField_5.getText();
					String newPass = textField_6.getText();
					
					@SuppressWarnings("unused")
					User user;
					try
					{
						if((user=DataProcessing.searchUser(name, password))!=null)
						{
							DataProcessing.updateUser(name, newPass, role);
							new DealMessage("�޸��û��ɹ�");
							f1.setVisible(false);
						}
						else
							new DealMessage("δ�ҵ����û��������ٴ�����");
					}
					catch(SQLException e1)
					{
						new DealMessage("���ݿ������������");
					}
				}
			});
			button_4.setBounds(180, 250, 60, 20);
			p2.add(button_4);
			
			JButton button_5 = new JButton("ȡ��");
			button_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					f1.setVisible(true);
				}
			});
			button_5.setBounds(320, 250, 60, 20);
			p2.add(button_5);
			
			
			f1.setContentPane(pane);
			f1.setVisible(true);
			
			
			
			
		
		 
		 
		 
		 f1.setVisible(true);
		
	}

}
