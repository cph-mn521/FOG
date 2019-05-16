<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.customer != null 
              && sessionScope.carport != null
              && sessionScope.roof != null
              && sessionScope.order != null}">
      <h1>
          Ændre ordre
      </h1>
      <div id="changeOrderForm">
          <div class="container">
              Ordre ID: ${sessionScope.order.order_id}<br/>
              Kunde Adresse: ${sessionScope.order.customer_address}<br/>

              Tag type: ${roof.roofTypeId}: ${roof.type}, ${roof.color}<br/>

              Cartport længde: ${sessionScope.carport.length}<br/>

              Cartport bredde: ${sessionScope.carport.width}<br/>

              Cartport højde: ${sessionScope.carport.height}<br/>

              Ordre modtaget: ${sessionScope.order.order_receive_date}<br/>

              Ordre afsendt: ${sessionScope.order.order_send_date}<br/>

              Ordre status: ${sessionScope.order.order_status}<br/>

              Kunde adresse: ${sessionScope.order.customer_address}<br/>

              Total_price<br/>
              <input type="text" id="totalPrice" name="totalPrice" value="${sessionScope.order.total_price}"><br/>

              <div class="btn-group" role="group" aria-label="button group changing order">
                  <div>
                      <button id="doneChangeOrder" onclick="changeOrderForm()" class="btn btn-primary">Fortsæt</button>
                  </div>
                  <div>
                      <button id="regretOrderForm" onclick="regretOrderForm()" class="btn btn-danger">Fortryd</button>
                  </div>
                  <div>
                      <button id="removeOrderForm" onclick="removeOrderForm(${sessionScope.order.order_id})" class="btn btn-warning">Fjern</button>
                  </div>
                  <div>
                      <button id="showDrawing" onclick="showDrawing()" class="btn btn-success">Se tegning</button>
                  </div>
                  <div>
                      <button id="downloadPDF" onclick="downloadPDF()" class="btn btn-info">Download stykliste</button>
                  </div>
                  <div>
                      <button id="orderSent" onclick="orderSent(${sessionScope.order.order_id})" class="btn btn-primary">Ordre sendt</button>
                  </div>
              </div>
          </div>
      </c:if>
      <c:if test="${sessionScope.bomMap != null}">
          <h1>
              Stykliste:
          </h1>
          <div id="componentsTable">
              <div class="container">
                  <p> OrderID:  <span class="orderIDText"><c:out value="${sessionScope.orderID}" /></span> </p>
                  <table id="componentListTable" class="table table-hover table-condensed table-striped text-center">
                      <tr class="table">
                          <th>Komponent ID</th>
                          <th>Beskrivelse</th>
                          <th>Hjælpetekst</th>
                          <th>Bredde</th>
                          <th>Højde</th>
                          <th>Stykpris</th>
                          <th>Antal</th>  
                      </tr>
                      <c:forEach items="${sessionScope.bomMap}" var="components"> 
                          <tr class="table-bordered">
                              <td>${components.key.componentId}</td>
                              <td>${components.key.description}</td>
                              <td>${components.key.helpText}</td>
                              <td>${components.key.width}</td>
                              <td>${components.key.height}</td>
                              <td>${components.key.price}</td>
                              <td>${components.value}</td>
                          </c:forEach>
                  </table>
              </div>
          </div>
      </c:if>    
</div>
