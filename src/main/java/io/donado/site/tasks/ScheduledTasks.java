package io.donado.site.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ScheduledTasks {
    @Scheduled(fixedRate = 120, timeUnit = TimeUnit.SECONDS)
    public void deleteExpiredTokens () {
        System.out.println("Todo - Delete expired tokens");
    }
}
