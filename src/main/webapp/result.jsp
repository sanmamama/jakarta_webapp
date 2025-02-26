<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.Book, java.util.List" %>
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
			String title = (String) request.getAttribute("title");
			String author = (String) request.getAttribute("author");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>検索結果</title>
</head>
<body>
    <h2>検索文字列</h2>
	<p>タイトル：<%= title %></p>
	<p>著者：<%= author %></p>
	<p>検索結果は以下の通りです。</p>
    <ul>
        <% for (Book book : books) { %>
            <li>
                <strong><%= book.getTitle() %></strong> - <%= book.getAuthor() %><br>
                <em><%= book.getDescription() %></em>
            </li>
        <% } %>
    </ul>
    <a href="index.jsp">戻る</a>
</body>
</html>
