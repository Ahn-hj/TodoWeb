import React, { useState } from "react";
import {
  ListItem,
  ListItemText,
  //ListItemSecondaryAction대신 ListItem의 secondaryAction prop사용
  InputBase,
  Checkbox,
  IconButton,
} from "@mui/material";
import DeleteOutlined from "@mui/icons-material/DeleteOutlined";

const Todo = (props) => {
    const [item, setItem] = useState(props.item);
    const [readOnly, setReadOnly] = useState(true);
    const deleteItem = props.deleteItem;
    const editItem = props.editItem;
  
    const editEventHandler = (e) => {
      setItem({...item, title: e.target.value});
      editItem({...item, title: e.target.value});
    };
  
    const checkboxEventHandler = (e) => {
      setItem({...item, done: e.target.checked});
      editItem({...item, done: e.target.checked});
    }
  
    const deleteEventHandler = () => {
      deleteItem(item);
    };
  
    const turnOffReadOnly = () => {
      setReadOnly(false);
    }
  
    const turnOnReadOnly = (e) => {
      if (e.key === "Enter") {
          setReadOnly(true);
      }
    };
  
    return (
      <ListItem
        secondaryAction={
          <IconButton 
            edge="end" 
            aria-label="delete Todo"
            onClick={deleteEventHandler}
          >
            <DeleteOutlined />
          </IconButton>
        }
      >
        <Checkbox 
          checked={item.done}
          onChange={checkboxEventHandler} 
        />
        <ListItemText>
          <InputBase
            inputProps={{ 
                "aria-label": "naked",
                readOnly: readOnly 
            }}
            onClick={turnOffReadOnly}
            onKeyDown={turnOnReadOnly}
            onChange={editEventHandler}
            type="text"
            id={item.id}
            name={item.id}
            value={item.title}
            multiline={true}
            fullWidth={true}
          />
        </ListItemText>
      </ListItem>
    );
  };
  
  export default Todo;