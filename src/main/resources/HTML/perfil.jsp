<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="pe.edu.utp.Model.Cliente" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Mi Perfil</title>
    <link rel="stylesheet" href="../css/miPerfil.css">
</head>
<body>
<h1>Mi Perfil</h1>
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
%>
<table>
    <tr>
        <th>Nombre</th>
        <td><%= cliente.getNombre() %></td>
    </tr>
    <tr>
        <th>Apellido Paterno</th>
        <td><%= cliente.getApellidoPaterno() %></td>
    </tr>
    <tr>
        <th>Apellido Materno</th>
        <td><%= cliente.getApellidoMaterno() %></td>
    </tr>
    <tr>
        <th>Nro Identidad</th>
        <td><%= cliente.getNroIdentidad() %></td>
    </tr>
    <tr>
        <th>Teléfono</th>
        <td><%= cliente.getTelefono() %></td>
    </tr>
    <tr>
        <th>Email</th>
        <td><%= cliente.getEmail() %></td>
    </tr>
    <tr>
        <th>Fecha de Nacimiento</th>
        <td><%= cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento().toString() : "" %></td>
    </tr>
</table>
</body>
</html>
