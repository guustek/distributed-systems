package rsi.ps4.client;

import rsi.ps4.client.geoipservice.GeoIPService;
import rsi.ps4.client.geoipservice.GeoIPServiceSoap;

public class Client {

    public static void main(String[] args) {
        GeoIPService client = new GeoIPService();
        GeoIPServiceSoap service = client.getGeoIPServiceSoap();
        String result = args.length > 0 ? service.getIpLocation(args[0]) : service.getLocation();
        System.out.println(result);
    }
}
