package cc.kpug.benedict.consumer.utils;

import cc.kpug.benedict.consumer.domain.MethodDescription;
import com.google.common.collect.Lists;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Lawrence
 * @note
 * @since 2018. 3. 11.
 * @version 0.0.1
 */
public class ParserUtils {

    public static List<String> extractMethodSignature(String rawContent) {
//        Pattern pattern =  Pattern.compile("((public|private|protected|static|final|native|synchronized|abstract|transient)+\\s)+[\\$_\\w\\<\\>\\[\\]]*\\s+[\\$_\\w]+\\([^\\)]*\\)?\\s*");
//        Matcher matcher = pattern.matcher(rawContent);
//
//        List<String> listOfMethodSignature = Lists.newArrayList();
//        while (matcher.find()) {
//            listOfMethodSignature.add(matcher.group());
//        }
//        return listOfMethodSignature;

        return extractMethodAndLineNumber(rawContent).stream()
                .map(Tuple2::getT2)
                .collect(Collectors.toList());
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

    public static List<String> extractMethodName(String methodSignature){
        List<String> methodNames = Lists.newArrayList();
        final Pattern pattern =  Pattern.compile("(?<=\\s)[\\$_\\w]+(?=\\()");
        Matcher matcher = pattern.matcher(methodSignature);
        while(matcher.find()) methodNames.add(matcher.group());
        return methodNames;
    }

    public static List<Tuple2<Integer, String>> extractMethodAndLineNumber(String rawContent) {

        Pattern pattern =  Pattern.compile("((public|private|protected|static|final|native|synchronized|abstract|transient)+\\s)+[\\$_\\w\\<\\>\\[\\]]*\\s+[\\$_\\w]+\\([^\\)]*\\)?\\s*");

        String[] split = rawContent.split("\\r?\\n");

        return IntStream.range(0, split.length)
                .mapToObj(i -> Tuples.of(i+1, split[i]))
                .filter(t2 -> pattern.matcher(t2.getT2()).find())
                .collect(Collectors.toList());
    }

    public static Tuple3<String, String, String> extractFileInfo(final String url) {

        /*
        * TODO : 정규식으로 project name 이랑 path 랑 좀 뺴줘요.
        * https://raw.githubusercontent.com/iluwatar/java-design-patterns/master/layers/src/main/java/com/iluwatar/layers/CakeBakingServiceImpl.java
        * */

        final String projectName = "TODO";
        final String branch = "TODO";
        final String filePath = url;

        return Tuples.of(projectName, branch, filePath);
    }
}
