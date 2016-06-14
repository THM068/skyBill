package com.sky

import grails.test.mixin.TestFor
import org.junit.Rule
import org.mockserver.client.server.MockServerClient
import org.mockserver.junit.MockServerRule
import org.mockserver.mockserver.MockServer
import org.mockserver.model.Header
import org.mockserver.model.HttpRequest
import org.mockserver.model.HttpResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

/**
 * Created by tm1c14 on 07/06/2016.
 */
@TestFor(CustomerBillService)
class CustomerBillServiceSpecWithMockServer extends Specification{

    @Rule
    public MockServerRule mockServerRule = new MockServerRule(this, 9000)
    private MockServerClient mockServerClient

    def customerBillService
    final String  URL = 'http://safe-plains-5453.herokuapp.com/bill.json'

    def setup() {
        RestTemplate restTemplate = new RestTemplate()
        customerBillService = new CustomerBillService()
        customerBillService.restTemplate = restTemplate
        customerBillService.grailsApplication = grailsApplication
    }

    void "getCustomerBillResponseEntity: Check if bill endpoint  works"() {
        given:
            mockServerClient.when(HttpRequest.request("/bill.json"))
                    .respond(
                    HttpResponse.response().withStatusCode(200).
                    withHeaders(
                            new Header("Content-Type", "application/json; charset=utf-8"),
                            new Header("Cache-Control", "public, max-age=86400")
                    ).
                    withBody(getJson())
            );
        when:
            ResponseEntity<CustomerBillWrapper> responseEntity = customerBillService.getCustomerBillResponseEntity()
        then:
            responseEntity.statusCode == HttpStatus.OK
        and:
            CustomerBillWrapper wrapper = responseEntity.getBody()
            wrapper != null
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

    def 'A connection exception is thrown when non OK response is returned'() {
        given:
            mockServerClient.when(
                    HttpRequest.request("/bill.json").
                    withMethod("GET")
            )
            .respond(
                    HttpResponse.notFoundResponse().
                    withBody('Not found')
            )
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
