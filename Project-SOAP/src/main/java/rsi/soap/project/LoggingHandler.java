package rsi.soap.project;

import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;


public class LoggingHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean outboundProperty = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        System.out.println();
        System.out.println(outboundProperty ? "Outgoing SOAP Message:" : "Incoming SOAP Message:");
        System.out.println("URL: " + getUrl(context));
        System.out.println("Operation: " + getOperation(context));
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        System.err.println();
        System.err.println("SOAP Fault:");
        System.err.println("URL: " + getUrl(context));
        System.err.println("Operation:" + getOperation(context));
        return true;
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public void close(MessageContext context) {
    }

    private static String getOperation(SOAPMessageContext context) {
        return context.get(MessageContext.WSDL_OPERATION).toString().split("}")[1];
    }

    private String getUrl(SOAPMessageContext context) {
        return context.get("com.sun.xml.ws.transport.http.servlet.requestURL").toString();
    }
}
