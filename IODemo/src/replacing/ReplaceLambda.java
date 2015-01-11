package replacing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import static java.nio.file.StandardOpenOption.*;

public class ReplaceLambda {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		if(args.length != 4) {
			System.out.println("Usage:\njava Replace <infilename> <outfilename> <word-to-be-replaced> <replacement>");
			return;
		}
		List<String> resultLines = 
			Files.lines(Paths.get(args[0])).map(s -> s.replaceAll(args[2], args[3]) )
			.collect(Collectors.toList());
		Files.write(Paths.get(args[1]), resultLines, APPEND);
	    System.out.println("Replacement is successful to file: " + args[1]);	  
	}
}
