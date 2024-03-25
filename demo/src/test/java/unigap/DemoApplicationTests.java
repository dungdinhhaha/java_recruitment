package unigap;

import io.sentry.Sentry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void ssss() {
		try {
			throw new Exception("This is a test.");
		} catch (Exception e) {
			Sentry.captureException(e);
		}
	}


}
