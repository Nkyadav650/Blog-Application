import React from 'react'
import Base from '../components/Base'
import { useParams } from 'react-router-dom'
import { useEffect } from 'react';
import { Col, Row } from 'reactstrap';
import CategorySideMenu from '../components/CategorySideMenu';
import { loadPostCategoryWise } from '../Service/Post-Service';
import { useState } from 'react';
import { toast } from 'react-toastify';
import ViewPost from '../components/ViewPost';

const PostByCategory = () => {

  const{categoryId}=useParams();
  const[posts,setPosts]=useState([])
  useEffect(()=>{
   console.log(categoryId); 
   loadPostCategoryWise(categoryId).then(data=>{
    setPosts(data.Data)
    console.log(data)
   }).catch(error=>{
    console.log(error)
    toast.error("Error in loadpost in category wise")
   })
  },[categoryId])
  return (
    <>
    <Base>
    <Row className='mt-3'>
      <Col md={2} className='pt-5 mt-3'>
        <CategorySideMenu/>
      </Col>
      <Col md={10}>
      {<h1> Blog Content({(posts.length)})</h1>}
      {posts && posts.map((post,index)=>{
         return( <ViewPost post={post} key={index} />)
      })}
      {
        posts.length<=0?<h1>No post in this Category</h1>:''
      }
      </Col>
    </Row>
       
    </Base>
       
    </>
  
  )
}

export default PostByCategory;