import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ConsumerTest {

	private Simulation sim;
	private Consumer user1;
	private Consumer user2;
	private Consumer user3;
	private Consumer user5;
	private Document doc1;
	private Producer producer1;
	private Producer producer2;
	private Producer producer3;
	
	@Before
	public void setUp() throws Exception{
		sim = new Simulation();
		user1 = new Consumer("Sports", "Hamza", sim);
		user2 = new Consumer("Sports", "Hamza", sim);
		user3 = new Consumer("Music", "John", sim);
		user5 = new Consumer("Movies", "Dan", sim);
		producer1 = new Producer_Strategy_A("Sports", "David", sim);
		producer2 = new Producer_Strategy_B("Movies", "Sara", sim);
		producer3 = new Producer_Strategy_A("Movies", "Angela", sim);
		doc1 = new Document("Sports", producer1);
	}
	
	@Test
	public void testConsumerGetTag() {
		assertEquals("The tag of the Consumer should be", "Sports", user1.getTag());
	}
	
	@Test
	public void testConsumerGetName() {
		assertEquals("The name of the Consumer should be", "Hamza", user1.getName());
	}
	
	@Test
	public void testConsumerSetName() {
		user1.setName("David");
		assertEquals("The new Name of the Consumer is", "David", user1.getName());
	}
	
	@Test
	public void testConsumerSetTag() {
		user1.setTag("Music");
		assertEquals("The new Tag of the Consumer is", "Music", user1.getTag());
	}
	
	@Test
	public void testConsumerSimulation() {
		assertEquals("The Simulation should be", sim, user1.getSim());
	}
	
	@Test
	public void testConsumerLike() {
		user1.like(doc1);
		assertEquals("This document would get 1 like", 1, doc1.getNumOfLikes());
	}
	
	@Test
	public void testEqualsObject() {
		assertTrue(user1.equals(user2));
		assertFalse(user3.equals(user2));
	}
	
	
	@Test
	public void testConsumerFollowing() {
		Consumer user4 = new Consumer("Sports", "Doug", sim);
		assertTrue(user4.getTag().equals(producer1.getTag()));
		user4.follow(producer1);
		assertEquals("Producer 2 has one follower ", 1, producer1.getFollowers().size());
		assertFalse(producer2.getFollowers().size() == 1);
	}
	
	@Test
	public void testConsumerUnFollowing() {
		assertTrue(user5.getTag().equals(producer2.getTag()));
		assertTrue(user5.getTag().equals(producer3.getTag()));
		user5.follow(producer2);
		user5.follow(producer3);
		user5.unfollow(producer2);
		assertEquals("Producer 3 has one follower ", 1, producer3.getFollowers().size());
		assertFalse(producer2.getFollowers().size() == 1);
	}
	
	@Test
	public void testConsumerFollowerList() {
		producer1.follow(user1);
		assertEquals("This is the string representation of the Consumer follower list", "Hamza's followers include {David }\n", user1.FollowerList());
	}
	
	
	

}
