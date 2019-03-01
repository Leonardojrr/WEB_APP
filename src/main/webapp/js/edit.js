function $(id){
    return document.getElementById(id);
}
function update() {
    let dataUser = JSON.parse(localStorage.getItem("userInfo"));
    let data={
        name:$('name').value,
        last_name:$('lastName').value,
        username:dataUser.username,
        email:$('email').value,
        birthday:$('date').value,
        sex: $('male').checked,
    },
    params={
        method: "POST", 
        headers: new Headers({'Content-Type': 'application/json'}), 
        body:JSON.stringify(data) 
}
fetch("./../update", params)
.then(resp => resp.json())
.then(data => {
    console.log(data);
  if (data.status==200){
      localStorage.clear();
      localStorage.setItem("userInfo",JSON.stringify(data.data));
      location.href = "./../views/profile.html";
  }else{
      alert(data.message+", status("+data.status+")");
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
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.datepicker');
    var instances = M.Datepicker.init(elems,{ format:"yyyy-mm-dd",yearRange:[1910,2019]});
  });