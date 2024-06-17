package rsi.project.rest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static final Gson GSON = new GsonBuilder()
			.setPrettyPrinting()
			.create();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    public static String readFile(Path path) {
        try {
            return new String(Files.readAllBytes(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void writeToFile(Path path, String payload) {
        try {
            Files.write(path.toAbsolutePath(), payload.getBytes());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * @return Hostname/IP Address of local machine based on network interface used for internet connections
     */
    private static String getMachineAddress() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("google.com", 80));
            return socket.getLocalAddress().getHostAddress();
        } catch (IOException ex) {
            try {
                return InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                return "localhost";
            }
        }
    }
}
