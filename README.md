# photo-reformat-project
<br>
## A small Java program that converts image files to .PNG and resizes them based on chosen scale through a GUI.
<br>
### Motivation for this project

I currently maintain an online store through eBay and have sold thousands of items over the past few years. Providing great photos of your items is one of the most important things you can do as a seller. A couple of years ago I started noticing compression issues with my photos once they were pulled from the cloud and uploaded to eBay. My listings were plagued with grainy photos that appeared far worse than they should have. I found that converting the images from .JPEG to .PNG alleviated this issue. However, doing so also greatly increased the size of the image files. I found myself using third-party apps for image conversion and manually resizing each photo. Needless to say, this was a major inconvenience and took time away from other important tasks required for listing items. This project started as an attempt to solve this issue with a single Java program.

### What this program does

Using a Java.swing-based GUI, the user first selects a folder of image files. Then, the user selects the scale they wish their files to be resized to after conversion. The scale options range from 0.1 to 0.9, in increments of one-tenth. Clicking the "Convert" button will execute the program and convert image files to .PNG format and then resize the now larger .PNG files to the selected scale. Non-image files are ignored and files already in .PNG format will only be resized. Folders cannot be empty.

#### A JAR file is provided for demo!
