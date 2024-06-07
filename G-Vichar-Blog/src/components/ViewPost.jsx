import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { Button, Card, CardBody, CardText } from 'reactstrap'
import { getCurrentUserDetails, isLoggedIn } from '../Auth/Index'

const ViewPost=({post={userId:-1,title:"this is default post title",content:"this is default content"},deleteUserPost})=> {

  const [user, setUser]=useState(null);
  const [loggin,setLoggin]=useState(null); 


  // for checking user is loggin or not and get current user details
  useEffect(()=>{
    setLoggin(isLoggedIn())
    setUser(getCurrentUserDetails())
  },[])

  
  return (
   <Card className='border-0 shadow-sm mt-3'>
    <CardBody>
        <h1>{post.title}</h1>
        <CardText   dangerouslySetInnerHTML={{__html: post.content.substring(0,30)+"...."}}>
           
        </CardText>
        <div>
            <Link className='btn btn-secondary rounded-0' to={"/post/"+post.postId}>Read More</Link>

            {
              isLoggedIn && (user && user.userId ? <Button className='btn btn-danger ms-2' onClick={() => deleteUserPost(post)}>Delete</Button>:'')
            }
        </div>
    </CardBody>
   </Card>
  )
}

export default ViewPost