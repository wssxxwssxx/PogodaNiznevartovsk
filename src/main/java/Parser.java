import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    //будет возвращать страницу
    private static Document getPage() throws IOException {
        String url = "https://weather.rambler.ru/v-nizhnevartovske/";
        Document page = Jsoup.parse(new URL(url),3000);
        return page;
    }

    private static Pattern pattern = Pattern.compile("\\d{2}");

    private static String getDateFromString(String stringDate) throws Exception{
        Matcher matcher = pattern.matcher(stringDate);

        if(matcher.find()) {
            return matcher.group();
        }
        throw new Exception("Cant");
        //<span class="qlxe">26<!-- --> <!-- -->мая</span>
        //26 мая
    }

    public static void main(String[] args) throws Exception {
        Document page = getPage();
        Element tabletWth = page.select("table[class=_3krP]").first();
        Elements names = tabletWth.select("span[class=qlxe]");

        for(Element name: names){
            String dateString = name.select("span[class=qlxe]").text();
            String date = getDateFromString(dateString);
            System.out.println( date +  "   Температура    Осадки      Ветер   ");
        }


    }
}
