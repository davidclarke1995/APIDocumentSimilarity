//David Clarke - G00335563
package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


//parses the shingles of size 2
public class Parser {
	
	private int shingleSize;
	
	public Parser(int shingleSize) {
		this.shingleSize = shingleSize;
	}
	
	/*
	 * Creates a StringBuilder object and an Iterator object.
	 * If counter == 3 then add the words in groups of 4(shingleSize) 
	 * to the shingles set after getting the hashcode of that group of words
	 * reset counter to 0 and make a new StringBuilder.
	 * Otherwise add the next iterated string to the StringBuilder.
	 * Increment counter.
	 * 
	 */
	
	public Set<Integer> parse(InputStream stream) throws IOException {
		Set<Integer> shingles = new LinkedHashSet<Integer>();
		//calls method parsed
		List<String> buffer = parsed(stream);
		StringBuilder sb = new StringBuilder();
		int counter  = 0;
		Iterator<String> iter = buffer.iterator();
		while(iter.hasNext()) {
			if (counter == shingleSize - 1) {
				
				shingles.add(sb.toString().toUpperCase().hashCode());
				counter = 0;
				//reset counter to 0 and create new StringBuilder
				sb = new StringBuilder();
			} else {
				sb.append(iter.next());
				iter.remove();
				counter++;
				//increment counter
			}
		}
		return shingles;
	}
	
	private List<String> parsed(InputStream stream) throws IOException {
		List<String> buffer = new LinkedList<String>();
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		String line = null;
		//while
		while ((line = br.readLine()) != null) {
			//splits the lines at space
			String[] words = line.split(" ");
			for(int i = 0; i < words.length; i++) {
				buffer.add(words[i]);
			}
		}
		//adds the words and the index of i and returns buffer
		return buffer;
	}
}