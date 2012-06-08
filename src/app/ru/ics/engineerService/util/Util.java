package ru.ics.engineerService.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.symmetric.ARC4.Base;

/**
 * Supplementary class containing various non application specific utility
 * methods
 */
public final class Util {
	
	
	/** private constructor to prevent instantiation */
	private Util() {
	}

	/**
	 * Indicates if string is null or empty
	 * 
	 * @param string
	 *            String
	 * @return <code>false</code> if specified string is not <code>null</code>
	 *         and contains at least one non space character, <code>true</code>
	 *         otherwise
	 */
	public static boolean isEmpty(String string) {
		return string == null || string.trim().length() == 0;
	}

	/**
	 * Method trims string and if trimmed string is empty then returns null
	 * 
	 * @param string
	 *            string to trim
	 * @return trimmed string if trimmed string is empty then returns null
	 */
	

	/**
	 * MD5 sum counting
	 * 
	 * @param bytes
	 *            source bytes
	 * @return md5 sum
	 */
	public static String MD5(byte[] bytes) {
		try {
			return Base64.encodeBase64String(MessageDigest.getInstance("MD5").digest(bytes));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error making MD5 hash: " + e.getMessage(), e);
		}
	}

		
	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);

		// Get the size of the file
		long length = file.length();

		if (length > Integer.MAX_VALUE) {
			// File is too large
			throw new IOException("File is too large(more tnan 2.1 gb)"
					+ file.getName());
		}

		// Create the byte array to hold the data
		byte[] bytes = new byte[(int) length];

		// Read in the bytes
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length
				&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
			offset += numRead;
		}

		// Ensure all the bytes have been read in
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "
					+ file.getName());
		}

		// Close the input stream and return bytes
		is.close();
		return bytes;
	}
	
	
}
