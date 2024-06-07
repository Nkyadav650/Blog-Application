import React, { useEffect, useMemo, useRef, useState } from 'react'
import Base from './Base'
import {Button, Card,CardBody,Container,Form,Input,Label} from 'reactstrap';
import { loadAllCategories } from '../Service/Category-Service';
import JoditEditor from 'jodit-react';
import { createPost as doCreatePost, uploadPostImage } from '../Service/Post-Service';
import { getCurrentUserDetails } from '../Auth/Index';
import { toast } from 'react-toastify';
const AddPost =() =>{

  const [post,setPost]=useState(
    {
      title:'',
      content:'',
      categoryId:''
    })
    const [image,setImage]=useState(null)
    const editer = useRef(null);
 const [user,setUser]=useState(undefined);
  const[categories,setCategories]=useState([])

   useEffect(()=>{
    setUser(getCurrentUserDetails())
    loadAllCategories().then((data)=>{
      console.log(data)
      setCategories(data)
    }).catch(error=>{
      console.log(error)})
   },[])

   // reset post function
   const resetPost =()=>{
    window.location.reload();
   }

   //create field changed function
   const fieldChanged =(event)=>{
    setPost({...post,[event.target.name]:event.target.value})
   }

   //for content field change
   const ContentFieldChanged = (event) => {
    console .log(event);
    setPost({ ...post, 'content': event});
  }
  
  //create post function
  const createPost =(event)=>{
    event.preventDefault();
    

    if(post.title.trim()===''){
      toast.error("post is required !!")
      return;
    }
    if(post.content.trim()===''){
      toast.error("content is required !!")
      return;
    }
    if(post.categoryId.trim()===''){
      toast.error("select any category !!" )
      return;
    }

    //submit the form on server
    post['userId']=user.userId
    doCreatePost(post).then(data=>{
      console.log(data)
      

      //if (image.match(/\.(jpg|jpeg|png|gif)$/)) {
      uploadPostImage(image,data?.Data?.postId).then(data=>{
        setImage(null)
        toast.success("image uploaded successfully !!")
      }).catch(error=>{
        toast.error("error in image uploading... !!")
        console.log(error)
      })
    // }else{
    //   toast.error("image supports only (jpg|jpeg|png|gif) files ")
    //   return;
    // }
      toast.success("post Created !!")
      setPost({
      title:'',
      content:'',
      categoryId:''})
      
    }).catch((error)=>{
      toast.error("error")
      console.log(error)
    })
  }
   
  //handling file change event
  const imageFileChange = (event) =>{
    console.log(event.target.files[0])
    setImage(event.target.files[0])
  }
  return (
  <>
  <div className='wrapper'>
    <Card className='shadow-sm mt-4'>
        <CardBody>
            <h3>Are you want to post somthing ?</h3>
            <Form onSubmit={createPost}>

            <div>
                <Label for='title'>Post Title</Label>
               <Input type='text' 
               placeholder='Enter here' 
               id='title' 
               name='title'
               className='rounded-0' 
               onChange={(event)=>fieldChanged(event)} 
               /> 
            </div>

            <div className='my-3'>
                <Label for='content'>Post Content</Label>
                <JoditEditor 
                  ref={editer}
                  value={post.content}
                  id='content'                 
                  onChange={(event) => ContentFieldChanged(event)}
                />
               {/* <Input type='textarea' placeholder='Enter here' id='content' className='rounded-0'
               style={{height:'300px'}} />  */}
            </div>
            <div className='my-3'>
              <Label for='image'>Select image</Label>
              <Input type='file' id='image' name='image'
              
                onChange={imageFileChange} multiple
              />
            </div>
            <div className='my-3'>
                <Label for='categoryId'>Post Category</Label>
                <Input
                  type='select'
                  placeholder='Enter here'
                  id='categoryId'
                  className='rounded-0'
                  name='categoryId'
                  onChange={(event)=>fieldChanged(event)} 
                  defaultValue={0}
                >
                <option disabled value={0}>--select category--</option>
               {
                categories?.Data && categories.Data.map(category => (
                 <option value={category.categoryId} key={category.categoryId}>
                 {category.categoryTitle}
                 
                 </option>
                  ))
                }

                {/* <option>programming</option>
                <option>enterainment</option>
                <option>News</option>
                <option>Polytics</option>
                <option>History</option>
                <option>Mathematics</option>
                <option>Science</option>
                <option>speaking Language</option>
                <option>trading</option>
                <option>Electrical</option>
                <option>Mechanical</option> */}
               </Input> 
            </div>
            <Container className='text-center'>
              <Button  className='rounded-0' color='primary'>Create Post</Button>
              <Button  onClick={resetPost} className='rounded-0 ms-2' color='danger'>Reset Post</Button>
            </Container>
           
            </Form>
          
        </CardBody>
    </Card>
 </div>

  </>
   
   
  )
}

export default AddPost