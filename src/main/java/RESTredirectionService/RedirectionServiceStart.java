package RESTredirectionService;

import VotingServerImpl.util.Implmentation.ZkServiceImplmentation;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;


@SpringBootApplication
public class RedirectionServiceStart {
	public static ZkServiceImplmentation zkservice;
	public static void main(String[] args) {
		if(args.length == 0){
			System.out.println("Missing arguments, closing server");
			exit(0);
		}
		BasicConfigurator.configure();
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);
		startup(args[0]);
		SpringApplication.run(RedirectionServiceStart.class, args);
	}
	static void startup(String hosts){
		zkservice = new ZkServiceImplmentation(hosts);
	}
}
