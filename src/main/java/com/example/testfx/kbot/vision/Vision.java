package com.example.testfx.kbot.vision;

import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Vision {

    public Mat image_frame;
    public int width;
    public int height;
    public int method;

    public Vision(){
        this.image_frame = Imgcodecs.imread(
                "./data/images/currentInputs/needles/image_001.png",
                Imgproc.TM_CCOEFF_NORMED);
        this.width = this.image_frame.width();
        this.height = this.image_frame.height();
        this.method = Imgproc.TM_CCOEFF_NORMED;
    }

    public Vision(final Mat mat){
        this.image_frame = mat;
        this.width = this.image_frame.width();
        this.height = this.image_frame.height();
        this.method = Imgproc.TM_CCOEFF_NORMED;
    }

    public Vision(final String imagePath){
        this.image_frame = Imgcodecs.imread(imagePath,Imgproc.TM_CCOEFF_NORMED);
        this.width = this.image_frame.width();
        this.height = this.image_frame.height();
        this.method = Imgproc.TM_CCOEFF_NORMED;
    }

    public Vision(final String imagePath, final int flag){
        this.image_frame = Imgcodecs.imread(imagePath,flag);
        this.width = this.image_frame.width();
        this.height = this.image_frame.height();
        this.method = flag;
    }


}
