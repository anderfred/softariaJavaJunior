import java.time.LocalDate;
import java.util.Map;

public class Page {
    private Map<String, String> content;
    private LocalDate date;

    public Page(Map<String, String> data, LocalDate date) {
        this.content = data;
        this.date = date;
    }

    public Map<String, String> getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
