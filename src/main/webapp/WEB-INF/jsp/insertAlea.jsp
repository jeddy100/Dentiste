<%@ page import="com.example.dentiste.model.Etat" %>
<%@ page import="java.util.List" %>
<jsp:include page="template/header.jsp" />

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Etat>etatList= (List<Etat>) request.getAttribute("listEtats");

%>

<div class="col-md-12  grid-margin stretch-card ">
    <div class="card">
        <div class="card-body">
            <h2>Ajouter Patient</h2>

            <form action="patientaleapost" method="post">
                <label for="nom">Nom du Patient:</label>
                <input type="text" id="nom" name="nom" required><br>

                <p>Dent:</p>
                <input name="dents" type="text">

                <p>Note:</p>
                <input name="etats" type="text">




                <input type="submit" value="ajouter patient">
            </form>
        </div>
    </div>
</div>
<jsp:include page="template/footer.jsp"/>