package com.baguix.utils.net;

import java.io.*;
import java.net.*;

public class DownloadTool {

	public static void main(String[] args) {
		download("http://animations.fg-a.com/animated-m-face-21.gif",
				"c:/temp/new.gif");
	}

	public static void download(String downloadPath, String localFilePath) {
		OutputStream out = null;
		URLConnection conn = null;
		InputStream in = null;
		try {
			// Connecting to site
			URL url = new URL(downloadPath);
			conn = url.openConnection();
			in = conn.getInputStream();

			out = new BufferedOutputStream(new FileOutputStream(localFilePath));

			byte[] buffer = new byte[1024];
			int numRead;
			long numWritten = 0;

			// Reading the file into buffer
			while ((numRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, numRead);
				numWritten += numRead;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException ioe) {
			}
		}
	}
}
