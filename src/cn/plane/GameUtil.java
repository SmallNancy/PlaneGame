package cn.plane;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GameUtil {
	/**
	 *   工具类最好私有化
	 */
	private GameUtil() {
		
	}
	
	/**
	 *    指定路径文件的路径
	 * @param path
	 * @return
	 */
	public static Image getImage(String path) {
		BufferedImage bi = null;
		URL u = GameUtil.class.getClassLoader().getResource(path);
		try {
			bi = ImageIO.read(u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bi;
	}

}
