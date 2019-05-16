<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.customer != null 
              && sessionScope.carport != null
              && sessionScope.roof != null
              && sessionScope.order != null}">
      <div class="jumbotron text-center">
          <h1 id="headline">
              Ændre ordre
          </h1>
      </div>
      <div id="changeOrderForm">
          <div class="container">
              <div>
                  <table id="orderInfoTable" style="width:100%">
                      <tr>
                          <td>Ordre ID</td>
                          <td>${sessionScope.order.order_id}</td> 
                      </tr>
                      <tr>
                          <td>Kunde Adresse</td>
                          <td>${sessionScope.order.customer_address}</td> 
                      </tr>
                      <tr>
                          <td>Tag type</td>
                          <td>${roof.roofTypeId}: ${roof.type}, ${roof.color}</td> 
                      </tr>
                      <tr>
                          <td>Cartport længde</td>
                          <td>${sessionScope.carport.length}</td> 
                      </tr>
                      <tr>
                          <td>Cartport bredde</td>
                          <td>${sessionScope.carport.width}</td> 
                      </tr>
                      <tr>
                          <td>Cartport højde</td>
                          <td>${sessionScope.carport.height}</td> 
                      </tr>
                      <tr>
                          <td>Ordre modtaget</td>
                          <td>${sessionScope.order.order_receive_date}</td> 
                      </tr>
                      <tr>
                          <td>Ordre afsendt</td>
                          <td>${sessionScope.order.order_send_date}</td> 
                      </tr>
                      <tr>
                          <td>Ordre status</td>
                          <td>${sessionScope.order.order_status}</td> 
                      </tr>
                      <tr>
                          <td>Kunde adresse</td>
                          <td>${sessionScope.order.customer_address}</td> 
                      </tr>
                      <tr>
                          <td>Total pris</td>
                          <td><input type="text" id="totalPrice" name="totalPrice" value="${sessionScope.order.total_price}"></td> 
                      </tr>
                  </table>
              </div>

              <table id="orderInfoTable" style="width:100%">
                  <!--<div class="btn-group" role="group" aria-label="button group changing order">-->
                  <tr>
                      <td><button id="doneChangeOrder" onclick="changeOrderForm()" class="btn btn-primary">Fortsæt</button></td>
                      <td><button id="regretOrderForm" onclick="regretOrderForm()" class="btn btn-danger">Fortryd</button></td>
                      <td><button id="removeOrderForm" onclick="removeOrderForm(${sessionScope.order.order_id})" class="btn btn-warning">Fjern</button></td>
                      <td><button id="showDrawing" onclick="showDrawing()" class="btn btn-success">Se tegning</button></td>
                      <td><button id="downloadPDF" onclick="downloadPDF()" class="btn btn-info">Download stykliste</button></td>
                      <td><button id="orderSent" onclick="orderSent(${sessionScope.order.order_id})" class="btn btn-primary">Ordre sendt</button></td>
                  </tr>
                  <!--</div>-->
              </table>
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
