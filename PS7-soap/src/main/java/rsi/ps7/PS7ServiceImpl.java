package rsi.ps7;

import javax.jws.WebService;

@WebService(endpointInterface = "rsi.ps7.PS7Service", serviceName = "PS7Service")
public class PS7ServiceImpl implements PS7Service {

    @Override
    public String getHello(String name) {
        System.out.println("Received parameter: " + name);
        return "Hello from JAX-WS: " + name;
    }
}
