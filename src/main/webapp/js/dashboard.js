function $(id){
    return document.getElementById(id);
}
window.onload = ()=>{
    let user = $("username");
    user.innerHTML='<i class="material-icons left">account_circle</i>'+JSON.parse(localStorage.getItem("userInfo"));
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