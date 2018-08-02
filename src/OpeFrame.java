import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;





public class OpeFrame {
	
	public OpeFrame() {
		
		JFrame f1= new JFrame("档案管理员界面");
		f1.setBounds(350,200,600,450);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   Container c=f1.getContentPane();
	   JPanel p=new JPanel();
	   c.add(p);
	   p.setBackground(Color.lightGray);
		f1.setBackground(Color.lightGray);
	   p.setBorder(new EmptyBorder(5, 5, 5, 5));
		 p.setLayout(new BorderLayout(0, 0));
		 f1.setContentPane(p);
		 p.setLayout(null);
		 
			JMenuBar menuBar = new JMenuBar();
			f1.setJMenuBar(menuBar);
			JMenu menu = new JMenu("文档管理");
			menuBar.add(menu);
			
			JMenuItem menuItem= new JMenuItem("上传");
			
			menu.add(menuItem);
			menuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Fileload();
				}
			});
			
			JMenuItem menuItem_1 = new JMenuItem("下载");
			menuItem_1.setEnabled(true);
			menu.add(menuItem_1);
			menuItem_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Fileload();
				}
			});
		
			
			
			
			JMenu menu_1= new JMenu("个人信息管理");
			menuBar.add(menu_1);
			
			JMenuItem menuItem_3 = new JMenuItem("修改密码");
			menuItem_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ChangePassword();
				}
			});
			menu_1.add(menuItem_3);
			
			
			
			
			

			f1.setVisible(true);
			
			
	}

}
