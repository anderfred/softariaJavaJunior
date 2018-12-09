import javax.mail.*;
import javax.mail.internet.*;
import java.util.List;
import java.util.Properties;

public class NotificationSender {

    private Page today;
    private Page yesterday;
    private String address;

    public NotificationSender(Page yesterday, Page today, String address) {
        this.today = today;
        this.yesterday = yesterday;
        this.address = address;
    }

    public String prepareText() {
        StringBuilder content = new StringBuilder();
        content.append("Здравствуйте, дорогая и.о. секретаря ");
        List<String> createdPages = PageDiffUtils.getNewPages(yesterday, today);
        List<String> editedPages = PageDiffUtils.getEditedPages(yesterday, today);
        List<String> deletedPages = PageDiffUtils.getDeletedPages(yesterday, today);
        if (createdPages.size() == 0 && editedPages.size() == 0 && deletedPages.size() == 0)
            content.append("\n За последние сутки ничего не изменилось.");
        else {
            content.append("\nЗа последние сутки во вверенных Вам сайтах произошли следующие изменения:\n");
            content.append("\n\tИсчезли следующие страницы:");
            content.append(deletedPages);
            content.append("\n\tПоявились следующие новые страницы:");
            content.append(createdPages);
            content.append("\n\tИзменились следующие страницы:");
            content.append(editedPages);
            content.append("\n\nС уважением,\n" +
                    "автоматизированная система\n" +
                    "мониторинга.\n" +
                    "\n");
        }
        System.out.println(content.toString());
        return content.toString();
    }

    public void send() throws MessagingException {
        String msg = prepareText();
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("578ba808344f50", "328dc576fda213");
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("from@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(address));
        message.setSubject("Mail Subject");

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/plain; charset=UTF-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }
}
