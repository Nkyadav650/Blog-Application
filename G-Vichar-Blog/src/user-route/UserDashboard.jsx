import React, { useState, useEffect } from 'react';
import Base from '../components/Base';
import AddPost from '../components/AddPost';
import { Container, ListGroupItem } from 'reactstrap';
import { getCurrentUserDetails } from '../Auth/Index';
import { loadPostUserWise } from '../Service/Post-Service';
import { toast } from 'react-toastify';
import ViewPost from '../components/ViewPost';

const UserDashboard = () => {
  const [user, setUser] = useState([]);
  const [posts, setPosts] = useState([]);
  

  useEffect(() => {
    console.log(getCurrentUserDetails());
    setUser(getCurrentUserDetails());

    loadPostUserWise(getCurrentUserDetails().userId)
      .then((data) => {
        console.log(data);
        setPosts(data.Data ); // Set to an empty array if data.Data is undefined
      })
      .catch((error) => {
        console.log(error);
        toast.error('Error in user post loading !!');
      });
  }, []);

  // function to delete post
   function deleteUserPost(post){

    // for delete the post by user by calling url
    deletePost(post.postsId).then(data=>{
      console.log(data)
      toast.success("post deleted !!")
    }).catch(error=>{
      console.log(error)
    })

  }
  return (
    <>
      <Base>
        <Container>
          <AddPost/>
          <h1 className='mt-3'>Post Count: ({posts.length})</h1>
          {posts.map((post, index) => (
            <ViewPost post={post} key={index} deleteUserPost={deleteUserPost} />
          ))}
        </Container>
      </Base>
    </>
  );
};

export default UserDashboard;
