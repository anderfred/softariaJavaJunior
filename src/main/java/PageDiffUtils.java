import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PageDiffUtils {

    public static List<String> getEditedPages(Page firstDay, Page secondDay) {
        return firstDay.getContent().entrySet().stream()
                .filter(map -> secondDay.getContent().containsKey(map.getKey()) && !secondDay.getContent().get(map.getKey()).equals(map.getValue()))
                .map(Map.Entry::getKey).sorted().collect(Collectors.toList());
    }

    public static List<String> getDeletedPages(Page firstDay, Page secondDay){
        return firstDay.getContent().entrySet().stream()
                .filter(map -> !secondDay.getContent().containsKey(map.getKey()))
                .map(Map.Entry::getKey).sorted().collect(Collectors.toList());
    }


    public static List<String> getNewPages(Page firstDay, Page secondDay){
        return getDeletedPages(secondDay, firstDay);
    }
}

