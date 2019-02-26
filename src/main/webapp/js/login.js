function $(id){
    return document.getElementById(id);
}
function login() {
    let data={
        username:$('user').value,
        password:$('password').value
    },
    params={
        method: "POST", 
        headers: new Headers({'Content-Type': 'application/json'}), 
        body:JSON.stringify(data) 
}
fetch("http://localhost:8080/SocialForge/session", params)
.then(resp => resp.json())
.then(data => {
    console.log(`${data.status} ${data.message}`);
  if (data.status==200){
      location.href = "http://localhost:8080/SocialForge/views/dashboard.html";
  }else{
      alert("Error al iniciar sesion, status:"+data.status);
  }
});
}