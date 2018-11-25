package Hex;

public class Transfer {
	public static String strToHex(String s) {
	    String str = "";
	    for (int i = 0; i < s.length(); i++) {
	        int ch = (int) s.charAt(i);
	        String s4 = Integer.toHexString(ch);
	        str = str + s4;
	    }
	    return str;
	}
	
	public static String hexToString(String s) {
	    if (s == null || s.equals("")) {
	        return null;
	    }
	    s = s.replace(" ", "");
	    byte[] baKeyword = new byte[s.length() / 2];
	    for (int i = 0; i < baKeyword.length; i++) {
	        try {
	            baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    try {
	        s = new String(baKeyword, "UTF-8");
	        new String();
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }
	    return s;
	}
	
	public static void main(String[] args) {
		String name = "1522005108959/8/4/FILE_ACCESS/MOD_Gene_Ontology_Consortium";
		String hex = strToHex(name);
		System.out.println(hex);
		name = hexToString("313532323135333130323531322f312f312f46494c455f4143434553532f544f504d6564");
		System.out.println(name);
	}

}
