import React, { useEffect, useState } from 'react';
import { loadAllPost } from '../Service/Post-Service';
import { Col, Row } from 'reactstrap';
import ViewPost from './ViewPost';
import { toast } from 'react-toastify';
import InfiniteScroll from 'react-infinite-scroller';

const NewFeed = () => {
  const [postContent, setPostContent] = useState(null);
  const [hasMore, setHasMore] = useState(true);
  const [pageNumber, setPageNumber] = useState(0);
  const [loading, setLoading] = useState(false);

  const fetchData = async (pageNumber, pageSize) => {
    try {
      const data = await loadAllPost(pageNumber, pageSize);
      console .log("data fetched !!")
      console.log(data);
      
      return data;
    } catch (error) {
      console.log(error);
      toast.error('Error in loading post');
      throw error; // Re-throw the error to be caught by the caller
    }
  };

  const loadMore = () => {
    console.log("hasMore : "+hasMore)
    console.log("loading : "+loading)
    if( pageNumber===postContent.Data.totalPage){
      setHasMore(false)
      setLoading(true)
      
    }
    if (hasMore && !loading ) {
      setLoading(true);
      console.log("before fetch data : "+pageNumber);
      
      setPageNumber(pageNumber+1)
      fetchData(pageNumber, 5)
        .then((data) => {
          
         // setHasMore(data.Data && !data.Data.lastPage  );
        })
        .catch((error) => {
          console.log(error);
          toast.error('Error in loading more posts');
        })
        .finally(() => {
          setLoading(false);
        });
    }
  };
  
  useEffect(() => {
    fetchData(pageNumber, 5)
      .then((data) => {
        setPostContent((prevData) => ({
         
          Data: {
            ...data.Data,
            content: prevData ? [...prevData.Data.content, ...data.Data.content] : data.Data.content,
          },
        }));
        setHasMore(data.Data && !data.Data.lastPage);
      })
      .catch((error) => {
        console.log(error);
        toast.error('Error in loading post');
      });
  }, [pageNumber+1]);

  return (
    <>
      <div className="container-fluid">
        <Row>
          <Col md={{ size: 10,}}>
            <h1>Blog Content ({postContent?.Data?.totalElement})</h1>

            {postContent?.Data?.content ? (
              <InfiniteScroll
                pageStart={pageNumber}
                loadMore={loadMore}
                hasMore={hasMore}
                loader={<div className="loader" key={0}>Loading ...</div>}
                endmessage={<div>No more items</div>}
              >
                {postContent?.Data?.content.map((post, index) => (
                    <ViewPost post={post} key={index} />
                  ))}

              </InfiniteScroll>
            ) : (
              <div>Loading...</div>
            )}
          </Col>
        </Row>
      </div>
    </>
  );
};

export default NewFeed;
