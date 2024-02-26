package com.helperMethods;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImgFix {

    // Img Rotate
    public static BufferedImage getRotImage(BufferedImage img, int rotAngle) {
        int w = img.getWidth();
        int h = img.getHeight();

        BufferedImage newImage = new BufferedImage(w, h, img.getType());
        Graphics2D g2D = newImage.createGraphics();

        g2D.rotate(Math.toRadians(rotAngle), w / 2, h / 2);
        g2D.drawImage(img, 0, 0, null);
        g2D.dispose();

        return newImage;
    }

    // Img Layer Build
    public static BufferedImage buildImage(BufferedImage[] images) {
        int w = images[0].getWidth();
        int h = images[0].getHeight();

        BufferedImage newImage = new BufferedImage(w, h, images[0].getType());
        Graphics2D g2D = newImage.createGraphics();

        for (BufferedImage img : images) {
            g2D.drawImage(img, 0, 0, null);
        }
        g2D.dispose();
        return newImage;
    }

    // Rotate Second Image only
    public static BufferedImage getBuildRotImage(BufferedImage[] images, int rotAngle, int rotIndex) {

        int w = images[0].getWidth();
        int h = images[0].getHeight();

        BufferedImage newImage = new BufferedImage(w, h, images[0].getType());
        Graphics2D g2D = newImage.createGraphics();

        for (int i = 0; i < images.length; i++) {
            if (rotIndex == i)
                g2D.rotate(Math.toRadians(rotAngle), w / 2, h / 2);
            g2D.drawImage(images[i], 0, 0, null);
            if (rotIndex == i)
                g2D.rotate(Math.toRadians(-rotAngle), w / 2, h / 2);
        }
        g2D.dispose();
        return newImage;
    }

    // Rotate Second Image only + animation
    public static BufferedImage[] getBuildRotImages(BufferedImage[] images, BufferedImage secondImage, int rotAngle) {

        int w = images[0].getWidth();
        int h = images[0].getHeight();

        BufferedImage[] arr=new BufferedImage[images.length];

        
        for (int i = 0; i < images.length; i++) {
            
            BufferedImage newImage = new BufferedImage(w, h, images[0].getType());
            Graphics2D g2D = newImage.createGraphics();

            g2D.drawImage(images[i], 0, 0, null);
            g2D.rotate(Math.toRadians(rotAngle), w / 2, h / 2);
            g2D.drawImage(secondImage, 0, 0, null);
            g2D.dispose();

            arr[i]=newImage;
        }

        return arr;
    }
}
