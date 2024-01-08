<!-- consultationList.jsp -->
<%@ page import="java.util.List" %>
<%@ page import="com.example.dentiste.model.Consultation" %>
<%@ page import="com.example.dentiste.repository.InfoDentRepository" %>
<%@ page import="com.example.dentiste.model.InfoDent" %>
<%@ page import="com.example.dentiste.model.Patient" %>
<jsp:include page="template/header.jsp" />
<%
    List<Consultation>consultations= (List<Consultation>) request.getAttribute("listConsultations");

%>
<div class="col-md-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h2>Liste des Consultations</h2>
            <ul>

                <% for (Consultation consultation : consultations) { %>
                <li>
                    <a href="/consultationDetails?consultationId=<%= consultation.getId() %>">
                        Consultation du <%= consultation.getPatient().getNom() %>
                    </a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</div>

<jsp:include page="template/footer.jsp" />
