package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {

    private LinkedListStack<URL> back;
    private LinkedListStack<URL> forward;

    private URL current;

    public WebBrowser() {
        back = new LinkedListStack<>();
        forward = new LinkedListStack<>();
    }

    public WebBrowser(SinglyLinkedList<URL> history){
        back = new LinkedListStack<>();
        forward = new LinkedListStack<>();

        for (URL url : history){
            back.push(url);
        }

        current = back.pop();

//        back = new LinkedListStack<>();
//
//        for (int i = history.size() - 1; i >= 0; i--){
//            back.push(history.deleteFirst());
//        }
//
//        current = back.pop();
//
//        forward = new LinkedListStack<>();
    }

    public void visit(URL webpage){
        if (current != null){
            back.push(current);
        }
        current = webpage;
        forward.clear();
    }

    public URL back() throws NoSuchElementException {

        forward.push(current);
        if (back.isEmpty()){
            throw new NoSuchElementException();
        }
        current = back.pop();
        return current;
    }

    public URL forward() throws NoSuchElementException {
        back.push(current);
        if (forward.isEmpty()){
            throw new NoSuchElementException();
        }
        current = forward.pop();
        return current;
    }

    public SinglyLinkedList<URL> history() {
        SinglyLinkedList<URL> history = new SinglyLinkedList<>();
        LinkedListStack<URL> tempBack = back;

        while(!tempBack.isEmpty()){
            history.insertFirst(tempBack.pop());
        }

        if (current != null){
            history.insertFirst(current);
        }

        return history;
    }

}
