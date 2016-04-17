package skybill.com.sky

import com.sky.ConnectException
import com.sky.CustomerBillWrapper

class CustomerController {
    def customerBillService

    def index() {
        CustomerBillWrapper customerBill = customerBillService.getCustomerBill()
        render(view: 'index', model: [customerBill: customerBill])
    }

    def test() {
        render 'vewi ....'
    }

    def connectException(final ConnectException exception) {
         render view: 'error', model: [errorCode: exception.message ]
    }
}
