import java.io.IOException;

import com.intenum.primefaces.ThemeUnpacker;

public class Main {
	public static void main(String[] args) throws IOException {
		ThemeUnpacker themeDownloader = new ThemeUnpacker();
		themeDownloader.setClearDestination(true);
		themeDownloader.setThemeName("blue");
		themeDownloader.setDestinationDirectory("c:\\project\\src\\main\\webapp\\resources\\primefaces-blue");
		themeDownloader.unpack("C:\\Downloads\\jquery-ui-1.9.2.custom.zip");
	}
}
