import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.intenum.primefaces.ThemeUnpacker;


public class UnpackTest {

	@Test
	public void test() throws IOException {
		ThemeUnpacker themeDownloader = new ThemeUnpacker();
		themeDownloader.setClearDestination(true);
		themeDownloader.setThemeName("blue");
		themeDownloader.setDestinationDirectory("c:\\work\\test\\");
		themeDownloader.unpack("C:\\Users\\ismail\\Downloads\\jquery-ui-1.9.2.custom.zip");
	}

}
