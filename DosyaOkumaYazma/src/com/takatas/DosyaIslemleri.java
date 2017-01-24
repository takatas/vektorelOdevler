package com.takatas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class DosyaIslemleri {

	static String fileSeparator = System.getProperty("file.separator");

	public static void main(String[] args) {

		System.out.println(dosyaOku());
		System.out.println("-----------------------------------------");

		try {
			dosyaYaz(dosyaOku());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-----------------------------------------");
	}

	private static String dosyaOku() {
		String str = "";

		File file = new File("G:" + fileSeparator + "soner" + fileSeparator
				+ "dosyaIslemleri.txt");

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] buffer = new byte[100];

			try {
				while (fileInputStream.available() > 0) {

					int okunanVeri = fileInputStream.read(buffer);

					str += new String(buffer, 0, okunanVeri);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return str;

	}

	private static void dosyaYaz(String yazilacakVeri) throws IOException {

		File directory = new File("G:" + fileSeparator + "Dosya Ýþlemleri");

		if (!directory.exists()) {
			directory.mkdirs();
		}
		File file = new File("G:" + fileSeparator + "Dosya Ýþlemleri"
				+ fileSeparator + "dosyaIslemleri.txt");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		FileWriter fileWriter = new FileWriter(file, false);
		BufferedWriter bWriter = new BufferedWriter(fileWriter);
		bWriter.write(yazilacakVeri);
		bWriter.close();

	}

}
