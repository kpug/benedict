package cc.kpug.benedict.consumer.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Lawrence
 * @note
 * @since 2018. 3. 11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserUtilsTest {

    private String raw = "import java.util.List;\n" +
            "\n" +
            "/**\n" +
            " * Created by before30 on 2016. 11. 16..\n" +
            " */\n" +
            "public class CoinChange {\n" +
            "    public static int total(int n, int[] v, int i) {\n" +
            "        if (n<0) {\n" +
            "            return 0;\n" +
            "        } else if (n ==0) {\n" +
            "            return 1;\n" +
            "        } else if (i== v.length && n > 0) {\n" +
            "            return 0;\n" +
            "        } else {\n" +
            "            return total(n - v[i], v, i) + total(n, v, i+1);\n" +
            "        }\n" +
            "    }\n" +
            "    public static int total(int n, int[] v, int i) {\n" +
            "    public static int total(int n, int[] v, int i) {\n" +
            "//        System.out.println(solve(100));\n" +
            "        int[] coins = {1, 5, 10};\n" +
            "        System.out.println(total(100,coins, 0));\n" +
            "\n" +
            "    }\n" +
            "}";

    @Test
    public void test(){
        List<String> linesMethodDefined = ParserUtils.getLinesMethodDefined(raw);
    }
}
