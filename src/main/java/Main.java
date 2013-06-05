import java.io.IOException;

import com.intenum.primefaces.ThemeUnpacker;

public class Main {
	public static void main(String[] args) throws IOException {
		ThemeUnpacker themeDownloader = new ThemeUnpacker();
		themeDownloader.setClearDestination(true);
		themeDownloader.setThemeName("blue");
		themeDownloader.setDestinationDirectory("c:\\work\\test\\");
		themeDownloader.unpack("C:\\Users\\ismail\\Downloads\\jquery-ui-1.9.2.custom.zip");
	}
}
