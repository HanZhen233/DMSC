import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class BrFrame {
	public BrFrame() {
		JFrame f1= new JFrame("��ͨ�û�");
		f1.setBounds(350,200,600,450);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   Container c=f1.getContentPane();
	   JPanel p=new JPanel();
	   c.add(p);
	   p.setBackground(Color.lightGray);
	   p.setBorder(new EmptyBorder(5, 5, 5, 5));
		 p.setLayout(new BorderLayout(0, 0));
		 f1.setContentPane(p);
		 p.setLayout(null);
		 
			JMenuBar menuBar = new JMenuBar();
			f1.setJMenuBar(menuBar);
			
			
			
			JMenu menu_1 = new JMenu("�ļ�����");
			menuBar.add(menu_1);
			
			JMenuItem menuItem_4 = new JMenuItem("�����ļ�");
			menuItem_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new DownLoadFrame();
				}
			});
			menu_1.add(menuItem_4);
			
			JMenu menu_2 = new JMenu("�޸ĸ�����Ϣ");
			menuBar.add(menu_2);
			
			JMenuItem menuItem_5 = new JMenuItem("�޸�����");
			menuItem_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ChangePassword();
				}
			});
			menu_2.add(menuItem_5);
		
		
		
		
		
		
		
		
		
		
		f1.setVisible(true);
				
	}

}
