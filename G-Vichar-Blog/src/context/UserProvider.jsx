import React from 'react'
import userContext from './userContext'
import { useState,useEffect } from 'react';

function UserProvider ({children}){

  const userData = localStorage.getItem('data');

    const[user,setUser]=useState(null);
    useEffect(() => {
      
      if (userData) {
        setUser(JSON.parse(userData));
      }
    }, []);
  return (
    
    <userContext.Provider value={user}>
            {children}
    </userContext.Provider>
  )
}

export default UserProvider