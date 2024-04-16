
package rsi.ps5.client.generated.authservice;

import javax.jws.WebMethod;
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
@WebService(name = "AuthenticationService", targetNamespace = "http://server.ps5.rsi/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface AuthenticationService {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "authenticate", targetNamespace = "http://server.ps5.rsi/", className = "rsi.ps5.client.generated.authservice.Authenticate")
    @ResponseWrapper(localName = "authenticateResponse", targetNamespace = "http://server.ps5.rsi/", className = "rsi.ps5.client.generated.authservice.AuthenticateResponse")
    @Action(input = "http://server.ps5.rsi/AuthenticationService/authenticateRequest", output = "http://server.ps5.rsi/AuthenticationService/authenticateResponse")
    public String authenticate();

}
