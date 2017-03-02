import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by vidit on 2/24/17.
 */
public class TestRemoval2 {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        Tree t = new Tree();

        for(int i=1;i<=16;i++){
            t.add(i);
        }

        for(int i=4;i<10;i++){
            t.remove(i);
        }
        t.iterator_inorder(t.root);
    }

    @After
    public void cleanUpStreams(){
        System.setOut(null);
        System.setErr(null);
    }


    @Test
    public void testInsertion() {

        Assert.assertEquals(outContent.toString(), "1, 2, 3, 10, 11, 12, 13, 14, 15, 16, ");

    }
}
