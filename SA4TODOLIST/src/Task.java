public class Task {
    //atributos
    String description;
    boolean done;
    //construtor
    public Task(String description) {
        this.description = description;
        this.done = false;
    }
    //gets and set
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
    
    
}
