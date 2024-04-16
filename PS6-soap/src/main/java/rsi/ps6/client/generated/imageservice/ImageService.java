
package rsi.ps6.client.generated.imageservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ImageService", targetNamespace = "http://server.ps6.rsi/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ImageService {


    /**
     * 
     * @param arg0
     * @return
     *     returns byte[]
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "downloadImage", targetNamespace = "http://server.ps6.rsi/", className = "rsi.ps6.client.generated.imageservice.DownloadImage")
    @ResponseWrapper(localName = "downloadImageResponse", targetNamespace = "http://server.ps6.rsi/", className = "rsi.ps6.client.generated.imageservice.DownloadImageResponse")
    @Action(input = "http://server.ps6.rsi/ImageService/downloadImageRequest", output = "http://server.ps6.rsi/ImageService/downloadImageResponse")
    public byte[] downloadImage(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "echo", targetNamespace = "http://server.ps6.rsi/", className = "rsi.ps6.client.generated.imageservice.Echo")
    @ResponseWrapper(localName = "echoResponse", targetNamespace = "http://server.ps6.rsi/", className = "rsi.ps6.client.generated.imageservice.EchoResponse")
    @Action(input = "http://server.ps6.rsi/ImageService/echoRequest", output = "http://server.ps6.rsi/ImageService/echoResponse")
    public String echo(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}