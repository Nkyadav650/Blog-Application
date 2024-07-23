import React, { useEffect, useState } from 'react';
import {NavLink as ReactLink, useNavigate} from 'react-router-dom';
import { CgProfile } from "react-icons/cg";
import { IoIosLogOut } from "react-icons/io";
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  NavbarText,
} from 'reactstrap';
import { doLogout, getCurrentUserDetails, isLoggedIn } from '../Auth/Index';

const CustomNavbar = () => {
  const [isOpen, setIsOpen] = useState(false);
  const toggle = () => { setIsOpen(!isOpen); };
  let navigate= useNavigate()
  const [login,setLogin]=useState(false);
  const [user,setUser]=useState(undefined);
  useEffect(()=>{
    setLogin(isLoggedIn())
    setUser(getCurrentUserDetails())
  },[login])
const Logout =()=>{
doLogout(()=>{
  //logged out
  setLogin(false)
  navigate("/")
})
}
 
  return (
    <div>
      <Navbar className="fixed-top px-2" color="light" light expand="md">
        <NavbarBrand >G-Vichar</NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="me-auto" navbar>
            <NavItem>
              <NavLink  tag={ReactLink} to="/">Home</NavLink>
            </NavItem>
            <NavItem>
              <NavLink  tag={ReactLink} to="/about">About</NavLink>
            </NavItem>
            <NavItem>
              <NavLink  tag={ReactLink} to="/services">Services</NavLink>
            </NavItem>
            <NavItem>
              <NavLink  tag={ReactLink} to="/category">Category</NavLink>
            </NavItem>
            <UncontrolledDropdown nav inNavbar>
              <DropdownToggle nav caret>
               More
              </DropdownToggle>
              <DropdownMenu end> {/* Use "end" instead of "right" */}
                <DropdownItem tag={ReactLink} to="/services">Services</DropdownItem>
                <DropdownItem tag={ReactLink} to="/contact">Contact</DropdownItem>
                <DropdownItem divider />
                <DropdownItem>Youtube</DropdownItem>
                <DropdownItem>Youtube</DropdownItem>
                <DropdownItem>Youtube</DropdownItem>
              </DropdownMenu>
            </UncontrolledDropdown>
          </Nav>
          
          <Nav navbar>
          {login&&(
            <>
            
            <NavItem>
              <NavLink tag={ReactLink} to="/user/dashboard">
              {"Hello "+user.userName} 
              </NavLink>
            </NavItem> 
            <NavItem>
              <NavLink tag={ReactLink} to="/user/profileInfo">
              <CgProfile style={{fontSize:"30px"}}/>
              </NavLink>
            </NavItem>
            <NavItem onClick={Logout}>
              <NavLink  >
             <IoIosLogOut style={{fontSize:"30px", cursor:'pointer'}}/>
              </NavLink>
            </NavItem> 
            </>
             )
          }{
            !login &&(
              <>
              <NavItem>
              <NavLink tag={ReactLink} to="/login">
                Login
              </NavLink>
            </NavItem>             
            <NavItem>
              <NavLink tag={ReactLink} to="/signup">
                Signup
              </NavLink>
            </NavItem>
              </>
            )
          }
          

          </Nav>
          
        </Collapse>
      </Navbar>
    </div>
  );
};

export default CustomNavbar;
