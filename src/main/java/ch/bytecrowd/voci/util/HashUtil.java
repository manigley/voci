package ch.bytecrowd.voci.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

import com.google.common.base.Charsets;

public final class HashUtil {

	private final static Logger LOG = Logger.getLogger(HashUtil.class);
	
	public static String hashMD5(String string) {
		try {
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] hashBytes = digest.digest(string.getBytes(Charsets.UTF_8));
			return new BigInteger(1, hashBytes).toString(16);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("MD5 hashing faild", e);
		}
		return null;
	}
}
