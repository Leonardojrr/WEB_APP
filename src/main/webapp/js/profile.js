function $(id){
    return document.getElementById(id);
}
window.onload = ()=>{
    let user = $("username");
    let username = $('userN');
    let name = $('name');
    let lastname = $('lastName');
    let birthday = $('birthday');
    let gender = $('gender');
    let email = $('email');
    let dataUser = JSON.parse(localStorage.getItem("userInfo"));
    user.innerHTML = '<i class="material-icons left">account_circle</i>'+ dataUser.username;
    username.innerHTML = dataUser.username;
    name.innerHTML = dataUser.name;
    lastname.innerHTML = dataUser.last_name;
    birthday.innerHTML = dataUser.birthday;
    if(dataUser.sex == true){
    gender.innerHTML = "Masculino"
    }else{
        gender.innerHTML = "Femenino"
    }
    email.innerHTML = dataUser.email;

    console.log(dataUser);
}
let info = $('info').style.height = "100vw" - 64+"px";
let info1 = $('info1').style.height = "100vw" - 64+"px";

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