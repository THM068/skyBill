<g:set var="statement" value="${customerBill.statement}" />
<g:set var="period" value="${statement.period}" />
<div class="row">
    <div class="col-md-7"></div>
    <div class="col-md-5">
        <table class="table">
            <tr>
                <td>
                    <h4>Bill Date:</h4>
                </td>
                <td>
                    <sky:dayMonthYearDate dateString="${statement.generated}" />
                </td>
            </tr>
            <tr>
                <td>
                    <h4>Bill Period:</h4>
                </td>
                <td>
                    <sky:dayMonthYearDate dateString="${period.from}" /> - <sky:dayMonthYearDate dateString="${period.to}" />
                </td>
            </tr>
            <tr>
                <td>
                    <h4>
                        Bill Due:
                    </h4>
                </td>
                <td>
                    <sky:dayMonthYearDate dateString="${statement.due}" />
                </td>
            </tr>
        </table>
    </div>
</div>
