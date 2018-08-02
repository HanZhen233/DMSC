import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.sql.SQLException;
import java.awt.color.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Test{
	public static void main(String[] args) throws Exception {
		

	

		JFrame f1 = new JFrame("系统登录界面");
		f1.setBounds(350, 200, 600, 450);

		f1.setDefaultCloseOperation(DataProcessing.shutdown());
		
		Container c = f1.getContentPane();

		JPanel p = new JPanel();
		c.add(p);

		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		p.setLayout(new BorderLayout(0, 0));
		f1.setContentPane(p);
		p.setLayout(null);
		p.setBackground(Color.lightGray);
		JLabel label_0 = new JLabel("档案管理系统");
		label_0.setFont(new Font("楷体", Font.BOLD, 28));
		label_0.setBounds(190, 50, 300, 40);
		p.add(label_0);
		JLabel label = new JLabel("用户名");
		label.setBounds(170, 142, 54, 15);
		p.add(label);

		JLabel label_1 = new JLabel("密码");
		label_1.setBounds(175, 213, 54, 15);
		p.add(label_1);

		JTextField textField = new JTextField();
		textField.setBounds(220, 140, 135, 21);
		p.add(textField);
		textField.setColumns(10);

		JButton button = new JButton("登录");
		button.setBounds(180, 300, 60, 20);
		p.add(button);

		JButton button_1 = new JButton("退出");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setBounds(320, 300, 60, 20);
		p.add(button_1);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(220, 210, 135, 21);
		p.add(passwordField);
		f1.setVisible(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				char[] c1 = passwordField.getPassword();
				String password = new String(c1);
				User use;
				try {
					use = DataProcessing.searchUser(name, password);
					if (use != null)

					{
						use.showMenu();
						f1.setVisible(false);
					} else
						new DealMessage("您的密码或用户名输入错误");
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}

			}
		});

	}

}
