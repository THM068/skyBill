package com.sky

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

/**
 * Created by thandomafela on 10/05/2016.
 */
class JsonExampleSpec extends Specification {


    void 'JsonAnyGetter test'() {
        given:
            ExtendableBean exBean = new ExtendableBean();
            Map map = exBean.getProperties();
            map.put("Friend", "Kerrie");
            map.put("Sex", "Female");
            ObjectMapper mapper = new ObjectMapper();

        when:
            String result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(exBean);
        then:
            result.contains("Sex")
             println result
     }
}
