<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
        /* フォーム入力フィールド */
        .input-field input {
            border-bottom: 2px solid #00796b !important; /* アクア系の強調色 */
            box-shadow: none !important;
        }
        /* 検索ボタン */
        .btn-search {
            background-color: #00796b; /* 深いグリーン */
            border-radius: 50px;
            transition: 0.3s;
        }
        .btn-search:hover {
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
<div class="container">
    <div class="row">
        <div class="col s12 m8 offset-m2">
            <div class="card z-depth-3">
                <div class="card-content">
                    <span class="card-title center teal-text">🔍 1冊の好きな本からあなたの性格を診断します</span>
                    <form action="search" method="GET">
                        <div class="input-field">
                            <i class="material-icons prefix">book</i>
                            <input type="text" id="title" name="title" required>
                            <label for="title">本のタイトル</label>
                        </div>

                        <div class="input-field">
                            <i class="material-icons prefix">person</i>
                            <input type="text" id="author" name="author">
                            <label for="author">著者名</label>
                        </div>

                        <div class="center">
                            <button type="submit" class="btn waves-effect waves-light btn-search">
                                <i class="material-icons left">search</i>診断する
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Materialize JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

</body>
</html>
