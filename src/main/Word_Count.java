package main;

public class Word_Count {

	public String fileName;
	public int word_count;
	public Word_Count(String fileName) {
		super();
		this.fileName = fileName;
		this.word_count = 0;
	}
	public Word_Count() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getWord_count() {
		return word_count;
	}
	public void setWord_count(int word_count) {
		this.word_count = word_count;
	}
	
	
	
}
