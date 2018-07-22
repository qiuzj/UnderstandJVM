package cn.javaee.javaagent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SampleClass {
	public static void main(String[] args) throws IOException {
		fetch("http://mail.163.com");
		fetch("http://www.iteye.com");
	}

	private static void fetch(final String address) throws MalformedURLException, IOException {

		final URL url = new URL(address);
		final URLConnection connection = url.openConnection();

		try (final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

			String inputLine = null;
			final StringBuffer sb = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}

			System.out.println("Content size: " + sb.length());
		}
	}
}