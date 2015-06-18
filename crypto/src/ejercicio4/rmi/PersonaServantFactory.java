package ejercicio4.rmi;

import java.rmi.RemoteException;


public abstract class PersonaServantFactory {

	public static PersonaServant createPersonaServant() throws RemoteException {
		return new PersonaServantImpl();
	}

}
