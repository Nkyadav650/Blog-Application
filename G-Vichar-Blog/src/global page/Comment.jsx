const Comment =()=>{
    return(
        <>
            <Input
            type='text'
            value={editedContent}
            onChange={(event) => setEditedContent(event.target.value)}
          />
        </>
    )
}