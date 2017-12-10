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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
        <link rel="stylesheet" href="css/css.css">
        <title>HOME - Projeto 05</title>
    </head>
    <body>
        <%@include file="WEB-INF/jspf/header.jspf" %>
        <main class="container" style="margin-top: 10%;">                   
                <%if(loginErrorMessage!=null){%>
                    <div style="color: red;"><%=loginErrorMessage%></div>
                <%}%>
                <h2 class="text-center"> Bem-vindo(a)!</h2><br>
                
                <form method="post" class="col-md-6">
                    <h3>Faça o seu login: </h3>
                    <label>Login:</label><br/>
                    <input type="email" name="login" class="form-control"><br/>
                    <label>Senha:</label><br/>
                    <input type="password" name="pass" class="form-control"/><br/>
                    <input type="submit" name="do-login" value="ENTRAR" class="btn align-middle btn-outline-light"style=" margin-left: 15%;"/> ou<a href="cadastro.jsp"style="font-size: 0.7em;"><button class="btn btn-login">Cadastre-se</button></a>
                </form>
        </main>
        <%@include file="WEB-INF/jspf/footer.jspf" %>
        
    </body>
</html>