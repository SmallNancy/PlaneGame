package cn.plane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.sun.org.apache.bcel.internal.classfile.Constant;

public class MyGameFrame extends Frame{
	Image bg = GameUtil.getImage("images/bg.jpg");
	Image planeImg = GameUtil.getImage("images/plane.png");
	Plane plane = new Plane(planeImg, 150, 150);
	/**
	 * 自动调用paint方法
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g);
		
	}
	class PaintThread extends Thread{
		@Override
		public void run() {
			while (true) {
				System.out.println("huahua!!");
		       repaint();//重画窗口
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
	 *   添加双缓冲
	 */
	private Image offScreenImage = null;
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(500,500);
			Graphics goff = offScreenImage.getGraphics();
			paint(goff);
			g.drawImage(offScreenImage, 0, 0, null);
		}
	}
    /**
              * 初始化窗口
     */
	public void initFrame() {
		this.setTitle("planeGame");
		this.setSize(500, 500);
		this.setLocation(300, 300);
		this.setVisible(true);
		
		/**
		 *    匿名内部类， 添加事件监听器
		 */
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		new PaintThread().start();//启动线程
		
		
	}
	public static void main(String[] args) {
		MyGameFrame mf = new MyGameFrame();
		mf.initFrame();
	}
}
