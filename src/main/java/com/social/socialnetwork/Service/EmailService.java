package com.social.socialnetwork.Service;

import com.social.socialnetwork.dto.MailRequest;
import com.social.socialnetwork.dto.MailRespone;
import com.social.socialnetwork.dto.RegisterReqest;
import freemarker.template.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private  JavaMailSender sender;
    @Autowired
    private Configuration config;

    public MailRespone sendEmail(MailRequest request, Map<String,Object> model){
        MailRespone respone = new MailRespone();
        MimeMessage mimeMessage = sender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_RELATED
            , StandardCharsets.UTF_8.name());

//            helper.addAttachment("logo.png",new ClassPathResource("logo.png"));

            Template t = config.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t,model);

            helper.setTo(request.getTo());
            helper.setText(html,true);
            helper.setSubject(request.getSubject());
            helper.setFrom(request.getFrom());
            sender.send(mimeMessage);

            respone.setMessage("Mail sending to: "+ request.getTo());
            respone.setStatus(Boolean.TRUE);
        } catch ( IOException | TemplateException | MessagingException e) {
            respone.setMessage("Mail sending failure: "+ e.getMessage());
            respone.setStatus(Boolean.FALSE);
        }
        return respone;
    }
}
