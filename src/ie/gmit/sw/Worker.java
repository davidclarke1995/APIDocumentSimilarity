//David Clarke - G00335563 
package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.Callable;
//import java.util.*;
import java.util.Set;

//public class Worker implements Runnable {
    //private Thread shingleSize = null;
    
//}
public class Worker implements Callable<NewDocument> {
	private File file;
	private int shingleSize;
	private int documentId;
	public Worker(int documentId, File file, int shingleSize) {
		this.file = file;
		this.shingleSize = shingleSize;
		this.documentId = documentId;		
	}

	//overriding implement methods
	@Override
	public NewDocument call() throws Exception {
		Parser parser = new Parser(shingleSize);
		//multi-threading
		Set<Integer> map = parser.parse(new FileInputStream(file));
		NewDocument document = new NewDocument(documentId, map);
		return document;//returns the document object NewDocument
	}

}
