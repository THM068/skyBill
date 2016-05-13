package com.sky

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.text.StringContainsInOrder
import spock.lang.Specification

import java.text.SimpleDateFormat
import static spock.util.matcher.HamcrestSupport.that
import static  org.hamcrest.CoreMatchers.containsString
import static  org.hamcrest.CoreMatchers.not
/**
 * Created by tm1c14 on 11/05/2016.
 */
public class JacksonAnnotationSpec extends Specification  {

    void 'Test custom date serializer'() {
        given:
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
            String toParse = "20-12-2014 02:30:30"
            Date date = df.parse(toParse)
            Event event = new Event("Party", date)

        when:
            String result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(event)

        then:
        that result, containsString(toParse)
            //result.contains(toParse)
    }

    void 'Test bean with JsonIgnoreProperties'() {
        when:
            String result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(new BeanWithIgnore())
        then:
            that result, containsString("Kerrie Channer")
            that result, not(containsString("id"))
    }

    void 'Test bean with JsonIgnore'() {
        given:
            BeanWithIgnore bw = new BeanWithIgnore()
        when:
            String result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(bw)
        then:
            that result, containsString("Kerrie Channer")
            that result, not(containsString("eyeColor"))
    }

    void 'Test bean with JsonIgnoreType'() {
        given:
            User.Name name = new User.Name("John","Doe")
            User user = new User(1, name);
        when:
            String result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user)
        then:
            println result
            that result, containsString("1")
            that result, not(containsString("name"))
            that result, not(containsString("John"))
    }

    void 'Test with jsonInclude'() throws JsonProcessingException, IOException  {
        given:
            BeanWithJsonInclude bi = new BeanWithJsonInclude(45, null)
        when:
            String result = new ObjectMapper().writeValueAsString(bi)
        then:
            that result, containsString("45")
            that result, not(containsString("name"))
    }

    void 'Test with jsonAutoDetect'() {
        given:
            PrivateBean pb = new PrivateBean(34, "Kerrie")
        when:
            String result = new ObjectMapper().writeValueAsString(pb)
        then:
            that result, containsString("34")
            that result, containsString("Kerrie")
    }
}
