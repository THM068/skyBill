package com.sky

import org.apache.http.protocol.HTTP
import org.junit.Rule
import org.mockserver.client.server.MockServerClient
import org.mockserver.junit.MockServerRule
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import javax.xml.ws.Response


import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.verify.VerificationTimes.exactly;
/**
 * Created by tm1c14 on 07/06/2016.
 */
class TestMockServer extends Specification {
    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this, 9000);
    RestTemplate rs = new RestTemplate()

    private MockServerClient mockServerClient;
    def 'Run the foo endpoint'() {
        given:
            mockServerClient.when(HttpRequest.request("/foo"))
                    .respond(
                    HttpResponse.response().withStatusCode(200).

                            withBody(expected)
            );
        when:
            ResponseEntity<String> re = rs.getForEntity('http://localhost:9000/foo',String.class)

        then:
            re.getStatusCode() == HttpStatus.OK
            re.getBody() == expected
        where:
            expected = "This is very easy ..."
    }
}
