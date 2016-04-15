package com.sky

class CustomerController {
    def customerBillService

    def index() {
        CustomerBillWrapper customerBill = customerBillService.getCustomerBill()

        [customerBill: customerBill]
    }
}
