package com.crcl.notification.service;

import com.crcl.notification.domain.Template;
import com.crcl.notification.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailTemplateGeneratorImpl implements MailTemplateGenerator {
    private final TemplateRepository templateRepository;


    @Override
    public String generate(Map<String, Object> props, String id) {
        Template template = this.templateRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("template with id:" + id + "does not exists")))
                .block();
        Context context = new Context();
        context.setVariables(props);

        StringTemplateResolver resolver = new StringTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);

        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        assert template != null;
        return templateEngine.process(template.getContent(), context);
    }
}
