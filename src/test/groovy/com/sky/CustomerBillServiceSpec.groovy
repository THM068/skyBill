package com.sky

import grails.test.mixin.TestFor
import org.apache.commons.io.IOUtils
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.web.client.RestTemplate
import spock.lang.Ignore
import spock.lang.Specification

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(CustomerBillService)
class CustomerBillServiceSpec extends Specification {

    CustomerBillService customerBillService
    MockRestServiceServer mockServer
    final String  url = 'http://safe-plains-5453.herokuapp.com/bill.json'

    def setup() {
        RestTemplate restTemplate = new RestTemplate()
        mockServer = MockRestServiceServer.createServer(restTemplate);
        grailsApplication.config.grails.customer.bill.url= url
        customerBillService = new CustomerBillService()
        customerBillService.restTemplate = restTemplate
        customerBillService.grailsApplication = grailsApplication
    }

    def cleanup() {
    }

    void "getCustomerBillResponseEntity: Check if bill endpoint  works"() {
        given:
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(getJson(), MediaType.APPLICATION_JSON));
        when:
            ResponseEntity<CustomerBillWrapper> response = customerBillService.getCustomerBillResponseEntity()
            mockServer.verify();
        then:
            response.getStatusCode() == HttpStatus.OK
    }

    void "getCustomerBillWrapper:  A customer bill wrapper is returned with correct values"() {
        given:
            mockServer.expect(requestTo(url))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withSuccess(getJson(), MediaType.APPLICATION_JSON));
        when:
            CustomerBillWrapper wrapper = customerBillService.customerBill
        then:
            wrapper instanceof CustomerBillWrapper
            wrapper.total == total
            wrapper.statement.generated == statement_generated
            wrapper.statement.due == statement_due

            PeriodWrapper periodWrapper = wrapper.statement.period
            periodWrapper.from == period_from
            periodWrapper.to == period_to

            PackageWrapper packageWrapper = wrapper.packageWrapper
            packageWrapper.total == package_total

            List<SubscriptionWrapper> subscriptions = packageWrapper.subscriptions

            subscriptions.get(0).type == package_subscription_0.type
            subscriptions.get(0).name == package_subscription_0.name
            subscriptions.get(0).cost  == package_subscription_0.cost

            subscriptions.get(1).type == package_subscription_1.type
            subscriptions.get(1).name == package_subscription_1.name
            subscriptions.get(1).cost  == package_subscription_1.cost

            subscriptions.get(2).type == package_subscription_2.type
            subscriptions.get(2).name == package_subscription_2.name
            subscriptions.get(2).cost  == package_subscription_2.cost

            CallChargeWrapper callChargeWrapper = wrapper.getCallCharges();
            callChargeWrapper.total == call_charge_total
            List<CallWrapper> callCharges = callChargeWrapper.calls

            for(CallWrapper callWrapper: callCharges) {
                assert callWrapper.called instanceof String
                assert callWrapper.duration == call_charge.duration
                assert callWrapper.cost == call_charge.cost
            }

            SkyStoreWrapper skyWrapper = wrapper.skyStore
            List<RentalWrapper> rentals = skyWrapper.rentals
            RentalWrapper rental = rentals.get(0);
            rental.title == rental_title
            rental.cost == rental_cost

            skyWrapper.total == skystore_total

            List<BuyAndKeepWrapper> buyAndKeep = skyWrapper.buyAndKeep
            buyAndKeep.get(0).title == buyAndKeep_item_0.title
            buyAndKeep.get(0).cost == buyAndKeep_item_0.cost
            buyAndKeep.get(1).title == buyAndKeep_item_1.title
            buyAndKeep.get(1).cost == buyAndKeep_item_1.cost


        where:
            total = 136.03
            statement_generated = "2015-01-11"
            statement_due = "2015-01-25"
            period_to = "2015-02-25"
            period_from = "2015-01-26"
            package_total = 71.4
            package_subscription_0 = [type: 'tv', name: 'Variety with Movies HD', cost: 50]
            package_subscription_1 = [type: 'talk', name: 'Sky Talk Anytime', cost: 5]
            package_subscription_2 = [type: 'broadband', name: 'Fibre Unlimited', cost: 16.4]
            call_charge = [called: "07716393769", duration: "00:23:03", cost: 2.13]
        call_charge_total = 59.64
            rental_title= "50 Shades of Grey"
            rental_cost = 4.99
            skystore_total = 24.97
            buyAndKeep_item_0 = [title: "That's what she said", cost: 9.99]
            buyAndKeep_item_1 = [title: "Broke back mountain", cost: 9.99]

    }

    void "getCustomerBillWrapper:  A connection exception is thrown when endpoint cannot be reached"() {
        given:
            ResponseEntity.metaClass.getStatusCode = { -> HttpStatus.NOT_FOUND }
        mockServer.expect(requestTo(url)).andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        when:
            CustomerBillWrapper wrapper = customerBillService.customerBill
        then:
            thrown(ConnectException)

    }

    private String getJson() {
        String result = CustomerBillServiceSpec.class.getClassLoader().getResource('com/sky/customer-bill.json').text
        return result
    }

}
