package sainthonore.pollaapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@RestController
@RequestMapping("task")
@EnableScheduling
public class TaskController {

    @Autowired
    private ConectionToPollaApiService pollaservice;

    @Scheduled(cron = "00 * * * * *")
    @RequestMapping(value = "send-mail", method = RequestMethod.GET)
    public String sendSellsFile()
            throws IOException, NoSuchAlgorithmException, NoSuchProviderException, ParseException {
        System.out.println("execute");
        System.out.println(pollaservice.getInfoFromApiPolla("send-mail", ""));
        return "ok";

    }
}
