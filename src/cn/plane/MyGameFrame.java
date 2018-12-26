package cn.plane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyGameFrame extends Frame{
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		Font f = g.getFont();
		g.setColor(Color.green);
		g.drawLine(100, 100, 300, 300);
		g.setColor(c);
	}
    /**
              * ��ʼ������
     */
	public void initFrame() {
		this.setTitle("planeGame");
		this.setSize(500, 500);
		this.setLocation(300, 300);
		this.setVisible(true);
		
		/**
		 *    �����ڲ��࣬ ����¼�������
		 */
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	public static void main(String[] args) {
		MyGameFrame mf = new MyGameFrame();
		mf.initFrame();
	}
}
