package rsi.ps5.client;

import rsi.ps5.client.generated.shopinfoservice.InvalidInputException_Exception;
import rsi.ps5.client.generated.shopinfoservice.ShopInfoService;
import rsi.ps5.client.generated.shopinfoservice.ShopInfoServiceImpl;

public class Client {
    public static void main(String[] args) {
        String name = args.length > 0 ? args[0] : "Pawel";
        ShopInfoServiceImpl client = new ShopInfoServiceImpl();
        ShopInfoService port = client.getShopInfoServiceImplPort();

        try {
            String response = port.getShopInfo(name);
            System.out.println("Response: " + response);
        } catch (InvalidInputException_Exception e) {
            System.out.println("Error response: " + e.getMessage());
            System.out.println("Error details: " + e.getFaultInfo().getErrorDetails());
        }
    }

}
