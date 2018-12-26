package cn.plane;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 *    飞机游戏主窗口
 * @author Small
 *
 */
public class MyGameFrame extends Frame{
	Image bg = GameUtil.getImage("images/bg.jpg");
	Image planeImg = GameUtil.getImage("images/plane.png");
	Plane plane = new Plane(planeImg, 150, 150);
	Shell[] shells = new Shell[30];
	Explode bao = null ;
	/**
	 * 自动调用paint方法
	 */
	@Override
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		plane.drawSelf(g);
		for(int i =0;i<shells.length;i++) {
			shells[i].draw(g);
			//碰撞检测
			boolean crash = shells[i].getRect().intersects(plane.getRect());
			if(crash) {
				plane.live = false;
//				if(bao == null) {
//					bao = new Explode((int)plane.x, (int)plane.y);
//				}
				//bao.draw(g);
			}
		}
		
	}
	class PaintThread extends Thread{
		@Override
		public void run() {
			while (true) {
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
	 * 键盘监听内部类
	 */
	class KeyMoniter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			plane.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			super.keyReleased(e);
			plane.cancleDirection(e);
		}
		
	}
	
	/**
	 *   添加双缓冲
	 */
	private Image offScreenImage = null;
	public void update(Graphics g) {
		offScreenImage = this.createImage(Constant.GAME_WIGHT,Constant.GAME_HEIGHT);
		Graphics goff = offScreenImage.getGraphics();
		this.paint(goff);
		g.drawImage(offScreenImage, 0, 0, null);
		
	}
    /**
              * 初始化窗口
     */
	public void initFrame() {
		this.setTitle("planeGame");
		this.setSize(Constant.GAME_HEIGHT, Constant.GAME_WIGHT);
		this.setLocation(300, 300);
		this.setVisible(true);
		
		
		for(int i =0;i<shells.length;i++) {
			shells[i] = new Shell();
		}
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
		addKeyListener(new KeyMoniter());
	}
	public static void main(String[] args) {
		MyGameFrame mf = new MyGameFrame();
		mf.initFrame();
	}
}
