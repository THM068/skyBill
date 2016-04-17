<g:set var="package" value="${customerBill.packageWrapper}" />
<g:set var="subscriptions" value="${package.subscriptions}" />
<g:set var="callCharges" value="${customerBill.callCharges}" />
<g:set var="calls" value="${callCharges.calls}" />
<g:set var="skyStore" value="${customerBill.skyStore}" />
<g:set var="rentals" value="${skyStore.rentals}" />
<g:set var="buyAndKeep" value="${skyStore.buyAndKeep}" />
<div class="row">
    <div class="col-md-7">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3>
                    Usage Charges
                </h3>
            </div>
            <div class="panel-body">
                <g:render template="/customer/subscriptionsPanel" />
                <g:render template="/customer/callChargePanel" />
                <g:render template="/customer/ondemandPanel" />
            </div>
            <div class="panel-footer">
                <table class="table ">
                    <tr >
                        <td class="no-border">
                            <h3>Usage Charge Total</h3>
                        </td>
                        <td class="no-border">
                            <h3>${customerBill.total}</h3>
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="col-md-5"></div>
    </div>
</div>