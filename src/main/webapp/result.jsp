<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.Book, java.util.List" %>
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    String personalityAnalysis = (String) request.getAttribute("personalityAnalysis");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BookPersona</title>

    <!-- Material Design (MaterializeCSS) -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <style>
        /* 背景色 */
        body {
            background-color: #f5f5f5;
        }
        /* カードデザイン */
        .card {
            border-radius: 12px;
        }
        /* 戻るボタン */
        .btn-back {
            background-color: #00796b;
            border-radius: 50px;
            transition: 0.3s;
        }
        .btn-back:hover {
            background-color: #004d40;
        }
    </style>
</head>
<body>

<!-- ナビゲーションバー -->
<nav class="teal darken-3">
    <div class="nav-wrapper container">
        <a href="#" class="brand-logo center">📖 BookPersona</a>
    </div>
</nav>

<!-- メインコンテンツ -->
<div class="container py-5">
    <div class="row">
        <div class="col s12 m8 offset-m2">
            <div class="card z-depth-3">
                <div class="card-content">
                    <span class="card-title center teal-text">🔍 検索結果</span>

                    <% if (books != null && !books.isEmpty()) { %>
                        <ul class="collection">
                            <% for (Book book : books) { %>
                                <li class="collection-item avatar">
                                    <i class="material-icons circle teal">book</i>
                                    <span class="title"><strong><%= book.getTitle() %></strong></span>
                                    <p class="grey-text">著者: <%= book.getAuthor() %></p>
                                    <p><%= book.getDescription() %></p>
                                </li>
                            <% } %>
                        </ul>
                    <% } else { %>
                        <p class="red-text">検索結果が見つかりませんでした。</p>
                    <% } %>

                    <% if (personalityAnalysis != null && !personalityAnalysis.isEmpty()) { %>
                        <div class="card-panel yellow lighten-4">
                            <h5 class="black-text">📌 あなたの性格</h5>
                            <p><%= personalityAnalysis %></p>
                            <p>
                            <a href="https://twitter.com/intent/tweet?text=<%= personalityAnalysis %>%0A%0A%23さんまままアプリ" target="_blank" onclick="window.open(this.href, null, 'width=600,height=400'); return false;">結果をポストする</a>
                            </p>

                        </div>
                    <% } %>
                </div>
                <div class="card-action center">
                    <a href="index.jsp" class="btn waves-effect waves-light btn-back">
                        <i class="material-icons left">arrow_back</i>戻る
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Materialize JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>
</html>
