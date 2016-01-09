package timeserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeServer {
	public static final int PORT = 8000;
	public static Logger logger = Logger.getLogger("timeserver.TimeServer");

	public static void main(String[] args) throws IOException {
		logger.setLevel(Level.ALL);
		try (ServerSocket ss = new ServerSocket(PORT)) {
			logger.log(Level.INFO, "TimeServer stated on port: " + ss.getLocalPort());

			while (true) {
				try (Socket s = ss.accept()) {
					logger.log(Level.INFO, "TimeServer connection accepted: " + s);
					BufferedReader in = new BufferedReader(
							new InputStreamReader(s.getInputStream()));
					PrintWriter out = new PrintWriter(
							new BufferedWriter(
									new OutputStreamWriter(s.getOutputStream())),
							true);

					String zoneStr = in.readLine();
					logger.log(Level.INFO, "Client time request for zone: " + zoneStr);
					ZoneId zoneId;
					try {
						zoneId = ZoneId.of(zoneStr);
					} catch (DateTimeException ex) {
						logger.log(Level.SEVERE, "Invalid zone Id: " + zoneStr + ". Taking Greenwich zone instead");
						zoneId = ZoneId.of("UTC/Greenwich");
					}
					LocalDateTime now = LocalDateTime.now(zoneId);
					out.println(now.toString());

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
