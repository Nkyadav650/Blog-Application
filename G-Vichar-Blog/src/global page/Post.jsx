import React, { useEffect, useState } from 'react';
import Base from '../components/Base';
import { Button, Card, CardBody, CardText, Col, Container, Input, Row } from 'reactstrap';
import { Link, useParams } from 'react-router-dom';
import { createComment, deleteComments, loadPost } from '../Service/Post-Service';
import { toast } from 'react-toastify';
import { BASE_URL } from '../Service/Helper';
import { getCurrentUserDetails, isLoggedIn } from '../Auth/Index';

const Post = () => {
  // Destructure the specific parameter you want from the object returned by useParams
  const { postId } = useParams();
  const [post, setPost] = useState(null);
  const [comment,setComment]=useState({content:''})

  useEffect(() => {
    // load post of postId
    loadPost(postId)
      .then((data) => {
        console.log(data);
        setPost(data);
      })
      .catch((error) => {
        console.log(error);
        toast.error('Something went wrong while loading the post.');
      });
  }, [postId]); // Include postId in the dependency array

 // Function to format timestamp to a human-readable date
const formatTimestamp = (timestampArray) => {
  if (!timestampArray || timestampArray.length !== 7) {
    return "Invalid Date";
  }

  const [year, month, day, hour, minute, second, millisecond] = timestampArray;
  const date = new Date(year, month - 1, day, hour, minute, second, millisecond);

  // Adjust the options based on your desired date format
  const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric', second: 'numeric' };

  return date.toLocaleString(undefined, options);
};

 // submit comment function
const submitComment = () => {
  if(!isLoggedIn()){
    toast.error("You are not Login")
  }
  if(comment.content.trim()===''){
    return;
  }

// calling createComment function from service/post-service
  createComment(comment, post?.Data?.postId,getCurrentUserDetails().userId)
    .then((response) => {
       setPost({ ...post,
        Data: {...post.Data,
          comment: [...(post.Data.comment || []), response.data.NewComment],
        },}) 
        setComment({content:''})
    })  
    .catch((error) => {
      console.log("API Error:", error);
    });
};

//Edit comment from post
const editComment =() =>{
  if(!isLoggedIn()){
    toast.error("You are not Login")
    return;
  }
  
}
// delete comment from post
const deleteComment = (commentsId) => {
  if (!isLoggedIn()) {
    toast.error("You are not logged in");
    return;
  }
  console.log("commentsId Value: " + commentsId);
  deleteComments(commentsId).then(res=>{
    console.log(res.data);
    if(res.data!==null && res.data!==undefined){
      toast.success("comments deleted")
    }
  
  });
};


  return (
    <Base>
      <Container className='mt-4'>
        <Link to={'/'}>Home</Link> / {post && ( <Link to={"/"}>{post.Data.title}</Link> )}
        <Row>
          <Col md={{ size:12}}>
          <Card className='mt-3 ps-2 border-0 shadow-sm'>
          { (post)&&(
              <CardBody>
                <CardText>
                  Posted By <b>{post.Data.user.userName}</b> At <b>{formatTimestamp(post?.Data?.timeStamp)}</b>
                </CardText>
                <CardText>
                  <span className='text-muted'>{post.Data.category.categoryTitle}</span>
                </CardText>
                <div className='divder' style={{
                  width:"100%",
                  height:'1px',
                  background:"#e2e2e2"
                }}></div>
                <CardText>
                {<h3>{post.Data.title}</h3>}
                 
                </CardText>
                <div className='image-container container mt-5 shadow' style={{maxWidth:'50%'}}>
                <img className='img-fluid' src={BASE_URL+'/posts/image/'+post.Data.imageName}></img>
                </div>
                <CardText className='mt-5' dangerouslySetInnerHTML={{__html:post.Data.content}}>
                
                </CardText>
                
              </CardBody>
            )}
          </Card>
          </Col>
        </Row>

        <Row>
    <Col md={{size: 6, offset: 2}}>
        <h3>Comments</h3>
        <Card className='mt-4 border-0' >
                <CardBody>
                    <CardText>
                        <Input type='text' 
                         placeholder='Enter here'
                         value={comment.content}
                         onChange={(event)=>{setComment({content:event.target.value})}}
                         
                        //  onkeyDown is use to execute by clicking keywords of keyboard
                         onKeyDown={(event) => {
                          if (event.key === 'Enter') {
                            submitComment();
                          }}}
                         />
                        <Button onClick={submitComment} className='mt-2'>send</Button>
                    </CardText>                   
                </CardBody>
            </Card>
            {/* in this line  */}
        {post?.Data?.comment.slice().reverse().map((comment,index) => (
            <Card className='mt-3 border-0' key={index} >
               <CardBody>
                   <CardText>
                      {comment.content}
                      <Button  className="ms-2" onClick={() => deleteComment(comment.id)}>delete</Button>
                      <Button className="ms-2" onClick={()=>editComment()}>Edit</Button>
                    </CardText>
               </CardBody>
           </Card>
           
        ))}
     

    </Col>
</Row>
      </Container>
    </Base>
  );
};

export default Post;
