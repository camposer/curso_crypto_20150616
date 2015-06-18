package ejercicio4.rmi;

import java.rmi.RemoteException;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

/**
 * -Djavax.net.ssl.keyStore=file/keystore.jks -Djavax.net.ssl.keyStorePassword=123456
 * -Djavax.net.ssl.truestStore=file/keystore.jks -Djavax.net.ssl.trustStorePassword=123456
 */
public class PersonaServantSsl2w extends PersonaServantBase {
	private static final long serialVersionUID = 1L;

	public PersonaServantSsl2w() throws RemoteException {
		super(new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory(null, null, true));
	}
}
