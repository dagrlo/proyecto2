function Usuario(apellidos,eMail,idioma,nombre,password,passwordString,sexo,username){
    this.apellidos=apellidos;
    this.EMail=eMail;
    this.idioma=idioma;
    this.nombre=nombre;
    this.password=password;
    this.passwordString=passwordString;
    this.sexo=sexo;
    this.username=username;
}

function Password(email,nombre){
    this.email=email;
    this.nombre=nombre;
}

function Chip(texto,autor,id){
    this.texto=texto;
    this.autor=autor;
    this.id=id;
}

function Tema(id,tag,text,user){
    this.id=id;
    this.tag=tag;
    this.text=text;
    this.user=user;
}

function Follows(miUsuario,idSeguido){
    this.miUsuario=miUsuario;
    this.idSeguido=idSeguido;
}