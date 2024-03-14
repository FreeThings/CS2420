package assign06;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.util.NoSuchElementException;
import java.net.URL;

public class WebBrowserTest {

    private SinglyLinkedList<URL> urlList;
    private WebBrowser emptyBrowser;
    private WebBrowser smallBrowser;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        emptyBrowser = new WebBrowser();
        smallBrowser = new WebBrowser();
        smallBrowser.visit(new URL("https://www.google.com"));
        smallBrowser.visit(new URL("https://www.yahoo.com"));
        smallBrowser.visit(new URL("https://www.bing.com"));
        smallBrowser.visit(new URL("https://www.youtube.com"));
        urlList = new SinglyLinkedList<URL>();
        urlList.insertFirst(new URL("https://www.google.com"));
        urlList.insertFirst(new URL("https://www.yahoo.com"));
        urlList.insertFirst(new URL("https://www.bing.com"));
        urlList.insertFirst(new URL("https://www.youtube.com"));
    }

    @Test
    void testCreateWebBrowserZeroParam() {
        WebBrowser wb = new WebBrowser();
        assertNotNull(wb);
    }

    @Test
    void testCreateWebBrowserFromList() {
        WebBrowser listBrowser = new WebBrowser(urlList);
        assertEquals("https://www.yahoo.com", listBrowser.back().toString());
        assertEquals("https://www.bing.com", listBrowser.back().toString());
        assertEquals("https://www.youtube.com", listBrowser.back().toString());
    }

    @Test
    void testVisitEmptyBrowser() throws MalformedURLException {
        emptyBrowser.visit(new URL("https://www.google.com"));
        emptyBrowser.visit(new URL("https://www.yahoo.com"));
        assertEquals("https://www.google.com", emptyBrowser.back().toString());
    }

    @Test
    void testVisitSmallBrowser() throws MalformedURLException {
        smallBrowser.visit(new URL("https://www.msn.com"));
        smallBrowser.visit(new URL("https://www.gmail.com"));
        assertEquals("https://www.msn.com", smallBrowser.back().toString());
        assertEquals("https://www.youtube.com", smallBrowser.back().toString());
        assertEquals("https://www.bing.com", smallBrowser.back().toString());
        assertEquals("https://www.yahoo.com", smallBrowser.back().toString());
        assertEquals("https://www.google.com", smallBrowser.back().toString());
    }

    @Test
    void testBackEmptyBrowser() {
        assertThrows(NoSuchElementException.class, () -> emptyBrowser.back());
    }

    @Test
    void testBackSmallBrowser() {
        assertEquals("https://www.bing.com", smallBrowser.back().toString());
        assertEquals("https://www.yahoo.com", smallBrowser.back().toString());
        assertEquals("https://www.google.com", smallBrowser.back().toString());
        assertThrows(NoSuchElementException.class, () -> smallBrowser.back());
    }

    @Test
    void testForwardEmptyBrowser() {
        assertThrows(NoSuchElementException.class, () -> emptyBrowser.forward());
    }

    @Test
    void testForwardSmallBrowser() {
        smallBrowser.back();
        smallBrowser.back();
        smallBrowser.back();
        assertEquals("https://www.yahoo.com", smallBrowser.forward().toString());
        assertEquals("https://www.bing.com", smallBrowser.forward().toString());
        assertEquals("https://www.youtube.com", smallBrowser.forward().toString());
        assertThrows(NoSuchElementException.class, () -> smallBrowser.forward());
    }

    @Test
    void testBackAndForwardSmallBrowser() {
        smallBrowser.back();
        smallBrowser.back();
        smallBrowser.back();
        smallBrowser.forward();
        smallBrowser.forward();
        assertEquals("https://www.youtube.com", smallBrowser.forward().toString());
    }

    @Test
    void testHistoryEmptyBrowser() {
        Object[] expected = new Object[0];
        Object[] actual = emptyBrowser.history().toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    void testHistorySmallBrowser() {
        Object[] expected = new Object[4];
        expected[0] = "https://www.youtube.com";
        expected[1] = "https://www.bing.com";
        expected[2] = "https://www.yahoo.com";
        expected[3] = "https://www.google.com";
        Object[] actual = smallBrowser.history().toArray();
        Object[] actual2 = new Object[actual.length];
        for (int i = 0; i < actual.length; i++) {
            actual2[i] = actual[i].toString();
        }
        assertArrayEquals(expected, actual2);
    }


//    @Test
//    public void testCreateWebBrowserZeroParam() {
//        WebBrowser wb = new WebBrowser();
//        assert(wb != null);
//    }
//
//    @Test
//    public void testBack() {
//
//        WebBrowser wb = new WebBrowser();
//
//    }
//
//    @Test
//    public void testCreateWebBrowserFromList() {
//        SinglyLinkedList<URL> urlList = new SinglyLinkedList<URL>();
//        try {
//            urlList.insertFirst(new URL("https://www.google.com"));
//            urlList.insertFirst(new URL("https://www.yahoo.com"));
//            urlList.insertFirst(new URL("https://www.bing.com"));
//            urlList.insertFirst(new URL("https://www.youtube.com"));
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//
//        WebBrowser listBrowser = new WebBrowser(urlList);
//
//
//    }
//
//    @Test
//    void testWebBrowserConstructorWithList() throws MalformedURLException {
//        WebBrowser wb = new WebBrowser(urlList);
//        assertEquals("https://www.yahoo.com", wb.back().toString());
//        assertEquals("https://www.bing.com", wb.back().toString());
//        assertEquals("https://www.youtube.com", wb.back().toString());
////        assertEquals("https://www.google.com", wb.toString());
//
//
//    }

}
