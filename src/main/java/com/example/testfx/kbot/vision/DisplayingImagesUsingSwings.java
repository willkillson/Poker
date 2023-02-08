package com.example.testfx.kbot.vision;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.bytedeco.opencv.opencv_tracking.MatchTemplateDistance;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;


public class DisplayingImagesUsingSwings {

    //! [declare]
    /// Global Variables
    Boolean use_mask = false;
    Mat img = new Mat(), templ = new Mat();
    Mat mask = new Mat();

    int match_method;


    public static void main(String args[]) throws Exception {
        //Loading the OpenCV core library
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

        //# Can use IMREAD flags to do different pre-processing of image files,
        //# like making them grayscale or reducing the size.
        //# https://docs.opencv.org/4.2.0/d4/da8/group__imgcodecs.html
        String needle_file = "C:\\dev\\TestFX\\data\\images\\currentInputs\\needles\\image_004.png";
        String haystack_file = "C:\\dev\\TestFX\\data\\images\\currentInputs\\haystack\\haystack_001.png";
        Mat needle_image = Imgcodecs.imread(needle_file, Imgcodecs.IMREAD_UNCHANGED);
        Mat hay_stack_image = Imgcodecs.imread(haystack_file, Imgcodecs.IMREAD_UNCHANGED);

        // Set up the result (java)
        Mat result = new Mat();
        Mat img_display = new Mat();
        hay_stack_image.copyTo(img_display);
        int result_cols = hay_stack_image.cols() - needle_image.cols() + 1;
        int result_rows = hay_stack_image.rows() - needle_image.rows() + 1;
        result.create(result_rows, result_cols, CvType.CV_32FC1);

        // Modifies result
        Imgproc.matchTemplate(hay_stack_image, needle_image, result, Imgproc.TM_CCOEFF_NORMED);


        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        Core.MinMaxLocResult mmr = Core.minMaxLoc(result);

        Point matchLoc = mmr.maxLoc;
        Point minLoc = mmr.minLoc;
        double minValue = mmr.minVal;
        double maxVal = mmr.maxVal;

        Imgproc.rectangle(
                img_display,
                matchLoc,
                new Point(matchLoc.x + needle_image.cols(), matchLoc.y + needle_image.rows()),
                new Scalar(0, 0, 0),
                2,
                Imgproc.LINE_4,
                0);

//        Imgproc.rectangle(
//                result,
//                matchLoc,
//                new Point(matchLoc.x + needle_image.cols(), matchLoc.y + needle_image.rows()),
//                new Scalar(0, 0, 0),
//                2,
//                Imgproc.LINE_4,
//                0);


//        Image tmpImg = HighGui.toBufferedImage(img_display);
        BufferedImage bufferedImage = DisplayingImagesUsingSwings.Mat2BufferedImage(img_display);
        //Encoding the image
//        MatOfByte matOfByte = new MatOfByte();
//        Imgcodecs.imencode(".png", needle_image, matOfByte);
        //Storing the encoded Mat in a byte array
//        byte[] byteArray = matOfByte.toArray();
        //Preparing the Buffered Image
//        InputStream in = new ByteArrayInputStream(byteArray);
//        BufferedImage bufImage = ImageIO.read(in);
        //Instantiate JFrame
        JFrame frame = new JFrame();
        //Set Content to the JFrame
        frame.getContentPane().add(new JLabel(new ImageIcon(bufferedImage)));
        frame.pack();
        frame.setVisible(true);
        System.out.println("Image Loaded");
    }


    public static BufferedImage Mat2BufferedImage(Mat mat) throws IOException {
        //Encoding the image
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", mat, matOfByte);
        //Storing the encoded Mat in a byte array
        byte[] byteArray = matOfByte.toArray();
        //Preparing the Buffered Image
        InputStream in = new ByteArrayInputStream(byteArray);
        BufferedImage bufImage = ImageIO.read(in);
        return bufImage;
    }
}