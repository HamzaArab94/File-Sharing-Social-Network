import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProducerTest {

	
	private Simulation sim;
	private Producer producer1;
	private Producer producer2;
	private Producer producer3;
	
	@Before
	public void setUp() throws Exception{
		sim = new Simulation();
		producer1 = new Producer_Strategy_A("Sports", "David", sim);
		producer2 = new Producer_Strategy_A("Sports", "David", sim);
		producer3 = new Producer_Strategy_A("Movies", "Tom", sim);
	}
	
	@Test
	public void testProducerGetTag() {
		assertEquals("The tag of the Consumer should be", "Sports", producer1.getTag());
	}
	
	@Test
	public void testProducerGetName() {
		assertEquals("The name of the Consumer should be", "David", producer1.getName());
	}
	
	@Test
	public void testProducerSetName() {
		producer1.setName("Anthony");
		assertEquals("The new Name of the Consumer is", "Anthony", producer1.getName());
	}
	
	@Test
	public void testProduceretTag() {
		producer1.setTag("Music");
		assertEquals("The new Tag of the Consumer is", "Music", producer1.getTag());
	}
	
	@Test
	public void testProducerSimulation() {
		assertEquals("The Simulation should be", sim, producer1.getSim());
	}
	
	@Test
	public void testProduceDocument() {
		producer1.ProduceDocument();
		producer1.ProduceDocument();
		assertEquals("This Producer has two documents", 2, producer1.getMyDocuments().size());
	}
	
	@Test
	public void testEqualsObject() {
		assertTrue(producer1.equals(producer2));
		assertFalse(producer3.equals(producer2));
	}
	
	
	@Test
	public void testProducerFollowing() {
		Consumer user4 = new Consumer("Sports", "Doug", sim);
		assertTrue(user4.getTag().equals(producer1.getTag()));
		user4.follow(producer1);
		assertEquals("Producer has one follower ", 1, producer1.getFollowers().size());
	}
	
	@Test
	public void testProducerUnFollowing() {
		Consumer user = new Consumer("Sports", "Sam", sim);
		assertTrue(user.getTag().equals(producer2.getTag()));
		user.follow(producer2);
		user.unfollow(producer2);
		assertTrue(producer2.getFollowers().size() == 0);
	}
	
	@Test
	public void testProducerFollowerList() {
		Producer producer4 = new Producer_Strategy_B("Sports", "Sandra", sim);
		producer1.follow(producer4);
		System.out.println(producer1.FollowerList());
		assertEquals("This is the string representation of the Consumer follower list", "Sandra's followers include {David }\n", producer4.FollowerList());
	}
	
	@Test
	public void testProducer() {
		Producer producer4 = new Producer_Strategy_B("Sports", "Sandra", sim);
		producer1.follow(producer4);
		System.out.println(producer1.FollowerList());
		assertEquals("This is the string representation of the Consumer follower list", "Sandra's followers include {David }\n", producer4.FollowerList());
	}
	

}
