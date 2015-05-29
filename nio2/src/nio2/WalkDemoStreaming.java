package nio2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WalkDemoStreaming {

	public static void main(String[] args) throws IOException {
		Path projectPath = Paths.get(".").toAbsolutePath();
		Files.walk(projectPath)
				.filter((Path path) -> path.getFileSystem()
						.getPathMatcher("glob:**.java").matches(path))
				.forEach((Path path) -> {
					try{
						System.out.println(path);
//						Files.readAllLines(path, StandardCharsets.UTF_8).stream()
						Files.lines(path, StandardCharsets.UTF_8) //lazy reading
						.forEach(
								line -> {System.out.println(line);});
					} catch(IOException e){
						e.printStackTrace();
					}
				});
		
	}
}
