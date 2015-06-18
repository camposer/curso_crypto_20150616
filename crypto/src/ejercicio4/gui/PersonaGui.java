package ejercicio4.gui;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.Scanner;

import ejercicio4.model.Persona;
import ejercicio4.rmi.PersonaServant;

/**
 * -Djavax.net.ssl.keyStore=file/keystore.jks -Djavax.net.ssl.keyStorePassword=123456
 * -Djavax.net.ssl.truestStore=file/keystore.jks -Djavax.net.ssl.trustStorePassword=123456
 */
public class PersonaGui {
	private Scanner scanner;
	private PersonaServant personaServant;
	
	public PersonaGui() throws Exception {
		scanner = new Scanner(System.in);
		personaServant = (PersonaServant) LocateRegistry
				.getRegistry()
				.lookup("PersonaServant");
	}
	
	public static void main(String[] args) throws Exception {
		new PersonaGui().iniciar();
	}

	private void iniciar() throws RemoteException {
		while (true) {
			System.out.println();
			System.out.println("1. Agregar");
			System.out.println("2. Listar");
			System.out.println("3. Salir");
			System.out.println("? ");
			
			String opcion = scanner.nextLine();
			
			if (opcion.equals("1"))
				agregar();
			else if (opcion.equals("2"))
				listar();
			else if (opcion.equals("3"))
				break;
				
		}
	}

	private void listar() throws RemoteException {
		List<Persona> personas = personaServant.obtenerPersonas();
		if (personas != null) for (Persona p : personas)
			System.out.println(p);
	}

	private void agregar() throws RemoteException {
		System.out.print("Nombre? ");
		String nombre = scanner.nextLine();
		System.out.print("Apellido? ");
		String apellido = scanner.nextLine();
		System.out.print("Edad? ");
		Integer edad = Integer.parseInt(scanner.nextLine());
		
		personaServant.agregarPersona(new Persona(nombre, apellido, edad));
	}
}
