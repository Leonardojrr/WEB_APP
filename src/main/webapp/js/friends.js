function $(id){
    return document.getElementById(id);
}
function c(clase){
    return document.getElementsByClassName(clase)
}
window.onload = ()=>{
    let user = $("username");
    let dataUser = JSON.parse(localStorage.getItem("userInfo"));
    user.innerHTML = '<i class="material-icons left">account_circle</i>'+ dataUser.username;
    console.log(dataUser);
    params={
        method: "GET", 
        headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}), 
}
    fetch("./../friend", params)
    .then(resp => resp.json())
    .then(data => {
        console.log(data.data);
      if (data.data.length>0){
          let friends = $('friends');
          friends.innerHTML = data.data.length;
        data.data.forEach(element => {
    
        $("listFriends").innerHTML +=`
        <div class="red" style="border: 1px solid #212121 ;border-radius: 10px;height:auto ;padding: 1%;margin-bottom: 2%;margin-top: 2% ">
        <div style="display:flex">
            <div style="width: 12%;height: 30%;">
                <img src="avatar.png" alt="no cargo" class="responsive-img circle" width="90%" height="80%">
                <p>${element.birthday}</p>                      
            </div>
            <div >
                <div style="display:flex">
                <h5 class="user">${element.name} ${element.lastName}</h5><span id="user" style="padding-left:1%;padding-top: 11%;">${element.username}</span>
                </div>
                <p><span>BD:</span> 11/12/1997</p>
            </div>
        </div>
        <div style="display:flex">
        <a id="profile" class="btn grey darken-4" style="width: 50%;">Ver Perfil</a>
        <a id="delete" class="btn grey darken-4" style="width: 50%;">Eliminar</a>
        </div>
    </div>`
    });
    $('profile').addEventListener('click', profile);
    $('delete').addEventListener('click', borrar);
      }else{
          console.log(1)
        alert(data.message+", status("+data.status+")"+"No se encontro coincidencia");
      }
    });
}
function out() {
    params={
        method: "POST", 
        headers: new Headers({'Content-Type': 'application/json'}), 
}
fetch("./../LogOutServlet", params)
.then(resp => resp.json())
.then(data => {
    console.log(data);
  if (data.status==200){
      alert(data.message+", status("+data.status+")");
      location.href = "./../";
  }else{
      alert(data.message+", status("+data.status+")");
  }
});
}
function search(){
    let user = $('searchFriends').value;
    params={
        method: "GET", 
        headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}), 
}
    fetch("./../search?op=2&param="+user, params)
.then(resp => resp.json())
.then(data => {
    console.log(data.data);
  if (data.data.length>0){
    data.data.forEach(element => {

    $("listFriends").innerHTML +=`
    <div class="red" style="border: 1px solid #212121 ;border-radius: 10px;height:auto ;padding: 1%;margin-bottom: 2%;margin-top: 2% ">
    <div style="display:flex">
        <div style="width: 12%;height: 30%;">
            <img src="avatar.png" alt="no cargo" class="responsive-img circle" width="90%" height="80%">
            <p>${element.birthday}</p>                      
        </div>
        <div >
            <div style="display:flex">
            <h5 class="user">${element.name} ${element.lastName}</h5><span id="user" style="padding-left:1%;padding-top: 11%;">${element.username}</span>
            </div>
            <p><span>BD:</span> 11/12/1997</p>
        </div>
    </div>
    <div style="display:flex">
    <a id="profile" class="btn grey darken-4" style="width: 50%;">Ver Perfil</a>
    <a id="delete" class="btn grey darken-4" style="width: 50%;">Eliminar</a>
    </div>
</div>`
});
$('profile').addEventListener('click', profile);
$('delete').addEventListener('click', borrar);

  }else{
      console.log(1)
    alert(data.message+", status("+data.status+")"+"No se encontro coincidencia");
  }
});
}
function clear(){
    $('listFriends').innerHTML ='';
}
function profile(){
    let user = $('user').innerHTML;
    params={
        method: "GET", 
        headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}), 
}
    fetch("./../search?op=3&user="+user, params)
.then(resp => resp.json())
.then(data => {
    console.log(data);
    if (data.status==200){
        localStorage.setItem("user",JSON.stringify(data.data));
        location.href = "./../views/user.html";
    }else{
        alert(data.message+", status("+data.status+")");
    }
});

}
function borrar(){
    let user = $('user').innerHTML;
    params={
        method: "DELETE", 
        headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}), 
}
    fetch("./../friend?user2="+user, params)
.then(resp => resp.json())
.then(data => {
    console.log(data);
    if (data.status==200){
        alert(data.message+", status("+data.status+")");
        }else{
        alert(data.message+", status("+data.status+")");
    }
});

}
$('searchFriends').addEventListener('click', clear);
$('searchFriends').addEventListener('change',search);