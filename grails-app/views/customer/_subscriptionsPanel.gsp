<p>
    <h4>
        Subscription Charges
    </h4>
    </p>

    <p>
    <table class="table">
        <tr>
            <th>Type</th>
            <th>Name</th>
            <th>Cost (£)</th>
        </tr>
        <g:each in="${subscriptions}" var="subscription">
            <tr>
                <td>${subscription.type}</td>
                <td>${subscription.name}</td>
                <td>${subscription.cost}</td>
            </tr>
        </g:each>
        <tr>
            <td colspan="2">
                <h4>Subscription Total Charges</h4>
            </td>
            <td>
                ${package.total}
            </td>
        </tr>
    </table>
</p>
