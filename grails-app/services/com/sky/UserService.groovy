package com.sky

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

/**
 * Created by thandomafela on 15/05/2016.
 */
class UserService {

    def grailsApplication
    RestTemplate restTemplate

    public UserWrapper getUserWrapper(Integer id) {
        String url = grailsApplication.config.grails.user.with.id.url
        UserWrapper wrapper = restTemplate.getForObject(url, UserWrapper.class, ["id": id])

        return wrapper
    }

    public ResponseEntity<UserWrapper[]> getUserListResponseEntity() {
        String url = grailsApplication.config.grails.user.list.url
        ResponseEntity<UserWrapper[]> responseEntity = restTemplate.getForEntity(url, UserWrapper[].class)

        return responseEntity
    }

    public UserWrapper[] getUserWrapperArray() {
        ResponseEntity<UserWrapper []> responseEntity
        UserWrapper [] userWrapperArray

        try {
            responseEntity = getUserListResponseEntity()
        }
        catch(RestClientException ex) {
            throw new ConnectException('Error fetching user list')
        }

        if(HttpStatus.OK == responseEntity.getStatusCode()){
            userWrapperArray = responseEntity.getBody()
            return userWrapperArray
        }
        else {
            throw new ConnectException('connection.fail.error');
        }
    }
}
