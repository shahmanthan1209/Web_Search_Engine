package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Demo {

	private static String htmlPath = System.getProperty("user.dir") + "\\HTML_Files";
	private static String textPath = System.getProperty("user.dir") + "\\Text_Files\\";
	public static ArrayList<TST<Integer>> myTST = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		File myFolder = new File(htmlPath);
		String[] myHTMLFiles = myFolder.list();

		// Converting html to text
		// htmlToText(myHTMLFiles);

		// reading text folder
		myFolder = new File(textPath);
		String[] files = myFolder.list();

		// reading each text file
		long start = System.currentTimeMillis();
		for (String f : files) {

			TST<Integer> tst = new TST<>();
			String[] tokens = textTokenizing(f);

			int freq = 0;
			// storing the tokens into TST

			for (int i = 0; i < tokens.length; i++) {

				// Calculating frequency of word and storing it as value in
				// TST.(taking more Time)
				for (String s : tokens) {
					if (s.equals(tokens[i]))
						freq++;
				}
				tokens[i].replaceAll("[^a-zA-Z0-9]", "");
				if (tokens[i].length() > 0) {
					tst.put(tokens[i], freq);
				}

				freq = 0;
			}

			myTST.add(tst);

		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		System.out.println(myTST.size());

		String searchWord = "java is a programming language".toLowerCase();
		String[] keywords = generateKeyword(searchWord);
		for(String q:keywords){
			System.out.println(q);
		}
		
		int[][] f = search(keywords);

		Arrays.sort(f, new Comparator<int[]>() {

			@Override
			public int compare(final int[] entry1, final int[] entry2) {

				if (entry1[0] < entry2[0])
					return 1;
				else
					return -1;
			}
		});

		for (int i = 0; i < f.length; i++) {
			System.out.println(f[i][0] + " " + f[i][1]);
		}

		for (int i = 0; i < 10; i++) {
			if (i > f.length - 1)
				break;
			int index = f[i][1];
			System.out.println(files[index] + " " + index);
		}

	}

	// Converting HTML files to Text files
	public static void htmlToText(String[] myHTMLFiles) {

		try {
			for (int i = 0; i < myHTMLFiles.length; i++) {

				File myFile = new File(htmlPath + myHTMLFiles[i]);
				Document doc = Jsoup.parse(myFile, "UTF-8");
				String text = doc.text();

				PrintWriter out = new PrintWriter(textPath + myHTMLFiles[i].replaceAll(".htm", ".txt"));
				out.println(text);
				out.close();

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Returning Tokens of a file
	public static String[] textTokenizing(String f) {

		String[] tokens = null;
		try {

			String fName = textPath + f;
			File myTextFile = new File(fName);

			// storing file content into string
			BufferedReader reader = new BufferedReader(new FileReader(myTextFile));
			String line = null;
			String myStr;
			StringBuilder stringBuilder = new StringBuilder();
			String ls = System.getProperty(" ");

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
				stringBuilder.append(ls);
			}

			reader.close();
			myStr = stringBuilder.toString().toLowerCase();

			// Tokenizing the file content
			tokens = myStr.split(" ");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tokens;

	}

	public static int[][] search(String[] keywords) {

		
		int[][] freqList = new int[myTST.size()][2];
		TST<Integer> tst;
		for (int i = 0; i < myTST.size(); i++) {
			tst = myTST.get(i);
			freqList[i][0] = 0;
			freqList[i][1] = i;
			for(String w:keywords){
				if (tst.contains(w)) {
					freqList[i][0] += tst.get(w);
				}
			}
		}
		return freqList;

	}

	public static String[] generateKeyword(String keyword) {

		ArrayList<String> stopWords = new ArrayList<>();
		String[] filteredKeywords = {};
		String line;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(System.getProperty("user.dir") +"\\stopWords.txt"));

			while ((line = reader.readLine()) != null) {
				stopWords.add(line);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] keywords = keyword.split(" ");
		StringBuilder builder = new StringBuilder();
		for(String word : keywords) {
			word = word.trim();
			if (!stopWords.contains(word)) {
				builder.append(word + "\n");
			}
		}
		
		filteredKeywords = builder.toString().split("\n").clone();
		
		return filteredKeywords;

	}
}
