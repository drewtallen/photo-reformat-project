//GUI component for the PictureConversion program.


import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ConvertFrame {

	private static File directory = null;
	private static File[] directoryContents;
	
	private JFrame frame = new JFrame("Convert Pictures");
	private JLabel pathLabel = new JLabel("Choose a folder pathway:");
	private JLabel baseLabel = new JLabel("Convert a folder's images to PNG & reduce their size.");
	private JLabel scaleLabel = new JLabel("Scale size:");
	private JButton openButton = new JButton("Open");
	private JButton convertButton = new JButton("Convert");
	private JTextField pathDisplay = new JTextField(25);
	private JPanel mainPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private Cursor waitCursor = new Cursor(Cursor.WAIT_CURSOR);
	private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);
	private JOptionPane infoPane = new JOptionPane("Conversion in progress...\nThis may take a few minutes.", 
											              JOptionPane.INFORMATION_MESSAGE); 
	private JDialog infoBox = infoPane.createDialog("Converting");
	private JComboBox<Double> scaleBox;
	

	
	@SuppressWarnings("unchecked")
	public ConvertFrame() {
		openButton.addActionListener(new setDirectoryListener());
		convertButton.addActionListener(new convertListener());
		
		Double[] scaleOptions = {.10,.20,.30,.40,.50,.60,.70,.80,.90};
		scaleBox = new JComboBox(scaleOptions);
		frame.setLayout(new BorderLayout(35,38));
		mainPanel.add(pathLabel);
		mainPanel.add(pathDisplay);
		mainPanel.add(openButton);
		bottomPanel.add(scaleLabel);
		bottomPanel.add(scaleBox);
		bottomPanel.add(convertButton);
		frame.add(baseLabel, BorderLayout.NORTH);
		frame.add(mainPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		
		frame.setSize(350,220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	//Anonymous inner class for ActionListener to set directory folder
	class setDirectoryListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setDialogTitle("Select a pathway");
			int affirm = chooser.showOpenDialog(chooser);
			if(affirm == JFileChooser.APPROVE_OPTION) {
				directory = chooser.getSelectedFile();
				directoryContents = directory.listFiles();
				pathDisplay.setText(directory.toString());
				System.out.println(directory.toString());
			}
		}
	}
	
	//GUI adapted conversion method
	class convertListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			progressText pt = new progressText();
			if(directory==null)
				JOptionPane.showMessageDialog(null, "No directory has been selected.", "Error", 
											  JOptionPane.ERROR_MESSAGE);
			else {
				try {
					pt.execute();
					infoBox.setVisible(true);
				} catch (IllegalArgumentException error) {
					JOptionPane.showMessageDialog(null, "An error occurred during conversion", "Failure", 
												  JOptionPane.ERROR_MESSAGE);
					error.printStackTrace();
				}
			}
		}
	}
	
	class progressText extends SwingWorker<Void, Void> {
		@Override
		protected Void doInBackground() throws IOException {
			if(directory==null)
				JOptionPane.showMessageDialog(null, "No directory has been selected.", "Error", 
											  JOptionPane.ERROR_MESSAGE);
			else {
				double scale = (double) scaleBox.getSelectedItem();
				frame.setCursor(waitCursor);
				infoBox.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				infoBox.setCursor(waitCursor);
				
				int totalPictures = PictureConversion.countPictures(directoryContents);
				Picture[] myArray = PictureConversion.createPictureArray(directoryContents);
				System.out.println("Now converting " + totalPictures + " pictures for the " + 
									directory.toString() + " folder...");
				PictureConversion.convertPictures(myArray, scale);
				infoBox.setVisible(false);
				JOptionPane.showMessageDialog(null, "Conversion complete!");
				frame.setCursor(defaultCursor);
				pathDisplay.setText(null);
				directory=null;
				directoryContents=null;
			}
			return null;
		}
	}
}