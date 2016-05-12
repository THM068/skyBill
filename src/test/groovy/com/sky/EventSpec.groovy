package com.sky

import com.fasterxml.jackson.databind.ObjectMapper;
import spock.lang.Specification

import java.text.SimpleDateFormat;

/**
 * Created by tm1c14 on 11/05/2016.
 */
public class EventSpec extends Specification  {

    void 'Test custom date serializer'() {
        given:
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss")
            String toParse = "20-12-2014 02:30:30"
            Date date = df.parse(toParse)
            Event event = new Event("Party", date)

        when:
            String result = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(event)

        then:
            result.contains(toParse)
    }
}
