package com.sky

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate


class CustomerBillService {

    def grailsApplication

    public CustomerBillWrapper getCustomerBill() throws ConnectException {
        ResponseEntity<CustomerBillWrapper> response

        try {
            response = getCustomerBillResponseEntity()
        }
        catch (RestClientException rex) {
            throw new ConnectException( rex.message)
        }

        if(response.getStatusCode() == HttpStatus.OK) {
            CustomerBillWrapper wrapper = response.getBody();
            return wrapper
        }
        else {
            throw new ConnectException('connection.fail.error');
        }
    }

    public ResponseEntity<CustomerBillWrapper> getCustomerBillResponseEntity() {
        RestTemplate restTemplate = new RestTemplate()
        String billUrl = grailsApplication.config.grails.customer.bill.url

        ResponseEntity<CustomerBillWrapper> response = restTemplate.getForEntity(billUrl, CustomerBillWrapper.class);


        return response

    }
}
