//David Clarke - G00335563
//This class is for creating the document
package ie.gmit.sw;

import java.util.Set;

public class NewDocument {
	private int id;
	private Set<Integer> shingles;

	//constructor consisting of an integer and a set of shingles.
	 
	
	
	public NewDocument(int id, Set<Integer> shingles) {
		this.id = id;
		this.shingles = shingles;
	}

	
	//getters and setters
	public int getId() {
		return id;
	}

	public Set<Integer> getShingles() {
		return shingles;
	}
	
	//toString formatting
	public String toString() {
		return "Document [id=" + id + ", shingles=" + shingles.size() + "]";
	}
}