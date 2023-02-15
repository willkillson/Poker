package com.example.testfx.kbot.vision;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Vision {


    public List<Mat> needles;
    public Mat needle;
    public int width;
    public int height;
    public int method;

    public Vision() throws IOException {
        //Loading the OpenCV core library
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );

        //# Can use IMREAD flags to do different pre-processing of image files,
        //# like making them grayscale or reducing the size.q
        //# https://docs.opencv.org/4.2.0/d4/da8/group__imgcodecs.html
        String needleFile_001 = "C:\\dev\\TestFX\\data\\images\\currentInputs\\needles\\image_001.png";
        String needleFile_002 = "C:\\dev\\TestFX\\data\\images\\currentInputs\\needles\\image_002.png";

        this.needles = new ArrayList<>();
        this.needles.add(Imgcodecs.imread(needleFile_001, Imgcodecs.IMREAD_UNCHANGED));
        this.needles.add(Imgcodecs.imread(needleFile_002, Imgcodecs.IMREAD_UNCHANGED));

    }

    public BufferedImage executeMatch(BufferedImage hayStack) throws IOException {
        Mat haystackMat = bufferedImageToMat(hayStack);
        return matchNeedleToHaystack(haystackMat);
//        return hayStack;

    }

    public BufferedImage matchNeedleToHaystack(Mat haystack) throws IOException {
        // Set up the result (java)
        Mat result = new Mat();
        Mat img_display = new Mat();
        haystack.copyTo(img_display);
        for( int i = 0;i< this.needles.size();i++){
            Mat needle = needles.get(i);
            int result_cols = haystack.cols() - needle.cols() + 1;
            int result_rows = haystack.rows() - needle.rows() + 1;
            result.create(result_rows, result_cols, CvType.CV_32FC1);

            // Modifies result
            Imgproc.matchTemplate(haystack, needle, result, Imgproc.TM_CCOEFF_NORMED);

            Core.MinMaxLocResult mmr = Core.minMaxLoc(result);
            Point matchLoc = mmr.maxLoc;
            double maxVal = mmr.maxVal;
            double threshHold = 0.8;
            Scalar s = null;
            if(i ==0){
                s = new Scalar(255,255,255);
            }else if(i ==1){
                s = new Scalar(255,255,255);
            }


            if(maxVal >= threshHold){
                Imgproc.putText(
                        img_display,
                        "poop",
                        new Point(matchLoc.x + needle.cols(), matchLoc.y + needle.rows()),
                        Imgproc.FONT_HERSHEY_PLAIN,
                        5.0,
                        s
                );
                Imgproc.rectangle(
                        img_display,
                        matchLoc,
                        new Point(matchLoc.x + needle.cols(), matchLoc.y + needle.rows()),
                        s,
                        5,
                        Imgproc.LINE_AA,
                        0);
            }
        }


        BufferedImage bufferedImage = mat2BufferedImage(img_display);
        return bufferedImage;
    }


    public BufferedImage mat2BufferedImage(Mat mat) throws IOException {
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

    public Mat bufferedImageToMat(BufferedImage bi) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", byteArrayOutputStream);
        byteArrayOutputStream.flush();
        return Imgcodecs.imdecode(new MatOfByte(byteArrayOutputStream.toByteArray()), Imgcodecs.IMREAD_UNCHANGED);
    }


}
