package com.intenum.primefaces;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class ThemeUnpacker {
	private String destinationDirectory;
	private String themeName;
	private boolean useCompressedCss;
	private boolean clearDestination;
	private static final Pattern directoryPattern = Pattern
			.compile("^.*/css/[a-zA-Z0-9-]*/(.*)");
	private static final Pattern cssPattern = Pattern.compile("^.*\\.css");

	public String getDestinationDirectory() {
		return destinationDirectory;
	}

	public boolean isUseCompressedCss() {
		return useCompressedCss;
	}

	public void setUseCompressedCss(boolean useCompressedCss) {
		this.useCompressedCss = useCompressedCss;
	}

	public String getThemeName() {
		return themeName;
	}

	public boolean isClearDestination() {
		return clearDestination;
	}

	public void setClearDestination(boolean clearDestination) {
		this.clearDestination = clearDestination;
	}

	public void setDestinationDirectory(String destinationDirectory) {
		this.destinationDirectory = destinationDirectory;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public void unpack(String file) throws IOException {
		byte[] buffer = new byte[1024];

		if (StringUtils.isEmpty(themeName)) {
			throw new RuntimeException("Please set the 'themeName'");
		}

		if (StringUtils.isEmpty(destinationDirectory)) {
			throw new RuntimeException("Please set the 'destinationDirectory'");
		}

		File destinationFolder = new File(destinationDirectory);

		if (clearDestination && destinationFolder.exists()) {
			FileUtils.deleteDirectory(destinationFolder);
		}

		ZipInputStream zis = new ZipInputStream(new FileInputStream(file));

		ZipEntry ze = zis.getNextEntry();

		while (ze != null) {

			String fileName = ze.getName();

			// we need only css folder
			Matcher matcher = directoryPattern.matcher(fileName);
			if (matcher.matches()) {
				fileName = matcher.group(1);

				// if it is css file
				matcher = cssPattern.matcher(fileName);
				boolean cssFile = false;
				if (matcher.matches()) {
					boolean compressedCss = fileName.contains(".min.");
					if (compressedCss == useCompressedCss) {
						fileName = "theme.css";
					}
					cssFile = true;
				}

				File newFile = new File(destinationDirectory + File.separator
						+ fileName);
				new File(newFile.getParent()).mkdirs();

				FileOutputStream fos = new FileOutputStream(newFile);

				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}

				fos.close();

				// edit the css file 
				if (cssFile) {
					String content = FileUtils.readFileToString(newFile);
					content = content.replaceAll("\\.png", ".png']}\"");
					content = content.replaceAll("url\\(images",
							"url(\"#{resource['primefaces-" + themeName
									+ ":images");
					FileUtils.writeStringToFile(newFile, content);
				}
			}
			ze = zis.getNextEntry();
		}

		zis.closeEntry();
		zis.close();
	}
}
