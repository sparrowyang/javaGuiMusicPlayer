package gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 * Í·²¿Í¼Æ¬
 * @author sparrow
 *
 */
public class Headbar {
	public JLabel label= new JLabel();
	Icon icon =new ImageIcon("image/images/head.jpg");
	public Headbar() {
		// TODO Auto-generated constructor stub
		label.setIcon(icon);
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
	}
}
