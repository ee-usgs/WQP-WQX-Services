<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
  <head><title>WQP Gateway - ${filter} Cache Status</title></head>
  <body>
    <h1>${filter} - Cache Status</h1>
    <h3>Total Filters Cached: ${filters.size()}</h3>
    <table style="border-collapse:collapse;">
    	<tr>
    		<td style="border: 2px solid black;font-size: 20px;font-weight: bold;text-align: center;padding-left: 5px;padding-right: 5px;">
    			Filter Name
    		</td>
    		<td style="border: 2px solid black;font-size: 20px;font-weight: bold;text-align: center;padding-left: 5px;padding-right: 5px;">
    			Filter to Column Mapping
    		</td>
    		<td style="border: 2px solid black;font-size: 20px;font-weight: bold;text-align: center;padding-left: 5px;padding-right: 5px;">
    			Date Cached
    		</td>
    		<td style="border: 2px solid black;font-size: 20px;font-weight: bold;text-align: center;padding-left: 5px;padding-right: 5px;">
    			Total Count
    		</td>
    		<td style="border: 2px solid black;font-size: 20px;font-weight: bold;text-align: center;padding-left: 5px;padding-right: 5px;">
    			Current Access Count since last Refresh
    		</td>
                <td style="border: 2px solid black;font-size: 20px;font-weight: bold;text-align: center;padding-left: 5px;padding-right: 5px;">
                        Force Refresh
                </td>
    	</tr>
    	<c:forEach var="filterItem" items="${filters}" varStatus="status">
    		<tr>
    			<td style="border: 1px solid black;padding-left: 5px;padding-right: 5px;">
    				${filterItem.name}
	    		</td>
	    		<td style="border: 1px solid black;padding-left: 5px;padding-right: 5px;">
    				${filterItem.mapping}
	    		</td>
	    		<td style="border: 1px solid black;padding-left: 5px;padding-right: 5px;">
	    			<fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${filterItem.dateCached}" />
	    		</td>
	    		<td style="border: 1px solid black;padding-left: 5px;padding-right: 5px;">
	    			${filterItem.filterCount}
	    		</td>
	    		<td style="border: 1px solid black;padding-left: 5px;padding-right: 5px;">
	    			${filterItem.accessCount}
	    		</td>
                        <td style="border: 1px solid black;padding-left: 5px;padding-right: 5px;">
                                BUTTON
                        </td>
    		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
