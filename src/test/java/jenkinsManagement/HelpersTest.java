package jenkinsManagement;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class HelpersTest {
	Helpers helpers;

	@Before
	public void before() {
		helpers = new Helpers();
	}

	@Test
	public void testParseMapToString() {
		HashMap<String, String> testMap = new HashMap<String, String>();
		testMap.put("P1", "V1");
		testMap.put("P2", "V2");
		String expectedResult = "P1=V1&P2=V2";

		String actualResult = helpers.parseMapToString(testMap);

		assertEquals(actualResult, expectedResult);
	}

}
