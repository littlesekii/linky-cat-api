package cat.linky.linkycat_api.infra.process;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cat.linky.linkycat_api.core.model.Link;

@Component
public class ExperimentalProcess implements CommandLineRunner {

    @Value("${experimental.enabled}")
    private boolean ENABLED;
    
    private void runExperimental() {
        System.out.println("\nUse this class to run experimental tests.");
        System.out.println("=========================================\n");

        Link link = new Link();
        link.setId(1L);
        link.setUrl("https://shorturl.linky.cat");
        link.setLabel("URL Shortener");

        System.out.println(link.toString());

        System.out.println("\n=========================================\n");
    }


    @Override
    public void run(String... args) throws Exception {
        if (ENABLED) runExperimental();
    }
    

}
