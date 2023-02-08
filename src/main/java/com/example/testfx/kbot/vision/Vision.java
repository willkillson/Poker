package com.example.testfx.kbot.vision;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Vision {

    public Mat image_frame;
    public int width;
    public int height;
    public int method;

    public Vision(){

        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
//        this.image_frame = Imgcodecs.imread(
//                "./data/images/currentInputs/needles/image_001.png",
//                Imgproc.TM_CCOEFF_NORMED);
//        this.width = this.image_frame.width();
//        this.height = this.image_frame.height();
//        this.method = Imgproc.TM_CCOEFF_NORMED;
    }

//    public Vision(final Mat mat){
//        this.image_frame = mat;
//        this.width = this.image_frame.width();
//        this.height = this.image_frame.height();
//        this.method = Imgproc.TM_CCOEFF_NORMED;
//    }
//
//    public Vision(final String imagePath){
//        this.image_frame = Imgcodecs.imread(imagePath,Imgproc.TM_CCOEFF_NORMED);
//        this.width = this.image_frame.width();
//        this.height = this.image_frame.height();
//        this.method = Imgproc.TM_CCOEFF_NORMED;
//    }
//
//    public Vision(final String imagePath, final int flag){
//        this.image_frame = Imgcodecs.imread(imagePath,flag);
//        this.width = this.image_frame.width();
//        this.height = this.image_frame.height();
//        this.method = flag;
//    }

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
