//David Clarke - G00335563
package ie.gmit.sw;
//imports
import java.io.File;
import java.util.Scanner;
//import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//import java.util.concurrent.LinkedBlockingQueue;

//Runner simply runs the project through the worker threads
public class Runner {
	private static Scanner scanner = new Scanner(System.in);
	private static File file, file2;
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		//sets numberOfHashes to 100
		int numberOfHashes = 100;
		//sets shingleSize to 2
		int shingleSize = 2;
		
		readFiles();

		//BlockingQueue<Shingle> q = new LinkedBlockingQueue<>();
		
		//Creates a threadpool
		ExecutorService service = Executors.newCachedThreadPool();
		//Thread t1 = new Thread(new Parser(q, ui.getFileNameA(), ui.getShingleSize()),"A");
		//Thread t2 = new Thread(new Parser(q, ui.getFileNameB(), ui.getShingleSize()),"B");
	
		//t1.start();
		//t2.start();
	 
		Future<NewDocument> doc1 = service.submit(new Worker(1, file, shingleSize));
		Future<NewDocument> doc2 = service.submit(new Worker(2, file2, shingleSize));

		MinHash comparer = new MinHash(numberOfHashes);
		float jaccardIndex = comparer.compare(doc1.get(), doc2.get());
		System.out.printf("The MinHash approximation of the jaccard index for the two documents is %.2f", jaccardIndex);

		scanner.close();
	}
	
	//reads the files
	private static void readFiles() {
		//do while validation
		boolean validFileNames = true;
		do {
			if (!validFileNames) {
				System.out.println("ERROR");
				System.out.println("These documents are not files on this machine.");
				System.out.println("Please try again");
				System.out.println("");
			}
			//asks the user and only accepts and runs if both files are on the system
			System.out.print("Please enter the first document: ");
			file = new File(scanner.nextLine());
			System.out.print("Please enter another document: ");
			file2 = new File(scanner.nextLine());
			validFileNames = false;
			//compares if both files exist
		} while (!file.exists() && !file.exists());
	}
}//end of Runner class