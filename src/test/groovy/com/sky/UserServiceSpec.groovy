package com.sky

import grails.test.mixin.TestFor
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.lang.reflect.Member

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static  org.hamcrest.CoreMatchers.equalTo
import static spock.util.matcher.HamcrestSupport.that

/**
 * Created by thandomafela on 15/05/2016.
 */
@TestFor(UserService)
class UserServiceSpec extends Specification  {
    UserService userService
    MockRestServiceServer mockServer
    private final USER_LIST_URL = 'http://jsonplaceholder.typicode.com/users'
    private final USER_ID_URL = 'http://jsonplaceholder.typicode.com/users/5'

    def setup() {
        RestTemplate restTemplate = new RestTemplate()
        mockServer = MockRestServiceServer.createServer(restTemplate)
        userService = new UserService()
        userService.restTemplate = restTemplate
        userService.grailsApplication = grailsApplication
    }

    def 'UserService: check correct endpoint work'() {
        given:
            mockServer.expect(requestTo(USER_LIST_URL)).
                    andExpect(method(HttpMethod.GET)).
                    andRespond(withSuccess(getUserList_Json(), MediaType.APPLICATION_JSON))
        when:
            ResponseEntity<UserWrapper[]> response = userService.getUserListResponseEntity()
            mockServer.verify()
        then:
            that(response.getStatusCode(), equalTo(HttpStatus.OK ))
    }

    def 'UserService: returns a userWrapper array'() {
        given:
            mockServer.expect(requestTo(USER_LIST_URL)).
                   andExpect(method(HttpMethod.GET)).
                   andRespond(withSuccess(getUserList_Json(), MediaType.APPLICATION_JSON))
        when:
            UserWrapper[] userWrapperArray = userService.getUserWrapperArray()
            mockServer.verify()
        then:
            userWrapperArray[0] instanceof UserWrapper
            UserWrapper userWrapper = userWrapperArray[0]

            userWrapper.id == 1
        userWrapper.name == name
        userWrapper.email == email
        userWrapper.username == username

        where:
        name = "Leanne Graham"
        username ="Bret"
        email = "Sincere@april.biz"

    }

    def 'userService: can a user object'() {
        given:
            mockServer.expect(requestTo(USER_ID_URL)).
                    andExpect(method(HttpMethod.GET)).
                    andRespond(withSuccess(getUserJson(), MediaType.APPLICATION_JSON))
        when:
            UserWrapper userWrapper = userService.getUserWrapper(id)
            mockServer.verify()
        then:
            userWrapper != null
            userWrapper.id == id
            userWrapper.name == name
            userWrapper.email == email
            userWrapper.username == username

        where:
            id = 5
            name = 'Chelsey Dietrich'
        username = 'Kamren'
            email = 'Lucio_Hettinger@annie.ca'
    }
    private String getUserList_Json() {
        String result = UserServiceSpec.class.getClassLoader().getResource("com/sky/userList.json").text
        return result
    }

    private String getUserJson() {
        String result = UserServiceSpec.class.getClassLoader().getResource("com/sky/user.json").text
        return result
    }

}
