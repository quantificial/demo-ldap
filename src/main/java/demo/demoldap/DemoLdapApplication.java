package demo.demoldap;

import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DemoLdapApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoLdapApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Hashtable authEnv = new Hashtable(11);
		String username = "test2";
		String password = "12341234";
		String base = "cn=users,dc=localhost";
		String dn = "uid=" + username + "," + base;
		
		//dn = username;
		//String ldapURL = "ldap://192.168.1.188:389";
		String ldapURL = "ldaps://192.168.1.188:389";
		
		authEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		authEnv.put(Context.PROVIDER_URL, ldapURL);
		authEnv.put(Context.SECURITY_AUTHENTICATION, "simple");
		authEnv.put(Context.SECURITY_PRINCIPAL, dn);
		authEnv.put(Context.SECURITY_CREDENTIALS, password);

		try {
			DirContext authContext = new InitialDirContext(authEnv);
			log.info("Authentication Success!");
		} catch (AuthenticationException authEx) {
			log.info("Authentication failed!");
		} catch (NamingException namEx) {
			log.info("Something went wrong!");
			namEx.printStackTrace();
		}

		System.exit(0);

	}

}
