import org.apache.commons.lang.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.net.URL;
import java.io.IOException;

/**
 * Example program to list links from a URL.
 */
public class ListLinks {
    public static void main(String[] args) throws IOException {
        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        URL url = new URL(args[0]);
        System.out.println("Fetching " + url.toExternalForm() + "...");

        Document doc = Jsoup.parse(url, 3*1000);
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        System.out.println("\nMedia: " + media.size());
        for (Iterator i = media.iterator(); i.hasNext();) {
            Element src = (Element) i.next();
            if (src.tagName().equals("img"))
                System.out.println(" * " + src.tagName() + ": < " + src.attr("abs:src")
                        + "> " + src.attr("width") + "x" + src.attr("height") + " (" +
                        trim(src.attr("alt"), 20) + ")");
            else
                System.out.println(" * " + src.tagName() + ": <" + src.attr("abs:src") + ">");
        }

        System.out.println("\nImports: (" + imports.size() + ")");
        for (Iterator i2 = imports.iterator(); i2.hasNext();) {
            Element link = (Element) i2.next();
            System.out.println(
                    " * " + link.tagName() + " <" + link.attr("abs:href") + "> (" + link.attr("rel") + ")");
        }

        System.out.println("\nLinks: (" + links.size() + ")");
        for (Iterator i3 = imports.iterator(); i3.hasNext();) {
            Element link = (Element) i3.next();
            System.out.println(
                    " * a: <" + link.attr("abs:href") + ">  (" + trim(link.text(), 35) + ")");
        }
    }

    private static String trim(String s, int width) {
        if (s.length() > width)
            return s.substring(0, width-1) + ".";
        else
            return s;
    }
}
