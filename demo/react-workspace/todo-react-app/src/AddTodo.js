import React, { useState } from "react";
import { Button, TextField } from "@mui/material";
import Grid2 from "@mui/material/Unstable_Grid2"; 

const AddTodo = (props) => {
    // 사용자의 입력을 저정할 오브젝트
    const [item, setItem] = useState({ title: ""});
    const addItem = props.addItem;

    // onButtonClick 함수 작성
    const onButtonClick = () => {
        addItem(item); // addItem 함수 사용
        setItem({ title: "" });
    }

    // onInputChange 함수 작성
    const onInputChange = (e) => {
        setItem({title: e.target.value});
        console.log(item);
        };

    // enterKeyEventHandler 함수
    const enterKeyEventHandler = (e) => {
        if (e.key === 'Enter') {
          onButtonClick();
        }
    };

    // onInputChange 함수 TextField에 연결    
    return (
        //Grid를 Grid2로 변경
         <Grid2 container style={{ marginTop: 20 }}>
            <Grid2 xs={11} md={11} item style={{ paddingRight: 16 }}>
                <TextField placeholder="Add Todo here" fullWidth  
                onChange={onInputChange}
                // onKeyPress를 onKeyDown으로 변경 
                onKeyDown={enterKeyEventHandler}
                value={item.title}/>

            </Grid2>
            <Grid2 xs={1} md={1} item>
                <Button fullWidth style={{ height: '100%' }} color="secondary" variant="outlined">
                    +
                </Button>
            </Grid2>
        </Grid2>
    );
    
}
export default AddTodo;