package mx.gob.imss.cit.pmc.riesgosinumf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class BatchRiesgoSinUmfApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchRiesgoSinUmfApplication.class, args);
    }

}
