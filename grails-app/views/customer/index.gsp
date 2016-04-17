<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Customer Bill</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <g:render template="/customer/billHeader" model="[customerBill: customerBill]"/>

    <div class="row">
        <div class="col-md-12">
            <h2>Your bill breakdown</h2>
        </div>
    </div>

    <g:render template="/customer/billbreakdown" model="[customerBill: customerBill]"/>

</body>
</html>
