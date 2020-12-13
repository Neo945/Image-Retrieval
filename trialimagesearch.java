package miniproject;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class trialimagesearch 
{
	
	
	ArrayList<Double> percentage=new ArrayList<>();
	public void getImgDifference(BufferedImage i1, BufferedImage i2,int n) {
	
		 int width1 = i1.getWidth();
		    int height1 = i1.getHeight();
		    int width2 = i2.getWidth();
		    int height2 = i2.getHeight();
		    double percentage1 = 0.0, percentage2 = 0.0;
		    double[] hsv = new double[3];
		    
		    if ((width1!=width2)||(height1!=height2)) 
		         System.out.println("Both images should have same dimensions");
		    else {
		         long diff1 = 0, diff2 = 0;
		         for (int j = 0; j < height1; j++) {
		            for (int i = 0; i < width1; i++) {
		               //Getting the RGB values of a pixel
		               int pixel1 = i1.getRGB(i, j);
		               Color color1 = new Color(pixel1, true);
		               int r1 = color1.getRed();
		               int g1 = color1.getGreen();
		               int b1 = color1.getBlue();
		               hsv = rgb_to_hsv(r1,g1,b1);
		               double h1 = hsv[0];
		               double s1 = hsv[1];
		               double v1 = hsv[2];
		               int pixel2 = i2.getRGB(i, j);
		               Color color2 = new Color(pixel2, true);
		               int r2 = color2.getRed();
		               int g2 = color2.getGreen();
		               int b2= color2.getBlue();
		               hsv = rgb_to_hsv(r1,g1,b1);
		               double h2 = hsv[0];
		               double s2 = hsv[1];
		               double v2 = hsv[2];
		               //sum of differences of RGB values of the two images
		               long data1 = Math.abs(r1-r2)+Math.abs(g1-g2)+ Math.abs(b1-b2);
		               diff1 = diff1+data1;
		               long data2 = Math.abs(r1-r2)+Math.abs(g1-g2)+ Math.abs(b1-b2);
		               diff2 = diff2+data2;
		            }
		         }
		         double avg1 = diff1/(width1*height1*3);
		         percentage1 = (avg1/255)*100;
                         percentage.add(percentage1);
		         double avg2 = diff2/(width1*height1*3);
		         percentage2 = (avg2/255)*100;
		    }
		    System.out.println("Difference from RGB: "+ percentage1);
		    System.out.println("Difference from RGB: "+ percentage.get(n));
                    
	}

	
	public double[] rgb_to_hsv(double r, double g, double b)
	{
		
	    // R, G, B values are divided by 255 to change the range from 0..255 to 0..1 
	    r = r / 255.0; 
	    g = g / 255.0; 
	    b = b / 255.0; 
	  
	    // h, s, v = hue, saturation, value 
	    double cmax = Math.max(r, Math.max(g, b)); // maximum of r, g, b 
	    double cmin = Math.min(r, Math.min(g, b)); // minimum of r, g, b 
	    double diff = cmax - cmin; // diff of cmax and cmin. 
	    double h = -1, s = -1; 
	          
	    
	    if (cmax == cmin)     // if cmax and cmax are equal then h = 0 
	        h = 0; 
	    else if (cmax == r)           // if cmax equal r then compute h 
	            h = (60 * ((g - b) / diff) + 360) % 360; 
	    else if (cmax == g)    // if cmax equal g then compute h 
	            h = (60 * ((b - r) / diff) + 120) % 360; 
	    else if (cmax == b)          // if cmax equal b then compute h 
	            h = (60 * ((r - g) / diff) + 240) % 360; 
	  
	    if (cmax == 0)               // if cmax equal zero 
	        s = 0; 
	    else
	        s = (diff / cmax) * 100; 
	  
	    // compute v 
	    double v = cmax * 100; 
	    //System.out.println("(" + h + " " + s + " " + v + ")"); 
	    double[] hsv = {h,s,v};
	    return hsv;
	} 


	public static void main(String[] args) {
            		
	//Reading the image
	    File file1= new File("C:\\Users\\akshat\\OneDrive\\Desktop\\Mini Project\\final dataset\\Image6.jpg");
	   
            int i=1;
            while(i<=12)
            {
                String filename="C:\\Users\\akshat\\OneDrive\\Desktop\\Mini Project\\final dataset\\Image"+i+".jpg";
                System.out.println(filename);
                File file2= new File(filename);
                    try  {
	    	BufferedImage img1 = ImageIO.read(file1);
	    	BufferedImage img2 = ImageIO.read(file2);
                 new trialimagesearch().getImgDifference(img1, img2,i);
                 
	    } 
                    catch (Exception e) 
                    {
                        System.out.println("Error!!");
                    }
                i=i+1;
                }
            }
        for(int i=0;i<=12;i++)
        {
            if(percentage.get(i))
            {
                
            }
        }
    }