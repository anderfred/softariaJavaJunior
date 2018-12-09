import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.Hashtable;

public class Init {
    public static void main(String[] args) {
        Page yesterday = new Page(new Hashtable<>(), LocalDate.of(2018, 12, 5));
        Page today = new Page(new Hashtable<>(), LocalDate.of(2018, 12, 6));
        {
            yesterday.getContent().put("http://1", "1");
            yesterday.getContent().put("http://2", "2");
            yesterday.getContent().put("http://3", "3");
            yesterday.getContent().put("http://4", "4");
            yesterday.getContent().put("http://yandex.ru", "hello");


            today.getContent().put("http://1", "1");
            today.getContent().put("http://2", "22");
            today.getContent().put("http://3", "33");
            today.getContent().put("http://google.com", "google");
            today.getContent().put("http://yandex.ru", "some text");
        }
        System.out.println("Init\n");

        NotificationSender notificationSender = new NotificationSender( yesterday, today, "superAdmin@gmail.com");
        try {
            notificationSender.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
