import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.net.ssl.HttpsURLConnection;

public class FunctionsJson {
	JsonReader reader;

	public JsonValue openJSON(String path) {
		try {
			if (path.toLowerCase().startsWith("http://")) {
				return readHttp(path);
			} else if (path.toLowerCase().startsWith("https://")) {
				return readHttps(path);
			} else {
				return readFile(path);
			}
		} catch (IOException e) {
			System.out.println("Error processed doc Json " +
					e.getLocalizedMessage());
			return null;
		}
	}

	public JsonValue readFile(String path) throws FileNotFoundException {
		try (JsonReader reader = Json.createReader(new FileReader(path))) {
			return reader.read();
		}
	}

	public JsonValue readHttps(String path) throws IOException {
		URL url = new URL(path);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		try (InputStream is = conn.getInputStream();
				JsonReader reader = Json.createReader(is)) {
			return reader.read();
		} finally {
			conn.disconnect();
		}
	}

	public JsonValue readHttp(String path) throws IOException {
		URL url = new URL(path);
		try (InputStream is = url.openStream();
				JsonReader reader = Json.createReader(is)) {
			return reader.read();
		}
	}
}
