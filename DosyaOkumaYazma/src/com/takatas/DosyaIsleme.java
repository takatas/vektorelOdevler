package com.takatas;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class DosyaIsleme {

	static String fileSeparator = System.getProperty("file.separator");
	static String veri = "@PatNo:234562@Pat:Taner TEMEL@Age:45@HBG:23@AG:45@#"
			+ "@PatNo:234563@Pat:Kemal TEMEL@Age:34@HBG:24@AG:45@#"
			+ "@PatNo:234564@Pat:Cemal TEMEL@Age:23@HBG:43@AG:45@#"
			+ "@PatNo:234565@Pat:Ay�e TEMEL@Age:5@HBG:33@AG:45@#"
			+ "@PatNo:234566@Pat:Fatma TEMEL@Age:75@HBG:13@AG:45@#"
			+ "@PatNo:234562@Pat:Mustafa TEMEL@Age:45@HBG:25@AG:45@#"
			+ "@PatNo:234563@Pat:Mehmet TEMEL@Age:34@HBG:27@AG:45@#"
			+ "@PatNo:234565@Pat:Hatice TEMEL@Age:5@HBG:26@AG:45@#"
			+ "@PatNo:234566@Pat:Canan TEMEL@Age:75@HBG:29@AG:45@#";

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("1 - Bilgileri De�i�kenden Oku");
		System.out.println("2 - Bilgileri Dosyadan Oku");
		System.out.println("3 - ��k��");

		System.out.println("De�er Giriniz...");
		int deger = scanner.nextInt();
		System.out.println("---------------------------------------");


		switch (deger) {
		case 1:
			dosyaParsEt(veri);

			break;
		case 2:
			dosyaParsEt(dosyaOku());

			break;
		case 3:
			break;

		default:
			break;
		}

		System.out.println(dosyaOku());
		System.out.println("-----------------------------------------");


	}

	private static String dosyaOku() {
		String icerik = "";
		try {
			File file = new File("D:" + fileSeparator + "TTEMEL"
					+ fileSeparator + "sonuclar.txt");
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				icerik += scanner.nextLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return icerik;
	}

	private static void dosyaYaz(String metin, String dosyaAdi) {
		try {

			File directory = new File("E:" + fileSeparator + "SONU�LAR");

			if (!directory.exists()) {
				directory.mkdirs();
			}
			File file = new File("E:" + fileSeparator + "SONU�LAR"
					+ fileSeparator + "Hasta Sonu�lar�.txt");

			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}

			FileOutputStream fileOutputStream = new FileOutputStream(file, true);
			fileOutputStream.write(metin.getBytes());
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void dosyaParsEt(String veri) {

		String[] okunanVeri = veri.split("#");

		for (int i = 0; i < okunanVeri.length; i++) {

			String[] hastaBilgisi = okunanVeri[i].split("@");
			String str = "";

			for (int j = 1; j < hastaBilgisi.length; j++) {
				String[] hasta = hastaBilgisi[j].split(":");

				if ((hasta[0].equals("HBG")) || (hasta[0].equals("AG")))
					str += hasta[0] + ",";

				str += hasta[1] + ",";
			}

			String[] sonuclar = str.split(",");
			String ornekNo = sonuclar[0];
			String hastaAdi = sonuclar[1];
			String yas = sonuclar[2];
			String hbg = sonuclar[3];
			String hbgSonuc = sonuclar[4];
			String ag = sonuclar[5];
			String agSonuc = sonuclar[6];

			System.out.println("�rnek No :" + ornekNo + " - Hasta Ad� : "
					+ hastaAdi + " - Ya� : " + yas + " - HBG Sonu� :"
					+ hbgSonuc + " - AG Sonu� : " + agSonuc);
			System.out.println("-------------------------------------");

			String bilgileriYaz = "�rnek No :" + ornekNo + " - Hasta Ad� : "
					+ hastaAdi + " - Ya� : " + yas + " - HBG Sonu� :"
					+ hbgSonuc + " - AG Sonu� : " + agSonuc + "\r\n";
			dosyaYaz(bilgileriYaz, "HastaSonuclari.txt");

		}

	}

}
