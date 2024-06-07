import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import { BrowserRouter ,Routes, Route } from 'react-router-dom';
import './App.css'
import 'react-toastify/dist/ReactToastify.css';
import { ToastContainer, } from 'react-toastify';
import Home from './components/Home';
import Login from './global page/Login';
import Signup from './global page/Signup';
import About from './global page/About';
import CustomNavbar from './components/CustomNavbar';
import Services from './global page/Services';
import Contact from './global page/Contact';
import UserDashboard from './user-route/UserDashboard';
import PrivateRoute from './components/PrivateRoute';
import ProfileInfo from './user-route/ProfileInfo';
import PostByCategory from './global page/PostByCategory';
import Post from './global page/Post';
import UserProvider from './context/userProvider';
function App() {
  
  return (
    <>
      <div>
      <UserProvider>
        <BrowserRouter>
        <ToastContainer/>
        <Routes>
          <Route path="/"  element={<Home/>}/>
          <Route path="/login"  element={<Login/>}/>
          <Route path="/signup"  element={<Signup/>}/>
          <Route path="/about"  element={<About/>}/>
          <Route path="/navbar" element={<CustomNavbar/>}/>
          <Route path="/services" element={<Services/>}/>
          <Route path="/contact" element={<Contact/>}/>
          <Route path="/post/:postId" element={<Post/>}/>
          <Route path='/category/:categoryId' element={<PostByCategory/>} />

          <Route path='/user' element={<PrivateRoute/>}>
          <Route path='dashboard' element={<UserDashboard/>}/>
          <Route path='profileInfo' element={<ProfileInfo/>}/>
          </Route>
          
        </Routes>
       
        </BrowserRouter>
        </UserProvider>
       </div>
    </>
  )
}

export default App;
