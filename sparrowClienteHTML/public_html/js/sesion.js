function codifica(cadena){
    return window.btoa(unescape(encodeURIComponent(cadena)));
}

function creaSesion(usr,pass){
    var convertido=codifica(usr+":"+pass);
    //cookie con el nombre de usuario
    //no usar secure=true. si se usa las cookies solo iran por https y no se reciben
    Cookies.set("sparrowUsr",usr,{
        expires: 1,
        path: '/'
        
    });
    
    //cookie con los datos de clave 
    
    Cookies.set("sparrowData",convertido,{
        expires: 1,
        path: '/'
        
    });
}

function dameUsr(){
    return Cookies.get('sparrowUsr');
}

function getAuth(){
    return Cookies.get('sparrowData');
}

