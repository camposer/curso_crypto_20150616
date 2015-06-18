package ejercicio4.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PersonaServer {
	private static final int RMI_PUERTO = 1099;
	
	public static void main(String[] args) throws Exception {
		Registry registry = LocateRegistry.createRegistry(RMI_PUERTO);
		System.out.println("Enlazando PersonaServant...");
		registry.rebind("PersonaServant", PersonaServantFactory.createPersonaServant());
	}
}
