package invoicing.utility;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SerialDB {
	public static <T> List<T> readObjects(Path path) throws IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(Files.newInputStream(path));
		@SuppressWarnings("unchecked")
		List<T> result = (List<T>) is.readObject();
		return result;
	}
	
	public static <T>   void writeObjects(Path path, List<T> dataList ) throws IOException, ClassNotFoundException {
		ObjectOutputStream is = new ObjectOutputStream(Files.newOutputStream(path));
		is.writeObject(dataList);	
	}

}
