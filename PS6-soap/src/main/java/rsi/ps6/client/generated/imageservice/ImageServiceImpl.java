
package rsi.ps6.client.generated.imageservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "ImageServiceImpl", targetNamespace = "http://server.ps6.rsi/", wsdlLocation = "http://localhost:8080/PS6-soap/ImageServiceImpl?wsdl")
public class ImageServiceImpl
    extends Service
{

    private final static URL IMAGESERVICEIMPL_WSDL_LOCATION;
    private final static WebServiceException IMAGESERVICEIMPL_EXCEPTION;
    private final static QName IMAGESERVICEIMPL_QNAME = new QName("http://server.ps6.rsi/", "ImageServiceImpl");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/PS6-soap/ImageServiceImpl?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        IMAGESERVICEIMPL_WSDL_LOCATION = url;
        IMAGESERVICEIMPL_EXCEPTION = e;
    }

    public ImageServiceImpl() {
        super(__getWsdlLocation(), IMAGESERVICEIMPL_QNAME);
    }

    public ImageServiceImpl(WebServiceFeature... features) {
        super(__getWsdlLocation(), IMAGESERVICEIMPL_QNAME, features);
    }

    public ImageServiceImpl(URL wsdlLocation) {
        super(wsdlLocation, IMAGESERVICEIMPL_QNAME);
    }

    public ImageServiceImpl(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, IMAGESERVICEIMPL_QNAME, features);
    }

    public ImageServiceImpl(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ImageServiceImpl(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ImageService
     */
    @WebEndpoint(name = "ImageServiceImplPort")
    public ImageService getImageServiceImplPort() {
        return super.getPort(new QName("http://server.ps6.rsi/", "ImageServiceImplPort"), ImageService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ImageService
     */
    @WebEndpoint(name = "ImageServiceImplPort")
    public ImageService getImageServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://server.ps6.rsi/", "ImageServiceImplPort"), ImageService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (IMAGESERVICEIMPL_EXCEPTION!= null) {
            throw IMAGESERVICEIMPL_EXCEPTION;
        }
        return IMAGESERVICEIMPL_WSDL_LOCATION;
    }

}
