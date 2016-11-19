import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

    
    private Simulation sim;
    
    private Consumer user1;
    private Consumer user2;
    private Consumer user3;
    private Consumer user5;
    
    private Document doc1;
    private Document doc2;
    
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
        producer2 = new Producer_Strategy_B("Sports", "David", sim);
        producer3 = new Producer_Strategy_B("Movies", "Tom", sim);
        
        doc1 = new Document("Sports", producer1);
        doc2 = new Document("Politics", producer2);
    }
    
    @Test
    public void testGetTag() {
        assertEquals("The tag of the Consumer should be", "Sports", producer1.getTag());
        assertEquals("The tag of the Consumer should be", "Sports", user1.getTag());
    }
    
    @Test
    public void testGetName() {
        assertEquals("The name of the Consumer should be", "David", producer1.getName());
        assertEquals("The name of the Consumer should be", "Hamza", user1.getName());
    }
    
    @Test
    public void testSetName() {
        producer1.setName("Anthony");
        assertEquals("The new Name of the Consumer is", "Anthony", producer1.getName());
        user1.setName("David");
        assertEquals("The new Name of the Consumer is", "David", user1.getName());
    }
    
    @Test
    public void testTag() {
        producer1.setTag("Music");
        assertEquals("The new Tag of the Consumer is", "Music", producer1.getTag());
        user1.setTag("Music");
        assertEquals("The new Tag of the Consumer is", "Music", user1.getTag());
    }
    
    public void testLike() {
        user1.like(doc1);
		assertEquals("This document would get 1 like", 1, doc1.getNumOfLikes());
		producer1.like(doc2);
		assertEquals("This document would get 1 like", 1, doc2.getNumOfLikes());
    }
    
    @Test
    public void testProduceDocument() {
        producer1.ProduceDocument();
        assertEquals("This Producer has 1 documents", 2, producer1.getMyDocuments().size());
    }
    
    @Test
    public void testIsFollowing() {
        Consumer user4 = new Consumer("Sports", "Doug", sim);
        assertTrue(user4.getTag().equals(producer1.getTag()));
        user4.follow(producer1);
        assertEquals("Producer has one follower ", 1, producer1.getFollowers().size());
        
        Consumer user6 = new Consumer("Sports", "Dan", sim);
		assertTrue(user4.getTag().equals(producer1.getTag()));
		user4.follow(producer1);
		assertEquals("Producer 2 has one follower ", 1, producer1.getFollowers().size());
		assertFalse(producer2.getFollowers().size() == 1);
    }
    
    @Test
    public void testUnFollow() {
       	user5.follow(producer2);
		user5.unfollow(producer2);
		assertTrue(producer2.getFollowers().size() == 0);
        
        Consumer user = new Consumer("Sports", "Sam", sim);
        assertTrue(user.getTag().equals(producer2.getTag()));
        user.follow(producer2);
        user.unfollow(producer2);
        assertTrue(producer2.getFollowers().size() == 0);
    }
    
    @Test
    public void testFollowerList() {
        producer1.follow(user1);
		assertEquals("This is the string representation of the Consumer follower list", "Hamza's followers include {David }\n", user1.FollowerList());
        
        Producer producer4 = new Producer_Strategy_A("Sports", "Sandra", sim);
        producer1.follow(producer4);
        System.out.println(producer1.FollowerList());
        assertEquals("This is the string representation of the Consumer follower list", "Sandra's followers include {David }\n", producer4.FollowerList());
    }
    
    @Test
    public void LikeSimilarityTest(){
        user1.like(doc1);
        user2.like(doc1);
        assertEquals("count should be 1", 1, user1.LikeSimilarity(user2));
    }   
    
    @Test
    public void FollowSimilarityTest(){
        user3.follow(user1);
        user3.follow(user2);
        assertEquals("Return count should be 1", 1, user1.FollowSimilarity(user2));
    }
    
    @Test
    public void distanceTest(){
        user2.follow(user1);
        assertEquals("Value returned should be 2", 2, user1.distance(user2));
        user3.follow(user2);
        user3.follow(user5);
        assertEquals("Value retruned should be 1", 1, user2.distance(user5));
        assertEquals("Value returned should be 0", 0, user5.distance(user1));
    }
    
    @Test
    public void getTypeTest(){
        assertTrue(producer1.getType() == "producer (Type-A)");
        assertTrue(producer2.getType() == "producer (Type-B)");
        assertTrue(user1.getType() == "consumer");
    }
}