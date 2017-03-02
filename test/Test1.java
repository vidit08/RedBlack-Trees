

import org.junit.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Test1 {

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
        t.iterator_inorder(t.root);
		}

		@After
		public void cleanUpStreams(){
  	    System.setOut(null);
    	System.setErr(null);
		}


	@Test
	public void testInsertion() {

		Assert.assertEquals(outContent.toString(), "1, 4, 6, 8, 13, 15, 22, ");

	}

}