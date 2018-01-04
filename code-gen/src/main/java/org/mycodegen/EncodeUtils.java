package org.mycodegen;

public class EncodeUtils {
	public static String native2ascii(String str) {
		char[] ac = str.toCharArray();
		StringBuffer rs = new StringBuffer(ac.length);
		for (int k = 0; k < ac.length; k++)
			if (ac[k] > '\177') {
				rs.append((char) 92);
				rs.append((char) 117);
				String s1 = Integer.toHexString(ac[k]);
				StringBuffer stringbuffer = new StringBuffer(s1);
				stringbuffer.reverse();
				int l = 4 - stringbuffer.length();
				for (int i1 = 0; i1 < l; i1++)
					stringbuffer.append('0');

				for (int j1 = 0; j1 < 4; j1++)
					rs.append(stringbuffer.charAt(3 - j1));

			} else {
				rs.append(ac[k]);
			}
		return rs.toString();
	}

}
