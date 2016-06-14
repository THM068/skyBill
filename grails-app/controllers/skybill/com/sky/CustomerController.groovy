package skybill.com.sky

import com.fasterxml.jackson.databind.ObjectMapper
import com.sky.ConnectException
import com.sky.CustomerBillWrapper
import org.springframework.transaction.annotation.Transactional

@Transactional
class CustomerController {
    def customerBillService

    def index() {
        CustomerBillWrapper customerBill = customerBillService.getCustomerBill()
        String result = toJson(customerBill)
        //[customerBill: customerBill]
        render(contentType: 'application/json', text: result )
    }

    def connectException(final ConnectException exception) {
         render view: 'error', model: [errorCode: exception.message ]
    }

    private String toJson(objectTobeSerialized) {
        String result = ""
        ObjectMapper mapper = new ObjectMapper()

        if(objectTobeSerialized) {
            result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objectTobeSerialized)
        }

        return result
    }
}
