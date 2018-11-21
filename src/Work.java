import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Work {
    private static Set<String> blacList;
    private static List<String> text;

    public static void start() {
        setBlacList();
        proverka();
        itog();
    }

    private static void setBlacList() {
        blacList = new TreeSet<>();
        try (FileReader reader = new FileReader("blacList.txt")) {
            String line = new String();
            int c;
            while ((c = reader.read()) != -1) {
                if ((char) c == '\r' || (char) c == '\n') {
                    line += " ";
                } else {
                    if ((char) c == '.' || (char) c == ',') {
                        line = line.trim();
                        line = line.toLowerCase();
                        blacList.add(line);
                        line = new String();
                    } else {
                        line += (char) c;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("===Цензура===");
        System.out.println(blacList);
        System.out.println("==================");
    }

    private static void proverka() {
        text = new ArrayList<>();
        try (FileReader reader = new FileReader("text.txt")) {
            String line = new String();
            int c;
            while ((c = reader.read()) != -1) {
                if ((char) c == '\r' || (char) c == '\n') {
                    line += " ";
                } else {
                    if ((char) c == '.' || (char) c == '!' || (char) c == '?') {
                        line = line.trim();
                        search(line);
                        line = new String();
                    } else {
                        line += (char) c;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void search(String line) {
        String line2;
        line2 = line.toLowerCase();
        String[] mas = line2.split("\\s*(\\s|,)\\s*");
        for (String s : mas) {
            if (blacList.contains(s)) {
                text.add(line);
                break;
            }
        }
    }

    private static void itog() {
        if (text.size() == 0) {
            System.out.println("Текст проше проверку на цензуру");
        } else {
            System.out.println("Количество предложений который надо исправить: " + text.size());
            System.out.println(text);
        }
    }
}

