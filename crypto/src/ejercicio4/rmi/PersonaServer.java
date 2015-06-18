package ejercicio4.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * -Djavax.net.ssl.keyStore=file/keystore.jks -Djavax.net.ssl.keyStorePassword=123456
 * -Djavax.net.ssl.truestStore=file/keystore.jks -Djavax.net.ssl.trustStorePassword=123456
 */
public class PersonaServer {
	private static final int RMI_PUERTO = 1099;
	
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.createRegistry(RMI_PUERTO);
		System.out.println("Enlazando PersonaServant...");
		registry.rebind("PersonaServant", PersonaServantFactory.createPersonaServant());
	}
}
