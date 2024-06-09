package rsi.ps7;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService(name="PS7Service")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface PS7Service {
    @WebMethod(operationName = "GetHello")
    @WebResult(name = "result")
    String getHello(@WebParam(name="name") String name);
}
