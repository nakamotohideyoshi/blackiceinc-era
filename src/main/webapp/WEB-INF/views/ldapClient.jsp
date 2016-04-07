<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ldap Client</title>

    <style>
        input[type="text"] {
            width: 100%;
        }
    </style>
</head>
<body>
<a href="/ldapClient?domain=north">North</a>
<a href="/ldapClient?domain=south">South</a>
<form:form method="POST" action="/ldapClient" modelAttribute="ldapAuth">
    <table >
        <col width="10%">
        <col width="90%">
        <tr>
            <td><form:label path="ldapConfig.ldapServers[0]">Ldap Server</form:label></td>
            <td><form:input path="ldapConfig.ldapServers[0]"/></td>
        </tr>
        <tr>
            <td><form:label path="ldapConfig.ldapServerPort">Ldap Server Port</form:label></td>
            <td><form:input path="ldapConfig.ldapServerPort"/></td>
        </tr>
        <tr>
            <td><form:label path="ldapConfig.baseDn">Base DN</form:label></td>
            <td><form:input path="ldapConfig.baseDn"/></td>
        </tr>
        <tr>
            <td><form:label path="ldapConfig.bindDn">Bind DN</form:label></td>
            <td><form:input path="ldapConfig.bindDn"/></td>
        </tr>
        <tr>
            <td><form:label path="ldapConfig.bindPassword">Bind Password</form:label></td>
            <td><form:input path="ldapConfig.bindPassword"/></td>
        </tr>
        <tr>
            <td><form:label path="ldapConfig.userFilter">User Filter</form:label></td>
            <td><form:input path="ldapConfig.userFilter"/></td>
        </tr>
        <tr>
            <td><form:label path="ldapConfig.groupFilter">Group Filter</form:label></td>
            <td><form:input path="ldapConfig.groupFilter"/></td>
        </tr>

        <tr style="height: 20px;">
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td><form:label path="username">Username</form:label></td>
            <td><form:input path="username"/></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
        <tr style="height: 20px;">
            <td></td>
            <td></td>
        </tr>

    </table>
</form:form>

<c:if test="${result eq true}">
    <div style="height: 20px; color: green">
        SUCCESS
    </div>
</c:if>
<c:if test="${result eq false}">
    <div style="height: 20px; color: red">
        FAILURE
    </div>
</c:if>

</body>
</html>
