package com.example.service;

import com.example.model.Book;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoogleBooksService {
    private static final String API_URL = "https://www.googleapis.com/books/v1/volumes?q=";

    public List<Book> searchBooks(String title, String author) {
        List<Book> bookList = new ArrayList<>();
        try {
            String query = API_URL + "intitle:" + title;
            if (author != null && !author.isEmpty()) {
                query += "+inauthor:" + author;
            }

            URL url = new URL(query);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.toString());
            JsonNode items = rootNode.get("items");

            if (items != null) {
                for (JsonNode item : items) {
                    JsonNode volumeInfo = item.get("volumeInfo");
                    String bookTitle = volumeInfo.get("title").asText();
                    String bookAuthor = volumeInfo.get("authors").get(0).asText();
                    String description = volumeInfo.has("description") ? volumeInfo.get("description").asText() : "No description available";
                    
                    if (description.length() < 30)continue;
                    bookList.add(new Book(bookTitle, bookAuthor, description));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            
            bookList.add(new Book("exception", "exception", e.toString()));
        }
        return bookList;
    }
}
