package test.common;

import java.io.IOException;

import com.lbass.server.main.HttpServerMain;

public class ServerStartProcessor implements Runnable {

	@Override
	public void run() {
		try {
			HttpServerMain.main(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
