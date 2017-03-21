import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyListTest {
    List<String> list;
    MyList myList;

    @Before
    public void beforeList(){
        list = new ArrayList<String>();
        list.add("aa");
        list.add("aah");
        list.add("ah");
        list.add("ed");
        list.add("he");
        myList = new MyList();
        myList.words = list;
    }

    @Test
    public void wordsAnaliseTrue() throws Exception {
        assertTrue(myList.wordsAnalise("aahed"));// aahed - aa, aah, ah, ed, he
    }

    @Test
    public void wordsAnaliseFalse() throws Exception {
        assertFalse(myList.wordsAnalise("aaheds"));// aahed - aa, aah, ah, ed, he
    }

}