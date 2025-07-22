package cat.linky.linkycat_api.process;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ExperimentalProcess implements CommandLineRunner {

    @Value("${experimental.enabled}")
    private boolean ENABLED;
    
    private void runExperimental() {
        System.out.println("Use this class to run experimental tests.");
    }


    @Override
    public void run(String... args) throws Exception {
        if (ENABLED) runExperimental();
    }
    

}
