<p>
    <h4>
        Call Charges
    </h4>
</p>
<p>
    <table class="table">
        <tr>
            <th># Called</th>
            <th>Duration</th>
            <th>Cost (£)</th>
        </tr>
        <g:each in="${calls}" var="call">
            <tr>
                <td>${call.called}</td>
                <td>${call.duration}</td>
                <td>${call.cost}</td>
            </tr>

        </g:each>
        <tr>
            <td colspan="2">
                <h4>Calls Total Charges</h4>
            </td>
            <td>
                ${callCharges.total}
            </td>
        </tr>
    </table>
</p>
