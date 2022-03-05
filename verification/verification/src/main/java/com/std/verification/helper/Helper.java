package com.std.verification.helper;

import org.springframework.stereotype.Component;
import java.util.Base64;

@Component
public class Helper {
	
	public String getImgByBLob(byte[] blobData) {
		if (blobData == null)
			return null;
		try {
			byte[] encode = Base64.getEncoder().encode(blobData);
			return new String(encode, "UTF-8");
		} catch (Exception ex) {
			return null;
		}
	}
	
}
