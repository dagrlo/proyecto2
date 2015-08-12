var datos = null;
var clave = null;
var devuelto = null;
var seguidos=null;
var seguidores=null;

function getData(donde) {
    var auth = 'Basic ' + getAuth();

    $.ajax({
        type: 'GET',
        url: donde,
        dataType: "json",
        contentType: 'application/json',
        headers: {'authorization': auth},
    }).done(function (result) {
        datos = result;
        
        /*console.log("size:" + data.length);
         $("#resultadoView").empty();
         for (var i=0;i<data.length;i++){
         $("#resultadoView").append("["+i+"] -> "+data[i].username+"<br/>");
         } */
    }).fail(function () {
        alert("Error al recibir datos.");
    })
}

function getSeguidos(usr) {
    var auth = 'Basic ' + getAuth();

    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/SparrowEJB2/rest/users/getFolloweds_"+usr,
        dataType: "json",
        contentType: 'application/json',
        headers: {'authorization': auth},
    }).done(function (result) {
        seguidores = result;             
    }).fail(function () {
        alert("Error al recibir datos.");
    })
}

function getSeguidores(usr) {
    var auth = 'Basic ' + getAuth();

    $.ajax({
        type: 'GET',
        url: "http://localhost:8080/SparrowEJB2/rest/users/getFollowers_"+usr,
        dataType: "json",
        contentType: 'application/json',
        headers: {'authorization': auth},
    }).done(function (result) {
        seguidos = result;             
    }).fail(function () {
        alert("Error al recibir datos.");
    })
}

function setData(url, data, funcion) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'            
        },
        'type': 'POST',
        'url': url,
        'data': data,
        'dataType': 'json',
        'complete': funcion(),
        'error': function () {
            alert("Error enviando datos");
        }
    })
}

function setDataAuth(url, data, funcion) {
    var auth = 'Basic ' + getAuth();
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'authorization': auth
        },
        'type': 'POST',
        'url': url,
        'data': data,
        'dataType': 'json',
        'complete': funcion(),
        'error': function () {
            alert("Error enviando datos");
        }
    })
}

function setGetData(url, data, funcion) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': url,
        'data': data,
        'dataType': 'json',
        contentType: 'application/json',
        'success': function (result) {
            verPass(result);
        }
    }
    )
}


function dameTemas() {
    getData("http://localhost:8080/SparrowEJB2/rest/chips/topics");
}

function buscaUsuario(apellidos){
    getData("http://localhost:8080/SparrowEJB2/rest/users/find_"+apellidos);
}

function actualizaUsuario(usuario){
    setDataAuth("http://localhost:8080/SparrowEJB2/rest/users/editUser",JSON.stringify(usuario),function(){
        ventanaVolver();
    })
}

function cargaUsuario(username){
    getData("http://localhost:8080/SparrowEJB2/rest/users/getUsr_"+username);
}

function nuevoUsuario(usr) {
    setData("http://localhost:8080/SparrowEJB2/rest/users/addUser", usr, function () {
        ventanaVolver()
    });
}

function respondeChip(texto,autor,id){
    chip=new Chip(texto,autor,id);
    setDataAuth("http://localhost:8080/SparrowEJB2/rest/chips/response",JSON.stringify(chip),function(){
        ventanaVolver();
    })
}

function nuevoTema(tag,text,user){
    tema=new Tema(0,tag,text,user);
    setDataAuth("http://localhost:8080/SparrowEJB2/rest/chips/addtopic",JSON.stringify(tema),function(){
        ventanaVolver();
    })
}

function seguir(miUsuario,idSeguido){
    follow=new Follows(miUsuario,idSeguido);
    setDataAuth("http://localhost:8080/SparrowEJB2/rest/users/followUser",JSON.stringify(follow),function(){
        ventanaVolver();
    })
}

function noSeguir(miUsuario,idSeguido){
    follow=new Follows(miUsuario,idSeguido);
    setDataAuth("http://localhost:8080/SparrowEJB2/rest/users/noFollow",JSON.stringify(follow),function(){
        ventanaVolver();
    })
}

function generaClave(clave) {


    donde = "http://localhost:8080/SparrowEJB2/rest/users/genKey_" + clave;
    $.ajax({
        type: 'GET',
        url: donde,
    }).done(function (result) {
        var data = result;
        password = data;

        console.log(password);
        usuario = new Usuario($("#apellidosField").val(), $("#emailField").val(), $("#idiomaField").val()
                , $("#nombreField").val(), password, $("#passField").val(), $("#sexoField").val()
                , $("#usernameField").val());
        usuarioJSON = JSON.stringify(usuario);
        console.log(usuarioJSON);
        nuevoUsuario(usuarioJSON);
    }).fail(function () {
        alert("Error al recibir datos.");
    })

}

function ventanaVolver() {
    $("#tapa").show(100);
    $("#volver").show(300);
}

function resetPass(nombre, email) {
    pass = new Password(email, nombre);
    setGetData("http://localhost:8080/SparrowEJB2/rest/users/resetPassword", JSON.stringify(pass), verPass());
}

function verPass(res) {
    if (res !== undefined) {
        console.log("Pass: " + res.email);
        $("#tapa").show(100);
        $("#volver").show(300).html("<b>Password: </b>" + res.email + "<br/><br/><a href='index.html'>Volver</a>");
    }
}

function dameParam(param){
    var sPageURL = window.location.search.substring(1);

    var sURLVariables = sPageURL.split('&');

    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == param)
        {
            console.log(sParameterName[1]);
            return sParameterName[1];
        }
    }
}

function quitaEspacio(cadena){
   return cadena.split(" ").join("%20");
           //.replace(" ","%20"); ESTE SOLO CAMBIA LA PRIMERA COINCIDENCIA!!
}
