package cc.kpug.benedict.consumer.services;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author Lawrence
 * @version 0.0.2
 * @note
 * @since 2018. 4. 10.
 */
@Service
public class AnalogueTermServiceImpl implements AnalogueTermService {

    private Map<String, List<String>> synonyms = Maps.newHashMap();

    public AnalogueTermServiceImpl(){
    }

    @PostConstruct
    public void setUp() {
        synonyms.put("April", Lists.newArrayList("Apr"));
        synonyms.put("Apr", Lists.newArrayList("April"));
        synonyms.put("December", Lists.newArrayList("Dec"));
        synonyms.put("Dec", Lists.newArrayList("December"));
    }

    @Override
    public List<String> findAnalogueTerm(String word) {
        return synonyms.getOrDefault(word, Lists.newArrayList(word));
    }
}
