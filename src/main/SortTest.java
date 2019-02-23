package main;

import java.util.Arrays;
import java.util.Comparator;

public class SortTest {
	
	public static void main(String[] args) {
		
		int[][] arr = new int[10][2];
		for(int i=0,j=10;i<10;i++,j--){
			arr[i][0] = j;
			arr[i][1] = i;
			
			System.out.println(arr[i][0] + " " + arr[i][1]);
		}
		System.out.println("\n");
		
		Arrays.sort(arr, new Comparator<int[]>() { 
            
	          @Override              
	          // Compare values according to columns 
	          public int compare(final int[] entry1,  
	                             final int[] entry2) { 
	  
	            // To sort in descending order revert  
	            // the '>' Operator 
	            if (entry1[0] > entry2[0]) 
	                return 1; 
	            else
	                return -1; 
	          } 
	        });  // End of function call sort(). 
		
		for(int i=0;i<10;i++){
			System.out.println(arr[i][0] + " " + arr[i][1]);
		}
	}

}
