package service;


import models.GroupOrder;
import play.libs.mailer.Email;
import play.libs.mailer.MailerPlugin;

public class MailService {
    String sender = play.Play.application().configuration().getString("smtp.user");
    boolean flag = true;
    public void sendGroupDemandMail(String title, String htmlBody) {
	/* Example
       Email email = new Email();
        email.setSubject("Simple email");
        email.setFrom("Mister FROM <from@email.com>");
        email.addTo("Miss TO <to@email.com>");
        // adds attachment
        email.addAttachment("attachment.pdf", new File("/some/path/attachment.pdf"));
        // adds inline attachment from byte array
        email.addAttachment("data.txt", "data".getBytes(), "text/plain", "Simple data", EmailAttachment.INLINE);
        // sends text, HTML or both...
        email.setBodyText("A text message");
        email.setBodyHtml("<html><body><p>An <b>html</b> message</p></body></html>");*/
	
        try
	{
	Email email = new Email();
        email.setSubject(title);
        email.setFrom(sender);
        email.addTo(sender);

        email.setBodyHtml(htmlBody);
        MailerPlugin.send(email);
	}
	catch (Exception e)
	{
	    play.Logger.info("send mail exception !!!");
	    //	    flag = false;
	}
	//	return flag;
    }
}
