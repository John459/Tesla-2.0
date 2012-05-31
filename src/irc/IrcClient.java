package irc;

import irc.adaptors.InputHandlerAdapter;

import java.io.File;
import java.io.IOException;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;


public class IrcClient extends IrcProtocol implements Runnable {
	
	private Thread thread;
	
	@Override
	public void run() {
		String str;
		try {
			while ((str = this.getIn().readLine()) != null) {
				System.out.println(str);
				this.invokeListeners(str);
				Thread.sleep(20);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadModule(String module) {
		PythonInterpreter interpreter = new PythonInterpreter();
		try {
			interpreter.exec("from scripts." + module + " import " + module);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		PyObject pyObject = interpreter.get(module);
		PyObject newObj = pyObject.__call__();
		InputHandlerAdapter listener = (InputHandlerAdapter) newObj.__tojava__(InputHandlerAdapter.class);
		listener.setIrcClient(this);
		this.addInputEventListener(listener);
	}
	
	public void loadModules(String path) {
		File folder = new File(path);
		String[] files = folder.list();
		for (String file : files) {
			if (file.equals("__init__.py")) continue;
			loadModule(file.substring(0, file.indexOf(".")));
		}
	}

	public IrcClient(String host, int ip, String nick, String user, String pass) throws IOException {
		super(host, ip, nick, user, pass);
		loadModules("jython/scripts/");
		thread = new Thread(this);
		thread.start();
	}
	
}