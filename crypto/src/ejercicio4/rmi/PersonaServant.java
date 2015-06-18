package ejercicio4.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ejercicio4.model.Persona;

public interface PersonaServant extends Remote {
	void agregarPersona(Persona p) throws RemoteException;
	void modificarPersona(Persona p) throws RemoteException;
	void eliminarPersona(Long id)  throws RemoteException;
	Persona obtenerPersona(Long id) throws RemoteException;
	List<Persona> obtenerPersonas() throws RemoteException;
}
