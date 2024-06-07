import React from "react";
import Base from '../components/Base';
import userContext from "../context/userContext";
const Services=()=>{
    return(
        <>
        <userContext.Consumer>
        {(user)=>(
       <Base>
       <h1>this is Services pages</h1>
       <p>you can use this services</p>
       <h1>Welcome to user :{user.name}</h1>
              </Base>
        )}
       </userContext.Consumer>
        </>
    )
}
export default Services;