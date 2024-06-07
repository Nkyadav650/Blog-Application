import React from 'react'
import { useState } from 'react'
import { ListGroup, ListGroupItem } from 'reactstrap'
import { loadAllCategories } from '../Service/Category-Service'
import { toast } from 'react-toastify'
import { useEffect } from 'react'
import { Link } from 'react-router-dom'

const CategorySideMenu = () => {

    const [category, setCategory] = useState([])

    useEffect(() => {
        loadAllCategories().then(data => {
            console.log(data)
            setCategory(data.Data)
            console.log("category : " + category)
        }).catch(error => {
            console.log(error);
            toast.error("error in loading categories")
        })
    }, [])

    return (
        <div>
            <ListGroup>
                <ListGroupItem tag={Link} to="/" action={true} className='border-0'>
                    All Blog
                </ListGroupItem>
                {category && category.map((cat, index) => {
                    return (
                        <ListGroupItem tag={Link} to={'/category/' + cat.categoryId} key={index} className='border-0 mt-1'>
                            {cat.categoryTitle}
                        </ListGroupItem>
                    )
                })}

            </ListGroup>
        </div>
    )
}

export default CategorySideMenu;