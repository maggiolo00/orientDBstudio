package studio;

import java.io.File;

import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.OServerMain;

public class MainClass {

	public static void main(String[] args) throws Exception {
		OServer server = OServerMain.create();
		server.startup(new File("db.config"));
		server.activate();

		System.out.println("Server started");
		System.in.read();
	}

}
