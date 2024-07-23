import React from 'react'
import Base from '../components/Base';
import userContext from '../context/userContext';
import { useContext } from 'react';
import ProfileImg from '../assets/profile.jpg'
import './profile.css'
const ProfileInfo = () => {

  const userData = useContext(userContext)

  if (!userData) {
    return <div>Loading...</div>;
  }

  return (
    <>

      <Base>

        <div className='profile'>
          <h1>User Information</h1>
          <img src={ProfileImg} alt="Profile" />

          <div>
            <table>
              <tr>
                <th>User Id:</th> <td>{userData.user.userId}</td>
              </tr>
              <tr>
                <th>User Name:</th><td>{userData.user.userName}</td>
              </tr>
              <tr>
                <th>Email:</th><td>{userData.user.email}</td>
              </tr>
              <tr>
                <th>Contact:</th><td>{userData.user.contact}</td>
              </tr>
              <tr>
                <th>About:</th><td>{userData.user.about}</td>
              </tr>
            </table>

          </div>

        </div>
      </Base>
    </>

  )
}

export default ProfileInfo;