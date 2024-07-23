import React, { useEffect, useMemo, useRef, useState } from 'react';
import Base from './Base';
import { Button, Card, CardBody, Container, Form, Input, Label } from 'reactstrap';
import { loadAllCategories } from '../Service/Category-Service';
import JoditEditor from 'jodit-react';
import { createPost as doCreatePost, uploadPostImage } from '../Service/Post-Service';
import { getCurrentUserDetails } from '../Auth/Index';
import { toast } from 'react-toastify';

const AddPost = () => {
  const [post, setPost] = useState({
    title: '',
    content: '',
    categoryId: ''
  });
  const [image, setImage] = useState(null);
  const editor = useRef(null);
  const [user, setUser] = useState(undefined);
  const [categories, setCategories] = useState([]);
  const [showForm, setShowForm] = useState(false);

  useEffect(() => {
    setUser(getCurrentUserDetails());
    loadAllCategories()
      .then((data) => {
        console.log(data);
        setCategories(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const resetPost = () => {
    window.location.reload();
  };

  const fieldChanged = (event) => {
    setPost({ ...post, [event.target.name]: event.target.value });
  };

  const contentFieldChanged = (event) => {
    console.log(event);
    setPost({ ...post, content: event });
  };

  const createPost = (event) => {
    event.preventDefault();

    if (post.title.trim() === '') {
      toast.error('Title is required !!');
      return;
    }
    if (post.content.trim() === '') {
      toast.error('Content is required !!');
      return;
    }
    if (post.categoryId.trim() === '') {
      toast.error('Select any category !!');
      return;
    }

    post['userId'] = user.userId;
    doCreatePost(post)
      .then((data) => {
        console.log(data);

        uploadPostImage(image, data?.Data?.postId)
          .then(() => {
            setImage(null);
            toast.success('Image uploaded successfully !!');
          })
          .catch((error) => {
            toast.error('Error in image uploading... !!');
            console.log(error);
          });

        toast.success('Post Created !!');
        window.location.reload();
      })
      .catch((error) => {
        toast.error('Error');
        console.log(error);
      });
  };

  const imageFileChange = (event) => {
    console.log(event.target.files[0]);
    setImage(event.target.files[0]);
  };

  return (
    <>
      <div className="wrapper">
        <Card className="shadow-sm mt-4">
          <CardBody>
            <h3 onClick={() => setShowForm(!showForm)} style={{ cursor: 'pointer' }}>
              Are you want to post something?
            </h3>
            {showForm && (
              <Form onSubmit={createPost}>
                <div>
                  <Label for="title">Post Title</Label>
                  <Input
                    type="text"
                    placeholder="Enter here"
                    id="title"
                    name="title"
                    className="rounded-0"
                    onChange={(event) => fieldChanged(event)}
                  />
                </div>

                <div className="my-3">
                  <Label for="content">Post Content</Label>
                  <JoditEditor
                    ref={editor}
                    value={post.content}
                    id="content"
                    onChange={(event) => contentFieldChanged(event)}
                  />
                </div>

                <div className="my-3">
                  <Label for="image">Select image</Label>
                  <Input type="file" id="image" name="image" onChange={imageFileChange} multiple />
                </div>

                <div className="my-3">
                  <Label for="categoryId">Post Category</Label>
                  <Input
                    type="select"
                    placeholder="Enter here"
                    id="categoryId"
                    className="rounded-0"
                    name="categoryId"
                    onChange={(event) => fieldChanged(event)}
                    defaultValue={0}
                  >
                    <option disabled value={0}>
                      --select category--
                    </option>
                    {categories?.Data &&
                      categories.Data.map((category) => (
                        <option value={category.categoryId} key={category.categoryId}>
                          {category.categoryTitle}
                        </option>
                      ))}
                  </Input>
                </div>

                <Container className="text-center">
                  <Button className="rounded-0" color="primary">
                    Create Post
                  </Button>
                  <Button onClick={resetPost} className="rounded-0 ms-2" color="danger">
                    Reset Post
                  </Button>
                </Container>
              </Form>
            )}
          </CardBody>
        </Card>
      </div>
    </>
  );
};

export default AddPost;
