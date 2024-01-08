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

            <form action="patientpost" method="post">
                <label for="nom">Nom du Patient:</label>
                <input type="text" id="nom" name="nom" required><br>


                <h3>Dents du haut</h3>
                <div class="row">
                    <% for (int i = 1; i <= 16; i++) { %>
                    <div class="col-md-3">
                        <label for="etatDent<%= i %>">Dent <%= i %>:</label>
                        <select name="etatDent<%= i %>">
                            <% for (Etat etat : etatList) { %>
                            <option value="<%= etat.getId() %>"><%= etat.getNomEtat() %></option>
                            <% } %>
                        </select>
                    </div>
                    <% } %>
                </div>

                <h3>Dents du bas</h3>
                <div class="row">
                    <% for (int i = 17; i <= 32; i++) { %>
                    <div class="col-md-3">
                        <label for="etatDent<%= i %>">Dent <%= i %>:</label>
                        <select name="etatDent<%= i %>">
                            <% for (Etat etat : etatList) { %>
                            <option value="<%= etat.getId() %>"><%= etat.getNomEtat() %></option>
                            <% } %>
                        </select>
                    </div>
                    <% } %>
                </div>



                <input type="submit" value="ajouter patient">
            </form>
        </div>
    </div>
</div>
<jsp:include page="template/footer.jsp"/>