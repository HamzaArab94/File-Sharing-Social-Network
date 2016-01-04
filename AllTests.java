import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ConsumerTest.class, ProducerTest.class, DocumentTest.class})
public class AllTests {

}
