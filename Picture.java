//Drew Allen
//Small class for Picture objects to hold basic information.
//Each object holds the original picture's file path & width/height

public class Picture {

	private String inPath;
	private String outPath;
	private int width;
	private int height;
	
	public Picture() {
		this.inPath = "";
		this.outPath = "";
		this.width = 0;
		this.height = 0;
	}

	public String getInPath() {
		return inPath;
	}

	public void setInPath(String inPath) {
		this.inPath = inPath;
	}

	public String getOutPath() {
		return outPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public String toString() {
		return("Input path: " + inPath +
			   "\nOutput path: " + outPath +
			   "\nWidth: " + width + " pixels" +
			   "\nHeight: " + height + " pixels");
	}
}