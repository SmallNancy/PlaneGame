package cn.plane;

import java.awt.Graphics;
import java.awt.Image;

public class Explode {
	double x,y;
	static Image[] imgs = new Image[16];
	static {
		for(int i =0;i < imgs.length;i++) {
			imgs[i] = GameUtil.getImage("images\\explode\\e" + i + ".gif");
		}
	}
	int count ;
	public void draw(Graphics g) {
		if(count < 16) {
			g.drawImage(imgs[count], (int)x, (int)y, null);
			count ++;
		}
	}
	public Explode(double x,double y) {
		this.x = x;
		this.y = y;
	}

}
