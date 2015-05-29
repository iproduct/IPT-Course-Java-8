package nio2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkDemo {

	public static void main(String[] args) throws IOException {
		Path projectPath = Paths.get(".").toAbsolutePath();
		Files.walkFileTree(projectPath, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult postVisitDirectory(Path dir,
					IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException {
				System.out.println("Visiting directory: " + projectPath.relativize(dir));
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				System.out.println("Visiting file: " + projectPath.relativize(file));
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException {
				System.out.println("Error visiting file: " + projectPath.relativize(file));
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
