package studio;

import com.orientechnologies.orient.client.remote.OEngineRemote;
import com.orientechnologies.orient.core.Orient;
import com.orientechnologies.orient.server.OServer;
import com.orientechnologies.orient.server.OServerMain;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Features;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraphNoTx;

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

		// AT THE BEGINNING
		// OrientGraphFactory factory = new OrientGraphFactory(
		// "plocal:C:/Sources/graphdb").setupPool(1, 10);
		Orient.instance().registerEngine(new OEngineRemote());
		OrientGraphNoTx graph = new OrientGraphNoTx("plocal:C:/Sources/graphdb") {
			@Override
			public Features getFeatures() {
				return null;
			}
		};
		try {
			Vertex luca = graph.addVertex(null); // 1st OPERATION:
			// /IMPLICITLY
			// BEGIN A TRANSACTION
			luca.setProperty("name", "Luca");
			Vertex marko = graph.addVertex(null);
			marko.setProperty("name", "Marko");
			Edge lucaKnowsMarko = graph.addEdge(null, luca, marko, "knows");
			graph.commit();
			System.out.println("Done.");
		} catch (Exception e) {
			graph.rollback();
			System.out.print("Fail");
		} finally {
			graph.shutdown();
		}
		System.in.read();
	}
}
