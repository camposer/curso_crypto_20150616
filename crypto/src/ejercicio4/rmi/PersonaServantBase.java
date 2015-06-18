package ejercicio4.rmi;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ejercicio4.model.Persona;
import ejercicio4.service.PersonaService;
import ejercicio4.service.PersonaServiceFactory;

public abstract class PersonaServantBase
		extends UnicastRemoteObject 
		implements PersonaServant {
	private static final long serialVersionUID = 5032335132359126610L;

	private PersonaService personaService;
	
	public PersonaServantBase() throws RemoteException {
		super();
		personaService = PersonaServiceFactory.createPersonaService();
	}
	
	public PersonaServantBase(RMIClientSocketFactory clientSocketFactory,
			RMIServerSocketFactory serverSocketFactory) throws RemoteException {
		super(0, clientSocketFactory, serverSocketFactory);
		personaService = PersonaServiceFactory.createPersonaService();
	}

	@Override
	public void agregarPersona(Persona p) throws RemoteException {
		personaService.agregarPersona(p);
	}

	@Override
	public void modificarPersona(Persona p) throws RemoteException {
		personaService.modificarPersona(p);
	}

	@Override
	public void eliminarPersona(Long id) throws RemoteException {
		personaService.eliminarPersona(id);
	}

	@Override
	public Persona obtenerPersona(Long id) throws RemoteException {
		return personaService.obtenerPersona(id);
	}

	@Override
	public List<Persona> obtenerPersonas() throws RemoteException {
		return personaService.obtenerPersonas();
	}

}
