package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {
    private final static String SUBJECT = "Tasks: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final JavaMailSender javaMailSender;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail(){
        long size = taskRepository.count();
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "Currently in database you got: " + size + ((size == 1)? " task" : " tasks"),
                        null
                )
        );
    }

    @Scheduled(cron = "0 0 10 * * ?")
    public void sendDailyTaskCount(){
        javaMailSender.send(simpleEmailService.createDailyMimeMessage(new Mail(
                adminConfig.getAdminMail(),
                "Daily task count",
                "",
                null
        )));
    }
}
