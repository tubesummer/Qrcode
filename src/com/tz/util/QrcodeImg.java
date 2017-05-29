package com.tz.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;
	
/**
 * 二维码生成工具类
 * @author Yxw
 *
 */
public class QrcodeImg {
	//生成二维码的方法
	/**
	 * 生成二维码的方法
	 * @param content 二维码内容
	 * @param imgpath 二维码路径
	 */
	public static void getQrcodeImg(String content,String imgPath){
			//实例话对象
			Qrcode qrQrcode=new Qrcode();
			//编码N A K
			qrQrcode.setQrcodeEncodeMode('B');
			//排错率
			qrQrcode.setQrcodeErrorCorrect('M');
			//版本
			qrQrcode.setQrcodeVersion(15);
			int width=235;
			int height=235;
			//画板
			BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			//绘制工具
			Graphics2D gs=image.createGraphics();
			//开始绘制
			//绘制矩形
			gs.clearRect(0, 0, width, height);
			//背景，内容颜色
			gs.setBackground(Color.WHITE);
			gs.setColor(Color.BLACK);
			//开始处理信息
			byte[] codeOut;
			try {
				codeOut=content.getBytes("utf-8");
				boolean[][] code=qrQrcode.calQrcode(codeOut);
				//遍历这个boolean二位数组判断
				for(int i=0;i<code.length;i++){
					for(int j=0;j<code.length;j++){
						if(code[j][i]){
							//如果为真就涂黑
							gs.fillRect(j*3+2, i*3+2, 3,3);
						}
					}
				}
				
				
				
				
				
				//施放资源
				gs.dispose();
				image.flush();
				//保存到指定路径
				ImageIO.write(image,"png",new File(imgPath));
				System.out.println("二维码生成成功");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
	}	
	
	public static void main(String[] args) {
		getQrcodeImg("手机号13131313","D:/shouji.png");	
	}
}
