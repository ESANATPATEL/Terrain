<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<script>
function callserver()
{
var e = document.getElementById("selectbox");
var valu = e.options[e.selectedIndex].value; 
document.getElementById("hiddentxt").value=valu;
document.paymentform.submit();
}

</script>
<style>
	table,th,td
	{
		border:1px solid black;
	}
	table
	{
		border-collapse:collapse;
		width:0%;
	}
	td
	{
		height:40px;
	}
	tr
	{
		background-color:green;
		color:white;
	}
	th
	{
		background-color:#000099;
		color:black;
	}
	tbody tr:nth-child(even)
	{
	  background: #b3b3ff;
	}
	tbody tr:nth-child(odd)
	{
	  background: #b3b3ff;
	}
	
	.productName
	{
	  color: black;
  	font-size: 24px;
  	font-weight: 300;
  	max-width:350px;
	}
		.marketingCop
	{
	  color: black;
  	font-size: 24px;
  	font-weight: 300;
  	max-width:400px;
	}
		.productType
	{
	  color: black;
  	font-size: 24px;
  	font-weight: 300;
  	max-width:155px;
	}
		.purchaseRate
	{
	  color: black;
  	font-size: 24px;
  	font-weight: 300;
  	max-width:155px;
	}
	
	.testdiv
	{
	font-size: 25px;
	font-weight: normal;
	line-height: 10px;
	margin: 0;
	padding: 10px 0;
	text-align: center;
	}
	.select1 {
  background: #f2f2f2;
  color: solid black;
  line-height: normal;
  font-family: inherit;
  font-size: inherit;
  line-height: inherit;
}
</style>


</head>
<body bgcolor="#f2f2f2">
<form autocomplete="on" name = "paymentform" action="/TerrainArtId/Fetch">
<div class="testdiv">Sanat, Here are your 10 results for  <select id = "selectbox" onchange="callserver()" class="select1">
  <option  value="CNSMR">Consumer Card</option>
  <option value="CMRCL">Business Card</option>
</select></div>
<br>
	<table align="center">
		<tr>
		<th>Product Name</th>
		<th>Product Type</th>
		<th>Details</th>
		<th>Purchase Rate</th>
		<th>Check Prequalification<br>Apply Now</th>
		<th></th>
		</tr>
		<c:forEach items="${products}" var="product">
        <tr>
            <td class="productName" >${product.productDisplayName}<br><img height="200" width="345" src="${product.imageUrl}" ></img></td>
            <td class="productType"><c:out value="${product.productType}" /></td>
              <td class="marketingCop">${product.marketingCop}  <br><a href="">more..</a></td>
            <td class="purchaseRate">${product.aprDesc} <br>Variable</td>
            <td><a href="prequalify.html">Check if you're Pre-qualified</a><br><br><br><a href="https://applynow.capitalone.com/?productId=2017"> Apply Now </a></td>
        </tr>
    </c:forEach>

	</table>
	    <input type="hidden" id="hiddentxt" name="hiddentxt" >
	</form>
</body>
</html>