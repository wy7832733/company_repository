package cn.cyit.traffic.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;
/**
 * 在图片指定位置，加上指定大小的图片、文字生成新图片的工具类 
 * @author wuyao
 * @date 2019年4月29日上午11:09:58
 */
public class ImgEditUtils {

	   /**
     * @param srcImgPath 源图片路径
     * @param tarImgPath 保存的图片路径
     * @param waterMarkContent 水印内容
     * @param markContentColor 水印颜色
     * @param font 水印字体
     */
    public void addWaterMark(String srcImgPath, String tarImgPath,List<Map<String,Object>> fontList,List<Map<String,Object>> coverImgList) throws Exception{
        
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
           
            // 画底图
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            System.out.println(fontList.toString());
            //循环画文字
            for (Map<String, Object> font1 : fontList) {
                g.setColor((Color)font1.get("color")); //根据图片的背景设置水印颜色
                g.setFont((Font)font1.get("font")); 
                String content=(String)font1.get("content");
                Integer x=Integer.valueOf(font1.get("x")+"");
                Integer y=Integer.valueOf(font1.get("y")+"");
                g.drawString(oConvertUtils.checkNull(content), x, y);
                
			}
            System.out.println(coverImgList.toString());
            for (Map<String, Object> cover : coverImgList) {
                Integer x=Integer.valueOf(cover.get("x")+"");
                Integer y=Integer.valueOf(cover.get("y")+"");				
                Integer width=Integer.valueOf(cover.get("width")+"");
                Integer height=Integer.valueOf(cover.get("height")+"");	
                String src=(String)cover.get("src");
                File coverFile = new File(src); //覆盖层
                BufferedImage coverImg = ImageIO.read(coverFile); 
                g.drawImage(coverImg, x, y, width, height, null);
			}
            // 输出图片  
            g.dispose();  
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);  
            //图片压缩
            bufImg=ImageUtils.maxLengthResize2(bufImg, 800, 0.8f);
            ImageIO.write(bufImg, "png", outImgStream);
            System.out.println("--------绘图完成--------");  
            outImgStream.flush();  
            outImgStream.close();  
            
                        
    }
    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {  
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());  
    }  
    /**
     * 主方法用于测试  
     * @param args
     * @throws Exception
     * @author wuyao
     * @date 2019年4月29日上午11:09:17
     */
    public static void main(String[] args) throws Exception {

    } 
    
    public static void drawPic(Integer type) throws Exception{
    	if(type==1){//安全培训合格证
            String srcImgPath="C://Users//Administrator//Desktop//证书图片原件//aqpxhgz1.jpg"; //源图片地址
            String tarImgPath="C://Users//Administrator//Desktop//aqpxhgz1_draw.jpg"; //待存储的地址
            List<Map<String,Object>> fontList=new ArrayList<>();
            List<Map<String,Object>> coverImgList=new ArrayList<>();
            
            Map<String,Object> font1=new HashMap<String,Object>();
            font1.put("color", Color.black);
            font1.put("content", "吴  尧");
            font1.put("x",1150);
            font1.put("y",1260);
            font1.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font1);
            
            Map<String,Object> font2=new HashMap<String,Object>();
            font2.put("color", Color.black);
            font2.put("content", "测试组");
            font2.put("x",1150);
            font2.put("y",1380);
            font2.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font2);
            
            Map<String,Object> font3=new HashMap<String,Object>();
            font3.put("color", Color.black);
            font3.put("content", "321027199102192736");
            font3.put("x",1020);
            font3.put("y",1500);
            font3.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font3);
            
            Map<String,Object> font4=new HashMap<String,Object>();
            font4.put("color", Color.black);
            font4.put("content", "B1");
            font4.put("x",1150);
            font4.put("y",1620);
            font4.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font4);
            
            Map<String,Object> font5=new HashMap<String,Object>();
            font5.put("color", Color.black);
            font5.put("content", "ASD20190507");
            font5.put("x",1080);
            font5.put("y",1740);
            font5.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font5);
            
            Map<String,Object> font6=new HashMap<String,Object>();
            font6.put("color", Color.black);
            font6.put("content", "2019   3     7");
            font6.put("x",2220);
            font6.put("y",810);
            font6.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font6);
            
            Map<String,Object> font7=new HashMap<String,Object>();
            font7.put("color", Color.black);
            font7.put("content", "2019   5     7");
            font7.put("x",1870);
            font7.put("y",945);
            font7.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font7);
            
            Map<String,Object> font8=new HashMap<String,Object>();
            font8.put("color", Color.black);
            font8.put("content", "华东石油技师学院");
            font8.put("x",2205);
            font8.put("y",1455);
            font8.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font8);
            
            Map<String,Object> font9=new HashMap<String,Object>();
            font9.put("color", Color.black);
            font9.put("content", "2019年5月10日");
            font9.put("x",2205);
            font9.put("y",1575);
            font9.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font9);
            
            Map<String,Object> font10=new HashMap<String,Object>();
            font10.put("color", Color.black);
            font10.put("content", "2019.5.10   2020.5.10");
            font10.put("x",2150);
            font10.put("y",1745);
            font10.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font10);
            
            Map<String,Object> coverImg1=new HashMap<String,Object>();
            coverImg1.put("src", "C://Users//Administrator//Desktop//8.JPG");
            coverImg1.put("x", 890);
            coverImg1.put("y", 460);
            coverImg1.put("width", 418);
            coverImg1.put("height", 610);
            coverImgList.add(coverImg1);
            
            new ImgEditUtils().addWaterMark(srcImgPath, tarImgPath, fontList,coverImgList);    		
    	}else if(type==2){//健康安全环境管理培训证书
            String srcImgPath="C://Users//Administrator//Desktop//证书图片原件//hjglpxzs.jpg"; //源图片地址
            String tarImgPath="C://Users//Administrator//Desktop//hjglpxzs_test.jpg"; //待存储的地址
            List<Map<String,Object>> fontList=new ArrayList<>();
            List<Map<String,Object>> coverImgList=new ArrayList<>();
            
            Map<String,Object> font1=new HashMap<String,Object>();
            font1.put("color", Color.black);
            font1.put("content", "吴尧");
            font1.put("x",725);
            font1.put("y",980);
            font1.put("font", new Font("宋体", Font.BOLD, 50));
            fontList.add(font1);  
            
            Map<String,Object> font2=new HashMap<String,Object>();
            font2.put("color", Color.black);
            font2.put("content", "wuyao");
            font2.put("x",2090);
            font2.put("y",990);
            font2.put("font", new Font("宋体", Font.BOLD, 50));
            fontList.add(font2);  
            
            Map<String,Object> font3=new HashMap<String,Object>();
            font3.put("color", Color.black);
            font3.put("content", "2019年5月7日");
            font3.put("x",935);
            font3.put("y",1340);
            font3.put("font", new Font("宋体", Font.BOLD, 45));
            fontList.add(font3);  
            
            Map<String,Object> font4=new HashMap<String,Object>();
            font4.put("color", Color.black);
            font4.put("content", "18个月");
            font4.put("x",935);
            font4.put("y",1410);
            font4.put("font", new Font("宋体", Font.BOLD, 45));
            fontList.add(font4);  
            
            new ImgEditUtils().addWaterMark(srcImgPath, tarImgPath, fontList,coverImgList);
    	}else if(type==3){//井控证，正反面都要，反面不需加工，直接给原图
            String srcImgPath="C://Users//Administrator//Desktop//证书图片原件//jkz1.jpg"; //源图片地址
            String tarImgPath="C://Users//Administrator//Desktop//jkx1_test.jpg"; //待存储的地址
            List<Map<String,Object>> fontList=new ArrayList<>();
            List<Map<String,Object>> coverImgList=new ArrayList<>(); 

            Map<String,Object> font1=new HashMap<String,Object>();
            font1.put("color", Color.black);
            font1.put("content", "吴尧");
            font1.put("x",550);
            font1.put("y",1800);
            font1.put("font", new Font("宋体", Font.BOLD, 52));
            fontList.add(font1);  
            
            Map<String,Object> font2=new HashMap<String,Object>();
            font2.put("color", Color.black);
            font2.put("content", "岗位1");
            font2.put("x",1300);
            font2.put("y",1800);
            font2.put("font", new Font("宋体", Font.BOLD, 52));
            fontList.add(font2);  
            
            Map<String,Object> font3=new HashMap<String,Object>();
            font3.put("color", Color.black);
            font3.put("content", "B1");
            font3.put("x",2135);
            font3.put("y",1800);
            font3.put("font", new Font("宋体", Font.BOLD, 52));
            fontList.add(font3);  
            
            Map<String,Object> font4=new HashMap<String,Object>();
            font4.put("color", Color.black);
            font4.put("content", "18个月");
            font4.put("x",840);
            font4.put("y",1950);
            font4.put("font", new Font("宋体", Font.BOLD, 52));
            fontList.add(font4);  
            
            Map<String,Object> font5=new HashMap<String,Object>();
            font5.put("color", Color.black);
            font5.put("content", "321027199102192736");
            font5.put("x",1720);
            font5.put("y",1950);
            font5.put("font", new Font("宋体", Font.BOLD, 52));
            fontList.add(font5);  
            
            Map<String,Object> font6=new HashMap<String,Object>();
            font6.put("color", Color.black);
            font6.put("content", "华东石油技师学院");
            font6.put("x",840);
            font6.put("y",2100);
            font6.put("font", new Font("宋体", Font.BOLD, 52));
            fontList.add(font6);  
            
            Map<String,Object> font7=new HashMap<String,Object>();
            font7.put("color", Color.black);
            font7.put("content", "华东石油技师学院");
            font7.put("x",840);
            font7.put("y",2250);
            font7.put("font", new Font("宋体", Font.BOLD, 52));
            fontList.add(font7);  
            
            Map<String,Object> font8=new HashMap<String,Object>();
            font8.put("color", Color.black);
            font8.put("content", "20190508");
            font8.put("x",816);
            font8.put("y",2900);
            font8.put("font", new Font("宋体", Font.BOLD, 52));
            fontList.add(font8);  
            
            Map<String,Object> font9=new HashMap<String,Object>();
            font9.put("color", Color.black);
            font9.put("content", "吴尧");
            font9.put("x",590);
            font9.put("y",2950);
            font9.put("font", new Font("宋体", Font.BOLD, 42));
            fontList.add(font9);  
            
            Map<String,Object> font10=new HashMap<String,Object>();
            font10.put("color", Color.black);
            font10.put("content", "测试组");
            font10.put("x",630);
            font10.put("y",3030);
            font10.put("font", new Font("宋体", Font.BOLD, 42));
            fontList.add(font10);  
            
            Map<String,Object> font11=new HashMap<String,Object>();
            font11.put("color", Color.black);
            font11.put("content", "B1");
            font11.put("x",650);
            font11.put("y",3085);
            font11.put("font", new Font("宋体", Font.BOLD, 42));
            fontList.add(font11);  
            
            Map<String,Object> font12=new HashMap<String,Object>();
            font12.put("color", Color.black);
            font12.put("content", "321027199102192736");
            font12.put("x",730);
            font12.put("y",3140);
            font12.put("font", new Font("宋体", Font.BOLD, 42));
            fontList.add(font12);  
            
            Map<String,Object> font13=new HashMap<String,Object>();
            font13.put("color", Color.black);
            font13.put("content", "18个月");
            font13.put("x",730);
            font13.put("y",3195);
            font13.put("font", new Font("宋体", Font.BOLD, 42));
            fontList.add(font13);  
            
            Map<String,Object> font14=new HashMap<String,Object>();
            font14.put("color", Color.black);
            font14.put("content", "华东石油技师学院");
            font14.put("x",830);
            font14.put("y",3265);
            font14.put("font", new Font("宋体", Font.BOLD, 42));
            fontList.add(font14);  
            
            Map<String,Object> font15=new HashMap<String,Object>();
            font15.put("color", Color.black);
            font15.put("content", "20190508");
            font15.put("x",1154);
            font15.put("y",880);
            font15.put("font", new Font("宋体", Font.BOLD, 52));
            fontList.add(font15);  
            
            new ImgEditUtils().addWaterMark(srcImgPath, tarImgPath, fontList,coverImgList);
    	}else if(type==4){
            String srcImgPath="C://Users//Administrator//Desktop//证书图片原件//lhqfhjspxzs.jpg"; //源图片地址
            String tarImgPath="C://Users//Administrator//Desktop//lhqfhjspxzs_test.jpg"; //待存储的地址
            List<Map<String,Object>> fontList=new ArrayList<>();
            List<Map<String,Object>> coverImgList=new ArrayList<>();

            Map<String,Object> font1=new HashMap<String,Object>();
            font1.put("color", Color.black);
            font1.put("content", "吴尧");
            font1.put("x",860);
            font1.put("y",920);
            font1.put("font", new Font("宋体", Font.BOLD, 50));
            fontList.add(font1);
            
            Map<String,Object> font2=new HashMap<String,Object>();
            font2.put("color", Color.black);
            font2.put("content", "wuyao");
            font2.put("x",1555);
            font2.put("y",990);
            font2.put("font", new Font("宋体", Font.BOLD, 50));
            fontList.add(font2);
            
            Map<String,Object> font3=new HashMap<String,Object>();
            font3.put("color", Color.black);
            font3.put("content", "2019年5月8日");
            font3.put("x",1030);
            font3.put("y",1230);
            font3.put("font", new Font("宋体", Font.BOLD, 50));
            fontList.add(font3);
            
            Map<String,Object> font4=new HashMap<String,Object>();
            font4.put("color", Color.black);
            font4.put("content", "18个月");
            font4.put("x",1010);
            font4.put("y",1310);
            font4.put("font", new Font("宋体", Font.BOLD, 50));
            fontList.add(font4);
            
            Map<String,Object> font5=new HashMap<String,Object>();
            font5.put("color", Color.black);
            font5.put("content", "20190508");
            font5.put("x",2445);
            font5.put("y",1700);
            font5.put("font", new Font("宋体", Font.BOLD, 50));
            fontList.add(font5);
            
            new ImgEditUtils().addWaterMark(srcImgPath, tarImgPath, fontList,coverImgList);
    		
    	}else if(type==5){//培训合格证（有正反两面）
    		
            String srcImgPath="C://Users//Administrator//Desktop//证书图片原件//pxhgzs1.jpg"; //源图片地址
            String tarImgPath="C://Users//Administrator//Desktop//pxhgzs1_draw.jpg"; //待存储的地址
            List<Map<String,Object>> fontList=new ArrayList<>();
            List<Map<String,Object>> coverImgList=new ArrayList<>();
            
            Map<String,Object> font1=new HashMap<String,Object>();
            font1.put("color", Color.black);
            font1.put("content", "吴  尧");
            font1.put("x",1150);
            font1.put("y",1160);
            font1.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font1);
            
            Map<String,Object> font2=new HashMap<String,Object>();
            font2.put("color", Color.black);
            font2.put("content", "测试组");
            font2.put("x",1150);
            font2.put("y",1280);
            font2.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font2);
            
            Map<String,Object> font3=new HashMap<String,Object>();
            font3.put("color", Color.black);
            font3.put("content", "321027199102192736");
            font3.put("x",1020);
            font3.put("y",1400);
            font3.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font3);
            
            Map<String,Object> font4=new HashMap<String,Object>();
            font4.put("color", Color.black);
            font4.put("content", "B1");
            font4.put("x",1150);
            font4.put("y",1520);
            font4.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font4);
            
            Map<String,Object> font5=new HashMap<String,Object>();
            font5.put("color", Color.black);
            font5.put("content", "ASD20190507");
            font5.put("x",1080);
            font5.put("y",1660);
            font5.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font5);
            
            Map<String,Object> font6=new HashMap<String,Object>();
            font6.put("color", Color.black);
            font6.put("content", "2019   3     7");
            font6.put("x",2220);
            font6.put("y",720);
            font6.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font6);
            
            Map<String,Object> font7=new HashMap<String,Object>();
            font7.put("color", Color.black);
            font7.put("content", "2019   5     7");
            font7.put("x",1860);
            font7.put("y",875);
            font7.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font7);
            /*
            Map<String,Object> font8=new HashMap<String,Object>();
            font8.put("color", Color.black);
            font8.put("content", "华东石油技师学院");
            font8.put("x",2205);
            font8.put("y",1405);
            font8.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font8);
            */
            Map<String,Object> font9=new HashMap<String,Object>();
            font9.put("color", Color.black);
            font9.put("content", "2019年5月10日");
            font9.put("x",2205);
            font9.put("y",1525);
            font9.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font9);
            
            Map<String,Object> font10=new HashMap<String,Object>();
            font10.put("color", Color.black);
            font10.put("content", "2019.5.10   2020.5.10");
            font10.put("x",2110);
            font10.put("y",1670);
            font10.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font10);
            
            Map<String,Object> coverImg1=new HashMap<String,Object>();
            coverImg1.put("src", "C://Users//Administrator//Desktop//8.JPG");
            coverImg1.put("x", 875);
            coverImg1.put("y", 355);
            coverImg1.put("width", 418);
            coverImg1.put("height", 610);
            coverImgList.add(coverImg1);
            
            new ImgEditUtils().addWaterMark(srcImgPath, tarImgPath, fontList,coverImgList);        		
    	}else if(type==6){//培训证书（有正反两面）
            String srcImgPath="C://Users//Administrator//Desktop//证书图片原件//pxzs1.jpg"; //源图片地址
            String tarImgPath="C://Users//Administrator//Desktop//pxzs1_draw.jpg"; //待存储的地址
            List<Map<String,Object>> fontList=new ArrayList<>();
            List<Map<String,Object>> coverImgList=new ArrayList<>();
            
            Map<String,Object> font1=new HashMap<String,Object>();
            font1.put("color", Color.black);
            font1.put("content", "pxhgz20190508");
            font1.put("x",760);
            font1.put("y",635);
            font1.put("font", new Font("黑体", Font.BOLD, 60));
            fontList.add(font1); 
            
            new ImgEditUtils().addWaterMark(srcImgPath, tarImgPath, fontList,coverImgList);
    	}else if(type==7){
            String srcImgPath="C://Users//Administrator//Desktop//证书图片原件//xxsgz1.jpg"; //源图片地址
            String tarImgPath="C://Users//Administrator//Desktop//xxsgz1_draw.jpg"; //待存储的地址
            List<Map<String,Object>> fontList=new ArrayList<>();
            List<Map<String,Object>> coverImgList=new ArrayList<>();
            
            Map<String,Object> font1=new HashMap<String,Object>();
            font1.put("color", Color.black);
            font1.put("content", "测试组");
            font1.put("x",1580);
            font1.put("y",1385);
            font1.put("font", new Font("黑体", Font.BOLD, 48));
            fontList.add(font1); 
            
            Map<String,Object> font2=new HashMap<String,Object>();
            font2.put("color", Color.black);
            font2.put("content", "华东石油技师学院");
            font2.put("x",1580);
            font2.put("y",1470);
            font2.put("font", new Font("黑体", Font.BOLD, 48));
            fontList.add(font2); 
            
            Map<String,Object> font3=new HashMap<String,Object>();
            font3.put("color", Color.black);
            font3.put("content", "吴尧");
            font3.put("x",1580);
            font3.put("y",1550);
            font3.put("font", new Font("黑体", Font.BOLD, 48));
            fontList.add(font3); 
            
            Map<String,Object> font4=new HashMap<String,Object>();
            font4.put("color", Color.black);
            font4.put("content", "2019年5月8日");
            font4.put("x",1580);
            font4.put("y",1635);
            font4.put("font", new Font("黑体", Font.BOLD, 48));
            fontList.add(font4); 
            
            Map<String,Object> font5=new HashMap<String,Object>();
            font5.put("color", Color.black);
            font5.put("content", "江苏省扬州市邗江区");
            font5.put("x",1580);
            font5.put("y",1720);
            font5.put("font", new Font("黑体", Font.BOLD, 48));
            fontList.add(font5); 
            
            Map<String,Object> font6=new HashMap<String,Object>();
            font6.put("color", Color.black);
            font6.put("content", "15189732329");
            font6.put("x",1580);
            font6.put("y",1805);
            font6.put("font", new Font("黑体", Font.BOLD, 48));
            fontList.add(font6); 
            
            Map<String,Object> font7=new HashMap<String,Object>();
            font7.put("color", Color.black);
            font7.put("content", "pxzs89757");
            font7.put("x",1580);
            font7.put("y",1890);
            font7.put("font", new Font("黑体", Font.BOLD, 48));
            fontList.add(font7); 
            
            Map<String,Object> font8=new HashMap<String,Object>();
            font8.put("color", Color.black);
            font8.put("content", "2019年5月8日");
            font8.put("x",1580);
            font8.put("y",1975);
            font8.put("font", new Font("黑体", Font.BOLD, 48));
            fontList.add(font8); 
            
            Map<String,Object> font9=new HashMap<String,Object>();
            font9.put("color", Color.black);
            font9.put("content", "18个月");
            font9.put("x",1580);
            font9.put("y",2060);
            font9.put("font", new Font("黑体", Font.BOLD, 48));
            fontList.add(font9); 
            
            Map<String,Object> coverImg1=new HashMap<String,Object>();
            coverImg1.put("src", "C://Users//Administrator//Desktop//8.JPG");
            coverImg1.put("x", 1060);
            coverImg1.put("y", 1485);
            coverImg1.put("width", 238);
            coverImg1.put("height", 355);
            coverImgList.add(coverImg1);            
            
            new ImgEditUtils().addWaterMark(srcImgPath, tarImgPath, fontList,coverImgList);    		
    		
    	}else if(type==9){//作业许可证（两张）
            String srcImgPath="C://Users//Administrator//Desktop//证书图片原件//zyzgxkz1.jpg"; //源图片地址
            String tarImgPath="C://Users//Administrator//Desktop//zyzgxkz1_draw.jpg"; //待存储的地址
            List<Map<String,Object>> fontList=new ArrayList<>();
            List<Map<String,Object>> coverImgList=new ArrayList<>();    		
 
            Map<String,Object> font1=new HashMap<String,Object>();
            font1.put("color", Color.black);
            font1.put("content", "吴尧");
            font1.put("x",1280);
            font1.put("y",1200);
            font1.put("font", new Font("黑体", Font.BOLD, 40));
            fontList.add(font1);             
            
            Map<String,Object> font2=new HashMap<String,Object>();
            font2.put("color", Color.black);
            font2.put("content", "2019年5月8日");
            font2.put("x",1460);
            font2.put("y",1480);
            font2.put("font", new Font("黑体", Font.BOLD, 40));
            fontList.add(font2);             
            
            Map<String,Object> font3=new HashMap<String,Object>();
            font3.put("color", Color.black);
            font3.put("content", "zyxk20190508");
            font3.put("x",1460);
            font3.put("y",1570);
            font3.put("font", new Font("黑体", Font.BOLD, 40));
            fontList.add(font3);             
            
            
            Map<String,Object> coverImg1=new HashMap<String,Object>();
            coverImg1.put("src", "C://Users//Administrator//Desktop//8.JPG");
            coverImg1.put("x", 900);
            coverImg1.put("y", 1173);
            coverImg1.put("width", 318);
            coverImg1.put("height", 480);
            coverImgList.add(coverImg1);  
            
            new ImgEditUtils().addWaterMark(srcImgPath, tarImgPath, fontList,coverImgList);
    	}else if(type==10){//培训证书
    		
    	}
    }     
	
}
