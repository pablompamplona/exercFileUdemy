package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Product;

public class Program {

	public static void main(String[] args) throws ParseException{
		
		Scanner input = new Scanner(System.in);
		ArrayList<Product> products = new ArrayList();
		
		System.out.println("Enter file patch: ");
		String strPatch = input.nextLine();
		
		File inputFile = new File(strPatch);
		String folderPatch = inputFile.getParent();
		
		boolean success = new File(folderPatch + "\\out").mkdir();
		String strPatchFileOut = folderPatch + "\\out\\summary.csv";	
		
		try (BufferedReader br = new BufferedReader(new FileReader(strPatch))) {
			String itemCsv = br.readLine();
			while (itemCsv != null) {
				String[] fields = itemCsv.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quant = Integer.parseInt(fields[2]);
				products.add(new Product(name, price, quant));
				
				itemCsv = br.readLine();	
			}
			
 		try (BufferedWriter bw = new BufferedWriter(new FileWriter(strPatchFileOut))) {
 			for (Product p : products) {
 				bw.write(p.getName() + ", " + String.format("%.2f", p.valorTotal()));
 				bw.newLine();
 			}
 		}
 		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		input.close();
	}

}
