<!-- consultationDetails.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.dentiste.model.Consultation" %>
<%@ page import="com.example.dentiste.model.InfoDent" %>
<%@ page import="com.example.dentiste.model.Patient" %>
<jsp:include page="template/header.jsp" />
<%
    Consultation consultation= (Consultation) request.getAttribute("consultation");
    List<InfoDent> listeDents= (List<InfoDent>) request.getAttribute("listeDents");
%>
<div class="col-md-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h2>Détails de la Consultation</h2>
            <h3>Patient: <%= consultation.getPatient().getNom() %></h3>
            <h3>Budget: <%= consultation.getBudget() %></h3>
            <ul>
                <% for (InfoDent infoDent : listeDents) { %>
                <li>
                    Dent <%= infoDent.getDent().getTypeDent().getNumeroDent() %> - État <%= infoDent.getEtat().getNomEtat() %>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</div>

<jsp:include page="template/footer.jsp" />
