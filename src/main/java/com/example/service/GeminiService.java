package com.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import io.github.cdimascio.dotenv.Dotenv;

public class GeminiService {
    private static final Dotenv dotenv = Dotenv.configure()
        .directory("C:/Users/user/Documents/GitHub/jakarta_webapp/src/main/resources")
        .ignoreIfMalformed()
        .ignoreIfMissing()
        .load();
	private static final String API_KEY = dotenv.get("API_KEY");
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=";

    public String analyzeReaderPersonality(String description) {
        try {
            // **API キーの確認**
            if (API_KEY == null || API_KEY.isEmpty()) {
                return "エラー: API_KEYが設定されていません";
            }
			// if(description.length() < 30){
			// 	return "エラー：本の情報が得られませんでした";
			// }

            // **リクエストの JSON データ (`temperature` を削除)**
            String requestBody = "{"
                    + "\"contents\": [{"
                    + "  \"parts\": [{"
                    + "    \"text\": \"" + description + " という内容の本を読む人はどのような性格だと推測できますか？100文字以下の文章でまとめて、「あなたの性格は[推測した100文字以下の文章]です。」とまとめてください。\""
                    + "  }]"
                    + "}]"
                    + "}";

            // **HTTP リクエストを作成**
            URL url = new URL(API_URL + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // **リクエストを送信**
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // **HTTP ステータスコードを確認**
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                Scanner errorScanner = new Scanner(conn.getErrorStream(), StandardCharsets.UTF_8);
                StringBuilder errorResponse = new StringBuilder();
                while (errorScanner.hasNext()) {
                    errorResponse.append(errorScanner.nextLine());
                }
                errorScanner.close();
                return "エラー: Gemini API から HTTP " + responseCode + " が返されました。詳細: " + errorResponse.toString();
            }

            // **レスポンスを取得**
            Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8);
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // **JSON レスポンスを解析**
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(response.toString());

			// **`candidates` が存在するかチェック**
            if (!rootNode.has("candidates") || rootNode.get("candidates").isEmpty()) {
                return "エラー: Gemini API のレスポンスに 'candidates' が含まれていません";
            }

            JsonNode candidates = rootNode.get("candidates").get(0);
            if (!candidates.has("content") || candidates.get("content").get("parts").isEmpty()) {
                return "エラー: Gemini API のレスポンスに 'content' が含まれていません";
            }

            return candidates.get("content").get("parts").get(0).get("text").asText();

        } catch (Exception e) {
            e.printStackTrace();
            return "エラー: " + e.toString();
        }
    }
}
