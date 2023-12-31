package com.vatsal.com.equity.alert.utils;

import com.vatsal.com.equity.alert.models.Alert;
import com.vatsal.com.equity.alert.models.EquityCMP;
import com.vatsal.com.equity.alert.service.EquityCMPService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CronJob {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EquityCMPService equityCMPService;

    private final WebClient webClient;

    private boolean isFirstIteration = true;

    public CronJob(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }

    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void yourScheduledTask() {
        System.out.println("Executing scheduled task...");

        // load database only during first iteration
        if (isFirstIteration) {
            System.out.println("Task executed during the first iteration.");
            String result = webClient.get()
                    .uri("/cmpFile/loadData")
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            System.out.println("Data loaded in DB");
            isFirstIteration = false;
        }

//        API CALL: Getting all alerts
        List<Alert> result = webClient.get()
                .uri("/alert/")
                .retrieve()
                .bodyToFlux(Alert.class)
                .collectList()
                .block();
//      Checking if any alert is triggered
        for (Alert alert : result){
            String answer = checkIfAlertIsTriggered(alert);
            if(!Objects.equals(answer, "")){

                emailService.sendEmail("vatsaldoshi11@gmail.com", "Email triggered(testing demo project)", answer);
//                delete the alert once triggered
                webClient.delete()
                        .uri("/alert/{id}",alert.getId())
                        .retrieve()
                        .bodyToMono(Void.class).subscribe();
                System.out.println("Alert triggered!");
            }

        }
    }

    String checkIfAlertIsTriggered(Alert alert){
            String stockName = alert.getStock();
            String condition = alert.getCond();
            double alertPrice = alert.getPrice();

//          API CALL: Getting CMP data for that alert
            EquityCMP cmp = webClient.get()
                            .uri("/equityCMP/getData/{stockName}", stockName)
                            .retrieve()
                            .bodyToMono(EquityCMP.class)
                            .block();

            if(Objects.equals(condition, ">")){
                if(cmp.getCmp() > alertPrice){
                    return "Alert triggered for" + stockName + ". CMP" + condition + alertPrice;
                }
            }
            else if(Objects.equals(condition, "<")){
                if(cmp.getCmp() < alertPrice){
                    return "Alert triggered for" + stockName + ". CMP" + condition + alertPrice;
                }
            } else if (Objects.equals(condition, "=")) {
                if(cmp.getCmp() == alertPrice){
                    return "Alert triggered for" + stockName + ". CMP" + condition + alertPrice;
                }
            }
            else
                return "";
        return "";
    }


}
