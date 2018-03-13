package cc.kpug.benedict.consumer.utils;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Lawrence
 * @note
 * @since 2018. 3. 11.
 */
public class ParserUtils {
    public static List<String> getLinesMethodDefined(String rawContent) {
        System.out.println(rawContent);
//        Pattern pattern = Pattern.compile("((public|private|protected|static|final|native|synchronized|abstract|transient)+\\s)+[\\$_\\w\\<\\>\\[\\]]*\\s+[\\$_\\w]+\\([^\\)]*\\)?\\s*\\{?\\s");
        // TODO 적절한 regex를 찾아라!
        Pattern pattern = Pattern.compile("((public|private|protected|static|final|native|synchronized|abstract|transient)+\\s)+");
        Matcher matcher = pattern.matcher(rawContent);

        if(matcher.find()) {
            for(int i = 0; i < matcher.groupCount(); i++) {
                System.out.println(i + " " + matcher.group(i));
            }
            return Lists.newArrayList();
        } else {
            System.out.println("there");
            return Lists.newArrayList();
        }

    }
}
