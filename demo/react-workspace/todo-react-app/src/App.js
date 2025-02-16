import './App.css';
import Todo from "./Todo";
import React, { useState } from "react";
import { List, Paper } from "@mui/material";
import AddTodo from "./AddTodo";

function App() {
  // const [items, setItems] = useState([
  //   {
  //     id: "0",
  //     title: "Hello World 1",
  //     done: true,
  //   },
  //   {
  //     id: "1",
  //     title: "Hello World 2",
  //     done: true,
  //   },
  // ]);

  const addItem = (item) => {
    item.id = "ID-" + items.length; // key를 위한 id
    item.done = false; // done 초기화
    // 업데이트는 반드시 setItems 후 새 배열을 만들기
    setItems([...items, item]);
    console.log("items : ", items);
    };

    const editItem = () => {
      setItems([...items]);
    };

    const deleteItem = (item) => {
      // 삭제 아이템 찾기
      const newItems = items.filter(e => e.id !== item.id);
      // 삭제 아이템 제외한 아이템 배열 정리
      setItems([...newItems]);
    }

  let todoItems = items.length > 0 && (
      <Paper style={{ margin: 16 }}>
        <List>
          {items.map((item) => (
            <Todo item={item} key={item.id} />
            ))}
        </List>
     </Paper>
    );
    return (<div className="App">
        <Container maxWidth="md">
          <AddTodo addItem={addItem} />
          <div className="TodoList">{todoItems}</div>
        </Container>
    </div>);
}
export default App;
