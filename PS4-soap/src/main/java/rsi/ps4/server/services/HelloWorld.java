package rsi.ps4.server.services;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import java.util.Collection;

import rsi.ps4.server.products.Product;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface HelloWorld {
    @WebMethod
    String getHelloWorldAsString(String name);

    @WebMethod
    Collection<Product> getProducts();

    @WebMethod
    Product getProductByName(String name);
}
