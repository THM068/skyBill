<p>
    <h4>
        On Demand Charges
    </h4>
</p>
<p>
    <h4>Rentals</h4>

    <table class="table">
        <tr>
            <th>Title</th>
            <th>Cost (£)</th>
        </tr>
        <g:each in="${rentals}" var="rental">
            <tr>
                <td>${rental.title}</td>
                <td>${rental.cost}</td>
            </tr>
        </g:each>
    </table>
</p>
<p>
    <h4>Buy and Keep</h4>

    <table class="table">
        <g:each in="${buyAndKeep}" var="bk">
            <tr>
                <td>${bk.title}</td>
                <td>${bk.cost}</td>
            </tr>
        </g:each>
        <tr>
            <td>
                <h4>On Demand Total</h4>
            </td>
            <td>
                ${skyStore.total}
            </td>
        </tr>
    </table>
</p>



%{--rentals--}%
%{--buyAndKeep--}%