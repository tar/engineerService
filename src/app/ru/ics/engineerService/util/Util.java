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

/**
 * Supplementary class containing various non application specific utility
 * methods
 */
public final class Util {
	private static String CHAR173 = new String(new char[] { 173 });
	private static Set<Character> BAD_URL_CHARS = new HashSet<Character>();

	static {
		BAD_URL_CHARS.add('.');
		BAD_URL_CHARS.add(',');
		BAD_URL_CHARS.add('(');
		BAD_URL_CHARS.add(')');
		BAD_URL_CHARS.add('{');
		BAD_URL_CHARS.add('}');
		BAD_URL_CHARS.add('[');
		BAD_URL_CHARS.add(']');
		BAD_URL_CHARS.add(':');
		BAD_URL_CHARS.add(';');
	}
	
	/** private constructor to prevent instantiation */
	private Util() {
	}

	/**
	 * Indicates if string is empty
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
	public static String trimOrNull(String string) {
		if (string != null) {
			string = string.trim();
			if (string.length() == 0) {
				string = null;
			}
		}
		return string;
	}

	/**
	 * MD5 sum counting
	 * 
	 * @param bytes
	 *            source bytes
	 * @return md5 sum
	 */
	public static String MD5(byte[] bytes) {
		try {
			return byteToHexString(MessageDigest.getInstance("MD5").digest(bytes));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error making MD5 hash: " + e.getMessage(), e);
		}
	}

	/**
	 * Builds string presentation of bytes
	 * 
	 * @param hash
	 *            source bytes
	 * @return hex string
	 */
	public static String byteToHexString(byte[] hash) {
		StringBuilder buf = new StringBuilder(hash.length * 2);

		for (byte hashByte : hash) {
			if ((hashByte & 0xff) < 0x10) {
				buf.append("0");
			}

			buf.append(Long.toString(hashByte & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * Builds collection of string presentations from collection of objects
	 * 
	 * @param <T>
	 *            type of source collection
	 * @param items
	 *            source collection
	 * @param result
	 *            result collection to fill
	 * @return filled result collection
	 */
	public static <T> Collection<String> strings(Collection<T> items, Collection<String> result) {
		for (T string : items) {
			result.add(string.toString());
		}
		return result;
	}

	/**
	 * Builds Sorted set of collection with comparable object
	 * 
	 * @param <E>
	 *            type of object collection consists of
	 * @param items
	 *            source collection
	 * @return {@link SortedSet} with objects from given collection
	 */
	public static <E extends Comparable<?>> SortedSet<E> sort(Collection<E> items) {
		TreeSet<E> result = new TreeSet<E>();
		for (E item : items) {
			result.add(item);
		}
		return result;
	}

	/**
	 * Convert string to an integer, in case of error returns zero
	 * 
	 * @param str
	 *            String
	 * 
	 * @return Integer
	 */
	public static Integer strToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		}

	}
	
	public static int round(double doubleVal) {
		return (int) (doubleVal + 0.5);
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
	
	public static String trimMy(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, "\n");
		StringBuilder builder = new StringBuilder();
		while (tokenizer.hasMoreTokens()) {
			builder.append(tokenizer.nextToken().trim()).append('\n');
		}
		return builder.toString();
	}

	public static String trimPoppler(String str) {
		return str.replace(CHAR173, "");
	}

	public static String trimUrl(String str) {
		str = str.trim().toLowerCase();
		int length = str.length();
		int i = 0;
		while (i < length && BAD_URL_CHARS.contains(str.charAt(i))) {
			++i;
		}

		int j = length - 1;
		while (j >= 0 && BAD_URL_CHARS.contains(str.charAt(j))) {
			--j;
		}

		if (i < j && (i > 0 || j < length - 1)) {
			return str.substring(i, j + 1);
		} else {
			return str;
		}
	}
}
