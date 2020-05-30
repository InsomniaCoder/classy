package com.pp.classy.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Component;

@Component
public class QRGeneratorService {

  public BufferedImage generateQRCodeImage(String qrCodeText) throws Exception {
    ByteArrayOutputStream stream = QRCode
        .from(qrCodeText)
        .withSize(250, 250)
        .stream();
    ByteArrayInputStream bis = new ByteArrayInputStream(stream.toByteArray());

    return ImageIO.read(bis);
  }
}
