package com.example.controller;
import com.example.service.GeminiService;
import com.example.service.GoogleBooksService;
import com.example.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private final GoogleBooksService booksService = new GoogleBooksService();
    private final GeminiService geminiService = new GeminiService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // **エンコーディングの設定 (重要)**
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        // **フォームの入力値を取得**
        String title = request.getParameter("title");
        String author = request.getParameter("author");

        // **Google Books API で本を検索**
        List<Book> books = booksService.searchBooks(title, author);

        // **最初の本の description を取得**
        String description = books.isEmpty() ? "本が見つかりません" : books.get(0).getDescription();

        // **Gemini API で分析**
        String personalityAnalysis = books.isEmpty() ? "本が見つかりません":geminiService.analyzeReaderPersonality(description);

        // **検索結果を JSP に渡す**
        request.setAttribute("books", books);
        request.setAttribute("personalityAnalysis", personalityAnalysis);

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
