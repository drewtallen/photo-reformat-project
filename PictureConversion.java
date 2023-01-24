//Drew Allen July 22, 2022
/*A Program to format & resize photos from a chosen folder.
Currently converts image files to PNG format & resizes them to user's chosen scale--
Resizing is done by multiplying original photos width/height by this scale.*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class PictureConversion {
			
	public static void main(String[] args) {//throws IOException, FileNotFoundException {
		
		@SuppressWarnings("unused")
		ConvertFrame frame = new ConvertFrame();
	}
	
	//Counts the total number of files in the given pathway
	public static int countFiles(File[] directory) {
		if(directory!=null) {
			int count=0;
			for(@SuppressWarnings("unused") File file:directory)
				count+=1;
			return count;
		}
		else
			throw new NullPointerException("Error: the folder is empty or the pathway is incorrect");
	}
	
	//Prints the total number of files in the given pathway
	public static void printCount(File[] directory) {
		System.out.println("\nThere are " + countFiles(directory) +" files in this directory.");
	}
	
	//Counts the number of files that are specifically image files
	public static int countPictures(File[] contents) {
		if(contents!=null) {
			int count=0;
			for(int i=0; i<countFiles(contents);i++) {
				try {
				    BufferedImage image = ImageIO.read(new File(contents[i].toString()));
				    if (image != null) 
				    	count+=1;
				} catch(IOException ex) {}
			}
			return count;
		}
		else
			System.out.println("Folder is empty.");
			return 0;
	}
	
	//Currently generates the array for Picture objects. If image doesn't load (if file isn't an image) --
	//the array location remains Null.

	public static Picture[] createPictureArray(File[] directory) {
		if(directory!=null) {
			int arraySize = countFiles(directory);
			Picture[] imageArray = new Picture[arraySize];
			for(int i=0; i<arraySize;i++) {
				try {
				    BufferedImage image = ImageIO.read(new File(directory[i].toString()));
				    if(image != null) {
				    	Picture pic = new Picture();
				    	pic.setInPath(directory[i].getAbsolutePath());
				    	pic.setOutPath(pic.getInPath().substring(0, pic.getInPath().lastIndexOf('.')) + i + ".PNG");
				    	pic.setWidth(image.getWidth());
				    	pic.setHeight(image.getHeight());
				    	imageArray[i] = pic;
				    }
				} catch(IOException ex) {}
			}
			return imageArray;
		}
		else
			throw new NullPointerException("Error: the folder is empty or the pathway is incorrect");
	}
	
	//Converts images in Pictures[] array to PNG format & resizes them to 55% of their original size
	public static void convertPictures(Picture[] pictures, double scale) throws IOException {
		int type;
		for(int i=0;i<pictures.length;i++) {
			try {
				if(pictures[i]!=null) {
					FileInputStream inputStream = new FileInputStream(pictures[i].getInPath());
					FileOutputStream outputStream = new FileOutputStream(pictures[i].getOutPath());
					BufferedImage convertedPicture = ImageIO.read(inputStream);
					type = convertedPicture.getType();
					BufferedImage resizedPicture = resizePicture(convertedPicture, type, 
							(int)Math.ceil(pictures[i].getWidth()*scale), 
							(int)Math.ceil(pictures[i].getHeight()*scale));
					ImageIO.write(resizedPicture, "PNG", outputStream);
					outputStream.close();
					inputStream.close();
					File originalPicture = new File(pictures[i].getInPath());
					originalPicture.delete();
				}
			} catch (IllegalArgumentException error) {
				System.out.println("An error occurred during conversion...");
				error.printStackTrace();
			}
		}
		System.out.println("Conversion complete.");
	}
	
	private static BufferedImage resizePicture(BufferedImage originalImage, int type, int width, int height) {
	    BufferedImage resizedPicture = new BufferedImage(width, height, type);
	    Graphics2D g = resizedPicture.createGraphics();
	    g.drawImage(originalImage, 0, 0, width, height, null);
	    g.dispose();
	    return resizedPicture;
	}
}