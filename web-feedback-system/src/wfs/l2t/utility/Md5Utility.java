package wfs.l2t.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Md5Utility {

	public Md5Utility() {

	}

	public String generateToken()
	{
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		Date dateobj = new Date();
		String time = df.format(dateobj);
		return this.md5(time);		
	}
	
	public String md5(String input) {
		String md5 = null;
		if (null == input)
			return null;
		try {
			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("MD5");
			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());
			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md5;
	}
}
