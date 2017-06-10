package it.polimi.ingsw.GC_21.fx;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import it.polimi.ingsw.GC_21.CLIENT.Connections;
import it.polimi.ingsw.GC_21.CLIENT.RmiClient;
import it.polimi.ingsw.GC_21.CLIENT.SocketClient;
import it.polimi.ingsw.GC_21.view.ServerInterface;

public class MetaController {
	
	protected static RmiClient client2;
	protected static SocketClient client1;
	protected static Connections client;
	
	public static void factorySocket() {
	    try {
				String ip = InetAddress.getLocalHost().getHostAddress();
				client1 = new SocketClient(ip, 6620);
				client1.startClient();
				client = client1;
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e2) {
	            System.out.println("errore");
	        }
	    	        

	     }
	    
	    public static void factoryRmi() throws RemoteException, NotBoundException {
	    	Registry reg = LocateRegistry.getRegistry(8000);
	        ServerInterface srv = (ServerInterface) reg.lookup("server");
	    	client2 = new RmiClient(ViewType.GUI);
	        srv.join(client2);
	        client = client2;
		}
	
}