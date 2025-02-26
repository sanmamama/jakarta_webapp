<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>本の検索</title>
</head>
<body>
    <h2>本の検索</h2>
    <form action="search" method="GET">
        <label for="title">本のタイトル:</label>
        <input type="text" id="title" name="title" required><br>

        <label for="author">著者:</label>
        <input type="text" id="author" name="author" required><br>

        <button type="submit">検索</button>
    </form>
</body>
</html>
