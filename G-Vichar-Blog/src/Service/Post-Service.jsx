import { myAxios, privateAxios } from "./Helper";
import { getToken } from "../Auth/Index";
 
// create post
export const createPost = (postData) => {
  const token = getToken();
  
  return privateAxios.post(`/posts/savePost/user/${postData.userId}/category/${postData.categoryId}`, postData)
    .then(response => response.data)
    .catch(error => {
      throw error; // Rethrow the error to propagate it to the caller
    });
};

//get all post 
export const loadAllPost =(pageNumber,pageSize)=>{
return myAxios.get(`/posts/allPosts?pageNumber=${pageNumber}&pageSize=${pageSize}&sortBy=timeStamp&sortDir=desc`).then(response=>response.data)
}

//get single post details
export const loadPost =(postId)=>{
  return myAxios.get("/posts/getBy/"+postId).then(response=>response.data)
}

// upload post image
export const uploadPostImage =(image,postId) =>{
  let formData =new FormData();
  formData.append("image",image);

  return privateAxios.put(`/posts/image/upload/`+postId,formData,
  {headers:{'Content-Type':'multipart/form-data'}}
  ).then((response)=>response.data)
};

// get post  category wise
export const loadPostCategoryWise =(categoryId) =>{
return privateAxios.get(`/posts/category/${categoryId}/posts`).then(res=>res.data)
}

// get post by user wise
export const loadPostUserWise =(userId)=>{
  return privateAxios.get(`/posts/user/posts/${userId}`).then(res=>res.data)
}

//delete post by user
export const deletePost =(postsId)=>{
  return privateAxios.delete(`/post/deletePosts/${postsId}`).then(res=>res.data)
}

//create comments on post
export const createComment=(comment,postId,userId)=>{
  return myAxios.post(`/comments/saveComments/${postId}/${userId}`,comment)
}

//delete comments on post
export const deleteComments =(commentsId)=>{
  console.log("inside post-service deleteComments commentId: "+commentsId)
  return myAxios.delete(`/comments/delete/`+commentsId).then(res=>res.data)
}
// create likes on post
export const createLikes =(likes,postId,userId)=>{
  console.log("inside post-service createLikes likes: "+likes +"postId : "+postId + "userId: " +userId)
  return myAxios.post(`/likes/share/subscribe/saveLikes/${postId}/${userId}`,likes).then(res=>res.data)
}