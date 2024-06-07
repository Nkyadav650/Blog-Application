// isLoggedIn
export const isLoggedIn =()=>{
let data =localStorage.getItem("data");
if(data!=null) {return true;
}else{ return false;}
};

// do login => set data =>  set to local storage
export const doLogin =(data,next) =>{
    localStorage.setItem("data",JSON.stringify(data));
    next()
};

// do logout => remove from local storage
export const doLogout =(next) =>{
    localStorage.removeItem("data");
    next()
};

// for get the token
export const getToken=()=>{
    if(isLoggedIn()){
        return JSON.parse(localStorage.getItem("data")).Token ;
    }else{
        return null;
    }
}

// get current user
export const getCurrentUserDetails =() =>{
    if(isLoggedIn()){
        const userData=JSON.parse(localStorage.getItem("data"))
        return userData.user;
    }else{
        return undefined;
    }
}