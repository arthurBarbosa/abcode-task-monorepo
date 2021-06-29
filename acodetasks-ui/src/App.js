import { Component } from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Login from "./components/Login";
import NavBar from "./components/NavBar";
import TaskForm from "./components/TaskForm";
import TaskListTable from "./components/TaskListTable";



function App() {
  return (
   <BrowserRouter>
      <div className="App">
         <NavBar />
         <div className="container">
            <Switch>
            
              <Route exact path="/login" component={Login} />
              <Route exact path="/form" component={TaskForm} />
              <Route exact path="/form/:id" component={TaskForm} />
              <Route path="/" component={TaskListTable}  />
            
            </Switch>
        </div>
      </div>
   </BrowserRouter>
  );
}

export default App;
