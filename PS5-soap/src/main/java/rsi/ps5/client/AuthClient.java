package rsi.ps5.client;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.MessageContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rsi.ps5.client.generated.authservice.AuthenticationService;
import rsi.ps5.client.generated.authservice.AuthenticationServiceImpl;

public class AuthClient {
    public static void main(String[] args) {
        String username = args.length > 0 ? args[0] : "Pawel";
        String password = args.length > 0 ? args[0] : "123";
        AuthenticationServiceImpl client = new AuthenticationServiceImpl();
        AuthenticationService port = client.getAuthenticationServiceImplPort();

        Map<String, Object> requestContext = ((BindingProvider)port).getRequestContext();

        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Username", Collections.singletonList(username));
        headers.put("Password", Collections.singletonList(password));

        requestContext.put(MessageContext.HTTP_REQUEST_HEADERS, headers);

        System.out.println(port.authenticate());
    }
}
