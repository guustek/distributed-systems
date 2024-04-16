package rsi.ps5.server;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService(name="ShopInfoService")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
@HandlerChain(file= "server-handler-chain.xml")
public interface ShopInfoService {

    @WebMethod
    String getShopInfo(String name) throws InvalidInputException;
}
