import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by vidit on 2/24/17.
 */
public class TestPreOrder {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Tree t = new Tree();
        t.add(1);
        t.add(4);
        t.add(6);
        t.add(8);
        t.add(13);
        t.add(15);
        t.add(22);

        t.iterator_preorder(t.root);
    }

    @After
    public void cleanUpStreams(){
        System.setOut(null);
        System.setErr(null);
    }


    @Test
    public void testInsertion() {

        Assert.assertEquals(outContent.toString(), "4, 1, 8, 6, 15, 13, 22, ");

    }
}
