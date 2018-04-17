package cc.kpug.benedict.consumer.utils;

import org.apache.commons.validator.routines.UrlValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Lawrence
 * @version 0.0.1
 * @note
 * @since 2018. 3. 11.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HttpUtilsTest {

    @Autowired
    UrlValidator urlValidator;

    @Test
    public void test() {
        assertTrue(urlValidator.isValid("http://www.naver.com"));
        assertFalse(urlValidator.isValid("http://localhost:9000"));
    }
}
