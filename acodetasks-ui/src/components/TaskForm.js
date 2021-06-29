import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import AuthService from '../api/AuthService';
import TaskService from '../api/TaskService';

class TaskForm extends Component {
    constructor(props){
        super(props);

        this.state = {
            task: {
                id: 0,
                description: '',
                whenToDo: ''
            },
            redirect: false,
            buttonName: 'Cadastrar'
        }

        this.onSubmitHandler = this.onSubmitHandler.bind(this);
        this.onInputChangeHandler =  this.onInputChangeHandler.bind(this);
    }

    componentDidMount(){
        const editId = this.props.match.params.id;
        if(editId){
            const task = TaskService.load(~~editId);
            this.setState({ task: task, buttonName: 'Alterar' });
            
        }
    }

    onSubmitHandler(event){
        event.preventDefault();
        TaskService.save(this.state.task);
        this.setState({ redirect: true });       
    }

    onInputChangeHandler(event){
        const field = event.target.name;
        const value = event.target.value;
        this.setState(prevState => ({ task: {...prevState.task, [field]: value}}));
    }

    render() {
       
        if(!AuthService.isAuthenticated()){
            return <Redirect to="/login" />
        }

        if(this.state.redirect){
            return <Redirect to={"/"}/>
        }

        return (
            <div>
                <h1>Cadastro da Tarefa</h1>
                <form onSubmit={this.onSubmitHandler}>
                    <div className="form-group">
                        <label htmlFor="description">Descrição</label>
                        <input type="text"
                                className="form-control"
                                name="description"
                                value={this.state.task.description}
                                placeholder="Digite a descrição"
                                onChange={this.onInputChangeHandler}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Data</label>
                        <input type="date"
                                className="form-control"
                                name="whenToDo"
                                value={this.state.task.whenToDo}
                                placeholder="Informe a data"
                                onChange={this.onInputChangeHandler}/>
                    </div>
                    <button type="submit" className="btn btn-primary mr-1">{this.state.buttonName}</button>
                    <button 
                        type="submit" 
                        className="btn btn-primary"
                        onClick={() => this.setState({ redirect: true })}>
                            Cancelar 
                    </button>
                </form>
            </div>
        );
    }
}

export default TaskForm;