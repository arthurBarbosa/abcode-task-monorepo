import axios from "axios";
import { API_ENDPOINT } from "../constants";
import AuthService from "./AuthService";

class TaskService {
    constructor(){
        this.tasks = [
            {id:1, description: "Tarefa 1", whenToDo: "2021-01-13", done: true},
            {id:2, description: "Tarefa 2", whenToDo: "2021-01-13", done: false},
            {id:3, description: "Tarefa 3", whenToDo: "2021-01-13", done: false},
        ]
    }

    list(onFetch, onError){
        axios.get(`${API_ENDPOINT}/tasks?sort=whenToDo,asc`, this.buildAuthHeader())
                .then(response => onFetch(response.data.content))
                .catch(e => onError(e));
    }

    delete(id, onDelete, onError){
          axios.delete(`${API_ENDPOINT}/tasks/${id}`, this.buildAuthHeader())
                .then(() => onDelete())     
                .catch(e => onError(e));
    }

    save(task){
        if(task.id !== 0 ){
            this.tasks = this.tasks.map(t => task.id !== t.id ? t : task);
        
        } else {
            const taskId = Math.max(...this.tasks.map(t => t.id)) + 1;
            task.id = taskId;
            this.tasks.push(task);
        }
        
    }

    load(id, onLoad, onError){
        axios.get(`${API_ENDPOINT}/tasks/${id}`, this.buildAuthHeader())
            .then(response => onLoad(response.data))
            .catch(e => onError(e));
    }

    buildAuthHeader() {
        return {
            headers: {
                'Authorization': `Bearer ${AuthService.getJWTToken()}`
            }
        }
    }


}

export default new TaskService();