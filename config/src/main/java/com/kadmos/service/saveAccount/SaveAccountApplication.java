package com.kadmos.service.saveAccount;


import com.kadmos.service.saveAccount.appConfig.AppConfigContext;
import com.kadmos.service.saveAccount.persistence.PersistenceContext;
import com.kadmos.service.saveAccount.restcontroller.RestControllerContext;
import com.kadmos.service.saveAccount.service.ServiceContext;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        AppConfigContext.class,
        PersistenceContext.class,
        ServiceContext.class,
        RestControllerContext.class
})
@EnableFeignClients
public class SaveAccountApplication {

    public static void main(String[] args) throws Exception{
        Options options=new Options();
        options.addRequiredOption("p", "port", true, "The port this app will listen on");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        // If port string is not valid, it will throw an exception.
        int port = Integer.parseInt(cmd.getOptionValue("port"));

        // Start Dapr's callback endpoint by passing in the port.
        SpringApplication app = new SpringApplication(SaveAccountApplication.class);
        app.run(String.format("--server.port=%d", port));
    }
}
