//David Clarke - G00335563
package ie.gmit.sw;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

//Creates TreeSet called minHashes
public class MinHash{

	private Set<Integer> minHashes;
	//adds random numbers to the TreeSet
	public MinHash(int numberOfHashes) {
		minHashes = new TreeSet<Integer>();
		//random number is generated
		Random rnd = new Random();
		for (int i = 0; i < numberOfHashes; i++) {
			minHashes.add(rnd.nextInt());
		}
	}

	
	//calculation
	//method Compare takes two NewDocument parameters
	public float compare(NewDocument doc1, NewDocument doc2) {
		//calculates each NewDocument passed in
		Set<Integer> a = calculateMinHash(doc1);
		Set<Integer> b = calculateMinHash(doc2);
		//calculate the intersection of the two sets
		Set<Integer> n = new TreeSet<Integer>(b);
		n.retainAll(a);
		//divides the intersection by the size of the minHashes
		return 1.0f * n.size() / minHashes.size();
	}
	
	//method to calculate the minHash of a document
	private Set<Integer> calculateMinHash(NewDocument document) {
		//adds minHash to a set called shingles
		Set<Integer> shingles = new TreeSet<Integer>();
		for (Integer hash : minHashes) {
			int min = Integer.MAX_VALUE;
			for (int shingle : document.getShingles()) {
				int minhash = shingle ^ hash;
				if (minhash < min) {
					min = minhash;
				}
			}
			//shingles
			shingles.add(min);
		}
		//return set of minHashes
		return shingles;
	}
}
