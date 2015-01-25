package cn.iolove.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MD5utils {

	public static byte [] md5(byte[] b){
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(b);
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			return new byte[]{};
		}
	}
	public static String leftpad(String str,char c,int length){
		char[] temp = new char[length];
		Arrays.fill(temp, c);
		char [] st = str.toCharArray();
		System.arraycopy(st, 0, temp, length-st.length, st.length);
		return new String(temp);
	}
	public static String toHexString(byte[] b){
		int a;
		StringBuilder str = new StringBuilder();
		for(byte s:b){
			a= s&0xff;
			str.append(leftpad(Integer.toHexString(a),'0',2));
		}
		return str.toString();
	}
	public static String md5(String date){
		try{
			byte [] temp = md5(date.getBytes("utf-8"));

				return toHexString(temp);
				
			
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
	}


}
