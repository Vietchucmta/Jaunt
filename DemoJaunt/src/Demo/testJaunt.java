package Demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.UserAgent;

public class testJaunt {
	public static void main(String arg[]) throws Exception {
//		Set<String> StringKey=new HashSet<String>();
//		StringKey=CutString(readKeyinFile());
//		System.out.println(readKeyinFile());
//		for(String key:StringKey) {
//			System.out.println(key);
////			ghiFile(key+"nextfile");
////			getLink(key);
//		}
		getLink("samsung");
		System.out.println("ok");
	}

	public static void getLink(String s) throws Exception {
		UserAgent uA = new UserAgent();
		uA.visit("https://www.google.com.vn");
		uA.doc.apply(s);
		uA.doc.submit();
		Elements elements = uA.doc.findEvery("<tr>").findEvery("<td>").findEvery("<a href>");
		for (Element element : elements) {
			ghiFile("Next page:\n" + element.getAtString("href") + "\n");
			UserAgent userAgent = null;
			try {
				userAgent = new UserAgent();
				userAgent.visit(element.getAtString("href"));
				Elements links = userAgent.doc.findEvery("<h3>").findEvery("<a href");
				for (Element link : links) {
					// System.out.println(link.getAtString("href"));
					ghiFile(link.getAtString("href"));
					// System.out.println("\t");
				}
			} catch (Exception ex) {
			}
			ghiFile("\n");
		}
	}

	// ghi ra file
	public static void ghiFile(String x) throws Exception {
		FileOutputStream fos = new FileOutputStream("F:\\Jaunt.txt", true);
		PrintWriter pw = new PrintWriter(fos);
		pw.println(x);
		pw.close();
		fos.flush();
		fos.close();
	}

	// doc key tu file
	public static String readKeyinFile() throws Exception {
		FileInputStream fis =null;
		FilterInputStream filter =null; 
		String chuoi="";
		try {
			fis= new FileInputStream("F:\\KeySearch.txt");
			filter=new BufferedInputStream(fis);
			int k=0;
			while((k=filter.read())!=-1) {
				chuoi+=(char)k;
			}
			return chuoi;
		} catch (Exception ex) {
			return "";
		}
	}

	// cat chuoi
	public static Set<String> CutString(String s) {
		Set<String> setStringKey= new HashSet<String>();
		String [] keys=s.split(".");
		for(String key:keys) {
			setStringKey.add(key);
		}
		return setStringKey;
	}
}
