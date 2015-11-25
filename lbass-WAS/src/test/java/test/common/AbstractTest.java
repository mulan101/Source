package test.common;

public class AbstractTest {

	public void startServer() {
		ServerStartProcessor p = new ServerStartProcessor();
		Thread t = new Thread(p);
		t.start();
	}
}
