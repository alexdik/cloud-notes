package notes.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import restdisp.io.IOUtils;

public class UrlFetcher {
	public static String get(String urlString) throws IOException {
		URL url = new URL(urlString);

		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		conn.connect();

		byte[] bos = IOUtils.readStream(conn.getInputStream());
		
		return new String(bos, Const.ENCODING);
	}
}