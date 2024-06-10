package org.example.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.StringTokenizer;

public class BasicAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            try {
                // Decode the Base64 encoded string
                String base64Credentials = authHeader.substring(6);
                byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
                String decodedString = new String(decodedBytes, StandardCharsets.UTF_8);

                // Split the decoded string into username and password
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();

                // You can now use the username and password as needed
                // For example, you could set them as request attributes
                request.setAttribute("username", username);
                request.setAttribute("password", password);

                // Print the username and password (for debugging purposes)
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);

            } catch (IllegalArgumentException e) {
                // Handle the case where the Base64 string could not be decoded
                System.err.println("Failed to decode basic authentication token");
            }
        }

        // Proceed with the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
