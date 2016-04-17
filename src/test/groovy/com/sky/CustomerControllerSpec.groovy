package com.sky

import com.sky.ConnectException
import com.sky.CustomerBillService
import com.sky.CustomerBillWrapper
import grails.test.mixin.TestFor
import skybill.com.sky.CustomerController
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(CustomerController)
class CustomerControllerSpec extends Specification {

    def customerBillService

    def setup() {
        customerBillService = Mock(CustomerBillService);
        controller.customerBillService = customerBillService
    }


    void "A customer/'s bill is returned"() {
        when:
            def model = controller.index()
        then:
            1 * customerBillService.getCustomerBill() >> new CustomerBillWrapper();

            model.customerBill instanceof CustomerBillWrapper
    }

    void "An error message code is returned when index action throws an excetion"() {
        given:
            String errorCode = 'resource.not.found'
        when:
            def model = controller.index()
        then:
            1 * customerBillService.getCustomerBill() >> { throw new ConnectException(errorCode)}

            view == '/customer/error'
            controller.modelAndView.model.errorCode == errorCode;
    }
}
