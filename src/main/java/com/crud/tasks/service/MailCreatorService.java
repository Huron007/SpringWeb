package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://huron007.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("preview_message", "New trello card has been created.");
        context.setVariable("goodbye_message", "Have a nice day " + adminConfig.getAdminName());
        context.setVariable("company_details", companyConfig.getCompanyName() + " " + companyConfig.getCompanyEmail() + " " + companyConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String dailyTasksCountEmail(){
        long size = taskRepository.count();
        Context context = new Context();
        context.setVariable("message", "Currently in database you got: " + size + ((size == 1)? " task" : " tasks"));
        context.setVariable("preview_message", "Daily task count.");
        context.setVariable("tasks_url", "https://huron007.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("show_button", true);
        context.setVariable("goodbye_message", "Have a nice day " + adminConfig.getAdminName());
        context.setVariable("company_details", companyConfig.getCompanyName() + " " + companyConfig.getCompanyEmail() + " " + companyConfig.getCompanyPhone());
        context.setVariable("is_friend", true);
        return templateEngine.process("mail/daily-task-count-mail", context);
    }
}