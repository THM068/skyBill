package com.sky

import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

/**
 * Created by thandomafela on 10/06/2016.
 */
@RestController
@RequestMapping(value = '/person')
class PersonController {

    @RequestMapping(value = 'name', method = RequestMethod.GET, produces = 'application/json' )
    public Map name() {
        def map = [name: 'thando', age: 34, sex: 'Male']
        return map
    }

    private final String IMAGE_LOCATION = "/Users/thandomafela/Desktop/kerrie.jpg";


    @RequestMapping(method = RequestMethod.GET, value = "/profile")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InputStreamResource> getProfilePicture() throws Exception{
        File imageFile = new File(IMAGE_LOCATION);
        if(!imageFile.exists()) {
            throw new FileNotFoundException("Image does not exist");
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<InputStreamResource>(
                new InputStreamResource(
                        new FileInputStream(imageFile)),httpHeaders, HttpStatus.OK);

    }


}
