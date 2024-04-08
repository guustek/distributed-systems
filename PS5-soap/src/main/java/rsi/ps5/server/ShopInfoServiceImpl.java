package rsi.ps5.server;

import javax.jws.WebService;

@WebService(endpointInterface = "rsi.ps5.server.ShopInfoService", serviceName = "ShopInfoServiceImpl")
public class ShopInfoServiceImpl implements ShopInfoService {

    @Override
    public String getShopInfo(String name) throws InvalidInputException {
        if (!name.equals("Pawel")) {
            throw new InvalidInputException("Incorrect input", name + " is not valid");
        }
        return "Accepted " + name;
    }
}
