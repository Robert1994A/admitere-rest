package ro.inf.ucv.admitere.wrapper;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class Email {

	@NotEmpty
	private List<String> to;

	private List<String> cc;

	private List<MultipartFile> attachments;;

	@NotEmpty
	private String subject;

	@NotEmpty
	private String content;

	private boolean useMyEmailAddress = false;

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isUseMyEmailAddress() {
		return useMyEmailAddress;
	}

	public void setUseMyEmailAddress(boolean useMyEmailAddress) {
		this.useMyEmailAddress = useMyEmailAddress;
	}

	public List<MultipartFile> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<MultipartFile> attachments) {
		this.attachments = attachments;
	}

}
