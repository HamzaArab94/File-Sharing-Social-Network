import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class DocumentTest {
	
	private Simulation sim1;
	
	private List<User> tempList1;
	private List<User> tempList2;
	
	private Consumer consumer1;
	
	private Producer producer1;
	private Producer producer2;
	
	private Document doc1;
	
	@Before
	public void setUp() throws Exception {
		
		sim1 = new Simulation();
		
		tempList1 = new ArrayList<User>();
		tempList2 = new ArrayList<User>();
		
		consumer1 = new Consumer("Politics", "Steven Phillips", sim1);
		
		producer1 = new Producer_Strategy_A("Politics", "Kevin Gates", sim1);
		producer2 = new Producer_Strategy_B("Technology", "David Abbot", sim1);
		
		doc1 = new Document("Politics", producer1);
	}

	@Test
	public void getTagTest() {
		assertEquals("Tag of doc1 should be Politics" ,"Politics", doc1.getTag());
	}
	
	@Test
	public void setTagTest(){
		doc1.setTag("Music");
		assertEquals("Tag should be Music","Music", doc1.getTag());
	}
	
	@Test
	public void getNumOfLikesTest(){
		consumer1.like(doc1);
		assertEquals("Num of likes should be 1.", 1, doc1.getNumOfLikes());
	}
	
	@Test
	public void setNumOfLikesTest(){
		doc1.setNumofLikes(5);
		assertEquals("The number of likes should be 5.", 5, doc1.getNumOfLikes());
	}
	
	@Test
	public void getAuthorTest(){
		assertEquals("The author should be producer1", producer1, doc1.getAuthor());
	}
	 
	@Test
	public void setAuthorTest(){
		doc1.setAuthor(producer2);
		assertEquals("The author should be producer", producer2, doc1.getAuthor());
	}
	
	@Test
	public void getUserLikesTest(){
		
		consumer1.like(doc1);
		List<User> compareList = doc1.getUserLikes();
		tempList1.add(consumer1);
		tempList1.add(producer1);

		compareList.removeAll(tempList1);
		
		assertTrue(compareList.size() == 0);
	}

}

    