package rsi.ps4.server.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import java.util.Collection;

import rsi.ps4.server.products.Product;

@WebService(name="PS4Service")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
public interface PS4Service {
    @WebMethod(operationName = "GetHello")
    @WebResult(name = "result")
    String getHello(@WebParam(name="name") String name);

    @WebMethod(operationName = "GetProducts")
    @WebResult(name = "products")
    Collection<Product> getProducts();

    @WebMethod(operationName = "GetProductByName")
    @WebResult(name = "product")
    Product getProductByName(@WebParam(name="name") String name);

    @WebMethod(operationName = "AddProduct")
    void addProduct(@WebParam(name="product") Product product);
}
