package studio;

import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.OServerMain;

public class MainClass {

	public static void main(String[] args) throws Exception {
		MainClass mainClass = new MainClass();
		mainClass.startServer();
	}

	public void startServer() throws Exception {
		OServer server = OServerMain.create();
		server = server.startup(getClass().getResourceAsStream("db.config"));
		server.activate();

		System.out.println("Server started");
		System.in.read();
	}
}
