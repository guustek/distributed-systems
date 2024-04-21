package rsi.ps6.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import java.awt.*;

@WebService(name="ImageService")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface ImageService {

    @WebMethod
    String echo(String text);

    @WebMethod
    Image downloadImage(String name);
}
