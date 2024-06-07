import React from 'react'
import Base from '../components/Base';
import userContext from '../context/userContext';
import { useContext } from 'react';
const ProfileInfo=() => {

  const user=useContext(userContext)
  return (
    <>
    
        <Base>
        <div>
    <h1>ProfileInfo</h1>
    <p>larem eijeoiwrijojowjsf,knlkoadfnnlkdmzofj,mfskknldsfjljgs,z.;jfsndkjsfnksjkjskkkkkkkkkkkkkkkkdmfnnn z m ncx ,zmvn,zm,                                ,znfkkkkkkkkkknlzknflkkkkkkkkkkkkkkkkkkkkkkkkkk
    fskklajalllllllllllllllllllllmndfffffffffffjeeeeeeeeeeeeendddddddddd</p>
    <h1>Welcome user :{user.name}</h1>
    </div>
        </Base>
    </>

  )
}

export default ProfileInfo;