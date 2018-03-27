package cc.kpug.benedict.consumer.utils;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Lawrence
 * @note
 * @since 2018. 3. 11.
 * @version 0.0.1
 */
public class ParserUtils {
    public static List<String> extractMethodSignature(String rawContent) {
        Pattern pattern =  Pattern.compile("((public|private|protected|static|final|native|synchronized|abstract|transient)+\\s)+[\\$_\\w\\<\\>\\[\\]]*\\s+[\\$_\\w]+\\([^\\)]*\\)?\\s*");
        Matcher matcher = pattern.matcher(rawContent);

        List<String> listOfMethodSignature = Lists.newArrayList();
        while (matcher.find()) {
            listOfMethodSignature.add(matcher.group());
        }
        return listOfMethodSignature;
    }

    public static List<String> extractMethodName(List<String> listOfMethodSignature){
        List<String> listOfMethodName = Lists.newArrayList();
        final Pattern pattern =  Pattern.compile("(?<=\\s)[\\$_\\w]+(?=\\()");

        for(String content: listOfMethodSignature) {
            Matcher matcher = pattern.matcher(content);
            while (matcher.find()) {
                listOfMethodName.add(matcher.group());
            }
        }

        return listOfMethodName;
    }
}
