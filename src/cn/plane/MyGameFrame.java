package cn.plane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyGameFrame extends Frame{
	Image bg = GameUtil.getImage("images/bg.jpg");
	Image plane = GameUtil.getImage("images/plane.png");
	int planeX = 100;
	int planeY = 100;
	/**
	 * �Զ�����paint����
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		g.drawImage(plane, planeX, planeY, null);
		planeX ++;
		
	}
	class PaintThread extends Thread{
		@Override
		public void run() {
			while (true) {
				System.out.println("huahua!!");
		       repaint();//�ػ�����
		       try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
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
		
		new PaintThread().start();//�����߳�
	}
	public static void main(String[] args) {
		MyGameFrame mf = new MyGameFrame();
		mf.initFrame();
	}
}
