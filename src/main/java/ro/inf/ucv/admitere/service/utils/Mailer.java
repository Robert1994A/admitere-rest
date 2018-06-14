package ro.inf.ucv.admitere.service.utils;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

import freemarker.template.Configuration;
import freemarker.template.Template;
import ro.inf.ucv.admitere.entity.User;
import ro.inf.ucv.admitere.service.UserService;
import ro.inf.ucv.admitere.utils.ListUtils;
import ro.inf.ucv.admitere.wrapper.Email;

@Service
public class Mailer {

	private static final Logger logger = Logger.getLogger(Mailer.class);

	@Autowired
	private JavaMailSenderImpl mailSender;

	@Autowired
	private Configuration configuration;

	@Autowired
	private UserService userService;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public boolean sendMail(String mailFrom, List<String> mailTo, List<String> mailCC, String mailSubject, String text,
			String mailTemplate, HashMap<String, String> velocityContextMap, List<MultipartFile> files) {
		boolean success = false;
		try {
			String html = null;
			if (StringUtils.isNotBlank(text)) {
				html = text;
			} else if (StringUtils.isNotBlank(mailTemplate)) {
				Template template = this.configuration.getTemplate(mailTemplate);
				html = FreeMarkerTemplateUtils.processTemplateIntoString(template, velocityContextMap);
			}

			MimeMessage message = this.mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, false);
			if (!ListUtils.isEmpty(mailCC)) {
				for (String cc : mailCC) {
					if (StringUtils.isNotBlank(cc)) {
						helper.setCc(cc);
					}
				}
			}

			if (!ListUtils.isEmpty(files)) {
				for (MultipartFile file : files) {
					if (file != null && !file.isEmpty()) {
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
				throw new Exception("Must set at least one recipient to send email.");
			}

			if (StringUtils.isNotBlank(mailSubject)) {
				helper.setSubject(mailSubject);
			}

			if (StringUtils.isNotBlank(mailFrom)) {
				helper.setFrom(mailFrom);
			}

			if (StringUtils.isNotBlank(html)) {
				helper.setText(html, true);
			}

			this.mailSender.send(message);
			success = true;
		} catch (Exception e) {
			logger.error("Send email error: ", e);
		}

		return success;
	}

	public boolean sendMail(List<String> mailTo, List<String> mailCC, String mailSubject, String text,
			String mailTemplate, HashMap<String, String> velocityContextMap) {
		return sendMail(null, mailTo, mailCC, mailSubject, text, mailTemplate, velocityContextMap, null);
	}

	public boolean sendMail(List<String> mailTo, List<String> mailCC, String mailSubject, String mailTemplate,
			HashMap<String, String> velocityContext) {
		return sendMail(null, mailTo, mailCC, mailSubject, null, mailTemplate, velocityContext, null);
	}

	public boolean sendMail(String mailFrom, List<String> to, List<String> cc, String subject, String content) {
		return sendMail(mailFrom, to, cc, subject, content, null, null, null);
	}

	public boolean sendMail(Email email, Principal principal) {
		String myEmail = null;
		if (email.isUseMyEmailAddress()) {
			User authenticatedUser = this.userService.findByUsername(principal.getName());
			if (authenticatedUser != null) {
				myEmail = authenticatedUser.getEmail();
			}
		}

		List<String> to = email.getTo();
		if (!ListUtils.isEmpty(to)) {
			List<User> users = this.userService.findAllById(to);
			if (!ListUtils.isEmpty(users)) {
				List<String> emails = users.stream().map(User::getEmail).collect(Collectors.toList());
				return this.sendMail(myEmail, emails, email.getCc(), email.getSubject(), email.getContent(), null, null,
						email.getAttachments());
			}
		}

		return false;
	}
}