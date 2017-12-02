<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Passagens.User"%>
<!DOCTYPE html>
<%
    String loginErrorMessage = null;
    if(request.getParameter("do-login")!= null){
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");
        try{
            User u = User.getUser(login, pass);
            if(u==null){
                loginErrorMessage = "Login e/ou senha não encontrados";
            }else{
                session.setAttribute("me.id", u.getId());
                session.setAttribute("me.name", u.getName());
                session.setAttribute("me.login", u.getLogin());
                session.setAttribute("me.passwordHash", u.getPasswordHash());
                response.sendRedirect(request.getContextPath()+"/stays.jsp");
            }
        }catch(Exception ex){
            loginErrorMessage = ex.getMessage();
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME - Projeto 05</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <h2>Login</h2>
        <fieldset>
            <legend>Formulário de Login</legend>
            <%if(loginErrorMessage!=null){%>
                <div style="color: red;"><%=loginErrorMessage%></div>
            <%}%>
            <form method="post">
                Login:<br/>
                <input type="text" name="login"/><br/>
                Senha:<br/>
                <input type="password" name="pass"/><br/><br/>
                <input type="submit" name="do-login" value="Entrar"/>
            </form>
        </fieldset>
        <hr>
     
        <%@include file="WEB-INF/jspf/footer.jspf" %>
    </body>
</html>