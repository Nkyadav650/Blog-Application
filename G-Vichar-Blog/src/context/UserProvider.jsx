import React from 'react'
import userContext from './userContext'
import { useState } from 'react';

function UserProvider ({children}){

    const[user,setUser]=useState({name:'Rawan'});

  return (
    
    <userContext.Provider value={user}>
            {children}
    </userContext.Provider>
  )
}

export default UserProvider