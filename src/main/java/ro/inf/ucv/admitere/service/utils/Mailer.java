package ro.inf.ucv.admitere.service.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import ro.inf.ucv.admitere.utils.ListUtils;

@Service
public class Mailer {

	private static final Logger logger = Logger.getLogger(Mailer.class);

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Autowired
	private Configuration configuration;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void sendMail(List<String> mailTo, String[] mailCC, String mailSubject, String mailTemplate,
			HashMap<String, String> velocityContextMap, List<File> files) {
		try {
			Template template = configuration.getTemplate(mailTemplate);
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, velocityContextMap);
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, false);
			if (!ArrayUtils.isEmpty(mailCC)) {
				for (String cc : mailCC) {
					if (StringUtils.isNotBlank(cc)) {
						helper.setCc(cc);
					}
				}
			}
			if (!ListUtils.isEmpty(files)) {
				for (File file : files) {
					if (file != null && file.exists() && !file.isDirectory()) {
						helper.addAttachment(file.getName(), file);
					}
				}
			}
			boolean found = false;
			if (!ListUtils.isEmpty(mailTo)) {
				for (String to : mailTo) {
					if (StringUtils.isNotBlank(to)) {
						found = true;
						helper.addTo(to);
					}
				}
			}
			if (!found) {
				throw new Exception("Must set at least one recipient.");
			}
			if (StringUtils.isNotBlank(mailSubject)) {
				helper.setSubject(mailSubject);
			}
			if (StringUtils.isNotBlank(html)) {
				helper.setText(html, true);
			}
			mailSender.send(message);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	public void sendMail(List<String> mailTo, String[] mailCC, String mailSubject, String mailTemplate,
			HashMap<String, String> velocityContextMap) {
		sendMail(mailTo, mailCC, mailSubject, mailTemplate, velocityContextMap, null);
	}
}