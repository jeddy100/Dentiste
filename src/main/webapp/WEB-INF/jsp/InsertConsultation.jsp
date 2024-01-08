<%@ page import="com.example.dentiste.model.Etat" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.dentiste.model.Patient" %>
<jsp:include page="template/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Patient>patientList= (List<Patient>) request.getAttribute("listPatients");

%>

<div class="col-md-12  grid-margin stretch-card ">
    <div class="card">
        <div class="card-body">
            <h2>Consultation</h2>
            <form action="/consultationpost" method="post" >

                <p>selectionner un patient</p>
                <select name="patient">
                <% for (int i = 0; i < patientList.size(); i++) { %>
                <option  value="<%=patientList.get(i).getId() %>"><%= patientList.get(i).getNom()%></option>
                <% } %>
                </select>

                <input type="number" name="budget" placeholder="budget">

                <p>Choisir le type de consultation</p>
                <select name="type_consultation">
                    <option value="1">Soin</option>
                    <option value="2">Beauté</option>
                </select>

                <input type="submit" value="valider">
            </form>




        </div>
    </div>
</div>
<jsp:include page="template/footer.jsp"/>