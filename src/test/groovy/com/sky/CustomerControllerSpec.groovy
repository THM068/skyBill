package com.sky

import grails.test.mixin.TestFor
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
}
