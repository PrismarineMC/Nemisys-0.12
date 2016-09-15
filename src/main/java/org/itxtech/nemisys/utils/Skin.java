package org.itxtech.nemisys.utils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class Skin {
    public static final int SINGLE_SKIN_SIZE = 64 * 32 * 4;
    public static final int DOUBLE_SKIN_SIZE = 64 * 64 * 4;

    private byte[] data = new byte[SINGLE_SKIN_SIZE];
    private boolean slim = false;
    private boolean transparent = false;

    public Skin(byte[] data) {
        this(data, false);
    }

    public Skin(InputStream inputStream) {
        this(inputStream, false);
    }

    public Skin(ImageInputStream inputStream) {
        this(inputStream, false);
    }

    public Skin(File file) {
        this(file, false);
    }

    public Skin(URL url) {
        this(url, false);
    }

    public Skin(BufferedImage image) {
        this(image, false);
    }

    public Skin(byte[] data, boolean slim) {
        this(data, slim, false);
    }

    public Skin(InputStream inputStream, boolean slim) {
        this(inputStream, slim, false);
    }

    public Skin(ImageInputStream inputStream, boolean slim) {
        this(inputStream, slim, false);
    }

    public Skin(File file, boolean slim) {
        this(file, slim, false);
    }

    public Skin(URL url, boolean slim) {
        this(url, slim, false);
    }

    public Skin(BufferedImage image, boolean slim) {
        this(image, false, false);
    }

    public Skin(byte[] data, boolean slim, boolean transparent) {
        this.setData(data);
        this.slim = slim;
        this.setTransparent(transparent);
    }

    public Skin(InputStream inputStream, boolean slim, boolean transparent) {
        BufferedImage image;
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.parseBufferedImage(image);
        this.slim = slim;
        this.setTransparent(transparent);
    }

    public Skin(ImageInputStream inputStream, boolean slim, boolean transparent) {
        BufferedImage image;
        try {
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.parseBufferedImage(image);
        this.slim = slim;
        this.setTransparent(transparent);
    }

    public Skin(File file, boolean slim, boolean transparent) {
        BufferedImage image;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.parseBufferedImage(image);
        this.slim = slim;
        this.setTransparent(transparent);
    }

    public Skin(URL url, boolean slim, boolean transparent) {
        BufferedImage image;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.parseBufferedImage(image);
        this.slim = slim;
        this.setTransparent(transparent);
    }

    public Skin(BufferedImage image, boolean slim, boolean transparent) {
        this.parseBufferedImage(image);
        this.slim = slim;
        this.setTransparent(transparent);
    }

    public void parseBufferedImage(BufferedImage image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y), true);
                outputStream.write(color.getRed());
                outputStream.write(color.getGreen());
                outputStream.write(color.getBlue());
                outputStream.write(color.getAlpha());
            }
        }
        image.flush();
        this.setData(outputStream.toByteArray());
    }

    public byte[] getData() {
        return data;
    }

    public boolean isSlim() {
        return slim;
    }

    public boolean isTransparent() {
        return transparent;
    }

    public void setData(byte[] data) {
        if (!this.isValid()) {
            throw new IllegalArgumentException("Invalid skin");
        }
        this.data = data;
    }

    public void setSlim(boolean slim) {
        this.slim = slim;
    }

    public void setTransparent(boolean transparent) {
        this.transparent = transparent;
    }

    public boolean isValid() {
        return this.data.length == SINGLE_SKIN_SIZE || this.data.length == DOUBLE_SKIN_SIZE;
    }
}
