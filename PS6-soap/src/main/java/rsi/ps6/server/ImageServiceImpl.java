package rsi.ps6.server;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;
import java.awt.*;
import java.io.IOException;

@MTOM
@BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING)
@WebService(endpointInterface = "rsi.ps6.server.ImageService", serviceName = "ImageServiceImpl")
public class ImageServiceImpl implements ImageService {
    @Override
    public String echo(String text) {
        return "Hello " + text;
    }

    @Override
    public Image downloadImage(String name) {
        try {
            return ImageIO.read(getClass().getClassLoader().getResource(name));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
